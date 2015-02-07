package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="FUNCIONARIO")
@SequenceGenerator(name="SE_FUNCIONARIO", sequenceName="SE_FUNCIONARIO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="FuncionarioEntity.queryMan", query="from FuncionarioEntity obj"),
	@NamedQuery(name="FuncionarioEntity.querySel", query="select new FuncionarioEntity(obj.id, obj.nome, obj.professor, obj.dataCadastro, obj.status, obj.telefone, obj.celular, obj.email) from FuncionarioEntity obj order by obj.nome asc"),
	@NamedQuery(name="FuncionarioEntity.querySelLookup", query="select new FuncionarioEntity (obj.id, obj.nome) from FuncionarioEntity obj where obj.id = ? order by obj.id asc")
})
public class FuncionarioEntity extends Funcionario {
 	
    /*
     * Construtor padrão
     */
    public FuncionarioEntity() {
    }
	public FuncionarioEntity(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	@Override
	public String toString() {
		return getNome();
	}

	public FuncionarioEntity(Long id, String nome, PlcSimNao professor, java.util.Date dataCadastro, AtivoInativo status, String telefone, String celular, String email) {
		setId(id);
		setNome(nome);
		setProfessor(professor);
		setDataCadastro(dataCadastro);
		setStatus(status);
		setTelefone(telefone);
		setCelular(celular);
		setEmail(email);
	}
	
	public FuncionarioEntity(Long id, String nome, PlcSimNao professor, String email, String celular) {
		setId(id);
		setNome(nome);
		setProfessor(professor);
		setCelular(celular);
		setEmail(email);
	}
	
	public FuncionarioEntity(Long id, String nome, PlcSimNao professor) {
		setId(id);
		setNome(nome);
		setProfessor(professor);
	}
}
