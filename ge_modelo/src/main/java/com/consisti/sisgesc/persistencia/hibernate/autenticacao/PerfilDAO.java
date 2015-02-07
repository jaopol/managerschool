package com.consisti.sisgesc.persistencia.hibernate.autenticacao;

import java.util.List;

import com.consisti.sisgesc.entidade.autenticacao.PerfilEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class PerfilDAO extends AppBaseDAO {

	/**
	 * Recupera as urls do perfil que nao esteja bloqueado, pelo login do usuario
	 * @param login
	 * @return
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilEntity> recuperaListaUrlPerfilByLoginUsuario( String login ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select new PerfilEntity ( url.url ) " );
		hql.append(	" from PerfilEntity obj " );
		hql.append( " inner join obj.perfilUsuario perfUsu " );
		hql.append( " inner join perfUsu.usuario usu " );
		hql.append( " inner join obj.urlAcessoPerfil urlPerf " );
		hql.append( " inner join urlPerf.urlAcesso url " );
		hql.append( " where " );
		hql.append( " usu.login =:login and " );
		hql.append( " obj.bloqueado = 'N' " );
		hql.append( " order by  " );
		hql.append( " url.url asc  " );
		
		return getSession().createQuery( hql.toString() ).setString("login", login).list();
	}
	
	/**
	 * Recupera o as permissoes peril que nao esteja bloqueado, pelo login do usuario
	 * @param login
	 * @return
	 * @throws PlcException
	 */
	public PerfilEntity recuperaPerfilUsuarioByLoginUsuario( String login ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select new PerfilEntity ( obj.grava, obj.exclui, obj.pesquisa ) " );
		hql.append(	" from PerfilEntity obj " );
		hql.append( " inner join obj.perfilUsuario perfUsu " );
		hql.append( " inner join perfUsu.usuario usu " );
		hql.append( " where " );
		hql.append( " usu.login =:login and " );
		hql.append( " obj.bloqueado = 'N' " );
		
		return (PerfilEntity)getSession().createQuery( hql.toString() ).setString("login", login).uniqueResult();
		
	}
}
