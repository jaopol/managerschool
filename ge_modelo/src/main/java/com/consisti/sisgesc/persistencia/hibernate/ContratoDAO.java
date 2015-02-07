package com.consisti.sisgesc.persistencia.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ContratoDAO extends AppBaseDAO {
	
	/**
	 * Vai verificar se ja existe um contrato
	 * gerado para o ano corrente para o aluno
	 * em questão
	 * @param idAluno
	 * @return Long
	 * @throws PlcException
	 */
	public Long temContratoVigente( Long idAluno, int ano ) throws PlcException{
		
		Session sess = getSession();
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select obj.id " );
		hql.append( " from ContratoEntity obj " );
		hql.append( " where obj.aluno.id =:idAluno " );
		hql.append( " and obj.anoContrato =:ano " );
		hql.append( " and obj.statusContrato = 'A' " );
		
		Query query = sess.createQuery(hql.toString());
		query.setLong("idAluno", idAluno);
		query.setLong("ano", new Long(ano));
		
		return (Long) query.uniqueResult();
	}

	/**
	 * Recupera o ultimo contrato do aluno
	 * @param idAluno
	 * @return
	 * @throws PlcException
	 */
	public ContratoEntity recuperaContratoAluno(Long idAluno) throws PlcException{
		
		Session sess = getSession();
		
		String hql = "from ContratoEntity obj where obj.aluno.id= :IDALUNO order by obj.anoContrato desc";
		
		Query query = sess.createQuery(hql);
		query.setLong("IDALUNO", idAluno);
		
		return (ContratoEntity) query.uniqueResult();
		
	}

	/**
	 * Recupera o contrato - arquivo do aluno
	 * @param idContrato
	 * @return
	 * @throws PlcException 
	 */
	public byte[] recuperaContrato(Long idContrato) throws PlcException {
		
		Session sess = getSession();
		
		String hql = "select obj.contratoAluno from ContratoEntity obj " +
				     "where obj.id= :IDCONTRATO";
		
		Query query = sess.createQuery(hql);
		query.setLong("IDALUNO", idContrato);
		
		return (byte[]) query.uniqueResult();
	}
	
}
