package com.consisti.sisgesc.persistencia.hibernate;

import java.math.BigDecimal;

import com.consisti.sisgesc.entidade.ServicosEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ServicoDAO extends AppBaseDAO {
	
	/**
	 * Recupera o Serviço pelo id
	 * @param idServico
	 * @return
	 * @throws PlcException
	 */
	public ServicosEntity recuperaServico( Long idServico ) throws PlcException{
		
		String hql = " from ServicosEntity obj where obj.id =:idServico ";
		
		return (ServicosEntity) getSession().createQuery( hql ).setParameter("idServico", idServico ).uniqueResult();
	}
	
	/**
	 * Recupera o valor do servico pelo id
	 * @param idServico
	 * @return
	 * @throws PlcException
	 */
	public BigDecimal recuperaValorServico( Long idServico ) throws PlcException{
		
		String hql = " Select obj.valorServico from ServicosEntity obj where obj.id =:idServico ";
		
		return (BigDecimal) getSession().createQuery( hql ).setLong("idServico", idServico ).uniqueResult();
	}

}
