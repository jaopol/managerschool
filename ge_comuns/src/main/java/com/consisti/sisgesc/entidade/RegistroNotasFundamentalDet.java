package com.consisti.sisgesc.entidade;


import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.validator.Valid;

@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class RegistroNotasFundamentalDet extends AppBaseEntity {

	
	@OneToMany (targetEntity = RegistroNotasFundamentalSubDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="registroNotasFundamentalDet")
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALSUBDET_REGISTRONOTASFUNDAMENTALDET")
	@Valid
	@JoinColumn (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL_DET")
	private List<RegistroNotasFundamentalSubDet> registroNotasFundamentalSubDet;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_REGISTRO_NOTAS_FUNDAMENTAL_DET")
	@Column (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL_DET", nullable=false, length=5)
	private Long id;
	
	@ManyToOne (targetEntity = RegistroNotasFundamentalEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALDET_REGISTRONOTASFUNDAMENTAL")
	@JoinColumn (name = "ID_REGISTRO_NOTAS_FUNDAMENTAL", nullable=false)
	private RegistroNotasFundamental registroNotasFundamental;
	
	@ManyToOne (targetEntity = TurmaEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_REGISTRONOTASFUNDAMENTALDET_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public RegistroNotasFundamental getRegistroNotasFundamental() {
		return registroNotasFundamental;
	}

	public void setRegistroNotasFundamental(RegistroNotasFundamental registroNotasFundamental) {
		this.registroNotasFundamental=registroNotasFundamental;
	}

	public List<RegistroNotasFundamentalSubDet> getRegistroNotasFundamentalSubDet() {
		return registroNotasFundamentalSubDet;
	}

	public void setRegistroNotasFundamentalSubDet(List<RegistroNotasFundamentalSubDet> registroNotasFundamentalSubDet) {
		this.registroNotasFundamentalSubDet=registroNotasFundamentalSubDet;
	}

}
