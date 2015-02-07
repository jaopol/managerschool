/** ******************************* LÓGICA-PADRÃO: CONSULTA ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF006.ContaReceberDemandaAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.CDUF006"
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),		apiQuerySel = "querySel4",
				argumentos = {
						@PlcConfigArgumento(propriedade="nomeAluno", operador=Operador.LIKE_PERC_FINAL, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="turma", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="tipoEducacao", operador=Operador.IGUAL_A, formato=Formato.STRING)
			})

)
	
//Gerado em 06/08/2014 16:12 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.contareceberdemandacon;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

