/** ******************************** LÓGICA-PADRÃO: TABULAR *************************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(

	action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF001.FormaPagamentoAction.class,
	
	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.CDUF001"
						),


	tabular = @PlcConfigTabular(
				propReferenciaDesprezar = "descricao",
				testaDuplicataFlagDesprezar = true,
				numeroNovos = 4)
				
	
,
		comportamento = @PlcConfigComportamento(detalheLembra = true)
)
	
//Gerado em 06/06/2013 21:13 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.formapagamentotab;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.comuns.PlcConfigComportamento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabular;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
