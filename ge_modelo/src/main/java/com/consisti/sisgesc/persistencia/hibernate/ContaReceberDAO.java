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
		str.append( " Select new ContaReceberEntity ( " );
		str.append( " aluno.nomeAluno, " );
		str.append( " obj.outro, " );
		str.append( " obj.valorTotal, " );
		str.append( " prodVenda.descricao, " );
		str.append( " obj.dataVencimento, " );
		str.append( " obj.dataRecebimento " );
		str.append( " ) " );
		str.append(" from ContaReceberEntity obj " );
		str.append(" left join obj.aluno aluno " );
		str.append(" left join obj.contaReceberProdutoVenda venda " );
		str.append(" left join venda.produtoVenda prodVenda " );
		str.append(" where obj.dataRecebimento >=:dataInicio " );
		str.append(" and obj.dataRecebimento <=:dataFim " );
		str.append(" order by obj.dataVencimento asc, obj.aluno asc, obj.outro asc");
		
		return getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).list();
		
	}

	@SuppressWarnings("unchecked")
	public List<ContaReceber> recuperaListaContasAReceber(Date date, Long idBanco) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" SELECT new ContaReceberEntity( ");
		str.append(" obj.id, ");
		str.append(" obj.valorTotal, ");
		str.append(" alu.nomeAluno, ");
		str.append(" pg.descricao ) ");
		str.append(" from ContaReceberEntity obj ");
		str.append(" join obj.aluno alu ");
		str.append(" join obj.formaRecebimento pg ");
		str.append(" where obj.dataRecebimento =:data ");
		str.append(" and obj.tipoContaReceber = 'D' ");
		
		if(idBanco != null ){
			str.append(" and obj.banco.id =:idBanco " );
		}
		
		try {
			Query query = getSession().createQuery(str.toString());
			query.setParameter("data", date);
			
			if( idBanco != null ){
				query.setParameter("idBanco", idBanco );
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
