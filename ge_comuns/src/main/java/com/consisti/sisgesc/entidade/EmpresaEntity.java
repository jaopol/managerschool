package com.consisti.sisgesc.entidade;


import java.text.SimpleDateFormat;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcEntidade;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
import javax.persistence.Transient;
@Entity
@Table(name="EMPRESA")
@SequenceGenerator(name="SE_EMPRESA", sequenceName="SE_EMPRESA")

@PlcEntidade(numPorPagina=2)

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="EmpresaEntity.queryMan", query="from EmpresaEntity obj"),
	@NamedQuery(name="EmpresaEntity.querySel", query="select new EmpresaEntity(obj.id, obj.nomeEmpresa, obj.dataCadastro) from EmpresaEntity obj order by obj.nomeEmpresa asc"),
	@NamedQuery(name="EmpresaEntity.querySelLookup", query="select new EmpresaEntity (obj.id, obj.nomeEmpresa) from EmpresaEntity obj where obj.id = ? order by obj.id asc"),
	@NamedQuery(name="EmpresaEntity.naoDeveExistirNomeEmpresaDuplicado", query="select count(*) from EmpresaEntity obj where UPPER( TRIM( obj.nomeEmpresa ) ) = UPPER( TRIM( :nomeEmpresa ) ) and obj.id is not null")
})
public class EmpresaEntity extends Empresa {
 	
    /*
     * Construtor padrão
     */
    public EmpresaEntity() {
    }
	public EmpresaEntity(Long id, String nomeEmpresa) {
		this.setId(id);
		this.setNomeEmpresa(nomeEmpresa);
	}
	@Override
	public String toString() {
		return getNomeEmpresa();
	}
	
	@Transient
	private String getDataCadastroFormatada(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getDataCadastro());
	}

	public EmpresaEntity(Long id, String nomeEmpresa, java.util.Date dataCadastro) {
		setId(id);
		setNomeEmpresa(nomeEmpresa);
		setDataCadastro(dataCadastro);
	}
}
