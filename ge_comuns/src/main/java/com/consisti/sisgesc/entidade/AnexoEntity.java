package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.AccessType;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="TB_ANEXO")
@SequenceGenerator(name="SE_ANEXO", sequenceName="SE_ANEXO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="AnexosAlunoEntity.querySelLookup", query="select new AnexoEntity (obj.id, obj.nomeArquivo) from AnexoEntity obj where obj.id = ? order by obj.id asc")
})
public class AnexoEntity extends Anexo {
 	
	private transient String descricaoStr;
	
    /*
     * Construtor padrão
     */
    public AnexoEntity() {
    }
	public AnexoEntity(Long id, String nomeArquivo) {
		this.setId(id);
		this.setNomeArquivo(nomeArquivo);
	}
	@Override
	public String toString() {
		return getNomeArquivo();
	}

	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Aluno.class};
	}
	public String getDescricaoStr() {
		if (StringUtils.isNotEmpty(this.getDescricao())){
			if (this.getDescricao().length()>50){
				return this.getDescricao().substring(0, 50)+"...";
			} else {
				return this.getDescricao();
			}
		}
		return descricaoStr;
	}
	public void setDescricaoStr(String descricaoStr) {
		this.descricaoStr = descricaoStr;
	}

}
