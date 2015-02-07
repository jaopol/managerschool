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
@Table(name="ADITIVO")
@SequenceGenerator(name="SE_ADITIVO", sequenceName="SE_ADITIVO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="AditivoEntity.queryMan", query="from AditivoEntity obj"),
	@NamedQuery(name="AditivoEntity.querySelLookup", query="select new AditivoEntity (obj.id, obj.contrato) from AditivoEntity obj where obj.id = ? order by obj.id asc")
})
public class AditivoEntity extends Aditivo {
 	
    /*
     * Construtor padrão
     */
    public AditivoEntity() {
    }

	public AditivoEntity(Long id, ContratoEntity contrato) {
		this.setId(id);
		this.setContrato(contrato);
	}
	@Override
	public String toString() {
		return getIdAux();
	}

	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.ContratoEntity.class};
	}

}
