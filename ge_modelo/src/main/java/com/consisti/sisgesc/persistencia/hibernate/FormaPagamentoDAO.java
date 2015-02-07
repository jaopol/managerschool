package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.financeiro.FormaPagamento;
import com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class FormaPagamentoDAO extends AppBaseDAO {
	
	/**
	 * Utilizado para recuperar a quantidade de parcelas pelo id da forma de pagamento
	 * @param idFormaPag
	 * @return
	 * @throws PlcException
	 */
	public Integer recuperaQtdeParcela( Long idFormaPag ) throws PlcException{
		
		String hql = "SELECT obj.quantidadeParcela FROM FormaPagamentoEntity obj WHERE obj.id =:idFormaPag ";
		
		return (Integer) getSession().createQuery(hql).setLong("idFormaPag", idFormaPag).uniqueResult();
		
	}
	
	public List<FormaPagamento> recuperaFormaPagamento() throws PlcException{
		String hql = " FROM FormaPagamentoEntity obj ";
		return getSession().createQuery(hql).list();
	}

}
