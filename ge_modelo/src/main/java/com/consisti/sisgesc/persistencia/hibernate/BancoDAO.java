package com.consisti.sisgesc.persistencia.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class BancoDAO extends AppBaseDAO {

	public BancoEntity recuperaBancoContaReceber( Long idBanco ) throws PlcException{
		
		Criteria cr = getSession().createCriteria(BancoEntity.class);
		cr.add( Restrictions.eq("id", idBanco) );
		
		return (BancoEntity)cr.uniqueResult();
	}
	
	public List<BancoEntity> recuperaListaBanco() throws PlcException{
		Criteria cr = getSession().createCriteria(BancoEntity.class);
		return cr.list();
	}
	
	public BancoEntity recuperarBancoByBancoSuportado(BancoSuportado bancoSuportado) throws PlcException{
		String str = "Select new BancoEntity( obj.id ) from BancoEntity obj where obj.bancoSuportado =:bancoSuportado ";
		return (BancoEntity) getSession().createQuery(str).setParameter("bancoSuportado", bancoSuportado).uniqueResult();
	}
	
}
