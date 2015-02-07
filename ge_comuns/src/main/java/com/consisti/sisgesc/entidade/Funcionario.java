package com.consisti.sisgesc.entidade;

import java.util.Date;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.EstadoCivil;
import com.consisti.sisgesc.dominio.TipoResidencia;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.TemporalType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.validator.Valid;
import org.hibernate.annotations.ForeignKey;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Funcionario extends AppBaseEntity {

	
	@OneToMany (targetEntity = HorarioTrabalhoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@ForeignKey(name="FK_HORARIOTRABALHO_FUNCIONARIO")
	@Valid
	@JoinColumn (name = "ID_FUNCIONARIO")
	private List<HorarioTrabalho> horarioTrabalho;
	
	@OneToMany (targetEntity = DadoFuncionarioEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@ForeignKey(name="FK_DADOFUNCIONARIO_FUNCIONARIO")
	@Valid
	@JoinColumn (name = "ID_FUNCIONARIO")
	private List<DadoFuncionario> dadoFuncionario;
	
	@OneToMany (targetEntity = EnderecoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@ForeignKey(name="FK_ENDERECO_FUNCIONARIO")
	@Valid
	@JoinColumn (name = "ID_FUNCIONARIO")
	private List<Endereco> endereco;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FUNCIONARIO")
	@Column (name = "ID_FUNCIONARIO", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME", nullable=false, length=50)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PROFESSOR", length=1)
	private PlcSimNao professor;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "MONITOR", length=1)
	private PlcSimNao monitor;
	
	@Column (name = "DATA_NASCIMENTO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", length=1)
	private AtivoInativo status;
	
	@Column (name = "TELEFONE", nullable=false, length=13)
	private String telefone;
	
	@Column (name = "CELULAR", length=13)
	private String celular;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO_RESIDENCIA", nullable=false, length=2)
	private TipoResidencia tipoResidencia;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_MAE", length=50)
	private String nomeMae;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "NOME_PAI", length=50)
	private String nomePai;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "EMAIL", length=250)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ESTADO_CIVIL", nullable=false, length=1)
	private EstadoCivil estadoCivil;
	
	@OneToMany (targetEntity = AnexoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@ForeignKey(name="FK_ANEXO_FUNCIONARIO")
	@Valid
	@JoinColumn (name = "ID_FUNCIONARIO")
	private List<Anexo> anexoFuncionario;

	@Lob
	@Column (name = "FOTO_FUNCIONARIO")
	private byte [] fotoFuncionario;
	
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

	public PlcSimNao getProfessor() {
		return professor;
	}

	public void setProfessor(PlcSimNao professor) {
		this.professor=professor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento=dataNascimento;
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

	public TipoResidencia getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(TipoResidencia tipoResidencia) {
		this.tipoResidencia=tipoResidencia;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae=nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai=nomePai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public List<DadoFuncionario> getDadoFuncionario() {
		return dadoFuncionario;
	}

	public void setDadoFuncionario(List<DadoFuncionario> dadoFuncionario) {
		this.dadoFuncionario=dadoFuncionario;
	}

	public List<HorarioTrabalho> getHorarioTrabalho() {
		return horarioTrabalho;
	}

	public void setHorarioTrabalho(List<HorarioTrabalho> horarioTrabalho) {
		this.horarioTrabalho=horarioTrabalho;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public PlcSimNao getMonitor() {
		return monitor;
	}

	public void setMonitor(PlcSimNao monitor) {
		this.monitor = monitor;
	}

	public List<Anexo> getAnexoFuncionario() {
		return anexoFuncionario;
	}

	public void setAnexoFuncionario(List<Anexo> anexoFuncionario) {
		this.anexoFuncionario = anexoFuncionario;
	}

	public byte[] getFotoFuncionario() {
		return fotoFuncionario;
	}

	public void setFotoFuncionario(byte[] fotoFuncionario) {
		this.fotoFuncionario = fotoFuncionario;
	}

}
