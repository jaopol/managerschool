package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoImpostoRenda {
    
	S("SIMPLIFICADO") /* tipoImpostoRenda.S=Simplificado */,
	C("COMPLETO") /* tipoImpostoRenda.C=Completo */;

	private String descricao;
	
	TipoImpostoRenda(String descricao){
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
