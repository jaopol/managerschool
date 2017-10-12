package com.consisti.sisgesc.controle.jsf.financeiro.CDUF008;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.financeiro.ExtratoAluno;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.controle.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ExtratoAlunoAction extends AppAction  {
	
	private List<ExtratoAluno> listExtratoAluno;
	
	private BigDecimal valorTotal;
	private BigDecimal totalMensalidade;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_IMPRIMIR, "S");
	}
	
	@Override
	public String pesquisa() throws PlcException {
		
		try {
			pesquisarExtratoAluno();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	private void pesquisarExtratoAluno() throws PlcException, ParseException{
		
		List<PlcArgVO> listaArgumentos 	= montaListaArgumentosPesquisa();
		
		String idAluno = null;
		Date dataInicio = null;
		Date dataFim = null;
		PlcSimNao recebido = null;
		if( listaArgumentos != null && !listaArgumentos.isEmpty() ){
			for (int i = 0; i < listaArgumentos.size(); i++) {
				if( "aluno".equals( listaArgumentos.get(i).getNome() ) ){
					idAluno = listaArgumentos.get(i).getValor();
				}
				else if( "dataInicio".equals( listaArgumentos.get(i).getNome() ) ){
					dataInicio = retornaDateByString( listaArgumentos.get(i).getValor() );
				}
				else if( "dataFim".equals( listaArgumentos.get(i).getNome() ) ){
					dataFim = retornaDateByString( listaArgumentos.get(i).getValor() );
				}
				else if( "recebido".equals( listaArgumentos.get(i).getNome() ) ){
					recebido = PlcSimNao.valueOf( (String)listaArgumentos.get(i).getValor() );
				}
			}
		}
		
		if( recebido != null && PlcSimNao.N.equals(recebido) ){
			dataInicio = null;
			dataFim = null;
		}
		
		IAppFacade facade = (IAppFacade)getServiceFacade(); 
		listExtratoAluno = facade.getListExtratoProdutoAluno(new Long(idAluno), dataInicio, dataFim, recebido);
		
	}

	@Override
	protected String limpaArgsApos() throws PlcException {
		listExtratoAluno = new ArrayList<ExtratoAluno>();
		return super.limpaArgsApos();
	}
	
	public List<ExtratoAluno> getListExtratoAluno() {
		return listExtratoAluno;
	}

	public void setListExtratoAluno(List<ExtratoAluno> listExtratoAluno) {
		this.listExtratoAluno = listExtratoAluno;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getTotalMensalidade() {
		return totalMensalidade;
	}

	public void setTotalMensalidade(BigDecimal totalMensalidade) {
		this.totalMensalidade = totalMensalidade;
	}
}
