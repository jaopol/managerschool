package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ProfessorDisciplinaDAO extends AppBaseDAO {
	
	@SuppressWarnings("unchecked")
	public List<Turma> recuperaListaTurmaProfessor( Long idProfessor, Long idDisciplina ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select new TurmaEntity( turma.id, turma.descricao ) " );
		hql.append( " from ProfessorDisciplinaEntity obj " );
		hql.append( " inner join obj.turmaProfessor turPro " );
		hql.append( " inner join turPro.turma turma " );
		hql.append( " where " );
		hql.append( " obj.professor.id =:idProfessor and " );
		hql.append( " obj.disciplina.id =:idDisciplina " );
		hql.append( " order by " );
		hql.append( " turma.descricao asc " );
		
		return getSession().createQuery( hql.toString() )
		.setLong( "idProfessor", idProfessor).setLong( "idDisciplina", idDisciplina ).list();
	}

}
