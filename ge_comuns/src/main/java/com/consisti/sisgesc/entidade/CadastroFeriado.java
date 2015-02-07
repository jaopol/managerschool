package com.consisti.sisgesc.entidade;

import java.util.Date;

import com.powerlogic.jcompany.dominio.valida.PlcFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcFormatoSimples.Formato;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class CadastroFeriado extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_CADASTRO_FERIADO")
	@Column (name = "ID_CADASTRO_FERIADO", nullable=false, length=5)
	private Long id;

	@PlcValFormatoSimples( formato = FormatoSimples.MAIUSCULO )
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@Column (name = "DIA_MES_FERIADO", nullable=false, length=5)
	private String diaMesFeriado;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public String getDiaMesFeriado() {
		return diaMesFeriado;
	}

	public void setDiaMesFeriado(String diaMesFeriado) {
		this.diaMesFeriado=diaMesFeriado;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

}
