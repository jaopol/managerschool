package com.consisti.sisgesc.entidade;

import java.util.Date;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;
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
public abstract class CronogramaTurma extends PlcBaseVO {

	
	@OneToMany (targetEntity = CronogramaTurmaDisciplinaEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="cronogramaTurma")
	@ForeignKey(name="FK_CRONOGRAMATURMADISCIPLINA_CRONOGRAMATURMA")
	@Valid
	@JoinColumn (name = "ID_CRONOGRAMA_TURMA")
	private List<CronogramaTurmaDisciplina> cronogramaTurmaDisciplina;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CRONOGRAMA_TURMA")
	@Column (name = "ID_CRONOGRAMA_TURMA", nullable=false, length=5)
	private Long id;
	
	@Version
	@Column (name = "VERSAO", nullable=false, length=5)
	private int versao;
	
	@Column (name = "DATA_ULT_ALTERACAO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltAlteracao = new Date();
	
	@Column (name = "USUARIO_ULT_ALTERACAO", nullable=false)
	private String usuarioUltAlteracao = "";
	
	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_CRONOGRAMATURMA_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", nullable=true, length=200)
	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao=versao;
	}

	public Date getDataUltAlteracao() {
		return dataUltAlteracao;
	}

	public void setDataUltAlteracao(Date dataUltAlteracao) {
		this.dataUltAlteracao=dataUltAlteracao;
	}

	public String getUsuarioUltAlteracao() {
		return usuarioUltAlteracao;
	}

	public void setUsuarioUltAlteracao(String usuarioUltAlteracao) {
		this.usuarioUltAlteracao=usuarioUltAlteracao;
	}

	public List<CronogramaTurmaDisciplina> getCronogramaTurmaDisciplina() {
		return cronogramaTurmaDisciplina;
	}

	public void setCronogramaTurmaDisciplina(List<CronogramaTurmaDisciplina> cronogramaTurmaDisciplina) {
		this.cronogramaTurmaDisciplina=cronogramaTurmaDisciplina;
	}

}
