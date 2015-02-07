package com.consisti.sisgesc.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Valid;

import com.consisti.sisgesc.dominio.PeriodoAluno;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples;
import com.powerlogic.jcompany.dominio.valida.PlcValidacaoUnificada;
import com.powerlogic.jcompany.dominio.valida.PlcValFormatoSimples.FormatoSimples;


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class TabelaPreco extends AppBaseEntity {

	@OneToMany (targetEntity = TabelaPrecoDetEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="tabelaPreco")
	@ForeignKey(name="FK_TABELAPRECODET_TABELAPRECO")
	@Valid
	@JoinColumn (name = "ID_TABELA_PRECO")
	private List<TabelaPrecoDet> tabelaPrecoDet;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_TABELA_PRECO")
	@Column (name = "ID_TABELA_PRECO", nullable=false, length=5)
	private Long id;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "DESCRICAO", nullable=false, length=50)
	private String descricao;
	
	@ManyToOne (targetEntity = TurmaEntity.class)
	@ForeignKey(name="FK_TABELAPRECO_TURMA")
	@JoinColumn (name = "ID_TURMA", nullable=false)
	private Turma turma;
	
	@PlcValFormatoSimples(formato=FormatoSimples.MAIUSCULO)
	@Column (name = "OBSERVACAO", length=200)
	private String observacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma=turma;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public List<TabelaPrecoDet> getTabelaPrecoDet() {
		return tabelaPrecoDet;
	}

	public void setTabelaPrecoDet(List<TabelaPrecoDet> tabelaPrecoDet) {
		this.tabelaPrecoDet=tabelaPrecoDet;
	}

}
