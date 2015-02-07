package com.consisti.sisgesc.entidade;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.validator.Valid;
import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CalendarioAnual extends AppBaseEntity {

	@OneToMany (targetEntity = CalendarioAnualDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="calendarioAnual")
	@ForeignKey(name="FK_CALENDARIOANUALDET_CALENDARIOANUAL")
	@Valid
	@JoinColumn (name = "ID_CALENDARIO_ANUAL")
	private List<CalendarioAnualDet> calendarioAnualDet;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CALENDARIO_ANUAL")
	@Column (name = "ID_CALENDARIO_ANUAL", nullable=false, length=5)
	private Long id;
	
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "TOTAL_DIAS_LETIVOS", nullable=false, length=5)
	private Integer totalDiasLetivos;
	
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

	public Integer getTotalDiasLetivos() {
		return totalDiasLetivos;
	}

	public void setTotalDiasLetivos(Integer totalDiasLetivos) {
		this.totalDiasLetivos=totalDiasLetivos;
	}

	public List<CalendarioAnualDet> getCalendarioAnualDet() {
		return calendarioAnualDet;
	}

	public void setCalendarioAnualDet(List<CalendarioAnualDet> calendarioAnualDet) {
		this.calendarioAnualDet=calendarioAnualDet;
	}

}
