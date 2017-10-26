package com.consisti.sisgesc.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Contrato extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CONTRATO")
	@Column (name = "ID_CONTRATO", nullable=false, length=5)
	private Long id;
	
	@Column (name="DATA_INICI0_CONTRATO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioContrato;
	
	@Column (name="DATA_FIM_CONTRATO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimContrato;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CONTRATO_ALUNO")
	@JoinColumn (name = "ID_ALUNO")
	private Aluno aluno;
	
	@Column (name = "ano_contrato", nullable=false)
	private Long anoContrato;
	
	@Lob
	@Column (name = "contrato_aluno", columnDefinition="blob(6M)")
	private byte[] contrato; 
	
	@OneToMany (targetEntity = AditivoEntity.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="contrato")
	@ForeignKey(name="FK_ADITIVO_ALUNO")
	@Valid
	@JoinColumn (name = "ID_CONTRATO")
	private List<AditivoEntity> adtivoAluno;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS_CONTRATO", nullable=false, length=1)
	private AtivoInativo statusContrato;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public byte[] getContrato() {
		return contrato;
	}

	public void setContrato(byte[] contrato) {
		this.contrato = contrato;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Long getAnoContrato() {
		return anoContrato;
	}

	public void setAnoContrato(Long anoContrato) {
		this.anoContrato = anoContrato;
	}

	public List<AditivoEntity> getAdtivoAluno() {
		return adtivoAluno;
	}

	public void setAdtivoAluno(List<AditivoEntity> adtivoAluno) {
		this.adtivoAluno = adtivoAluno;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public AtivoInativo getStatusContrato() {
		return statusContrato;
	}

	public void setStatusContrato(AtivoInativo statusContrato) {
		this.statusContrato = statusContrato;
	}

}
