package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CERTIDAO_NASCIMENTO")
@SequenceGenerator(name="SE_CERTIDAO_NASCIMENTO", sequenceName="SE_CERTIDAO_NASCIMENTO")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="certidaoNascimentoIdentidade")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="CertidaoNascimentoEntity.querySelLookup", query="select new CertidaoNascimentoEntity (obj.id, obj.certidaoNascimentoIdentidade) from CertidaoNascimentoEntity obj where obj.id = ? order by obj.id asc")
})
public class CertidaoNascimentoEntity extends CertidaoNascimento {
	
	private transient String certidaoNascimentoIdentidadeDesprezar;
 	
    /*
     * Construtor padrão
     */
    public CertidaoNascimentoEntity() {
    }
	public CertidaoNascimentoEntity(Long id, String certidaoNascimentoIdentidade) {
		this.setId(id);
		this.setCertidaoNascimentoIdentidade(certidaoNascimentoIdentidade);
	}
	@Override
	public String toString() {
		return getCertidaoNascimentoIdentidade();
	}
	public String getCertidaoNascimentoIdentidadeDesprezar() {
		return getCertidaoNascimentoIdentidade();
	}
	public void setCertidaoNascimentoIdentidadeDesprezar(
			String certidaoNascimentoIdentidadeDesprezar) {
		this.certidaoNascimentoIdentidadeDesprezar = getCertidaoNascimentoIdentidade();
	}
	
	@Transient
	public String getUfCartorioStr(){
		if (getUfCartorio()!=null){
			return getUfCartorio().getDescricao();
		}
		return "";
	}
	
	@Transient
	public String getUfIdentidadeStr(){
		if (getUfIdentidade()!=null){
			return getUfIdentidade().getDescricao();
		}
		return "";
	}

}
