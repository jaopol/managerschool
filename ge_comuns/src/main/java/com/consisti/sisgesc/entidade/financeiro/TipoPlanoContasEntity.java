package com.consisti.sisgesc.entidade.financeiro;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.entidade.financeiro.PlanoContasEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="TIPO_PLANO_CONTAS")
@SequenceGenerator(name="SE_TIPO_PLANO_CONTAS", sequenceName="SE_TIPO_PLANO_CONTAS")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="TipoPlanoContasEntity.queryMan", query="from TipoPlanoContasEntity obj"),
	@NamedQuery(name="TipoPlanoContasEntity.querySel", query="select new TipoPlanoContasEntity(obj.id, obj.planoContas.id , obj.planoContas.descricao, obj.descricao) from TipoPlanoContasEntity obj left outer join obj.planoContas order by obj.planoContas.descricao, obj.descricao asc"),
	@NamedQuery(name="TipoPlanoContasEntity.querySelLookup", query="select new TipoPlanoContasEntity (obj.id, obj.descricao) from TipoPlanoContasEntity obj where obj.id = ? order by obj.id asc")
})
public class TipoPlanoContasEntity extends TipoPlanoContas {
 	
    /*
     * Construtor padrão
     */
    public TipoPlanoContasEntity() {
    }
	public TipoPlanoContasEntity(Long id, String descricao) {
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
		return new Class[] {com.consisti.sisgesc.entidade.financeiro.PlanoContas.class};
	}

	public TipoPlanoContasEntity(Long id, Long planoContasId, String planoContasDescricao, String descricao) {
		setId(id);
		if (getPlanoContas() == null){
			setPlanoContas(new PlanoContasEntity());
		}
		getPlanoContas().setId(planoContasId);
		getPlanoContas().setDescricao(planoContasDescricao);
		setDescricao(descricao);
	}
}
