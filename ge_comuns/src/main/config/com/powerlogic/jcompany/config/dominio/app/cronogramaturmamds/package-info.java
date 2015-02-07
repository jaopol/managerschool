/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE com SubDetalhe *****************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.CronogramaTurmaEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.DisciplinasEntity.class,com.consisti.sisgesc.entidade.CronogramaTurmaDisciplinaEntity.class,com.consisti.sisgesc.entidade.ConteudoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SUBDETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
				@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.CronogramaTurmaDisciplinaEntity.class,
						nomeColecao = "cronogramaTurmaDisciplina", numNovos = 1,
						cardinalidade = "0..*", porDemanda = false,
						propReferenciaDesprezar = "disciplina",
								subDetalhe = @PlcConfigSubDetalhe(classe = com.consisti.sisgesc.entidade.CronogramaTurmaDisciplinaConteudoEntity.class,
																nomeColecao = "cronogramaTurmaDisciplinaConteudo", numNovos = 4,
																cardinalidade = "0..*",
																propReferenciaDesprezar = "conteudo")
								
								)

		}
	)
	
//Gerado em 19/03/2013 18:46 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.cronogramaturmamds;

import com.consisti.sisgesc.entidade.CronogramaTurmaDisciplinaConteudo;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigSubDetalhe;

