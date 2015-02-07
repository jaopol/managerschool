package com.consisti.sisgesc.controle.jsf.estoque;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.dominio.TipoMovimentacao;
import com.consisti.sisgesc.entidade.estoque.EstoqueEntity;
import com.consisti.sisgesc.entidade.estoque.Movimento;
import com.consisti.sisgesc.entidade.estoque.ProdutoMaterial;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
public class EstoqueAction extends RelatorioActionPlc  {
	
	private Date dataInicio;
	private Date dataFim;
	private ProdutoMaterial produtoMaterial;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		super.trataBotoesConformeLogicaApos();
		
		contextHelperPlc.getRequest().setAttribute("exibeRelEstoqueAnalitico", PlcSimNao.S);
		contextHelperPlc.getRequest().setAttribute("exibeRelEstoqueSintetico", PlcSimNao.S);
		
	}
	
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
		
		for (Movimento movimento : estoque.getMovimento()) {
			if (StringUtils.isNotEmpty(movimento.getObservacao())){
				if (movimento.getQtdeEntrada()==null && movimento.getQtdeSaida()==null){
					throw new PlcException("erro.entrada.saida.obrigatorio");
				}
			}
			
			if (movimento.getId()==null){
				movimento.setDataMovimentacao(new Date());
			}
			
		}
		
		return super.gravaSimplesAntes();
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelEstoqueAnalitico() throws PlcException, IllegalAccessException, InvocationTargetException{
		
		EstoqueEntity estoque = new EstoqueEntity();
		List<PlcArgVO> listaArgumentos 	= montaListaArgumentosPesquisa();
		List<EstoqueEntity> recuperaLista = getServiceFacade().recuperaLista(montaContextHelperPlc.montaContextParam(entidadePlc,controleConversacaoPlc),
				this.configUrlColaboracaoPlc.getAcao().entidade(),
				controleConversacaoPlc.getOrdenacaoPlc(),listaArgumentos);
		
		if (recuperaLista.isEmpty()){
			throw new PlcException("estoque.vazio");
		} else {
			estoque.setDadosEstoque(new ArrayList<EstoqueEntity>());
		}
		
		Set<Long> listaaAux = new HashSet<Long>();
		
		for (EstoqueEntity estoqueAux : recuperaLista) {
			Iterator<Movimento> it = estoqueAux.getMovimento().iterator();
			while (it.hasNext()) {
				Movimento mov = (Movimento) it.next();
				if (!listaaAux.add(mov.getId())){
					it.remove();
				}
			}
			
			if (!estoqueAux.getMovimento().isEmpty()){
				estoque.getDadosEstoque().add(estoqueAux);
			}
			
		}
		
		HashMap<String, String> map = new HashMap();
		String dataIni = contextHelperPlc.getRequest().getParameter("corpo:formulario:dataMovimentacao_ArgINI");
		String dataFim = contextHelperPlc.getRequest().getParameter("corpo:formulario:dataMovimentacao_ArgFIM");
		
		if (StringUtils.isNotEmpty(dataIni) && StringUtils.isNotEmpty(dataFim)){
			map.put("periodoMovimentacao", "Perído de Movimentação: "+dataIni+" à "+dataFim);
		} else {
			map.put("periodoMovimentacao", "Perído de Movimentação: "+dataIni+dataFim);
		}
		
		super.geraRelatorioPlc(AppConstantesComuns.RELATORIO.ESTOQUE_ANALITICO, estoque, map);
		
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelEstoqueSintetico() throws PlcException, IllegalAccessException, InvocationTargetException{
		
		EstoqueEntity estoque = new EstoqueEntity();
		List<PlcArgVO> listaArgumentos 	= montaListaArgumentosPesquisa();
		List<EstoqueEntity> recuperaLista = getServiceFacade().recuperaLista(montaContextHelperPlc.montaContextParam(entidadePlc,controleConversacaoPlc),
				this.configUrlColaboracaoPlc.getAcao().entidade(),
				controleConversacaoPlc.getOrdenacaoPlc(),listaArgumentos);
		
		if (recuperaLista.isEmpty()){
			throw new PlcException("estoque.vazio");
		} else {
			estoque.setDadosEstoque(new ArrayList<EstoqueEntity>());
		}
		
		Set<Long> listaaAux = new HashSet<Long>();
		
		for (EstoqueEntity est : recuperaLista) {
			if (listaaAux.add(est.getId())){
				estoque.getDadosEstoque().add(est);
			}
		}
		
		HashMap<String, String> map = new HashMap();
		String dataIni = contextHelperPlc.getRequest().getParameter("corpo:formulario:dataMovimentacao_ArgINI");
		String dataFim = contextHelperPlc.getRequest().getParameter("corpo:formulario:dataMovimentacao_ArgFIM");
		
		if (StringUtils.isNotEmpty(dataIni) && StringUtils.isNotEmpty(dataFim)){
			map.put("periodoMovimentacao", "Perído de Movimentação: "+dataIni+" à "+dataFim);
		} else {
			map.put("periodoMovimentacao", "Perído de Movimentação: "+dataIni+dataFim);
		}
		
		somaTotais(estoque.getDadosEstoque(), estoque);
		
		super.geraRelatorioPlc(AppConstantesComuns.RELATORIO.ESTOQUE_SINTETICO, estoque, map);
		
	}

	/**
	 * Soma os totais do relatorio
	 * @param dadosEstoque
	 * @param estoque
	 */
	private void somaTotais(List<EstoqueEntity> dadosEstoque, EstoqueEntity estoque) {
		
		BigDecimal valorTotal = new BigDecimal(0);
		Long saldoTotalSomado = new Long(0);
		
		for (EstoqueEntity estoqueEntity : dadosEstoque) {
			valorTotal = valorTotal.add(estoqueEntity.getValorTotalEstoque());
			saldoTotalSomado = saldoTotalSomado + estoqueEntity.getSaldo();
		}
		
		estoque.setSaldoTotalSomado(saldoTotalSomado);
		estoque.setValorTotalSomado(NumberFormat.getCurrencyInstance().format(valorTotal));
		
	}

	public void calculaEntradaSaidaEstoque() throws PlcException{
		
		String indice = contextHelperPlc.getRequest().getParameter("corpo:formulario:indice");
		
		if (StringUtils.isNotEmpty(indice)){
			
			EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
			
			Movimento mov = estoque.getMovimento().get(Integer.valueOf(indice));

			calcula(estoque, mov);
		
			calculaTotalEstoque(estoque.getMovimento());
			
		}
		
	}
	
	public void ajustaEstoque() throws PlcException{
		
		String indice = contextHelperPlc.getRequest().getParameter("corpo:formulario:indice");
		
		if (StringUtils.isNotEmpty(indice)){
			
			EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
			
			Movimento movimento = estoque.getMovimento().get(Integer.valueOf(indice));

			if (movimento.getTipoMovimentacao()!=null){
				if (TipoMovimentacao.E.equals(movimento.getTipoMovimentacao())){
					movimento.setQtdeSaida(null);
					movimento.setValorTotal(null);
					movimento.setValorUnitario(null);
				} else {
					movimento.setQtdeEntrada(null);
					movimento.setValorTotal(null);
					movimento.setValorUnitario(null);
				}
				calcula(estoque, movimento);
			} else {
				movimento.setQtdeEntrada(null);
				movimento.setQtdeSaida(null);
				movimento.setValorTotal(null);
				movimento.setValorUnitario(null);
			}
			
			calculaTotalEstoque(estoque.getMovimento());
			
		}
		
	}
	
	private void calcula(EstoqueEntity estoque, Movimento mov) throws PlcException {
		
		if (TipoMovimentacao.E.equals(mov.getTipoMovimentacao())){
			if (mov.getValorTotal()!=null && mov.getQtdeEntrada()!=null){
				mov.setValorUnitario(mov.getValorTotal().divide(new BigDecimal(mov.getQtdeEntrada()), 2, RoundingMode.HALF_UP));
			}
		} else {
			if (mov.getQtdeSaida()!=null){
				if (estoque.getValorTotalEstoque()!=null || estoque.getSaldo()!=null){
					if (estoque.getSaldo()>0){
						BigDecimal valorUnitario = estoque.getValorTotalEstoque().divide(new BigDecimal(estoque.getSaldo()), 2, RoundingMode.HALF_UP);
						mov.setValorUnitario(valorUnitario);
						mov.setValorTotal(valorUnitario.multiply(new BigDecimal(mov.getQtdeSaida())));
					} else {
						throw new PlcException("erro.valor.estoque.zerado");
					}
				}
			}
			
		}
		
	}

	private void calculaTotalEstoque(List<Movimento> movimento) {
		
		BigDecimal totalEstoque = new BigDecimal(0).setScale(2);
		Long saldo = 0L;
		
		for (Movimento movimento2 : movimento) {
			if (TipoMovimentacao.E.equals(movimento2.getTipoMovimentacao())){
				if (movimento2.getValorTotal()!=null && movimento2.getQtdeEntrada()!=null){
					totalEstoque = totalEstoque.add(movimento2.getValorTotal());
					saldo = saldo + movimento2.getQtdeEntrada();
				}
			} else {
				if (movimento2.getValorTotal()!=null && movimento2.getQtdeSaida()!=null){
					totalEstoque = totalEstoque.subtract(movimento2.getValorTotal());
					saldo = saldo - movimento2.getQtdeSaida();
				}
			}
			
		}
		
		EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
		estoque.setValorTotalEstoque(totalEstoque);
		estoque.setSaldo(saldo);
		
	}

	public void verificaMovimento(){
		
		EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
		
		for (Movimento movimento : estoque.getMovimento()) {
			if (movimento.getId()==null){
				//calculaMovimento(movimento, estoque);
			}
		}
		
	}

	@Override
	protected boolean excluiAntes() throws PlcException {
		
		EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
		
		for (Movimento movimento : estoque.getMovimento()) {
			if (movimento.getId()!=null && PlcSimNao.S.name().equals(movimento.getIndExcPlc())){
				calculaMovimento(movimento);
			}
		}
		
		return super.excluiAntes();
	}
	
	private void calculaMovimento(Movimento movimento) {
		
		EstoqueEntity estoque = (EstoqueEntity) entidadePlc;
		BigDecimal valorTotalEstoque = new BigDecimal(0);
		
		if (TipoMovimentacao.E.equals(movimento.getTipoMovimentacao())){
			//movimento.getQtdeEntrada()*movimento.getValorUnitario();
			//valorTotalEstoque.add(augend)
			//estoque.setValorTotalEstoque(valorTotalEstoque)
		} else {
			
		}
		
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ProdutoMaterial getProdutoMaterial() {
		return produtoMaterial;
	}

	public void setProdutoMaterial(ProdutoMaterial produtoMaterial) {
		this.produtoMaterial = produtoMaterial;
	}
	
}
