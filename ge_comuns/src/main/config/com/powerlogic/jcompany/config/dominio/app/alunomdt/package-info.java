/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.AlunoEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.EmpresaEntity.class},
		classesDominioDiscreto = {com.consisti.sisgesc.dominio.TipoMatricula.class,
			                      com.consisti.sisgesc.dominio.EstadoCivil.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
									detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.EnderecoEntity.class,
								nomeColecao = "endereco", numNovos = 1,
								cardinalidade = "0..1", porDemanda = false,
								propReferenciaDesprezar = "logradouro")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.CertidaoNascimentoEntity.class,
								nomeColecao = "certidaoNascimento", numNovos = 1,
								cardinalidade = "0..1", porDemanda = false,
								propReferenciaDesprezar = "certidaoNascimentoIdentidadeDesprezar")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.FiliacaoPaiEntity.class,
								nomeColecao = "filiacaoPai", numNovos = 1,
								cardinalidade = "0..1", porDemanda = false,
								propReferenciaDesprezar = "nomePai")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.FiliacaoMaeEntity.class,
								nomeColecao = "filiacaoMae", numNovos = 1,
								cardinalidade = "0..1", porDemanda = false,
								propReferenciaDesprezar = "nomeMae")
,
			
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.SaudeAlunoEntity.class,
								nomeColecao = "saudeAluno", numNovos = 1,
								cardinalidade = "1..1", porDemanda = false,
								propReferenciaDesprezar = "fazTratamentoSaudeDesprezar")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.ResponsavelAlunoCasoAcidenteEntity.class,
								nomeColecao = "responsavelAlunoCasoAcidente", numNovos = 2,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "nome")
								
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity.class,
								nomeColecao = "responsavelFinanceiroAluno", numNovos = 1,
								cardinalidade = "1..1", porDemanda = false,
								propReferenciaDesprezar = "nome")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.ServicoAlunoEntity.class,
										nomeColecao = "servicoAluno", numNovos = 1,
										cardinalidade = "0..*", porDemanda = false,
										propReferenciaDesprezar = "servico")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.AnexoEntity.class,
								nomeColecao = "anexoAluno", numNovos = 2,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "descricao")

	
		}
	)
	
//Gerado em 15/01/2013 21:37 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.alunomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;

