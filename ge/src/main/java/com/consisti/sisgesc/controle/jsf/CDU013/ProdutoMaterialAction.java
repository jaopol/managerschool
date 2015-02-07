package com.consisti.sisgesc.controle.jsf.CDU013;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.estoque.ProdutoMaterialEntity;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ProdutoMaterialAction extends AppAction  {
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		ProdutoMaterialEntity produto = (ProdutoMaterialEntity)entidadePlc;
		if( produto.getDataCadastro() == null ){
			produto.setDataCadastro( new Date() );
		}
		
		return super.novoApos();
	}
	
}
