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
package com.consisti.sisgesc.controle.listener;

import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

import com.powerlogic.jcompany.controle.PlcConstantes;
import com.powerlogic.jcompany.controle.cache.PlcCacheSessaoVO;
import com.powerlogic.jcompany.controle.listener.PlcHttpSessionListener;

/**
 * rhdemofcls. Classe que implementa o DP "Listener" para Sessão
 */
public class AppHttpSessionListener extends PlcHttpSessionListener {
	/**
	 * rhdemofcls: Realiza procedimentos no momento de encerramento de cada Sessão 
	 */
    @Override
	public void aoEncerrarSessao (HttpSessionEvent event) {
		
		log.debug("Aplicacao: Encerrando Sessao");
	}
	
	/**
	 *  rhdemofcls: Realiza procedimentos no momento de inicialização de cada Sessão 
	 */
    @Override
	public void aoInicializarSessao (HttpSessionEvent event,PlcCacheSessaoVO plcSessao) {
						
		Logger log = Logger.getLogger(this.getClass());
		
		log.debug("Aplicacao: Iniciando Sessao");
		
		// Coloca Javascript do jcompany
		event.getSession().setAttribute(PlcConstantes.JAVASCRIPT.JAVASCRIPT_JCOMPANY,"/plc/javascript/PlcGeral.js");
		// Coloca Javascript complementar da aplicação
		event.getSession().setAttribute(PlcConstantes.JAVASCRIPT.JAVASCRIPT_ESPECIFICO,"/plc/javascript/AppGeral.js");
		// Coloca CSS default
		event.getSession().setAttribute(PlcConstantes.GUI.PELE.CSS_ESPECIFICO,"/plc/css-w2/" + plcSessao.getPele() +"/PlcPele.css");
		// Coloca CSS default para 256 cores, ou seja, máquinas com monitores com baixa resolução
		// Os layouts do jCompany irão comutar automaticamente entre um e outro
		event.getSession().setAttribute(PlcConstantes.GUI.PELE.CSS_ESPECIFICO_256,"/plc/css-w2/" + plcSessao.getPele() +"/PlcPele256.css");
		
		// JSF - Coloca informacoes de leiaute na sessao
		plcSessao.setIndLayoutReduzido("N");
		event.getSession().setAttribute(PlcConstantes.SESSION_CACHE_KEY,plcSessao);
		
		// Auxiliar provisorio para manter compatibilidade
		event.getSession().setAttribute("contextPathPlc","./../");

	}
}
