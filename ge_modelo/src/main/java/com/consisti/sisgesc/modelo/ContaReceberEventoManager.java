package com.consisti.sisgesc.modelo;

import java.util.List;

import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class ContaReceberEventoManager extends AppManager {
	
	private AlunoDAO alunoDAO;
	
	public ContaReceberEventoManager( AlunoDAO alunoDAO ) {
		this.alunoDAO = alunoDAO;
	}
	
	public List<AlunoEntity> recuperarAluno(Aluno aluno, Turma turma, TipoEducacao educacao) throws PlcException{
		
		return alunoDAO.recuperarAluno(aluno, turma, educacao);
	}

}
