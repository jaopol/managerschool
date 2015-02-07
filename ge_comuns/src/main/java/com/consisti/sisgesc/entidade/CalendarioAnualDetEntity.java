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
@Table(name="CALENDARIO_ANUAL_DET")
@SequenceGenerator(name="SE_CALENDARIO_ANUAL_DET", sequenceName="SE_CALENDARIO_ANUAL_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CalendarioAnualDetEntity.querySelLookup", query="select new CalendarioAnualDetEntity (obj.id, obj.descricao) from CalendarioAnualDetEntity obj where obj.id = ? order by obj.id asc")
})
public class CalendarioAnualDetEntity extends CalendarioAnualDet {

	private transient String estadoSubDetalhePlc="E"; // E-expandido, R-retraido

 	
    /*
     * Construtor padrão
     */
    public CalendarioAnualDetEntity() {
    }
	public CalendarioAnualDetEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

	public String getEstadoSubDetalhePlc() {
		return estadoSubDetalhePlc;
	}

	public void setEstadoSubDetalhePlc(String estadoSubDetalhePlc) {
		this.estadoSubDetalhePlc=estadoSubDetalhePlc;
	}

}
