package com.consisti.sisgesc.entidade.financeiro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class FormaPagamento extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FORMA_PAGAMENTO")
	@Column (name = "ID_FORMA_PAGAMENTO", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "QUANTIDADE_PARCELA", nullable=true, length=2)
	private Integer quantidadeParcela;
	
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
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

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela=quantidadeParcela;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

}
