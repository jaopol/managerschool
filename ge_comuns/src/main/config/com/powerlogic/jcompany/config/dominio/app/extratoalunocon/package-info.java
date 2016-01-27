/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.financeiro.ExtratoAluno.class,
		classesLookup = {com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.CONSULTA,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 18/01/2016 21:19 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.extratoalunocon;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
import com.powerlogic.jcompany.config.dominio.PlcConfigComponente;

