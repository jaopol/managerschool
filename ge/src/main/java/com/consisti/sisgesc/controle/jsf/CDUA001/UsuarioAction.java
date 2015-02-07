package com.consisti.sisgesc.controle.jsf.CDUA001;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class UsuarioAction extends AppAction  {
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		UsuarioEntity usuario = (UsuarioEntity)entidadePlc;
		setaStatus( usuario );
		return super.novoApos();
	}

	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesAntes()
	 */
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		UsuarioEntity usuario = (UsuarioEntity)entidadePlc;
		comparaSenha( usuario );
		
		return super.gravaSimplesAntes();
	}
	
	@Override
	protected void gravaSimplesApos() throws PlcException {
		
		UsuarioEntity usuario = (UsuarioEntity)entidadePlc;
		usuario.setAlteraSenha( PlcSimNao.N );
		
		super.gravaSimplesApos();
	}
	
	/**
	 * Utilizado para verificar se a senha esta igual a confirmada
	 * @param usuario
	 * @throws PlcException
	 */
	private void comparaSenha(UsuarioEntity usuario) throws PlcException {
		
		if( StringUtils.isNotEmpty( usuario.getSenha() ) && StringUtils.isNotEmpty( usuario.getConfirmaSenha() ) ){
			if( !usuario.getSenha().equals( usuario.getConfirmaSenha() ) ){
				throw new PlcException("error.cadastroUsuario.senhaDiferente");
			}
		}
	}

	/**
	 * Seta o Ativo como default igual a S
	 * @param usuario
	 */
	private void setaStatus(UsuarioEntity usuario) {
		usuario.setAtivo( PlcSimNao.S );
	}
	
	/**
	 * Utilizado quando o usuario escolher alterar a senha
	 * @return
	 */
	public String alterarSenha(){
		
		UsuarioEntity usuario = (UsuarioEntity)entidadePlc;
		if( PlcSimNao.S.equals( usuario.getAlteraSenha() ) ){
			usuario.setSenha( new String() );
			usuario.setConfirmaSenha( new String() );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	public String recuperaFuncionario() throws PlcException{
	
		UsuarioEntity usuario = (UsuarioEntity)entidadePlc;
		if( usuario.getIdFuncionario() != null ){
			
			IAppFacade fc = (IAppFacade)getServiceFacade();
			FuncionarioEntity funcionario = fc.recuperaFuncionarioById( usuario.getIdFuncionario() );
			
			if( funcionario != null ){
				usuario.setNome( funcionario.getNome() );
				usuario.setEmail( funcionario.getEmail() );
				usuario.setCelular( funcionario.getCelular() );
				usuario.setFlgProfessor( funcionario.getProfessor() );
				usuario.setIdFuncionario( funcionario.getId() );
				usuario.setAlteraCampo(false);
			}
			
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
}
