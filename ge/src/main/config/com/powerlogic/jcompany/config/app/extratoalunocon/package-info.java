/** ******************************* LÓGICA-PADRÃO: CONSULTA ***********************************
 *  ******************************************************************************************/

/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.financeiro.CDUF008.ExtratoAlunoAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/financeiro.CDUF008"
						),
	selecao = @PlcConfigSelecao(
				navegador = @PlcConfigNavegador(numPorPagina=20, dinamicoTipo=com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador.DinamicoTipo.DINAMICO_PAGINA),				argumentos = {
						@PlcConfigArgumento(propriedade="aluno", operador=Operador.IGUAL_A, formato=Formato.STRING),
						@PlcConfigArgumento(propriedade="dataInicio_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="dataFim_ArgINI", operador=Operador.MAIOR_OU_IGUAL_QUE, formato=Formato.DATE),
						@PlcConfigArgumento(propriedade="recebido", operador=Operador.IGUAL_A, formato=Formato.STRING)
			})

)
	
//Gerado em 18/01/2016 21:19 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.extratoalunocon;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Operador;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento.Formato;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigArgumento;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigNavegador;

