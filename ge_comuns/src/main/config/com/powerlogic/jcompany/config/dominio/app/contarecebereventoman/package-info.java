/** ******************************** LÓGICA-PADRÃO: CRUD *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.financeiro.BancoEntity.class,
			com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class,
			com.consisti.sisgesc.entidade.TurmaEntity.class,
			com.consisti.sisgesc.entidade.EventoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CRUD,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 16/02/2016 22:02 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.contarecebereventoman;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;
