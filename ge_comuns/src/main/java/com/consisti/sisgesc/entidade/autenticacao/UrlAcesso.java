package com.consisti.sisgesc.entidade.autenticacao;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class UrlAcesso extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_URL_ACESSO")
	@Column (name = "ID_URL_ACESSO", nullable=false, length=5)
	private Long id;

	@Column (name = "URL", nullable=false, length=250)
	private String url;
	
	@Column (name = "DESCRICAO", nullable=true, length=250)
	private String descricao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url=url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
