package com.consisti.sisgesc.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.consisti.sisgesc.dominio.EstadoCivil;
import com.consisti.sisgesc.dominio.TipoImpostoRenda;
import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValCpf;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ResponsavelFinanceiroAluno extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_RESPONSAVEL_FINANCEIRO_ALUNO")
	@Column (name = "ID_RESPONSAVEL_FINANCEIRO_ALUNO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_RESPONSAVELFINANCEIROALUNO_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME", nullable=false, length=50)
	private String nome;
	
	@PlcValCpf
	@Column (name = "CPF", nullable=false, length=14)
	private String cpf;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "IDENTIDADE", length=14)
	private String identidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "ENDERECO", nullable=false, length=50)
	private String endereco;
	
	@Column (name = "CEP", nullable=false, length=10)
	private String cep;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "BAIRRO", nullable=false, length=50)
	private String bairro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NUMERO", nullable=false, length=8)
	private String numero;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "COMPLEMENTO", length=50)
	private String complemento;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "CIDADE", nullable=false, length=50)
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UF", nullable=false, length=2)
	private Uf uf;
	
	@Column (name = "TELEFONE", nullable=false, length=14)
	private String telefone;
	
	@Column (name = "CELULAR", length=14)
	private String celular;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "EMAIL", length=200)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "QUAL_IMPOSTO", length=1)
	private TipoImpostoRenda qualImposto;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Enumerated(EnumType.STRING)
	@Column (name = "IMPOSTO_RENDA", nullable=false, length=1)
	private PlcSimNao impostoRenda;
	
	@Column (name = "DATA_NASCIMENTO", nullable=false, length=17)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column(name="ESTADO_CIVEL", length=1)
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column(name="NACIONALIDADE")
	private String nacionalidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column(name="PROFISSAO")
	private String profissao;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column(name="LOCALIDADE")
	private String localTrabalho;
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf=cpf;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade=identidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco=endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro=bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade=cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf=uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone=telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular=celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public TipoImpostoRenda getQualImposto() {
		return qualImposto;
	}

	public void setQualImposto(TipoImpostoRenda qualImposto) {
		this.qualImposto=qualImposto;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public PlcSimNao getImpostoRenda() {
		return impostoRenda;
	}

	public void setImpostoRenda(PlcSimNao impostoRenda) {
		this.impostoRenda = impostoRenda;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
