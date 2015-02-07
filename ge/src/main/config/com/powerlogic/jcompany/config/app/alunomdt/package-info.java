/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
	action = com.consisti.sisgesc.controle.jsf.CDU001.AlunoAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/CDU001"
						),
	/**
	 * Usa tab-folder como layout do formulario
	 */
	tabFolder = @PlcConfigTabFolder(tabFolderUsa = true, tabFolderLayout = "automatico",
			tabFolderCamposFoco = {"corpo:formulario:nomeAluno","corpo:formulario:endereco:0:logradouro","corpo:formulario:certidaoNascimento:0:certidaoNascimentoIdentidade","corpo:formulario:filiacaoMae:0:nomeMae","corpo:formulario:filiacaoPai:0:nomePai","corpo:formulario:saudeAluno:0:fazTratamentoSaude","corpo:formulario:responsavelFinanceiroAluno:0:nome,corpo:formulario:servicoAluno:0:servico,corpo:formulario:anexoAluno:0:nomeAnexo"})

)
	
//Gerado em 15/01/2013 21:37 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.alunomdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;

