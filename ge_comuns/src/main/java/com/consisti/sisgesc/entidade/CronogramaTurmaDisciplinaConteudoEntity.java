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
@Table(name="CRONOGRAMA_TURMA_DISCIPLINA_CONTEUDO")
@SequenceGenerator(name="SE_CRONOGRAMA_TURMA_DISCIPLINA_CONTEUDO", sequenceName="SE_CRONOGRAMA_TURMA_DISCIPLINA_CONTEUDO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="conteudo")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CronogramaTurmaDisciplinaConteudoEntity.querySelLookup", query="select new CronogramaTurmaDisciplinaConteudoEntity (obj.id, obj.conteudo) from CronogramaTurmaDisciplinaConteudoEntity obj where obj.id = ? order by obj.id asc")
})
public class CronogramaTurmaDisciplinaConteudoEntity extends CronogramaTurmaDisciplinaConteudo {
 	
    /*
     * Construtor padrão
     */
    public CronogramaTurmaDisciplinaConteudoEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getConteudoAux() {
    	return getConteudo()!=null? getConteudo().getIdAux() : null;
    }
    
    public void setConteudoAux(String conteudoAux) {
    	if (conteudoAux != null && conteudoAux.trim().length() > 0) {
    		Long id = Long.valueOf(conteudoAux);
    		if (getConteudo()==null || !id.equals(getConteudo().getId())) {
    			com.consisti.sisgesc.entidade.Conteudo obj = new com.consisti.sisgesc.entidade.ConteudoEntity();
    			obj.setId(id);
    			this.setConteudo(obj);
    		}
    	} else {
    		this.setConteudo(null);
    	}
    }

	public CronogramaTurmaDisciplinaConteudoEntity(Long id, Conteudo conteudo) {
		this.setId(id);
		this.setConteudo(conteudo);
	}
	@Override
	public String toString() {
		return getConteudoAux();
	}

}
