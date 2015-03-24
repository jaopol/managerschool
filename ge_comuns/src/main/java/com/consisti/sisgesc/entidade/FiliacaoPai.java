package com.consisti.sisgesc.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.dominio.valida.PlcValCpf;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class FiliacaoPai extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FILIACAO_PAI")
	@Column (name = "ID_FILIACAO_PAI", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_FILIACAOPAI_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_PAI", length=50)
	private String nomePai;
	
	@Column (name = "DATA_NASCIMENTO", length=10)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "IDENTIDADE", length=20)
	private String identidade;
	
	@PlcValCpf
	@Column (name = "CPF", length=14)
	private String cpf;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "GRAU_INSTRUCAO", length=30)
	private String grauInstrucao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "PROFISSAO", length=30)
	private String profissao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "LOCAL_TRABALHO", length=50)
	private String localTrabalho;
	
	@Column (name = "TELEFONE", length=14)
	private String telefone;
	
	@Column (name = "CELULAR", length=14)
	private String celular;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "EMAIL", length=100)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai=nomePai;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento=dataNascimento;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade=identidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf=cpf;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao=grauInstrucao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao=profissao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho=localTrabalho;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone=telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular=celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

}
