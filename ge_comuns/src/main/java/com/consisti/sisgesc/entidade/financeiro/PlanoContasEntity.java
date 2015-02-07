package com.consisti.sisgesc.entidade.financeiro;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcEntidade;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="PLANO_CONTAS")
@SequenceGenerator(name="SE_PLANO_CONTAS", sequenceName="SE_PLANO_CONTAS")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="PlanoContasEntity.queryMan", query="from PlanoContasEntity obj"),
	@NamedQuery(name="PlanoContasEntity.querySelLookup", query="select new PlanoContasEntity (obj.id, obj.descricao) from PlanoContasEntity obj where obj.id = ? order by obj.id asc")
})
public class PlanoContasEntity extends PlanoContas {
 	
    /*
     * Construtor padrão
     */
    public PlanoContasEntity() {
    }
	public PlanoContasEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

}
