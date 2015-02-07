package com.consisti.sisgesc.entidade.estoque;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcIoC;

import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="PRODUTO_MATERIAL")
@SequenceGenerator(name="SE_PRODUTO_MATERIAL", sequenceName="SE_PRODUTO_MATERIAL")
@AccessType("field")
@PlcIoC(nomeClasseBC="com.consisti.sisgesc.modelo.ProdutoMaterialManager")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ProdutoMaterialEntity.queryMan", query="from ProdutoMaterialEntity obj"),
	@NamedQuery(name="ProdutoMaterialEntity.querySel", query="select new ProdutoMaterialEntity(obj.id, obj.descricao, obj.dataCadastro) from ProdutoMaterialEntity obj order by obj.descricao asc"),
	@NamedQuery(name="ProdutoMaterialEntity.querySelLookup", query="select new ProdutoMaterialEntity (obj.id, obj.descricao) from ProdutoMaterialEntity obj where obj.id = ? order by obj.id asc")
})
public class ProdutoMaterialEntity extends ProdutoMaterial {
 	
    /*
     * Construtor padrão
     */
    public ProdutoMaterialEntity() {
    }
	public ProdutoMaterialEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		if (StringUtils.isEmpty(getDescricao())){
			return "";
		}
		return getDescricao();
	}

	public ProdutoMaterialEntity(Long id, String descricao, java.util.Date dataCadastro) {
		setId(id);
		setDescricao(descricao);
		setDataCadastro(dataCadastro);
	}
}
