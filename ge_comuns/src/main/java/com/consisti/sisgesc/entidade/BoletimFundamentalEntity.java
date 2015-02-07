package com.consisti.sisgesc.entidade;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.dominio.SituacaoBoletim;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.AccessType;
import javax.persistence.Entity;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.entidade.AlunoEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="BOLETIM_FUNDAMENTAL")
@SequenceGenerator(name="SE_BOLETIM_FUNDAMENTAL", sequenceName="SE_BOLETIM_FUNDAMENTAL")
@AccessType("field")


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="BoletimFundamentalEntity.queryMan", query="from BoletimFundamentalEntity obj"),
	@NamedQuery(name="BoletimFundamentalEntity.querySel", query="select new BoletimFundamentalEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.anoLetivo, obj.situacaoBoletim, obj.turma.id , obj.turma.descricao) from BoletimFundamentalEntity obj left outer join obj.aluno left outer join obj.turma order by obj.id asc"),
	@NamedQuery(name="BoletimFundamentalEntity.querySelLookup", query="select new BoletimFundamentalEntity (obj.id, obj.anoLetivo, obj.situacaoBoletim) from BoletimFundamentalEntity obj where obj.id = ? order by obj.id asc")
})
public class BoletimFundamentalEntity extends BoletimFundamental {
 	
	private transient Long maxId;
	private transient String situacaoBoletimStr;
	private transient String documento;
    
	/*
     * Construtor padrão
     */
    public BoletimFundamentalEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    private transient String anoLetivoStr;

    public String getAnoLetivoStr()   {
       if (getAnoLetivo() != null) 
           return( getAnoLetivo() + "" );
       else
           return "";
    }
    public void setAnoLetivoStr( String novoAnoLetivoStr )   {
        anoLetivoStr = novoAnoLetivoStr;
        if (anoLetivoStr != null && !anoLetivoStr.equals("") && 
	    org.apache.commons.lang.math.NumberUtils.isNumber(anoLetivoStr)) 
            setAnoLetivo(new Integer(anoLetivoStr));
        if (anoLetivoStr.equals("")) 
            setAnoLetivo(null);
    }

	public BoletimFundamentalEntity(Long id, Integer anoLetivo, SituacaoBoletim situacaoBoletim) {
		this.setId(id);
		this.setAnoLetivo(anoLetivo);
		this.setSituacaoBoletim(situacaoBoletim);
		this.setDocumento( AppConstantesComuns.TIPO_DOCUMENTO.BOLETIM );
	}
	
	public BoletimFundamentalEntity(Long idMax, Long idAluno, String nomeAluno, Integer anoLetivo ) {
		this.setMaxId(idMax);
		if( getAluno() == null )
			setAluno( new AlunoEntity() );
		getAluno().setId( idAluno );
		getAluno().setNomeAluno(nomeAluno);
		this.setAnoLetivo(anoLetivo);
	}
	
	@Override
	public String toString() {
		return getSituacaoBoletim() != null ? getAnoLetivoStr()+"-"+getSituacaoBoletim().toString() : "";
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Aluno.class,com.consisti.sisgesc.entidade.Turma.class};
	}

	public BoletimFundamentalEntity(Long id, Long alunoId, String alunoNomeAluno, Integer anoLetivo, SituacaoBoletim situacaoBoletim, Long turmaId, String turmaDescricao) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(alunoNomeAluno);
		setAnoLetivo(anoLetivo);
		if( SituacaoBoletim.A.equals( situacaoBoletim ) ){
			setSituacaoBoletimStr( "APROVADO" );
		}
		else if( SituacaoBoletim.C.equals( situacaoBoletim ) ){
			setSituacaoBoletimStr( "EM CURSO" );
		}
		else if( SituacaoBoletim.R.equals( situacaoBoletim ) ){
			setSituacaoBoletimStr( "REPROVADO" );
		}
		//setSituacaoBoletim(situacaoBoletim);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		
		this.setDocumento( AppConstantesComuns.TIPO_DOCUMENTO.BOLETIM );
	}
	public Long getMaxId() {
		return maxId;
	}
	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}
	public String getSituacaoBoletimStr() {
		return situacaoBoletimStr;
	}
	public void setSituacaoBoletimStr(String situacaoBoletimStr) {
		this.situacaoBoletimStr = situacaoBoletimStr;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public BoletimFundamentalEntity(Long id, Long alunoId, String alunoMatricula, Integer anoLetivo, SituacaoBoletim situacaoBoletim) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setMatricula(alunoMatricula);
		setAnoLetivo(anoLetivo);
		setSituacaoBoletim(situacaoBoletim);
	}
}
