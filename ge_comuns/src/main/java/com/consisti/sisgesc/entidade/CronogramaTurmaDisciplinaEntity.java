package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CRONOGRAMA_TURMA_DISCIPLINA")
@SequenceGenerator(name="SE_CRONOGRAMA_TURMA_DISCIPLINA", sequenceName="SE_CRONOGRAMA_TURMA_DISCIPLINA")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="disciplina")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CronogramaTurmaDisciplinaEntity.querySelLookup", query="select new CronogramaTurmaDisciplinaEntity (obj.id, obj.disciplina, obj.cronogramaTurma) from CronogramaTurmaDisciplinaEntity obj where obj.id = ? order by obj.id asc")
})
public class CronogramaTurmaDisciplinaEntity extends CronogramaTurmaDisciplina {

	private transient String estadoSubDetalhePlc="E"; // E-expandido, R-retraido

 	
    /*
     * Construtor padrão
     */
    public CronogramaTurmaDisciplinaEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getDisciplinaAux() {
    	return getDisciplina()!=null? getDisciplina().getIdAux() : null;
    }
    
    public void setDisciplinaAux(String disciplinaAux) {
    	if (disciplinaAux != null && disciplinaAux.trim().length() > 0) {
    		Long id = Long.valueOf(disciplinaAux);
    		if (getDisciplina()==null || !id.equals(getDisciplina().getId())) {
    			com.consisti.sisgesc.entidade.Disciplinas obj = new com.consisti.sisgesc.entidade.DisciplinasEntity();
    			obj.setId(id);
    			this.setDisciplina(obj);
    		}
    	} else {
    		this.setDisciplina(null);
    	}
    }

    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getCronogramaTurmaAux() {
    	return getCronogramaTurma()!=null? getCronogramaTurma().getIdAux() : null;
    }
    
    public void setCronogramaTurmaAux(String cronogramaTurmaAux) {
    	if (cronogramaTurmaAux != null && cronogramaTurmaAux.trim().length() > 0) {
    		Long id = Long.valueOf(cronogramaTurmaAux);
    		if (getCronogramaTurma()==null || !id.equals(getCronogramaTurma().getId())) {
    			com.consisti.sisgesc.entidade.CronogramaTurma obj = new com.consisti.sisgesc.entidade.CronogramaTurmaEntity();
    			obj.setId(id);
    			this.setCronogramaTurma(obj);
    		}
    	} else {
    		this.setCronogramaTurma(null);
    	}
    }

	public CronogramaTurmaDisciplinaEntity(Long id, Disciplinas disciplina, com.consisti.sisgesc.entidade.CronogramaTurma cronogramaTurma) {
		this.setId(id);
		this.setDisciplina(disciplina);
		this.setCronogramaTurma(cronogramaTurma);
	}
	@Override
	public String toString() {
		return getDisciplinaAux()+"-"+getCronogramaTurmaAux();
	}

	public String getEstadoSubDetalhePlc() {
		return estadoSubDetalhePlc;
	}

	public void setEstadoSubDetalhePlc(String estadoSubDetalhePlc) {
		this.estadoSubDetalhePlc=estadoSubDetalhePlc;
	}

}
