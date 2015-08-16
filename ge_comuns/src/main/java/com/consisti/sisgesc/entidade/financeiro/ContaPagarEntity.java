package com.consisti.sisgesc.entidade.financeiro;


import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.entidade.Fornecedor;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.powerlogic.jcompany.comuns.anotacao.PlcIoC;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CONTA_PAGAR")
@SequenceGenerator(name="SE_CONTA_PAGAR", sequenceName="SE_CONTA_PAGAR")
@AccessType("field")
@PlcIoC(nomeClasseBC="com.consisti.sisgesc.modelo.ContaPagarManager")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ContaPagarEntity.queryMan", query="from ContaPagarEntity obj"),
	@NamedQuery(name="ContaPagarEntity.querySel", query="select new ContaPagarEntity(obj.id, obj.favorecido.id , obj.favorecido.nome, obj.planoContas.id , obj.planoContas.descricao, obj.formaPagamento.id , obj.formaPagamento.descricao, obj.dataVencimento, obj.dataInclusao, obj.valorPagar, obj.numeroDocumento, obj.contaPaga) from ContaPagarEntity obj left outer join obj.favorecido left outer join obj.planoContas left outer join obj.formaPagamento order by obj.dataVencimento desc"),
	@NamedQuery(name="ContaPagarEntity.querySelLookup", query="select new ContaPagarEntity (obj.id, obj.favorecido) from ContaPagarEntity obj where obj.id = ? order by obj.id asc")
})
public class ContaPagarEntity extends ContaPagar {
 	
	private transient String contaPagaDesc;
	
    /*
     * Construtor padrão
     */
    public ContaPagarEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getFavorecidoStr() {
    	return getFavorecido()!=null? getFavorecido().getIdAux() : null;
    }
    
    public void setFavorecidoStr(String favorecidoStr) {
    	if (favorecidoStr != null && favorecidoStr.trim().length() > 0) {
    		Long id = Long.valueOf(favorecidoStr);
    		if (getFavorecido()==null || !id.equals(getFavorecido().getId())) {
    			com.consisti.sisgesc.entidade.Fornecedor obj = new com.consisti.sisgesc.entidade.FornecedorEntity();
    			obj.setId(id);
    			this.setFavorecido(obj);
    		}
    	} else {
    		this.setFavorecido(null);
    	}
    }

	public ContaPagarEntity(Long id, Fornecedor favorecido) {
		this.setId(id);
		this.setFavorecido(favorecido);
	}

	@Override
	public String toString() {
		return getFavorecidoStr();
	}

	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Fornecedor.class,com.consisti.sisgesc.entidade.financeiro.PlanoContas.class,com.consisti.sisgesc.entidade.financeiro.FormaPagamento.class};
	}

	public ContaPagarEntity(Long id, Long favorecidoId, String favorecidoNome, Long planoContasId, String planoContasDescricao, Long formaPagamentoId, String formaPagamentoDescricao, java.util.Date dataVencimento, java.util.Date dataInclusao, java.math.BigDecimal valorPagar, Long numeroDocumento, PlcSimNao contaPaga) {
		setId(id);
		if (getFavorecido() == null){
			setFavorecido(new FornecedorEntity());
		}
		getFavorecido().setId(favorecidoId);
		getFavorecido().setNome(favorecidoNome);
		if (getPlanoContas() == null){
			setPlanoContas(new PlanoContasEntity());
		}
		getPlanoContas().setId(planoContasId);
		getPlanoContas().setDescricao(planoContasDescricao);
		if (getFormaPagamento() == null){
			setFormaPagamento(new FormaPagamentoEntity());
		}
		getFormaPagamento().setId(formaPagamentoId);
		getFormaPagamento().setDescricao(formaPagamentoDescricao);
		setDataVencimento(dataVencimento);
		setDataInclusao(dataInclusao);
		setValorPagar(valorPagar);
		setNumeroDocumento(numeroDocumento);
		if( PlcSimNao.S.equals( contaPaga ) )
			setContaPagaDesc("SIM");
		else
			setContaPagaDesc("NÃO");	
	}
	
	/**
	 * Utilizado em ContaPagarDAO.recuperaListaContasAPagar
	 * @param id
	 * @param favorecidoNome
	 * @param formaPagamentoDescricao
	 * @param valorPagar
	 */
	public ContaPagarEntity(Long id, BigDecimal valorPagar, String favorecidoNome, String formaPagamentoDescricao ) {
		setId(id);
		if (getFavorecido() == null){
			setFavorecido(new FornecedorEntity());
		}
		getFavorecido().setNome(favorecidoNome);
		if (getPlanoContas() == null){
			setPlanoContas(new PlanoContasEntity());
		}
		if (getFormaPagamento() == null){
			setFormaPagamento(new FormaPagamentoEntity());
		}
		getFormaPagamento().setDescricao(formaPagamentoDescricao);
		setValorPagar(valorPagar);
	}
	
	public String getContaPagaDesc() {
		return contaPagaDesc;
	}
	public void setContaPagaDesc(String contaPagaDesc) {
		this.contaPagaDesc = contaPagaDesc;
	}
	
	@Transient
	public String getValorTotalFormatado(){
		if (getValorPagar()!=null){
			return NumberFormat.getCurrencyInstance().format(getValorPagar());
		}
		return "";
	}
}
