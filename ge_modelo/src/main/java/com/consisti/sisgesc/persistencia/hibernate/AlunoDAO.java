package com.consisti.sisgesc.persistencia.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.Aluno;
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
		
		String hql = "";
		Iterator it = null;
		
		if (idTurma==null){
			hql = " from AlunoEntity aluno " +
			"where aluno.status = :ATIVO";
			List<AlunoEntity> list = getSession().createQuery(hql.toString()).setParameter("ATIVO", AtivoInativo.A).list();
			Collections.sort(list, new BeanComparator("turma.descricao"));
			it = list.iterator();
		} else {
			hql = " from AlunoEntity aluno " +
			"left outer join aluno.turma tur " +
			"left outer join aluno.contrato contr " +
			"where tur.id =:idTurma " +
			"and aluno.status = :ATIVO " +
			"order by aluno.nomeAluno asc ";
			it = getSession().createQuery( hql.toString() ).setLong( "idTurma", idTurma ).setParameter("ATIVO", AtivoInativo.A).iterate();
		}
		
		if (it!=null){
			List<AlunoEntity> lista = new ArrayList<AlunoEntity>();
			while (it.hasNext()) {
				AlunoEntity aluno = null;
				if (idTurma==null){
					aluno = (AlunoEntity) it.next();
				} else {
					Object[]tupla = (Object[]) it.next();
					aluno = (AlunoEntity) tupla[0];
					TurmaEntity turma = (TurmaEntity) tupla[1];
					aluno.setTurma(turma);
				}
				
				ResponsavelFinanceiroAlunoEntity responsavel = responsavelFinanceiroAlunoDAO.recuperaNomeResponsavelFinanceiroAluno(aluno.getId());
				if (responsavel!=null && StringUtils.isNotBlank(responsavel.getNome())){
					aluno.setResponsavelFinanceiroStr(responsavel.getNome());
				}
				
				if (!aluno.getContrato().isEmpty()){
					for (Contrato contrato : aluno.getContrato()) {
						Calendar calInicio = Calendar.getInstance();
						Calendar calFim = Calendar.getInstance();
						calInicio.setTime(contrato.getDataInicioContrato());
						calFim.setTime(contrato.getDataFimContrato());
						Integer meses = calFim.get(Calendar.MONTH) - calInicio.get(Calendar.MONTH);
						if (meses!=null){
							aluno.setPeriodoContrato(new Long(meses));
						} 
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
	
	public AlunoEntity recuperaValorMensalidadeAluno(Long idAluno) throws PlcException {
		
		StringBuffer hql = new StringBuffer(); 
		hql.append( " SELECT new AlunoEntity (obj.valorTotalMensalidade) " );
		hql.append(	" FROM AlunoEntity obj " );
		hql.append(	" WHERE obj.id =:idAluno " ); 
		
		return (AlunoEntity)getSession().createQuery(hql.toString()).setLong("idAluno", idAluno).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<AlunoEntity> recuperarAluno(Aluno aluno, Turma turma, TipoEducacao educacao) throws PlcException{
		
		StringBuilder stb = new StringBuilder();
		
		stb.append( " SELECT new AlunoEntity( " );
		stb.append( " obj.id, " );
		stb.append( " obj.nomeAluno ) " );
		stb.append( " FROM " );
		stb.append( " AlunoEntity obj " );
		stb.append( " WHERE 1 = 1 " );
		
		
		if( aluno != null ){
			stb.append( " AND obj.id =:idAluno " );
		}
		if( turma != null ){
			stb.append( " AND obj.turma.id =:idTurma " );
		}
		if( educacao != null ){
			stb.append( " AND obj.tipoEducacao =:educacao " );
		}
		
		Query query = getSession().createQuery( stb.toString() );
		
		if( aluno != null ){
			query.setParameter( "idAluno", aluno.getId() );
		}
		if( turma != null ){
			query.setParameter( "idTurma", turma.getId() );
		}
		if( educacao != null ){
			query.setParameter( "educacao", educacao );
		}
		
		return query.list();
	}
}
