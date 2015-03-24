package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.PeriodoAluno;
import com.consisti.sisgesc.dominio.Sexo;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.dominio.TipoMatricula;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Aluno extends AppBaseEntity {

	@OneToMany (targetEntity = ResponsavelFinanceiroAlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_RESPONSAVELFINANCEIROALUNO_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<ResponsavelFinanceiroAluno> responsavelFinanceiroAluno;

	@OneToMany (targetEntity = ResponsavelAlunoCasoAcidenteEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_RESPONSAVELALUNOCASOACIDENTE_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<ResponsavelAlunoCasoAcidente> responsavelAlunoCasoAcidente;

	@OneToMany (targetEntity = CertidaoNascimentoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_ALUNO_CERTIDAONASCIMENTO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<CertidaoNascimento> certidaoNascimento;
	
	@OneToMany (targetEntity = SaudeAlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_SAUDEALUNO_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<SaudeAluno> saudeAluno;

	@OneToMany (targetEntity = FiliacaoPaiEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_FILIACAOPAI_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<FiliacaoPai> filiacaoPai;

	@OneToMany (targetEntity = FiliacaoMaeEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_FILIACAOMAE_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<FiliacaoMae> filiacaoMae;
	
	@OneToMany (targetEntity = EnderecoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_ENDERECO_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<Endereco> endereco;

	@OneToMany (targetEntity = ServicoAlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_SERVICOS_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<ServicoAluno> servicoAluno;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_ALUNO")
	@Column (name = "ID_ALUNO", nullable=false, length=5)
	private Long id;
	
	@Column (name = "MATRICULA", nullable=false, length=12)
	private String matricula;
	
	@ManyToOne (targetEntity = TurmaEntity.class)
	@ForeignKey(name="FK_ALUNO_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=17)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_ALUNO", nullable=false, length=50)
	private String nomeAluno;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "SEXO", nullable=false, length=1)
	private Sexo sexo;
	
	@Column (name = "DATA_NASCIMENTO", nullable=false, length=17)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column (name = "TELEFONE", length=14)
	private String telefone;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "RESPONSAVEL_ALUNO", nullable=false, length=50)
	private String responsavelAluno;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NECESSIDADE_EDU_ESPECIAL", length=100)
	private String necessidadeEduEspecial;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "ULTIMA_ESCOLA", length=50)
	private String ultimaEscola;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO_EDUCACAO", nullable=false, length=1)
	private TipoEducacao tipoEducacao;
	
	@Column (name = "VALOR_MENSALIDADE_ALUNO", nullable=false, length=10, precision=10, scale=2 )
	private BigDecimal valorMensalidadeAluno;
	
	@Column (name = "DESCONTO_MENSALIDADE", length=10, precision=10, scale=2 )
	private BigDecimal descontoMensalidade;
	
	@Column (name = "VALOR_TOTAL_MENSALIDADE", nullable=false, length=10, precision=10, scale=2 )
	private BigDecimal valorTotalMensalidade;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PERIODO_ALUNO", nullable=false, length=1)
	private PeriodoAluno periodoAluno;
	
	@OneToMany (targetEntity = AnexoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_ANEXO_ALUNO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<Anexo> anexoAluno;
	
	@Column (name="TIPO_MATRICULA")
	@Enumerated (EnumType.STRING)
	private TipoMatricula tipoMatricula;
	
	@Column (name="VALOR_MATRICULA", precision=10, scale=2)
	private BigDecimal valorMatricula;
	
	@Column (name="DESCONTO_MATRICULA", precision=10, scale=2)
	private BigDecimal descontoMatricula;
	
	@Lob
	@Column (name = "foto_aluno")
	private byte [] fotoAluno;
	
	@OneToMany (targetEntity = ContratoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="aluno")
	@ForeignKey(name="FK_CONTRATO")
	@Valid
	@JoinColumn (name = "ID_ALUNO")
	private List<Contrato> contrato;
	
	@Column (name="HORA_ENTRADA", length=5)
	private String horaEntrada;
	
	@Column (name="HORA_SAIDA", length=5)
	private String horaSaida;
	
	@Column (name="CARGA_HORARIA", length=5)
	private String cargaHoraria;
	
	@PlcValFormatoSimples(formato=FormatoSimples.NUMERICO)
	@Column (name="ANO_LETIVO", length=4)
	private String anoLetivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula=matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno=nomeAluno;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo=sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento=dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone=telefone;
	}

	public List<CertidaoNascimento> getCertidaoNascimento() {
		return certidaoNascimento;
	}

	public void setCertidaoNascimento(List<CertidaoNascimento> certidaoNascimento) {
		this.certidaoNascimento=certidaoNascimento;
	}

	public String getResponsavelAluno() {
		return responsavelAluno;
	}

	public void setResponsavelAluno(String responsavelAluno) {
		this.responsavelAluno=responsavelAluno;
	}

	public String getNecessidadeEduEspecial() {
		return necessidadeEduEspecial;
	}

	public void setNecessidadeEduEspecial(String necessidadeEduEspecial) {
		this.necessidadeEduEspecial=necessidadeEduEspecial;
	}

	public String getUltimaEscola() {
		return ultimaEscola;
	}

	public void setUltimaEscola(String ultimaEscola) {
		this.ultimaEscola=ultimaEscola;
	}

	public TipoEducacao getTipoEducacao() {
		return tipoEducacao;
	}

	public void setTipoEducacao(TipoEducacao tipoEducacao) {
		this.tipoEducacao=tipoEducacao;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco=endereco;
	}

	public List<FiliacaoMae> getFiliacaoMae() {
		return filiacaoMae;
	}

	public void setFiliacaoMae(List<FiliacaoMae> filiacaoMae) {
		this.filiacaoMae=filiacaoMae;
	}

	public List<FiliacaoPai> getFiliacaoPai() {
		return filiacaoPai;
	}

	public void setFiliacaoPai(List<FiliacaoPai> filiacaoPai) {
		this.filiacaoPai=filiacaoPai;
	}

	public List<SaudeAluno> getSaudeAluno() {
		return saudeAluno;
	}

	public void setSaudeAluno(List<SaudeAluno> saudeAluno) {
		this.saudeAluno=saudeAluno;
	}

	public List<ResponsavelAlunoCasoAcidente> getResponsavelAlunoCasoAcidente() {
		return responsavelAlunoCasoAcidente;
	}

	public void setResponsavelAlunoCasoAcidente(List<ResponsavelAlunoCasoAcidente> responsavelAlunoCasoAcidente) {
		this.responsavelAlunoCasoAcidente=responsavelAlunoCasoAcidente;
	}

	public BigDecimal getValorMensalidadeAluno() {
		return valorMensalidadeAluno;
	}

	public void setValorMensalidadeAluno(BigDecimal valorMensalidadeAluno) {
		this.valorMensalidadeAluno = valorMensalidadeAluno;
	}

	public BigDecimal getDescontoMensalidade() {
		return descontoMensalidade;
	}

	public void setDescontoMensalidade(BigDecimal descontoMensalidade) {
		this.descontoMensalidade = descontoMensalidade;
	}

	public BigDecimal getValorTotalMensalidade() {
		return valorTotalMensalidade;
	}

	public void setValorTotalMensalidade(BigDecimal valorTotalMensalidade) {
		this.valorTotalMensalidade = valorTotalMensalidade;
	}

	public PeriodoAluno getPeriodoAluno() {
		return periodoAluno;
	}

	public void setPeriodoAluno(PeriodoAluno periodoAluno) {
		this.periodoAluno = periodoAluno;
	}

	public List<Anexo> getAnexoAluno() {
		return anexoAluno;
	}

	public void setAnexoAluno(List<Anexo> anexoAluno) {
		this.anexoAluno = anexoAluno;
	}

	public byte[] getFotoAluno() {
		return fotoAluno;
	}

	public void setFotoAluno(byte[] fotoAluno) {
		this.fotoAluno = fotoAluno;
	}

	public TipoMatricula getTipoMatricula() {
		return tipoMatricula;
	}

	public void setTipoMatricula(TipoMatricula tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}

	public BigDecimal getValorMatricula() {
		return valorMatricula;
	}

	public void setValorMatricula(BigDecimal valorMatricula) {
		this.valorMatricula = valorMatricula;
	}

	public BigDecimal getDescontoMatricula() {
		return descontoMatricula;
	}

	public void setDescontoMatricula(BigDecimal descontoMatricula) {
		this.descontoMatricula = descontoMatricula;
	}

	public List<Contrato> getContrato() {
		return contrato;
	}

	public void setContrato(List<Contrato> contrato) {
		this.contrato = contrato;
	}

	public List<ResponsavelFinanceiroAluno> getResponsavelFinanceiroAluno() {
		return responsavelFinanceiroAluno;
	}

	public void setResponsavelFinanceiroAluno(List<ResponsavelFinanceiroAluno> responsavelFinanceiroAluno) {
		this.responsavelFinanceiroAluno=responsavelFinanceiroAluno;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<ServicoAluno> getServicoAluno() {
		return servicoAluno;
	}

	public void setServicoAluno(List<ServicoAluno> servicoAluno) {
		this.servicoAluno = servicoAluno;
	}

	public String getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}


}
