/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
	action = com.consisti.sisgesc.controle.jsf.CDU002.FuncionarioAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/CDU002"
						),
	/**
	 * Usa tab-folder como layout do formulario
	 */
	tabFolder = @PlcConfigTabFolder(tabFolderUsa = true, tabFolderLayout = "automatico",
			tabFolderCamposFoco = {"corpo:formulario:nome","corpo:formulario:endereco:0:aluno","corpo:formulario:dadoFuncionario:0:tituloEleitoral","corpo:formulario:horarioTrabalho:0:horaEntrada","corpo:formulario:horarioTrabalho:0:nomeArquivo"})

)
	
//Gerado em 31/01/2013 22:38 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.funcionariomdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigAssistente;
