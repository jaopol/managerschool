package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

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
}
