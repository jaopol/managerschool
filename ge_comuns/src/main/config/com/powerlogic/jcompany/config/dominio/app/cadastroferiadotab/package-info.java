/** ******************************** LÓGICA-PADRÃO: TABULAR *************************************
 *  ******************************************************************************************/
@PlcConfigGrupoAgregacao(
		entidade = com.consisti.sisgesc.entidade.CadastroFeriadoEntity.class,
		padrao = @PlcConfigPadrao(logica = PlcConfigPadrao.Logica.TABULAR,
									complexidade = PlcConfigPadrao.Complexidade.SIMPLES,
									exclusaoModo = PlcConfigPadrao.ExclusaoModo.FISICA)
	)
	
//Gerado em 02/04/2013 21:26 por jCompany Code Generator
package com.powerlogic.jcompany.config.dominio.app.cadastroferiadotab;

import com.powerlogic.jcompany.config.dominio.PlcConfigGrupoAgregacao;
import com.powerlogic.jcompany.config.dominio.PlcConfigPadrao;
