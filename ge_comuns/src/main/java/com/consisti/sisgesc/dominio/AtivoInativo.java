package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum AtivoInativo {
    
	A("ATIVO") /* ativoInativo.A=Ativo */,
	I("INATIVO") /* ativoInativo.I=Inativo */;

	private String descricao;
	
	AtivoInativo(String descricao){
		this.descricao=descricao;
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
