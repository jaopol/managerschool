package com.consisti.sisgesc.dominio;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum Uf {
    
	AC("ACRE") /* uf.AC=AC */,
	AL("ALAGOAS") /* uf.AL=AL */,
	AP("AMAPÁ") /* uf.AP=AP */,
	AM("AMAZONAS") /* uf.AM=AM */,
	BA("BAHIA") /* uf.BA=BA */,
	CE("CEARÁ") /* uf.CE=CE */,
	DF("DISTRITO FEDERAL") /* uf.DF=DF */,
	ES("ESPÍRITO SANTO") /* uf.ES=ES */,
	GO("GOIÁS") /* uf.GO=GO */,
	MA("MARANHÃO") /* uf.MA=MA */,
	MT("MATO GROSSO") /* uf.MT=MT */,
	MS("MATO GROSSO DO SUL") /* uf.MS=MS */,
	MG("MINAS GERAIS") /* uf.MG=MG */,
	PA("PARÁ") /* uf.PA=PA */,
	PB("PARAÍBA") /* uf.PB=PB */,
	PR("PARANÁ") /* uf.PR=PR */,
	PE("PERNAMBUCO") /* uf.PE=PE */,
	PI("PÍAUI") /* uf.PI=PI */,
	RJ("RIO DE JANEIRO") /* uf.RJ=RJ */,
	RN("RIO GRANDE DO NORTE") /* uf.RN=RN */,
	RS("RIO GRANDE DO SUL") /* uf.RS=RS */,
	RO("RONDÔNIA") /* uf.RO=RO */,
	RR("RORÂIMA") /* uf.RR=RR */,
	SC("SANTA CATARINA") /* uf.SC=SC */,
	SP("SÃO PAULO") /* uf.SP=SP */,
	SE("SERGIPE") /* uf.SE=SE */,
	TO("TOCANTINS") /* uf.TO=TO */;

	public String descricao;
	
	Uf(String descricao){
		this.descricao = descricao;
	}
	
    /**
     * @return Retorna o codigo.
     */
	public String getCodigo() {
		return this.toString();
	}

	/**
	 * Retorna a descricao do UF
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	
}
