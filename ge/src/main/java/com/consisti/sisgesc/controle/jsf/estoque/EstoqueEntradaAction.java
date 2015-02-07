package com.consisti.sisgesc.controle.jsf.estoque;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.dominio.TipoMovimentacao;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.entidade.estoque.EstoqueEntity;
import com.consisti.sisgesc.entidade.estoque.Movimento;
import com.consisti.sisgesc.entidade.estoque.MovimentoEntity;
import com.consisti.sisgesc.entidade.estoque.ProdutoMaterialEntity;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseContextVO;
import com.powerlogic.jcompany.comuns.PlcConstantesComuns;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.colaboracao.PlcConfigArgumentoDetalhe.Formato;
import com.powerlogic.jcompany.config.comuns.colaboracao.PlcConfigArgumentoDetalhe.Operador;

/**
 * Classe de Controle gerada pelo assistente
 */
public class EstoqueEntradaAction extends AppAction  {
	
	private boolean podeAlterarMovimentacao;
	
	@Override
	protected boolean pesquisaAntes(List<PlcArgVO> listaArgumentos)
			throws PlcException {
		
		PlcArgVO arg = new PlcArgVO();
		arg.setNome("tipoMovimentacao");
		arg.setValor(TipoMovimentacao.E.name());
		arg.setTipo(Formato.STRING.name());
		arg.setOperador(Operador.IGUAL_A.toString());
		arg.setTipo(PlcConstantesComuns.CONSULTA.QBE.QBE_TIPO_ARGUMENTO);
		listaArgumentos.add(arg);
		
		return super.pesquisaAntes(listaArgumentos);
	}
	
	@Override
	protected String novoApos() throws PlcException {
		
		Movimento mov = (Movimento) entidadePlc;
		mov.setTipoMovimentacao(TipoMovimentacao.E);
		mov.setDataMovimentacao(new Date());
		mov.setFornecedor(new FornecedorEntity());
		mov.setEstoque(new EstoqueEntity());
		
		return super.novoApos();
	}
	
	public void setaEstoqueExistente() throws PlcException{
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		
		ProdutoMaterialEntity produto = (ProdutoMaterialEntity) getServiceFacade().recuperaObjeto(new PlcBaseContextVO(), ProdutoMaterialEntity.class, mov.getEstoque().getProdutoMaterial().getId())[0];
		
		if (!produto.getEstoque().isEmpty()){
			mov.setEstoque(produto.getEstoque().get(0));
			mov.setValorEstoqueTemporario(mov.getEstoque().getValorTotalEstoque());
			mov.setSaldoTemporario(mov.getEstoque().getSaldo());
		} else {
			mov.getEstoque().setProdutoMaterial(produto);
		}
		
	}
	
	public void calculaEntrada() throws PlcException {
		
		Movimento mov = (Movimento) entidadePlc;
		mov.setValorUnitario(obtemValorUnitario(mov));
		calculaTotalEstoque(mov);
		
	}
	
	private BigDecimal obtemValorUnitario(Movimento mov) {
		
		return mov.getValorTotal().divide(new BigDecimal(mov.getQtdeEntrada()), 2, RoundingMode.HALF_UP);
		
	}
	
	private void calculaTotalEstoque(Movimento movimento) {
		
		BigDecimal totalEstoque = new BigDecimal(0);
		
		if (movimento.getEstoque().getValorTotalEstoque()!=null){
			totalEstoque = movimento.getEstoque().getValorTotalEstoque().setScale(2);
		}
		
		Long saldo = 0L;
		if (movimento.getEstoque().getSaldo()!=null){
			saldo = movimento.getEstoque().getSaldo();
		}
		
		totalEstoque = totalEstoque.add(movimento.getValorTotal());
		saldo = saldo + movimento.getQtdeEntrada();
		
		((MovimentoEntity)movimento).setValorEstoqueTemporario(totalEstoque);
		((MovimentoEntity)movimento).setSaldoTemporario(saldo);
		
	}
	
	@Override
	public String edita() throws PlcException {
		
		String retorno = super.edita();
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		mov.setValorEstoqueTemporario(mov.getEstoque().getValorTotalEstoque());
		mov.setSaldoTemporario(mov.getEstoque().getSaldo());
		
		return retorno;
	}
	
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		
		if (mov.getValorUnitario()==null){
			throw new PlcException("erro.calcule.novamente");
		}
		if (mov.getValorUnitario().compareTo(obtemValorUnitario(mov)) != 0){
			throw new PlcException("erro.calcule.novamente");
		} 
		
		return super.gravaSimplesAntes();
	}

	public boolean isPodeAlterarMovimentacao() {
		return podeAlterarMovimentacao;
	}

	public void setPodeAlterarMovimentacao(boolean podeAlterarMovimentacao) {
		this.podeAlterarMovimentacao = podeAlterarMovimentacao;
	}

}
