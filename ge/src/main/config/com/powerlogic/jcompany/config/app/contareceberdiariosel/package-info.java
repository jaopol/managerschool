/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF006.ContaReceberDiarioAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.contareceberdiario"
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),		apiQuerySel = "querySel3",
				argumentos = {
						@PlcConfigArgumento(propriedade="aluno", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="formaRecebimento", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="dataRecebimento_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="tipoContaReceber", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="recebido", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="numeroDocumento", operador=Operador.LIKE_PERC_INICIO, formato=Formato.STRING)
			})

)
	
//Gerado em 04/06/2015 18:54 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.contareceberdiariosel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

