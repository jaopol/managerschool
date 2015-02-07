package com.consisti.sisgesc.entidade;



import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.GeneratedValue;
@MappedSuperclass
@SuppressWarnings("serial")
public class FrequenciaAluno extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FREQUENCIA_ALUNO")
	@Column (name = "ID_FREQUENCIA_ALUNO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = RegistrarFrequenciaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_FREQUENCIAALUNO_REGISTRARFREQUENCIA")
	@JoinColumn (name = "ID_REGISTRAR_FREQUENCIA", nullable=false)
	private RegistrarFrequencia registrarFrequencia;
	
	@ManyToOne (targetEntity = AlunoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_FREQUENCIAALUNO_ALUNO")
	@JoinColumn (name = "ID_ALUNO", nullable=false)
	private Aluno aluno;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_UM", length=1)
	private String diaUm;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DOIS", length=1)
	private String diaDois;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_TRES", length=1)
	private String diaTres;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_QUATRO", length=1)
	private String diaQuatro;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_CINCO", length=1)
	private String diaCinco;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_SEIS", length=1)
	private String diaSeis;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_SETE", length=1)
	private String diaSete;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_OITO", length=1)
	private String diaOito;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_NOVE", length=1)
	private String diaNove;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DEZ", length=1)
	private String diaDez;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_ONZE", length=1)
	private String diaOnze;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DOZE", length=1)
	private String diaDoze;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_TREZE", length=1)
	private String diaTreze;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_QUATORZE", length=1)
	private String diaQuatorze;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_QUINZE", length=1)
	private String diaQuinze;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DEZESSEIS", length=1)
	private String diaDezesseis;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DEZESSETE", length=1)
	private String diaDezessete;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DEZOITO", length=1)
	private String diaDezoito;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_DEZENOVE", length=1)
	private String diaDezenove;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE", length=1)
	private String diaVinte;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_UM", length=1)
	private String diaVinteUm;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_DOIS", length=1)
	private String diaVinteDois;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_TRES", length=1)
	private String diaVinteTres;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_QUATRO", length=1)
	private String diaVinteQuatro;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_CINCO", length=1)
	private String diaVinteCinco;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_SEIS", length=1)
	private String diaVinteSeis;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_SETE", length=1)
	private String diaVinteSete;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_OITO", length=1)
	private String diaVinteOito;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_VINTE_NOVE", length=1)
	private String diaVinteNove;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_TRINTA", length=1)
	private String diaTrinta;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DIA_TRINTA_UM", length=1)
	private String diaTrintaUm;
	
	@Column (name = "TOTAL", length=1)
	private Integer total;
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getDiaUm() {
		return diaUm;
	}
	public void setDiaUm(String diaUm) {
		this.diaUm = diaUm;
	}
	public String getDiaDois() {
		return diaDois;
	}
	public void setDiaDois(String diaDois) {
		this.diaDois = diaDois;
	}
	public String getDiaTres() {
		return diaTres;
	}
	public void setDiaTres(String diaTres) {
		this.diaTres = diaTres;
	}
	public String getDiaQuatro() {
		return diaQuatro;
	}
	public void setDiaQuatro(String diaQuatro) {
		this.diaQuatro = diaQuatro;
	}
	public String getDiaCinco() {
		return diaCinco;
	}
	public void setDiaCinco(String diaCinco) {
		this.diaCinco = diaCinco;
	}
	public String getDiaSeis() {
		return diaSeis;
	}
	public void setDiaSeis(String diaSeis) {
		this.diaSeis = diaSeis;
	}
	public String getDiaSete() {
		return diaSete;
	}
	public void setDiaSete(String diaSete) {
		this.diaSete = diaSete;
	}
	public String getDiaOito() {
		return diaOito;
	}
	public void setDiaOito(String diaOito) {
		this.diaOito = diaOito;
	}
	public String getDiaNove() {
		return diaNove;
	}
	public void setDiaNove(String diaNove) {
		this.diaNove = diaNove;
	}
	public String getDiaDez() {
		return diaDez;
	}
	public void setDiaDez(String diaDez) {
		this.diaDez = diaDez;
	}
	public String getDiaOnze() {
		return diaOnze;
	}
	public void setDiaOnze(String diaOnze) {
		this.diaOnze = diaOnze;
	}
	public String getDiaDoze() {
		return diaDoze;
	}
	public void setDiaDoze(String diaDoze) {
		this.diaDoze = diaDoze;
	}
	public String getDiaTreze() {
		return diaTreze;
	}
	public void setDiaTreze(String diaTreze) {
		this.diaTreze = diaTreze;
	}
	public String getDiaQuatorze() {
		return diaQuatorze;
	}
	public void setDiaQuatorze(String diaQuatorze) {
		this.diaQuatorze = diaQuatorze;
	}
	public String getDiaQuinze() {
		return diaQuinze;
	}
	public void setDiaQuinze(String diaQuinze) {
		this.diaQuinze = diaQuinze;
	}
	public String getDiaDezesseis() {
		return diaDezesseis;
	}
	public void setDiaDezesseis(String diaDezesseis) {
		this.diaDezesseis = diaDezesseis;
	}
	public String getDiaDezessete() {
		return diaDezessete;
	}
	public void setDiaDezessete(String diaDezessete) {
		this.diaDezessete = diaDezessete;
	}
	public String getDiaDezoito() {
		return diaDezoito;
	}
	public void setDiaDezoito(String diaDezoito) {
		this.diaDezoito = diaDezoito;
	}
	public String getDiaDezenove() {
		return diaDezenove;
	}
	public void setDiaDezenove(String diaDezenove) {
		this.diaDezenove = diaDezenove;
	}
	public String getDiaVinte() {
		return diaVinte;
	}
	public void setDiaVinte(String diaVinte) {
		this.diaVinte = diaVinte;
	}
	public String getDiaVinteUm() {
		return diaVinteUm;
	}
	public void setDiaVinteUm(String diaVinteUm) {
		this.diaVinteUm = diaVinteUm;
	}
	public String getDiaVinteDois() {
		return diaVinteDois;
	}
	public void setDiaVinteDois(String diaVinteDois) {
		this.diaVinteDois = diaVinteDois;
	}
	public String getDiaVinteTres() {
		return diaVinteTres;
	}
	public void setDiaVinteTres(String diaVinteTres) {
		this.diaVinteTres = diaVinteTres;
	}
	public String getDiaVinteQuatro() {
		return diaVinteQuatro;
	}
	public void setDiaVinteQuatro(String diaVinteQuatro) {
		this.diaVinteQuatro = diaVinteQuatro;
	}
	public String getDiaVinteCinco() {
		return diaVinteCinco;
	}
	public void setDiaVinteCinco(String diaVinteCinco) {
		this.diaVinteCinco = diaVinteCinco;
	}
	public String getDiaVinteSeis() {
		return diaVinteSeis;
	}
	public void setDiaVinteSeis(String diaVinteSeis) {
		this.diaVinteSeis = diaVinteSeis;
	}
	public String getDiaVinteSete() {
		return diaVinteSete;
	}
	public void setDiaVinteSete(String diaVinteSete) {
		this.diaVinteSete = diaVinteSete;
	}
	public String getDiaVinteOito() {
		return diaVinteOito;
	}
	public void setDiaVinteOito(String diaVinteOito) {
		this.diaVinteOito = diaVinteOito;
	}
	public String getDiaVinteNove() {
		return diaVinteNove;
	}
	public void setDiaVinteNove(String diaVinteNove) {
		this.diaVinteNove = diaVinteNove;
	}
	public String getDiaTrinta() {
		return diaTrinta;
	}
	public void setDiaTrinta(String diaTrinta) {
		this.diaTrinta = diaTrinta;
	}
	public String getDiaTrintaUm() {
		return diaTrintaUm;
	}
	public void setDiaTrintaUm(String diaTrintaUm) {
		this.diaTrintaUm = diaTrintaUm;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public RegistrarFrequencia getRegistrarFrequencia() {
		return registrarFrequencia;
	}

	public void setRegistrarFrequencia(RegistrarFrequencia registrarFrequencia) {
		this.registrarFrequencia=registrarFrequencia;
	}

}
