package com.consisti.sisgesc.modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class UsuarioManager extends AppManager {
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.modelo.PlcBaseBO#antesPersistencia(com.powerlogic.jcompany.comuns.PlcBaseVO, com.powerlogic.jcompany.comuns.PlcBaseVO, java.lang.String)
	 */
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		UsuarioEntity usuario = (UsuarioEntity)entidadeAtual;
		criptografaSenha( usuario );
		setRoles(usuario);
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

	/**
	 * Utilizado para criptografar a senha para gravar
	 * @param usuario
	 * @throws PlcException 
	 * 
	 */
	private void criptografaSenha(UsuarioEntity usuario) throws PlcException {
		//somente se for um novo registro o usuario for trocar a senha
		if( usuario.getId() == null || PlcSimNao.S.equals( usuario.getAlteraSenha() ) ){
		
			String md5 = gerarMd5(usuario);
			//seta a senha criptografada
			usuario.setSenha( md5.toString() );
			usuario.setConfirmaSenha( md5.toString() );
		}
	}

	/**
	 * Seta as roles de acordo com o cadastro.
	 * Roles utilizado para validacao do contaner
	 * @param usuario
	 */
	private void setRoles(UsuarioEntity usuario) {
		//Utilizado para passar pela validação do container
		if( PlcSimNao.S.equals( usuario.getFlgAdministrador() ) ){
			usuario.setRole( "Administrador" );
		}
		else if( PlcSimNao.S.equals( usuario.getFlgProfessor() ) ){
			usuario.setRole( "Professor" );
		}
		else{
			usuario.setRole( "Membros" );
		}
	}

	/**
	 * Gera a criptografia MD5
	 * @param usuario
	 * @return String - senha criptografada
	 * @throws PlcException
	 */
	private String gerarMd5(UsuarioEntity usuario) throws PlcException{
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		
			BigInteger hash = new BigInteger(1, md.digest( usuario.getSenha().getBytes() ) );
			String str = hash.toString(16);
			if( str.length() % 2 != 0 ){
				str = "0" + str;
			}
			return str;
		} 
		catch (NoSuchAlgorithmException e) {
			throw new PlcException(e);
		}
	}

}
