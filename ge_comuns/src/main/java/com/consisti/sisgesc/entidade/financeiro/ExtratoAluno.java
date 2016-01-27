package com.consisti.sisgesc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.consisti.sisgesc.dominio.TipoContaReceber;
import com.consisti.sisgesc.entidade.AlunoEntity;

@SuppressWarnings("serial")
public class ExtratoAluno implements Serializable {

	private AlunoEntity aluno;
	private ContaReceberEntity contaReceber;
	private Date dataInicio;
	private Date dataFim;

	private String nomeAluno;
	private String descricaoProduto;
	private Integer quantidadeVendida;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private BigDecimal valorTotalMensalidade;
	
	public ExtratoAluno(String nomeAluno, String descricaoProduto, Integer quantidadeVendida, 
			BigDecimal valorUnitario, BigDecimal valorTotal, BigDecimal valorTotalMensalidade, TipoContaReceber tipoContaReceber){
		
		this.setNomeAluno(nomeAluno);
		this.setDescricaoProduto(descricaoProduto);
		this.setQuantidadeVendida(quantidadeVendida);
		this.setValorUnitario(valorUnitario);
		this.setValorTotal(valorTotal);
		if( TipoContaReceber.M.equals(tipoContaReceber) ){
			this.setValorTotalMensalidade(valorTotalMensalidade);
			this.setDescricaoProduto("MENSALIDADE");
		}
	}
	
	public ContaReceberEntity getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber(ContaReceberEntity contaReceber) {
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

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}
	
	public ExtratoAluno(Long id, AlunoEntity aluno, java.util.Date dataInicio, java.util.Date dataFim, ContaReceberEntity contaReceber) {
		setAluno(aluno);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setContaReceber(contaReceber);
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotalMensalidade() {
		return valorTotalMensalidade;
	}

	public void setValorTotalMensalidade(BigDecimal valorTotalMensalidade) {
		this.valorTotalMensalidade = valorTotalMensalidade;
	}

}
