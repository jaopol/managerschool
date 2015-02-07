package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum SituacaoBoletim {
    
	A /* situacaoBoletim.A=Aprovado */,
	R /* situacaoBoletim.R=Reprovado */,
	C /* situacaoBoletim.C=EM CUSRO */;

	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
}
