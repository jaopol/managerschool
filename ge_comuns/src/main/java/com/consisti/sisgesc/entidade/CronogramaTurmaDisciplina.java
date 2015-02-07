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
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CronogramaTurmaDisciplina extends AppBaseEntity {

	@OneToMany (targetEntity = CronogramaTurmaDisciplinaConteudoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="cronogramaTurmaDisciplina")
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINACONTEUDO_CRONOGRAMATURMADISCIPLINA")
	@Valid
	@JoinColumn (name = "ID_CRONOGRAMA_TURMA_DISCIPLINA")
	private List<CronogramaTurmaDisciplinaConteudo> cronogramaTurmaDisciplinaConteudo;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CRONOGRAMA_TURMA_DISCIPLINA")
	@Column (name = "ID_CRONOGRAMA_TURMA_DISCIPLINA", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = CronogramaTurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINA_CRONOGRAMATURMA")
	@JoinColumn (name = "ID_CRONOGRAMA_TURMA", nullable=false)
	private CronogramaTurma cronogramaTurma;

	@ManyToOne (targetEntity = DisciplinasEntity.class, fetch = FetchType.EAGER)
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINA_DISCIPLINA")
	@JoinColumn (name = "ID_DISCIPLINA", nullable=false)
	private Disciplinas disciplina;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina=disciplina;
	}

	public CronogramaTurma getCronogramaTurma() {
		return cronogramaTurma;
	}

	public void setCronogramaTurma(CronogramaTurma cronogramaTurma) {
		this.cronogramaTurma=cronogramaTurma;
	}

	public List<CronogramaTurmaDisciplinaConteudo> getCronogramaTurmaDisciplinaConteudo() {
		return cronogramaTurmaDisciplinaConteudo;
	}

	public void setCronogramaTurmaDisciplinaConteudo(List<CronogramaTurmaDisciplinaConteudo> cronogramaTurmaDisciplinaConteudo) {
		this.cronogramaTurmaDisciplinaConteudo=cronogramaTurmaDisciplinaConteudo;
	}

}
