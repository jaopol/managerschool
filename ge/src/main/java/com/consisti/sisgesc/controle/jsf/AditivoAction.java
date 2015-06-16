package com.consisti.sisgesc.controle.jsf;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.omg.CORBA.Request;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.entidade.AditivoEntity;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcBaseContextVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.controle.struts.helper.PlcMsgHelper;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem;


/**
 * Classe de Controle gerada pelo assistente
 */
public class AditivoAction extends RelatorioActionPlc  {

	private String texto;
	private Date dataAtual;
	private Long idAluno;
	private AlunoEntity aluno;
	private Long idContrato;
	
	public void gravarAditivo() throws PlcException{

		AditivoEntity aditivo = new AditivoEntity();
		ContratoEntity contrato = (ContratoEntity) entidadePlc;
		AlunoEntity aluno2 = (AlunoEntity) contrato.getAluno();
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		AlunoEntity aluno = facade.recuperarAlunoVO( aluno2.getId() ) ;
		
		HashMap<String, String> parametros = mesAtual();
		parametros.put("texto", contrato.getTexto().trim());
		parametros.put("nomeRespFinanceiroStr", aluno.getResponsavelFinanceiroAluno().get(0).getNome());
		parametros.put("respAluno", "CPF: "+ aluno.getResponsavelFinanceiroAluno().get(0).getCpf());
		parametros.put("nomeAluno", aluno.getNomeAluno());
		
		contextHelperPlc.getRequest().getSession().setAttribute("gravarAditivo", PlcSimNao.S);
		contextHelperPlc.getRequest().getSession().setAttribute("idContrato", contrato.getId());
		geraRelatorio(AppConstantesComuns.RELATORIO.ADITIVO, aditivo, parametros);
		
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataAtual() {
		return new Date();
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	
}
