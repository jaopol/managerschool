package com.consisti.sisgesc.entidade.financeiro;

import java.util.Date;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class PlanoContas extends AppBaseEntity {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_PLANO_CONTAS")
	@Column (name = "ID_PLANO_CONTAS", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	@Column (name = "DATA_CRIACAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao=dataCriacao;
	}

}
