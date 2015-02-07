package com.consisti.sisgesc.controle.jsf.financeiro.CDUF002;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.PlanoContasEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class PlanoContasAction extends AppAction  {
	
	@Override
	protected String tabularNovoApos() throws PlcException {
		
		List<PlcBaseVO> listPlanoContas = logicaItensPlc.getItensPlc();
		setaDataCadastro( listPlanoContas );
		
		return super.tabularNovoApos();
	}

	/**
	 * Seta a data de cadastro
	 * @param listPlanoContas
	 */
	private void setaDataCadastro(List<PlcBaseVO> listPlanoContas) {
		
		if( !listPlanoContas.isEmpty() ){
			for (PlcBaseVO plcBaseVO : listPlanoContas) {
				PlanoContasEntity planoConta = (PlanoContasEntity)plcBaseVO;
				if(planoConta.getDataCriacao() == null ){
					planoConta.setDataCriacao( new Date() );
				}
			}
		}
		
	}
	
}
