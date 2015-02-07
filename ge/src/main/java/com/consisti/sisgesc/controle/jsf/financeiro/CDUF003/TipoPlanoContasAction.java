package com.consisti.sisgesc.controle.jsf.financeiro.CDUF003;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.TipoPlanoContasEntity;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class TipoPlanoContasAction extends AppAction  {
	
	@Override
	protected String novoApos() throws PlcException {
		
		TipoPlanoContasEntity tipoPlano = (TipoPlanoContasEntity)entidadePlc;
		setaDataCadastro(tipoPlano);
		
		return super.novoApos();
	}

	private void setaDataCadastro(TipoPlanoContasEntity tipoPlano) {
		tipoPlano.setDataCadastro( new Date() );
	}
	
}
