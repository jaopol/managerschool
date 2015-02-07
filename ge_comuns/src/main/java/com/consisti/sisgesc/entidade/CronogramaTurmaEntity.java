package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.entidade.TurmaEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CRONOGRAMA_TURMA")
@SequenceGenerator(name="SE_CRONOGRAMA_TURMA", sequenceName="SE_CRONOGRAMA_TURMA")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CronogramaTurmaEntity.queryMan", query="from CronogramaTurmaEntity obj"),
	@NamedQuery(name="CronogramaTurmaEntity.querySel", query="select new CronogramaTurmaEntity(obj.id, obj.turma.id , obj.turma.descricao, obj.dataCadastro) from CronogramaTurmaEntity obj left outer join obj.turma order by obj.id asc"),
	@NamedQuery(name="CronogramaTurmaEntity.querySel2", query="select distinct new CronogramaTurmaEntity(" +
																"obj.id, obj.turma.id , obj.turma.descricao, obj.dataCadastro) " +
															"from CronogramaTurmaEntity obj " +
															"left outer join obj.turma " +
															//"left outer join obj2.disciplina "+
															"order by obj.id asc"),
	@NamedQuery(name="CronogramaTurmaEntity.querySelLookup", query="select new CronogramaTurmaEntity (obj.id, obj.turma) from CronogramaTurmaEntity obj where obj.id = ? order by obj.id asc")
})
public class CronogramaTurmaEntity extends CronogramaTurma {
 	
    /*
     * Construtor padrão
     */
    public CronogramaTurmaEntity() {
    }
    
    public CronogramaTurmaEntity(Long id, Long idTurma, String descricaoTurma) {
    	
    	this.setId(id);
    	this.setTurma(new TurmaEntity());
    	this.getTurma().setId(idTurma);
    	this.getTurma().setDescricao(descricaoTurma);
    	
    }
    
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getTurmaStr() {
    	return getTurma()!=null? getTurma().getIdAux() : null;
    }
    
    public void setTurmaStr(String turmaStr) {
    	if (turmaStr != null && turmaStr.trim().length() > 0) {
    		Long id = Long.valueOf(turmaStr);
    		if (getTurma()==null || !id.equals(getTurma().getId())) {
    			com.consisti.sisgesc.entidade.Turma obj = new com.consisti.sisgesc.entidade.TurmaEntity();
    			obj.setId(id);
    			this.setTurma(obj);
    		}
    	} else {
    		this.setTurma(null);
    	}
    }

	public CronogramaTurmaEntity(Long id, Turma turma) {
		this.setId(id);
		this.setTurma(turma);
	}
	@Override
	public String toString() {
		return getTurmaStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Turma.class};
	}

	public CronogramaTurmaEntity(Long id, Long turmaId, String turmaDescricao, java.util.Date dataCadastro) {
		setId(id);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		setDataCadastro(dataCadastro);
	}
	public CronogramaTurmaEntity(Long id) {
		setId(id);
	}
}
