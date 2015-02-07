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
package com.consisti.sisgesc.controle.factory;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * sisgesc . 
 * Classe de factory para servir como fábrica de criação de classes de serviço
 */

@Name("AppServiceFactory")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class AppServiceFactory {

	
	/**
	 *
	 * jCompany 5.1.0 - Exemplo de método para gerar PlcEntidadeService
	 *
	@Factory(value="serviceEntidadePlc")
	public PlcEntidadeService geraEntidadeService() {
		return (PlcEntidadeService)Component.getInstance("AppMinhaEntidadeService",true);

	}
	*/ 

}
