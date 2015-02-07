package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;

import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.entidade.AlunoEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CONTRATO")
@SequenceGenerator(name="SE_CONTRATO", sequenceName="SE_CONTRATO")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ContratoEntity.querySel2", query="select new ContratoEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.anoContrato, obj.statusContrato) from ContratoEntity obj left outer join obj.aluno order by obj.anoContrato desc, obj.aluno.nomeAluno asc"),
	@NamedQuery(name="ContratoEntity.queryMan", query="from ContratoEntity obj"),
	@NamedQuery(name="ContratoEntity.querySel", query="select new ContratoEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.anoContrato) from ContratoEntity obj left outer join obj.aluno order by obj.anoContrato desc, obj.aluno.nomeAluno asc"),
	@NamedQuery(name="ContratoEntity.querySelLookup", query="select new ContratoEntity (obj.id) from ContratoEntity obj where obj.id = ? order by obj.id asc")
})
public class ContratoEntity extends Contrato {
 	
	@Transient
	private String texto;
	
    /*
     * Construtor padrão
     */
    public ContratoEntity() {
    }
	public ContratoEntity(Long id) {
		this.setId(id);
	}
	@Override
	public String toString() {
		return getIdAux();
	}

	public ContratoEntity(Long id, Long alunoId, String alunoNomeAluno, Long anoContrato) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(alunoNomeAluno);
		setAnoContrato(anoContrato);
	}
	public ContratoEntity(Long id, Long alunoId, String alunoNomeAluno, Long anoContrato, AtivoInativo statusContrato) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(alunoNomeAluno);
		setAnoContrato(anoContrato);
		setStatusContrato(statusContrato);
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
