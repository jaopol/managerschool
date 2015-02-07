package com.consisti.sisgesc.modelo;

import com.consisti.sisgesc.entidade.estoque.ProdutoMaterialEntity;
import com.consisti.sisgesc.persistencia.hibernate.ProdutoMaterialDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ProdutoMaterialManager extends AppManager {
	
	private ProdutoMaterialDAO produtoMaterialDAO;
	
	public ProdutoMaterialManager( ProdutoMaterialDAO produtoMaterialDAO ) {
		this.produtoMaterialDAO = produtoMaterialDAO;
	}
	
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		ProdutoMaterialEntity produto = (ProdutoMaterialEntity)entidadeAtual;
		naoDeveExistirDescricaoDuplicada( produto );
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

	/**
	 * Verifica se ja existe 
	 * @param produto
	 * @throws PlcException 
	 */
	private void naoDeveExistirDescricaoDuplicada(ProdutoMaterialEntity produto) throws PlcException {
		
		if( produtoMaterialDAO.existeProdutoMaterial( produto ) ){
			throw new PlcException("msg.info.produtoMaterial.duplicado", new String[]{produto.getDescricao()} );
		}
	}

}
