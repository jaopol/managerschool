package com.consisti.sisgesc.controle.jsf.financeiro.CDUF001;

import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class FormaPagamentoAction extends AppAction  {
	
	@Override
	protected String tabularNovoApos() throws PlcException {
		
		List<PlcBaseVO> listFormaPagamento = logicaItensPlc.getItensPlc();
		setaDataCadastro( listFormaPagamento );
		
		return super.tabularNovoApos();
	}

	/**
	 * Seta a data de cadastro
	 * @param listFormaPagamento
	 */
	private void setaDataCadastro(List<PlcBaseVO> listFormaPagamento) {
		
		if( !listFormaPagamento.isEmpty() ){
			for (PlcBaseVO plcBaseVO : listFormaPagamento) {
				FormaPagamentoEntity formaPagamento = (FormaPagamentoEntity) plcBaseVO;
				if(formaPagamento.getDataCadastro() == null ){
					formaPagamento.setDataCadastro( new Date() );
				}
			}
		}
		
	}
}
