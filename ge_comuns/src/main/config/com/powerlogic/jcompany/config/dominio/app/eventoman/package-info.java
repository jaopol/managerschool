/** ******************************** LÓGICA-PADRÃO: CRUD *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.EventoEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CRUD,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 30/01/2016 15:14 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.eventoman;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;
