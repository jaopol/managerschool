package com.consisti.sisgesc.entidade.autenticacao;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="URL_ACESSO")
@SequenceGenerator(name="SE_URL_ACESSO", sequenceName="SE_URL_ACESSO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="url")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="UrlAcessoEntity.querySel2", query="select new UrlAcessoEntity(obj.id, obj.url, obj.descricao) from UrlAcessoEntity obj order by obj.url asc"),
	@NamedQuery(name="UrlAcessoEntity.querySel", query="select new UrlAcessoEntity(obj.id, obj.url, obj.descricao) from UrlAcessoEntity obj order by obj.url asc"),
	@NamedQuery(name="UrlAcessoEntity.querySelLookup", query="select new UrlAcessoEntity (obj.id, obj.url, obj.descricao) from UrlAcessoEntity obj where obj.id = ? order by obj.id asc")
})
public class UrlAcessoEntity extends UrlAcesso {
 	
    /*
     * Construtor padrão
     */
    public UrlAcessoEntity() {
    }
	public UrlAcessoEntity(Long id, String url, String descricao) {
		this.setId(id);
		this.setUrl(url);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getUrl();
	}

}
