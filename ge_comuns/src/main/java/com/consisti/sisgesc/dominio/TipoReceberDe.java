package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoReceberDe {
    
	A("ALUNO") /* tipoReceberDe.A=Aluno */,
	O("OUTROS") /* tipoReceberDe.O=Outros */,
	E("EVENTOS") /* tipoReceberDe.E=Eventos */;

	private String descricao;
	
	TipoReceberDe(String descricao){
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
