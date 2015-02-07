package com.consisti.sisgesc.entidade;

import java.math.BigDecimal;
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


@SuppressWarnings("serial")
@MappedSuperclass
@PlcValidacaoUnificada
public abstract class TabelaPrecoDet extends AppBaseEntity {

	@ManyToOne (targetEntity = TabelaPrecoEntity.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_TABELAPRECODET_TABELAPRECO")
	@JoinColumn (name = "ID_TABELA_PRECO", nullable=false)
	private TabelaPreco tabelaPreco;

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_TABELA_PRECO_DET")
	@Column (name = "ID_TABELA_PRECO_DET", nullable=false, length=5)
	private Long id;
	
	@Column (name = "TEMPO_HRS", nullable=false, length=5 )
	private String tempoHrs;
	
	@Column (name = "VALOR", nullable=false, length=10, precision=10, scale=2)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getTempoHrs() {
		return tempoHrs;
	}

	public void setTempoHrs(String tempoHrs) {
		this.tempoHrs=tempoHrs;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor=valor;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco=tabelaPreco;
	}

}
