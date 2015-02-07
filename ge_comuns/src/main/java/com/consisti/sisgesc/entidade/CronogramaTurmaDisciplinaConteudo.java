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
public abstract class CronogramaTurmaDisciplinaConteudo extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CRONOGRAMA_TURMA_DISCIPLINA_CONTEUDO")
	@Column (name = "ID_CRONOGRAMA_TURMA_DISCIPLINA_CONTEUDO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = CronogramaTurmaDisciplinaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINACONTEUDO_CRONOGRAMATURMADISCIPLINA")
	@JoinColumn (name = "ID_CRONOGRAMA_TURMA_DISCIPLINA", nullable=false)
	private CronogramaTurmaDisciplina cronogramaTurmaDisciplina;

	@ManyToOne (targetEntity = ConteudoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINACONTEUDO_CONTEUDO")
	@JoinColumn (name = "ID_CONTEUDO", nullable=false)
	private Conteudo conteudo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo=conteudo;
	}

	public CronogramaTurmaDisciplina getCronogramaTurmaDisciplina() {
		return cronogramaTurmaDisciplina;
	}

	public void setCronogramaTurmaDisciplina(CronogramaTurmaDisciplina cronogramaTurmaDisciplina) {
		this.cronogramaTurmaDisciplina=cronogramaTurmaDisciplina;
	}

}
