package com.consisti.sisgesc.entidade.estoque;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;

import com.consisti.sisgesc.dominio.TipoMovimentacao;
import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.consisti.sisgesc.entidade.Fornecedor;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Movimento extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_MOVIMENTO")
	@Column (name = "ID_MOVIMENTO", length=5)
	private Long id;
	
	@Column (name = "QTDE_ENTRADA", length=10)
	private Long qtdeEntrada;
	
	@Column (name = "QTDE_SAIDA", length=10)
	private Long qtdeSaida;
	
	@Column (name = "VALOR_UNITARIO", precision=10, scale=2)
	private BigDecimal valorUnitario;
	
	@Column (name = "DATA_MOVIMENTACAO", length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMovimentacao;
	
	@Column (name = "OBSERVACAO", length=500)
	private String observacao;
	
	@SuppressWarnings("unchecked")
	@ManyToOne (targetEntity = EstoqueEntity.class, fetch = FetchType.EAGER)
	@ForeignKey(name="FK_MOVIMENTO_ESTOQUE")
	@JoinColumn (name = "ID_ESTOQUE")
	private Estoque estoque;
	
	@Column(name="TIPO_MOVIMENTACAO")
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	@NotNull
	@Column(name="VALOR_TOTAL", precision=10, scale=2)
	private BigDecimal valorTotal;
	
	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.EAGER)
	@ForeignKey (name="FK_TURMA_ESTOQUE")
	@JoinColumn (name="ID_TURMA")
	private Turma turma;
	
	@ManyToOne (targetEntity = FornecedorEntity.class, fetch = FetchType.LAZY)
	@ForeignKey (name="FK_FORNECEDOR_MATERIAL")
	@JoinColumn (name="ID_FORNECEDOR")
	private Fornecedor fornecedor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getQtdeEntrada() {
		return qtdeEntrada;
	}

	public void setQtdeEntrada(Long qtdeEntrada) {
		this.qtdeEntrada=qtdeEntrada;
	}

	public Long getQtdeSaida() {
		return qtdeSaida;
	}

	public void setQtdeSaida(Long qtdeSaida) {
		this.qtdeSaida=qtdeSaida;
	}

	public Date getDataMovimentacao() {
		if (this.dataMovimentacao==null){
			return new Date();
		}
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao=dataMovimentacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque=estoque;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
