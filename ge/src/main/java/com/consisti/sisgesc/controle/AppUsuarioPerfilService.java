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
package com.consisti.sisgesc.controle;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.consisti.sisgesc.comuns.AppUsuarioPerfilVO;
import com.consisti.sisgesc.entidade.autenticacao.PerfilEntity;
import com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcBaseUsuarioPerfilVO;
import com.powerlogic.jcompany.comuns.facade.IPlcFacade;
import com.powerlogic.jcompany.controle.service.PlcBaseUsuarioPerfilService;

/**
 * sisgesc . Implementar aqui lógicas de perfil do usuário
 * da aplicação (user profiling)
 */
@SuppressWarnings("serial")
public class AppUsuarioPerfilService extends PlcBaseUsuarioPerfilService {
	/**
	 * jCompany 5.0: Lógicas de Inicialização de Perfil de Usuario
	 * Recebe a requisição, o perfil do usuário preenchido no ancestral e 
	 * a interface para chamada da persistência.
	 *
	 * Preencher o objeto de Perfil com informações específicas, especializando-o
	 * se necessário.
	 */
	@Override
	public PlcBaseUsuarioPerfilVO registraPerfilEspecifico(HttpServletRequest request,
			 HttpServletResponse response, PlcBaseUsuarioPerfilVO plcPerfilVO, IPlcFacade plcImpl) throws Exception {
		IAppFacade facade = (IAppFacade) plcImpl;
		
		//TODO NAO DELETAR SE PRECISAR APENAS COMENTAR
		/*
		 * NECESSARIO PARA FAZER AS VALIDACOES DE ACESSO
		 * */
		AppUsuarioPerfilVO appUsuarioPerfilVO = (AppUsuarioPerfilVO) plcPerfilVO;
		
		PerfilEntity  perfil = facade.recuperaPerfilUsuarioByLoginUsuario( plcPerfilVO.getLogin() );
		UsuarioEntity usuario = facade.recuperaUsuarioByLogin( plcPerfilVO.getLogin() );
		
		if( perfil != null && usuario != null){
			appUsuarioPerfilVO.setUsuarioExclui( perfil.getExclui() );
			appUsuarioPerfilVO.setUsuarioGrava( perfil.getGrava() );
			appUsuarioPerfilVO.setUsuarioPesquisa( perfil.getPesquisa() );
			appUsuarioPerfilVO.setUsuarioProfessor( usuario.getFlgProfessor() );
			appUsuarioPerfilVO.setUsuarioAdm( usuario.getFlgAdministrador() );
			appUsuarioPerfilVO.setNomeUsuario( usuario.getNome() );
			appUsuarioPerfilVO.setIdUsuario( usuario.getId() );
			appUsuarioPerfilVO.setIdFuncionario( usuario.getIdFuncionario() );
			
			List<PerfilEntity> urlAcessoPerfil = facade.recuperaListaUrlPerfilByLoginUsuario( plcPerfilVO.getLogin() );
			
			if( urlAcessoPerfil != null ){
				if( appUsuarioPerfilVO.getFiltros() == null )
					appUsuarioPerfilVO.setFiltros( new ArrayList<String>() );
				
				for (PerfilEntity perfilEntity : urlAcessoPerfil) {
					appUsuarioPerfilVO.getFiltros().add( perfilEntity.getUrlAcesso() );
				}
			}
		}
		else{
			return null;
		}

		return appUsuarioPerfilVO;

		}
}

