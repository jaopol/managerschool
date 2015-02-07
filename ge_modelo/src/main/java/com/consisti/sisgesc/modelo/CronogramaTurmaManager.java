package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.entidade.Disciplinas;
import com.consisti.sisgesc.entidade.DisciplinasEntity;
import com.consisti.sisgesc.entidade.TabelaPreco;
import com.consisti.sisgesc.entidade.TabelaPrecoDet;
import com.consisti.sisgesc.entidade.TabelaPrecoDetEntity;
import com.consisti.sisgesc.entidade.TabelaPrecoEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.persistencia.IPlcDAO;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class CronogramaTurmaManager extends AppManager {

	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
//		IPlcDAO baseDAO = getDAO(entidadeAtual.getClass());
//		Turma turma = new TurmaEntity();
//		turma.setDescricao("11111");
//		turma.setIdadeMinima(1L);
//		turma.setIdadeMaxima("3r");
//		turma.setAteQueIdade(3L);
//		//turma.setId(1L);
//		//baseDAO.inclui(turma);
//		
//		TabelaPreco di = new TabelaPrecoEntity();
//		di.setDescricao("ss");
//		di.setTurma(turma);
//		di.setObservacao("dddd");
//		
//		TabelaPrecoDet det = new TabelaPrecoDetEntity();
//		det.setTempoHrs("2");
//		det.setValor(new BigDecimal(33));
//		det.setTabelaPreco(di);
//		List<TabelaPrecoDet> lista = new ArrayList<TabelaPrecoDet>();
//		lista.add(det);
//		di.setTabelaPrecoDet(lista);
//		
//		baseDAO.inclui(di);
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}
	
}
