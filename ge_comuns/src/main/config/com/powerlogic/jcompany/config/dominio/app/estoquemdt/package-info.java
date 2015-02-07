/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.estoque.EstoqueEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.estoque.MovimentoEntity.class,
								nomeColecao = "movimento", numNovos = 1,
								cardinalidade = "1..*", porDemanda = false, testaDuplicata = false,
								propReferenciaDesprezar = "tipoMovimentacao",
								navegador=@PlcConfigNavegadorDetalhe(numPorPagina=4,topoEstilo=com.powerlogic.jcompany.config.comuns.colaboracao.PlcConfigNavegadorDetalhe.TopoEstilo.SETAS_DE_PAGINACAO))

		}
	)
	
//Gerado em 26/11/2013 19:14 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.estoquemdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
import com.powerlogic.jcompany.config.comuns.colaboracao.PlcConfigNavegadorDetalhe;
