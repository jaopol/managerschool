/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.AlunoEntity.class,com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SELECAO,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 04/06/2015 18:54 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.contareceberdiariosel;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;

