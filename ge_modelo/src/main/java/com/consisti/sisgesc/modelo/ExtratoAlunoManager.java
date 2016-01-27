package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.consisti.sisgesc.dominio.TipoContaReceber;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.financeiro.ExtratoAluno;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class ExtratoAlunoManager extends AppManager {
	
	private ContaReceberDAO contaReceberDAO;
	
	public ExtratoAlunoManager(ContaReceberDAO contaReceberDAO) {
		this.contaReceberDAO = contaReceberDAO;
	}

	@SuppressWarnings("unchecked")
	public List<ExtratoAluno> getListExtratoAluno(AlunoEntity aluno, Date dataInicio, Date dataFim) throws PlcException {
		
		Long idAluno = null;
		if(aluno != null){
			idAluno = aluno.getId();
		}
		List contasReceberAluno = contaReceberDAO.recuperarAllContasReceberAluno(idAluno, dataInicio, dataFim);
		
		if( contasReceberAluno != null && !contasReceberAluno.isEmpty() ){
			List<ExtratoAluno> listExtrato = new ArrayList<ExtratoAluno>();
			
			for (Iterator iterator = contasReceberAluno.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				listExtrato.add(new ExtratoAluno( (String)object[0], (String)object[1], (Integer)object[2], (BigDecimal)object[3], (BigDecimal)object[4], (BigDecimal)object[5], (TipoContaReceber)object[6] ) );
			}
			return listExtrato;
		}
		return null;
	}

}
