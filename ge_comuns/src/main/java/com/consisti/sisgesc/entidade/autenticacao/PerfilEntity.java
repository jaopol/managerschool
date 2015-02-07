package com.consisti.sisgesc.entidade.autenticacao;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="PERFIL")
@SequenceGenerator(name="SE_PERFIL", sequenceName="SE_PERFIL")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="PerfilEntity.querySel2", query="select new PerfilEntity(obj.id, obj.nome, obj.descricao, obj.bloqueado, obj.grava, obj.exclui, obj.pesquisa) from PerfilEntity obj order by obj.nome asc"),
	@NamedQuery(name="PerfilEntity.queryMan", query="from PerfilEntity obj"),
	@NamedQuery(name="PerfilEntity.querySel", query="select new PerfilEntity(obj.id, obj.nome, obj.bloqueado, obj.descricao, obj.grava, obj.exclui, obj.pesquisa) from PerfilEntity obj order by obj.nome asc"),
	@NamedQuery(name="PerfilEntity.querySelLookup", query="select new PerfilEntity (obj.id, obj.nome) from PerfilEntity obj where obj.id = ? order by obj.id asc")
})
public class PerfilEntity extends Perfil {
 	
	private transient String urlAcesso;
	
    /*
     * Construtor padrão
     */
    public PerfilEntity() {
    }
	public PerfilEntity(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	@Override
	public String toString() {
		return getNome();
	}

	public PerfilEntity(Long id, String nome, PlcSimNao bloqueado, String descricao, PlcSimNao grava, PlcSimNao exclui, PlcSimNao pesquisa) {
		setId(id);
		setNome(nome);
		setBloqueado(bloqueado);
		setDescricao(descricao);
		setGrava(grava);
		setExclui(exclui);
		setPesquisa(pesquisa);
	}
	
	/**
	 * Usado no perfilDAO - recuperaListaUrlPerfilByLoginUsuario
	 */
	public PerfilEntity( String url ) {
		this.setUrlAcesso( url );
	}
	
	/**
	 * Usado no perfilDAO - recuperaPerfilUsuarioByLoginUsuario
	 */
	public PerfilEntity( PlcSimNao grava, PlcSimNao exclui, PlcSimNao pesquisa ) {
		this.setGrava( grava );
		this.setExclui( exclui );
		this.setPesquisa( pesquisa );
	}
	
	public String getUrlAcesso() {
		return urlAcesso;
	}
	public void setUrlAcesso(String urlAcesso) {
		this.urlAcesso = urlAcesso;
	}
	public PerfilEntity(Long id, String nome, String descricao, PlcSimNao bloqueado, PlcSimNao grava, PlcSimNao exclui, PlcSimNao pesquisa) {
		setId(id);
		setNome(nome);
		setDescricao(descricao);
		setBloqueado(bloqueado);
		setGrava(grava);
		setExclui(exclui);
		setPesquisa(pesquisa);
	}
}
