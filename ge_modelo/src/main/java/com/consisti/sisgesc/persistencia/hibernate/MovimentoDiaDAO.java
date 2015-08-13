package com.consisti.sisgesc.persistencia.hibernate;

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

}
