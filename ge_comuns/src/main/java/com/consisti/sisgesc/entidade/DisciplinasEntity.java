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
@Table(name="DISCIPLINAS")
@SequenceGenerator(name="SE_DISCIPLINAS", sequenceName="SE_DISCIPLINAS")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="DisciplinasEntity.queryMan", query="from DisciplinasEntity obj"),
	@NamedQuery(name="DisciplinasEntity.querySelLookup", query="select new DisciplinasEntity (obj.id, obj.descricao) from DisciplinasEntity obj where obj.id = ? order by obj.id asc")
})
public class DisciplinasEntity extends Disciplinas {
 	
    /*
     * Construtor padrão
     */
    public DisciplinasEntity() {
    }
	public DisciplinasEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

}
