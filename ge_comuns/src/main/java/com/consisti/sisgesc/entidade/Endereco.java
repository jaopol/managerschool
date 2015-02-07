package com.consisti.sisgesc.entidade;

import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;
import com.consisti.sisgesc.entidade.financeiro.Banco;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;

@MappedSuperclass
@PlcValidacaoUnificada
@SuppressWarnings("serial")
public abstract class Endereco extends AppBaseEntity {

	
	@ManyToOne (targetEntity = BancoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_ENDERECO_BANCO")
	@JoinColumn (name = "ID_BANCO", nullable = true)
	private Banco banco;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_ENDERECO")
	@Column (name = "ID_ENDERECO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_ENDERECO_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=true)
	private Aluno aluno;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_ENDERECO_FUNCIONARIO")
	@JoinColumn (name = "ID_FUNCIONARIO", nullable=true)
	private Funcionario funcionario;

	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "LOGRADOURO", nullable=false, length=100)
	private String logradouro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NUMERO", nullable=false, length=8)
	private String numero;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "COMPLEMENTO", length=50)
	private String complemento;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "BAIRRO", nullable=false, length=50)
	private String bairro;
	
	@Column (name = "CEP", nullable=false, length=10)
	private String cep;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "CIDADE", nullable=false, length=50)
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UF", nullable=false, length=2)
	private Uf uf;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro=logradouro;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro=bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep=cep;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public com.consisti.sisgesc.entidade.financeiro.Banco getBanco() {
		return banco;
	}

	public void setBanco(com.consisti.sisgesc.entidade.financeiro.Banco banco) {
		this.banco=banco;
	}

}
