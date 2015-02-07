package com.consisti.sisgesc.entidade.estoque;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.entidade.TurmaEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="MOVIMENTO")
@SequenceGenerator(name="SE_MOVIMENTO", sequenceName="SE_MOVIMENTO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="observacao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="MovimentoEntity.querySel", query="select new MovimentoEntity(obj.id, obj.qtdeSaida, obj.valorUnitario, obj.dataMovimentacao, obj.valorTotal, obj.turma.id , obj.turma.descricao) from MovimentoEntity obj left outer join obj.turma left outer join obj.fornecedor order by obj.id asc"),
	@NamedQuery(name="MovimentoEntity.queryMan", query="from MovimentoEntity obj"),
	@NamedQuery(name="MovimentoEntity.querySel2", query="select new MovimentoEntity(obj.id, obj.qtdeEntrada, obj.dataMovimentacao, obj.valorUnitario, obj.valorTotal, obj.fornecedor.id , obj.fornecedor.nome, prod.id, prod.descricao) " +
			                                           "from MovimentoEntity obj " +
			                                           "left outer join obj.fornecedor " +
			                                           "left outer join obj.estoque estoq " +
			                                           "left outer join estoq.produtoMaterial prod " +
			                                           "order by obj.id asc"),
	@NamedQuery(name="MovimentoEntity.querySelLookup", query="select new MovimentoEntity (obj.id, obj.observacao) from MovimentoEntity obj where obj.id = ? order by obj.id asc")
})
public class MovimentoEntity extends Movimento {

	private transient String fornecedorAuxLookup;

	private transient String turmaAuxLookup;
	
	@Transient
	private BigDecimal valorEstoqueTemporario;
	
	@Transient
	private Long saldoTemporario;
	
	@Transient
	private Long idProduto;
	
	@Transient
	private String descricaoProduto;
 	
    /*
     * Construtor padrão
     */
    public MovimentoEntity() {
    }
	public MovimentoEntity(Long id, String observacao) {
		this.setId(id);
		this.setObservacao(observacao);
	}
	@Override
	public String toString() {
		return getIdAux();
	}


	public void setTurmaAuxLookup(String turmaAuxLookup) {
		this.turmaAuxLookup=turmaAuxLookup;
	}


	public void setFornecedorAuxLookup(String fornecedorAuxLookup) {
		this.fornecedorAuxLookup=fornecedorAuxLookup;
	}
	
	public String getValorUnitarioStr(){
		return NumberFormat.getCurrencyInstance().format(getValorUnitario());
	}
	
	public String getValorTotalStr(){
		return NumberFormat.getCurrencyInstance().format(getValorTotal());
	}

	public MovimentoEntity(Long id, Long qtdeEntrada, Date dataMovimentacao, BigDecimal valorUnitario, BigDecimal valorTotal, Long fornecedorId, String fornecedorNome, Long idProduto, String descricaoProduto) {
		setId(id);
		setQtdeEntrada(qtdeEntrada);
		setValorUnitario(valorUnitario);
		setDataMovimentacao(dataMovimentacao);
		setValorTotal(valorTotal);
		if (getFornecedor() == null){
			setFornecedor(new FornecedorEntity());
		}
		getFornecedor().setId(fornecedorId);
		getFornecedor().setNome(fornecedorNome);
		setIdProduto(idProduto);
		setDescricaoProduto(descricaoProduto);
	}
	public MovimentoEntity(Long id, Long qtdeSaida, java.math.BigDecimal valorUnitario, java.util.Date dataMovimentacao, java.math.BigDecimal valorTotal, Long turmaId, String turmaDescricao) {
		setId(id);
		setQtdeSaida(qtdeSaida);
		setValorUnitario(valorUnitario);
		setDataMovimentacao(dataMovimentacao);
		setValorTotal(valorTotal);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
	}
	
	public BigDecimal getValorEstoqueTemporario() {
		return valorEstoqueTemporario;
	}
	public void setValorEstoqueTemporario(BigDecimal valorEstoqueTemporario) {
		this.valorEstoqueTemporario = valorEstoqueTemporario;
	}
	public Long getSaldoTemporario() {
		return saldoTemporario;
	}
	public void setSaldoTemporario(Long saldoTemporario) {
		this.saldoTemporario = saldoTemporario;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
}
