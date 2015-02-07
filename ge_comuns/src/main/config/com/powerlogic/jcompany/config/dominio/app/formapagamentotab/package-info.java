/** ******************************** LÓGICA-PADRÃO: TABULAR *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.TABULAR,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 06/06/2013 21:13 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.formapagamentotab;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
