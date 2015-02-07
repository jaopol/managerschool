/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE com SubDetalhe *****************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.CalendarioAnualEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SUBDETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.CalendarioAnualDetEntity.class,
								nomeColecao = "calendarioAnualDet", numNovos = 4,
								cardinalidade = "0..4", porDemanda = false,
								propReferenciaDesprezar = "descricao",
								subDetalhe = @PlcConfigSubDetalhe(classe = com.consisti.sisgesc.entidade.CalendarioAnualSubDetEntity.class,
																nomeColecao = "calendarioAnualSubDet", numNovos = 4,
																cardinalidade = "0..*",
																propReferenciaDesprezar = "dataFeriado")
)


		}
	)
	
//Gerado em 03/04/2013 16:23 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.calendarioanualmds;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
import com.powerlogic.jcompany.config.dominio.PlcConfigSubDetalhe;
