package com.consisti.sisgesc.entidade;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.entidade.financeiro.Banco;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="MOVIMENTO_DIA")
@SequenceGenerator(name="SE_MOVIMENTO_DIA", sequenceName="SE_MOVIMENTO_DIA")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="MovimentoDiaEntity.querySel", query="select new MovimentoDiaEntity(obj.id) from MovimentoDiaEntity obj order by obj.id asc"),
	@NamedQuery(name="MovimentoDiaEntity.querySelLookup", query="select new MovimentoDiaEntity (obj.id, obj.saldoDia) from MovimentoDiaEntity obj where obj.id = ? order by obj.id asc")
})
public class MovimentoDiaEntity extends MovimentoDia {
 	
	private transient List<ContaPagar> contasPagar;
	
	private transient List<ContaReceber> contasReceber;
	
	private transient Banco banco;
	
	private transient String saldoDiaAnteriorStr;
	
    /*
     * Construtor padrão
     */
    public MovimentoDiaEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
	private static final DecimalFormat df = (DecimalFormat)DecimalFormat.getNumberInstance(new Locale("pt","BR"));
	static {df.setMinimumFractionDigits(2);}



	@javax.persistence.Transient
    public String getSaldoDiaStr()   {
       if (getSaldoDia() != null) 
           return NumberFormat.getCurrencyInstance().format(getSaldoDia());
       else
           return "";
    }
	
	@javax.persistence.Transient
    public String getSaldoTotalStr()   {
       if (getSaldoTotal() != null) 
           return NumberFormat.getCurrencyInstance().format(getSaldoTotal());
       else
           return "";
    }
	
    public void setSaldoDiaStr( String saldoDiaStr )   {
        if (saldoDiaStr != null && saldoDiaStr.length()>0) {
        		try{
	    			Number number = (Number)df.parse(saldoDiaStr);
	    			setSaldoDia(new BigDecimal(number.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP));
	    		}catch(Exception e){
	    			setSaldoDia(null);
	    		}
        } else {
            setSaldoDia(null);
        }
    }

	public MovimentoDiaEntity(Long id, java.math.BigDecimal saldoDia) {
		this.setId(id);
		this.setSaldoDia(saldoDia);
	}
	@Override
	public String toString() {
		return getSaldoDiaStr();
	}

	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.financeiro.ContaPagar.class,com.consisti.sisgesc.entidade.financeiro.ContaReceber.class};
	}

	public MovimentoDiaEntity(Long id) {
		setId(id);
	}
	public List<ContaPagar> getContasPagar() {
		return contasPagar;
	}
	public void setContasPagar(List<ContaPagar> contasPagar) {
		this.contasPagar = contasPagar;
	}
	public List<ContaReceber> getContasReceber() {
		return contasReceber;
	}
	public void setContasReceber(List<ContaReceber> contasReceber) {
		this.contasReceber = contasReceber;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getSaldoDiaAnteriorStr() {
	   if (getSaldoDiaAnterior() != null) 
	        return NumberFormat.getCurrencyInstance().format(getSaldoDiaAnterior());
       else
           return "";
	}

}
