/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.CDU012.RegistroNotasFundamentalAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/registronotasfundamental"
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),		argumentos = {
					@PlcConfigArgumento(propriedade="professor", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="disciplina", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="anoLetivo", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="bimestre", operador=Operador.IGUAL_A, formato=Formato.STRING)
			})

)
	
//Gerado em 20/03/2013 22:10 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.registronotasfundamentalsel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;

