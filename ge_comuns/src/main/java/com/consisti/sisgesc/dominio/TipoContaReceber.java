package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoContaReceber {
    
	M /* tipoContaReceber.M=MENSALIDADE */,
	D /* tipoContaReceber.D=DIARIO */;

	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
}
