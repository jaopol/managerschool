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
@Table(name="PERFIL_USUARIO")
@SequenceGenerator(name="SE_PERFIL_USUARIO", sequenceName="SE_PERFIL_USUARIO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="perfil")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="PerfilUsuarioEntity.querySelLookup", query="select new PerfilUsuarioEntity (obj.id, obj.perfil, obj.usuario) from PerfilUsuarioEntity obj where obj.id = ? order by obj.id asc")
})
public class PerfilUsuarioEntity extends PerfilUsuario {

	private transient String usuarioAuxLookup;

 	
    /*
     * Construtor padrão
     */
    public PerfilUsuarioEntity() {
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
    public String getUsuarioAux() {
    	return getUsuario()!=null? getUsuario().getIdAux() : null;
    }
    
    public void setUsuarioAux(String usuarioAux) {
    	if (usuarioAux != null && usuarioAux.trim().length() > 0) {
    		Long id = Long.valueOf(usuarioAux);
    		if (getUsuario()==null || !id.equals(getUsuario().getId())) {
    			com.consisti.sisgesc.entidade.autenticacao.Usuario obj = new com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity();
    			obj.setId(id);
    			this.setUsuario(obj);
    		}
    	} else {
    		this.setUsuario(null);
    	}
    }

	public PerfilUsuarioEntity(Long id, com.consisti.sisgesc.entidade.autenticacao.Perfil perfil, Usuario usuario) {
		this.setId(id);
		this.setPerfil(perfil);
		this.setUsuario(usuario);
	}
	@Override
	public String toString() {
		return getPerfilAux()+"-"+getUsuarioAux();
	}


	public void setUsuarioAuxLookup(String usuarioAuxLookup) {
		this.usuarioAuxLookup=usuarioAuxLookup;
	}


}
