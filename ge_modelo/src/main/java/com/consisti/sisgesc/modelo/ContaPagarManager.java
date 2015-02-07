package com.consisti.sisgesc.modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import com.consisti.sisgesc.entidade.financeiro.ContaPagarEntity;
import com.consisti.sisgesc.persistencia.hibernate.FormaPagamentoDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.comuns.helper.PlcBeanCloneHelper;

public class ContaPagarManager extends AppManager {
	
	private FormaPagamentoDAO formaPagamentoDAO;
	
	public ContaPagarManager( FormaPagamentoDAO formaPagamentoDAO ) {
		this.formaPagamentoDAO = formaPagamentoDAO;
	}
	
	@Override
	protected void aposPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		ContaPagarEntity contaPagar = (ContaPagarEntity)entidadeAtual;
		if( "inclusaoPlc".equals( modoGravacao ) ){
			setParcelaContaPagar( contaPagar );
		}
		
		super.aposPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

	/**
	 * Caso a conta a pagar tenha mais de uma parcela, sera persistido a quantidade de parcela necessaria para os meses seguintes
	 * @param contaPagar
	 * @throws PlcException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	private void setParcelaContaPagar(ContaPagarEntity contaPagar) throws PlcException{

		if( contaPagar.getFormaPagamento() != null ){
			Integer qtdeParcela = formaPagamentoDAO.recuperaQtdeParcela( contaPagar.getFormaPagamento().getId() );
			
			if( qtdeParcela != null && qtdeParcela > 1 ){
				Calendar cal = Calendar.getInstance();
				cal.setTime( contaPagar.getDataVencimento() );
				//tira um pois foi o primeiro registro gravado
				for ( int i = 0; i < qtdeParcela - 1; i++ ) {
					//adiciona 1 mes para cada parcela
					cal.add(Calendar.MONTH, 1);
					
					try {
						//clona a entidade
						ContaPagarEntity cloneContaPagar = (ContaPagarEntity) PlcBeanCloneHelper.getInstance().cloneBean(contaPagar);
						cloneContaPagar.setId(null);
						cloneContaPagar.setDataVencimento( cal.getTime() );
						Long v = getDAOPadrao().inclui(cloneContaPagar);
						
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
