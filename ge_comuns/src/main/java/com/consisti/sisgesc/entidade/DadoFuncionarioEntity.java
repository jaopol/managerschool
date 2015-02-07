package com.consisti.sisgesc.entidade;


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
@Table(name="DADO_FUNCIONARIO")
@SequenceGenerator(name="SE_DADO_FUNCIONARIO", sequenceName="SE_DADO_FUNCIONARIO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="escolaridadeDesprezar")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="DadoFuncionarioEntity.querySelLookup", query="select new DadoFuncionarioEntity (obj.id, obj.escolaridade) from DadoFuncionarioEntity obj where obj.id = ? order by obj.id asc")
})
public class DadoFuncionarioEntity extends DadoFuncionario {
 	
	@SuppressWarnings("unused")
	private transient String escolaridadeDesprezar;
	
    /*
     * Construtor padrão
     */
    public DadoFuncionarioEntity() {
    }
	public DadoFuncionarioEntity(Long id, String escolaridade) {
		this.setId(id);
		this.setEscolaridade(escolaridade);
	}
	@Override
	public String toString() {
		return getEscolaridade();
	}
	public String getEscolaridadeDesprezar() {
		return getEscolaridade();
	}
	public void setEscolaridadeDesprezar(String escolaridadeDesprezar) {
		this.escolaridadeDesprezar = getEscolaridade();
	}

}
