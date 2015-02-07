package com.consisti.sisgesc.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class ServicoAluno extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_SERVICO_ALUNO")
	@Column (name = "ID_SERVICO_ALUNO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = ServicosEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_SERVICOALUNO_SERVICO")
	@JoinColumn (name = "ID_SERVICO", nullable=false)
	private Servicos servico;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", nullable=true, length=200)
	private String observacao;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=17)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_SERVICOS_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=true)
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Servicos getServico() {
		return servico;
	}

	public void setServico(Servicos servico) {
		this.servico=servico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Date getDataCadastro() {
		if( dataCadastro == null ){
			setDataCadastro(new Date());
		}
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
