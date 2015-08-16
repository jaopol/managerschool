package com.consisti.sisgesc.controle.jsf.financeiro.CDUF004;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class BancoAction extends AppAction  {
	
	@Override
	protected String novoApos() throws PlcException {
		
		BancoEntity banco = (BancoEntity)entidadePlc;
		banco.setDataCadastro( new Date() );
		
		return super.novoApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesAntes()
	 */
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		BancoEntity banco = (BancoEntity)entidadePlc;
		validaContaBanco(banco);
		validaAgenciaBanco(banco);
		
		return super.gravaSimplesAntes();
	}
	
	@Override
	protected void gravaSimplesApos() throws PlcException {
		super.gravaSimplesApos();
		getServiceClasseLookup().recuperaUmaClasseLookupDaPersistenciaParaCache(BancoEntity.class, "obj.bancoSuportado asc", null);
		//atualizaClassesLookupDoBanco();
	}

	/**
	 * Utilizado para garantir que a agencia da conta tera somente numero com . e - caso necessario
	 * @param banco
	 * @throws PlcException 
	 * @throws PlcException
	 */
	private void validaAgenciaBanco(BancoEntity banco) throws PlcException {
		
		try {
			if( banco.getAgencia().contains("-") ){
				Integer.valueOf( banco.getAgencia().split("-")[0].replace(".", "") );
				Integer.valueOf( banco.getAgencia().split("-")[1].replace(".", "") );
			}
			else if(banco.getAgencia().contains(".")){
				Integer.valueOf( banco.getAgencia().replace(".", "") );
			}
			else{
				Integer.valueOf( banco.getAgencia() );
			}
		} catch (Exception e) {
			throw new PlcException("msg.error.numero.agenciaBanco");
		}
		
	}

	/**
	 * Utilizado para garantir que o numero da conta tera somente numero com . e - caso necessario
	 * @param banco
	 * @throws PlcException
	 */
	private void validaContaBanco(BancoEntity banco) throws PlcException {
		try {
			 
			if( banco.getNumeroConta().contains("-") ){
				Integer.valueOf( banco.getNumeroConta().split("-")[0].replace(".", "") );
				Integer.valueOf( banco.getNumeroConta().split("-")[1].replace(".", "") );
			}
			else{
				Integer.valueOf( banco.getNumeroConta().replaceAll(".", "") );
			}
		} catch (Exception e) {
			throw new PlcException("msg.error.numero.contaBanco");
		}
	}
	
	@Override
	protected String pesquisaApos() throws PlcException {
		//Utilizado para identificar aposSelecao contareceberMan
		contextHelperPlc.setSessionAttribute("vinculadoAluno", "N");
		
		return super.pesquisaApos();
	}
	
}
