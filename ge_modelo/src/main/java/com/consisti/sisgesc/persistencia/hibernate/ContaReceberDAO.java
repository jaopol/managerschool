package com.consisti.sisgesc.persistencia.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

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
		str.append( " obj.dataRecebimento, " );
		str.append( " venda.valorTotal " );
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

	/**
	 * Recupera as contas a receber diarias dos alunos.
	 * @param date
	 * @param idBanco
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ContaReceber> recuperaListaContasAReceber(Date date, Long idBanco) {
		
		StringBuffer str = new StringBuffer(); 
		str.append(" SELECT new ContaReceberEntity( ");
		str.append(" obj.id, ");
		str.append(" obj.valorTotal, ");
		str.append(" alu.nomeAluno, ");
		str.append(" pg.descricao, ");
		str.append(" obj.outro ) ");
		str.append(" from ContaReceberEntity obj ");
		str.append(" left join obj.aluno alu ");
		str.append(" left join obj.formaRecebimento pg ");
		str.append(" left join obj.banco banco ");
		str.append(" where obj.dataRecebimento =:data ");
		str.append(" and banco.bancoSuportado =:bancoSuportado ");
		
		if(idBanco != null ){
			str.append(" and obj.banco.id =:idBanco " );
		}
		
		try {
			Query query = getSession().createQuery(str.toString());
			query.setParameter("data", date);
			query.setParameter("bancoSuportado", BancoSuportado.B000);
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
	
	@SuppressWarnings("unchecked")
	public List recuperarAllContasReceberDiarioAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido, Long idProd) throws PlcException{
		
		StringBuffer stb = new StringBuffer();
		stb.append( " SELECT " );
		stb.append( "  alu.nomeAluno, " );
		stb.append( "  pro.id, " );
		stb.append( "  pro.descricao, " );
		stb.append( "  prodVen.quantidadeVendida, " );
		stb.append( "  prodVen.valorUnitario, " );
		stb.append( "  prodVen.valorTotal, " );
		//stb.append( "  obj.valorTotal, " );
		stb.append( "  obj.dataRecebimento " );
		//stb.append( "  obj.tipoContaReceber, " );
		//stb.append( "  obj.tipoReceberDe " );
		getPartQueryProdVendaReceber(idAluno, dataInicio, dataFim, recebido, stb, idProd);
		
		Query query = getSession().createQuery(stb.toString());
		
		if( idAluno != null ){
			query.setLong("idAluno", idAluno);
		}
		if( dataInicio != null ){
			query.setParameter("dataInicio", dataInicio);
		}
		if( dataFim != null ){
			query.setParameter("dataFim", dataFim);
		}
		if( recebido != null ){
			query.setParameter("recebido", recebido);
		}
		if( idProd != null ){
			query.setParameter("idProd", idProd);
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List recuperarIdProdContasReceberDiarioAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException{
		
		StringBuffer stb = new StringBuffer();
		stb.append( " SELECT distinct " );
		stb.append( "  pro.id " );
		getPartQueryProdVendaReceber(idAluno, dataInicio, dataFim, recebido, stb, null);
		
		Query query = getSession().createQuery(stb.toString());
		
		if( idAluno != null ){
			query.setLong("idAluno", idAluno);
		}
		if( dataInicio != null ){
			query.setParameter("dataInicio", dataInicio);
		}
		if( dataFim != null ){
			query.setParameter("dataFim", dataFim);
		}
		if( recebido != null ){
			query.setParameter("recebido", recebido);
		}
		
		return query.list();
		
	}

	private void getPartQueryProdVendaReceber(Long idAluno, Date dataInicio, Date dataFim,
			PlcSimNao recebido, StringBuffer stb, Long idProd) {
		stb.append( " FROM " );
		stb.append( " ContaReceberEntity obj " );
		stb.append( " LEFT JOIN obj.contaReceberProdutoVenda prodVen " );
		stb.append( " INNER JOIN obj.aluno alu " );
		stb.append( " INNER JOIN prodVen.produtoVenda pro " );
		stb.append( " WHERE obj.tipoContaReceber = 'D' " ); //somente diario
		if( idAluno != null ){
			stb.append( " and alu.id =:idAluno " );
		}
		if( dataInicio != null ){
			stb.append( " and obj.dataRecebimento >=:dataInicio " );
		}
		if( dataFim != null ){
			stb.append( " and obj.dataRecebimento <=:dataFim " );
		}
		if( recebido != null ){
			stb.append( " and obj.recebido =:recebido " );
		}
		if( idProd != null ){
			stb.append( " and pro.id =:idProd " );
		}
		
		stb.append( " ORDER BY " );
		stb.append( " 	pro.id ASC " );
		if( idProd != null ){
			stb.append( " 	obj.dataRecebimento ASC " );
		}
	}
	
	@SuppressWarnings("unchecked")
	public List recuperarAllContasReceberMensalidadeAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException{
		
		StringBuffer stb = new StringBuffer();
		stb.append( " SELECT " );
		stb.append( "  alu.nomeAluno, " );
		//stb.append( "  pro.descricao, " );
		//stb.append( "  prodVen.quantidadeVendida, " );
		//stb.append( "  prodVen.valorUnitario, " );
		//stb.append( "  prodVen.valorTotal, " );
		stb.append( "  obj.valorTotal, " );
		stb.append( "  obj.dataRecebimento, " );
		stb.append( "  obj.tipoContaReceber " );
		stb.append( " FROM " );
		stb.append( " ContaReceberEntity obj " );
		stb.append( " INNER JOIN obj.aluno alu " );
		stb.append( " WHERE obj.tipoContaReceber = 'M' " ); //somente mensalidade
		if( idAluno != null ){
			stb.append( " and alu.id =:idAluno " );
		}
		if( dataInicio != null ){
			stb.append( " and obj.dataRecebimento >=:dataInicio " );
		}
		if( dataFim != null ){
			stb.append( " and obj.dataRecebimento <=:dataFim " );
		}
		if( recebido != null ){
			stb.append( " and obj.recebido =:recebido " );
		}
		
		stb.append( " ORDER BY " );
		stb.append( " 	alu.nomeAluno ASC, " );
		stb.append( " 	obj.dataRecebimento ASC " );
		
		Query query = getSession().createQuery(stb.toString());
		
		if( idAluno != null ){
			query.setLong("idAluno", idAluno);
		}
		if( dataInicio != null ){
			query.setParameter("dataInicio", dataInicio);
		}
		if( dataFim != null ){
			query.setParameter("dataFim", dataFim);
		}
		if( recebido != null ){
			query.setParameter("recebido", recebido);
		}
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List recuperarAllContasReceberEventoAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException{
		
		StringBuffer stb = new StringBuffer();
		stb.append( " SELECT " );
		stb.append( "  alu.nomeAluno, " );
		stb.append( "  ev.descricao, " );
		//stb.append( "  prodVen.quantidadeVendida, " );
		//stb.append( "  prodVen.valorUnitario, " );
		//stb.append( "  prodVen.valorTotal, " );
		stb.append( "  obj.valorTotal, " );
		stb.append( "  obj.dataRecebimento, " );
		stb.append( "  obj.tipoContaReceber " );
		stb.append( " FROM " );
		stb.append( " ContaReceberEntity obj " );
		stb.append( " INNER JOIN obj.aluno alu " );
		stb.append( " INNER JOIN obj.evento ev " );
		stb.append( " WHERE obj.tipoContaReceber = 'E' " ); //somente evento
		if( idAluno != null ){
			stb.append( " and alu.id =:idAluno " );
		}
		if( dataInicio != null ){
			stb.append( " and obj.dataRecebimento >=:dataInicio " );
		}
		if( dataFim != null ){
			stb.append( " and obj.dataRecebimento <=:dataFim " );
		}
		if( recebido != null ){
			stb.append( " and obj.recebido =:recebido " );
		}
		
		stb.append( " ORDER BY " );
		stb.append( " 	alu.nomeAluno ASC, " );
		stb.append( " 	obj.dataRecebimento ASC " );
		
		Query query = getSession().createQuery(stb.toString());
		
		if( idAluno != null ){
			query.setLong("idAluno", idAluno);
		}
		if( dataInicio != null ){
			query.setParameter("dataInicio", dataInicio);
		}
		if( dataFim != null ){
			query.setParameter("dataFim", dataFim);
		}
		if( recebido != null ){
			query.setParameter("recebido", recebido);
		}
		
		return query.list();
		
	}
}
