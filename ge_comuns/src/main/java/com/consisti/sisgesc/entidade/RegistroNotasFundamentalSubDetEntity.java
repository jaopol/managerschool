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
@Table(name="REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET")
@SequenceGenerator(name="SE_REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET", sequenceName="SE_REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="avaliacao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="RegistroNotasFundamentalSubDetEntity.querySelLookup", query="select new RegistroNotasFundamentalSubDetEntity (obj.id, obj.aluno) from RegistroNotasFundamentalSubDetEntity obj where obj.id = ? order by obj.id asc")
})
public class RegistroNotasFundamentalSubDetEntity extends RegistroNotasFundamentalSubDet {
	
	private transient String nomeAluno;
 	
    /*
     * Construtor padrão
     */
    public RegistroNotasFundamentalSubDetEntity() {
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

	public RegistroNotasFundamentalSubDetEntity(Long id, Aluno aluno) {
		this.setId(id);
		this.setAluno(aluno);
	}
	@Override
	public String toString() {
		return getAlunoAux();
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

}
