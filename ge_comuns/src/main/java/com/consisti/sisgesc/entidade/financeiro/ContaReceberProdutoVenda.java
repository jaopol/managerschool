package com.consisti.sisgesc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
public class ContaReceberProdutoVenda extends AppBaseEntity {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CONTA_RECEBER_PRODUTO_VENDA")
	@Column (name = "ID_CONTA_RECEBER_PRODUTO_VENDA", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = ContaReceberEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTARECEBERPRODUTOVENDA_CONTARECEBER")
	@JoinColumn (name = "ID_CONTA_RECEBER", nullable=false)
	private ContaReceber contaReceber;

	@ManyToOne (targetEntity = ProdutoVendaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTARECEBERPRODUTOVENDA_PRODUTOVENDA")
	@JoinColumn (name = "ID_PRODUTO_VENDA", nullable=false)
	private ProdutoVenda produtoVenda;
	
	@Column (name = "QUANTIDADE_VENDIDA", nullable=false, length=5)
	private Integer quantidadeVendida;
	
	@Column (name = "VALOR_UNITARIO", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorUnitario;
	
	@Column (name = "VALOR_TOTAL", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorTotal;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public ProdutoVenda getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(ProdutoVenda produtoVenda) {
		this.produtoVenda=produtoVenda;
	}

	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida=quantidadeVendida;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario=valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal=valorTotal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public ContaReceber getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber(ContaReceber contaReceber) {
		this.contaReceber=contaReceber;
	}

}
