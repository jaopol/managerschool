/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity.class,
						com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.financeiro.ContaReceberProdutoVendaEntity.class,
								nomeColecao = "contaReceberProdutoVenda", numNovos = 4,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "produtoVenda")


		}
	)
	
//Gerado em 04/06/2015 18:54 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.contareceberdiariomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
