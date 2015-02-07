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
@Table(name="CONTEUDO")
@SequenceGenerator(name="SE_CONTEUDO", sequenceName="SE_CONTEUDO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ConteudoEntity.queryMan", query="from ConteudoEntity obj"),
	@NamedQuery(name="ConteudoEntity.querySel", query="select new ConteudoEntity(obj.id, obj.descricao, obj.dataCadastro) from ConteudoEntity obj order by obj.descricao asc"),
	@NamedQuery(name="ConteudoEntity.querySelLookup", query="select new ConteudoEntity (obj.id, obj.descricao) from ConteudoEntity obj where obj.id = ? order by obj.id asc")
})
public class ConteudoEntity extends Conteudo {
 	
    /*
     * Construtor padrão
     */
    public ConteudoEntity() {
    }
	public ConteudoEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	
	public ConteudoEntity(Long id, String descricao, Date dataCadastro) {
		this.setId(id);
		this.setDescricao(descricao);
		this.setDataCadastro(dataCadastro);
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}

}
