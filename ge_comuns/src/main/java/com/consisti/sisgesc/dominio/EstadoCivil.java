package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum EstadoCivil {
    
	S("SOLTEIRO(A)") /* estadoCivil.S=Solteiro(a) */,
	C("CASADO(A)") /* estadoCivil.C=Casado(a) */,
	D("DIVORCIADO(A)") /* estadoCivil.D=Divorciado(a) */,
	V("VIÚVO(A)")/* estadoCivil.V=Viúvo(a) */;

	String descricao;
	
	EstadoCivil(String descricao){
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
