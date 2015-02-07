package com.consisti.sisgesc.modelo;

import java.util.ArrayList;
import java.util.List;

import com.consisti.sisgesc.dominio.AbertoFechado;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.BoletimFundamentalDetEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalDet;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalDetEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalSubDet;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalSubDetEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ProfessorDisciplinaDAO;
import com.consisti.sisgesc.persistencia.hibernate.RegistroNotasFundamentalDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Modelo gerada pelo assistente
 */
public class RegistroNotasFundamentalManager extends AppManager {
	
	private ProfessorDisciplinaDAO professorDisciplinaDAO;
	private AlunoDAO alunoDAO;
	private RegistroNotasFundamentalDAO registroNotasFundamentalDAO;
	
	public RegistroNotasFundamentalManager( ProfessorDisciplinaDAO professorDisciplinaDAO, AlunoDAO alunoDAO, 
			RegistroNotasFundamentalDAO registroNotasFundamentalDAO ) {
		this.professorDisciplinaDAO = professorDisciplinaDAO;
		this.alunoDAO = alunoDAO;
		this.registroNotasFundamentalDAO = registroNotasFundamentalDAO;
	}
	
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		RegistroNotasFundamentalEntity registro = (RegistroNotasFundamentalEntity)entidadeAtual;

		if( registro.getId() == null ){
			validaExistiBimestre( registro );
			validaBimestreFechado( registro );
		}
		
		setNotaReprovada(registro);
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

	/**
	 * Utilizado para setar a nota reprovada como "N", quando estiver null ou quando o professor editar o registro e a nota reprovada estiver como "S"
	 * @param registro
	 */
	private void setNotaReprovada(RegistroNotasFundamentalEntity registro) {
		
		if( registro.getRegistroNotasFundamentalDet() != null ){
			for (RegistroNotasFundamentalDet registroDet : registro.getRegistroNotasFundamentalDet() ) {
				
				if(registroDet.getRegistroNotasFundamentalSubDet() != null ){
					for (RegistroNotasFundamentalSubDet registroSubDet : registroDet.getRegistroNotasFundamentalSubDet()) {
						
						if( PlcSimNao.S.equals( registroSubDet.getNotaReprovada() ) || registroSubDet.getNotaReprovada() == null ){
							registroSubDet.setNotaReprovada( PlcSimNao.N );
						}
					}
				}
			}
		}
	}
	
	/**
	 * Utilizado para recuperar quantos bimestre em aberto já existi
	 * @param registro
	 * @throws PlcException
	 */
	private void validaExistiBimestre(RegistroNotasFundamentalEntity registro) throws PlcException {
		
		Long consultaBimestre = registroNotasFundamentalDAO.verificaBimestreAberto( registro.getProfessor().getId(), registro.getDisciplina().getId(), registro.getBimestre() );
		
		if( consultaBimestre > 1 ){
			throw new PlcException("msg.info.bimestreExisti", new String[]{ registro.getBimestre().toString() });
		}
	}

	/**
	 * Utilizado para verificar quando for um registro novo se o bimestre anterior já está fechado.
	 * Caso o ultimo bimestre encontrado não estejá fechado lança exeção
	 * @param registro
	 * @throws PlcException 
	 */
	private void validaBimestreFechado(RegistroNotasFundamentalEntity registro) throws PlcException {
			
		AbertoFechado statusUltimoBim = registroNotasFundamentalDAO.recuperaUltimoRegistroProfessorDisciplina( registro.getProfessor().getId(), registro.getDisciplina().getId() );
		
		if( AbertoFechado.A.equals( statusUltimoBim ) ){
			throw new PlcException( "msg.info.bimestreAberto" );
		}
	}

	/**
	 * UTilizado para montar o detalhe e sub-detalhe do registro de notas, recuperando as turmas e alunos do professor para a disciplina
	 * @param registro
	 * @throws PlcException
	 */
	public void montaDetalheSubDetalhe( RegistroNotasFundamentalEntity registro ) throws PlcException{
		
		if( registro.getProfessor() != null && registro.getDisciplina() != null ){
		
			List<Turma> turma = professorDisciplinaDAO.recuperaListaTurmaProfessor( registro.getProfessor().getId(), registro.getDisciplina().getId() );		
			
			if( turma != null && !turma.isEmpty() ){
				registro.getRegistroNotasFundamentalDet().clear();

				//Lista Detalhe
				for (Turma turma2 : turma) {
					RegistroNotasFundamentalDetEntity registroTurma = new RegistroNotasFundamentalDetEntity();
					registroTurma.setTurma( turma2 );
					registroTurma.setNomeTurma( turma2.getDescricao() );
					registroTurma.setRegistroNotasFundamental( registro );
					
					List<AlunoEntity> listaAluno = alunoDAO.recuperaAlunoPelaTurma(turma2);
					
					//Lista Sub-Detalhe
					if( listaAluno != null && !listaAluno.isEmpty() ){
						
						for (AlunoEntity alunoEntity : listaAluno) {
							RegistroNotasFundamentalSubDetEntity registroAluno = new RegistroNotasFundamentalSubDetEntity();
							registroAluno.setAluno( alunoEntity );
							registroAluno.setNomeAluno( alunoEntity.getNomeAluno() );
							registroAluno.setRegistroNotasFundamentalDet( registroTurma );
							
							if( registroTurma.getRegistroNotasFundamentalSubDet() == null ){
								registroTurma.setRegistroNotasFundamentalSubDet( new ArrayList<RegistroNotasFundamentalSubDet>() );
							}
							registroTurma.getRegistroNotasFundamentalSubDet().add( registroAluno );
						}
					}
					
					if( registro.getRegistroNotasFundamentalDet() == null ){
						registro.setRegistroNotasFundamentalDet( new ArrayList<RegistroNotasFundamentalDet>() );
					}
					
					registro.getRegistroNotasFundamentalDet().add( registroTurma );
				}
			}
			else{
				throw new PlcException("msg.info.naoExistiTurma.professor");
			}
		
		}
	}

	/**
	 * Utilizado para setar o registro de notas como Aberto quando for reprovado pela diretoria.
	 * @param listBoletimDetReprovar
	 * @throws PlcException 
	 */
	public void reprovaRegistroNotas( List<BoletimFundamentalDetEntity> listBoletimDetReprovar ) throws PlcException {
		
		for (BoletimFundamentalDetEntity notas : listBoletimDetReprovar) {
			registroNotasFundamentalDAO.reprovaRegistroNotas( notas.getIdRegistroNotas(), notas.getIdRegistroSubDet() );
		}
	}
	
	
}
