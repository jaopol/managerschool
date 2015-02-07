package com.consisti.sisgesc.entidade;


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
@Table(name="RESPONSAVEL_ALUNO_CASO_ACIDENTE")
@SequenceGenerator(name="SE_RESPONSAVEL_ALUNO_CASO_ACIDENTE", sequenceName="SE_RESPONSAVEL_ALUNO_CASO_ACIDENTE")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="nome")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ResponsavelAlunoCasoAcidenteEntity.querySelLookup", query="select new ResponsavelAlunoCasoAcidenteEntity (obj.id, obj.nome) from ResponsavelAlunoCasoAcidenteEntity obj where obj.id = ? order by obj.id asc")
})
public class ResponsavelAlunoCasoAcidenteEntity extends ResponsavelAlunoCasoAcidente {
 	
    /*
     * Construtor padrão
     */
    public ResponsavelAlunoCasoAcidenteEntity() {
    }
	public ResponsavelAlunoCasoAcidenteEntity(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	@Override
	public String toString() {
		return getNome();
	}
	
}
