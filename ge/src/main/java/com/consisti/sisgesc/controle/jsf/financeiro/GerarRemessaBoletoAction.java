package com.consisti.sisgesc.controle.jsf.financeiro;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.controle.jsf.PlcBaseLogicaItens;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class GerarRemessaBoletoAction extends AppAction  {
	
	
	/* (non-Javadoc)
	 * @see com.consisti.sisgesc.controle.jsf.AppAction#trataBotoesConformeLogicaApos()
	 */
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute("exibeGerarRemessa", "S");
		
		super.trataBotoesConformeLogicaApos();
	}
	
	/**
	 * @return
	 * @throws PlcException
	 */
	public String gerarArquivoRemessa() throws PlcException{
		
		ContaReceberEntity conta = (ContaReceberEntity)entidadePlc;
		
		List<PlcBaseVO> listConta = (List<PlcBaseVO>) logicaItensPlc.getItensPlc();
		
		try {
			IAppFacade fc = (IAppFacade)getServiceFacade();
			HttpServletResponse response = contextHelperPlc.getResponse();
			ArquivoRemessa.exportarRemessa( conta, fc, response );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
}
