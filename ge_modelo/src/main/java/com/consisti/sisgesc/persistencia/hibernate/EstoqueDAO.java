package com.consisti.sisgesc.persistencia.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.consisti.sisgesc.entidade.ServicosEntity;
import com.consisti.sisgesc.entidade.estoque.EstoqueEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class EstoqueDAO extends AppBaseDAO {
	
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

	public EstoqueEntity recuperaDadosRelatorioEstoque(EstoqueEntity estoque) throws HibernateException, PlcException {
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("select obj.id from EstoqueEntity obj ");
		hql.append("left outer join obj.movimento mov ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
		hql.append(" where mov.dataMovimentacao >= '11/11/1111'");
		//hql.append(" and to_date(to_char('"+sdf.format(estoque.getDataFim())+"' , 'dd/MM/yyyy'), 'dd/MM/yyyy') <= mov.dataMovimentacao ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.list();
		
		return null ;
		
	}

}
