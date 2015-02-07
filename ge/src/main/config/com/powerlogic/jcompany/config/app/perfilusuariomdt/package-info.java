/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
	
/**
 * Meta-dados para a camada de controle. Define preferências e inversões de controle de uso somente da camada controle
 */	
@PlcConfigGrupoControle(
	action = com.consisti.sisgesc.controle.jsf.CDUA002.PerfilAction.class,

	/**
	 * Usa layout universal
	 */	
	layoutUniversal = @PlcConfigLayoutUniversal(
						dirBaseFacelets = "/WEB-INF/fcls/autenticacao"
						),
	/**
	 * Usa tab-folder como layout do formulario
	 */
	tabFolder = @PlcConfigTabFolder(tabFolderUsa = true, tabFolderLayout = "automatico",
			tabFolderCamposFoco = {"corpo:formulario:nome","corpo:formulario:perfilUsuario:0:usuario","corpo:formulario:urlAcessoPerfil:0:urlAcesso"})

)
	
//Gerado em 29/01/2013 22:18 por jCompany Code Generator
package com.powerlogic.jcompany.config.app.perfilusuariomdt;

import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigTabFolder;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigLayoutUniversal;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigAssistente;
