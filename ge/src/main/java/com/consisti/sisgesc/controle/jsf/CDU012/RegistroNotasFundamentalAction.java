package com.consisti.sisgesc.controle.jsf.CDU012;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.dominio.AbertoFechado;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalDet;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalDetEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalEntity;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalSubDet;
import com.consisti.sisgesc.entidade.RegistroNotasFundamentalSubDetEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class RegistroNotasFundamentalAction extends AppAction  {
	
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		RegistroNotasFundamentalEntity registro = (RegistroNotasFundamentalEntity)entidadePlc;
		//se o usuario for professor seta o objeto
		registro.setProfessor( setaProfessorVinculado() );
		setaAnoLetivo( registro );
		setaDataCadastro( registro );
		setaStatus( registro );
		trataCamposProfessor(registro);
		
		return super.novoApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#editaApos()
	 */
	@Override
	protected String editaApos() throws PlcException {
		
		RegistroNotasFundamentalEntity registro = (RegistroNotasFundamentalEntity)entidadePlc;
		
		setaNomeAlunosTurmas( registro );
		//esconde o botao do vinculado na edição para não alterar o professor
		contextHelperPlc.getRequest().setAttribute("desabilitaCampo", "S");
		trataCamposProfessor(registro);
		
		return super.editaApos();
	}

	/**
	 * Utilizado para desabilitar alguns campos quando o usuario for professor
	 * @param registro
	 * @throws PlcException
	 */
	private void trataCamposProfessor(RegistroNotasFundamentalEntity registro)
			throws PlcException {
		//se o usuario for professor bloqueia alguns campos
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioProfessor() ) ){
			registro.setBloqueiaCampo( true );
		}
	}

	/**
	 * Utilizado para setar o nome dos Alunos e turmas nos auxiliares, na ediçao
	 * @param registro
	 * @throws PlcException 
	 */
	private void setaNomeAlunosTurmas(RegistroNotasFundamentalEntity registro) throws PlcException {
		
		if( registro.getRegistroNotasFundamentalDet() != null ){
			
			for (RegistroNotasFundamentalDet registroTurma : registro.getRegistroNotasFundamentalDet() ) {
				((RegistroNotasFundamentalDetEntity)registroTurma).setNomeTurma( getIAppFacade().recuperaDescricaoTurma( registroTurma.getTurma().getId() ) );
				
				if( registroTurma.getRegistroNotasFundamentalSubDet() != null ){
					for (RegistroNotasFundamentalSubDet registroAluno : registroTurma.getRegistroNotasFundamentalSubDet() ) {
						((RegistroNotasFundamentalSubDetEntity)registroAluno).setNomeAluno( registroAluno.getAluno().getNomeAluno() );
					}
				}
			}
		}
	}

	/**
	 * Seta a data de cadastro
	 * @param registro
	 */
	private void setaDataCadastro(RegistroNotasFundamentalEntity registro) {
		registro.setDataCadastro( new Date() );
	}

	/**
	 * @param registro
	 */
	private void setaAnoLetivo(RegistroNotasFundamentalEntity registro) {
		
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");  
		Date dataAtual = new Date();
		registro.setAnoLetivo( Integer.parseInt( ano.format(dataAtual) ) );
		
	}

	/**
	 * Utilizado para montar o detalhe e sub-detalhe de acordo com o professor e disciplina
	 * @return
	 * @throws PlcException
	 */
	public String montaDetalheSubDetalhe() throws PlcException{
		
		RegistroNotasFundamentalEntity registro = (RegistroNotasFundamentalEntity)entidadePlc;
		
		if( registro.getDisciplina() != null ){
			getIAppFacade().montaDetalheSubDetalhe( registro );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/**
	 * Seta o status como aberto para novo registro
	 * @param registro
	 * @throws PlcException 
	 */
	private void setaStatus(RegistroNotasFundamentalEntity registro) throws PlcException {
		registro.setStatus( AbertoFechado.A );
	}
	
	/**
	 * Utilizado para acessar o facade
	 * @return IAppFacade
	 * @throws PlcException
	 */
	private IAppFacade getIAppFacade() throws PlcException {
		IAppFacade fc = (IAppFacade)getServiceFacade();
		return fc;
	}
}
