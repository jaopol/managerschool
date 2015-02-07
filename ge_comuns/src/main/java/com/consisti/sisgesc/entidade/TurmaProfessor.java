package com.consisti.sisgesc.entidade;


import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class TurmaProfessor extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_TURMA_PROFESSOR")
	@Column (name = "ID_TURMA_PROFESSOR", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = ProfessorDisciplinaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_TURMAPROFESSOR_PROFESSORDISCIPLINA")
	@JoinColumn (name = "ID_PROFESSOR_DISCIPLINA", nullable=false)
	private ProfessorDisciplina professorDisciplina;

	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_TURMAPROFESSOR_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public ProfessorDisciplina getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(ProfessorDisciplina professorDisciplina) {
		this.professorDisciplina=professorDisciplina;
	}

}
