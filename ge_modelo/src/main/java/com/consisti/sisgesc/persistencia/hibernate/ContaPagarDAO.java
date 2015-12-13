package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ContaPagarDAO extends AppBaseDAO {

	@SuppressWarnings("unchecked")
	public List<ContaPagar> recuperarAllContaPagar(Date dataInicio, Date dataFim) throws PlcException{
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaPagarEntity obj where obj.dataPagamento >=:dataInicio and obj.dataPagamento <=:dataFim order by obj.dataVencimento asc, obj.favorecido asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}

	public List<ContaPagar> recuperaContaPagar() {
		return null;
	}

	public List<ContaPagar> recuperaListaContasAPagar(Date date, BancoEntity banco) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaPagarEntity obj where obj.dataPagamento =:data ");
		if( banco != null ){
			str.append( " and obj.banco.id =:idBanco ");
		}

		try {
		
			Query query = getSession().createQuery(str.toString());
			query.setParameter("data", date);
			
			if( banco != null ){
				query.setParameter("idBanco", banco.getId() );
			}
		
			return query.list();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
