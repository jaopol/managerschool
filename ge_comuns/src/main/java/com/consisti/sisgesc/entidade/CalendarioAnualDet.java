package com.consisti.sisgesc.entidade;

import java.util.Date;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
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
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CalendarioAnualDet extends AppBaseEntity {

	@OneToMany (targetEntity = CalendarioAnualSubDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="calendarioAnualDet")
	@ForeignKey(name="FK_CALENDARIOANUALSUBDET_CALENDARIOANUALDET")
	@Valid
	@JoinColumn (name = "ID_CALENDARIO_ANUAL_DET")
	private List<CalendarioAnualSubDet> calendarioAnualSubDet;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CALENDARIO_ANUAL_DET")
	@Column (name = "ID_CALENDARIO_ANUAL_DET", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = CalendarioAnualEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CALENDARIOANUALDET_CALENDARIOANUAL")
	@JoinColumn (name = "ID_CALENDARIO_ANUAL", nullable=false)
	private CalendarioAnual calendarioAnual;

	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "INICIO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	
	@Column (name = "FIM", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fim;
	
	@Column (name = "QTDE_DIA_LETIVO", nullable=false, length=5)
	private Integer qtdeDiaLetivo;
	
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

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio=inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim=fim;
	}

	public Integer getQtdeDiaLetivo() {
		return qtdeDiaLetivo;
	}

	public void setQtdeDiaLetivo(Integer qtdeDiaLetivo) {
		this.qtdeDiaLetivo=qtdeDiaLetivo;
	}

	public CalendarioAnual getCalendarioAnual() {
		return calendarioAnual;
	}

	public void setCalendarioAnual(CalendarioAnual calendarioAnual) {
		this.calendarioAnual=calendarioAnual;
	}

	public List<CalendarioAnualSubDet> getCalendarioAnualSubDet() {
		return calendarioAnualSubDet;
	}

	public void setCalendarioAnualSubDet(List<CalendarioAnualSubDet> calendarioAnualSubDet) {
		this.calendarioAnualSubDet=calendarioAnualSubDet;
	}

}
