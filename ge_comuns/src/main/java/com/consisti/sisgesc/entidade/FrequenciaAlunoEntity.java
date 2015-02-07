package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="FREQUENCIA_ALUNO")
@SequenceGenerator(name="SE_FREQUENCIA_ALUNO", sequenceName="SE_FREQUENCIA_ALUNO")
@AccessType("field")

@PlcValidacaoUnificada
@PlcTabular(propReferenciaDesprezar="aluno")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FrequenciaAlunoEntity.querySelLookup", query="select new FrequenciaAlunoEntity (obj.id, obj.aluno) from FrequenciaAlunoEntity obj where obj.id = ? order by obj.id asc")
})
public class FrequenciaAlunoEntity extends FrequenciaAluno {

	private transient String alunoAuxLookup;

    /*
     * Construtor padrão
     */
    public FrequenciaAlunoEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getAlunoAux() {
    	return getAluno()!=null? getAluno().getIdAux() : null;
    }
    
    public void setAlunoAux(String alunoAux) {
    	if (alunoAux != null && alunoAux.trim().length() > 0) {
    		Long id = Long.valueOf(alunoAux);
    		if (getAluno()==null || !id.equals(getAluno().getId())) {
    			com.consisti.sisgesc.entidade.Aluno obj = new com.consisti.sisgesc.entidade.AlunoEntity();
    			obj.setId(id);
    			this.setAluno(obj);
    		}
    	} else {
    		this.setAluno(null);
    	}
    }

	public FrequenciaAlunoEntity(Long id, Aluno aluno) {
		this.setId(id);
		this.setAluno(aluno);
	}
	@Override
	public String toString() {
		return getAlunoAux();
	}


	public void setAlunoAuxLookup(String alunoAuxLookup) {
		this.alunoAuxLookup=alunoAuxLookup;
	}

}
