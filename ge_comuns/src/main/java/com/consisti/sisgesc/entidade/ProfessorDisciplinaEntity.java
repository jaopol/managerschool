package com.consisti.sisgesc.entidade;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.entidade.DisciplinasEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="PROFESSOR_DISCIPLINA")
@SequenceGenerator(name="SE_PROFESSOR_DISCIPLINA", sequenceName="SE_PROFESSOR_DISCIPLINA")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="ProfessorDisciplinaEntity.querySel2", query="select new ProfessorDisciplinaEntity(obj.id, pro.id , pro.nome, obj.dataCadastro, disc.descricao) from ProfessorDisciplinaEntity obj left outer join obj.professor pro left outer join obj.disciplina disc order by pro.nome asc"),
	@NamedQuery(name="ProfessorDisciplinaEntity.queryMan", query="from ProfessorDisciplinaEntity obj"),
	@NamedQuery(name="ProfessorDisciplinaEntity.querySel", query="select new ProfessorDisciplinaEntity(obj.id, pro.id , pro.nome, obj.dataCadastro, obj.observacao, disc.descricao) from ProfessorDisciplinaEntity obj left outer join obj.professor pro left outer join obj.disciplina disc order by pro.nome asc"),
	@NamedQuery(name="ProfessorDisciplinaEntity.querySelLookup", query="select new ProfessorDisciplinaEntity (obj.id, obj.professor) from ProfessorDisciplinaEntity obj where obj.id = ? order by obj.id asc")
})
public class ProfessorDisciplinaEntity extends ProfessorDisciplina {
	
	private transient String disciplinaAux;
 	
    /*
     * Construtor padrão
     */
    public ProfessorDisciplinaEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getProfessorStr() {
    	return getProfessor()!=null? getProfessor().getIdAux() : null;
    }
    
    public void setProfessorStr(String professorStr) {
    	if (professorStr != null && professorStr.trim().length() > 0) {
    		Long id = Long.valueOf(professorStr);
    		if (getProfessor()==null || !id.equals(getProfessor().getId())) {
    			com.consisti.sisgesc.entidade.Funcionario obj = new com.consisti.sisgesc.entidade.FuncionarioEntity();
    			obj.setId(id);
    			this.setProfessor(obj);
    		}
    	} else {
    		this.setProfessor(null);
    	}
    }

	public ProfessorDisciplinaEntity(Long id, Funcionario professor) {
		this.setId(id);
		this.setProfessor(professor);
	}
	
	
	@Override
	public String toString() {
		return getProfessorStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Funcionario.class};
	}

	public ProfessorDisciplinaEntity(Long id, Long professorId, String professorNome, java.util.Date dataCadastro, String observacao, String descricaoDisciplina) {
		setId(id);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		setDataCadastro(dataCadastro);
		setObservacao(observacao);
		setDisciplinaAux(descricaoDisciplina);
	}
	
	public String getDisciplinaAux() {
		return disciplinaAux;
	}
	public void setDisciplinaAux(String disciplinaAux) {
		this.disciplinaAux = disciplinaAux;
	}
	public ProfessorDisciplinaEntity(Long id, Long professorId, String professorNome, java.util.Date dataCadastro) {
		setId(id);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		setDataCadastro(dataCadastro);
	}
	
	public ProfessorDisciplinaEntity(Long id, Long professorId, String professorNome, java.util.Date dataCadastro, String descricaoDisciplina) {
		setId(id);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		setDataCadastro(dataCadastro);
		setDisciplinaAux(descricaoDisciplina);
	}
	public ProfessorDisciplinaEntity(Long id, Long professorId, String professorNome, java.util.Date dataCadastro, Long disciplinaId, String disciplinaDescricao) {
		setId(id);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		setDataCadastro(dataCadastro);
		if (getDisciplina() == null){
			setDisciplina(new DisciplinasEntity());
		}
		getDisciplina().setId(disciplinaId);
		getDisciplina().setDescricao(disciplinaDescricao);
	}
}
