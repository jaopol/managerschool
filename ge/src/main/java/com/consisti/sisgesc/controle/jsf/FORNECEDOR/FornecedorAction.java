package com.consisti.sisgesc.controle.jsf.FORNECEDOR;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.dominio.TipoPessoa;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
public class FornecedorAction extends AppAction  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String identificador;
	
	@Override
	protected boolean gravaValidarAntes() throws PlcException {
		
		limpaCamposPorTipoPessoa();
		
		return super.gravaValidarAntes();
	}
	
	@Override
	protected String novoApos() throws PlcException {
		
		FornecedorEntity fornecedor = (FornecedorEntity) entidadePlc;
		
		fornecedor.setDataCadastro(new Date());
		
		return super.novoApos();
	}

	/**
	 * Vai limpar alguns campos de acordo com o tipo de pessoa
	 */
	private void limpaCamposPorTipoPessoa() {

		FornecedorEntity fornecedor = (FornecedorEntity) entidadePlc;
		
		if (TipoPessoa.F.name().equals(fornecedor.getTipoPessoa().name())){
			fornecedor.setNomeFantasia("");
			fornecedor.setRazaoSocial("");
			fornecedor.setCnpj("");
		} else if (TipoPessoa.J.name().equals(fornecedor.getTipoPessoa().name())) {
			fornecedor.setNome("");
			fornecedor.setCpf("");
		}
		else{
			fornecedor.setCpf("");
			fornecedor.setNomeFantasia("");
			fornecedor.setRazaoSocial("");
			fornecedor.setCnpj("");
		}
	}
	
	@Override
	protected String editaApos() throws PlcException {
		
		FornecedorEntity fornecedor = (FornecedorEntity) entidadePlc;
		
		if (TipoPessoa.F.equals(fornecedor.getTipoPessoa())){
			setIdentificador(fornecedor.getCpf());
		} else {
			setIdentificador(fornecedor.getCnpj());
		}
		
		return super.editaApos();
	}
	
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		validaDuplicado();
		
		return super.gravaSimplesAntes();
	}

	/**
	 * @author Douglas Mendes
	 * valida se o cpf ou cnpj esta duplicado 
	 * @throws PlcException 
	 */
	private void validaDuplicado() throws PlcException {
		
		FornecedorEntity fornecedor = (FornecedorEntity) entidadePlc;
		IAppFacade facade = (IAppFacade) getServiceFacade();
		String msg = "";
		String identificadorFornecedor = "";
		
		if (StringUtils.isNotBlank(fornecedor.getCpf())){
			FornecedorEntity cpf = facade.recuperaCpf(fornecedor.getCpf());
			if (cpf!=null){
				identificadorFornecedor = fornecedor.getCpf();
				if (cpf.getCpfCnpj().equals(fornecedor.getCpf()) && fornecedor.getId()==null){
					msg = "CPF "+fornecedor.getCpf()+".";
				} else {
					if (cpf.getCpfCnpj().equals(fornecedor.getCpf()) && !fornecedor.getId().toString().equals(cpf.getId().toString())){
						msg = "CPF "+fornecedor.getCpf()+".";
					}
				}
			}
		} else if (StringUtils.isNotBlank(fornecedor.getCnpj())) {
			FornecedorEntity cnpj = facade.recuperaCnpj(fornecedor.getCnpj());
			if (cnpj!=null){
				identificadorFornecedor = fornecedor.getCnpj();
				if (cnpj.getCpfCnpj().equals(fornecedor.getCnpj()) && fornecedor.getId()==null){
					msg = "CNPJ "+fornecedor.getCnpj()+".";
				} else {
					if (cnpj.getCpfCnpj().equals(fornecedor.getCnpj()) && fornecedor.getId().toString().equals(cnpj.getId().toString())){
						msg = "CNPJ "+fornecedor.getCnpj()+".";
					}
				}
			}
		}
		
		if (fornecedor.getId()==null){
			if (StringUtils.isNotEmpty(msg)){
				throw new PlcException("erro.cpf.cnpj.duplicado", new Object[]{msg});
			}
		} else {
			if (StringUtils.isNotEmpty(msg) && !identificadorFornecedor.equals(this.identificador)){
				throw new PlcException("erro.cpf.cnpj.duplicado", new Object[]{msg});
			}
		}
		
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
}
