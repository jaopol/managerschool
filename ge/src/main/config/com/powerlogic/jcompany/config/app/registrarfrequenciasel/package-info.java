/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.AppAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/registrarfrequencia"
						),
	selecao = @PlcConfigSelecao(
			navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),				argumentos = {
				@PlcConfigArgumento(propriedade="turma", operador=Operador.IGUAL_A, formato=Formato.STRING),
				@PlcConfigArgumento(propriedade="dataCadastro_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
				@PlcConfigArgumento(propriedade="status", operador=Operador.IGUAL_A, formato=Formato.STRING),
				@PlcConfigArgumento(propriedade="professor", operador=Operador.IGUAL_A, formato=Formato.STRING),
				@PlcConfigArgumento(propriedade="periodo", operador=Operador.IGUAL_A, formato=Formato.STRING),
				@PlcConfigArgumento(propriedade="tipoEducacao", operador=Operador.IGUAL_A, formato=Formato.STRING)
			})

)
	
//Gerado em 12/10/2013 13:00 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.registrarfrequenciasel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

