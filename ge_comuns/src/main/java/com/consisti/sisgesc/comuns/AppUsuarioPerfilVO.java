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
package com.consisti.sisgesc.comuns;

import com.powerlogic.jcompany.comuns.PlcBaseUsuarioPerfilVO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
* sisgesc. Implementar aqui atributos de personalização
* específicos do usuario.
*/
public class AppUsuarioPerfilVO extends PlcBaseUsuarioPerfilVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PlcSimNao usuarioExclui;
	PlcSimNao usuarioPesquisa;
	PlcSimNao usuarioGrava;
	PlcSimNao usuarioProfessor;
	PlcSimNao usuarioAdm;
	String nomeUsuario;
	Long idUsuario;
	Long idFuncionario;
	
	public PlcSimNao getUsuarioExclui() {
		return usuarioExclui;
	}
	public void setUsuarioExclui(PlcSimNao usuarioExclui) {
		this.usuarioExclui = usuarioExclui;
	}
	public PlcSimNao getUsuarioPesquisa() {
		return usuarioPesquisa;
	}
	public void setUsuarioPesquisa(PlcSimNao usuarioPesquisa) {
		this.usuarioPesquisa = usuarioPesquisa;
	}
	public PlcSimNao getUsuarioGrava() {
		return usuarioGrava;
	}
	public void setUsuarioGrava(PlcSimNao usuarioGrava) {
		this.usuarioGrava = usuarioGrava;
	}
	public PlcSimNao getUsuarioProfessor() {
		return usuarioProfessor;
	}
	public void setUsuarioProfessor(PlcSimNao usuarioProfessor) {
		this.usuarioProfessor = usuarioProfessor;
	}
	public PlcSimNao getUsuarioAdm() {
		return usuarioAdm;
	}
	public void setUsuarioAdm(PlcSimNao usuarioAdm) {
		this.usuarioAdm = usuarioAdm;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	
	
}
