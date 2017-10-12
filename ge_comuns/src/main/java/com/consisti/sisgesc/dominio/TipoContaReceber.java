package com.consisti.sisgesc.dominio;

/**
 * Enum de dom�nio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoContaReceber {
    
	M /* tipoContaReceber.M=MENSALIDADE */,
	D /* tipoContaReceber.D=DIARIO */,
	E /* tipoContaReceber.E=EVENTO */;

	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
}
