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
@Table(name="REGISTRO_NOTAS_FUNDAMENTAL_DET")
@SequenceGenerator(name="SE_REGISTRO_NOTAS_FUNDAMENTAL_DET", sequenceName="SE_REGISTRO_NOTAS_FUNDAMENTAL_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="turma")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="RegistroNotasFundamentalDetEntity.querySelLookup", query="select new RegistroNotasFundamentalDetEntity (obj.id, obj.turma) from RegistroNotasFundamentalDetEntity obj where obj.id = ? order by obj.id asc")
})
public class RegistroNotasFundamentalDetEntity extends RegistroNotasFundamentalDet {

	private transient String estadoSubDetalhePlc="E"; // E-expandido, R-retraido
	
	private transient String nomeTurma;

 	
    /*
     * Construtor padrão
     */
    public RegistroNotasFundamentalDetEntity() {
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

	public RegistroNotasFundamentalDetEntity(Long id, Turma turma) {
		this.setId(id);
		this.setTurma(turma);
	}
	@Override
	public String toString() {
		return getTurmaAux();
	}

	public String getEstadoSubDetalhePlc() {
		return estadoSubDetalhePlc;
	}

	public void setEstadoSubDetalhePlc(String estadoSubDetalhePlc) {
		this.estadoSubDetalhePlc=estadoSubDetalhePlc;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

}
