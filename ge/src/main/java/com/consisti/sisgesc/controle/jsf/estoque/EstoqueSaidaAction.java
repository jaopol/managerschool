package com.consisti.sisgesc.controle.jsf.estoque;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.dominio.TipoMovimentacao;
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
public class EstoqueSaidaAction extends AppAction  {

	private boolean podeAlterarMovimentacao;
	
	@Override
	protected boolean pesquisaAntes(List<PlcArgVO> listaArgumentos)
			throws PlcException {
		
		PlcArgVO arg = new PlcArgVO();
		arg.setNome("tipoMovimentacao");
		arg.setValor(TipoMovimentacao.S.name());
		arg.setTipo(Formato.STRING.name());
		arg.setOperador(Operador.IGUAL_A.toString());
		arg.setTipo(PlcConstantesComuns.CONSULTA.QBE.QBE_TIPO_ARGUMENTO);
		listaArgumentos.add(arg);
		
		return super.pesquisaAntes(listaArgumentos);
	}
	
	@Override
	protected String novoApos() throws PlcException {
		
		Movimento mov = (Movimento) entidadePlc;
		mov.setTipoMovimentacao(TipoMovimentacao.S);
		mov.setEstoque(new EstoqueEntity());
		
		return super.novoApos();
	}
	
	public void setaEstoqueExistente() throws PlcException{
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		
		ProdutoMaterialEntity produto = (ProdutoMaterialEntity) getServiceFacade().recuperaObjeto(new PlcBaseContextVO(), ProdutoMaterialEntity.class, mov.getEstoque().getProdutoMaterial().getId())[0];
		mov.setValorTotal(new BigDecimal(0));
		if (!produto.getEstoque().isEmpty()){
			mov.setEstoque(produto.getEstoque().get(0));
			mov.setValorEstoqueTemporario(mov.getEstoque().getValorTotalEstoque());
			mov.setSaldoTemporario(mov.getEstoque().getSaldo());
		} else {
			throw new PlcException("erro.estoque.zerado", new Object [] {produto.getDescricao()});
		}
		
	}
	
	@Override
	protected String editaApos() throws PlcException {
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		
		mov.setSaldoTemporario(mov.getEstoque().getSaldo());
		mov.setValorEstoqueTemporario(mov.getValorEstoqueTemporario());
		
		return super.editaApos();
	}
	
	public void calculaSaida() throws PlcException {
		
		MovimentoEntity mov = (MovimentoEntity) entidadePlc;
		
		if (mov.getQtdeSaida() > mov.getSaldoTemporario()){
			throw new PlcException("erro.saida.maior.saldo");
		}
		
		mov.setValorUnitario(obtemValorUnitario(mov));
		calculaTotalEstoque(mov);
		
	}
	
	private BigDecimal obtemValorUnitario(Movimento mov) {
		
		return ((MovimentoEntity) mov).getValorEstoqueTemporario().divide(new BigDecimal(((MovimentoEntity) mov).getSaldoTemporario()), 2, RoundingMode.HALF_UP);
		
	}
	
	private void calculaTotalEstoque(Movimento movimento) throws PlcException {
		
		if (movimento.getEstoque().getValorTotalEstoque()==null){
			throw new PlcException("erro.estoque.zerado", new Object[] {movimento.getEstoque().getProdutoMaterial().getDescricao()});
		}
		
		BigDecimal totalEstoque = movimento.getEstoque().getValorTotalEstoque().setScale(2);
		if (new BigDecimal(0).compareTo(totalEstoque) == 0 ){
			throw new PlcException("erro.estoque.zerado", new Object[] {movimento.getEstoque().getProdutoMaterial().getDescricao()});
		}
		
		Long saldo = movimento.getEstoque().getSaldo();
		
		totalEstoque = totalEstoque.subtract(movimento.getValorUnitario().multiply(new BigDecimal(movimento.getQtdeSaida())));
		saldo = saldo - movimento.getQtdeSaida();
		
		((MovimentoEntity)movimento).setValorEstoqueTemporario(totalEstoque);
		((MovimentoEntity)movimento).setSaldoTemporario(saldo);
		
	}
	
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		Movimento mov = (Movimento) entidadePlc;
		
		mov.setDataMovimentacao(new Date());
		mov.getEstoque().setSaldo(((MovimentoEntity)mov).getSaldoTemporario());
		mov.getEstoque().setValorTotalEstoque(((MovimentoEntity)mov).getValorEstoqueTemporario());
		
		return super.gravaSimplesAntes();
	}

	public boolean isPodeAlterarMovimentacao() {
		return podeAlterarMovimentacao;
	}

	public void setPodeAlterarMovimentacao(boolean podeAlterarMovimentacao) {
		this.podeAlterarMovimentacao = podeAlterarMovimentacao;
	}
	
}
