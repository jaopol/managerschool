package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.consisti.sisgesc.entidade.financeiro.ExtratoAluno;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class ExtratoAlunoManager extends AppManager {
	
	private ContaReceberDAO contaReceberDAO;
	
	public ExtratoAlunoManager(ContaReceberDAO contaReceberDAO) {
		this.contaReceberDAO = contaReceberDAO;
	}

	@SuppressWarnings("unchecked")
	public List<ExtratoAluno> getListExtratoProdutoAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException {
		List<ExtratoAluno> listExtrato = new ArrayList<ExtratoAluno>();
		
		List<Long> idProdContasReceber = contaReceberDAO.recuperarIdProdContasReceberDiarioAluno(idAluno, dataInicio, dataFim, recebido);
		
		if( idProdContasReceber != null && !idProdContasReceber.isEmpty() ){
			for (Long idProd : idProdContasReceber) {
				ExtratoAluno extratoAluno = new ExtratoAluno();
				extratoAluno.setValorTotalLista( BigDecimal.ZERO );
				
				List contasReceberDiarioAluno = contaReceberDAO.recuperarAllContasReceberDiarioAluno(idAluno, dataInicio, dataFim, recebido, idProd);
				
				if( contasReceberDiarioAluno != null && !contasReceberDiarioAluno.isEmpty() ){
					
					for (Iterator iterator = contasReceberDiarioAluno.iterator(); iterator.hasNext();) {
						Object[] object = (Object[]) iterator.next();
						
						ExtratoAluno extratoAlunoAux = new ExtratoAluno( (String)object[0], (Long)object[1], (String)object[2], (Integer)object[3], (BigDecimal)object[4], (BigDecimal)object[5], (Date)object[6]); 
						
						extratoAluno.setValorTotalLista( extratoAluno.getValorTotalLista().add( extratoAlunoAux.getValorTotal() ) );
						
						if( extratoAluno.getListExtrato() == null ){
							extratoAluno.setListExtrato( new ArrayList<ExtratoAluno>() );
						}
						extratoAluno.getListExtrato().add(extratoAlunoAux);
					}
					listExtrato.add(extratoAluno);
				}
			}
		}
		
		List contasReceberMensalidadeAluno = contaReceberDAO.recuperarAllContasReceberMensalidadeAluno(idAluno, dataInicio, dataFim, recebido);
		
		if( contasReceberMensalidadeAluno != null && !contasReceberMensalidadeAluno.isEmpty() ){
			ExtratoAluno extratoAluno = new ExtratoAluno();
			extratoAluno.setValorTotalLista( BigDecimal.ZERO );
			for (Iterator iterator = contasReceberMensalidadeAluno.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				
				ExtratoAluno extratoAlunoAux = new ExtratoAluno( (String)object[0], (BigDecimal)object[1], (Date)object[2] ); 
				
				extratoAluno.setValorTotalLista( extratoAluno.getValorTotalLista().add( extratoAlunoAux.getValorTotalMensalidade() ) );
				
				if( extratoAluno.getListExtrato() == null ){
					extratoAluno.setListExtrato( new ArrayList<ExtratoAluno>() );
				}
				extratoAluno.getListExtrato().add(extratoAlunoAux);
			}
			listExtrato.add( extratoAluno );
		}
		
		List contasReceberEventoAluno = contaReceberDAO.recuperarAllContasReceberEventoAluno(idAluno, dataInicio, dataFim, recebido);
		
		if( contasReceberEventoAluno != null && !contasReceberEventoAluno.isEmpty() ){
			ExtratoAluno extratoAluno = new ExtratoAluno();
			extratoAluno.setValorTotalLista( BigDecimal.ZERO );
			for (Iterator iterator = contasReceberEventoAluno.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				
				ExtratoAluno extratoAlunoAux = new ExtratoAluno( (String)object[0], (String)object[1], (BigDecimal)object[2], (Date)object[3] ); 
				
				extratoAluno.setValorTotalLista( extratoAluno.getValorTotalLista().add( extratoAlunoAux.getValorTotal() ) );
				
				if( extratoAluno.getListExtrato() == null ){
					extratoAluno.setListExtrato( new ArrayList<ExtratoAluno>() );
				}
				extratoAluno.getListExtrato().add(extratoAlunoAux);
			}
			listExtrato.add( extratoAluno );
		}
		
		return listExtrato;
	}

}
