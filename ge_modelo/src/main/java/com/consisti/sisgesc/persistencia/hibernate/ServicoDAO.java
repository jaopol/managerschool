package com.consisti.sisgesc.persistencia.hibernate;

import java.math.BigDecimal;
import java.util.List;

import com.consisti.sisgesc.entidade.Servicos;
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
	
	public List<Servicos> recuperaValorServicoByIdAluno(Long idAluno) throws PlcException{
		
		StringBuffer str = new StringBuffer();
		
		str.append( " SELECT new ServicosEntity( serv.valorServico ) FROM ServicoAlunoEntity obj " );
		str.append( " LEFT JOIN obj.servico serv " );
		str.append( " WHERE obj.id =:idAluno " );
		
		return getSession().createQuery( str.toString() ).setLong("idAluno", idAluno).list();
		
	}

}
