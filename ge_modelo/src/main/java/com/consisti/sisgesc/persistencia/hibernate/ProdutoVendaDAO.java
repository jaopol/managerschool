package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ProdutoVendaDAO extends AppBaseDAO {

	public String recuperarDescricaoProdutoVenda(Long id) throws PlcException {
		ProdutoVendaEntity produtoVenda = (ProdutoVendaEntity)recupera(ProdutoVendaEntity.class, id);
		return produtoVenda.getDescricao();
	}

}
