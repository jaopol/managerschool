/** ******************************* L�GICA-PADR�O: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define prefer�ncias e invers�es de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.estoque.EstoqueEntradaAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/estoque"
						),
	selecao = @PlcConfigSelecao(
				apiQuerySel = "querySel2",
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),		
				argumentos = {
						@PlcConfigArgumento(propriedade="dataMovimentacao_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="fornecedor", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="dataMovimentacaoFim_ArgFIM", operador=Operador.MENOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="tipoMovimentacao", operador=Operador.IGUAL_A, formato=Formato.STRING)
			})

)
	
//Gerado em 18/04/2014 12:45 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.estoqueentradasel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

