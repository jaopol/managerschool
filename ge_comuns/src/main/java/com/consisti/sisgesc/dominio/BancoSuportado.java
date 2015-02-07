package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum BancoSuportado {
    
	B001( "001 - BANCO DO BRASIL" ) /* bancoSuportado.B001=BANCO DO BRASIL */,
	B004( "004 - BANCO DO NORDESTE DO BRASIL" ) /* bancoSuportado.B004=BANCO DO NORDESTE DO BRASIL */,
	B021( "021 - BANCO DO ESTADO DO ESPIRITO SANTO" ) /* bancoSuportado.B021=BANCO DO ESTADO DO ESPIRITO SANTO */,
	B033( "033 - BANCO SANTANDER" ) /* bancoSuportado.B033=BANCO SANTANDER */,
	B041( "041 - BANCO DO NORDESTE DO BRASIL" ) /* bancoSuportado.B041=BANCO DO NORDESTE DO BRASIL */,
	B077( "077 - BANCO INTEMEDIUM" ) /* bancoSuportado.B077=BANCO INTEMEDIUM */,
	B104( "104 - CAIXA ECONOMICA FEDERAL" ) /* bancoSuportado.B104=CAIXA ECONOMICA FEDERAL */,
	B151( "151 - NOSSA CAIXA" ) /* bancoSuportado.B151=NOSSA CAIXA */,
	B237( "237 - BANCO BRADESCO" ) /* bancoSuportado.B237=BANCO BRADESCO */,
	B341( "341 - BANCO ITAU" ) /* bancoSuportado.B341=BANCO ITAU */,
	B356( "356 - BANCO ABN AMRO REAL" ) /* bancoSuportado.B356=BANCO ABN AMRO REAL */,
	B389( "389 - MERCANTIL DO BRASIL" ) /* bancoSuportado.B389=MERCANTIL DO BRASIL */,
	B399( "399 - HSBC" ) /* bancoSuportado.B399=HSBC */,
	B409( "409 - UNIBANCO" ) /* bancoSuportado.B409=UNIBANCO */,
	B422( "422 - BANCO SAFRA" ) /* bancoSuportado.B422=BANCO SAFRA */,
	B453( "453 - BANCO RURAL" ) /* bancoSuportado.B453=BANCO RURAL */,
	B748( "748 - BANCO SICREDI" ) /* bancoSuportado.B748=BANCO SICREDI */,
	B756( "756 - BANCOOB" ) /* bancoSuportado.B756=BANCOOB */;

	private String descricao;
	
	BancoSuportado( String descricao ) {
		this.descricao = descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}
	
	public String getDescricao(){
		return descricao;
	}
}
