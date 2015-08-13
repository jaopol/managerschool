/** ******************************** LÓGICA-PADRÃO: CRUD *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.EmpresaEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CRUD,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 19/03/2015 20:36 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.cadastroempresaman;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;
