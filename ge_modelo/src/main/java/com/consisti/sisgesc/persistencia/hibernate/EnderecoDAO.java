package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.entidade.EnderecoEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class EnderecoDAO extends AppBaseDAO {

	public EnderecoEntity recuperaEnderecoAluno( Long idAluno ) throws PlcException{
		
		StringBuffer hql = new StringBuffer();
		hql.append( "Select new EnderecoEntity ( " );
		hql.append( " obj.id, " );
		hql.append( " obj.logradouro, " );
		hql.append( " obj.numero, " );
		hql.append( " obj.complemento, " );
		hql.append( " obj.bairro, " );
		hql.append( " obj.cep, " );
		hql.append( " obj.cidade, " );
		hql.append( " obj.uf )" );
		hql.append(	" from EnderecoEntity obj where obj.aluno.id =:idAluno " );
		
		return (EnderecoEntity)getSession().createQuery( hql.toString() ).setLong("idAluno", idAluno).uniqueResult();
	}
}
