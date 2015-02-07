/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
	action = com.consisti.sisgesc.controle.jsf.CDU006.GerirProfessorAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/CDU006"
						),
	/**
	 * Usa tab-folder como layout do formulario
	 */
	tabFolder = @PlcConfigTabFolder(tabFolderUsa = false, tabFolderLayout = "automatico",
			tabFolderCamposFoco = {"corpo:formulario:professor","corpo:formulario:turmaProfessor:0:turma"})

)
	
//Gerado em 18/03/2013 22:16 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.gerirprofessormdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;

