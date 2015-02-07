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
@Table(name="RESPONSAVEL_FINANCEIRO_ALUNO")
@SequenceGenerator(name="SE_RESPONSAVEL_FINANCEIRO_ALUNO", sequenceName="SE_RESPONSAVEL_FINANCEIRO_ALUNO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="nome")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ResponsavelFinanceiroAlunoEntity.querySelLookup", query="select new ResponsavelFinanceiroAlunoEntity (obj.id, obj.nome) from ResponsavelFinanceiroAlunoEntity obj where obj.id = ? order by obj.id asc")
})
public class ResponsavelFinanceiroAlunoEntity extends ResponsavelFinanceiroAluno {
 	
	private transient String apresentaImposto = "N";
	
    /*
     * Construtor padrão
     */
    public ResponsavelFinanceiroAlunoEntity() {
    }
	public ResponsavelFinanceiroAlunoEntity(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	@Override
	public String toString() {
		return getNome();
	}
	public String getApresentaImposto() {
		return apresentaImposto;
	}
	public void setApresentaImposto(String apresentaImposto) {
		this.apresentaImposto = apresentaImposto;
	}

}
