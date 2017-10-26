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
	 * @param anoLetivo 
	 * @return
	 * @throws PlcException 
	 */
	public boolean validaTabelaDuplicada(Long idTurma, Integer anoLetivo) throws PlcException {
		
		Session sess = getSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append( "select obj.id from TabelaPrecoEntity obj where obj.turma.id= :idTurma and obj.anoLetivo =:anoLetivo " );
		
		Query query = sess.createQuery(hql.toString());
		query.setLong("idTurma", idTurma);
		query.setLong("anoLetivo", anoLetivo);

		return query.uniqueResult()!=null ? true : false;
	}

	
	/**
	 * Recupera o valor da mensalidade pela turma e carga horaria
	 * @param idTurma
	 * @param cargaHoraria
	 * @return
	 * @throws PlcException
	 */
	public BigDecimal recuperaValorMensalidade( Long idTurma, String cargaHoraria, Integer anoLetivo ) throws PlcException{
		
		StringBuilder hql = new StringBuilder(); 
		hql.append( " select obj.valor " );
		hql.append( " from TabelaPrecoDetEntity obj " );
		hql.append( " join obj.tabelaPreco tab " );
		hql.append( " where tab.turma.id= :idTurma " );
		hql.append(	" and obj.tempoHrs = :cargaHoraria " );
		hql.append(	" and tab.anoLetivo = :anoLetivo " );
		
		return (BigDecimal) getSession().createQuery( hql.toString() )
			.setLong( "idTurma", idTurma).setString( "cargaHoraria", cargaHoraria )
			.setInteger("anoLetivo", anoLetivo).uniqueResult();
	}
}
