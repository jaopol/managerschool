/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.BoletimFundamentalEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.DisciplinasEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.BoletimFundamentalDetEntity.class,
								nomeColecao = "boletimFundamentalDet", numNovos = 4,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "disciplina")


		}
	)
	
//Gerado em 14/04/2013 12:45 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.boletimfundamentalmdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
