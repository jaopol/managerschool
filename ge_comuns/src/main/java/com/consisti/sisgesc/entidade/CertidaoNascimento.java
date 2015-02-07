package com.consisti.sisgesc.entidade;

import java.util.Date;

import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.ForeignKey;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CertidaoNascimento extends AppBaseEntity {

	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_ALUNO_CERTIDAONASCIMENTO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CERTIDAO_NASCIMENTO")
	@Column (name = "ID_CERTIDAO_NASCIMENTO", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "CERTIDAO_NASCIMENTO_IDENTIDADE", nullable=true, length=40)
	private String certidaoNascimentoIdentidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NUMERO_TERMO", nullable=true, length=40)
	private String numeroTermo;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "FOLHA", nullable=true, length=10)
	private String folha;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "LIVRO", nullable=true, length=10)
	private String livro;
	
	@Column (name = "DATA_EMISSAO", nullable=true, length=10)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UF_CARTORIO", nullable=true, length=2)
	private Uf ufCartorio;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_CARTORIO", nullable=true, length=50)
	private String nomeCartorio;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "DOCUMENTO_IDENTIDADE", nullable=true, length=1)
	private PlcSimNao documentoIdentidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NUMERO_IDENTIDADE", nullable=true, length=13)
	private String numeroIdentidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "COMPLEMENTO_IDENTIDADE", nullable=true, length=50)
	private String complementoIdentidade;
	
	@Column (name = "DATA_EXPEDICAO", nullable=true, length=10)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExpedicao;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UF_IDENTIDADE", nullable=true, length=2)
	private Uf ufIdentidade;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "ORGAO_EMISSOR", nullable=true, length=20)
	private String orgaoEmissor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getCertidaoNascimentoIdentidade() {
		return certidaoNascimentoIdentidade;
	}

	public void setCertidaoNascimentoIdentidade(String certidaoNascimentoIdentidade) {
		this.certidaoNascimentoIdentidade=certidaoNascimentoIdentidade;
	}

	public String getNumeroTermo() {
		return numeroTermo;
	}

	public void setNumeroTermo(String numeroTermo) {
		this.numeroTermo=numeroTermo;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha=folha;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro=livro;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao=dataEmissao;
	}

	public Uf getUfCartorio() {
		return ufCartorio;
	}

	public void setUfCartorio(Uf ufCartorio) {
		this.ufCartorio=ufCartorio;
	}

	public String getNomeCartorio() {
		return nomeCartorio;
	}

	public void setNomeCartorio(String nomeCartorio) {
		this.nomeCartorio=nomeCartorio;
	}

	public PlcSimNao getDocumentoIdentidade() {
		return documentoIdentidade;
	}

	public void setDocumentoIdentidade(PlcSimNao documentoIdentidade) {
		this.documentoIdentidade=documentoIdentidade;
	}

	public String getNumeroIdentidade() {
		return numeroIdentidade;
	}

	public void setNumeroIdentidade(String numeroIdentidade) {
		this.numeroIdentidade=numeroIdentidade;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao=dataExpedicao;
	}

	public Uf getUfIdentidade() {
		return ufIdentidade;
	}

	public void setUfIdentidade(Uf ufIdentidade) {
		this.ufIdentidade=ufIdentidade;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor=orgaoEmissor;
	}

	public String getComplementoIdentidade() {
		return complementoIdentidade;
	}

	public void setComplementoIdentidade(String complementoIdentidade) {
		this.complementoIdentidade = complementoIdentidade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
