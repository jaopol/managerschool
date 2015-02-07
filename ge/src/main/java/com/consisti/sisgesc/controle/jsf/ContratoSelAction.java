package com.consisti.sisgesc.controle.jsf;

import java.util.List;

import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContratoSelAction extends RelatorioActionPlc  {
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
	}
	
	/**
	 * Vai exibir o contrato do aluno
	 * de acordo com o contrato selecionado
	 * @throws PlcException 
	 */
	public void verContrato() throws PlcException{
		
		if (contextHelperPlc.getRequest().getAttribute("linha")!=null){
			List<PlcBaseVO> itens = logicaItensPlc.getItensPlc();
			ContratoEntity contrato = (ContratoEntity) itens.get(Integer.valueOf(contextHelperPlc.getRequest().getAttribute("linha").toString()));
			byte [] contratoAluno = ((IAppFacade)getServiceFacade()).recuperaContratoAluno(contrato.getId()); 
		}
		
	}
	
}
