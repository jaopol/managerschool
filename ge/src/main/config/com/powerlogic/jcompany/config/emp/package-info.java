/*  																													
	    				   jCompany Developer Suite																		
			    		Copyright (C) 2008  Powerlogic																	
	 																													
	    Este programa é licenciado com todos os seus códigos fontes. Você pode modificá-los e							
	    utilizá-los livremente, inclusive distribuí-los para terceiros quando fizerem parte de algum aplicativo 		
	    sendo cedido, segundo os termos de licenciamento gerenciado de código aberto da Powerlogic, definidos			
	    na licença 'Powerlogic Open-Source Licence 2.0 (POSL 2.0)'.    													
	  																													
	    A Powerlogic garante o acerto de erros eventualmente encontrados neste código, para os clientes licenciados, 	
	    desde que todos os códigos do programa sejam mantidos intactos, sem modificações por parte do licenciado. 		
	 																													
	    Você deve ter recebido uma cópia da licença POSL 2.0 juntamente com este programa.								
	    Se não recebeu, veja em <http://www.powerlogic.com.br/licencas/posl20/>.										
	 																													
	    Contatos: plc@powerlogic.com.br - www.powerlogic.com.br 														
																														
 */ 
 /** ******************* REDEFINIÇÕES DE META-DADOS GLOBAIS DA EMPRESA **********************
  ********************** Configurações default para toda a empresa *************************
  *******************************************************************************************/
			
@PlcConfigEmpresa(nome = "CONSIS-TI", dominio = "www.consisti.com.br", sigla = "FAC", 
			logotipo = "/plc/midia/login-logo-empresa.png", endereco = "",
			emailSuporte = "jaopol@gmail.com",telefoneSuporte = "(31)9214-7924")
			
@PlcConfigAparencia(pele = "azul", layout = "sistema",layoutFormato=LayoutFormato.CSS,
					ajaxUsa = true, destacaCampoFocadoUsa = true , riaUsa="S", parcialAjaxUsa="N")
			    	 
package com.powerlogic.jcompany.config.emp;

import com.powerlogic.jcompany.config.comuns.PlcConfigEmpresa;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigAparencia;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigAparencia.LayoutFormato;

