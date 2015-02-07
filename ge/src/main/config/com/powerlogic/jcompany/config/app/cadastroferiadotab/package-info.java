/** ******************************** LÓGICA-PADRÃO: TABULAR *************************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(

	action = com.consisti.sisgesc.controle.jsf.cadastroFeriado.CadastroFeriadoAction.class,
	
	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/cadastroFeriado"
						),


	tabular = @PlcConfigTabular(
				propReferenciaDesprezar = "descricao",
				testaDuplicataFlagDesprezar = true,
				numeroNovos = 4)
				
	
,
		comportamento = @PlcConfigComportamento(detalheLembra = true)
)
	
//Gerado em 02/04/2013 21:26 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.cadastroferiadotab;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.comuns.PlcConfigComportamento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabular;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
