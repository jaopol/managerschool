package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import com.consisti.sisgesc.entidade.Disciplinas;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class CronogramaTurmaDisciplinaDAO extends AppBaseDAO {

	/**
	 * Utilizado para recuperar as disciplinas do aluno
	 * @param idAluno
	 * @return List<Disciplinas>
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public List<Disciplinas> recuperaDisciplinasByIdAluno( Long idAluno ) throws PlcException{
		
		StringBuffer hql = new StringBuffer();
		hql.append( " Select new DisciplinasEntity ( disc.id, disc.descricao )" );
		hql.append( " from " );
		hql.append( " CronogramaTurmaDisciplinaEntity obj, " );
		hql.append( " AlunoEntity alu " );
		hql.append( " join obj.cronogramaTurma croTur " );
		hql.append( " join croTur.turma tur " );
		hql.append( " join obj.disciplina disc " );
		hql.append( " where " );
		hql.append( " alu.turma = tur.id and " );
		hql.append( " alu.id =:idAluno " );
		hql.append( " order by " );
		hql.append( " disc.descricao asc " );
		
		return getSession().createQuery( hql.toString() ).setLong( "idAluno", idAluno ).list();
	}
	
	/**
	 * Utilizado para recuperar as disciplinas do aluno
	 * @param idAluno
	 * @return List<Disciplinas>
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public List<Disciplinas> recuperaDisciplinasByIdTurma( Long idTurma ) throws PlcException{
		
		StringBuffer hql = new StringBuffer();
		hql.append( " Select distinct new DisciplinasEntity ( disc.id, disc.descricao )" );
		hql.append( " from " );
		hql.append( " CronogramaTurmaDisciplinaEntity obj, " );
		hql.append( " AlunoEntity alu " );
		hql.append( " join obj.cronogramaTurma croTur " );
		hql.append( " join croTur.turma tur " );
		hql.append( " join obj.disciplina disc " );
		hql.append( " where " );
		hql.append( " tur.id =:idTurma " );
		hql.append( " order by " );
		hql.append( " disc.descricao asc " );
		
		return getSession().createQuery( hql.toString() ).setLong( "idTurma", idTurma ).list();
	}
	
}
