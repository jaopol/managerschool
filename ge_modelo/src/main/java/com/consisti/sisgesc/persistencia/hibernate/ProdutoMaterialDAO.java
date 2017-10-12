package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.entidade.estoque.ProdutoMaterialEntity;
import com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ProdutoMaterialDAO extends AppBaseDAO {
	
	public boolean existeProdutoMaterial( ProdutoMaterialEntity produto ) throws PlcException{
		
		StringBuffer hql = new StringBuffer();
		hql.append( "select count(*) from ProdutoMaterialEntity obj where UPPER(obj.descricao) = UPPER(:descricao) " );
		
		Long count = 0L;
		
		if( produto.getId() != null ){
			hql.append( "and obj.id <> :id " );
			count = (Long) getSession().createQuery(hql.toString()).setString( "descricao", produto.getDescricao().trim() ).setLong("id", produto.getId()).uniqueResult();
		}
		else{
			count = (Long) getSession().createQuery(hql.toString()).setString( "descricao", produto.getDescricao().trim() ).uniqueResult();
		}
		
		if( count > 0 ){
			return true;
		}
		else{
			return false;
		}
	}

}
