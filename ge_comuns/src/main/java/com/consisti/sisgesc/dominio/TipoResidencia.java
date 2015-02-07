package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoResidencia {
    
	PQ ("") /* tipoResidencia.PQ=Própia Quitada */,
	PF ("")/* tipoResidencia.PF=Própia Financiada */,
	AL ("")/* tipoResidencia.AL=Alugada */,
	FA ("")/* tipoResidencia.FA=Famíliar */,
	CE ("")/* tipoResidencia.CE=Cedida */;

	private String descricao;
	
	TipoResidencia(String descricao){
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
