package com.consisti.sisgesc.entidade;


import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ResponsavelAlunoCasoAcidente extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_RESPONSAVEL_ALUNO_CASO_ACIDENTE")
	@Column (name = "ID_RESPONSAVEL_ALUNO_CASO_ACIDENTE", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_RESPONSAVELALUNOCASOACIDENTE_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;

	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME", length=50)
	private String nome;
	
	@Column (name = "TELEFONE", length=13)
	private String telefone;
	
	@Column (name = "CELULAR", length=13)
	private String celular;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome=nome;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

}
