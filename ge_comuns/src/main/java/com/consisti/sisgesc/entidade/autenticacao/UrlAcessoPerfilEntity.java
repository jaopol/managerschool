package com.consisti.sisgesc.entidade.autenticacao;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="URL_ACESSO_PERFIL")
@SequenceGenerator(name="SE_URL_ACESSO_PERFIL", sequenceName="SE_URL_ACESSO_PERFIL")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="perfil")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="UrlAcessoPerfilEntity.querySelLookup", query="select new UrlAcessoPerfilEntity (obj.id, obj.perfil, obj.urlAcesso) from UrlAcessoPerfilEntity obj where obj.id = ? order by obj.id asc")
})
public class UrlAcessoPerfilEntity extends UrlAcessoPerfil {

	private transient String urlAcessoAuxLookup;

 	
    /*
     * Construtor padrão
     */
    public UrlAcessoPerfilEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getPerfilAux() {
    	return getPerfil()!=null? getPerfil().getIdAux() : null;
    }
    
    public void setPerfilAux(String perfilAux) {
    	if (perfilAux != null && perfilAux.trim().length() > 0) {
    		Long id = Long.valueOf(perfilAux);
    		if (getPerfil()==null || !id.equals(getPerfil().getId())) {
    			com.consisti.sisgesc.entidade.autenticacao.Perfil obj = new com.consisti.sisgesc.entidade.autenticacao.PerfilEntity();
    			obj.setId(id);
    			this.setPerfil(obj);
    		}
    	} else {
    		this.setPerfil(null);
    	}
    }

    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getUrlAcessoAux() {
    	return getUrlAcesso()!=null? getUrlAcesso().getIdAux() : null;
    }
    
    public void setUrlAcessoAux(String urlAcessoAux) {
    	if (urlAcessoAux != null && urlAcessoAux.trim().length() > 0) {
    		Long id = Long.valueOf(urlAcessoAux);
    		if (getUrlAcesso()==null || !id.equals(getUrlAcesso().getId())) {
    			com.consisti.sisgesc.entidade.autenticacao.UrlAcesso obj = new com.consisti.sisgesc.entidade.autenticacao.UrlAcessoEntity();
    			obj.setId(id);
    			this.setUrlAcesso(obj);
    		}
    	} else {
    		this.setUrlAcesso(null);
    	}
    }

	public UrlAcessoPerfilEntity(Long id, com.consisti.sisgesc.entidade.autenticacao.Perfil perfil, UrlAcesso urlAcesso) {
		this.setId(id);
		this.setPerfil(perfil);
		this.setUrlAcesso(urlAcesso);
	}
	@Override
	public String toString() {
		return getPerfilAux()+"-"+getUrlAcessoAux();
	}


	public void setUrlAcessoAuxLookup(String urlAcessoAuxLookup) {
		this.urlAcessoAuxLookup=urlAcessoAuxLookup;
	}


}
