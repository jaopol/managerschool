/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.FORNECEDOR.FornecedorAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/fornecedor"
						),
	selecao = @PlcConfigSelecao(
			navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),				argumentos = {
					@PlcConfigArgumento(propriedade="nome", operador=Operador.LIKE_PERC_TOTAL, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="nomeFantasia", operador=Operador.LIKE_PERC_TOTAL, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="razaoSocial", operador=Operador.LIKE_PERC_TOTAL, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="cnpj", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="cpf", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="tipoPessoa", operador=Operador.IGUAL_A, formato=Formato.STRING),
					@PlcConfigArgumento(propriedade="status", operador=Operador.IGUAL_A, formato=Formato.STRING)
		})

)
	
//Gerado em 08/04/2013 18:37 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.fornecedorsel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;

