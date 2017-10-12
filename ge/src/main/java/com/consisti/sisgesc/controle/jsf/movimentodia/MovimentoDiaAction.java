package com.consisti.sisgesc.controle.jsf.movimentodia;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.controle.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.controle.jsf.PlcBaseLogicaArgumento;
import com.powerlogic.jcompany.controle.jsf.helper.PlcMsgJsfHelper;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class MovimentoDiaAction extends RelatorioActionPlc  {
	
	private boolean valorRecebidoMaiorValorPago = false;
	private BigDecimal totalRecebido;
	private BigDecimal totalPago;
	private List<ContaPagar> contasPagar;
	private List<ContaReceber> contasReceber;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		
		MovimentoDiaEntity movimentoDia = (MovimentoDiaEntity) entidadePlc;
		
		PlcArgVO plcArgVO = getDataArgumento();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		if (StringUtils.isEmpty(plcArgVO.getValor())){
			plcArgVO.setValor(sdf.format(new Date()));
		} else {
			Date dataInformadaZerada = null;
			Date dataAtual = new Date();
			dataAtual.setHours(0);
			dataAtual.setMinutes(0);
			dataAtual.setSeconds(0);
			sdf.format(dataAtual);
			try {
				dataInformadaZerada = sdf.parse(plcArgVO.getValor());
				dataInformadaZerada.setHours(0);
				dataInformadaZerada.setMinutes(0);
				dataInformadaZerada.setSeconds(0);
				
				if (dataInformadaZerada.compareTo(dataAtual) < 0 || dataInformadaZerada.compareTo(new Date()) > 0 && (!getContasPagar().isEmpty() || !getContasReceber().isEmpty())){
					contextHelperPlc.getRequest().setAttribute( "fecharCaixa", PlcConstantes.NAO_EXIBIR );
				} 
				
			} catch (ParseException e) {
				
			}
			
			/*if (movimentoDia!=null && movimentoDia.getId()!=null){
				contextHelperPlc.getRequest().setAttribute( "imitir_livro_caixa", PlcConstantes.EXIBIR );
			} else {
				contextHelperPlc.getRequest().setAttribute( "imitir_livro_caixa", PlcConstantes.NAO_EXIBIR );
			}*/
			contextHelperPlc.getRequest().setAttribute( "imitir_livro_caixa", PlcConstantes.EXIBIR );
		}
		
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_LIMPAR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		
		if( (movimentoDia != null && ( PlcSimNao.N.equals( movimentoDia.getCaixaFechado() ) || movimentoDia.getCaixaFechado() == null) ) &&
				isValorRecebidoMaiorValorPago() ){
			contextHelperPlc.getRequest().setAttribute( "fecharCaixa", PlcConstantes.EXIBIR );
		}
		else{
			contextHelperPlc.getRequest().setAttribute( "fecharCaixa", PlcConstantes.NAO_EXIBIR );
		}
		
	}

	private PlcArgVO getDataArgumento() {
		Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
		PlcArgVO plcArgVO = listaArgumentos.get("dataMovimento");
		return plcArgVO;
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelatorioMovimentoDia() throws PlcException {
		
		MovimentoDiaEntity movimentoDia = (MovimentoDiaEntity) entidadePlc;
		HashMap map = new HashMap<String, String>();
		map.put("totalPagoStr", getTotalPagoStr());
		map.put("totalRecebidoStr", getTotalRecebidoStr());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = sdf.parse( getDataArgumento().getValor() );
			map.put("dataMovimento", sdf.format( date ) );
			if(movimentoDia.getValorRetirada() == null){
				movimentoDia.setValorRetirada(BigDecimal.ZERO);
			}
			map.put("valorRetirada", NumberFormat.getCurrencyInstance().format(movimentoDia.getValorRetirada()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.geraRelatorio(AppConstantesComuns.RELATORIO.REL_MOVIMENTO_DIA, movimentoDia, map);
	}
	
	/**
	 * Acionado pelo usuario - botao Fechar Caixa
	 * @throws PlcException
	 */
	public void fecharCaixa() throws PlcException{
		
		MovimentoDiaEntity movimentoDia = (MovimentoDiaEntity) entidadePlc;
		movimentoDia.setDataFechamento(new Date());
		movimentoDia.setCaixaFechado(PlcSimNao.S);
		
		//TODO recuperar saldo anterior
		
		fechamentoCaixa(movimentoDia);
	}

	private void fechamentoCaixa(MovimentoDiaEntity movimentoDia) throws PlcException {
		
		
		if (movimentoDia.getValorRetirada()==null)
			movimentoDia.setValorRetirada(new BigDecimal(0));
		
		if (movimentoDia.getValorRetirada().compareTo(movimentoDia.getSaldoDia()) > 0){
			throw new PlcException("erro");
		}
		
		PlcArgVO plcArgVO = getDataArgumento();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			movimentoDia.setDataMovimento(sdf.parse(plcArgVO.getValor()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		movimentoDia.setSaldoTotal( movimentoDia.getSaldoDia().subtract(movimentoDia.getValorRetirada()) );
		getFachada().fecharCaixa(movimentoDia);
		if (contextHelperPlc.getRequest().getAttribute( "exibir_msg_cx_fechado")==null){
			PlcMsgJsfHelper.getInstance().msg("msg.infor.cx.fechado", Cor.msgAzulPlc.toString());
		}
		
		contextHelperPlc.getRequest().setAttribute( "exibir_msg_cx_fechado", null);
	}
	
	private IAppFacade getFachada() {
		
		try {
			return (IAppFacade)getServiceFacade();
		} catch (PlcException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public String pesquisa() throws PlcException {
		
		PlcArgVO plcArgVO = getDataArgumento();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataMovimento = null;
		try {
			dataMovimento = sdf.parse(plcArgVO.getValor());
		} catch (ParseException e) {
			throw new PlcException("data.invalida.caixa");
		}
		Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
		PlcArgVO bancoArg = listaArgumentos.get("banco");
		Long idBanco = null;
		if( bancoArg != null && StringUtils.isNotBlank( bancoArg.getValor() ) ){
			idBanco = Long.parseLong( bancoArg.getValor() );
		}
		MovimentoDiaEntity movimentoDia = getFachada().recuperaMovimentoExistente(dataMovimento);
		
		if (movimentoDia  == null){
			movimentoDia = new MovimentoDiaEntity();
		}
      
		entidadePlc = movimentoDia; 
		getFachada().pesquisaMovimentoDia(movimentoDia, dataMovimento, idBanco );
		
		if (movimentoDia.getContasPagar().isEmpty() && movimentoDia.getContasReceber().isEmpty()){
			setContasPagar(new ArrayList<ContaPagar>());
			setContasReceber(new ArrayList<ContaReceber>());
			movimentoDia.setTotalEntrada(null);
			setTotalRecebido(null);
			setTotalPago(null);
			movimentoDia.setTotalSaida(null);
			movimentoDia.setSaldoDia( null );
			movimentoDia.setSaldoDiaStr( null );
			movimentoDia.setSaldoTotal(null);
			//throw new PlcException("erro.pesquisa.movimento");
			if( movimentoDia.getSaldoDiaAnterior() != null && movimentoDia.getSaldoDiaAnterior().compareTo(BigDecimal.ZERO) > 0 ){
				setValorRecebidoMaiorValorPago(true);
			}
			helperMsgJsfPlc.msg("erro.pesquisa.movimento", Cor.msgVermelhoPlc.toString());
			contextHelperPlc.getRequest().setAttribute( "exibir_msg_cx_fechado", PlcConstantes.NAO_EXIBIR );
			return NAVEGACAO.IND_MESMA_PAGINA;
		}
		
		List<PlcBaseVO> itensPlc = new ArrayList<PlcBaseVO>();
		itensPlc.add(new PlcBaseVO());
		logicaItensPlc.setItensPlc(itensPlc);
		
		setContasPagar(movimentoDia.getContasPagar());
		setContasReceber(movimentoDia.getContasReceber());

		if( movimentoDia.getCaixaFechado() == null || PlcSimNao.N.equals( movimentoDia.getCaixaFechado() ) ){
			
			calculaTotais(movimentoDia);
		}
		else{
			helperMsgJsfPlc.msg("msg.informativo.caixaFechado", Cor.msgAzulPlc.toString());
		}
		
		//fechamentoCaixa(movimentoDia);
		
		/*if (movimentoDia != null && PlcSimNao.S.equals( movimentoDia.getCaixaFechado() ) ){
			setValorRecebidoMaiorValorPago(false);
			throw new PlcException("msg.informativo.caixaFechado");
		} */
		//contextHelperPlc.getRequest().setAttribute( "exibir_msg_cx_fechado", PlcConstantes.NAO_EXIBIR );
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	private void calculaTotais(MovimentoDiaEntity movimentoDia) {
		
		BigDecimal totalContasRecebidas = new BigDecimal(0);
		for (ContaReceber contasReceber : getContasReceber()) {
			totalContasRecebidas = totalContasRecebidas.add(contasReceber.getValorTotal());
		}
		
		BigDecimal totalContaPagas = new BigDecimal(0);
		for (ContaPagar contasPagar : getContasPagar()) {
			totalContaPagas = totalContaPagas.add(contasPagar.getValorPagar());
		}
		
		if (totalContasRecebidas.compareTo(totalContaPagas) > 0){
			setValorRecebidoMaiorValorPago(true);
		}
		
		setTotalRecebido(totalContasRecebidas);
		movimentoDia.setTotalEntrada(totalContasRecebidas);
		setTotalPago(totalContaPagas);
		movimentoDia.setTotalSaida(totalContaPagas);
		movimentoDia.setSaldoDia( movimentoDia.getSaldoDiaAnterior().add(movimentoDia.getTotalEntrada()).subtract(movimentoDia.getTotalSaida()) );
		
	}

	public boolean isValorRecebidoMaiorValorPago() {
		return valorRecebidoMaiorValorPago;
	}

	public void setValorRecebidoMaiorValorPago(boolean valorRecebidoMaiorValorPago) {
		this.valorRecebidoMaiorValorPago = valorRecebidoMaiorValorPago;
	}

	public BigDecimal getTotalRecebido() {
		return totalRecebido;
	}

	public void setTotalRecebido(BigDecimal totalRecebido) {
		this.totalRecebido = totalRecebido;
	}

	public BigDecimal getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}

	public List<ContaPagar> getContasPagar() {
		return contasPagar;
	}

	public void setContasPagar(List<ContaPagar> contasPagar) {
		this.contasPagar = contasPagar;
	}

	public List<ContaReceber> getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(List<ContaReceber> contasReceber) {
		this.contasReceber = contasReceber;
	}
	
	public String getTotalRecebidoStr() {
		if (getTotalRecebido()!=null){
			return NumberFormat.getCurrencyInstance().format(getTotalRecebido());
		}
		return "";
	}

	public String getTotalPagoStr() {
		if (getTotalPago()!=null){
			return NumberFormat.getCurrencyInstance().format(getTotalPago());
		}
		return "";
	}
	
}
