package com.consisti.sisgesc.persistencia.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class MovimentoDiaDAO extends AppBaseDAO {

	public MovimentoDiaEntity recuperaMovimentoExistente(Date dataMovimento) throws HibernateException, PlcException {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append( " from MovimentoDiaEntity obj " );
		hql.append( " where to_date(to_char(obj.dataMovimento, 'dd/MM/yyyy'), 'dd/MM/yyyy') =:dataMovimento " );
		
		List<MovimentoDiaEntity> lista = getSession().createQuery( hql.toString() ).setParameter("dataMovimento", dataMovimento).list();
		
		if (!lista.isEmpty()){
			return lista.get(0);
		}
		
		return null;
		
	}
	
	/**
	 * Recupera o ultimo saldo do caixa fechado
	 * @return
	 * @throws PlcException
	 */
	public BigDecimal recuperarUltimoSaldoTotal() throws PlcException{
		
		StringBuilder stb = new StringBuilder();
		stb.append( " SELECT obj.saldoTotal FROM MovimentoDiaEntity obj " );
		stb.append( " WHERE " );
		stb.append( " obj.caixaFechado = 'S' " );
		stb.append( " ORDER BY obj.id desc " );
		
		BigDecimal result = (BigDecimal)getSession().createQuery(stb.toString()).setMaxResults(1).uniqueResult();
		
		if(result == null){
			return BigDecimal.ZERO;
		}
		return result;
		
	}

}
