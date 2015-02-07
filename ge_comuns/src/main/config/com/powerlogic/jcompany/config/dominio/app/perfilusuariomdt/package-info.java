/** *************************** LÓGICA-PADRÃO: MESTRE-DETALHE ********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.autenticacao.PerfilEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.MESTRE_DETALHE,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA),
		detalhes = {
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.autenticacao.PerfilUsuarioEntity.class,
								nomeColecao = "perfilUsuario", numNovos = 4,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "usuario")
,
			@PlcConfigDetalhe(classe = com.consisti.sisgesc.entidade.autenticacao.UrlAcessoPerfilEntity.class,
								nomeColecao = "urlAcessoPerfil", numNovos = 4,
								cardinalidade = "0..*", porDemanda = false,
								propReferenciaDesprezar = "urlAcesso")


		}
	)
	
//Gerado em 29/01/2013 22:18 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.perfilusuariomdt;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigDetalhe;
