package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.util.List;

import com.consisti.sisgesc.entidade.ServicoAluno;
import com.consisti.sisgesc.persistencia.hibernate.ServicoDAO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class ServicoAlunoManager extends AppManager {
	
	private ServicoDAO servicoDAO;
	
	public ServicoAlunoManager( ServicoDAO servicoDAO ) {
		this.servicoDAO = servicoDAO;
	}
	
	
	/**
	 * Utilizado para somar e retornar o valor de todos os serviços do aluno
	 * @param listServicoAluno
	 * @return
	 * @throws PlcException 
	 */
	public BigDecimal getValorTotalServicosAluno( List<ServicoAluno> listServicoAluno ) throws PlcException{
		 
		BigDecimal valorTotalServico = new BigDecimal(0); 
		for (ServicoAluno servicoAlunoEntity : listServicoAluno) { 
			
			if(servicoAlunoEntity.getServico() != null ){
				valorTotalServico = valorTotalServico.add( servicoDAO.recuperaValorServico( servicoAlunoEntity.getServico().getId() ) );
			}
		}
		return valorTotalServico;
	}

}
