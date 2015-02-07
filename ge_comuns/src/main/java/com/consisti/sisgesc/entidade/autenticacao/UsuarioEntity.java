package com.consisti.sisgesc.entidade.autenticacao;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcIoC;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="USUARIO")
@SequenceGenerator(name="SE_USUARIO", sequenceName="SE_USUARIO")
@AccessType("field")
@PlcIoC(nomeClasseBC="com.consisti.sisgesc.modelo.UsuarioManager")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="UsuarioEntity.querySel2", query="select new UsuarioEntity(obj.id, obj.nome, obj.ativo, obj.login, obj.email, obj.celular) from UsuarioEntity obj order by obj.nome asc"),
	@NamedQuery(name="UsuarioEntity.queryMan", query="from UsuarioEntity obj"),
	@NamedQuery(name="UsuarioEntity.querySel", query="select new UsuarioEntity(obj.id, obj.nome, obj.ativo, obj.email, obj.login, obj.matricula, obj.celular) from UsuarioEntity obj order by obj.nome asc"),
	@NamedQuery(name="UsuarioEntity.querySelLookup", query="select new UsuarioEntity (obj.id, obj.nome, obj.login) from UsuarioEntity obj where obj.id = ? order by obj.id asc")
	//@NamedQuery(name="UsuarioEntity.naoDeveExistirLoginDuplicado", query="select count(*) from UsuarioEntity obj where UPPER( TRIM( obj.login ) ) = UPPER( TRIM( :login ) ) ")
})
public class UsuarioEntity extends Usuario {
	
	private transient PlcSimNao alteraSenha;
	private transient Long idFuncionario;
	private transient boolean alteraCampo = true;
	
    /*
     * Construtor padrão
     */
    public UsuarioEntity() {
    }
    
	public UsuarioEntity(Long id, String nome, String login) {
		this.setId(id);
		this.setNome(nome);
		this.setLogin(login);
	}
	
	public UsuarioEntity(Long id, String nome, PlcSimNao flgProfessor, PlcSimNao flgAdministrador, Long idFuncionario ) {
		this.setId(id);
		this.setNome(nome);
		this.setFlgProfessor(flgProfessor);
		this.setFlgAdministrador(flgAdministrador);
		this.setIdFuncionario(idFuncionario);
	}
	
	@Override
	public String toString() {
		return getNome() != null ? getNome()+" - "+getLogin() : "";
	}

	public UsuarioEntity(Long id, String nome, PlcSimNao ativo, String email, String login, String matricula, String celular) {
		setId(id);
		setNome(nome);
		setAtivo(ativo);
		setEmail(email);
		setLogin(login);
		setMatricula(matricula);
		setCelular(celular);
	}
	
	public PlcSimNao getAlteraSenha() {
		return alteraSenha;
	}
	public void setAlteraSenha(PlcSimNao alteraSenha) {
		this.alteraSenha = alteraSenha;
	}

	public boolean isAlteraCampo() {
		return alteraCampo;
	}

	public void setAlteraCampo(boolean alteraCampo) {
		this.alteraCampo = alteraCampo;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public UsuarioEntity(Long id, String nome, PlcSimNao ativo, String login, String email, String celular) {
		setId(id);
		setNome(nome);
		setAtivo(ativo);
		setLogin(login);
		setEmail(email);
		setCelular(celular);
	}
}
