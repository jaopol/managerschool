package com.consisti.sisgesc.entidade.financeiro;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.dominio.TipoConta;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="BANCO")
@SequenceGenerator(name="SE_BANCO", sequenceName="SE_BANCO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="BancoEntity.queryMan", query="from BancoEntity obj"),
	@NamedQuery(name="BancoEntity.querySel", query="select new BancoEntity(obj.id, obj.bancoSuportado, obj.tipoConta, obj.status, obj.agencia, obj.numeroConta, obj.telefoneBanco) from BancoEntity obj order by obj.bancoSuportado asc"),
	@NamedQuery(name="BancoEntity.querySelLookup", query="select new BancoEntity (obj.id, obj.bancoSuportado) from BancoEntity obj where obj.id = ? order by obj.id asc")
})
public class BancoEntity extends Banco {
 	
    /*
     * Construtor padrão
     */
    public BancoEntity() {
    }

    public BancoEntity(Long id) {
    	this.setId(id);
    }
    
	public BancoEntity(Long id, BancoSuportado bancoSuportado) {
		this.setId(id);
		this.setBancoSuportado(bancoSuportado);
	}
	@Override
	public String toString() {
		return getDescricaoBanco();
	}
	
	public String getDescricaoBanco(){
		return getBancoSuportado() != null ? getBancoSuportado().getDescricao() : "";
	}

	public BancoEntity(Long id, BancoSuportado bancoSuportado, TipoConta tipoConta, AtivoInativo status, String agencia, String numeroConta, String telefoneBanco) {
		setId(id);
		setBancoSuportado(bancoSuportado);
		setTipoConta(tipoConta);
		setStatus(status);
		setAgencia(agencia);
		setNumeroConta(numeroConta);
		setTelefoneBanco(telefoneBanco);
	}
}
