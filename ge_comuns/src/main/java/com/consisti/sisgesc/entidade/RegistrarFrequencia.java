package com.consisti.sisgesc.entidade;

import java.util.Date;

import com.consisti.sisgesc.dominio.AbertoFechado;
import com.consisti.sisgesc.dominio.PeriodoAluno;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;


@MappedSuperclass
@SuppressWarnings("serial")
public class RegistrarFrequencia extends AppBaseEntity {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_REGISTRAR_FREQUENCIA")
	@Column (name = "ID_REGISTRAR_FREQUENCIA", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRARFREQUENCIA_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRARFREQUENCIA_PROFESSOR")
	@JoinColumn (name = "ID_PROFESSOR", nullable=false)
	private Funcionario professor;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PERIODO", nullable=false, length=1)
	private PeriodoAluno periodo;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", nullable=false, length=1)
	private AbertoFechado status;
	
	@Column (name = "AULAS_PREVISTAS", nullable=false, length=5)
	private Integer aulasPrevistas;
	
	@Column (name = "AULAS_DADAS", length=5)
	private Integer aulasDadas;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO_EDUCACAO", nullable=false, length=1)
	private TipoEducacao tipoEducacao;
	
	@Column (name = "DIAS_LETIVOS", nullable=false, length=5)
	private Integer diasLetivos;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;

	@OneToMany (targetEntity = FrequenciaAlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="registrarFrequencia")
	@ForeignKey(name="FK_FREQUENCIAALUNO_REGISTRARFREQUENCIA")
	@Valid
	@JoinColumn (name = "ID_REGISTRAR_FREQUENCIA")
	private List<FrequenciaAluno> frequenciaAluno;

	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Funcionario getProfessor() {
		return professor;
	}
	public void setProfessor(Funcionario professor) {
		this.professor = professor;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public PeriodoAluno getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoAluno periodo) {
		this.periodo = periodo;
	}
	public AbertoFechado getStatus() {
		return status;
	}
	public void setStatus(AbertoFechado status) {
		this.status = status;
	}
	public Integer getAulasPrevistas() {
		return aulasPrevistas;
	}
	public void setAulasPrevistas(Integer aulasPrevistas) {
		this.aulasPrevistas = aulasPrevistas;
	}
	public Integer getAulasDadas() {
		return aulasDadas;
	}
	public void setAulasDadas(Integer aulasDadas) {
		this.aulasDadas = aulasDadas;
	}
	public TipoEducacao getTipoEducacao() {
		return tipoEducacao;
	}
	public void setTipoEducacao(TipoEducacao tipoEducacao) {
		this.tipoEducacao = tipoEducacao;
	}
	public Integer getDiasLetivos() {
		return diasLetivos;
	}
	public void setDiasLetivos(Integer diasLetivos) {
		this.diasLetivos = diasLetivos;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public List<FrequenciaAluno> getFrequenciaAluno() {
		return frequenciaAluno;
	}

	public void setFrequenciaAluno(List<FrequenciaAluno> frequenciaAluno) {
		this.frequenciaAluno=frequenciaAluno;
	}

}
