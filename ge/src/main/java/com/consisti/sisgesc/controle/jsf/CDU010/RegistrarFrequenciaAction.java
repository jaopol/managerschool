package com.consisti.sisgesc.controle.jsf.CDU010;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.dominio.AbertoFechado;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.FrequenciaAluno;
import com.consisti.sisgesc.entidade.FrequenciaAlunoEntity;
import com.consisti.sisgesc.entidade.RegistrarFrequenciaEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcConstantesComuns;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class RegistrarFrequenciaAction extends AppAction  {
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		trataBotoes();
		super.trataBotoesConformeLogicaApos();
	}
	
	
	/**
	 *Utilizado para tratar os botoes 
	 */
	private void trataBotoes() {
		
		if( ((RegistrarFrequenciaEntity)entidadePlc) != null && ((RegistrarFrequenciaEntity)entidadePlc).getId() != null ){
			contextHelperPlc.setRequestAttribute("exibeAtualizarAluno", "S"); 
			contextHelperPlc.setRequestAttribute("exibeImprimirRegistroFrequencia", "S"); 
		}
		
		if( !PlcConstantesComuns.MODOS.MODO_CONSULTA.equals( controleConversacaoPlc.getModoPlc() ) ){
			contextHelperPlc.setRequestAttribute("exibeImprimirFichaFrequencia", "S"); 
		}
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		RegistrarFrequenciaEntity frequencia = (RegistrarFrequenciaEntity)entidadePlc;
		setaMesFrequencia( frequencia );
		
		return super.novoApos(); 
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaValidarAntes()
	 */
	@Override
	protected boolean gravaValidarAntes() throws PlcException {
		RegistrarFrequenciaEntity frequencia = (RegistrarFrequenciaEntity)entidadePlc;
		validarDataLimiteLancarFrequencia( frequencia );
		return super.gravaValidarAntes();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#editaApos()
	 */
	@Override
	protected String editaApos() throws PlcException {
		RegistrarFrequenciaEntity frequencia = (RegistrarFrequenciaEntity)entidadePlc;
		validarDataLimiteLancarFrequencia( frequencia );
		setaMesFrequencia( frequencia );
		
		return super.editaApos();
	}
	
	/**
	 * O professor tem até o ultimo dia do mes para fazer o lançamento da frequencia.
	 * @param frequencia 
	 * @throws PlcException
	 */
	private void validarDataLimiteLancarFrequencia(RegistrarFrequenciaEntity frequencia) throws PlcException {
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime( new Date() );
		
		Calendar dataCadastro = GregorianCalendar.getInstance();
		dataCadastro.setTime( frequencia.getDataCadastro() );
		int mesCadastro = cal.get( Calendar.MONTH ) + 1;
		
		int ultimoDia = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
		int diaAtual = cal.get( Calendar.DAY_OF_MONTH );
		int mesAtual = cal.get( Calendar.MONTH ) + 1;
		int diferencaDias = ultimoDia - diaAtual;
		
		//Se estiver no mesmo mes
		if( mesAtual == mesCadastro ){
			
			if( diferencaDias <= 5 && diferencaDias >= 0 ){
				helperMsgJsfPlc.msg("msg.info.CDU010.diasRestantes", new String[]{ String.valueOf( ultimoDia - diaAtual)}, Cor.msgAmareloPlc.toString() );
			}
		}
		//Se o mes já tiver virado e o status estiver aberto
		else if( mesAtual != mesCadastro && AbertoFechado.A.equals( frequencia.getStatus() ) ){
			throw new PlcException( "msg.info.CDU010.terminoPrazo" );
		}
	}
	
	/**
	 * Utilizado para atualizar os alunos na lista de frequencia
	 * @return
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public String atualizarAluno() throws PlcException{
		
		RegistrarFrequenciaEntity frequencia = (RegistrarFrequenciaEntity)entidadePlc;
		List<AlunoEntity> listaAluno = getIAppFacade().recuperaAlunoPelaTurma( frequencia.getTurma() );
		
		for (FrequenciaAluno frequenciaAluno : frequencia.getFrequenciaAluno()) {
			
			for (Iterator iterator = listaAluno.iterator(); iterator.hasNext();) {
				AlunoEntity alunoEntity = (AlunoEntity) iterator.next();
				
				if( frequenciaAluno.getAluno().getId().equals( alunoEntity.getId() ) ){
					iterator.remove();
					break;
				}
			}
		}
		
		if(listaAluno != null && !listaAluno.isEmpty() ){
			for (AlunoEntity alunoEntity : listaAluno) {
				FrequenciaAluno frequenciaAluno = new FrequenciaAlunoEntity();
				frequenciaAluno.setAluno(alunoEntity);
				frequenciaAluno.setRegistrarFrequencia(frequencia);
				frequenciaAluno.setTotal(0);
				
				frequencia.getFrequenciaAluno().add( frequenciaAluno );
			}
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	
	/**
	 * Utilizado para setar o Mes/Ano da frequencia
	 * @param frequencia
	 */
	private void setaMesFrequencia(RegistrarFrequenciaEntity frequencia) {
		
		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		Date data = new Date();
		frequencia.setDataCadastro( data );
		String mesAno = format.format(data);
		
		frequencia.setMesAnoFrequencia( mesAno );
	}

	/**
	 * Utilizado quando selecionado a Turma para recuperar a lista de alunos da turma selecionada
	 * @return
	 * @throws PlcException
	 */
	public String recuperarAlunos() throws PlcException{
		
		RegistrarFrequenciaEntity frequencia = (RegistrarFrequenciaEntity)entidadePlc;
		
		if( frequencia != null && frequencia.getTurma() != null ){
			List<AlunoEntity> listaAluno = getIAppFacade().recuperaAlunoPelaTurma( frequencia.getTurma() );
			
			if( listaAluno == null || listaAluno.isEmpty() ){
				throw new PlcException("msg.info.aluno.nao.cadastrado.turma");
			}
			else{
				
				if( frequencia.getFrequenciaAluno() == null )
					frequencia.setFrequenciaAluno( new ArrayList<FrequenciaAluno>() );
				frequencia.getFrequenciaAluno().clear();
				
				for (AlunoEntity alunoEntity : listaAluno) {
					FrequenciaAluno frequenciaAluno = new FrequenciaAlunoEntity();
					frequenciaAluno.setAluno(alunoEntity);
					frequenciaAluno.setRegistrarFrequencia(frequencia);
					frequenciaAluno.setTotal(0);
					
					frequencia.getFrequenciaAluno().add( frequenciaAluno );
				}
			}
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	public String imprimirFichaFrequencia(){
		//TODO implementar relatorio
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	private IAppFacade getIAppFacade() throws PlcException {
		IAppFacade facade = (IAppFacade)getServiceFacade();
		return facade;
	}
}
