package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum CarteiraBanco {
    
	C101( "101-COBRANÇA SIMPLES RÁPIDA COM REGISTRO " ) /* carteiraBanco.C101=101 */,
	C102( "102-COBRANÇA SIMPLES SEM REGISTRO" ) /* carteiraBanco.C102=102 */,
	C201( "201-PENHOR RÁPIDA COM REGISTRO" ) /* carteiraBanco.C201=201 */;
	
	private String descricao;
	private Integer codigoInt;
	
	CarteiraBanco( String descricao ) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}

	public Integer getCodigoInt() {
		return codigoInt;
	}

	public void setCodigoInt(Integer codigoInt) {
		if( C101.equals( getCodigo() ) ){
			this.codigoInt = new Integer( 101 );
		}
		else if ( C102.equals( getCodigo() ) ){
			this.codigoInt = new Integer( 102 );
		}
		else if( C201.equals( getCodigo() ) ){
			this.codigoInt = new Integer( 201 );
		}
		this.codigoInt = codigoInt;
	}

}
