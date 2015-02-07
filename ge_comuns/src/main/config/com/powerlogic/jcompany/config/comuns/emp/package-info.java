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
@PlcConfigEmpresa(nome = "Powerlogic SA", dominio = "www.powerlogic.com.br", sigla = "PLC", 
			logotipo = "/plc/midia/login-logo-empresa.png", endereco = "Rua Paraíba, 330 / 19º andar. CEP:30130-917 - Funcionarios - Belo Horizonte/MG",
			emailSuporte = "suporte@powerlogic.com.br",telefoneSuporte = "55 31 3555-0050")
			
@PlcConfigSufixoClasse (entidade="Entity",entidadeGerente="Manager")

@PlcConfigPacote (entidade=".entidade.")
		
package com.powerlogic.jcompany.config.comuns.emp;

import com.powerlogic.jcompany.config.comuns.PlcConfigEmpresa;
import com.powerlogic.jcompany.config.comuns.PlcConfigPacote;
import com.powerlogic.jcompany.config.comuns.PlcConfigSufixoClasse;

