package com.consisti.sisgesc.entidade.estoque;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.Status;
import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Estoque extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_ESTOQUE")
	@Column (name = "ID_ESTOQUE", nullable=false, length=5)
	private Long id;
	
	@Column (name = "SALDO", length=10)
	@NotNull
	private Long saldo;
	
	@Column (name = "VALOR_TOTAL_ESTOQUE", precision=10, scale=2)
	@NotNull
	private BigDecimal valorTotalEstoque;
	
	@Column (name = "DATA_CRIACAO", length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", length=1)
	@NotNull
	private Status status;
	
	@Column (name = "OBSERVACAO", length=500)
	private String observacao;
	
	@OneToMany (targetEntity = MovimentoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="estoque")
	@ForeignKey(name="FK_MOVIMENTO_ESTOQUE")
	@Valid
	@JoinColumn (name = "ID_ESTOQUE")
	private List<Movimento> movimento;

	@ManyToOne (targetEntity = ProdutoMaterialEntity.class, fetch = FetchType.EAGER)
	@ForeignKey (name="FK_PRODUTO_MATERIAL")
	@JoinColumn (name="ID_PRODUTO_MATERIAL")
	private ProdutoMaterial produtoMaterial;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo=saldo;
	}

	public Date getDataCriacao() {
		if (this.dataCriacao==null){
			return new Date();
		}
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao=dataCriacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status=status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public List<Movimento> getMovimento() {
		return movimento;
	}

	public void setMovimento(List<Movimento> movimento) {
		this.movimento=movimento;
	}

	public BigDecimal getValorTotalEstoque() {
		return valorTotalEstoque;
	}

	public void setValorTotalEstoque(BigDecimal valorTotalEstoque) {
		this.valorTotalEstoque = valorTotalEstoque;
	}

	public ProdutoMaterial getProdutoMaterial() {
		return produtoMaterial;
	}

	public void setProdutoMaterial(ProdutoMaterial produtoMaterial) {
		this.produtoMaterial = produtoMaterial;
	}

}
