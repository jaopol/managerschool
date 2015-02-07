package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoFavorecido {
    
	F("FÍSICA")/* tipoFavorecido.F=FÍSICA */,
	J("JURÍDICA") /* tipoFavorecido.J=JURÍDICA */,
	I("IMPOSTO") /* tipoFavorecido.I=IMPOSTO */;

	private String descricao;
	
	TipoFavorecido(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
}
