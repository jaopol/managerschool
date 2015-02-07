package com.consisti.sisgesc.entidade;

import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@MappedSuperclass
@PlcValidacaoUnificada
@SuppressWarnings("serial")
public abstract class SaudeAluno extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_SAUDE_ALUNO")
	@Column (name = "ID_SAUDE_ALUNO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@ForeignKey(name="FK_SAUDEALUNO_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;

	@Enumerated(EnumType.STRING)
	@Column (name = "PROBL_GRAVE_SAUDE", nullable=false, length=1)
	private PlcSimNao problGraveSaude;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_PROBL_GRAVE_SAUDE", length=50)
	private String qualProblGraveSaude;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "USO_CONSTANTE_REMEDIO_CONTROLADO", nullable=false, length=1)
	private PlcSimNao usoConstanteRemedioControlado;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_REMEDIO_CONTROLADO", length=50)
	private String qualRemedioControlado;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PLANO_SAUDE", nullable=false, length=1)
	private PlcSimNao planoSaude;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_PLANO_SAUDE", length=50)
	private String qualPlanoSaude;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ALERGIA", nullable=false, length=1)
	private PlcSimNao alergia;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_ALERGIA", length=50)
	private String qualAlergia;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_REMEDIO_CASO_DOR_CAB_FEBRE", nullable=false, length=50)
	private String qualRemedioCasoDorCabFebre;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DOSAGEM_DOR_REMEDIO_CASO_DOR_CAB_FEBRE", nullable=false, length=50)
	private String dosagemDorRemedioCasoDorCabFebre;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "FAZ_TRATAMENTO_SAUDE", nullable=false, length=1)
	private PlcSimNao fazTratamentoSaude;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "QUAL_TRATAMENTO_SAUDE", length=50)
	private String qualTratamentoSaude;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public PlcSimNao getProblGraveSaude() {
		return problGraveSaude;
	}

	public void setProblGraveSaude(PlcSimNao problGraveSaude) {
		this.problGraveSaude=problGraveSaude;
	}

	public String getQualProblGraveSaude() {
		return qualProblGraveSaude;
	}

	public void setQualProblGraveSaude(String qualProblGraveSaude) {
		this.qualProblGraveSaude=qualProblGraveSaude;
	}

	public PlcSimNao getUsoConstanteRemedioControlado() {
		return usoConstanteRemedioControlado;
	}

	public void setUsoConstanteRemedioControlado(PlcSimNao usoConstanteRemedioControlado) {
		this.usoConstanteRemedioControlado=usoConstanteRemedioControlado;
	}

	public String getQualRemedioControlado() {
		return qualRemedioControlado;
	}

	public void setQualRemedioControlado(String qualRemedioControlado) {
		this.qualRemedioControlado=qualRemedioControlado;
	}

	public PlcSimNao getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlcSimNao planoSaude) {
		this.planoSaude=planoSaude;
	}

	public String getQualPlanoSaude() {
		return qualPlanoSaude;
	}

	public void setQualPlanoSaude(String qualPlanoSaude) {
		this.qualPlanoSaude=qualPlanoSaude;
	}

	public PlcSimNao getAlergia() {
		return alergia;
	}

	public void setAlergia(PlcSimNao alergia) {
		this.alergia=alergia;
	}

	public String getQualAlergia() {
		return qualAlergia;
	}

	public void setQualAlergia(String qualAlergia) {
		this.qualAlergia=qualAlergia;
	}

	public String getQualRemedioCasoDorCabFebre() {
		return qualRemedioCasoDorCabFebre;
	}

	public void setQualRemedioCasoDorCabFebre(String qualRemedioCasoDorCabFebre) {
		this.qualRemedioCasoDorCabFebre=qualRemedioCasoDorCabFebre;
	}

	public String getDosagemDorRemedioCasoDorCabFebre() {
		return dosagemDorRemedioCasoDorCabFebre;
	}

	public void setDosagemDorRemedioCasoDorCabFebre(String dosagemDorRemedioCasoDorCabFebre) {
		this.dosagemDorRemedioCasoDorCabFebre=dosagemDorRemedioCasoDorCabFebre;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno=aluno;
	}

	public PlcSimNao getFazTratamentoSaude() {
		return fazTratamentoSaude;
	}

	public void setFazTratamentoSaude(PlcSimNao fazTratamentoSaude) {
		this.fazTratamentoSaude = fazTratamentoSaude;
	}

	public String getQualTratamentoSaude() {
		return qualTratamentoSaude;
	}

	public void setQualTratamentoSaude(String qualTratamentoSaude) {
		this.qualTratamentoSaude = qualTratamentoSaude;
	}

}
