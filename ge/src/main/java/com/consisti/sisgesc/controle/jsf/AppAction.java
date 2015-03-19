/*  																													
	    				   jCompany Developer Suite																		
			    		Copyright (C) 2008  Powerlogic																	
	 																													
	    Este programa é licenciado com todos os seus códigos fontes. Você pode modificá-los e							
	    utilizá-los livremente, inclusive distribuí-los para terceiros quando fizerem parte de algum aplicativo 		
	    sendo cedido, segundo os termos de licenciamento gerenciado de código aberto da Powerlogic, definidos			
	    na licença 'Powerlogic Open-Source Licence 2.0 (POSL 2.0)'.    													
	  																													
	    A Powerlogic garante o acerto de erros eventualmente encontrados neste código, para os clientes licenciados, 	
	    desde que todos os códigos do programa sejam mantidos intactos, sem modificações por parte do licenciado. 		
	 																													
	    Você deve ter recebido uma cópia da licença POSL 2.0 juntamente com este programa.								
	    Se não recebeu, veja em <http://www.powerlogic.com.br/licencas/posl20/>.										
	 																													
	    Contatos: plc@powerlogic.com.br - www.powerlogic.com.br 														
																														
 */ 
package com.consisti.sisgesc.controle.jsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.model.UploadedFile;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.comuns.AppUsuarioPerfilVO;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.Anexo;
import com.consisti.sisgesc.entidade.AnexoEntity;
import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseContextVO;
import com.powerlogic.jcompany.comuns.PlcConstantesComuns;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction;
import com.powerlogic.jcompany.controle.jsf.PlcBaseLogicaArgumento;
import com.powerlogic.jcompany.controle.jsf.helper.PlcContextHelper;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

@SuppressWarnings("serial")
public class AppAction extends PlcBaseJsfAction {
	
	protected static final Logger log = Logger.getLogger(AppAction.class);
	private UploadedFile arquivo;
	private String descricao;
	
	/**
	 * vai validar o tamanho de um arquivo
	 * @throws PlcException 
	 */
	protected void validaTamanhoArquivo(UploadedFile arquivo, int tamanhoArquivo) throws PlcException {
		
		//throw new PlcException("msg.erro.tamanho.arquivo", new String[]{ arquivo.getFilename(), tamanhoArquivo+""});
		
	}
	
	/**
	 * Exibi o arquivo para o usuário
	 * @throws PlcException
	 * @throws IOException
	 */
	public void verArquivo() throws PlcException, IOException{
		
		String indice = contextHelperPlc.getRequest().getParameter("corpo:formulario:indiceArquivo");
		
		if (StringUtils.isNotEmpty(indice)){
			
			AnexoEntity anexo = null;
			
			if (entidadePlc instanceof AlunoEntity){
		    	AlunoEntity aluno = (AlunoEntity) entidadePlc;
		    	anexo = (AnexoEntity) aluno.getAnexoAluno().get(Integer.parseInt(indice));
		    } else if (entidadePlc instanceof FuncionarioEntity) {
		    	FuncionarioEntity funcionario = (FuncionarioEntity) entidadePlc;
		    	anexo = (AnexoEntity) funcionario.getAnexoFuncionario().get(Integer.parseInt(indice));
		    } else {
		    	ContratoEntity contrato = (ContratoEntity) entidadePlc;
		    	byte [] arquivo = contrato.getAdtivoAluno().get(Integer.parseInt(indice)).getAditivo();
		    	anexo = new AnexoEntity();
		    	anexo.setArquivo(arquivo);
		    	anexo.setNomeArquivo("Aditivo Contrato");
		    	anexo.setExtensao("pdf");
		    }
			
			downloadArquivo(anexo.getArquivo(), anexo.getNomeArquivo(), anexo.getExtensao());
		}
		
	}
	
	/**
	 * Add o arquivo em um objeto
	 * @throws IOException
	 * @throws PlcException
	 */
	public void addArquivo() throws IOException, PlcException{
		
		if (arquivo!=null){
			validaTamanhoArquivo(arquivo, 20);
			
			AnexoEntity anexo = new AnexoEntity();
			
			if (arquivo!=null){
					
				InputStream entrada = arquivo.getInputStream();
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
				
			    anexo.setNomeArquivo(arquivo.getFilename().substring(0, arquivo.getFilename().indexOf(".")));
			    anexo.setArquivo(bytes);
			    anexo.setDescricao(getDescricao());
			    anexo.setDataInclusao(new Date());
			    anexo.setExtensao(arquivo.getFilename().substring(arquivo.getFilename().indexOf(".")+1, arquivo.getFilename().length()));
			    
			    if (entidadePlc instanceof AlunoEntity){
			    	AlunoEntity aluno = (AlunoEntity) entidadePlc;
			    	anexo.setAluno((AlunoEntity)entidadePlc);
			    	if (aluno.getAnexoAluno()==null){
			    		aluno.setAnexoAluno(new ArrayList<Anexo>());
			    		aluno.getAnexoAluno().add(anexo);
			    	} else {
			    		aluno.getAnexoAluno().add(anexo);
			    	}
			    } else {
			    	FuncionarioEntity funcionario = (FuncionarioEntity) entidadePlc;
			    	anexo.setFuncionario((FuncionarioEntity)entidadePlc);
			    	if (funcionario.getAnexoFuncionario()==null){
			    		funcionario.setAnexoFuncionario(new ArrayList<Anexo>());
			    		funcionario.getAnexoFuncionario().add(anexo);
			    	} else {
			    		funcionario.getAnexoFuncionario().add(anexo);
			    	}
			    }
			    
			}
		}
		
		setDescricao("");
		
	}
	
	public static void downloadArquivo(byte[] arrayOut, String fileName, String extensao) throws PlcException, IOException{

		 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		
		 HttpServletResponse response = (HttpServletResponse) context.getResponse();
		 response.setHeader("Content-disposition", "attachment;filename="+ fileName+"."+extensao); 
		 response.setHeader("Cache-Control", "No Cache");
		 response.setContentLength(arrayOut.length); 
		 response.setContentType("application/"+extensao);
		
		 try{
		     OutputStream out = response.getOutputStream();
		
		     out.write(arrayOut, 0, arrayOut.length);
		
		     response.flushBuffer();
		     out.flush();
		     out.close();
		     FacesContext.getCurrentInstance().responseComplete();
		     return;
		 } catch (IOException ex){
		     System.out.println("Error in downloadFile: " + ex.getMessage());
		     throw new PlcException(ex);
		 }
		 
	 }
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#trataBotoesConformeLogicaApos()
	 */
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		super.trataBotoesConformeLogicaApos();
		
		//validaUrlAcesso();       
		
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_CLONAR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_VISUALIZA_DOCUMENTO, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_IMPRIMIR, PlcConstantes.NAO_EXIBIR );
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_PESQUISAR_RSS, PlcConstantes.NAO_EXIBIR );
		
		//trataPermissaoUsuario();
		
		if( PlcConstantesComuns.MODOS.MODO_INCLUSAO.equals( controleConversacaoPlc.getModoPlc() ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, PlcConstantes.NAO_EXIBIR );
		}
		
		//se for manuteçao nao apresenta o pesquisar
		if( PlcConstantesComuns.MODOS.MODO_INCLUSAO.equals( controleConversacaoPlc.getModoPlc() ) ||
				PlcConstantesComuns.MODOS.MODO_EDICAO.equals( controleConversacaoPlc.getModoPlc() )	){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_PESQUISAR, PlcConstantes.NAO_EXIBIR );
		}
		if( PlcConstantesComuns.MODOS.MODO_CONSULTA.equals( controleConversacaoPlc.getModoPlc() ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, PlcConstantes.NAO_EXIBIR );
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, PlcConstantes.NAO_EXIBIR );
		}
		//Se for popup nao apresenta o botao novo nem o excluir
		if( "popup".equals( controleConversacaoPlc.getModoJanelaPlc() ) &&
				!"S".equals( contextHelperPlc.getRequest().getParameter("exibeBtGrava") ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, PlcConstantes.NAO_EXIBIR );
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		}
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#abreAntes()
	 */
	@Override
	protected boolean abreAntes() throws PlcException {
		setaFuncionarioArgumento();
		
		return super.abreAntes();
	}

	/**
	 * Utilizado para apresentar os botoes de acordo com o perfil do usuario
	 * @throws PlcException
	 */
	private void trataPermissaoUsuario() throws PlcException {
		
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioGrava() ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, PlcConstantes.EXIBIR );
		}
		else{
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, PlcConstantes.NAO_EXIBIR );
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		}
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioPesquisa() ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_PESQUISAR, PlcConstantes.EXIBIR );
		}
		else{
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_PESQUISAR, PlcConstantes.NAO_EXIBIR );
		}
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioExclui() ) ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, PlcConstantes.EXIBIR );
		}
		else{
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, PlcConstantes.NAO_EXIBIR );
		}
	}
	
	/**
	 * Recupera o usuario
	 * @return AppUsuarioPerfilVO
	 * @throws PlcException
	 */
	@SuppressWarnings("deprecation")
	public AppUsuarioPerfilVO getPerfilUsuario() throws PlcException{
		
		PlcBaseContextVO contexto = (PlcBaseContextVO) getContext();
		if(contexto==null)
			contexto = (PlcBaseContextVO) montaContextHelperPlc.montaContextParam(contextHelperPlc.getRequest(), entidadePlc);
		else if(contexto.getPerfilUsu() == null)
			contexto = (AppBaseContextVO) montaContextHelperPlc.montaContextParam(contextHelperPlc.getRequest(), entidadePlc);
		
		return (AppUsuarioPerfilVO)contexto.getPerfilUsu();
		
	}
	
	/**
	 * Utilizado para validar se o usuario tem acesso a url
	 * @throws PlcException
	 */
	public void validaUrlAcesso() throws PlcException{
		
		if( getContext() != null && getContext().getPerfilUsu() != null ){
			
			if( getContext().getPerfilUsu().getFiltros() != null ){
				boolean permissaoAcesso = false;
				for (String filtros : getContext().getPerfilUsu().getFiltros()) {
					
					if( getContext().getNomeAction().toString().equals( filtros ) ){
						permissaoAcesso = true;
						break;
					}
				}
				
				if( !permissaoAcesso ){
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect( PlcContextHelper.getInstance().getRequest().getContextPath() + "/plc/erros/erro403.jsp" );
						FacesContext.getCurrentInstance().responseComplete();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Método recuperaArgVOPropriedadeArgumentoPesquisa()
	 * Método inserido para recuperar determinado ArgVO pelo nome - Utilizado em vários UC´s
	 * @param List <PlcArgVO> listaArgumentos
	 * @param String nomePropriedade
	 * @return String
	 * @throws PlcException 
	 */
	@SuppressWarnings("unchecked")
	public PlcArgVO recuperarArgVOPropriedadeArgumentoPesquisa(String nomeCampo) throws PlcException {
		
		if( ((PlcBaseLogicaArgumento)logicaItensPlc) != null ){

			Map<String, PlcArgVO> listaArgumentos = ((PlcBaseLogicaArgumento)logicaItensPlc).getArgumentos();
			Collection<PlcArgVO> collection = listaArgumentos.values();
			
			for (Iterator iter = collection.iterator(); iter.hasNext();) {
				PlcArgVO argVo = (PlcArgVO) iter.next();
				{
					if (nomeCampo.equals(argVo.getNome())) {
						return argVo;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Utilizado para setar o Funcionario na tela de argumento, quando o usuario for professor, para que ele possa pesquisar somente as turmas referente a ele.
	 * @throws PlcException
	 */
	private void setaFuncionarioArgumento() throws PlcException {
		
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioProfessor() ) ){
			
			PlcArgVO arg = recuperarArgVOPropriedadeArgumentoPesquisa("professor");
			
			if( arg != null ){
				FuncionarioEntity funcionario = getObjetoFuncionario();
				
				arg.setValor( funcionario.getIdAux() );
				arg.setValorObjeto(funcionario);
				
				contextHelperPlc.getRequest().setAttribute("desabilitaCampo", "S");
			}
		}
	}

	/**
	 * Utilizado para gerar um objeto de funcionario, com id e nome de acordo com o perfil do usuario
	 * @return
	 * @throws PlcException
	 */
	private FuncionarioEntity getObjetoFuncionario() throws PlcException {
		FuncionarioEntity funcionario = new FuncionarioEntity();
		funcionario.setId( getPerfilUsuario().getIdFuncionario() );
		funcionario.setNome( getPerfilUsuario().getNomeUsuario() );
		return funcionario;
	}
	
	/**
	 * Utilizado para setar o professor no vinculado quando o usuario logado for um professor.
	 * Para que ele já venha preenchido na tela de manutencao
	 * @param registro
	 * @throws PlcException
	 */
	public FuncionarioEntity setaProfessorVinculado() throws PlcException {
		
		if( PlcSimNao.S.equals( getPerfilUsuario().getUsuarioProfessor() ) ){
			
			FuncionarioEntity funcionario = getObjetoFuncionario();
			
			if( funcionario != null ){
				contextHelperPlc.getRequest().setAttribute("desabilitaCampo", "S");
				return funcionario;
			}
		}
		return null;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	/**
	 * Utilizado para retirar as mensagens quando necessário
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public void retirarMensagem() throws PlcException {
		//se informar os dois filtros.
		if(contextHelperPlc.getRequestAttribute("msgVermelhoPlc") != null){
            //Recupera a lista de mensagem com a msg padrao do jcompany
            List lista = (List) contextHelperPlc.getRequestAttribute("msgVermelhoPlc");
            //Se a lista esta com objetos, retira a msn para não aparecer
            if(!lista.isEmpty()){
            	for (int i = 0; i < lista.size(); i++) {
					if( lista.get(i).toString().contains("Não foi possível recuperar um registro com identificador 0.") ) {
						lista.remove(i);
					}
				}
            }
        }
	}
	
	/**
	 * Data de pagamento nao pode ser menor que a data atual
	 * @param contaPagar
	 * @throws PlcException 
	 */
	public void validaDataPagamento(Date dataPagamentoRecebimento) throws PlcException {
		
		if( dataPagamentoRecebimento != null ){
			if( dataPagamentoRecebimento.after( new Date() ) ){
				throw new PlcException("msg.info.dataPagamento.menor");
			}
		}
	}
	
	/**
	 * Valida data de vencimento, nao deixando cadastrar com a data de vencimento menor que a data atual
	 * @param contaPagar
	 * @throws PlcException
	 */
	public void validaDataVencimento(Date dataVencimento) throws PlcException {

		if( dataVencimento != null ){
			if( dataVencimento.before( new Date() ) ){
				helperMsgJsfPlc.msg("msg.info.dataVencimento.menor", Cor.msgAmareloPlc.toString() );
				//throw new PlcException("msg.info.dataVencimento.menor");
			}
		}
	}
	
}
