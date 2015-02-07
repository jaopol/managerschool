package com.consisti.sisgesc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.consisti.sisgesc.entidade.Fornecedor;
import com.consisti.sisgesc.entidade.Funcionario;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.ManyToOne;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;
import org.hibernate.annotations.ForeignKey;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ContaPagar extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CONTA_PAGAR")
	@Column (name = "ID_CONTA_PAGAR", nullable=false, length=5)
	private Long id;

	@ManyToOne (targetEntity = FornecedorEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTAPAGAR_FAVORECIDO")
	@JoinColumn (name = "ID_FAVORECIDO", nullable=false)
	private Fornecedor favorecido;
	
	@ManyToOne (targetEntity = PlanoContasEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTAPAGAR_PLANOCONTAS")
	@JoinColumn (name = "ID_PLANO_CONTAS", nullable=false)
	private PlanoContas planoContas;
	
	@ManyToOne (targetEntity = TipoPlanoContasEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTAPAGAR_TIPOPLANOCONTAS")
	@JoinColumn (name = "ID_TIPO_PLANO_CONTAS", nullable=false)
	private TipoPlanoContas tipoPlanoContas;
	
	@ManyToOne (targetEntity = FormaPagamentoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTAPAGAR_FORMAPAGAMENTO")
	@JoinColumn (name = "ID_FORMA_PAGAMENTO")
	private FormaPagamento formaPagamento;
	
	@Column (name = "DATA_PAGAMENTO", nullable=true, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPagamento;
	
	@Column (name = "DATA_INCLUSAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;
	
	@Column (name = "VALOR_PAGAR", nullable=false,  length=10, precision=10, scale=2)
	private BigDecimal valorPagar;
	
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	@Column (name = "NUMERO_DOCUMENTO", length=20)
	private Long numeroDocumento;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "CONTA_PAGA", length=1)
	private PlcSimNao contaPaga;
	
	@Column (name = "DATA_VENCIMENTO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	@ManyToOne (targetEntity = BancoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTAPAGAR_BANCO")
	@JoinColumn (name = "ID_BANCO")
	private Banco banco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Fornecedor getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(Fornecedor favorecido) {
		this.favorecido=favorecido;
	}

	public PlanoContas getPlanoContas() {
		return planoContas;
	}

	public void setPlanoContas(PlanoContas planoContas) {
		this.planoContas=planoContas;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento=formaPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento=dataPagamento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao=dataInclusao;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar=valorPagar;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento=numeroDocumento;
	}

	public TipoPlanoContas getTipoPlanoContas() {
		return tipoPlanoContas;
	}

	public void setTipoPlanoContas(TipoPlanoContas tipoPlanoContas) {
		this.tipoPlanoContas = tipoPlanoContas;
	}

	public PlcSimNao getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(PlcSimNao contaPaga) {
		this.contaPaga = contaPaga;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
