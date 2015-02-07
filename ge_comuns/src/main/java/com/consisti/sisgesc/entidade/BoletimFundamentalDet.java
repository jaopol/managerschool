package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class BoletimFundamentalDet extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_BOLETIM_FUNDAMENTAL_DET")
	@Column (name = "ID_BOLETIM_FUNDAMENTAL_DET", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = BoletimFundamentalEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_BOLETIMFUNDAMENTALDET_BOLETIMFUNDAMENTAL")
	@JoinColumn (name = "ID_BOLETIM_FUNDAMENTAL", nullable=false)
	private BoletimFundamental boletimFundamental;

	@ManyToOne (targetEntity = DisciplinasEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_BOLETIMFUNDAMENTALDET_DISCIPLINA")
	@JoinColumn (name = "ID_DISCIPLINA", nullable=false)
	private Disciplinas disciplina;
	
	@Column (name = "BIMESTRE", nullable=false, length=1)
	private Integer bimestre;
	
	@Column (name = "NOTA", length=5, precision=2)
	private BigDecimal nota;
	
	@Column (name = "FALTAS", length=5)
	private Integer faltas;
	
	@Column (name = "AULAS_DADAS", length=5)
	private Integer aulasDadas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina=disciplina;
	}

	public Integer getBimestre() {
		return bimestre;
	}

	public void setBimestre(Integer bimestre) {
		this.bimestre=bimestre;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota=nota;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas=faltas;
	}

	public Integer getAulasDadas() {
		return aulasDadas;
	}

	public void setAulasDadas(Integer aulasDadas) {
		this.aulasDadas=aulasDadas;
	}

	public BoletimFundamental getBoletimFundamental() {
		return boletimFundamental;
	}

	public void setBoletimFundamental(BoletimFundamental boletimFundamental) {
		this.boletimFundamental=boletimFundamental;
	}

}
