package com.consisti.sisgesc.entidade;


import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Turma extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_TURMA")
	@Column (name = "ID_TURMA", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "IDADE_MAXIMA", nullable=false, length=5)
	private String idadeMaxima;
	
	@Column (name = "IDADE_MINIMA", nullable=false, length=5)
	private String idadeMinima;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public String getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(String idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public String getIdadeMaxima() {
		return idadeMaxima;
	}

	public void setIdadeMaxima(String idadeMaxima) {
		this.idadeMaxima = idadeMaxima;
	}

}
