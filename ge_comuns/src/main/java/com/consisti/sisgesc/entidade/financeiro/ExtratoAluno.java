package com.consisti.sisgesc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.entidade.AlunoEntity;

@SuppressWarnings("serial")
public class ExtratoAluno implements Serializable {
	
	private AlunoEntity aluno;
	private ContaReceberEntity contaReceber;
	private Date dataInicio;
	private Date dataFim;

	private String nomeAluno;
	private Long idProduto;
	private String descricaoProduto;
	private Integer quantidadeVendida;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private BigDecimal valorTotalMensalidade;
	private Date dataRecebimento;
	
	private List<ExtratoAluno> listExtrato;
	private BigDecimal valorTotalLista;
	
	private String valorTotalStr;
	private String valorTotalMensalidadeStr;
	private String valorUnitarioStr;
	private String valorTotalListaStr;
	
	public ExtratoAluno(String nomeAluno, Long idProduto, String descricaoProduto, Integer quantidadeVendida, 
			BigDecimal valorUnitario, BigDecimal valorTotal, Date dataRecebimento){
		
		this.setNomeAluno(nomeAluno);
		this.setDescricaoProduto(descricaoProduto);
		this.setQuantidadeVendida(quantidadeVendida);
		this.setValorUnitario(valorUnitario);
		this.setValorTotal(valorTotal);
		this.setIdProduto(idProduto);
		this.setDataRecebimento(dataRecebimento);
	}
	
	public ExtratoAluno(String nomeAluno, BigDecimal valorTotalMensalidade, Date dataRecebimento){
		
		this.setNomeAluno(nomeAluno);
		this.setValorTotalMensalidade(valorTotalMensalidade);
		this.setDescricaoProduto("MENSALIDADE");
		this.setDataRecebimento(dataRecebimento);
	}

	public ExtratoAluno(String nomeAluno, String descricaoEvento, BigDecimal valorTotalMensalidade, Date dataRecebimento){
		
		this.setNomeAluno(nomeAluno);
		this.setValorTotal(valorTotalMensalidade);
		this.setDescricaoProduto(descricaoEvento);
		this.setDataRecebimento(dataRecebimento);
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

	public ExtratoAluno() {
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

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public List<ExtratoAluno> getListExtrato() {
		return listExtrato;
	}

	public void setListExtrato(List<ExtratoAluno> listExtrato) {
		this.listExtrato = listExtrato;
	}

	public BigDecimal getValorTotalLista() {
		return valorTotalLista;
	}

	public void setValorTotalLista(BigDecimal valorTotalLista) {
		this.valorTotalLista = valorTotalLista;
	}

	public String getValorTotalStr() {
		if( getValorTotal() != null ){
			valorTotalStr = NumberFormat.getCurrencyInstance().format( getValorTotal() );
		}
		else{
			valorTotalStr = "";
		}
		return valorTotalStr;
	}

	public String getValorTotalMensalidadeStr() {
		if(getValorTotalMensalidade() != null ){
			valorTotalMensalidadeStr = NumberFormat.getCurrencyInstance().format( getValorTotalMensalidade() );
		}
		else{
			valorTotalMensalidadeStr = "";
		}
		return  valorTotalMensalidadeStr;
	}

	public String getValorUnitarioStr() {
		
		if( getValorUnitario() != null ){
			valorUnitarioStr = NumberFormat.getCurrencyInstance().format( getValorUnitario() );
		}
		else{
			valorUnitarioStr = "";
		}
		return valorUnitarioStr;
	}

	public String getValorTotalListaStr() {
		if( getValorTotalLista() != null ){
			valorTotalListaStr = NumberFormat.getCurrencyInstance().format( getValorTotalLista() );
		}
		return valorTotalListaStr;
	}

}
