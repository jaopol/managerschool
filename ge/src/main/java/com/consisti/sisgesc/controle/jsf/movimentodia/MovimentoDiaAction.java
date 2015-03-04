package com.consisti.sisgesc.controle.jsf.movimentodia;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.controle.jsf.PlcBaseLogicaArgumento;
import com.powerlogic.jcompany.controle.jsf.helper.PlcMsgJsfHelper;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

/**
 * Classe de Controle gerada pelo assistente
 */
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
		
		if (isValorRecebidoMaiorValorPago()){
			contextHelperPlc.getRequest().setAttribute( "fecharCaixa", PlcConstantes.EXIBIR );
		}
			
		Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
		PlcArgVO plcArgVO = listaArgumentos.get("dataMovimento");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (StringUtils.isEmpty(plcArgVO.getValor())){
			plcArgVO.setValor(sdf.format(new Date()));
		} else {
			Date dataInformada = null;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			Date dataAtual = cal.getTime();
			try {
				dataInformada = sdf.parse(plcArgVO.getValor());
			} catch (ParseException e) {
				throw new PlcException("data.invalida.caixa");
			}
			if (dataInformada.compareTo(dataAtual) < 0 || dataInformada.compareTo(new Date()) > 0 && (!getContasPagar().isEmpty() || !getContasReceber().isEmpty())){
				contextHelperPlc.getRequest().setAttribute( "fecharCaixa", PlcConstantes.NAO_EXIBIR );
			}
			
			if (dataInformada.compareTo(dataAtual) == -1 || movimentoDia.getId()!=null){
				contextHelperPlc.getRequest().setAttribute( "imitir_livro_caixa", PlcConstantes.EXIBIR );
			} else {
				contextHelperPlc.getRequest().setAttribute( "imitir_livro_caixa", PlcConstantes.NAO_EXIBIR );
			}
			
		}
		
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_LIMPAR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelatorioMovimentoDia() throws PlcException {
		
		MovimentoDiaEntity movimentoDia = (MovimentoDiaEntity) entidadePlc;
		HashMap map = new HashMap<String, String>();
		map.put("totalPagoStr", getTotalPagoStr());
		map.put("totalRecebidoStr", getTotalRecebidoStr());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		map.put("dataMovimento", sdf.format(movimentoDia.getDataMovimento()));
		map.put("valorRetirada", NumberFormat.getCurrencyInstance().format(movimentoDia.getValorRetirada()));
		
		super.geraRelatorioPlc(AppConstantesComuns.RELATORIO.REL_MOVIMENTO_DIA, movimentoDia, map);
	}
	
	public void fecharCaixa() throws PlcException{
		
		MovimentoDiaEntity movimentoDia = (MovimentoDiaEntity) entidadePlc;
		
		if (movimentoDia.getValorRetirada().compareTo(movimentoDia.getSaldoDia()) > 0){
			throw new PlcException("erro");
		}
		
		Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
		PlcArgVO plcArgVO = listaArgumentos.get("dataMovimento");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			movimentoDia.setDataMovimento(sdf.parse(plcArgVO.getValor()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		movimentoDia.setSaldoTotal(movimentoDia.getSaldoDia().subtract(movimentoDia.getValorRetirada()));
		getFachada().fecharCaixa(movimentoDia);
		PlcMsgJsfHelper.getInstance().msg("msg.infor.cx.fechado", Cor.msgAzulPlc.toString());
		
	}
	
	private BigDecimal somaSaldoTotal(BigDecimal valorRetirada, BigDecimal saldoDoDia) {
		if (isValorRecebidoMaiorValorPago()){
			if (valorRetirada!=null){
				return saldoDoDia.subtract(valorRetirada);
			}
		}
		return saldoDoDia;
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
		
		Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
		PlcArgVO plcArgVO = listaArgumentos.get("dataMovimento");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataMovimento = null;
		try {
			dataMovimento = sdf.parse(plcArgVO.getValor());
		} catch (ParseException e) {
			throw new PlcException("data.invalida.caixa");
		}
		
		MovimentoDiaEntity movimentoDia = movimentoDia = getFachada().recuperaMovimentoExistente(dataMovimento);;
		
		if (movimentoDia  == null){
			movimentoDia = new MovimentoDiaEntity();
		} 
		
		entidadePlc = movimentoDia;
		
		getFachada().pesquisaMovimentoDia(movimentoDia, dataMovimento);
		
		if (movimentoDia.getContasPagar().isEmpty() && movimentoDia.getContasReceber().isEmpty()){
			throw new PlcException("erro.pesquisa.movimento");
		}
		
		List<PlcBaseVO> itensPlc = new ArrayList<PlcBaseVO>();
		itensPlc.add(new PlcBaseVO());
		logicaItensPlc.setItensPlc(itensPlc);
		
		setContasPagar(movimentoDia.getContasPagar());
		setContasReceber(movimentoDia.getContasReceber());
		
		calculaTotais(movimentoDia);
		
		return "";
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
		movimentoDia.setSaldoDia(totalContasRecebidas.subtract(totalContaPagas));
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
