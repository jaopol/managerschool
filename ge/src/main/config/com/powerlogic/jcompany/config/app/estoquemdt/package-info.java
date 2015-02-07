/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
	action = com.consisti.sisgesc.controle.jsf.estoque.EstoqueAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/estoque"
						)

)
	
//Gerado em 26/11/2013 19:14 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.estoquemdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigAssistente;
