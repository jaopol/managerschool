package com.consisti.sisgesc.controle.jsf.financeiro.CDUF007;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

/**
 * Classe de Controle gerada pelo assistente
 */
public class ExtratoContasAction extends AppAction  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ContaReceber> listContaReceber;
	private List<ContaPagar> listContaPagar;
	private BigDecimal totalContaPagar;
	private BigDecimal totalContaReceber;
	private BigDecimal saldo;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		contextHelperPlc.getRequest().setAttribute("exibeBtPesquisarExtrato", "S");
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_PESQUISAR, "N");
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_IMPRIMIR, "S");
	}
	
	@Override
	protected String limpaArgsApos() throws PlcException {
		if( listContaPagar != null){
			listContaPagar.clear();
		}
		if( listContaReceber != null ){
			listContaReceber.clear();
		}
		totalContaPagar = new BigDecimal(0);
		totalContaReceber = new BigDecimal(0);
		saldo = new BigDecimal(0);
		return super.limpaArgsApos();
	}
	
	public String pesquisarExtratoContas() throws PlcException, ParseException{
		
		List<PlcArgVO> listaArgumentos 	= montaListaArgumentosPesquisa();
		verificaArgumentoInformado(listaArgumentos);
		
		IAppFacade fc = (IAppFacade)getServiceFacade();
		//listExtratoConta = fc.recuperaTodosExtratoContas();
		Date dataInicio = retornaDateByString( listaArgumentos.get(0).getValor() );
		Date dataFim = retornaDateByString( listaArgumentos.get(1).getValor() );
		
		listContaPagar = fc.recuperaContaPagar(dataInicio, dataFim);
		listContaReceber = fc.recuperaContaReceber(dataInicio, dataFim);
		totalContaPagar = new BigDecimal(0);
		totalContaReceber = new BigDecimal(0);
		
		if(listContaPagar != null && !listContaPagar.isEmpty() ){
			for (ContaPagar contaPagar : listContaPagar) {
				totalContaPagar = totalContaPagar.add( contaPagar.getValorPagar());
			}
		}
		else{
			helperMsgJsfPlc.msg("msg.info.contaPagar.naoEncontrado", Cor.msgAmareloPlc.toString());
		}
		if( listContaReceber != null && !listContaReceber.isEmpty() ){
			for (ContaReceber cr : listContaReceber) {
				totalContaReceber = totalContaReceber.add(cr.getValorTotal());
			}
		}
		else{
			helperMsgJsfPlc.msg("msg.info.contaReceber.naoEncontrado", PlcMensagem.Cor.msgAmareloPlc.toString());
		}
		
		saldo = totalContaReceber.subtract(totalContaPagar);
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	private void verificaArgumentoInformado(List<PlcArgVO> listaArgumentos) throws PlcException, ParseException {
		
		Date data1 = retornaDateByString( listaArgumentos.get(0).getValor() );
		Date data2 = retornaDateByString( listaArgumentos.get(1).getValor() );
		DateTime dataInicio = new DateTime(data1);
		DateTime dataFim = new DateTime(data2);
		
		int dias = Days.daysBetween(dataInicio, dataFim).getDays();
		if( dias > 31 ){
			throw new PlcException("msg.periodoMaior.30dias");
		}
	}

	public List<ContaReceber> getListContaReceber() {
		return listContaReceber;
	}

	public void setListContaReceber(List<ContaReceber> listContaReceber) {
		this.listContaReceber = listContaReceber;
	}

	public List<ContaPagar> getListContaPagar() {
		return listContaPagar;
	}

	public void setListContaPagar(List<ContaPagar> listContaPagar) {
		this.listContaPagar = listContaPagar;
	}

	public BigDecimal getTotalContaPagar() {
		return totalContaPagar;
	}

	public void setTotalContaPagar(BigDecimal totalContaPagar) {
		this.totalContaPagar = totalContaPagar;
	}

	public BigDecimal getTotalContaReceber() {
		return totalContaReceber;
	}

	public void setTotalContaReceber(BigDecimal totalContaReceber) {
		this.totalContaReceber = totalContaReceber;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
}
