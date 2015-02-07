package com.consisti.sisgesc.entidade.financeiro;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ContaReceber extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CONTA_RECEBER")
	@Column (name = "ID_CONTA_RECEBER", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTARECEBER_ALUNO")
	@JoinColumn (name = "ID_ALUNO")
	private Aluno aluno;
	
	@ManyToOne (targetEntity = BancoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTARECEBER_BANCO")
	@JoinColumn (name = "ID_BANCO", nullable=true)
	private Banco banco;
	
	@ManyToOne (targetEntity = FormaPagamentoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTARECEBER_FORMARECEBIMENTO")
	@JoinColumn (name = "ID_FORMA_RECEBIMENTO", nullable=false)
	private FormaPagamento formaRecebimento;
	
	@Column (name = "VALOR_DOCUMENTO", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorDocumento;
	
	@Column (name = "JURO_VALOR", length=10, precision=10, scale=2)
	private BigDecimal juroValor;
	
	@Column (name = "JURO_PERCENTUAL", length=10, precision=10, scale=2)
	private BigDecimal juroPercentual;
	
	@Column (name = "DESCONTO_VALOR", length=10, precision=10, scale=2)
	private BigDecimal descontoValor;
	
	@Column (name = "DESCONTO_PERCENTUAL", length=10, precision=10, scale=2)
	private BigDecimal descontoPercentual;
	
	@Column (name = "VALOR_TOTAL", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorTotal;
	
	@Column (name = "DATA_VENCIMENTO", nullable=false, length=11)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Column (name = "DATA_RECEBIMENTO", length=11)
	@Temporal(TemporalType.DATE)
	private Date dataRecebimento;
	
	@Column (name = "DATA_DOCUMENTO", nullable=false, length=11)
	@Temporal(TemporalType.DATE)
	private Date dataDocumento;
	
	@Column (name = "NUMERO_DOCUMENTO", length=20)
	private String numeroDocumento;
	
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	@Lob
	@Column (name = "BOLETO")
	private byte [] boleto;
	
	@Column (name = "DATA_BOLETO", nullable=true, length=11)
	@Temporal(TemporalType.DATE)
	private Date dataBoleto;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "BOLETO_GERADO", length=1)
	private PlcSimNao boletoGerado;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "RECEBIDO", length=1)
	private PlcSimNao recebido;
	
	@Column (name = "NOSSO_NUMERO", length=16)
	private String nossoNumero;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "REMESSA_GERADO", length=1)
	private PlcSimNao remessaGerado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco=banco;
	}

	public FormaPagamento getFormaRecebimento() {
		return formaRecebimento;
	}

	public void setFormaRecebimento(FormaPagamento formaRecebimento) {
		this.formaRecebimento=formaRecebimento;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento=valorDocumento;
	}

	public BigDecimal getJuroValor() {
		return juroValor;
	}

	public void setJuroValor(BigDecimal juroValor) {
		this.juroValor=juroValor;
	}

	public BigDecimal getJuroPercentual() {
		return juroPercentual;
	}

	public void setJuroPercentual(BigDecimal juroPercentual) {
		this.juroPercentual=juroPercentual;
	}

	public BigDecimal getDescontoValor() {
		return descontoValor;
	}

	public void setDescontoValor(BigDecimal descontoValor) {
		this.descontoValor=descontoValor;
	}

	public BigDecimal getDescontoPercentual() {
		return descontoPercentual;
	}

	public void setDescontoPercentual(BigDecimal descontoPercentual) {
		this.descontoPercentual=descontoPercentual;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal=valorTotal;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento=dataVencimento;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento=dataRecebimento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento=dataDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento=numeroDocumento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public byte[] getBoleto() {
		return boleto;
	}

	public void setBoleto(byte[] boleto) {
		this.boleto = boleto;
	}

	public PlcSimNao getBoletoGerado() {
		return boletoGerado;
	}

	public void setBoletoGerado(PlcSimNao boletoGerado) {
		this.boletoGerado = boletoGerado;
	}

	public PlcSimNao getRecebido() {
		return recebido;
	}

	public void setRecebido(PlcSimNao recebido) {
		this.recebido = recebido;
	}

	public Date getDataBoleto() {
		return dataBoleto;
	}

	public void setDataBoleto(Date dataBoleto) {
		this.dataBoleto = dataBoleto;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public PlcSimNao getRemessaGerado() {
		return remessaGerado;
	}

	public void setRemessaGerado(PlcSimNao remessaGerado) {
		this.remessaGerado = remessaGerado;
	}

}
