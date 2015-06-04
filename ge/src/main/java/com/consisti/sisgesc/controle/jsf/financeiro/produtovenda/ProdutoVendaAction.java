package com.consisti.sisgesc.controle.jsf.financeiro.produtovenda;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ProdutoVendaAction extends AppAction  {
	
	@Override
	protected String tabularNovoApos() throws PlcException {
		
		List<PlcBaseVO> listProdutoVenda = logicaItensPlc.getItensPlc();
		setaDataCadastro( listProdutoVenda );
		
		return super.tabularNovoApos();
	}

	/**
	 * Seta a data de cadastro
	 * @param listPlanoContas
	 */
	private void setaDataCadastro(List<PlcBaseVO> listProdutoVenda) {
		
		if( !listProdutoVenda.isEmpty() ){
			for (PlcBaseVO plcBaseVO : listProdutoVenda) {
				ProdutoVendaEntity produtoVenda = (ProdutoVendaEntity)plcBaseVO;
				if(produtoVenda.getDataCadastro() == null ){
					produtoVenda.setDataCadastro( new Date() );
				}
			}
		}
		
	}
	
}
