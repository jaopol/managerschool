/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.FuncionarioEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.AlunoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.EnderecoEntity.class,
					nomeColecao = "endereco", numNovos = 1,
					cardinalidade = "1..1", porDemanda = false,
					propReferenciaDesprezar = "logradouro")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.DadoFuncionarioEntity.class,
								nomeColecao = "dadoFuncionario", numNovos = 1,
								cardinalidade = "1..1", porDemanda = false,
								propReferenciaDesprezar = "escolaridadeDesprezar")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.HorarioTrabalhoEntity.class,
								nomeColecao = "horarioTrabalho", numNovos = 1,
								cardinalidade = "1..1", porDemanda = false,
								propReferenciaDesprezar = "horaEntradaDesprezar")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.AnexoEntity.class,
								nomeColecao = "anexoFuncionario", numNovos = 2,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "descricao")


		}
	)
	
//Gerado em 31/01/2013 22:38 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.funcionariomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
