package com.consisti.sisgesc.controle.jsf.historico;

import java.util.Date;
import java.util.HashMap;

import org.apache.tools.ant.taskdefs.condition.HasMethod;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.comuns.helper.PlcDateHelper;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
public class HistoricoManAction extends RelatorioActionPlc  {
	
	private Boolean foraPrazo = true;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		super.trataBotoesConformeLogicaApos();
		
		contextHelperPlc.getRequest().setAttribute("exibe_jcompany_evt_incluir", PlcSimNao.N);
		contextHelperPlc.getRequest().setAttribute("exibe_jcompany_evt_excluir", PlcSimNao.N);
		contextHelperPlc.getRequest().setAttribute("exibe_jcompany_evt_gravar", PlcSimNao.N);
		
		if (getForaPrazo()){
			contextHelperPlc.getRequest().setAttribute("exibeBtnHistorico", PlcSimNao.S);
		} else {
			contextHelperPlc.getRequest().setAttribute("exibeBtnHistorico", PlcSimNao.N);
		}
		
	}
	
	public void geraHistorico() throws PlcException{
		
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		
		String meses[] = {"Janeiro", "Fevereiro", 
	             "Março", "Abril", "Maio", "Junho", 
	             "Julho", "Agosto", "Setembro", "Outubro",
		      "Novembro", "Dezembro"};
		   
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mes", meses[aluno.getDataNascimento().getMonth()]);  
		map.put("mesAtual", meses[new Date().getMonth()]);
		
		buscaDadosTuma();
		
		//super.geraRelatorioPlc("historico.jasper", aluno, map);
		
	}
	
	/**
	 * Vai buscar as disciplinas de acordo
	 * com a turma do aluno passada
	 * @throws PlcException 
	 */
	private void buscaDadosTuma() throws PlcException {
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		AlunoEntity aluno = (AlunoEntity) entidadePlc;

		aluno.setCronogramaTurma(facade.recuperaCronogramaTurma(aluno.getTurma().getId()));
		
	}

	@Override
	protected String editaApos() throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		
		if (aluno.getDataCadastro() != null){
			if (PlcDateHelper.getInstance().diasEntreDatas(aluno.getDataCadastro(), new Date()) > AppConstantesComuns.DIAS_PARA_GERAR_HISTORICO){
				setForaPrazo(Boolean.FALSE); 
				throw new PlcException("erro.gerar.historico.fora.prazo", new Object [] {aluno.getNomeAluno()});
			}
		}
		
		return super.editaApos();
	}

	public Boolean getForaPrazo() {
		return foraPrazo;
	}

	public void setForaPrazo(Boolean foraPrazo) {
		this.foraPrazo = foraPrazo;
	}
	
}
