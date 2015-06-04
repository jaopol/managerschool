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
@Table(name="PRODUTO_VENDA")
@SequenceGenerator(name="SE_PRODUTO_VENDA", sequenceName="SE_PRODUTO_VENDA")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ProdutoVendaEntity.queryMan", query="from ProdutoVendaEntity obj"),
	@NamedQuery(name="ProdutoVendaEntity.querySelLookup", query="select new ProdutoVendaEntity (obj.id, obj.descricao) from ProdutoVendaEntity obj where obj.id = ? order by obj.descricao asc")
})
public class ProdutoVendaEntity extends ProdutoVenda {
 	
    /*
     * Construtor padrão
     */
    public ProdutoVendaEntity() {
    }
	public ProdutoVendaEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

}
