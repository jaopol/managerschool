package com.consisti.sisgesc.controle.jsf.CDU006;

import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.ProfessorDisciplinaEntity;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.entidade.TurmaProfessor;
import com.powerlogic.jcompany.comuns.PlcException;

/**
 * Classe de Controle gerada pelo assistente
 */
public class GerirProfessorAction extends AppAction  {
	
	@Override
	protected String novoApos() throws PlcException {
		ProfessorDisciplinaEntity professor = (ProfessorDisciplinaEntity)entidadePlc;
		professor.setDataCadastro( new Date() );
		return super.novoApos();
	}
	
	@Override
	protected String novoDetalheApos() throws PlcException {
		ProfessorDisciplinaEntity professor = (ProfessorDisciplinaEntity)entidadePlc;
		inicializaDetalhe(professor);
		return super.novoDetalheApos();
	}

	private void inicializaDetalhe(ProfessorDisciplinaEntity professor) {
		
		if( professor.getTurmaProfessor() != null && !professor.getTurmaProfessor().isEmpty() ){
			for (TurmaProfessor turma : professor.getTurmaProfessor() ) {
				if( turma.getTurma() == null ){
					turma.setTurma( new TurmaEntity() );
				}
				
				/*if( disciplina.getDisciplina() != null && disciplina.getDisciplina().getDescricao() == null ){
					disciplina.getDisciplina().setDescricao( new String() );
				}*/
			}
		}
	}
	
	
	
}
