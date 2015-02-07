package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="SAUDE_ALUNO")
@SequenceGenerator(name="SE_SAUDE_ALUNO", sequenceName="SE_SAUDE_ALUNO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="qualProblGraveSaude")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="SaudeAlunoEntity.querySelLookup", query="select new SaudeAlunoEntity (obj.id, obj.qualProblGraveSaude) from SaudeAlunoEntity obj where obj.id = ? order by obj.id asc")
})
public class SaudeAlunoEntity extends SaudeAluno {
 	
	private transient PlcSimNao fazTratamentoSaudeDesprezar;
	
    /*
     * Construtor padrão
     */
    public SaudeAlunoEntity() {
    }
	public SaudeAlunoEntity(Long id, String qualProblGraveSaude) {
		this.setId(id);
		this.setQualProblGraveSaude(qualProblGraveSaude);
	}
	@Override
	public String toString() {
		return getQualProblGraveSaude();
	}
	
	public PlcSimNao getFazTratamentoSaudeDesprezar() {
		return getFazTratamentoSaude();
	}
	public void setFazTratamentoSaudeDesprezar(PlcSimNao fazTratamentoSaude) {
		this.fazTratamentoSaudeDesprezar = getFazTratamentoSaude();
	}

}
