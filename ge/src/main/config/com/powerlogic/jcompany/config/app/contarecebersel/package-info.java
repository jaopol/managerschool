/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF006.ContaReceberAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.CDUF006"
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),				argumentos = {
						@PlcConfigArgumento(propriedade="aluno", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="recebido", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="boletoGerado", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="dataVencimento_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="dataVencimento_ArgFIM", operador=Operador.MENOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="dataRecebimento_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="dataRecebimento_ArgFIM", operador=Operador.MENOR_OU_IGUAL_QUE, formato=Formato.DATE)
			})

)
	
//Gerado em 04/07/2013 22:10 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.contarecebersel;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

