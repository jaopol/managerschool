/** ******************************* LÓGICA-PADRÃO: SELECAO ***********************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity.class,
		classesLookup = {com.consisti.sisgesc.entidade.DisciplinasEntity.class,com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.AlunoEntity.class},
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.SELECAO,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 20/03/2013 22:10 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.registronotasfundamentalsel;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;

