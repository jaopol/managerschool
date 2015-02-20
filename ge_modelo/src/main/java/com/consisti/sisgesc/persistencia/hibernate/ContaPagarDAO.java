package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ContaPagarDAO extends AppBaseDAO {

	@SuppressWarnings("unchecked")
	public List<ContaPagar> recuperarAllContaPagar(Date dataInicio, Date dataFim) throws PlcException{
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaPagarEntity obj where obj.dataVencimento >=:dataInicio and obj.dataVencimento <=:dataFim order by obj.dataVencimento asc, obj.favorecido asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}

	public List<ContaPagar> recuperaContaPagar() {
		return null;
	}

	public List<ContaPagar> recuperaListaContasAPagar(Date date) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" from ContaPagarEntity obj where obj.dataPagamento =:data ");
		
		try {
			return getSession().createQuery(str.toString()).setParameter("data", date).list();
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
