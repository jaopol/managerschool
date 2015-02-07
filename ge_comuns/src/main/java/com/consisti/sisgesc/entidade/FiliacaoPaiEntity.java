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
@Table(name="FILIACAO_PAI")
@SequenceGenerator(name="SE_FILIACAO_PAI", sequenceName="SE_FILIACAO_PAI")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="nomePai")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FiliacaoPaiEntity.querySelLookup", query="select new FiliacaoPaiEntity (obj.id, obj.nomePai) from FiliacaoPaiEntity obj where obj.id = ? order by obj.id asc")
})
public class FiliacaoPaiEntity extends FiliacaoPai {
 	
    /*
     * Construtor padrão
     */
    public FiliacaoPaiEntity() {
    }
	public FiliacaoPaiEntity(Long id, String nomePai) {
		this.setId(id);
		this.setNomePai(nomePai);
	}
	@Override
	public String toString() {
		return getNomePai();
	}

}
