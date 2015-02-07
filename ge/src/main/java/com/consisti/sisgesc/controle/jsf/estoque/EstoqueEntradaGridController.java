package com.consisti.sisgesc.controle.jsf.estoque;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.comuns.PlcRuntimeException;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigGrupoControle;
import com.powerlogic.jcompany.config.controle.colaboracao.PlcConfigSelecao;
import com.powerlogic.jcompany.controle.rest.api.qualifiers.QPlcAtual;
import com.powerlogic.jcompany.controle.rest.api.qualifiers.QPlcControleNome;
import com.powerlogic.jcompany.controle.rest.api.qualifiers.QPlcControleQualificador;
import com.powerlogic.jcompany.controle.rest.api.stereotypes.SPlcControle;
import com.powerlogic.jcompany.controle.rest.controles.PlcBaseGridControle;

@SPlcControle
@QPlcControleNome("grid")
@QPlcControleQualificador("estoqueentradasel")
public class EstoqueEntradaGridController extends PlcBaseGridControle {

	@Inject @QPlcAtual
	protected PlcConfigGrupoControle configGrupoControle;
	
	private Integer total = null;
	
	@Override
	public void recuperaColecao() {
		recuperaColecaoAntes();
			
		try {
						
			PlcConfigSelecao configSelecao 	= configGrupoControle.selecao();
				
			String querySel = configSelecao.apiQuerySel();
			context.setApiQuerySel(StringUtils.isNotBlank(querySel)?querySel:null);
			
			Class<?> classeEntidade  = getTipoEntidade();
			
			//montagem do parâmetros da pesquisa...
			List<PlcArgVO> plcArgsVO = montaListaParametrosDoRequest(request, classeEntidade, configSelecao);
			
			total = recuperaTotal(facade, context, classeEntidade, plcArgsVO);
			
			String orderByDinamico = null;
			if (StringUtils.isNotEmpty(orderBy)) {
				if ("prod.descricao".equals(orderBy)){
					orderByDinamico = orderBy + " " + StringUtils.defaultIfEmpty(order, "asc");
				} else {
					orderByDinamico = "obj." + orderBy + " " + StringUtils.defaultIfEmpty(order, "asc");
				}
			}
			
			List lista = recuperaLista(facade, context, classeEntidade, orderByDinamico, plcArgsVO, ((page -1) * rows) , (rows));
			
			setColecaoEntidade(lista);
			
			recuperaColecaoApos();
		} catch (PlcException e) {
			throw new PlcRuntimeException(e);
		}
		
	}
	
	public Integer getTotal() {
		return total;
	}
	
}
