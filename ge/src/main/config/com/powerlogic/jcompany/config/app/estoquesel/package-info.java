/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
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
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),		apiQuerySel = "querySel2",
				argumentos = {
						@PlcConfigArgumento(propriedade="saldo", operador=Operador.IGUAL_A, formato=Formato.LONG),
						@PlcConfigArgumento(propriedade="status", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="produtoMaterial", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="fornecedor", operador=Operador.IGUAL_A, formato=Formato.STRING, alias="move"),
						@PlcConfigArgumento(propriedade="dataMovimentacao_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE, alias="move"),
						@PlcConfigArgumento(propriedade="dataMovimentacao_ArgFIM", operador=Operador.MENOR_OU_IGUAL_QUE, formato=Formato.DATE, alias="move")
			})

)
	
//Gerado em 26/11/2013 19:14 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.estoquesel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

