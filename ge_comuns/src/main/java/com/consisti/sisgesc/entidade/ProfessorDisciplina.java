package com.consisti.sisgesc.entidade;

import java.util.Date;

import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ProfessorDisciplina extends AppBaseEntity {

	
	@OneToMany (targetEntity = TurmaProfessorEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="professorDisciplina")
	@ForeignKey(name="FK_TURMAPROFESSOR_PROFESSORDISCIPLINA")
	@Valid
	@JoinColumn (name = "ID_PROFESSOR_DISCIPLINA")
	private List<TurmaProfessor> turmaProfessor;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_PROFESSOR_DISCIPLINA")
	@Column (name = "ID_PROFESSOR_DISCIPLINA", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_PROFESSORDISCIPLINA_PROFESSOR")
	@JoinColumn (name = "ID_PROFESSOR", nullable=false)
	private Funcionario professor;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", nullable=true, length=200)
	private String observacao;
	
	@ManyToOne (targetEntity = DisciplinasEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_DISCIPLINAPROFESSOR_DISCIPLINA")
	@JoinColumn (name = "ID_DISCIPLINA", nullable=false)
	private Disciplinas disciplina;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor=professor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina = disciplina;
	}

	public List<TurmaProfessor> getTurmaProfessor() {
		return turmaProfessor;
	}

	public void setTurmaProfessor(List<TurmaProfessor> turmaProfessor) {
		this.turmaProfessor=turmaProfessor;
	}

}
