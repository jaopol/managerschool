/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.BoletimFundamentalEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.DisciplinasEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SELECAO,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 14/04/2013 12:45 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.boletimfundamentalsel;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;

