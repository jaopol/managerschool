package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="TABELA_PRECO")
@SequenceGenerator(name="SE_TABELA_PRECO", sequenceName="SE_TABELA_PRECO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="TabelaPrecoEntity.queryMan", query="from TabelaPrecoEntity obj"),
	@NamedQuery(name="TabelaPrecoEntity.querySel", query="select new TabelaPrecoEntity(obj.id, obj.descricao, obj.turma.id , obj.turma.descricao, obj.observacao) from TabelaPrecoEntity obj left outer join obj.turma order by obj.descricao asc"),
	@NamedQuery(name="TabelaPrecoEntity.querySelLookup", query="select new TabelaPrecoEntity (obj.id, obj.descricao) from TabelaPrecoEntity obj where obj.id = ? order by obj.id asc")
})
public class TabelaPrecoEntity extends TabelaPreco {
 	
    /*
     * Construtor padrão
     */
    public TabelaPrecoEntity() {
    }
	public TabelaPrecoEntity(Long id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	@Override
	public String toString() {
		return getDescricao();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Turma.class};
	}

	public TabelaPrecoEntity(Long id, String descricao, Long turmaId, String turmaDescricao, String observacao) {
		setId(id);
		setDescricao(descricao);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		setObservacao(observacao);
	}
}
