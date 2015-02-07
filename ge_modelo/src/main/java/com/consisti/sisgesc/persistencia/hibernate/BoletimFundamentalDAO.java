package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.consisti.sisgesc.entidade.BoletimFundamental;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class BoletimFundamentalDAO extends AppBaseDAO {
	
	/**
	 * Utilizado para recuperar as notas do aluno no Registro de Notas
	 * @param idAluno
	 * @param anoLetivo
	 * @param situacaoBoletim
	 * @return
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public List<BoletimFundamental> recuperaNotasAlunoByBoletim( Long idAluno, Integer anoLetivo ) throws PlcException{
	
		StringBuilder hql = new StringBuilder();
		hql.append( "select new BoletimFundamentalEntity( " );
		hql.append(	" max( subDet.id ) , " );
		hql.append(	" alu.id, " );
		hql.append(	" alu.nomeAluno, " );
		hql.append(	" mestre.anoLetivo ) " );
		hql.append( " from " );
		hql.append( "	RegistroNotasFundamentalSubDetEntity subDet " );
		hql.append( " left outer join subDet.registroNotasFundamentalDet det " );
		hql.append( " left outer join det.registroNotasFundamental mestre " );
		hql.append( " left outer join subDet.aluno alu " );
		hql.append( " where " );
		hql.append(	"  alu.id =:idAluno " );
		
		if( anoLetivo != null )
			hql.append(	" and mestre.anoLetivo =:anoLetivo " );
		hql.append( " group by " );
		hql.append(	" alu.id, " );
		hql.append(	" alu.nomeAluno, " );
		hql.append(	" mestre.anoLetivo " );
		
		Query query = getSession().createQuery( hql.toString() );
		
		query.setLong("idAluno", idAluno);
		
		if( anoLetivo != null )
			query.setInteger("anoLetivo", anoLetivo);
			
		return query.list();
	}

}
