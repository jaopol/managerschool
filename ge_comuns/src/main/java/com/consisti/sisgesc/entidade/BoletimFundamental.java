package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
import java.util.Date;

import com.consisti.sisgesc.dominio.SituacaoBoletim;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class BoletimFundamental extends AppBaseEntity {

	
	@OneToMany (targetEntity = BoletimFundamentalDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="boletimFundamental")
	@ForeignKey(name="FK_BOLETIMFUNDAMENTALDET_BOLETIMFUNDAMENTAL")
	@Valid
	@JoinColumn (name = "ID_BOLETIM_FUNDAMENTAL")
	private List<BoletimFundamentalDet> boletimFundamentalDet;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_BOLETIM_FUNDAMENTAL")
	@Column (name = "ID_BOLETIM_FUNDAMENTAL", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_BOLETIMFUNDAMENTAL_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@Column (name = "ANO_LETIVO", nullable=false, length=4)
	private Integer anoLetivo;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "SITUACAO_BOLETIM", nullable=false, length=1)
	private SituacaoBoletim situacaoBoletim;
	
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_BOLETIMFUNDAMENTAL_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;
	
	@Column (name = "CARGA_HORARIA", nullable=false, length=6)
	private String cargaHoraria;
	
	@Column (name = "DIAS_LETIVOS", nullable=false, length=5)
	private Long diasLetivos;
	
	@Column (name = "DATA_GERACAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataGeracao;
	
	@Column (name = "TOTAL_NOTA", nullable=false, length=11, precision=2)
	private BigDecimal totalNota;
	
	@Column (name = "TOTAL_AULA_DADA", nullable=false, length=4 )
	private Integer totalAulaDada;
	
	@Column (name = "TOTAL_FALTAS", nullable=false, length=4 )
	private Integer totalFaltas;
	
	@Column (name = "PERC_FALTAS", nullable=false, length=11, precision=2)
	private BigDecimal percFaltas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo=anoLetivo;
	}

	public SituacaoBoletim getSituacaoBoletim() {
		return situacaoBoletim;
	}

	public void setSituacaoBoletim(SituacaoBoletim situacaoBoletim) {
		this.situacaoBoletim=situacaoBoletim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria=cargaHoraria;
	}

	public Long getDiasLetivos() {
		return diasLetivos;
	}

	public void setDiasLetivos(Long diasLetivos) {
		this.diasLetivos=diasLetivos;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao=dataGeracao;
	}

	public BigDecimal getTotalNota() {
		return totalNota;
	}

	public void setTotalNota(BigDecimal totalNota) {
		this.totalNota=totalNota;
	}

	public Integer getTotalAulaDada() {
		return totalAulaDada;
	}

	public void setTotalAulaDada(Integer totalAulaDada) {
		this.totalAulaDada=totalAulaDada;
	}

	public Integer getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(Integer totalFaltas) {
		this.totalFaltas=totalFaltas;
	}

	public BigDecimal getPercFaltas() {
		return percFaltas;
	}

	public void setPercFaltas(BigDecimal percFaltas) {
		this.percFaltas=percFaltas;
	}

	public List<BoletimFundamentalDet> getBoletimFundamentalDet() {
		return boletimFundamentalDet;
	}

	public void setBoletimFundamentalDet(List<BoletimFundamentalDet> boletimFundamentalDet) {
		this.boletimFundamentalDet=boletimFundamentalDet;
	}

}
