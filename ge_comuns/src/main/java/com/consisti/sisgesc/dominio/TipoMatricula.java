package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoMatricula {
    
	DE("DESCONTO") /* TipoMatricula.DE=Desconto */,
	IS("ISENTO") /* TipoMatricula.IS=Isento */,
	IN("INTEGRAL") /* TipoMatricula.IN=Integral */,
	PR("PROPORCIONAL") /* TipoMatricula.PR=Proporcional */;
	
	private String descricao;
	
	TipoMatricula(String descricao){
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
