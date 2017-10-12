package com.consisti.sisgesc.modelo;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.persistencia.hibernate.ContaPagarDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.consisti.sisgesc.persistencia.hibernate.MovimentoDiaDAO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;



/**
 * Classe de Modelo gerada pelo assistente
 */
public class MovimentoDiaManager extends AppManager {

	public ContaPagarDAO contaPagarDAO;
	public ContaReceberDAO contaReceberDAO;
	public MovimentoDiaDAO movimentoDiaDAO;
	
	public MovimentoDiaManager( ContaPagarDAO contaPagarDAO, ContaReceberDAO contaReceberDAO, MovimentoDiaDAO movimentoDiaDAO ) {
		this.contaPagarDAO = contaPagarDAO;
		this.contaReceberDAO = contaReceberDAO;
		this.movimentoDiaDAO = movimentoDiaDAO;
	}
	
	public void pesquisaMovimentoDia(MovimentoDiaEntity movimentoDia, Date date, Long idBanco) throws PlcException {
		
		List<ContaPagar> recuperaListaContasAPagar = contaPagarDAO.recuperaListaContasAPagar(date, idBanco);
		List<ContaReceber> recuperaListaContasAReceber = contaReceberDAO.recuperaListaContasAReceber(date, idBanco);
		if( movimentoDia.getCaixaFechado() == null || PlcSimNao.N.equals( movimentoDia.getCaixaFechado() ) ){
			movimentoDia.setSaldoDiaAnterior( movimentoDiaDAO.recuperarUltimoSaldoTotal() );
		}
		movimentoDia.setContasPagar(recuperaListaContasAPagar);
		movimentoDia.setContasReceber(recuperaListaContasAReceber);
		
	}
	
	public void recuperaSaldoDiaAnterior(){
		
	}
	
}
