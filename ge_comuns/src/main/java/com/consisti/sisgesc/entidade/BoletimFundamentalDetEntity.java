package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.powerlogic.jcompany.comuns.anotacao.PlcTabular;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="BOLETIM_FUNDAMENTAL_DET")
@SequenceGenerator(name="SE_BOLETIM_FUNDAMENTAL_DET", sequenceName="SE_BOLETIM_FUNDAMENTAL_DET")
@AccessType("field")

@PlcTabular(propReferenciaDesprezar="disciplina")

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="BoletimFundamentalDetEntity.querySelLookup", query="select new BoletimFundamentalDetEntity (obj.id, obj.disciplina) from BoletimFundamentalDetEntity obj where obj.id = ? order by obj.id asc")
})
public class BoletimFundamentalDetEntity extends BoletimFundamentalDet {
	
	private transient PlcSimNao reprovaRegistro;
	private transient Long idRegistroNotas;
	private transient Long idRegistroSubDet;
	private transient String notaReprovada;
 	
    /*
     * Construtor padrão
     */
    public BoletimFundamentalDetEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getDisciplinaAux() {
    	return getDisciplina()!=null? getDisciplina().getIdAux() : null;
    }
    
    public void setDisciplinaAux(String disciplinaAux) {
    	if (disciplinaAux != null && disciplinaAux.trim().length() > 0) {
    		Long id = Long.valueOf(disciplinaAux);
    		if (getDisciplina()==null || !id.equals(getDisciplina().getId())) {
    			com.consisti.sisgesc.entidade.Disciplinas obj = new com.consisti.sisgesc.entidade.DisciplinasEntity();
    			obj.setId(id);
    			this.setDisciplina(obj);
    		}
    	} else {
    		this.setDisciplina(null);
    	}
    }

	public BoletimFundamentalDetEntity(Long id, Disciplinas disciplina) {
		this.setId(id);
		this.setDisciplina(disciplina);
	}
	@Override
	public String toString() {
		return getDisciplinaAux();
	}
	public PlcSimNao getReprovaRegistro() {
		return reprovaRegistro;
	}
	public void setReprovaRegistro(PlcSimNao reprovaRegistro) {
		this.reprovaRegistro = reprovaRegistro;
	}
	public Long getIdRegistroNotas() {
		return idRegistroNotas;
	}
	public void setIdRegistroNotas(Long idRegistroNotas) {
		this.idRegistroNotas = idRegistroNotas;
	}
	public String getNotaReprovada() {
		return notaReprovada;
	}
	public void setNotaReprovada(String notaReprovada) {
		this.notaReprovada = notaReprovada;
	}
	public Long getIdRegistroSubDet() {
		return idRegistroSubDet;
	}
	public void setIdRegistroSubDet(Long idRegistroSubDet) {
		this.idRegistroSubDet = idRegistroSubDet;
	}

}
