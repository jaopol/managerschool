package com.consisti.sisgesc.entidade.autenticacao;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
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
public abstract class UrlAcessoPerfil extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_URL_ACESSO_PERFIL")
	@Column (name = "ID_URL_ACESSO_PERFIL", nullable=false, length=5)
	private Long id;

	@ManyToOne (targetEntity = PerfilEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_URLACESSOPERFIL_PERFIL")
	@JoinColumn (name = "ID_PERFIL", nullable=false)
	private Perfil perfil;
	
	@ManyToOne (targetEntity = UrlAcessoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_URLACESSOPERFIL_URLACESSO")
	@JoinColumn (name = "ID_URL_ACESSO", nullable=false)
	private UrlAcesso urlAcesso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil=perfil;
	}

	public UrlAcesso getUrlAcesso() {
		return urlAcesso;
	}

	public void setUrlAcesso(UrlAcesso urlAcesso) {
		this.urlAcesso=urlAcesso;
	}

}
