package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcEntidade;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="TURMA")
@SequenceGenerator(name="SE_TURMA", sequenceName="SE_TURMA")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="TurmaEntity.queryMan", query="from TurmaEntity obj"),
	@NamedQuery(name="TurmaEntity.querySelLookup", query="select new TurmaEntity (obj.id, obj.descricao) from TurmaEntity obj where obj.id = ? order by obj.id asc")
})
public class TurmaEntity extends Turma {
 	
    /*
     * Construtor padrão
     */
    public TurmaEntity() {
    }
	public TurmaEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}
	
	public TurmaEntity(Long id, String idadeMaxima, String idadeMinima) {
		this.setId(id);
		this.setIdadeMaxima(idadeMaxima);
		this.setIdadeMinima(idadeMinima);
	}

}
