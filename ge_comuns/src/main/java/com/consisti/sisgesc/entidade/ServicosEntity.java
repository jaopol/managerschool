package com.consisti.sisgesc.entidade;


import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.PeriodoServico;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="SERVICOS")
@SequenceGenerator(name="SE_SERVICOS", sequenceName="SE_SERVICOS")
@AccessType("field")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ServicosEntity.querySel2", query="select new ServicosEntity(obj.id) from ServicosEntity obj order by obj.id asc"),
	@NamedQuery(name="ServicosEntity.queryMan", query="from ServicosEntity obj"),
	@NamedQuery(name="ServicosEntity.querySel", query="select new ServicosEntity(obj.id, obj.descricao, obj.dataCadastro, obj.status, obj.valorServico, obj.periodoServico) from ServicosEntity obj order by obj.descricao asc"),
	@NamedQuery(name="ServicosEntity.querySelLookup", query="select new ServicosEntity (obj.id, obj.descricao, obj.periodoServico, obj.valorServico) from ServicosEntity obj where obj.id = ? order by obj.id asc")
})
public class ServicosEntity extends Servicos {
 	
	private transient String valorServicoStr;
	
    /*
     * Construtor padrão
     */
    public ServicosEntity() {
    }
    
	public ServicosEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	
	public ServicosEntity(Long id, String descricao, PeriodoServico periodo, BigDecimal valorServico) {
		this.setId(id);
		this.setDescricao(descricao);
		this.setPeriodoServico(periodo);
		this.setValorServico(valorServico);
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}

	public ServicosEntity(Long id, String descricao, java.util.Date dataCadastro, AtivoInativo status, java.math.BigDecimal valorServico, PeriodoServico periodoServico) {
		setId(id);
		setDescricao(descricao);
		setDataCadastro(dataCadastro);
		setStatus(status);
		setValorServico(valorServico);
		setPeriodoServico(periodoServico);
	}

	public ServicosEntity(Long id) {
		setId(id);
	}

	public ServicosEntity(BigDecimal valorServico) {
		setValorServico(valorServico);
	}

	public String getValorServicoStr() {
		if (this.getValorServico()!=null){
			return NumberFormat.getCurrencyInstance().format(this.getValorServico());
		}
		return valorServicoStr;
	}

	public void setValorServicoStr(String valorServicoStr) {
		this.valorServicoStr = valorServicoStr;
	}

}
