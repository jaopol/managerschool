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
	
	public ResponsavelFinanceiroAlunoEntity recuperaNomeResponsavelFinanceiroAluno(Long idAluno) throws PlcException {
		
		String hql = "select new ResponsavelFinanceiroAlunoEntity(obj.id, obj.nome) " +
				"from ResponsavelFinanceiroAlunoEntity obj " +
				"left outer join obj.aluno al " +
				"where al.id =:idAluno ";
		
		return (ResponsavelFinanceiroAlunoEntity) getSession().createQuery( hql.toString() ).setLong( "idAluno", idAluno ).uniqueResult();
	}

}
