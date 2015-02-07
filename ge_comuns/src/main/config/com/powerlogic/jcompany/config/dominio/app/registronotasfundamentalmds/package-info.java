/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE com SubDetalhe *****************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.DisciplinasEntity.class,com.consisti.sisgesc.entidade.TurmaEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SUBDETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.RegistroNotasFundamentalDetEntity.class,
								nomeColecao = "registroNotasFundamentalDet", numNovos = 1,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "turma",
								subDetalhe = @PlcConfigSubDetalhe(classe = com.consisti.sisgesc.entidade.RegistroNotasFundamentalSubDetEntity.class,
																nomeColecao = "registroNotasFundamentalSubDet", numNovos = 1,
																cardinalidade = "0..*",
																propReferenciaDesprezar = "nomeAluno")
)


		}
	)
	
//Gerado em 20/03/2013 22:10 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.registronotasfundamentalmds;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
import com.powerlogic.jcompany.config.dominio.PlcConfigSubDetalhe;
