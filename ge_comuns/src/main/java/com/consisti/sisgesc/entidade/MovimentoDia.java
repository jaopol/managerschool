package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaPagarEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;


@MappedSuperclass
@PlcValidacaoUnificada
public abstract class MovimentoDia extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_MOVIMENTO_DIA")
	@Column (name = "ID_MOVIMENTO_DIA", nullable=false, length=5)
	private Long id;
	
	@Column (name = "SALDO_DIA", nullable=false, length=19)
	private BigDecimal saldoDia;
	
	@Column (name = "TOTAL_ENTRADA", nullable=false, length=19)
	private BigDecimal totalEntrada;
	
	@Column (name = "TOTAL_SAIDA", nullable=false, length=19)
	private BigDecimal totalSaida;
	
	@Column (name = "SALDO_TOTAL", nullable=false, length=19)
	private BigDecimal saldoTotal;
	
	@Column (name = "VALOR_TOTAL_CAIXA", nullable=false, length=19)
	private BigDecimal valorTotalCaixa;
	
	@Column (name = "VALOR_RETIRADA", nullable=false, length=19)
	private BigDecimal valorRetirada;
	
	@Column (name = "DATA_MOVIMENTO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMovimento;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public BigDecimal getSaldoDia() {
		return saldoDia;
	}

	public void setSaldoDia(BigDecimal saldoDia) {
		this.saldoDia=saldoDia;
	}

	public BigDecimal getTotalEntrada() {
		return totalEntrada;
	}

	public void setTotalEntrada(BigDecimal totalEntrada) {
		this.totalEntrada=totalEntrada;
	}

	public BigDecimal getTotalSaida() {
		return totalSaida;
	}

	public void setTotalSaida(BigDecimal totalSaida) {
		this.totalSaida=totalSaida;
	}

	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal=saldoTotal;
	}

	public BigDecimal getValorTotalCaixa() {
		return valorTotalCaixa;
	}

	public void setValorTotalCaixa(BigDecimal valorTotalCaixa) {
		this.valorTotalCaixa=valorTotalCaixa;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento=dataMovimento;
	}

	public BigDecimal getValorRetirada() {
		return valorRetirada;
	}

	public void setValorRetirada(BigDecimal valorRetirada) {
		this.valorRetirada = valorRetirada;
	}

}
