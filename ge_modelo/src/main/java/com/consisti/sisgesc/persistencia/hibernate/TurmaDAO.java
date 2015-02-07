package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class TurmaDAO extends AppBaseDAO {
	
	/**
	 * Recupera a idade maxima e minima permitida
	 * para o aluno entrar na turma
	 * @param idTipoEducacao
	 * @return Long idade
	 * @throws PlcException
	 */
	public TurmaEntity recuperaIdadePermitida( Long idTurma ) throws PlcException{
		
		String hql = "select new TurmaEntity (obj.id, obj.idadeMaxima, obj.idadeMinima) from TurmaEntity obj where obj.id =:idTurma";
		return (TurmaEntity)getSession().createQuery( hql ).setParameter("idTurma", idTurma).uniqueResult();
		
	}
	
	/**
	 * Utilizado para recuperar a descriçao da turma pelo id
	 * @param idTurma
	 * @return
	 * @throws PlcException
	 */
	public String recuperaDescricaoTurma( Long idTurma ) throws PlcException{
		
		String hql = "select obj.descricao from TurmaEntity obj where obj.id =:idTurma";
		return (String) getSession().createQuery( hql.toString() ).setLong("idTurma", idTurma).uniqueResult();
	}
	
}
