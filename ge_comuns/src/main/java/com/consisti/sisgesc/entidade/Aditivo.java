package com.consisti.sisgesc.entidade;

import java.util.Date;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Aditivo extends AppBaseEntity {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_ADITIVO")
	@Column (name = "ID_ADITIVO", nullable=false, length=5)
	private Long id;
	
	@SuppressWarnings("unchecked")
	@ManyToOne (targetEntity = ContratoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_ADITIVO_CONTRATO")
	@JoinColumn (name = "ID_CONTRATO", nullable=false)
	private ContratoEntity contrato;
	
	@Column (name = "DATA_CRIACAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Lob
	@Column (name = "adtivo", columnDefinition="blob(6M)")
	private byte [] aditivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public ContratoEntity getContrato() {
		return contrato;
	}

	public void setContrato(ContratoEntity contrato) {
		this.contrato=contrato;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao=dataCriacao;
	}

	public byte[] getAditivo() {
		return aditivo;
	}

	public void setAditivo(byte[] aditivo) {
		this.aditivo = aditivo;
	}

}
