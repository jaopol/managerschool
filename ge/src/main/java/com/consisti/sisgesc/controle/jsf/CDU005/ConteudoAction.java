package com.consisti.sisgesc.controle.jsf.CDU005;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.ConteudoEntity;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
public class ConteudoAction extends AppAction  {
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		ConteudoEntity conteudo = (ConteudoEntity)entidadePlc;
		conteudo.setDataCadastro( new Date() );
		return super.novoApos();
	}
	
}
