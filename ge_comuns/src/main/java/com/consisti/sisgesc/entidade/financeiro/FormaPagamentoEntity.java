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
@Table(name="FORMA_PAGAMENTO")
@SequenceGenerator(name="SE_FORMA_PAGAMENTO", sequenceName="SE_FORMA_PAGAMENTO")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FormaPagamentoEntity.queryMan", query="from FormaPagamentoEntity obj"),
	@NamedQuery(name="FormaPagamentoEntity.querySelLookup", query="select new FormaPagamentoEntity (obj.id, obj.descricao) from FormaPagamentoEntity obj where obj.id = ? order by obj.id asc")
})
public class FormaPagamentoEntity extends FormaPagamento {
 	
    /*
     * Construtor padrão
     */
    public FormaPagamentoEntity() {
    }
	public FormaPagamentoEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

}
