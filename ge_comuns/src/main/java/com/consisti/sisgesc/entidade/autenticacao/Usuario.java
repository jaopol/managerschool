package com.consisti.sisgesc.entidade.autenticacao;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.Email;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Usuario extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_USUARIO")
	@Column (name = "ID_USUARIO", nullable=false, length=5)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ATIVO", nullable=true, length=1)
	private PlcSimNao ativo;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME", nullable=false, length=50)
	private String nome;
	
	@Column (name = "LOGIN", nullable=false, length=10)
	private String login;
	
	@Email
	@Column (name = "EMAIL", nullable=true, length=250)
	private String email;
	
	@Column (name = "SENHA", nullable=false, length=40)
	private String senha;
	
	@Column (name = "CONFIRMA_SENHA", nullable=true, length=40)
	private String confirmaSenha;
	
	@Column (name = "MATRICULA", nullable=true, length=10)
	private String matricula;
	
	@Column (name = "CELULAR", nullable=true, length=13)
	private String celular;
	
	@Column (name = "ROLE", nullable=true, length=20)
	private String role;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "FLG_PROFESSOR", nullable=true, length=1)
	private PlcSimNao flgProfessor;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "FLG_ADMINISTRADOR", nullable=true, length=1)
	private PlcSimNao flgAdministrador;
	
	@Column (name = "ID_FUNCIONARIO", nullable=true)
	private Long idFuncionario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public PlcSimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(PlcSimNao ativo) {
		this.ativo=ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome=nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login=login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha=senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula=matricula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular=celular;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public PlcSimNao getFlgProfessor() {
		return flgProfessor;
	}

	public void setFlgProfessor(PlcSimNao flgProfessor) {
		this.flgProfessor = flgProfessor;
	}

	public PlcSimNao getFlgAdministrador() {
		return flgAdministrador;
	}

	public void setFlgAdministrador(PlcSimNao flgAdministrador) {
		this.flgAdministrador = flgAdministrador;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

}
