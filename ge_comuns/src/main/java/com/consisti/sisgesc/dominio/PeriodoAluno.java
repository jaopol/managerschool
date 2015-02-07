package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum PeriodoAluno {
    
	I("INTEGRAL") /* periodoAluno.I=Integral */,
	M("MANHÃ") /* periodoAluno.M=Manha */,
	T("TARDE") /* periodoAluno.T=Tarde */;

	private String descricao;
	
	PeriodoAluno(String descricao) {
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
