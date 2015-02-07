package com.consisti.sisgesc.controle.jsf.cadastroFeriado;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.CadastroFeriadoEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class CadastroFeriadoAction extends AppAction  {
	
	@Override
	protected String tabularNovoApos() throws PlcException {
		
		List<PlcBaseVO> listFeriados = logicaItensPlc.getItensPlc();
		setDataCadastro(listFeriados);
		
		return super.tabularNovoApos();
	}

	/**
	 * Utilizado para setar a data de cadastro
	 * @param listDisciplinas
	 */
	private void setDataCadastro(List<PlcBaseVO> listFeriados) {
		if( listFeriados != null ){
			for (PlcBaseVO plcBaseVO : listFeriados) {
				CadastroFeriadoEntity feriado = (CadastroFeriadoEntity) plcBaseVO;
				if( feriado.getDataCadastro() == null ){
					feriado.setDataCadastro( new Date());
				}
			}
		}
	}
		
	
	
	
}
