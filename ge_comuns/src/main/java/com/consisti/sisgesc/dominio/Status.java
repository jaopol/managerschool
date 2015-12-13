package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum Status {
    
	A("ATIVO"),
	I("INATIVO");

	private String descricao;
	
	Status(String descricao){
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
