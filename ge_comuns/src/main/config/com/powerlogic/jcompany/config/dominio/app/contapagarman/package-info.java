/** ******************************** L�GICA-PADR�O: CRUD *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ContaPagarEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class, com.consisti.sisgesc.entidade.financeiro.PlanoContasEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CRUD,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 17/06/2013 21:48 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.contapagarman;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;

