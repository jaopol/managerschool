package com.consisti.sisgesc.controle.jsf.CDU008;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.ServicosEntity;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ServicosAction extends AppAction  {
	
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		ServicosEntity servicos = (ServicosEntity)entidadePlc;
		servicos.setDataCadastro( new Date() );
		
		return super.novoApos();
	}
	
}
