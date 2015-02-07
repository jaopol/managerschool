package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoConta {
    
	C("CONTA CORRENTE") /* tipoConta.C=Conta corrente */,
	P("POUPANÇA") /* tipoConta.P=Poupança */;

	private String descricao;
	
	TipoConta(String descricao){
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