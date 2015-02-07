package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class FuncionarioDAO extends AppBaseDAO {

	
	/**
	 * Utilizado para recuperar alguns dados do Funcionario
	 * @param idFuncionario
	 * @return FuncionarioEntity
	 * @throws PlcException
	 */
	public FuncionarioEntity recuperaFuncionarioById( Long idFuncionario ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select new FuncionarioEntity( obj.id, obj.nome, " );
		hql.append( " obj.professor, obj.email, obj.celular ) " );
		hql.append( " from FuncionarioEntity obj " );
		hql.append( " where " );
		hql.append( " obj.id =:idFuncionario " );
		
		return (FuncionarioEntity) getSession().createQuery( hql.toString() ).setParameter("idFuncionario", idFuncionario).uniqueResult();
	}
}
