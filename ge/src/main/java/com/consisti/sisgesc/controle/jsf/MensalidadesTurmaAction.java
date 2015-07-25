package com.consisti.sisgesc.controle.jsf;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;


/**
 * Classe de Controle gerada pelo assistente
 */
public class MensalidadesTurmaAction extends RelatorioActionPlc  {
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_ABRIR, PlcConstantes.NAO_EXIBIR );
		
	}
	
	public void geraRelatorioMensalidadeAluno() throws PlcException{
		
		IAppFacade fc = (IAppFacade)getServiceFacade();
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		List<AlunoEntity> listaDadosPorTurma = fc.recuperaDadosPorTurma(aluno.getTurma() != null ? aluno.getTurma().getId() : null);
		
		if (!listaDadosPorTurma.isEmpty()){
			aluno.setDadosPorTurma(listaDadosPorTurma);
			
			HashMap mesAtual = mesAtual();
			Calendar cal = Calendar.getInstance();
			mesAtual.put("ano", cal.get(Calendar.YEAR));
			mesAtual.put("dia", cal.get(Calendar.DAY_OF_MONTH));
			mesAtual.put("quantidadeAlunos", listaDadosPorTurma.size());
			mesAtual.put("valorTotalMensalidades", somaValorTotalMensalidadesFormatado(listaDadosPorTurma));
			
			super.geraRelatorio(AppConstantesComuns.RELATORIO.REL_MENSALIDADES_TURMA, aluno, mesAtual);
		} else {
			throw new PlcException("erro.nenhuem.registro.encontrado");
		}
		
	}
	
}
