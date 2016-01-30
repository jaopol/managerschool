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
@Table(name="EVENTO")
@SequenceGenerator(name="SE_EVENTO", sequenceName="SE_EVENTO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="EventoEntity.queryMan", query="from EventoEntity obj"),
	@NamedQuery(name="EventoEntity.querySel", query="select new EventoEntity(obj.id, obj.descricao, obj.dataEvento, obj.valor, obj.numeroParcela) from EventoEntity obj order by obj.descricao asc"),
	@NamedQuery(name="EventoEntity.querySelLookup", query="select new EventoEntity (obj.id, obj.descricao) from EventoEntity obj where obj.id = ? order by obj.descricao asc")
})
public class EventoEntity extends Evento {
 	
    /*
     * Construtor padrão
     */
    public EventoEntity() {
    }
	public EventoEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

	public EventoEntity(Long id, String descricao, java.util.Date dataEvento, java.math.BigDecimal valor, Integer numeroParcela) {
		setId(id);
		setDescricao(descricao);
		setDataEvento(dataEvento);
		setValor(valor);
		setNumeroParcela(numeroParcela);
	}
}
