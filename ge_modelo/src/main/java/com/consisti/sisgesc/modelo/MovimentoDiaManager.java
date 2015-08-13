package com.consisti.sisgesc.modelo;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.persistencia.hibernate.ContaPagarDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.powerlogic.jcompany.comuns.PlcException;



/**
 * Classe de Modelo gerada pelo assistente
 */
public class MovimentoDiaManager extends AppManager {

	public ContaPagarDAO contaPagarDAO;
	public ContaReceberDAO contaReceberDAO;
	
	public MovimentoDiaManager( ContaPagarDAO contaPagarDAO, ContaReceberDAO contaReceberDAO ) {
		this.contaPagarDAO = contaPagarDAO;
		this.contaReceberDAO = contaReceberDAO;
	}
	
	public void pesquisaMovimentoDia(MovimentoDiaEntity movimentoDia, Date date, BancoEntity banco) {
		
		List<ContaPagar> recuperaListaContasAPagar = contaPagarDAO.recuperaListaContasAPagar(date, banco);
		List<ContaReceber> recuperaListaContasAReceber = contaReceberDAO.recuperaListaContasAReceber(date, banco);
		
		movimentoDia.setContasPagar(recuperaListaContasAPagar);
		movimentoDia.setContasReceber(recuperaListaContasAReceber);
		
	}
	
}
