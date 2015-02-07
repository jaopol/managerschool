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
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.AbertoFechado;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class RegistroNotasFundamental extends AppBaseEntity {

	
	@OneToMany (targetEntity = RegistroNotasFundamentalDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="registroNotasFundamental")
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALDET_REGISTRONOTASFUNDAMENTAL")
	@Valid
	@JoinColumn (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL")
	private List<RegistroNotasFundamentalDet> registroNotasFundamentalDet;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_REGISTRO_NOTAS_FUNDAMENTAL")
	@Column (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL", nullable=false, length=5)
	private Long id;

	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTAL_PROFESSOR")
	@JoinColumn (name = "ID_PROFESSOR", nullable=false)
	private Funcionario professor;
	
	@ManyToOne (targetEntity = DisciplinasEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTAL_DISCIPLINA")
	@JoinColumn (name = "ID_DISCIPLINA", nullable=false)
	private Disciplinas disciplina;
	
	@Column (name = "ANO_LETIVO", nullable=false, length=4)
	private Integer anoLetivo;
	
	@Column (name = "BIMESTRE", nullable=false, length=1)
	private Integer bimestre;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=17)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "STATUS", nullable=false, length=1 )
	private AbertoFechado status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor=professor;
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina=disciplina;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo=anoLetivo;
	}

	public Integer getBimestre() {
		return bimestre;
	}

	public void setBimestre(Integer bimestre) {
		this.bimestre=bimestre;
	}

	public List<RegistroNotasFundamentalDet> getRegistroNotasFundamentalDet() {
		return registroNotasFundamentalDet;
	}

	public void setRegistroNotasFundamentalDet(List<RegistroNotasFundamentalDet> registroNotasFundamentalDet) {
		this.registroNotasFundamentalDet=registroNotasFundamentalDet;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public AbertoFechado getStatus() {
		return status;
	}

	public void setStatus(AbertoFechado status) {
		this.status = status;
	}

}
