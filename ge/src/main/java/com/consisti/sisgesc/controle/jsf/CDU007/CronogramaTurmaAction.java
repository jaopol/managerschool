package com.consisti.sisgesc.controle.jsf.CDU007;

import java.io.IOException;
import java.util.Date;

import javax.faces.context.FacesContext;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.CronogramaTurmaEntity;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class CronogramaTurmaAction extends AppAction  {
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		trataBotoes();
		super.trataBotoesConformeLogicaApos();
		
		if (contextHelperPlc.getRequest().getSession().getAttribute("gravado")!=null){
			helperMsgJsfPlc.msg("msg.gravacao.sucesso", PlcConstantes.MSG.IND_COR_MENSAGEM_VALORES.IND_AZUL);
			contextHelperPlc.getRequest().getSession().removeAttribute("gravado");
		}
		
	}

	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		CronogramaTurmaEntity cronograma = (CronogramaTurmaEntity)entidadePlc;
		cronograma.setDataCadastro( new Date() );
		
		return super.novoApos();
	}
	
	@Override
	protected String gravaApos() {
		
		String retorno = super.gravaApos();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cronogramaturmamds?chPlc="+entidadePlc.getId());
			contextHelperPlc.getRequest().getSession().setAttribute("gravado", "S");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return retorno;
	}
	
	/**
	 * trata apresentação dos botoes
	 */
	private void trataBotoes() {
		
		/*if( ((CronogramaTurmaEntity)entidadePlc) != null && ((CronogramaTurmaEntity)entidadePlc).getId() != null ){
			contextHelperPlc.getRequest().setAttribute("exibeImprimirCronograma", "S");
		}
		else{
			contextHelperPlc.getRequest().setAttribute("exibeImprimirCronograma", "N");
		}*/		
	}
}
