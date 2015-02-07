package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum Sexo {
    
	M("MASCULINO") /* sexo.M=Masculino */,
	F("FEMININO")/* sexo.F=Feminino */;

	private String descricao;
	
	Sexo(String descricao){
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
