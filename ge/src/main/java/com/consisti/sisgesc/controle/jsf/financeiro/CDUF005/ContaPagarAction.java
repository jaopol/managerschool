package com.consisti.sisgesc.controle.jsf.financeiro.CDUF005;

import java.util.ArrayList;
import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.ContaPagarEntity;
import com.consisti.sisgesc.entidade.financeiro.TipoPlanoContasEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.controle.PlcConstantes;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContaPagarAction extends AppAction  {
	
	private ArrayList<TipoPlanoContasEntity> listaTipoPlanoContas = new ArrayList<TipoPlanoContasEntity>();

	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		trataBotoes();
		super.trataBotoesConformeLogicaApos();
	}

	private void trataBotoes() {
		
		if( entidadePlc != null && entidadePlc.getId() != null ){
			//Se o titulo estiver quitado
			if( PlcSimNao.S.equals( ((ContaPagarEntity)entidadePlc ).getContaPaga() ) ){
				contextHelperPlc.getRequest().setAttribute("exibeLiquidaTitulo", "N");
				contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, "N");
				contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, "N");
			}
			//Se o titulo não estiver quitado
			else{
				contextHelperPlc.getRequest().setAttribute("exibeLiquidaTitulo", "S");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		ContaPagarEntity contaPagar = (ContaPagarEntity)entidadePlc;
		contaPagar.setDataInclusao( new Date() );
		contaPagar.setContaPaga( PlcSimNao.N );
		
		return super.novoApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesAntes()
	 */
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		ContaPagarEntity contaPagar = (ContaPagarEntity)entidadePlc;
		
		validaDataVencimento( contaPagar.getDataVencimento() );
		
		return super.gravaSimplesAntes();
	}
	

	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#editaApos()
	 */
	@Override
	protected String editaApos() throws PlcException {
		
		montaComboTipoPlanoContas();
		
		return super.editaApos();
	}

	/**
	 * Monta o combo tipo plano de contas de acordo com o plano de contas selecionado
	 * @return
	 * @throws PlcException
	 */
	public String montaComboTipoPlanoContas() throws PlcException{
		
		ContaPagarEntity contaPagar = (ContaPagarEntity)entidadePlc;
		
		if( contaPagar.getPlanoContas() != null ){
			IAppFacade facade = (IAppFacade)getServiceFacade();
			setListaTipoPlanoContas( facade.recuperaListaTipoPlanoContaByIdPlanoConta( contaPagar.getPlanoContas().getId() ) );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	
	/**
	 * Seta o titulo como pago
	 * @return
	 * @throws PlcException
	 */
	public String liquidaTitulo() throws PlcException{
		
		ContaPagarEntity contaPagar = (ContaPagarEntity)entidadePlc;
		
		
		if( PlcSimNao.S.equals( contaPagar.getContaPaga() ) ){
			throw new PlcException("msg.info.tituloLiquidado");
		}
		
		if( contaPagar.getBanco() == null ){
			throw new PlcException("msg.info.informar.banco");
		}

		validaDataPagamento( contaPagar.getDataPagamento() );
		
		contaPagar.setContaPaga( PlcSimNao.S );

		if( contaPagar.getDataPagamento() == null ){
			contaPagar.setDataPagamento( new Date() );
		}
		
		grava();
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	public ArrayList<TipoPlanoContasEntity> getListaTipoPlanoContas() {
		return listaTipoPlanoContas;
	}

	public void setListaTipoPlanoContas(
			ArrayList<TipoPlanoContasEntity> listaTipoPlanoContas) {
		this.listaTipoPlanoContas = listaTipoPlanoContas;
	}
	
}
