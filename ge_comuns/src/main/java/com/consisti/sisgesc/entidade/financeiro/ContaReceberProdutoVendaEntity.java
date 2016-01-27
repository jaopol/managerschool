package com.consisti.sisgesc.entidade.financeiro;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CONTA_RECEBER_PRODUTO_VENDA")
@SequenceGenerator(name="SE_CONTA_RECEBER_PRODUTO_VENDA", sequenceName="SE_CONTA_RECEBER_PRODUTO_VENDA")
@AccessType("field")

@PlcValidacaoUnificada
@PlcTabular(propReferenciaDesprezar="produtoVenda")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ContaReceberProdutoVendaEntity.querySelLookup", query="select new ContaReceberProdutoVendaEntity (obj.id, obj.produtoVenda) from ContaReceberProdutoVendaEntity obj where obj.id = ? order by obj.id asc")
})
public class ContaReceberProdutoVendaEntity extends ContaReceberProdutoVenda {

	private transient String produtoVendaAuxLookup;

 	
    /*
     * Construtor padrão
     */
    public ContaReceberProdutoVendaEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getProdutoVendaAux() {
    	return getProdutoVenda()!=null? getProdutoVenda().getIdAux() : null;
    }
    
    public void setProdutoVendaAux(String produtoVendaAux) {
    	if (produtoVendaAux != null && produtoVendaAux.trim().length() > 0) {
    		Long id = Long.valueOf(produtoVendaAux);
    		if (getProdutoVenda()==null || !id.equals(getProdutoVenda().getId())) {
    			com.consisti.sisgesc.entidade.financeiro.ProdutoVenda obj = new com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity();
    			obj.setId(id);
    			this.setProdutoVenda(obj);
    		}
    	} else {
    		this.setProdutoVenda(null);
    	}
    }

	public ContaReceberProdutoVendaEntity(Long id, ProdutoVenda produtoVenda) {
		this.setId(id);
		this.setProdutoVenda(produtoVenda);
	}
	
	@Override
	public String toString() {
		return getProdutoVendaAux();
	}


	public void setProdutoVendaAuxLookup(String produtoVendaAuxLookup) {
		this.produtoVendaAuxLookup=produtoVendaAuxLookup;
	}

}
