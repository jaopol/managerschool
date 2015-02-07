package com.consisti.sisgesc.controle.jsf.TabelaPreco;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.TabelaPrecoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcConstantesComuns;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
public class TabelaPrecoAction extends AppAction  {
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		// TODO Auto-generated method stub
		super.trataBotoesConformeLogicaApos();
	}
	
	@Override
	protected boolean gravaValidarAntes() throws PlcException {
		
		if (validaDuplicado()){
			throw new PlcException("erro.valor.duplicado");
		}
		
		return super.gravaValidarAntes();
	}

	/**
	 * @author douglas.mendes
	 * valida duplicidade
	 * @throws PlcException 
	 */
	private boolean validaDuplicado() throws PlcException {
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		TabelaPrecoEntity tabela = (TabelaPrecoEntity) entidadePlc;
		return facade.validaTabelaDuplicada(tabela.getTurma().getId(), tabela.getId());
		
	}
	
}
