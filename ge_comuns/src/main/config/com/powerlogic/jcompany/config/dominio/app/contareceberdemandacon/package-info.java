/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.AlunoEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class, 
				com.consisti.sisgesc.entidade.financeiro.BancoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CONSULTA,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 06/08/2014 16:12 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.contareceberdemandacon;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;

