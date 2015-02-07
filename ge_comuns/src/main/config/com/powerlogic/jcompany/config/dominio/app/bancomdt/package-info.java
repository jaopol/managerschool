/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.BancoEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.EnderecoEntity.class,
								nomeColecao = "endereco", numNovos = 1,
								cardinalidade = "0..1", porDemanda = false,
								propReferenciaDesprezar = "logradouro")


		}
	)
	
//Gerado em 10/06/2013 22:21 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.bancomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
