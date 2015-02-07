package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
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

import com.powerlogic.jcompany.dominio.valida.PlcValCpf;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class DadoFuncionario extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_DADO_FUNCIONARIO")
	@Column (name = "ID_DADO_FUNCIONARIO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_DADOFUNCIONARIO_FUNCIONARIO")
	@JoinColumn (name = "ID_FUNCIONARIO", nullable=false)
	private Funcionario funcionario;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "ESCOLARIDADE", nullable=false, length=30)
	private String escolaridade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "TITULO_ELEITORAL", length=5)
	private String tituloEleitoral;
	
	@Column (name = "ZONA", length=5)
	private Integer zona;
	
	@Column (name = "SECAO", length=5)
	private Integer secao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "IDENTIDADE", nullable=false, length=15)
	private String identidade;
	
	@Column (name = "DATA_EXPEDICAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExpedicao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "CTPS", nullable=false, length=5)
	private String ctps;
	
	@Column (name = "SERIE", nullable=false, length=5)
	private Integer serie;
	
	@Column (name = "DATA_EMISSAO_CTPS", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissaoCtps;
	
	@Column (name = "PIS", nullable=false, length=5)
	private Integer pis;
	
	@Column (name = "DATA_VINCULO_PIS", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVinculoPis;
	
	@PlcValCpf
	@Column (name = "CPF", nullable=false, length=14)
	private String cpf;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NATURALIDADE", nullable=false, length=50)
	private String naturalidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "FUNCAO", nullable=false, length=50)
	private String funcao;
	
	@Column (name = "RENDA_MENSAL", length=10, precision=10, scale=2)
	private BigDecimal rendaMensal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade=escolaridade;
	}

	public String getTituloEleitoral() {
		return tituloEleitoral;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral=tituloEleitoral;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona=zona;
	}

	public Integer getSecao() {
		return secao;
	}

	public void setSecao(Integer secao) {
		this.secao=secao;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade=identidade;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao=dataExpedicao;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps=ctps;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie=serie;
	}

	public Date getDataEmissaoCtps() {
		return dataEmissaoCtps;
	}

	public void setDataEmissaoCtps(Date dataEmissaoCtps) {
		this.dataEmissaoCtps=dataEmissaoCtps;
	}

	public Integer getPis() {
		return pis;
	}

	public void setPis(Integer pis) {
		this.pis=pis;
	}

	public Date getDataVinculoPis() {
		return dataVinculoPis;
	}

	public void setDataVinculoPis(Date dataVinculoPis) {
		this.dataVinculoPis=dataVinculoPis;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf=cpf;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade=naturalidade;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao=funcao;
	}

	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal=rendaMensal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario=funcionario;
	}

}
