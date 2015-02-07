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
@Table(name="HORARIO_TRABALHO")
@SequenceGenerator(name="SE_HORARIO_TRABALHO", sequenceName="SE_HORARIO_TRABALHO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="horaEntradaDesprezar")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="HorarioTrabalhoEntity.querySelLookup", query="select new HorarioTrabalhoEntity (obj.id, obj.horaEntrada) from HorarioTrabalhoEntity obj where obj.id = ? order by obj.id asc")
})
public class HorarioTrabalhoEntity extends HorarioTrabalho {
 	
	private transient String horaEntradaDesprezar;
	
    /*
     * Construtor padrão
     */
    public HorarioTrabalhoEntity() {
    }
	public HorarioTrabalhoEntity(Long id, String horaEntrada) {
		this.setId(id);
		this.setHoraEntrada(horaEntrada);
	}
	@Override
	public String toString() {
		return getHoraEntrada();
	}
	public String getHoraEntradaDesprezar() {
		return getHoraEntrada();
	}
	public void setHoraEntradaDesprezar(String horaEntradaDesprezar) {
		this.horaEntradaDesprezar = getHoraEntrada();
	}

}
