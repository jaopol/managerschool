package com.consisti.sisgesc.controle.jsf.financeiro.CDUF006;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContaReceberEventoAction extends RelatorioActionPlc  {
	
	//utilizado no xhtml
	private List<AlunoEntity> listAluno;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_ABRIR, "N");
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_GRAVAR, "S");
	}
	
	@Override
	protected String novoApos() throws PlcException {
		
		listAluno = new ArrayList<AlunoEntity>();
		listAluno.add( new AlunoEntity() );
		
		return super.novoApos();
	}
	
	public String recuperarAluno() throws PlcException{
	
		IAppFacade facade = (IAppFacade)getServiceFacade(IAppFacade.class);
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		
		listAluno = facade.recuperarAluno( contaReceber.getAluno(), contaReceber.getTurma(), contaReceber.getTipoEducacao() );
		
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	public String recuperarValorEvento(){
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		if( contaReceber.getEvento() != null ){
			contaReceber.setValorTotal( contaReceber.getEvento().getValor() );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	@Override
	public String grava() throws PlcException {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		IAppFacade facade = (IAppFacade)getServiceFacade();
		List<ContaReceberEntity> listContaReceberEvento = facade.gravarContaReceberEvento(listAluno, contaReceber);
		
		if(listContaReceberEvento != null ){
			HashMap<String , String> map = new HashMap<String, String>();
			geraListaRelatorio(AppConstantesComuns.RELATORIO.CARNE_EVENTO_ALUNO, listContaReceberEvento,map);
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	

	
	

	public List<AlunoEntity> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<AlunoEntity> listAluno) {
		this.listAluno = listAluno;
	}
	
}
