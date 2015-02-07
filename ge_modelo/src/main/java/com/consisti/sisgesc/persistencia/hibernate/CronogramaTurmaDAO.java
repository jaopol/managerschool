package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.consisti.sisgesc.entidade.CronogramaTurma;
import com.consisti.sisgesc.entidade.CronogramaTurmaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class CronogramaTurmaDAO extends AppBaseDAO {

	/**
	 * Utilizado para recuperar as disciplinas do aluno
	 * @param idAluno
	 * @return List<Disciplinas>
	 * @throws PlcException
	 */
	public List<CronogramaTurma> recuperaCronogramaTurma( Long idTurma) throws PlcException{
		
		StringBuffer hql = new StringBuffer();
		hql.append("Select new CronogramaTurmaEntity ");
		hql.append("(obj.id, obj.turma.id, obj.turma.descricao) ");
		hql.append("from ");
		hql.append("CronogramaTurmaEntity obj ");
		hql.append("order by obj.turma.descricao ");
		
		Query query = getSession().createQuery(hql.toString());
		
		List<CronogramaTurma> lista = query.list();
		for (CronogramaTurma cronogramaTurmaEntity : lista) {
			String query2 = "from CronogramaTurmaDisciplinaEntity obj where obj.cronogramaTurma = :IDCRONOGRAMATURMA";
			query = getSession().createQuery(query2.toString());
			query.setLong("IDCRONOGRAMATURMA", cronogramaTurmaEntity.getId());
			cronogramaTurmaEntity.setCronogramaTurmaDisciplina(query.list());
		}
		
		return lista;
		
	}
	
}
