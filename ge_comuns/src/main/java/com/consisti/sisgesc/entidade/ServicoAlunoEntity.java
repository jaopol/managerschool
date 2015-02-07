package com.consisti.sisgesc.entidade;


import java.util.Date;

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
@Table(name="SERVICO_ALUNO")
@SequenceGenerator(name="SE_SERVICO_ALUNO", sequenceName="SE_SERVICO_ALUNO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ServicoAlunoEntity.querySel2", query="select new ServicoAlunoEntity(obj.id) from ServicoAlunoEntity obj order by obj.id asc"),
	@NamedQuery(name="ServicoAlunoEntity.queryMan", query="from ServicoAlunoEntity obj"),
	@NamedQuery(name="ServicoAlunoEntity.querySel", query="select new ServicoAlunoEntity(obj.id, obj.servico.id , obj.servico.descricao, obj.dataCadastro, obj.observacao) from ServicoAlunoEntity obj left outer join obj.servico order by obj.observacao asc"),
	@NamedQuery(name="ServicoAlunoEntity.querySelLookup", query="select new ServicoAlunoEntity (obj.id, obj.servico) from ServicoAlunoEntity obj where obj.id = ? order by obj.id asc")
})
public class ServicoAlunoEntity extends ServicoAluno {
 	
    /*
     * Construtor padrão
     */
    public ServicoAlunoEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getServicoStr() {
    	return getServico()!=null? getServico().getIdAux() : null;
    }
    
    public void setServicoStr(String servicoStr) {
    	if (servicoStr != null && servicoStr.trim().length() > 0) {
    		Long id = Long.valueOf(servicoStr);
    		if (getServico()==null || !id.equals(getServico().getId())) {
    			com.consisti.sisgesc.entidade.Servicos obj = new com.consisti.sisgesc.entidade.ServicosEntity();
    			obj.setId(id);
    			this.setServico(obj);
    		}
    	} else {
    		this.setServico(null);
    	}
    }

	public ServicoAlunoEntity(Long id, Servicos servico) {
		this.setId(id);
		this.setServico(servico);
	}
	@Override
	public String toString() {
		return getServicoStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Servicos.class};
	}

	public ServicoAlunoEntity(Long id, Long servicoId, String servicoDescricao, Date dataCadastro, String observacao) {
		setId(id);
		if (getServico() == null){
			setServico(new ServicosEntity());
		}
		getServico().setId(servicoId);
		getServico().setDescricao(servicoDescricao);
		setDataCadastro(dataCadastro);
		setObservacao(observacao);
	}
	public ServicoAlunoEntity(Long id) {
		setId(id);
	}
}
