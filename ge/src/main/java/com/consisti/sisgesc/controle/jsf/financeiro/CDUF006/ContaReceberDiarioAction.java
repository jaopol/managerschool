package com.consisti.sisgesc.controle.jsf.financeiro.CDUF006;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.dominio.TipoContaReceber;
import com.consisti.sisgesc.dominio.TipoReceberDe;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberProdutoVenda;
import com.consisti.sisgesc.entidade.financeiro.ProdutoVenda;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.controle.PlcConstantes;
import com.powerlogic.jcompany.controle.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

@SuppressWarnings("serial")
public class ContaReceberDiarioAction extends RelatorioActionPlc {

	//utilizado para identificar a linha editada
	private int indexDet;
	//Usado na edicao devido o campo desabilitado perder o valor na gravacao
	private TipoReceberDe tipoReceberDeAuxEdit;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		
		if( entidadePlc != null && entidadePlc.getId() != null ){
			
			//Se o titulo estiver quitado
			if( PlcSimNao.S.equals( ((ContaReceberEntity)entidadePlc ).getRecebido() ) ){
				contextHelperPlc.getRequest().setAttribute("exibeLiquidaTitulo", "N");
				contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, "N");
				contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, "N");
			}
			//Se o titulo não estiver quitado
			else{
				contextHelperPlc.getRequest().setAttribute("exibeLiquidaTitulo", "S");
			}
			
			//gera carne somente para aluno
			if( StringUtils.isBlank( ((ContaReceberEntity)entidadePlc).getOutro() ) ){
				contextHelperPlc.getRequest().setAttribute("exibeGerarCarne", "S");
			}
			else{
				contextHelperPlc.getRequest().setAttribute("exibeGerarCarne", "N");	
			}
		}
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_IMPRIMIR, PlcConstantes.EXIBIR );
	}
	
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;

		if(contaReceber.getTipoReceberDe() == null ){
			contaReceber.setTipoReceberDe( getTipoReceberDeAuxEdit() );
		}
		
		setValorPadrao(contaReceber);
		
		return super.gravaSimplesAntes();
	}
	
	@Override
	protected String gravaApos() {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		if(TipoReceberDe.O.equals( contaReceber.getTipoReceberDe() ) ){
			contaReceber.setAluno(new AlunoEntity());
		}
		setTipoReceberDeAuxEdit( contaReceber.getTipoReceberDe() );
		
		return super.gravaApos();
	}

	/**
	 * @param contaReceber
	 */
	private void setValorPadrao(ContaReceberEntity contaReceber) {
		
		contaReceber.setBoletoGerado(PlcSimNao.N);
		contaReceber.setValorDocumento( contaReceber.getValorTotal() );
		if( contaReceber.getDataVencimento() == null ){
			contaReceber.setDataVencimento( contaReceber.getDataRecebimento() );
		}
		contaReceber.setTipoContaReceber( TipoContaReceber.D );
		
		if( TipoReceberDe.O.equals( contaReceber.getTipoReceberDe() ) ){
			contaReceber.setContaReceberProdutoVenda( new ArrayList<ContaReceberProdutoVenda>() );
			contaReceber.setAluno(null);
		}
		else{
			contaReceber.setOutro(null);
		}
	}

	@Override
	protected String novoApos() throws PlcException {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		
		contaReceber.setDataDocumento( new Date() );
		contaReceber.setTipoReceberDe( TipoReceberDe.A );
		contaReceber.setRecebido(PlcSimNao.N);
		
		return super.novoApos();
	}
	
	@Override
	protected String editaApos() throws PlcException {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		setTipoReceberDeAuxEdit( contaReceber.getTipoReceberDe() );
		
		return super.editaApos();
	}
	
	/**
	 * Seta o valor unitario do produto escolhido pelo usuario
	 * @return
	 */
	public String setaValorProduto(){
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		
		ProdutoVenda produtoVenda = contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getProdutoVenda();
		
		if(produtoVenda != null){
			if( contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getQuantidadeVendida() == null ){
				contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).setValorUnitario( produtoVenda.getValor());
			}
			else{
				contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).setValorUnitario( produtoVenda.getValor());
				setaValorTotal();
			}
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	/**
	 * Seta o valor total multiplicado pela quantidade informada pelo usuario
	 * @return
	 */
	public String setaValorTotal(){
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		ProdutoVenda produtoVenda = contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getProdutoVenda();
		
		if(produtoVenda != null && contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getQuantidadeVendida() != null){
			
			if(contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getValorTotal() != null){
				//retira valor total do produto alterado
				contaReceber.setValorTotal( contaReceber.getValorTotal().subtract( contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getValorTotal() ) );
			}
			
			Double valorTotal = contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getQuantidadeVendida() *
			contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getValorUnitario().doubleValue();
			contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).setValorTotal( new BigDecimal(valorTotal) );
			setaValorTotalReceber( contaReceber.getContaReceberProdutoVenda().get(getIndexDet()).getValorTotal(), contaReceber );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/**
	 * Seta o titulo como recebido
	 * @return
	 * @throws PlcException
	 */
	public String liquidaTitulo() throws PlcException{
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		
		if( PlcSimNao.S.equals( contaReceber.getRecebido() ) ){
			throw new PlcException("msg.info.tituloLiquidado");
		}
		
		contaReceber.setRecebido( PlcSimNao.S );

		if( contaReceber.getDataRecebimento() == null ){
			contaReceber.setDataRecebimento( new Date() );
		}
		
		grava();
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/**
	 * Seta o valor total do contas a receber
	 * @param valorTotalProduto
	 * @param contaReceber
	 */
	private void setaValorTotalReceber(BigDecimal valorTotalProduto,
			ContaReceberEntity contaReceber) {
		
		if(contaReceber.getValorTotal() == null){
			contaReceber.setValorTotal( BigDecimal.ZERO );
		}
		contaReceber.setValorTotal( contaReceber.getValorTotal().add( valorTotalProduto ) );
		
	}
	
	/**
	 * Utilizado para gerar o Carnê
	 * @return
	 * @throws PlcException
	 */
	public String gerarCarne() throws PlcException{
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadePlc;
		
		if( contaReceber.getContaReceberProdutoVenda() != null && !contaReceber.getContaReceberProdutoVenda().isEmpty() ){
			IAppFacade fc = (IAppFacade)getServiceFacade();
			for (ContaReceberProdutoVenda produtoVenda : contaReceber.getContaReceberProdutoVenda() ) {
				
				String desricaoProduto = "";
				if( StringUtils.isBlank( produtoVenda.getProdutoVenda().getDescricao() ) ){
					desricaoProduto = fc.recuperaDescricaoProdutoVenda( produtoVenda.getProdutoVenda().getId() );
				}
				else{
					desricaoProduto = produtoVenda.getProdutoVenda().getDescricao();
				}
				
				if( StringUtils.isBlank( contaReceber.getDescricaoCarne() ) ){
					contaReceber.setDescricaoCarne( desricaoProduto );
				}
				else{
					contaReceber.setDescricaoCarne( contaReceber.getDescricaoCarne() +", "+ desricaoProduto );
				}
			}
		}
		else{
			contaReceber.setDescricaoCarne( contaReceber.getEvento().getDescricao() );
		}
		
		List<ContaReceberEntity> listContaReceberEvento = new ArrayList<ContaReceberEntity>();
		listContaReceberEvento.add( contaReceber );
		if(listContaReceberEvento != null ){
			HashMap<String , String> map = new HashMap<String, String>();
			geraListaRelatorio(AppConstantesComuns.RELATORIO.CARNE_EVENTO_ALUNO, listContaReceberEvento,map);
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
		
	}

	public int getIndexDet() {
		return indexDet;
	}

	public void setIndexDet(int indexDet) {
		this.indexDet = indexDet;
	}

	public TipoReceberDe getTipoReceberDeAuxEdit() {
		return tipoReceberDeAuxEdit;
	}

	public void setTipoReceberDeAuxEdit(TipoReceberDe tipoReceberDeAuxEdit) {
		this.tipoReceberDeAuxEdit = tipoReceberDeAuxEdit;
	}
	
}
