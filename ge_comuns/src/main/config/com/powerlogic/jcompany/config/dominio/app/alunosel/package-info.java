/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.AlunoEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.FuncionarioEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SELECAO,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 15/01/2013 21:37 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.alunosel;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;

