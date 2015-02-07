package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.consisti.sisgesc.dominio.AbertoFechado;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class RegistroNotasFundamentalDAO extends AppBaseDAO {
	
	public AbertoFechado recuperaUltimoRegistroProfessorDisciplina( Long idProfessor, Long idDisciplina ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( "Select max( obj.status )" );
		hql.append( "from RegistroNotasFundamentalEntity obj " );
		hql.append( "where " );
		hql.append( "obj.professor =:idProfessor and " );
		hql.append( "obj.disciplina =:idDisciplina " );
		
		return (AbertoFechado) getSession().createQuery( hql.toString() ).setLong("idProfessor", idProfessor).setLong("idDisciplina", idDisciplina).uniqueResult();
	}
	
	/**
	 * Utilizado para verificar se já existi um bimestre em aberto.
	 * @param idProfessor
	 * @param idDisciplina
	 * @return
	 * @throws PlcException 
	 * @throws  
	 */
	public Long verificaBimestreAberto( Long idProfessor, Long idDisciplina, Integer bimestre ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( "Select count( obj.id ) " );
		hql.append( "from RegistroNotasFundamentalEntity obj " );
		hql.append( "where " );
		hql.append( "obj.professor =:idProfessor and " );
		hql.append( "obj.disciplina =:idDisciplina and " );
		hql.append( "obj.bimestre =:bimestre and " );
		hql.append( "obj.status = 'A' " );
		
		return (Long) getSession().createQuery( hql.toString() ).setLong("idProfessor", idProfessor)
								.setLong("idDisciplina", idDisciplina).setInteger("bimestre", bimestre).uniqueResult();
	}
	
	/**
	 * Recupera a lista de registro de notas do Aluno
	 * @param idTurma
	 * @return
	 * @throws PlcException 
	 * @throws  
	 */
	@SuppressWarnings("unchecked")
	public List<RegistroNotasFundamentalEntity> recuperaListaRegistroNotasByIdAluno( Long idAluno ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( "select " );
		hql.append( "reg.id_registro_notas_fundamental as id, " );
		hql.append( "reg.ano_letivo as anoLetivo, " );
		hql.append( "reg.bimestre as bimestre, " );
		hql.append( "reg.id_disciplina as idDisciplina, " );

		hql.append( "regSub.id_registro_notas_fundamental_sub_det as idRegistroSubDet, " );
		hql.append( "regSub.nota_trabalho as notaTrabalho, " );
		hql.append( "regSub.nota_prova as notaProva, " );
		hql.append( "regSub.avaliacao as avaliacao, " );
		hql.append( "regSub.conceito as conceito, " );
		hql.append( "regSub.aulas_dadas as aulasDadas, " );
		hql.append( "regSub.faltas as faltas, " );
		hql.append( "regSub.total as total, " );
		hql.append( "regSub.nota_reprovada as notaReprovada " );
		
		hql.append( "from " );
		hql.append( "registro_notas_fundamental reg " );
		hql.append( "left outer join registro_notas_fundamental_det regDet " );
		hql.append( "on reg.id_registro_notas_fundamental = regDet.id_registro_notas_fundamental " );
		hql.append( "left outer join registro_notas_fundamental_sub_det regSub " );
		hql.append( "on regDet.id_registro_notas_fundamental_det = regSub.id_registro_notas_fundamental_det " );
		hql.append( "where regSub.id_aluno =:idAluno " );
		//TODO descomentar e colocar o tipo de ensino
		//hql.append( "and reg.status = 'F' " );
		
		SQLQuery sql  = getSession().createSQLQuery( hql.toString() );
		sql.setLong("idAluno", idAluno);
		
		sql.addScalar("id", Hibernate.LONG );
		sql.addScalar("anoLetivo", Hibernate.INTEGER );
		sql.addScalar("bimestre", Hibernate.INTEGER );
		sql.addScalar("idDisciplina", Hibernate.LONG );
		sql.addScalar("idRegistroSubDet", Hibernate.LONG );
		sql.addScalar("notaTrabalho", Hibernate.BIG_DECIMAL );
		sql.addScalar("notaProva", Hibernate.BIG_DECIMAL );
		sql.addScalar("avaliacao", Hibernate.BIG_DECIMAL );
		sql.addScalar("conceito", Hibernate.BIG_DECIMAL );
		sql.addScalar("aulasDadas", Hibernate.INTEGER );
		sql.addScalar("faltas", Hibernate.INTEGER );
		sql.addScalar("total", Hibernate.BIG_DECIMAL );
		sql.addScalar("notaReprovada", Hibernate.STRING );
		
		return (List<RegistroNotasFundamentalEntity>) sql.setResultTransformer(Transformers.aliasToBean( RegistroNotasFundamentalEntity.class ) ).list();
	}
	
	/**
	 * Utilizado para fazer o update no registro de notas colocando ele como aberto para o professor corrigir as notas 
	 * e marcando qual nota foi reprovada
	 * @param idRegistroNotas
	 * @param idRegNotasSubDet 
	 * @throws PlcException 
	 */
	public void reprovaRegistroNotas(Long idRegistroNotas, Long idRegNotasSubDet) throws PlcException {
		
		StringBuilder sql = new StringBuilder();
		sql.append( "Update REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET set NOTA_REPROVADA = 'S' " );
		sql.append( "where ID_REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET =:idRegNotasSubDet " );
		
		getSession().createSQLQuery( sql.toString() ).setLong("idRegNotasSubDet", idRegNotasSubDet).executeUpdate(); 
		
		sql = new StringBuilder();
		sql.append( "Update REGISTRO_NOTAS_FUNDAMENTAL set STATUS = 'A' " );
		sql.append( "where ID_REGISTRO_NOTAS_FUNDAMENTAL =:idRegistroNotas " );
		
		getSession().createSQLQuery( sql.toString() ).setLong("idRegistroNotas", idRegistroNotas).executeUpdate(); 
		
	}

}

	
