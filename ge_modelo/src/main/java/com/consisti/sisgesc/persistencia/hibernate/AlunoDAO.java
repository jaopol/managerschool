package com.consisti.sisgesc.persistencia.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;

import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.Contrato;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class AlunoDAO extends AppBaseDAO {
	
private ResponsavelFinanceiroAlunoDAO responsavelFinanceiroAlunoDAO;
	
	public AlunoDAO( ResponsavelFinanceiroAlunoDAO responsavelFinanceiroAlunoDAO ) {
		this.responsavelFinanceiroAlunoDAO = responsavelFinanceiroAlunoDAO;
	}
	
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
	
	/**
	 * Recupera todos os alunos por turma
	 * @param idTurma
	 * @return
	 * @throws PlcException
	 */
	public List<AlunoEntity> recuperaDadosPorTurma(Long idTurma)throws PlcException {
		
		String hql = " from AlunoEntity aluno " +
				"left outer join aluno.turma tur " +
				"left outer join aluno.contrato contr " +
				"where tur.id =:idTurma " +
				"order by aluno.nomeAluno asc ";
		
		Iterator it = getSession().createQuery( hql.toString() ).setLong( "idTurma", idTurma ).iterate();
		if (it!=null){
			List<AlunoEntity> lista = new ArrayList<AlunoEntity>();
			while (it.hasNext()) {
				Object[]tupla = (Object[]) it.next();
				AlunoEntity aluno = (AlunoEntity) tupla[0];
				ResponsavelFinanceiroAlunoEntity responsavel = responsavelFinanceiroAlunoDAO.recuperaNomeResponsavelFinanceiroAluno(aluno.getId());
				if (responsavel!=null && StringUtils.isNotBlank(responsavel.getNome())){
					aluno.setResponsavelFinanceiroStr(responsavel.getNome());
				}
				TurmaEntity turma = (TurmaEntity) tupla[1];
				aluno.setTurma(turma);
				
				if (!aluno.getContrato().isEmpty()){
					for (Contrato contrato : aluno.getContrato()) {
						aluno.setPeriodoContrato(8L);
						break;
					}
				} else {
					aluno.setPeriodoContrato(null);
				}
				
				lista.add(aluno);
				
			}
			return lista;
		}
		
		return null;
		
	}
	
}
