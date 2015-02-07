package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CALENDARIO_ANUAL")
@SequenceGenerator(name="SE_CALENDARIO_ANUAL", sequenceName="SE_CALENDARIO_ANUAL")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CalendarioAnualEntity.queryMan", query="from CalendarioAnualEntity obj"),
	@NamedQuery(name="CalendarioAnualEntity.querySel", query="select new CalendarioAnualEntity(obj.id, obj.descricao, obj.totalDiasLetivos) from CalendarioAnualEntity obj order by obj.descricao asc"),
	@NamedQuery(name="CalendarioAnualEntity.querySelLookup", query="select new CalendarioAnualEntity (obj.id, obj.descricao) from CalendarioAnualEntity obj where obj.id = ? order by obj.id asc")
})
public class CalendarioAnualEntity extends CalendarioAnual {
 	
    /*
     * Construtor padrão
     */
    public CalendarioAnualEntity() {
    }
	public CalendarioAnualEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

	public CalendarioAnualEntity(Long id, String descricao, Integer totalDiasLetivos) {
		setId(id);
		setDescricao(descricao);
		setTotalDiasLetivos(totalDiasLetivos);
	}
}
