package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="ENDERECO")
@SequenceGenerator(name="SE_ENDERECO", sequenceName="SE_ENDERECO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="logradouro")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="EnderecoEntity.querySelLookup", query="select new EnderecoEntity (obj.id, obj.logradouro) from EnderecoEntity obj where obj.id = ? order by obj.id asc")
})
public class EnderecoEntity extends Endereco {
 	
    /*
     * Construtor padrão
     */
    public EnderecoEntity() {
    }
	public EnderecoEntity(Long id, String logradouro) {
		this.setId(id);
		this.setLogradouro(logradouro);
	}
	
	public EnderecoEntity(Long id, String logradouro, String numero, String complemento, String bairro, String cep, String cidade, Uf uf ) {
		this.setId(id);
		this.setLogradouro(logradouro);
		this.setNumero(numero); 
		this.setComplemento(complemento);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setCidade(cidade);
		this.setUf(uf);
	}
	
	@Override
	public String toString() {
		return getLogradouro();
	}

}
