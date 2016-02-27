/** ******************************** LÓGICA-PADRÃO: CRUD *************************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF006.ContaReceberEventoAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.CDUF006"
						)

,
		comportamento = @PlcConfigComportamento(entradaEmLote=true)
)
	
//Gerado em 16/02/2016 22:02 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.contarecebereventoman;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.comuns.PlcConfigComportamento;
