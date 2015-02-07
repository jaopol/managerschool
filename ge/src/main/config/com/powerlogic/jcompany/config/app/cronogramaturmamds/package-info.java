/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE com SubDetalhe *****************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.CDU007.CronogramaTurmaAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/cronogramaturma"
						)

)
	
//Gerado em 19/03/2013 18:46 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.cronogramaturmamds;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
