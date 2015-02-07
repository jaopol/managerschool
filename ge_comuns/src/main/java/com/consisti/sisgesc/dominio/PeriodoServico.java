package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum PeriodoServico {
    
	M("Mensal") /* periodoServico.M=Mensal */,
	D("Diário") /* periodoServico.D=Diário */;

	private String descricao;
	
	PeriodoServico(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
}
