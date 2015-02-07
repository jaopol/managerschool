package com.consisti.sisgesc.entidade;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.AbertoFechado;
@Entity
@Table(name="REGISTRO_NOTAS_FUNDAMENTAL")
@SequenceGenerator(name="SE_REGISTRO_NOTAS_FUNDAMENTAL", sequenceName="SE_REGISTRO_NOTAS_FUNDAMENTAL")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="RegistroNotasFundamentalEntity.queryMan", query="from RegistroNotasFundamentalEntity obj"),
	@NamedQuery(name="RegistroNotasFundamentalEntity.querySel", query="select new RegistroNotasFundamentalEntity(obj.id, obj.professor.id , obj.professor.nome, obj.disciplina.id , obj.disciplina.descricao, obj.anoLetivo, obj.bimestre) from RegistroNotasFundamentalEntity obj left outer join obj.professor left outer join obj.disciplina order by obj.anoLetivo asc"),
	@NamedQuery(name="RegistroNotasFundamentalEntity.querySelLookup", query="select new RegistroNotasFundamentalEntity (obj.id, obj.professor) from RegistroNotasFundamentalEntity obj where obj.id = ? order by obj.id asc")
})
public class RegistroNotasFundamentalEntity extends RegistroNotasFundamental {
 	
	private transient boolean bloqueiaCampo = false;
	
	private transient Long idDisciplina;
	private transient Long idRegistroSubDet;
	private transient BigDecimal notaTrabalho;
	private transient BigDecimal notaProva;
	private transient BigDecimal avaliacao;
	private transient BigDecimal conceito;
	private transient Integer aulasDadas;
	private transient Integer faltas;
	private transient BigDecimal total;
	private transient String notaReprovada;
	
	
    /*
     * Construtor padrão
     */
    public RegistroNotasFundamentalEntity() {
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

	public RegistroNotasFundamentalEntity(Long id, Funcionario professor) {
		this.setId(id);
		this.setProfessor(professor);
	}
	
	public RegistroNotasFundamentalEntity(AbertoFechado status, Integer bimestre) {
		this.setStatus(status);
		this.setBimestre(bimestre);
	}
	
	@Override
	public String toString() {
		return getProfessorStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Funcionario.class,com.consisti.sisgesc.entidade.Disciplinas.class};
	}

	public RegistroNotasFundamentalEntity(Long id, Long professorId, String professorNome, Long disciplinaId, String disciplinaDescricao, Integer anoLetivo, Integer bimestre) {
		setId(id);
		if (getProfessor() == null){
			setProfessor(new FuncionarioEntity());
		}
		getProfessor().setId(professorId);
		getProfessor().setNome(professorNome);
		if (getDisciplina() == null){
			setDisciplina(new DisciplinasEntity());
		}
		getDisciplina().setId(disciplinaId);
		getDisciplina().setDescricao(disciplinaDescricao);
		setAnoLetivo(anoLetivo);
		setBimestre(bimestre);
	}
	public boolean isBloqueiaCampo() {
		return bloqueiaCampo;
	}
	public void setBloqueiaCampo(boolean bloqueiaCampo) {
		this.bloqueiaCampo = bloqueiaCampo;
	}
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Long getIdRegistroSubDet() {
		return idRegistroSubDet;
	}
	public void setIdRegistroSubDet(Long idRegistroSubDet) {
		this.idRegistroSubDet = idRegistroSubDet;
	}
	public BigDecimal getNotaTrabalho() {
		return notaTrabalho;
	}
	public void setNotaTrabalho(BigDecimal notaTrabalho) {
		this.notaTrabalho = notaTrabalho;
	}
	public BigDecimal getNotaProva() {
		return notaProva;
	}
	public void setNotaProva(BigDecimal notaProva) {
		this.notaProva = notaProva;
	}
	public BigDecimal getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(BigDecimal avaliacao) {
		this.avaliacao = avaliacao;
	}
	public BigDecimal getConceito() {
		return conceito;
	}
	public void setConceito(BigDecimal conceito) {
		this.conceito = conceito;
	}
	public Integer getAulasDadas() {
		return aulasDadas;
	}
	public void setAulasDadas(Integer aulasDadas) {
		this.aulasDadas = aulasDadas;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getNotaReprovada() {
		return notaReprovada;
	}
	public void setNotaReprovada(String notaReprovada) {
		this.notaReprovada = notaReprovada;
	}
}
