package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ContaReceberDAO extends AppBaseDAO {

	public String recuperarUltimoNumeroDocumento( ) throws HibernateException, PlcException{
		
		String hql = "select max( obj.numeroDocumento ) from ContaReceberEntity obj ";
		return (String) getSession().createQuery(hql).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContaReceber> recuperarAllContaReceber(Date dataInicio, Date dataFim) throws PlcException{
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaReceberEntity obj where obj.dataVencimento >=:dataInicio and obj.dataVencimento <=:dataFim order by obj.dataVencimento asc, obj.aluno asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}
}
