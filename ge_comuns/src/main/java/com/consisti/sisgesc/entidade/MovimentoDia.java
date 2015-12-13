package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;


@SuppressWarnings("serial")
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
	
	@Column (name = "VALOR_RETIRADA", length=19)
	private BigDecimal valorRetirada;
	
	@Column (name = "DATA_MOVIMENTO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMovimento;
	
	@Column (name = "SALDO_DIA_ANTERIOR", length=10, precision=10, scale=2)
	private BigDecimal saldoDiaAnterior;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "CAIXA_FECHADO", length=1)
	private PlcSimNao caixaFechado;
	
	@Column (name = "DATA_FECHAMENTO", length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	

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

	public BigDecimal getSaldoDiaAnterior() {
		return saldoDiaAnterior;
	}

	public void setSaldoDiaAnterior(BigDecimal saldoDiaAnterior) {
		this.saldoDiaAnterior = saldoDiaAnterior;
	}

	public PlcSimNao getCaixaFechado() {
		return caixaFechado;
	}

	public void setCaixaFechado(PlcSimNao caixaFechado) {
		this.caixaFechado = caixaFechado;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

}
