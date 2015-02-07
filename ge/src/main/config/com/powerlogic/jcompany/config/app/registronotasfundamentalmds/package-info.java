/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE com SubDetalhe *****************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
		action = com.consisti.sisgesc.controle.jsf.CDU012.RegistroNotasFundamentalAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/registronotasfundamental"
						),
	/**
	 * Usa tab-folder como layout do formulario
	 */
	tabFolder = @PlcConfigTabFolder(tabFolderUsa = false, tabFolderLayout = "automatico",
			tabFolderCamposFoco = {"corpo:formulario:professor","corpo:formulario:registroNotasFundamentalDet:0:turma"})

)
	
//Gerado em 20/03/2013 22:10 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.registronotasfundamentalmds;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;

