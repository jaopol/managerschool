package com.consisti.sisgesc.entidade.autenticacao;

import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;

import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.validator.Valid;
import org.hibernate.annotations.ForeignKey;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class Perfil extends AppBaseEntity {

	
	@OneToMany (targetEntity = UrlAcessoPerfilEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="perfil")
	@ForeignKey(name="FK_URLACESSOPERFIL_PERFIL")
	@Valid
	@JoinColumn (name = "ID_PERFIL")
	private List<UrlAcessoPerfil> urlAcessoPerfil;
	
	@OneToMany (targetEntity = PerfilUsuarioEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="perfil")
	@ForeignKey(name="FK_PERFILUSUARIO_PERFIL")
	@Valid
	@JoinColumn (name = "ID_PERFIL")
	private List<PerfilUsuario> perfilUsuario;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_PERFIL")
	@Column (name = "ID_PERFIL", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "NOME", nullable=false, length=20)
	private String nome;
	
	@PlcValFormatoSimples( formato=FormatoSimples.MAIUSCULO )
	@Column (name = "DESCRICAO", nullable=false, length=100)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "BLOQUEADO", nullable=false, length=1)
	private PlcSimNao bloqueado;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "GRAVA", nullable=false, length=1)
	private PlcSimNao grava;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "EXCLUI", nullable=false, length=1)
	private PlcSimNao exclui;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "PESQUISA", nullable=false, length=1)
	private PlcSimNao pesquisa;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome=nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public PlcSimNao getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(PlcSimNao bloqueado) {
		this.bloqueado=bloqueado;
	}

	public PlcSimNao getGrava() {
		return grava;
	}

	public void setGrava(PlcSimNao grava) {
		this.grava=grava;
	}

	public PlcSimNao getExclui() {
		return exclui;
	}

	public void setExclui(PlcSimNao exclui) {
		this.exclui=exclui;
	}

	public PlcSimNao getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(PlcSimNao pesquisa) {
		this.pesquisa=pesquisa;
	}

	public List<PerfilUsuario> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(List<PerfilUsuario> perfilUsuario) {
		this.perfilUsuario=perfilUsuario;
	}

	public List<UrlAcessoPerfil> getUrlAcessoPerfil() {
		return urlAcessoPerfil;
	}

	public void setUrlAcessoPerfil(List<UrlAcessoPerfil> urlAcessoPerfil) {
		this.urlAcessoPerfil=urlAcessoPerfil;
	}

}
