package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import java.util.Date;

import com.powerlogic.jcompany.dominio.valida.PlcFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Evento extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_EVENTO")
	@Column (name = "ID_EVENTO", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "VALOR", nullable=false, length=11, precision=11, scale=2)
	private BigDecimal valor;
	
	@Column (name = "NUMERO_PARCELA", nullable=false, length=3)
	private Integer numeroParcela;
	
	@Column (name = "DATA_EVENTO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEvento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor=valor;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela=numeroParcela;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento=dataEvento;
	}

}
