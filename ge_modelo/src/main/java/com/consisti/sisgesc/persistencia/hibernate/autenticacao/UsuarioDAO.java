package com.consisti.sisgesc.persistencia.hibernate.autenticacao;

import com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class UsuarioDAO extends AppBaseDAO {
	
	public UsuarioEntity recuperaUsuarioByLogin( String login ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		hql.append( " Select new UsuarioEntity( obj.id, obj.nome, obj.flgProfessor, obj.flgAdministrador, obj.idFuncionario ) ");
		hql.append( " from UsuarioEntity obj ");
		hql.append( " where ");
		hql.append( " obj.login =:login and ");
		hql.append( " obj.ativo = 'S' ");
		
		
		return (UsuarioEntity) getSession().createQuery( hql.toString() ).setParameter("login", login).uniqueResult();
	}

}
