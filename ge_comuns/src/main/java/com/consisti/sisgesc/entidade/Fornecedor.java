package com.consisti.sisgesc.entidade;

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

import org.hibernate.validator.Email;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.TipoFavorecido;
import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.dominio.valida.PlcValCnpj;
import com.powerlogic.jcompany.dominio.valida.PlcValCpf;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Fornecedor extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FORNECEDOR")
	@Column (name = "ID_FORNECEDOR", nullable=false)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME")
	private String nome;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_FANTASIA")
	private String nomeFantasia;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "RAZAO_SOCIAL")
	private String razaoSocial;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO_PESSOA", length=1)
	private TipoFavorecido tipoPessoa;
	
	@PlcValCpf
	@Column (name = "CPF")
	private String cpf;
	
	@PlcValCnpj
	@Column (name = "CNPJ")
	private String cnpj;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "RUA")
	private String rua;
	
	@Column (name = "NUMERO")
	private String numero;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "COMPLEMENTO")
	private String complemento;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UF", length=2)
	private Uf uf;
	
	@Column (name = "TELEFONE_CONTATO", length=13)
	private String telefoneContato;
	
	@Column (name = "OBSERVACAO")
	private String observacao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_CONTATO")
	private String nomeContato;
	
	@Column (name = "OUTRO_MUNICIPIO")
	private String outroMunicipio;
	
	@Column (name = "CELULAR", length=13)
	private String celular;
	
	@Column (name = "DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column (name = "RG")
	private String rg;
	
	@Column (name = "EMAIL")
	@Email
	private String email;
	
	@Column (name = "STATUS")
	@Enumerated(EnumType.STRING)
	private AtivoInativo status;
	
	@Column (name = "SITE")
	private String site;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome=nome;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia=nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial=razaoSocial;
	}

	public TipoFavorecido getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoFavorecido tipoPessoa) {
		this.tipoPessoa=tipoPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf=cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj=cnpj;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua=rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero=numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento=complemento;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf=uf;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato=telefoneContato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getOutroMunicipio() {
		return outroMunicipio;
	}

	public void setOutroMunicipio(String outroMunicipio) {
		this.outroMunicipio = outroMunicipio;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AtivoInativo getStatus() {
		return status;
	}

	public void setStatus(AtivoInativo status) {
		this.status = status;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
