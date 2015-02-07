package com.consisti.sisgesc.entidade.estoque;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.dominio.Status;
import com.consisti.sisgesc.entidade.Fornecedor;
import com.consisti.sisgesc.entidade.estoque.ProdutoMaterialEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="ESTOQUE")
@SequenceGenerator(name="SE_ESTOQUE", sequenceName="SE_ESTOQUE")
@AccessType("field")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="EstoqueEntity.querySel2", query="select distinct new EstoqueEntity(obj.id, " +
			                                                                   "obj.saldo, " +
			                                                                   "obj.status, " +
			                                                                   "obj.produtoMaterial.id , " +
			                                                                   "obj.produtoMaterial.descricao," +
			                                                                   "obj.dataCriacao," +
			                                                                   "obj.valorTotalEstoque, " +
			                                                                   "move.dataMovimentacao) " +
			                                                                   "from EstoqueEntity obj " +
			                                                                   "left outer join obj.produtoMaterial " +
			                                                                   "left outer join obj.movimento move " +
			                                                                   "order by obj.produtoMaterial.descricao asc"),
	@NamedQuery(name="EstoqueEntity.queryMan", query="from EstoqueEntity obj"),
	@NamedQuery(name="EstoqueEntity.querySel", query="select new EstoqueEntity(obj.id, obj.produtoMaterial, obj.saldo, obj.dataCriacao, obj.status) from EstoqueEntity obj left outer join obj.movimento mov " +
			                                           "order by obj.produtoMaterial asc"),
	@NamedQuery(name="EstoqueEntity.querySelLookup", query="select new EstoqueEntity (obj.id, obj.produtoMaterial) from EstoqueEntity obj where obj.id = ? order by obj.id asc")
})
public class EstoqueEntity extends Estoque {
 	
	@Transient
	private Date dataInicio;
	
	@Transient
	private Date dataFim;
	
	@Transient
	private List<EstoqueEntity> dadosEstoque;
	
	@Transient
	private Long idMovimento;
	
	@Transient
	private Long entradaStr;
	
	@Transient
	private Long saidaStr;
	
	@Transient
	private BigDecimal valorUnitarioStr;
	
	@Transient
	private Long idFornecedor;
	
	@Transient
	private String fornecedorStr;
	
	@Transient
	private String valorTotalEstoqueStr;
	
	@Transient
	private Fornecedor fornecedor;
	
	@Transient
	private Date dataMovimentacao;
	
	@Transient
	private Date dataMovimentacaoFim;
	
	@Transient
	private String valorTotalSomado;
	
	@Transient
	private Long saldoTotalSomado;
	
    /*
     * Construtor padrão
     */
    public EstoqueEntity() {
    }
	public EstoqueEntity(Long id, ProdutoMaterial produtoMaterial) {
		this.setId(id);
		this.setProdutoMaterial(produtoMaterial);
	}
	@Override
	public String toString() {
		return getProdutoMaterial() != null ? getProdutoMaterial().getDescricao():"";
	}

	public EstoqueEntity(Long id, ProdutoMaterial produtoMaterial, Long saldo, Date dataCriacao, Status status) {
		setId(id);
		setProdutoMaterial(produtoMaterial);
		setSaldo(saldo);
		setDataCriacao(dataCriacao);
		setStatus(status);
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public List<EstoqueEntity> getDadosEstoque() {
		return dadosEstoque;
	}
	public void setDadosEstoque(List<EstoqueEntity> dadosEstoque) {
		this.dadosEstoque = dadosEstoque;
	}
	
	public EstoqueEntity(Long id, Long saldo, Status status, Long produtoMaterialId, String produtoMaterialDescricao, 
		 	             Date dataCriacao, BigDecimal valorTotalEstoque, Date dataMovimentacao) {
		setId(id);
		setSaldo(saldo);
		setStatus(status);
		if (getProdutoMaterial() == null){
			setProdutoMaterial(new ProdutoMaterialEntity());
		}
		getProdutoMaterial().setId(produtoMaterialId);
		getProdutoMaterial().setDescricao(produtoMaterialDescricao);
		setDataCriacao(dataCriacao);
		setValorTotalEstoque(valorTotalEstoque);
		setDataMovimentacao(dataMovimentacao);
	}
	
	public Long getIdMovimento() {
		return idMovimento;
	}
	public void setIdMovimento(Long idMovimento) {
		this.idMovimento = idMovimento;
	}
	public Long getEntradaStr() {
		return entradaStr;
	}
	public void setEntradaStr(Long entradaStr) {
		this.entradaStr = entradaStr;
	}
	public Long getSaidaStr() {
		return saidaStr;
	}
	public void setSaidaStr(Long saidaStr) {
		this.saidaStr = saidaStr;
	}
	public BigDecimal getValorUnitarioStr() {
		return valorUnitarioStr;
	}
	public void setValorUnitarioStr(BigDecimal valorUnitarioStr) {
		this.valorUnitarioStr = valorUnitarioStr;
	}
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getFornecedorStr() {
		return fornecedorStr;
	}
	public void setFornecedorStr(String fornecedorStr) {
		this.fornecedorStr = fornecedorStr;
	}
	public String getValorTotalEstoqueStr() {
		return NumberFormat.getCurrencyInstance().format(getValorTotalEstoque());
	}
	public void setValorTotalEstoqueStr(String valorTotalEstoqueStr) {
		this.valorTotalEstoqueStr = valorTotalEstoqueStr;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public Date getDataMovimentacaoFim() {
		return dataMovimentacaoFim;
	}
	public void setDataMovimentacaoFim(Date dataMovimentacaoFim) {
		this.dataMovimentacaoFim = dataMovimentacaoFim;
	}
	public String getValorTotalSomado() {
		return valorTotalSomado;
	}
	public void setValorTotalSomado(String valorTotalSomado) {
		this.valorTotalSomado = valorTotalSomado;
	}
	public Long getSaldoTotalSomado() {
		return saldoTotalSomado;
	}
	public void setSaldoTotalSomado(Long saldoTotalSomado) {
		this.saldoTotalSomado = saldoTotalSomado;
	}
}
