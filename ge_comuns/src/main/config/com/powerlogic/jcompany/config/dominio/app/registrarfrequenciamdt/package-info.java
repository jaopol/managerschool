/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.RegistrarFrequenciaEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.FuncionarioEntity.class,com.consisti.sisgesc.entidade.AlunoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.FrequenciaAlunoEntity.class,
								nomeColecao = "frequenciaAluno", numNovos = 4,
								cardinalidade = "0..*", porDemanda = true,
								propReferenciaDesprezar = "aluno")


		}
	)
	
//Gerado em 12/10/2013 13:00 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.registrarfrequenciamdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
