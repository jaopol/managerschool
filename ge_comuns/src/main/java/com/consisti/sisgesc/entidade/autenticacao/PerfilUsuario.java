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
public abstract class PerfilUsuario extends AppBaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_PERFIL_USUARIO")
	@Column (name = "ID_PERFIL_USUARIO", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = PerfilEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_PERFILUSUARIO_PERFIL")
	@JoinColumn (name = "ID_PERFIL", nullable=false)
	private Perfil perfil;
	
	@ManyToOne (targetEntity = UsuarioEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_PERFILUSUARIO_USUARIO")
	@JoinColumn (name = "ID_USUARIO", nullable=false)
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}

}
