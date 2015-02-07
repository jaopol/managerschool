package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.EnderecoEntity;
import com.consisti.sisgesc.entidade.estoque.Movimento;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class MovimentoDAO extends AppBaseDAO {
	
	public List<Movimento> recuperaMovimento(Long idEstoque) throws HibernateException, PlcException {
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("from MovimentoEntity obj where obj.estoque.id = :ID_ESTOQUE");
		
		return (List<Movimento>)getSession().createQuery( hql.toString() ).setParameter("ID_ESTOQUE", idEstoque).list();
		
	}

}
