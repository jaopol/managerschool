package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.TipoFavorecido;
import com.consisti.sisgesc.dominio.TipoPessoa;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="FORNECEDOR")
@SequenceGenerator(name="SE_FORNECEDOR", sequenceName="SE_FORNECEDOR")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FornecedorEntity.querySel2", query="select new FornecedorEntity(obj.id) from FornecedorEntity obj order by obj.id asc"),
	@NamedQuery(name="FornecedorEntity.queryMan", query="from FornecedorEntity obj"),
	@NamedQuery(name="FornecedorEntity.querySel", query="select new FornecedorEntity(obj.id, obj.tipoPessoa, obj.telefoneContato, obj.nome, obj.razaoSocial, obj.status) from FornecedorEntity obj order by obj.telefoneContato asc"),
	@NamedQuery(name="FornecedorEntity.querySelLookup", query="select new FornecedorEntity (obj.id, obj.nome, obj.razaoSocial) from FornecedorEntity obj where obj.id = ? order by obj.id asc")
})
public class FornecedorEntity extends Fornecedor {
 	
	private transient String cpfCnpj;
	
    /*
     * Construtor padrão
     */
    public FornecedorEntity() {
    }
	public FornecedorEntity(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	public FornecedorEntity(Long id, String nome, String razaoSocial) {
		this.setId(id);
		this.setNome(nome);
		this.setRazaoSocial(razaoSocial);
	}
	
	public FornecedorEntity(String cpfCnpj, Long id) {
		this.setId(id);
		this.setCpfCnpj(cpfCnpj);
	}
	
	@Override
	public String toString() {
		
		if (StringUtils.isNotEmpty(getRazaoSocial())){
			return getRazaoSocial();
		} else {
			return getNome();
		}
		
	}

	public FornecedorEntity(Long id, TipoFavorecido tipoPessoa, String telefoneContato, String nome, String razaoSocial, AtivoInativo status) {
		setId(id);
		setTipoPessoa(tipoPessoa);
		setTelefoneContato(telefoneContato);
		setNome(nome);
		setRazaoSocial(razaoSocial);
		setStatus(status);
	}
	public FornecedorEntity(Long id) {
		setId(id);
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
}
