package com.consisti.sisgesc.entidade.financeiro;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.dominio.CarteiraBanco;
import com.consisti.sisgesc.dominio.TipoConta;
import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.consisti.sisgesc.entidade.Endereco;
import com.consisti.sisgesc.entidade.EnderecoEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Banco extends AppBaseEntity {

	@OneToMany (targetEntity = EnderecoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="banco")
	@ForeignKey(name="FK_ENDERECO_BANCO")
	@Valid
	@JoinColumn (name = "ID_BANCO")
	private List<Endereco> endereco;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_BANCO")
	@Column (name = "ID_BANCO", nullable=false, length=5)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO_CONTA", nullable=false, length=1)
	private TipoConta tipoConta;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "BANCO_SUPORTADO", nullable=false, length=4)
	private BancoSuportado bancoSuportado;
	
	@Column (name = "AGENCIA", nullable=false, length=8)
	private String agencia;
	
	@Column (name = "NUMERO_CONTA", nullable=false, length=10)
	private String numeroConta;
	
	@Column (name = "TELEFONE_BANCO", length=14)
	private String telefoneBanco;
	
	@Column (name = "NUMERO_CNAB", length=20)
	private String numeroCnab;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", nullable=false, length=1)
	private AtivoInativo status;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "GERENTE", nullable=false, length=50)
	private String gerente;
	
	@Column (name = "TELEFONE_GERENTE", length=14)
	private String telefoneGerente;
	
	@Column (name = "CELULAR_GERENTE", length=14)
	private String celularGerente;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "EMAIL_GERENTE", length=200)
	private String emailGerente;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "CARTEIRA_BANCO", nullable=false, length=4)
	private CarteiraBanco carteiraBanco;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta=tipoConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia=agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta=numeroConta;
	}

	public String getTelefoneBanco() {
		return telefoneBanco;
	}

	public void setTelefoneBanco(String telefoneBanco) {
		this.telefoneBanco=telefoneBanco;
	}

	public String getNumeroCnab() {
		return numeroCnab;
	}

	public void setNumeroCnab(String numeroCnab) {
		this.numeroCnab=numeroCnab;
	}

	public AtivoInativo getStatus() {
		return status;
	}

	public void setStatus(AtivoInativo status) {
		this.status=status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente=gerente;
	}

	public String getTelefoneGerente() {
		return telefoneGerente;
	}

	public void setTelefoneGerente(String telefoneGerente) {
		this.telefoneGerente=telefoneGerente;
	}

	public String getCelularGerente() {
		return celularGerente;
	}

	public void setCelularGerente(String celularGerente) {
		this.celularGerente=celularGerente;
	}

	public String getEmailGerente() {
		return emailGerente;
	}

	public void setEmailGerente(String emailGerente) {
		this.emailGerente=emailGerente;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco=endereco;
	}

	public BancoSuportado getBancoSuportado() {
		return bancoSuportado;
	}

	public void setBancoSuportado(BancoSuportado bancoSuportado) {
		this.bancoSuportado = bancoSuportado;
	}

	public CarteiraBanco getCarteiraBanco() {
		return carteiraBanco;
	}

	public void setCarteiraBanco(CarteiraBanco carteiraBanco) {
		this.carteiraBanco = carteiraBanco;
	}

}
