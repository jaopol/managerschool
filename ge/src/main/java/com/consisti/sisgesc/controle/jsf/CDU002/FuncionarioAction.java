package com.consisti.sisgesc.controle.jsf.CDU002;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.myfaces.trinidad.model.UploadedFile;

import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.entidade.HorarioTrabalho;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.controle.jsf.PlcBaseLogicaArgumento;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class FuncionarioAction extends RelatorioActionPlc  {
	
	private boolean pesquisaProfessor = false;
	private UploadedFile fotoFuncionario;
	
	/* (non-Javadoc)
	 * @see com.consisti.sisgesc.controle.jsf.AppAction#trataBotoesConformeLogicaApos()
	 */
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		trataBotoes();
		super.trataBotoesConformeLogicaApos();
	}
	
	public void geraRelatorioPlc() throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		
		//super.geraRelatorioPlc("", aluno, mesAtual());
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {

		FuncionarioEntity funcionario = (FuncionarioEntity)entidadePlc;
		setDataCadastro( funcionario );
		return super.novoApos();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected boolean novoDetalheAntes(Collection colecao) throws PlcException {
		
		/*
		 * Detalhes q contem somente 1	
		 * */
		if( "endereco".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "dadoFuncionario".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "horarioTrabalho".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		
		return super.novoDetalheAntes(colecao);
	}

	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#limpaArgsApos()
	 */
	@Override
	protected String limpaArgsApos() throws PlcException {
		
		marcaFlagProfessor();
		return super.limpaArgsApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesAntes()
	 */
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		
		FuncionarioEntity funcionario = (FuncionarioEntity)entidadePlc;
		contextHelperPlc.getRequest().getSession().setAttribute("fotoFuncionario", funcionario.getFotoFuncionario());
		removeHorarioSabado( funcionario );
		
		return super.gravaSimplesAntes();
	}

	@Override
	protected String editaApos() throws PlcException {
		
		FuncionarioEntity funcionario = (FuncionarioEntity)entidadePlc;
		contextHelperPlc.getRequest().getSession().setAttribute("fotoFuncionario", funcionario.getFotoFuncionario());
		
		return super.editaApos();
	}
	
	public void anexarFotoFuncionario() throws IOException, PlcException {

		if (fotoFuncionario != null) {
			FuncionarioEntity funcionario = (FuncionarioEntity) entidadePlc;
			try {
				InputStream entrada = fotoFuncionario.getInputStream();
			    ByteArrayOutputStream saida = new ByteArrayOutputStream();  
			      
			    // copiar o conteudo do arquivo para o ByteArrayOutputStream  
			    byte[] buffer = new byte[4096];  
			    for (int lidos = -1;  
			         (lidos = entrada.read(buffer, 0, buffer.length)) != -1;  
			         saida.write(buffer, 0, lidos)) {  
			        // esse for não tem corpo  
			        // os próprios blocos de inicialização, teste e incremento  
			        // são suficientes para realizar a cópia
			    }  
			    entrada.close();  
			    saida.flush();  
			    saida.close();   
			     
			    // obter array de bytes com conteudo do arquivo lido  
			    byte[] bytes = saida.toByteArray();
			    funcionario.setFotoFuncionario(bytes);
				contextHelperPlc.getRequest().getSession().setAttribute("fotoFuncionario", bytes);
			} catch (Exception e) {
				throw new PlcException("erro.arquivo.formato.invalido");
			}
			
		}

	}
	
	/**
	 * Utilizado na alteração, para limpar os dados de horário quando o funcionario não trabalhar no sábado
	 * @param funcionario
	 */
	private void removeHorarioSabado(FuncionarioEntity funcionario) {
		
		if( funcionario.getHorarioTrabalho() != null && !funcionario.getHorarioTrabalho().isEmpty() ){
			for (HorarioTrabalho horario : funcionario.getHorarioTrabalho() ) {
				//se não trabalha no sabado limpa os campos de horarios do sabado na edição
				if( PlcSimNao.N.equals( horario.getTrabalhaSabado() ) && horario.getId() != null){
					horario.setEntradaSabado( null );
					horario.setSaidaSabado( null );
					horario.setIntervaloAlmocoSabInicio( null );
					horario.setIntervaloAlmocoSabFim( null );
				}
			}
		}
	}

	/**
	 * Utilizado quando popup e que o valor default do argumento Professor for 'S', para pesquisa somente de professor.
	 */
	private void marcaFlagProfessor() {
		
		//Se for para pesquisar somente por professor
		if( isPesquisaProfessor() ){
		
			Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento) this.logicaItensPlc).getArgumentos();
			if (listaArgumentos != null) {
				
				Collection<PlcArgVO> values = listaArgumentos.values();
				for (PlcArgVO plcArgVO : values) {
					if( "professor".equals( plcArgVO.getNome() ) ){
						plcArgVO.setValor( "S" );
					}
				}
			}
		}
	}
	
	/**
	 * @param funcionario
	 */
	private void setDataCadastro(FuncionarioEntity funcionario) {
		funcionario.setDataCadastro( new Date() );
	}
	
	/**
	 * Trata apresentação dos botões
	 */
	private void trataBotoes() {
		
		if( "popup".equals( controleConversacaoPlc.getModoJanelaPlc() ) ){
			contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR);
			
			if( "S".equals( contextHelperPlc.getRequestParameter("professor") ) ){
				setPesquisaProfessor( true );
			}
		}
		//TODO ainda nao tem relatorio
		/*if( ((FuncionarioEntity)entidadePlc) != null && ((FuncionarioEntity)entidadePlc).getId() != null ){
			contextHelperPlc.getRequest().setAttribute( "exibeImprimirFuncionario" , "S" );
		}*/
		
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ARQUIVO.IND_ARQ_ANEXADO, "S");
		
	}

	public boolean isPesquisaProfessor() {
		return pesquisaProfessor;
	}

	public void setPesquisaProfessor(boolean pesquisaProfessor) {
		this.pesquisaProfessor = pesquisaProfessor;
	}

	public UploadedFile getFotoFuncionario() {
		return fotoFuncionario;
	}

	public void setFotoFuncionario(UploadedFile fotoFuncionario) {
		this.fotoFuncionario = fotoFuncionario;
	}
	
}
