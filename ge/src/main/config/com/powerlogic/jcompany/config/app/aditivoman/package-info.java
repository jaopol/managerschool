/** ******************************** LÓGICA-PADRÃO: CRUD *************************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.AditivoAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/aditivo"
						)

)
	
//Gerado em 24/01/2013 23:22 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.aditivoman;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
