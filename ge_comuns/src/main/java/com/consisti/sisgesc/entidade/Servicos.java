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

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.PeriodoServico;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Servicos extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_SERVICOS")
	@Column (name = "ID_SERVICOS", nullable=false, length=5)
	private Long id;

	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", nullable=false, length=1)
	private AtivoInativo status;
	
	@Column (name = "VALOR_SERVICO", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorServico;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PERIODO_SERVICO", nullable=false, length=1)
	private PeriodoServico periodoServico;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", nullable=true, length=200)
	private String observacao;
	
	private transient String periodoDescricao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public AtivoInativo getStatus() {
		return status;
	}

	public void setStatus(AtivoInativo status) {
		this.status=status;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico=valorServico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public PeriodoServico getPeriodoServico() {
		return periodoServico;
	}

	public void setPeriodoServico(PeriodoServico periodoServico) {
		this.periodoServico = periodoServico;
	}
	
	public String getPeriodoDescricao() {
		return periodoDescricao;
	}

	public void setPeriodoDescricao(String periodoDescricao) {
		this.periodoDescricao = periodoDescricao;
	}

}
