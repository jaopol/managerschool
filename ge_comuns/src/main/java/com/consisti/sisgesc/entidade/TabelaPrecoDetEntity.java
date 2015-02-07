package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="TABELA_PRECO_DET")
@SequenceGenerator(name="SE_TABELA_PRECO_DET", sequenceName="SE_TABELA_PRECO_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="tempoHrs")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="TabelaPrecoDetEntity.querySelLookup", query="select new TabelaPrecoDetEntity (obj.id, obj.tempoHrs) from TabelaPrecoDetEntity obj where obj.id = ? order by obj.id asc")
})
public class TabelaPrecoDetEntity extends TabelaPrecoDet {
 	
    /*
     * Construtor padrão
     */
    public TabelaPrecoDetEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */

	public TabelaPrecoDetEntity(Long id, String tempoHrs) {
		this.setId(id);
		this.setTempoHrs(tempoHrs);
	}
	@Override
	public String toString() {
		return getIdAux();
	}

}
