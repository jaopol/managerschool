package com.consisti.sisgesc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

public class ExtratoContas {
	
	private ContaPagar contaPagar;
	private ContaReceber contaReceber;
	private Date dataInicio;
	private Date dataFim;
	private BigDecimal valorTotal;
	
	
	public ContaPagar getContaPagar() {
		return contaPagar;
	}
	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
	}
	public ContaReceber getContaReceber() {
		return contaReceber;
	}
	public void setContaReceber(ContaReceber contaReceber) {
		this.contaReceber = contaReceber;
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
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public ExtratoContas() {
		
	}

	public ExtratoContas(ContaPagar contaPagar, ContaReceber contaReceber, java.util.Date dataInicio, java.util.Date dataFim, java.math.BigDecimal valorTotal) {
		setContaPagar(contaPagar);
		setContaReceber(contaReceber);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setValorTotal(valorTotal);
	}
}
