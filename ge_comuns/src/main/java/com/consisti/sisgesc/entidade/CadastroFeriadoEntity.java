package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcEntidade;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CADASTRO_FERIADO")
@SequenceGenerator(name="SE_CADASTRO_FERIADO", sequenceName="SE_CADASTRO_FERIADO")
@AccessType("field")

@PlcEntidade(classeLookup=true)
@PlcTabular(propReferenciaDesprezar="descricao")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CadastroFeriadoEntity.queryMan", query="from CadastroFeriadoEntity obj"),
	@NamedQuery(name="CadastroFeriadoEntity.querySelLookup", query="select new CadastroFeriadoEntity (obj.id, obj.descricao) from CadastroFeriadoEntity obj where obj.id = ? order by obj.id asc")
})
public class CadastroFeriadoEntity extends CadastroFeriado {
 	
    /*
     * Construtor padrão
     */
    public CadastroFeriadoEntity() {
    }
	public CadastroFeriadoEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

}
