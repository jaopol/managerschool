package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;

import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public abstract class RegistroNotasFundamentalSubDet extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET")
	@Column (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL_SUB_DET", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = RegistroNotasFundamentalDetEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALSUBDET_REGISTRONOTASFUNDAMENTALDET")
	@JoinColumn (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL_DET", nullable=false)
	private RegistroNotasFundamentalDet registroNotasFundamentalDet;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALSUBDET_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@Column (name = "NOTA_TRABALHO", nullable=false, length=5)
	private BigDecimal notaTrabalho;
	
	@Column (name = "NOTA_PROVA", nullable=false, length=5)
	private BigDecimal notaProva;
	
	@Column (name = "AVALIACAO", nullable=false, length=5)
	private BigDecimal avaliacao;
	
	@Column (name = "CONCEITO", nullable=false, length=5)
	private BigDecimal conceito;
	
	@Column (name = "AULAS_DADAS", nullable=false, length=5)
	private Integer aulasDadas;
	
	@Column (name = "FALTAS", nullable=false, length=5)
	private Integer faltas;
	
	@Column (name = "TOTAL", nullable=false, length=5)
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "NOTA_REPROVADA", nullable=false, length=1)
	private PlcSimNao notaReprovada = PlcSimNao.N;
	
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

	public BigDecimal getNotaTrabalho() {
		return notaTrabalho;
	}

	public void setNotaTrabalho(BigDecimal notaTrabalho) {
		this.notaTrabalho=notaTrabalho;
	}

	public BigDecimal getNotaProva() {
		return notaProva;
	}

	public void setNotaProva(BigDecimal notaProva) {
		this.notaProva=notaProva;
	}

	public Integer getAulasDadas() {
		return aulasDadas;
	}

	public void setAulasDadas(Integer aulasDadas) {
		this.aulasDadas=aulasDadas;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas=faltas;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total=total;
	}

	public RegistroNotasFundamentalDet getRegistroNotasFundamentalDet() {
		return registroNotasFundamentalDet;
	}

	public void setRegistroNotasFundamentalDet(RegistroNotasFundamentalDet registroNotasFundamentalDet) {
		this.registroNotasFundamentalDet=registroNotasFundamentalDet;
	}

	public BigDecimal getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(BigDecimal avaliacao) {
		this.avaliacao = avaliacao;
	}

	public BigDecimal getConceito() {
		return conceito;
	}

	public void setConceito(BigDecimal conceito) {
		this.conceito = conceito;
	}

	public PlcSimNao getNotaReprovada() {
		return notaReprovada;
	}

	public void setNotaReprovada(PlcSimNao notaReprovada) {
		this.notaReprovada = notaReprovada;
	}

}
