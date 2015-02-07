package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoEducacao {
    
	I("Infantil") /* tipoEducacao.I=Infantil */,
	F("Fundamental")/* tipoEducacao.F=Fundamental */;

	private String descricao;
	
	TipoEducacao(String descricao){
		this.descricao = descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}

	public String getDescricao() {
		return descricao;
	}
	
}
