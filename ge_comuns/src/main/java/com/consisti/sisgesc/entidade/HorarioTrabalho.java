package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;

import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
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


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class HorarioTrabalho extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_HORARIO_TRABALHO")
	@Column (name = "ID_HORARIO_TRABALHO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_HORARIOTRABALHO_FUNCIONARIO")
	@JoinColumn (name = "ID_FUNCIONARIO", nullable=false)
	private Funcionario funcionario;

	@Column (name = "HORA_ENTRADA", nullable=false, length=5)
	private String horaEntrada;
	
	@Column (name = "HORA_SAIDA", nullable=false, length=5)
	private String horaSaida;
	
	@Column (name = "INTERVALO_ALMOCO_INICIO", nullable=false, length=5)
	private String intervaloAlmocoInicio;
	
	@Column (name = "INTERVALO_ALMOCO_FIM", nullable=false, length=5)
	private String intervaloAlmocoFim;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TRABALHA_SABADO", length=1)
	private PlcSimNao trabalhaSabado;
	
	@Column (name = "ENTRADA_SABADO", length=5)
	private String entradaSabado;
	
	@Column (name = "SAIDA_SABADO", length=5)
	private String saidaSabado;
	
	@Column (name = "INTERVALO_ALMOCO_SAB_INICIO", length=5)
	private String intervaloAlmocoSabInicio;
	
	@Column (name = "INTERVALO_ALMOCO_SAB_FIM", nullable=true, length=5)
	private String intervaloAlmocoSabFim;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "SALARIO_FAMILIA", nullable=false, length=1)
	private PlcSimNao salarioFamilia;
	
	@Column (name = "QUANTIDADE_FILHO", nullable=false, length=2)
	private Long quantidadeFilho;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "UTILIZA_VALE_TRANSPORTE", nullable=false, length=1)
	private PlcSimNao utilizaValeTransporte;
	
	@Column (name = "VALOR_VALE_TRANSPORTE", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valorValeTransporte;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "CONTRIBUICAO_SINDICAL_DESCONTADA", nullable=false, length=1)
	private PlcSimNao contribuicaoSindicalDescontada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada=horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida=horaSaida;
	}

	public String getIntervaloAlmocoInicio() {
		return intervaloAlmocoInicio;
	}

	public void setIntervaloAlmocoInicio(String intervaloAlmocoInicio) {
		this.intervaloAlmocoInicio=intervaloAlmocoInicio;
	}

	public String getIntervaloAlmocoFim() {
		return intervaloAlmocoFim;
	}

	public void setIntervaloAlmocoFim(String intervaloAlmocoFim) {
		this.intervaloAlmocoFim=intervaloAlmocoFim;
	}

	public PlcSimNao getTrabalhaSabado() {
		return trabalhaSabado;
	}

	public void setTrabalhaSabado(PlcSimNao trabalhaSabado) {
		this.trabalhaSabado=trabalhaSabado;
	}

	public String getEntradaSabado() {
		return entradaSabado;
	}

	public void setEntradaSabado(String entradaSabado) {
		this.entradaSabado=entradaSabado;
	}

	public String getSaidaSabado() {
		return saidaSabado;
	}

	public void setSaidaSabado(String saidaSabado) {
		this.saidaSabado=saidaSabado;
	}

	public String getIntervaloAlmocoSabInicio() {
		return intervaloAlmocoSabInicio;
	}

	public void setIntervaloAlmocoSabInicio(String intervaloAlmocoSabInicio) {
		this.intervaloAlmocoSabInicio=intervaloAlmocoSabInicio;
	}

	public String getIntervaloAlmocoSabFim() {
		return intervaloAlmocoSabFim;
	}

	public void setIntervaloAlmocoSabFim(String intervaloAlmocoSabFim) {
		this.intervaloAlmocoSabFim=intervaloAlmocoSabFim;
	}

	public PlcSimNao getSalarioFamilia() {
		return salarioFamilia;
	}

	public void setSalarioFamilia(PlcSimNao salarioFamilia) {
		this.salarioFamilia=salarioFamilia;
	}

	public Long getQuantidadeFilho() {
		return quantidadeFilho;
	}

	public void setQuantidadeFilho(Long quantidadeFilho) {
		this.quantidadeFilho=quantidadeFilho;
	}

	public PlcSimNao getUtilizaValeTransporte() {
		return utilizaValeTransporte;
	}

	public void setUtilizaValeTransporte(PlcSimNao utilizaValeTransporte) {
		this.utilizaValeTransporte=utilizaValeTransporte;
	}

	public BigDecimal getValorValeTransporte() {
		return valorValeTransporte;
	}

	public void setValorValeTransporte(BigDecimal valorValeTransporte) {
		this.valorValeTransporte=valorValeTransporte;
	}

	public PlcSimNao getContribuicaoSindicalDescontada() {
		return contribuicaoSindicalDescontada;
	}

	public void setContribuicaoSindicalDescontada(PlcSimNao contribuicaoSindicalDescontada) {
		this.contribuicaoSindicalDescontada=contribuicaoSindicalDescontada;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario=funcionario;
	}

}
