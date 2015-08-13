/** ******************************** LÓGICA-PADRÃO: TABULAR *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.TABULAR,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 12/04/2015 10:20 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.produtovendatab;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
