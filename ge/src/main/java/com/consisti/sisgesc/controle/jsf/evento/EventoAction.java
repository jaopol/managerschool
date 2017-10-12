package com.consisti.sisgesc.controle.jsf.evento;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class EventoAction extends AppAction  {
	
	@Override
	protected String gravaApos() {
		try {
			atualizaClassesLookupDoBanco();
		} catch (PlcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.gravaApos();
	}
	
}
