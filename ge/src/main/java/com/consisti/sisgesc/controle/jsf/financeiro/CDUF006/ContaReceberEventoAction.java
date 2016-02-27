package com.consisti.sisgesc.controle.jsf.financeiro.CDUF006;

import java.util.ArrayList;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContaReceberEventoAction extends AppAction  {
	
	//utilizado no xhtml
	private List<AlunoEntity> listAluno;
	
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

	public List<AlunoEntity> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<AlunoEntity> listAluno) {
		this.listAluno = listAluno;
	}
	
}
