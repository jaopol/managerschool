package com.consisti.sisgesc.modelo;

import com.consisti.sisgesc.entidade.TurmaEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

public class TurmaManager extends AppManager {
	
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		TurmaEntity turma = (TurmaEntity)entidadeAtual;
		turma.setIdadeMaxima("0");
		turma.setIdadeMinima("0");
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

}
