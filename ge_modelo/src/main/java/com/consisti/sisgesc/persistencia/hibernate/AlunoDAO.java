package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class AlunoDAO extends AppBaseDAO {
	
	public String recuperarUltimaMatriculaAluno( TipoEducacao tipoEducacao) throws HibernateException, PlcException{
		
		String hql = "select max( obj.matricula ) from AlunoEntity obj where obj.tipoEducacao =:tipoEducacao";
		return (String) getSession().createQuery(hql).setParameter("tipoEducacao", tipoEducacao).uniqueResult();
	}

	
	/**
	 * Recupera a lista de aluno da turma
	 * @param turma
	 * @return List<AlunoEntity>
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoEntity> recuperaAlunoPelaTurma( Turma turma ) throws PlcException{
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " Select new AlunoEntity( obj.id, obj.nomeAluno ) " );
		hql.append( " from AlunoEntity obj " );
		hql.append( " where obj.turma =:turma " );
		hql.append( " order by obj.nomeAluno asc " );
		
		return getSession().createQuery( hql.toString() ).setParameter("turma", turma).list();
	}
	
	/**
	 * Utilizado para recuperar a turma do aluno pelo id do aluno
	 * @param idAluno
	 * @return Long
	 * @throws PlcException
	 */
	public TurmaEntity recuperaTurmaByIdAluno( Long idAluno ) throws PlcException{
		
		String hql = "select obj.turma from AlunoEntity obj where obj.id =:idAluno ";
		
		return (TurmaEntity)getSession().createQuery( hql.toString() ).setParameter( "idAluno", idAluno ).uniqueResult();
	}
	
	/**
	 * Recupera Aluno pelo id
	 * @param idAluno
	 * @return Aluno (id, nome)
	 * @throws PlcException
	 */
	public AlunoEntity recuperaAlunoByIdAluno( Long idAluno ) throws PlcException{
		
		String hql = "select new AlunoEntity ( obj.id, obj.nomeAluno ) from AlunoEntity obj where obj.id =:idAluno ";
		
		return (AlunoEntity) getSession().createQuery( hql.toString() ).setLong( "idAluno", idAluno ).uniqueResult();
	}

	public AlunoEntity recuperaValorMensalidadeAluno(Long idAluno) throws PlcException {
		
		StringBuffer hql = new StringBuffer(); 
		hql.append( " SELECT new AlunoEntity (obj.valorTotalMensalidade, servico.valorServico ) " );
		hql.append(	" FROM AlunoEntity obj " );
		hql.append( " LEFT JOIN obj.servicoAluno ser " );
		hql.append( " LEFT JOIN ser.servico servico " );
		hql.append(	" WHERE obj.id =:idAluno " ); 
		
		return (AlunoEntity)getSession().createQuery(hql.toString()).setLong("idAluno", idAluno).uniqueResult();
	}
}
