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
@Table(name="FILIACAO_MAE")
@SequenceGenerator(name="SE_FILIACAO_MAE", sequenceName="SE_FILIACAO_MAE")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="nomeMae")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FiliacaoMaeEntity.querySelLookup", query="select new FiliacaoMaeEntity (obj.id, obj.nomeMae) from FiliacaoMaeEntity obj where obj.id = ? order by obj.id asc")
})
public class FiliacaoMaeEntity extends FiliacaoMae {
 	
    /*
     * Construtor padrão
     */
    public FiliacaoMaeEntity() {
    }
	public FiliacaoMaeEntity(Long id, String nomeMae) {
		this.setId(id);
		this.setNomeMae(nomeMae);
	}
	@Override
	public String toString() {
		return getNomeMae();
	}

}
