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


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CalendarioAnualSubDet extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CALENDARIO_ANUAL_SUB_DET")
	@Column (name = "ID_CALENDARIO_ANUAL_SUB_DET", nullable=false, length=5)
	private Long id;

	@ManyToOne (targetEntity = CalendarioAnualDetEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CALENDARIOANUALSUBDET_CALENDARIOANUALDET")
	@JoinColumn (name = "ID_CALENDARIO_ANUAL_DET", nullable=false)
	private CalendarioAnualDet calendarioAnualDet;
	
	/*@ManyToOne (targetEntity = CadastroFeriadoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CALENDARIOANUALSUBDET_FERIADOS")
	@JoinColumn (name = "ID_FERIADOS")
	private CadastroFeriado feriados;*/
	
	@Column (name = "DATA_FERIADO", length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFeriado;
	
	@Column (name = "DATA_EMENDA", length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmenda;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	/*public CadastroFeriado getFeriados() {
		return feriados;
	}

	public void setFeriados(CadastroFeriado feriados) {
		this.feriados=feriados;
	}
*/
	public Date getDataEmenda() {
		return dataEmenda;
	}

	public void setDataEmenda(Date dataEmenda) {
		this.dataEmenda=dataEmenda;
	}

	public CalendarioAnualDet getCalendarioAnualDet() {
		return calendarioAnualDet;
	}

	public void setCalendarioAnualDet(CalendarioAnualDet calendarioAnualDet) {
		this.calendarioAnualDet=calendarioAnualDet;
	}

	public Date getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(Date dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

}
