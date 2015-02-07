package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoPessoa {
    
	F("FÍSICA") /* TipoPessoa.F=Física */,
	J("JURÍDICA")/* TipoPessoa.J=Jurídica */;

	private String descricao;
	
	TipoPessoa(String descricao){
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
