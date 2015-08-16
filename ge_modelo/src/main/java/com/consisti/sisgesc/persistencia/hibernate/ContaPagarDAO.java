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
		str.append(" from ContaPagarEntity obj where obj.dataVencimento >=:dataInicio and obj.dataVencimento <=:dataFim order by obj.dataVencimento asc, obj.favorecido asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}

	public List<ContaPagar> recuperaContaPagar() {
		return null;
	}

	public List<ContaPagar> recuperaListaContasAPagar(Date date, Long idBanco) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" SELECT new ContaPagarEntity( ");
		str.append(" obj.id, ");
		str.append(" obj.valorPagar, ");
		str.append(" fav.nome, ");
		str.append(" pg.descricao ) ");
		str.append(" from ContaPagarEntity obj ");
		str.append(" join obj.favorecido fav ");
		str.append(" join obj.formaPagamento pg ");
		str.append(" join obj.banco ban ");
		str.append(" where obj.dataPagamento =:data ");
		if( idBanco != null ){
			str.append( " and obj.banco.id =:idBanco ");
		}

		try {
		
			Query query = getSession().createQuery(str.toString());
			query.setParameter("data", date);
			
			if( idBanco != null ){
				query.setParameter("idBanco", idBanco );
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
