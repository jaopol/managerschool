package com.consisti.sisgesc.entidade;


import java.util.Date;

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
@Table(name="CALENDARIO_ANUAL_SUB_DET")
@SequenceGenerator(name="SE_CALENDARIO_ANUAL_SUB_DET", sequenceName="SE_CALENDARIO_ANUAL_SUB_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="feriados")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CalendarioAnualSubDetEntity.querySelLookup", query="select new CalendarioAnualSubDetEntity (obj.id, obj.dataFeriado) from CalendarioAnualSubDetEntity obj where obj.id = ? order by obj.id asc")
})
public class CalendarioAnualSubDetEntity extends CalendarioAnualSubDet {
 	
    /*
     * Construtor padrão
     */
    public CalendarioAnualSubDetEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    /*public String getFeriadosAux() {
    	return getFeriados()!=null? getFeriados().getIdAux() : null;
    }*/
    
    /*public void setFeriadosAux(String feriadosAux) {
    	if (feriadosAux != null && feriadosAux.trim().length() > 0) {
    		Long id = Long.valueOf(feriadosAux);
    		if (getFeriados()==null || !id.equals(getFeriados().getId())) {
    			com.consisti.sisgesc.entidade.CadastroFeriado obj = new com.consisti.sisgesc.entidade.CadastroFeriadoEntity();
    			obj.setId(id);
    			this.setFeriados(obj);
    		}
    	} else {
    		this.setFeriados(null);
    	}
    }*/

	public CalendarioAnualSubDetEntity(Long id, Date feriados) {
		this.setId(id);
		this.setDataFeriado(feriados);
	}
	@Override
	public String toString() {
		return "";
	}

}
