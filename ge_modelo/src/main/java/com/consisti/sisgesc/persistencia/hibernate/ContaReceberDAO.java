package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
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
		str.append(" from ContaReceberEntity obj where obj.dataRecebimento >=:dataInicio and obj.dataRecebimento <=:dataFim order by obj.dataVencimento asc, obj.aluno asc, obj.outro asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}

	@SuppressWarnings("unchecked")
	public List<ContaReceber> recuperaListaContasAReceber(Date date, BancoEntity banco) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaReceberEntity obj where obj.dataRecebimento = :data ");
		
		if(banco != null ){
			str.append(" and obj.banco.id =:idBanco " );
		}
		
		try {
			Query query = getSession().createQuery(str.toString());
			query.setParameter("data", date);
			
			if( banco != null ){
				query.setParameter("idBanco", banco.getId() );
			}
			
			return query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (PlcException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
