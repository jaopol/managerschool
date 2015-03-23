package com.consisti.sisgesc.entidade;

import java.util.Date;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;


@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Empresa extends AppBaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_EMPRESA")
	@Column (name = "ID_EMPRESA", nullable=false, length=5)
	private Long id;
	
	@Column (name = "NOME_EMPRESA", nullable=false, length=100)	
	private String nomeEmpresa;
	
	@Column (name = "DATA_CADASTRO", nullable=false, length=11)
	@Temporal(TemporalType.TIMESTAMP)	
	private Date dataCadastro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa=nomeEmpresa;
	}
	
	public Date getDataCadastro() {
		if (dataCadastro==null){
			dataCadastro = new Date();
		}
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro=dataCadastro;
	}

}
