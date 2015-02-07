package com.consisti.sisgesc.entidade;


import java.util.Date;

import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;

@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Anexo extends AppBaseEntity {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_ANEXO")
	@Column (name = "ID_ANEXOS_ALUNO", nullable=false, length=5)
	private Long id;
	
	@Column (name = "NOME_ARQUIVO")
	private String nomeArquivo;
	
	@Column (name = "EXTENSAO")
	private String extensao;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_ANEXO_ALUNO")
	@JoinColumn (name = "ID_ALUNO")
	private Aluno aluno;
	
	@Column (name = "DESCRICAO")
	private String descricao;

	@Lob
	@Column (name = "ARQUIVO")
	private byte [] arquivo;
	
	@Column (name = "DATA_INCLUSAO")
	@Temporal (TemporalType.DATE)
	private Date dataInclusao;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_ANEXO_FUNCIONARIO")
	@JoinColumn (name = "ID_FUNCIONARIO")
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo=nomeArquivo;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao=extensao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
