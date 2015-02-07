/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.TabelaPrecoEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.TabelaPrecoDetEntity.class,
								nomeColecao = "tabelaPrecoDet", numNovos = 1,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "tempoHrs")


		}
	)
	
//Gerado em 23/01/2013 00:03 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.tabelaprecomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
