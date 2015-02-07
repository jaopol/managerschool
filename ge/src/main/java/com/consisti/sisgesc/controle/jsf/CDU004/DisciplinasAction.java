package com.consisti.sisgesc.controle.jsf.CDU004;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.DisciplinasEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class DisciplinasAction extends AppAction  {
	
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#tabularNovoApos()
	 */
	@Override
	protected String tabularNovoApos() throws PlcException {
		
		List<PlcBaseVO> listDisciplinas = logicaItensPlc.getItensPlc();
		setaDataCadastro(listDisciplinas);
		
		return super.tabularNovoApos();
	}
	
	/**
	 * Utilizado para setar a data de cadastro
	 * @param listDisciplinas
	 */
	private void setaDataCadastro(List<PlcBaseVO> listDisciplinas) {
		if( listDisciplinas != null ){
			for (PlcBaseVO plcBaseVO : listDisciplinas) {
				DisciplinasEntity disciplina = (DisciplinasEntity) plcBaseVO;
				if( disciplina.getDataCadastro() == null ){
					disciplina.setDataCadastro( new Date());
				}
			}
		}
	}
	
}
