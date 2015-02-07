package com.consisti.sisgesc.persistencia.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ResponsavelFinanceiroAlunoDAO extends AppBaseDAO {
	
	public ResponsavelFinanceiroAlunoEntity recuperaResponsavelFinanceiroAluno( Long idAluno ) throws PlcException{
		
		Criteria cr = getSession().createCriteria(ResponsavelFinanceiroAlunoEntity.class);
		cr.add(Restrictions.eq("aluno.id", idAluno));
		
		return (ResponsavelFinanceiroAlunoEntity)cr.uniqueResult();
	}

}
