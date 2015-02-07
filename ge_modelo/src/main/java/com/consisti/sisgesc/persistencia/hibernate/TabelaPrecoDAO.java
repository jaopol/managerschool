package com.consisti.sisgesc.persistencia.hibernate;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class TabelaPrecoDAO extends AppBaseDAO {
	
	/**
	 * Recupera um registro que esteja com o mesmo
	 * id da turma e periodo passados
	 * @author douglas.mendes
	 * @param idTurma
	 * @param periodo
	 * @param idTabela 
	 * @return
	 * @throws PlcException 
	 */
	public boolean validaTabelaDuplicada(Long idTurma, Long idTabela) throws PlcException {
		
		Session sess = getSession();
		
		String hql = "select obj.id from TabelaPrecoEntity obj where obj.turma.id= :IDTURMA and obj.id <> :IDTABELA";
		
		if (idTabela==null){
			hql = hql.replace("and obj.id <> :IDTABELA", "");
		}
		
		Query query = sess.createQuery(hql);
		query.setLong("IDTURMA", idTurma);
		if (idTabela!=null){
			query.setLong("IDTABELA", idTabela);
		}
		return query.uniqueResult()!=null ? true : false;
	}

	
	/**
	 * Recupera o valor da mensalidade pela turma e carga horaria
	 * @param idTurma
	 * @param cargaHoraria
	 * @return
	 * @throws PlcException
	 */
	public BigDecimal recuperaValorMensalidade( Long idTurma, String cargaHoraria ) throws PlcException{
		
		StringBuilder hql = new StringBuilder(); 
		hql.append( " select obj.valor " );
		hql.append( " from TabelaPrecoDetEntity obj " );
		hql.append( " join obj.tabelaPreco tab " );
		hql.append( " where tab.turma.id= :idTurma and obj.tempoHrs = :cargaHoraria " );
		
		return (BigDecimal) getSession().createQuery( hql.toString() ).setLong( "idTurma", idTurma).setString( "cargaHoraria", cargaHoraria ).uniqueResult();
	}
}
