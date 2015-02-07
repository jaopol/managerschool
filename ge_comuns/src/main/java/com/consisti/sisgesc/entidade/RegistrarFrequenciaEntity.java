package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.consisti.sisgesc.dominio.AbertoFechado;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.dominio.PeriodoAluno;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="REGISTRAR_FREQUENCIA")
@SequenceGenerator(name="SE_REGISTRAR_FREQUENCIA", sequenceName="SE_REGISTRAR_FREQUENCIA")
@AccessType("field")

@PlcValidacaoUnificada

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="RegistrarFrequenciaEntity.querySel3", query="select new RegistrarFrequenciaEntity(obj.id) from RegistrarFrequenciaEntity obj order by obj.id asc"),
	@NamedQuery(name="RegistrarFrequenciaEntity.querySel2", query="select new RegistrarFrequenciaEntity(obj.id) from RegistrarFrequenciaEntity obj order by obj.id asc"),
	@NamedQuery(name="RegistrarFrequenciaEntity.queryMan", query="from RegistrarFrequenciaEntity obj"),
	@NamedQuery(name="RegistrarFrequenciaEntity.querySel", query="select new RegistrarFrequenciaEntity(obj.id, obj.turma.id , obj.turma.descricao, obj.dataCadastro, obj.status, obj.professor.id , obj.professor.nome, obj.periodo, obj.tipoEducacao) from RegistrarFrequenciaEntity obj left outer join obj.turma left outer join obj.professor order by obj.id asc"),
	@NamedQuery(name="RegistrarFrequenciaEntity.querySelLookup", query="select new RegistrarFrequenciaEntity (obj.id, obj.turma, obj.status) from RegistrarFrequenciaEntity obj where obj.id = ? order by obj.id asc")
})
public class RegistrarFrequenciaEntity extends RegistrarFrequencia {
 	
	private transient String mesAnoFrequencia;
	
    /*
     * Construtor padrão
     */
    public RegistrarFrequenciaEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getTurmaStr() {
    	return getTurma()!=null? getTurma().getIdAux() : null;
    }
    
    public void setTurmaStr(String turmaStr) {
    	if (turmaStr != null && turmaStr.trim().length() > 0) {
    		Long id = Long.valueOf(turmaStr);
    		if (getTurma()==null || !id.equals(getTurma().getId())) {
    			com.consisti.sisgesc.entidade.Turma obj = new com.consisti.sisgesc.entidade.TurmaEntity();
    			obj.setId(id);
    			this.setTurma(obj);
    		}
    	} else {
    		this.setTurma(null);
    	}
    }

	public RegistrarFrequenciaEntity(Long id, Turma turma, AbertoFechado status) {
		this.setId(id);
		this.setTurma(turma);
		this.setStatus(status);
	}
	@Override
	public String toString() {
		return getTurmaStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Turma.class,com.consisti.sisgesc.entidade.Funcionario.class};
	}

	public RegistrarFrequenciaEntity(Long id, Long turmaId, String turmaDescricao, java.util.Date dataCadastro, AbertoFechado status, Long professorId, String professorNome, PeriodoAluno periodo, TipoEducacao tipoEducacao) {
		setId(id);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		setDataCadastro(dataCadastro);
		setStatus(status);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		setPeriodo(periodo);
		setTipoEducacao(tipoEducacao);
	}
	public String getMesAnoFrequencia() {
		return mesAnoFrequencia;
	}
	public void setMesAnoFrequencia(String mesAnoFrequencia) {
		this.mesAnoFrequencia = mesAnoFrequencia;
	}
	public RegistrarFrequenciaEntity(Long id) {
		setId(id);
	}
}
