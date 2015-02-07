/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.ProfessorDisciplinaEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.DisciplinasEntity.class,com.consisti.sisgesc.entidade.TurmaEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.TurmaProfessorEntity.class,
								nomeColecao = "turmaProfessor", numNovos = 1,
								cardinalidade = "1..*", porDemanda = false,
								propReferenciaDesprezar = "turma")


		}
	)
	
//Gerado em 18/03/2013 22:16 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.gerirprofessormdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
