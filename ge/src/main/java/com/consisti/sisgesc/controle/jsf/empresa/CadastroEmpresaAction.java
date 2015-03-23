package com.consisti.sisgesc.controle.jsf.empresa;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.EmpresaEntity;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.controle.PlcControleLocator;
import com.powerlogic.jcompany.controle.service.PlcClasseLookupService;

/**
 * Classe de Controle gerada pelo assistente
 */
public class CadastroEmpresaAction extends AppAction  {

	@Override
	protected String gravaApos() {
		
		try {
			montaListaEmpresaDesconto();
		} catch (PlcException e) {
			try {
				throw new PlcException("erro.atualizar.lista.empresas");
			} catch (PlcException e1) {
				e1.printStackTrace();
			}
		}
		
		return super.gravaApos();
	}
	
	/**
	 * Atualiza lista de empresas cadastradas
	 * @throws PlcException 
	 * @throws PlcException
	 */
	private void montaListaEmpresaDesconto() throws PlcException {

		PlcClasseLookupService classeLookupService = classeLookupService = (PlcClasseLookupService) PlcControleLocator.getInstance().get(PlcClasseLookupService.class);
		
		if (classeLookupService!=null){
			String orderBy = "nomeEmpresa";
			String fabricas = "ge";
			classeLookupService.recuperaUmaClasseLookupDaPersistenciaParaCache(EmpresaEntity.class, orderBy, fabricas);
		}
		
		
	}
	
}
