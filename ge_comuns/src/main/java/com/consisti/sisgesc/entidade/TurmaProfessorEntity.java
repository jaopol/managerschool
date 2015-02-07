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
@Table(name="TURMA_PROFESSOR")
@SequenceGenerator(name="SE_TURMA_PROFESSOR", sequenceName="SE_TURMA_PROFESSOR")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="turma")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="TurmaProfessorEntity.querySelLookup", query="select new TurmaProfessorEntity (obj.id, obj.turma) from TurmaProfessorEntity obj where obj.id = ? order by obj.id asc")
})
public class TurmaProfessorEntity extends TurmaProfessor {
 	
    /*
     * Construtor padrão
     */
    public TurmaProfessorEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getTurmaAux() {
    	return getTurma()!=null? getTurma().getIdAux() : null;
    }
    
    public void setTurmaAux(String turmaAux) {
    	if (turmaAux != null && turmaAux.trim().length() > 0) {
    		Long id = Long.valueOf(turmaAux);
    		if (getTurma()==null || !id.equals(getTurma().getId())) {
    			com.consisti.sisgesc.entidade.Turma obj = new com.consisti.sisgesc.entidade.TurmaEntity();
    			obj.setId(id);
    			this.setTurma(obj);
    		}
    	} else {
    		this.setTurma(null);
    	}
    }

	public TurmaProfessorEntity(Long id, Turma turma) {
		this.setId(id);
		this.setTurma(turma);
	}
	@Override
	public String toString() {
		return getTurmaAux();
	}

}
