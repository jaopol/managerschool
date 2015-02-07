/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.ContratoManAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/contrato"
						)

)
	
//Gerado em 26/02/2013 21:29 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.contratomdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigAssistente;
