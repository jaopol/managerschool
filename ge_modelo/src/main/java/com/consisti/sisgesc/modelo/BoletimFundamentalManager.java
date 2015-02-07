package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.BoletimFundamental;
import com.consisti.sisgesc.entidade.BoletimFundamentalDet;
import com.consisti.sisgesc.entidade.BoletimFundamentalDetEntity;
import com.consisti.sisgesc.entidade.BoletimFundamentalEntity;
import com.consisti.sisgesc.entidade.Disciplinas;
import com.consisti.sisgesc.entidade.DisciplinasEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.BoletimFundamentalDAO;
import com.consisti.sisgesc.persistencia.hibernate.CronogramaTurmaDisciplinaDAO;
import com.consisti.sisgesc.persistencia.hibernate.RegistroNotasFundamentalDAO;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class BoletimFundamentalManager extends AppManager {

	private BoletimFundamentalDAO boletimFundamentalDAO;
	private AlunoDAO alunoDAO;
	private CronogramaTurmaDisciplinaDAO cronogramaTurmaDisciplinaDAO;
	private RegistroNotasFundamentalDAO registroNotasFundamentalDAO;
	
	public BoletimFundamentalManager( BoletimFundamentalDAO boletimFundamentalDAO, AlunoDAO alunoDAO, 
			CronogramaTurmaDisciplinaDAO cronogramaTurmaDisciplinaDAO, RegistroNotasFundamentalDAO registroNotasFundamentalDAO ){
		this.boletimFundamentalDAO = boletimFundamentalDAO;
		this.alunoDAO = alunoDAO;
		this.cronogramaTurmaDisciplinaDAO = cronogramaTurmaDisciplinaDAO;
		this.registroNotasFundamentalDAO = registroNotasFundamentalDAO;
	}
	
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.modelo.PlcBaseBO#recuperaListaApos(java.lang.String, java.util.List, int, int, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void recuperaListaApos(String orderyByDinamico, List argsQBE,
			int primeiraLinha, int maximoLinhas, List lista)
			throws PlcException {
		
		lista = recuperaNotasRegistroNotas(argsQBE, lista);
		
		super.recuperaListaApos(orderyByDinamico, argsQBE, primeiraLinha, maximoLinhas,
				lista);
	}

	/**
	 * Utilizado para recuperar as notas do registro de notas do aluno para setar na tela de seleção
	 * @param argsQBE
	 * @param lista
	 * @return
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	private List recuperaNotasRegistroNotas(List argsQBE, List lista) throws PlcException {
		
		if( argsQBE != null && !argsQBE.isEmpty() ){
			Long idAluno = null;
			Integer anoLetivo = null;
			String situacaoBoletim = null;
			for (int i = 0; i < argsQBE.size(); i++) {
				
				if( "aluno".equals( ((PlcArgVO) argsQBE.get(i)).getNome() ) ){
					idAluno = Long.parseLong( ((PlcArgVO) argsQBE.get(i)).getValor() );
				}
				else if( "anoLetivo".equals( ((PlcArgVO) argsQBE.get(i)).getNome() ) ) {
					anoLetivo = Integer.parseInt( ((PlcArgVO) argsQBE.get(i)).getValor() );
				}
				else if( "situacaoBoletim".equals( ((PlcArgVO) argsQBE.get(i)).getNome() ) ){
					situacaoBoletim = ((PlcArgVO) argsQBE.get(i)).getValor();
				}
				
			}
			//Se passar situaçao do boletim nos argumentos não recupera no registro de notas
			if( StringUtils.isBlank( situacaoBoletim ) ){
				
				if( lista == null ){
					lista = new ArrayList();
				}
				//Recupera as notas no registro de notas
				List<BoletimFundamental> listaBoletim = boletimFundamentalDAO.recuperaNotasAlunoByBoletim( idAluno, anoLetivo );
				if( listaBoletim != null && !listaBoletim.isEmpty() ){
					for ( BoletimFundamental boletimFundamental : listaBoletim) {
						BoletimFundamentalEntity boletim = new BoletimFundamentalEntity();
						boletim.setAluno( new AlunoEntity() );
						boletim.getAluno().setId( boletimFundamental.getAluno().getId() );
						boletim.getAluno().setNomeAluno(  boletimFundamental.getAluno().getNomeAluno() );
						boletim.setAnoLetivo( boletimFundamental.getAnoLetivo() );
						//Quando recuperado pelo registro de notas ainda não tem boletim
						boletim.setSituacaoBoletimStr("-");
						boletim.setDocumento( AppConstantesComuns.TIPO_DOCUMENTO.REGISTRO_NOTAS );
			
						lista.add( boletim );
					}
				}
			}
		}
		return lista;
	}
	
	/**
	 * Utilizado para montar o grafo do boletim fundamental quando for um novo registro.
	 * Recuperando todas informaçoes pelo id do aluno
	 * @param idAluno
	 * @param appBaseContextVO 
	 * @throws PlcException 
	 */
	public PlcBaseVO montaBoletimAluno( Long idAluno, BoletimFundamentalEntity boletim, AppBaseContextVO appBaseContextVO ) throws PlcException{
		
		//recupera a turma do aluno
		TurmaEntity turma = alunoDAO.recuperaTurmaByIdAluno( idAluno );
		//recupera as disciplinas da turma
		List<Disciplinas> listDisciplinas = cronogramaTurmaDisciplinaDAO.recuperaDisciplinasByIdTurma( turma.getId() );
		
		List<RegistroNotasFundamentalEntity> listRegistroNotas = registroNotasFundamentalDAO.recuperaListaRegistroNotasByIdAluno( idAluno );
		
		if( listRegistroNotas != null && listDisciplinas != null && listRegistroNotas.size() < listDisciplinas.size() ){
			appBaseContextVO.setApresentaMensagem( true );
		}
		
		AlunoEntity aluno = alunoDAO.recuperaAlunoByIdAluno( idAluno );
		boletim.setAluno( aluno );
		boletim.setTurma( turma );
		
		if( boletim.getBoletimFundamentalDet() == null ){
			boletim.setBoletimFundamentalDet( new ArrayList<BoletimFundamentalDet>() );
		}
		boletim.getBoletimFundamentalDet().clear();
		BigDecimal notaTotal = new BigDecimal(0);
		Integer faltaTotal = new Integer(0);
		Integer aulaDadaTotal = new Integer(0);
		
		for (RegistroNotasFundamentalEntity registroNotas : listRegistroNotas) {
			
			BoletimFundamentalDetEntity boletimDet = new BoletimFundamentalDetEntity();
			//Soma totas as notas do registro de notas
			notaTotal = notaTotal.add( registroNotas.getNotaProva().add( registroNotas.getNotaTrabalho().add( registroNotas.getAvaliacao().add( registroNotas.getConceito() ) ) ) );
			//Soma total de faltas
			faltaTotal = faltaTotal + registroNotas.getFaltas();
			//Soma total de aulas dadas
			aulaDadaTotal = aulaDadaTotal + registroNotas.getAulasDadas();
			
			boletimDet.setBoletimFundamental( boletim );
			boletimDet.setIdRegistroNotas( registroNotas.getId() );
			boletimDet.setDisciplina( new DisciplinasEntity( registroNotas.getIdDisciplina(), null) );
			boletimDet.setBimestre( registroNotas.getBimestre() );
			boletimDet.setFaltas( registroNotas.getFaltas() );
			boletimDet.setNota( registroNotas.getNotaProva().add( registroNotas.getNotaTrabalho().add( registroNotas.getAvaliacao().add( registroNotas.getConceito() ) ) ) );
			boletimDet.setAulasDadas( registroNotas.getAulasDadas() );
			boletimDet.setNotaReprovada( registroNotas.getNotaReprovada() );
			boletimDet.setIdRegistroSubDet( registroNotas.getIdRegistroSubDet() );
			
			boletim.setAnoLetivo( registroNotas.getAnoLetivo() );
		
			boletim.getBoletimFundamentalDet().add( boletimDet );
		}
		
		boletim.setTotalNota( notaTotal );
		boletim.setTotalFaltas( faltaTotal );
		boletim.setTotalAulaDada( aulaDadaTotal );

		return boletim;
	}
}
