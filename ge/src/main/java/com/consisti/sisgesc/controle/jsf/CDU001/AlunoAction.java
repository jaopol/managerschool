package com.consisti.sisgesc.controle.jsf.CDU001;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.RelatorioActionPlc;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.dominio.TipoMatricula;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.CertidaoNascimento;
import com.consisti.sisgesc.entidade.CertidaoNascimentoEntity;
import com.consisti.sisgesc.entidade.FiliacaoMae;
import com.consisti.sisgesc.entidade.FiliacaoMaeEntity;
import com.consisti.sisgesc.entidade.FiliacaoPai;
import com.consisti.sisgesc.entidade.FiliacaoPaiEntity;
import com.consisti.sisgesc.entidade.ResponsavelAlunoCasoAcidente;
import com.consisti.sisgesc.entidade.ResponsavelAlunoCasoAcidenteEntity;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAluno;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity;
import com.consisti.sisgesc.entidade.SaudeAluno;
import com.consisti.sisgesc.entidade.ServicoAluno;
import com.consisti.sisgesc.entidade.ServicoAlunoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class AlunoAction extends RelatorioActionPlc  {

	private String descricao;
	private UploadedFile fotoAluno;
	private Calendar dataBaseContrato;
	
	private boolean popup;
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#trataBotoesConformeLogicaApos()
	 */
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		trataBotoes();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		aluno.setAnexoAluno(null);
		setDataCadastro( aluno );
		aluno.setStatus(AtivoInativo.A);
		return super.novoApos();
	}
	
	@SuppressWarnings("deprecation")
	public void calculaMatricula(ValueChangeEvent event) throws PlcException {

		AlunoEntity aluno = (AlunoEntity)entidadePlc;
	
		if (aluno.getValorMensalidadeAluno()!=null && event.getNewValue()!=null){
			TipoMatricula tipoMatricula = (TipoMatricula) event.getNewValue();
			if (TipoMatricula.IN.equals(tipoMatricula)){
				aluno.setValorMatricula(aluno.getValorMensalidadeAluno());
			} else if (TipoMatricula.IS.equals(tipoMatricula)){
				aluno.setValorMatricula(new BigDecimal(0));
				aluno.setDescontoMatricula(new BigDecimal(0));
			} else if (TipoMatricula.PR.equals(tipoMatricula)){
				BigDecimal valorDividido = aluno.getValorMensalidadeAluno().divide(new BigDecimal(12),2, RoundingMode.HALF_UP);
				BigDecimal mesesFaltantes = new BigDecimal(new Date().getMonth()+1-12);
				aluno.setValorMatricula(valorDividido.multiply(mesesFaltantes).multiply( new BigDecimal( -1 ) ) );
			}
			else if( TipoMatricula.DE.equals(tipoMatricula) ){
				aluno.setValorMatricula(aluno.getValorMensalidadeAluno());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#editaApos()
	 */
	@Override
	protected String editaApos() throws PlcException {
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		adicionaDetalhe( aluno );
		contextHelperPlc.getRequest().getSession().setAttribute("fotoAluno", aluno.getFotoAluno());
		return super.editaApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesApos()
	 */
	@Override
	protected void gravaSimplesApos() throws PlcException {
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		adicionaDetalhe( aluno );
		contextHelperPlc.getRequest().getSession().setAttribute("fotoAluno", aluno.getFotoAluno());
		super.gravaSimplesApos();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#gravaSimplesAntes()
	 */
	@Override
	protected boolean gravaSimplesAntes() throws PlcException {
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		
		if (aluno.getFiliacaoPai()!=null){
			if (aluno.getFiliacaoPai().size()>0){
				aluno.getFiliacaoPai().get(0).setAluno(aluno);
			}
		}
		
		if (aluno.getFiliacaoMae()!=null){
			if (aluno.getFiliacaoMae().size()>0){
				aluno.getFiliacaoMae().get(0).setAluno(aluno);
			}
		}
		
		validaCampoObrigatorio( aluno );
		
		contextHelperPlc.getRequest().getSession().setAttribute("fotoAluno", aluno.getFotoAluno());
		((AppBaseContextVO)getContext()).setCasoUso( AppConstantesComuns.CASOUSO.CDU001 );
		
		return super.gravaSimplesAntes();
	}
	
	/**
	 * @param aluno
	 * @throws PlcException
	 */
	private void validaCampoObrigatorio(AlunoEntity aluno) throws PlcException {
		List<String> listaCampoMSg = new ArrayList<String>();
		
		if(aluno.getValorMatricula() == null ){
			throw new PlcException("msg.campo.obrigatorio.aluno", new String[]{"Matricula � obrigat�rio(a)"} );
		}
		
		validaCampoObrigatorioPai( aluno.getFiliacaoPai(), listaCampoMSg );
		validaCampoObrigatorioMae( aluno.getFiliacaoMae(), listaCampoMSg );
		validaCampoObrigatorioSaudeAluno( aluno.getSaudeAluno(), listaCampoMSg );
		//Se a lista n�o estiver vazia apresenta a mensagem
		if( !listaCampoMSg.isEmpty() ){
			
			for (int i = 0; i < listaCampoMSg.size() -1 ; i++) {
				helperMsgJsfPlc.msgErro( "msg.campo.obrigatorio.aluno", new String[]{ listaCampoMSg.get(i) } );
			}
			throw new PlcException("msg.campo.obrigatorio.aluno", new String[]{ listaCampoMSg.get(listaCampoMSg.size() - 1)} );
		}
		
	}

	/**
	 * @param saudeAluno
	 * @param listaCampoMsg
	 */
	private void validaCampoObrigatorioSaudeAluno(List<SaudeAluno> saudeAluno, List<String> listaCampoMsg) {
		
		if( saudeAluno != null && !saudeAluno.isEmpty() ){
			
			for ( SaudeAluno saude : saudeAluno ) {
					
				if( PlcSimNao.S.equals( saude.getFazTratamentoSaude() ) && saude.getQualTratamentoSaude() == null ){
					listaCampoMsg.add( "O campo Qual de Faz algum tratamento de sa�de � obrigat�rio. [ Sa�de Aluno ]" );
				}
				
				if( PlcSimNao.S.equals( saude.getProblGraveSaude() ) && saude.getQualProblGraveSaude() == null ){
					listaCampoMsg.add( "O campo Qual de Seu filho apresenta algum problema grave de sa�de � obrigat�rio. [ Sa�de Aluno ]" );
				}
				
				if( PlcSimNao.S.equals( saude.getUsoConstanteRemedioControlado() ) && saude.getQualRemedioControlado() == null ){
					listaCampoMsg.add( "O campo Qual de Seu filho faz uso constantes de algum medicamento controlado � obrigat�rio. [ Sa�de Aluno ]" );
				}
				
				if( PlcSimNao.S.equals( saude.getPlanoSaude() ) && saude.getQualPlanoSaude() == null ){
					listaCampoMsg.add( "O campo Qual de Seu filho tem plano de sa�de � obrigat�rio. [ Sa�de Aluno ]" );
				}
				if( PlcSimNao.S.equals( saude.getAlergia() ) && saude.getQualAlergia() == null ){
					listaCampoMsg.add( "O campo Qual de Seu filho tem algum tipo de alergia � obrigat�rio. [ Sa�de Aluno ]" );
				}
			}
		}
	}

	/**
	 * @param filiacaoMae
	 * @param listaCampoMsg
	 */
	private void validaCampoObrigatorioMae(List<FiliacaoMae> filiacaoMae, List<String> listaCampoMsg) {
		
		if( filiacaoMae != null && !filiacaoMae.isEmpty() ){
			
			for ( FiliacaoMae mae : filiacaoMae ) {
				//Se o nome do pai estiver preenchido validar os campos obrigatorios
				if( mae.getNomeMae() != null ){
					
					if( mae.getDataNascimento() == null){
						listaCampoMsg.add( "O campo Data de nascimento � obrigat�rio. [ Filia��o M�e ]" );
					}
					
					if( StringUtils.isBlank( mae.getIdentidade() ) ){
						listaCampoMsg.add( "O campo Identidade � obrigat�rio. [ Filia��o M�e ]" );
					}
					
					if( StringUtils.isBlank( mae.getCpf() ) ){
						listaCampoMsg.add( "O campo CPF � obrigat�rio. [ Filia��o M�e ]" );
					}
				}
			}
		}
	}

	private void validaCampoObrigatorioPai(List<FiliacaoPai> filiacaoPai, List<String> listaCampoMsg ) {
		
		if( filiacaoPai != null && !filiacaoPai.isEmpty() ){
			
			for ( FiliacaoPai pai : filiacaoPai ) {
				//Se o nome do pai estiver preenchido validar os campos obrigatorios
				if( pai.getNomePai() != null ){
					
					if( pai.getDataNascimento() == null){
						listaCampoMsg.add( "O campo Data de nascimento � obrigat�rio. [ Filia��o Pai ]" );
					}
					if( StringUtils.isBlank( pai.getIdentidade() ) ){
						listaCampoMsg.add( "O campo Identidade � obrigat�rio. [ Filia��o Pai ]" );
					}
					if( StringUtils.isBlank( pai.getCpf() ) ){
						listaCampoMsg.add( "O campo CPF � obrigat�rio. [ Filia��o Pai ]" );
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoDetalheAntes(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean novoDetalheAntes(Collection colecao) throws PlcException {
		
		/*
		 * Detalhes q contem somente 1	
		 * */
		if( "endereco".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "filiacaoPai".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "filiacaoMae".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "saudeAluno".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "responsavelAlunoCasoAcidente".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "anexoAluno".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "responsavelFinanceiroAluno".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "certidaoNascimento".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		else if( "servicoAluno".equals( controleConversacaoPlc.getDetCorrPlc() ) ){
			return false;
		}
		return super.novoDetalheAntes(colecao);
	}

	public void anexarFotoAluno() throws IOException, PlcException {

		if (fotoAluno != null) {
			AlunoEntity aluno = (AlunoEntity) entidadePlc;
			try {
				InputStream entrada = fotoAluno.getInputStream();
			    ByteArrayOutputStream saida = new ByteArrayOutputStream();  
			      
			    // copiar o conteudo do arquivo para o ByteArrayOutputStream  
			    byte[] buffer = new byte[4096];  
			    for (int lidos = -1;  
			         (lidos = entrada.read(buffer, 0, buffer.length)) != -1;  
			         saida.write(buffer, 0, lidos)) {  
			        // esse for n�o tem corpo  
			        // os pr�prios blocos de inicializa��o, teste e incremento  
			        // s�o suficientes para realizar a c�pia
			    }  
			    entrada.close();  
			    saida.flush();  
			    saida.close();   
			     
			    // obter array de bytes com conteudo do arquivo lido  
			    byte[] bytes = saida.toByteArray();
				aluno.setFotoAluno(bytes);
				contextHelperPlc.getRequest().getSession().setAttribute("fotoAluno", bytes);
			} catch (Exception e) {
				throw new PlcException("erro.arquivo.formato.invalido");
			}
			
		}

	}
	
	/**
	 * Adiciona os detalhes caso necessario
	 * @param aluno 
	 * @throws PlcException
	 */
	private void adicionaDetalhe(AlunoEntity aluno) throws PlcException {
		
		if( aluno.getCertidaoNascimento() == null ){
			aluno.setCertidaoNascimento( new ArrayList<CertidaoNascimento>() );
		}
		if( aluno.getFiliacaoPai() == null ){
			aluno.setFiliacaoPai( new ArrayList<FiliacaoPai>() );
		}
		if( aluno.getFiliacaoMae() == null ){
			aluno.setFiliacaoMae( new ArrayList<FiliacaoMae>() );
		}
		if( aluno.getResponsavelAlunoCasoAcidente() == null ){
			aluno.setResponsavelAlunoCasoAcidente( new ArrayList<ResponsavelAlunoCasoAcidente>() );
		}
		if( aluno.getCertidaoNascimento().isEmpty() ){
			aluno.getCertidaoNascimento().add(new CertidaoNascimentoEntity());
		}
		if( aluno.getFiliacaoMae().isEmpty() ){
			aluno.getFiliacaoMae().add( new FiliacaoMaeEntity() );
		}
		if( aluno.getFiliacaoPai().isEmpty() ){
			aluno.getFiliacaoPai().add( new FiliacaoPaiEntity() );
		}
		
		if( aluno.getResponsavelAlunoCasoAcidente().isEmpty() ){
			aluno.getResponsavelAlunoCasoAcidente().add( new ResponsavelAlunoCasoAcidenteEntity() );
			aluno.getResponsavelAlunoCasoAcidente().add( new ResponsavelAlunoCasoAcidenteEntity() );
		}
		else{
			if( aluno.getResponsavelAlunoCasoAcidente().size() == 1 ){
				aluno.getResponsavelAlunoCasoAcidente().add( new ResponsavelAlunoCasoAcidenteEntity() );
			}
		}
		if( aluno.getServicoAluno().isEmpty() ){
			aluno.getServicoAluno().add( new ServicoAlunoEntity() );
		}
	}

	/**
	 *Utilizado para setar a data atual do cadastro
	 * @param aluno 
	 */
	private void setDataCadastro(AlunoEntity aluno) { 
		aluno.setDataCadastro( new Date() );
	}

	/**
	 * Calcula do valor da mensalidade com o desconto
	 * @return
	 * @throws PlcException
	 */
	public String calculaDescontoMensalidade() throws PlcException{
		
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		
		if ( aluno.getDescontoMensalidade() != null ){
			aluno.setValorTotalMensalidade( aluno.getValorMensalidadeAluno().subtract( aluno.getDescontoMensalidade() ) );
		} else {
			aluno.setValorTotalMensalidade( aluno.getValorMensalidadeAluno());
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/**
	 * Calcula do valor da matricula com o desconto
	 * @return
	 * @throws PlcException
	 */
	public String calculaDescontoMatricula() throws PlcException{
		
		AlunoEntity aluno = (AlunoEntity) entidadePlc;
		
		if ( aluno.getDescontoMatricula() != null ){
			aluno.setValorMatricula( aluno.getValorMensalidadeAluno().subtract(  aluno.getDescontoMatricula() ) );
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/**
	 * Utilizado para calcular a carga horaria
	 * @return
	 * @throws PlcException 
	 */
	public String calculaCargaHoraria() throws PlcException{
		
		AlunoEntity aluno = (AlunoEntity) entidadePlc;

		if (aluno.getHoraEntrada() != null && aluno.getHoraSaida() != null) {

			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

			long min_1 = getMinutos(aluno.getHoraEntrada(), formatter);
			long min_2 = getMinutos(aluno.getHoraSaida(), formatter);
			long result = (min_2 - min_1) * 60 * 1000;
			Date data = new Date(result);

			aluno.setCargaHoraria( formatter.format(data) );
			
			recuperaValorMensalidade( aluno );
		}

		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	 /**
	  * Recupera e seta o valor da mensalidade
	 * @param aluno
	 * @throws PlcException
	 */
	private void recuperaValorMensalidade(AlunoEntity aluno) throws PlcException {
		
		 if ( StringUtils.isNotBlank( aluno.getCargaHoraria() ) && aluno.getTurma() != null ){
			 
			 IAppFacade fc = (IAppFacade)getServiceFacade();
			 BigDecimal valorMensalidade = fc.recuperaValorMensalidade( aluno.getTurma().getId(), aluno.getCargaHoraria(), Integer.parseInt(aluno.getAnoLetivo()) ) ;
			 
			 if( valorMensalidade != null ){
				 aluno.setValorMensalidadeAluno( valorMensalidade );
				 aluno.setValorTotalMensalidade( aluno.getValorMensalidadeAluno() );
			 }
			 else{
				 throw new PlcException("erro.valorMensalidade.naoEncontrado");
			 }
		 }
		 else if( aluno.getTurma() == null ){
			 aluno.setHoraEntrada( null );
			 aluno.setHoraSaida( null );
			 aluno.setCargaHoraria(null);
			 throw new PlcException("msg.error.turmaObrigatoria");
		 }
	}

	/**
	 * Pega os minutos pelas horas
	 * @param hora
	 * @param formatter
	 * @return
	 */
	private static long getMinutos(String hora, SimpleDateFormat formatter) {    
         Date data;    
            try {
				data = formatter.parse(hora);
			} catch (ParseException e) {
				return 0;
			}    
		return data.getTime() / 1000 / 60;    
      }   

	/**
	 * Trata apresenta��o dos bot�es
	 * @throws PlcException 
	 */
	private void trataBotoes() throws PlcException {
		
		serviceVisaoPlc.naoExibirAbaTabFolderPos(contextHelperPlc.getRequest(), 6);
		serviceVisaoPlc.naoExibirAbaTabFolderPos(contextHelperPlc.getRequest(), 8);
		serviceVisaoPlc.naoExibirAbaTabFolderPos(contextHelperPlc.getRequest(), 9);
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ARQUIVO.IND_ARQ_ANEXADO, "S");
		contextHelperPlc.getRequest().setAttribute(PlcConstantes.ACAO.EXIBE_BT_EXCLUIR, "N");
		
		if( ((AlunoEntity)entidadePlc) != null && ((AlunoEntity)entidadePlc).getId() != null ){
			contextHelperPlc.getRequest().setAttribute("exibeImprimirMatricula", "S");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirTermoMatricula", "S");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirFichaSaude", "S");
		}
		else{
			contextHelperPlc.getRequest().setAttribute("exibeImprimirMatricula", "N");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirTermoMatricula", "N");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirFichaSaude", "N");
		}
		
		if( ((AlunoEntity)entidadePlc) != null && ((AlunoEntity)entidadePlc).getFotoAluno() != null  ){
			contextHelperPlc.getRequest().getSession().setAttribute("fotoAluno", ((AlunoEntity)entidadePlc).getFotoAluno() );
		}
	}
	
	@Override
	protected Map<String, PlcArgVO> montaMapaArgumentosApos(
			Map<String, PlcArgVO> argumentos) throws PlcException {
		//utilizado quando popup
		if( "S".equals( contextHelperPlc.getRequestParameter("alunoPopup") ) ){
			if (argumentos != null) {
				Collection<PlcArgVO> values = argumentos.values();
				for (PlcArgVO plcArgVO : values) {
					if( "status".equals( plcArgVO.getNome() ) ){
						plcArgVO.setValorObjeto(AtivoInativo.A);
						plcArgVO.setValor(AtivoInativo.A.getCodigo());
						break;
					}
				}
			}
			
		}
		
		return super.montaMapaArgumentosApos(argumentos);
	}
	
	@Override
	protected String limpaArgsApos() throws PlcException {
		
		if( "S".equals( contextHelperPlc.getRequestParameter("alunoPopup") ) ){
			List<PlcArgVO> argumentos = montaListaArgumentosPesquisa();
			if (argumentos != null) {
				for (PlcArgVO plcArgVO : argumentos) {
					if( "status".equals( plcArgVO.getNome() ) ){
						plcArgVO.setValorObjeto(AtivoInativo.A);
						plcArgVO.setValor(AtivoInativo.A.getCodigo());
						break;
					}
				}
			}
		}
		
		return super.limpaArgsApos();
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelatorioPlc() throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		HashMap map = mesAtual();
		setAnoLetivo(aluno, map);
		
		super.geraRelatorio(AppConstantesComuns.RELATORIO.REL_CDU001, aluno, map);
	}

	@SuppressWarnings("unchecked")
	private void setAnoLetivo(AlunoEntity aluno, HashMap map) {
		
		if( StringUtils.isNotBlank( aluno.getAnoLetivo() ) ){
			map.put("anoLetivo", aluno.getAnoLetivo() );
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			map.put("anoLetivo", sdf.format(getDataBaseContrato().getTime()));
		}
	}
	
	/**
	 * Utilizado para limpar os dados referente a valores da mensalidade e matricula, caso o usuario altere a turma
	 * @return
	 */
	public String limpaMatricula(){
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		aluno.setValorMatricula( null );
		aluno.setValorMensalidadeAluno( null );
		aluno.setValorTotalMensalidade( null );
		aluno.setCargaHoraria( null );
		aluno.setHoraEntrada( null );
		aluno.setHoraSaida( null );
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelatorioFichaSaudePlc() throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		
		HashMap map = mesAtual();
		setAnoLetivo(aluno, map);
		
		super.geraRelatorio(AppConstantesComuns.RELATORIO.REL_FICHA_SAUDE, aluno, map);
	}
	
	@SuppressWarnings("unchecked")
	public void geraRelatorioTermoMatricula() throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		HashMap map = mesAtual();
		String rg = aluno.getResponsavelFinanceiroAluno().get(0).getIdentidade();
		if (StringUtils.isNotEmpty(rg)){
			map.put("rgResp", rg);
		} else {
			map.put("rgResp", "");
		}
		setAnoLetivo(aluno, map);
		
		super.geraRelatorio(AppConstantesComuns.RELATORIO.TERMO_MATRICULA, aluno, map);
	}
	
	public String setarPaiResponsavel(){
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		if(aluno.getFiliacaoPai() != null && !aluno.getFiliacaoPai().isEmpty() ){
			if(aluno.getResponsavelFinanceiroAluno() == null ){
				aluno.setResponsavelFinanceiroAluno( new ArrayList<ResponsavelFinanceiroAluno>() );
			}
			getResponsavelFinanceiro(aluno.getFiliacaoPai().get(0), aluno);
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	public String setarMaeResponsavel(){
		
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		if(aluno.getFiliacaoMae() != null && !aluno.getFiliacaoMae().isEmpty() ){

			if( aluno.getResponsavelFinanceiroAluno() == null ){
				aluno.setResponsavelFinanceiroAluno( new ArrayList<ResponsavelFinanceiroAluno>() );
			}
			getResponsavelFinanceiro(aluno.getFiliacaoMae().get(0), aluno);
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	private void getResponsavelFinanceiro(Object obj, AlunoEntity aluno) {

		if( aluno.getResponsavelFinanceiroAluno().isEmpty() ){
			
			ResponsavelFinanceiroAluno resp = new ResponsavelFinanceiroAlunoEntity();
			resp.setAluno(aluno);
			
			if( obj instanceof FiliacaoPaiEntity ){
				setPaiResponsavelFinanceiro(aluno, resp);
			}
			else{
				setMaeResponsavelFinanceiro(aluno, resp);
			}
		}
		else{
			if( obj instanceof FiliacaoPaiEntity ){
				setPaiResponsavelFinanceiro( aluno, aluno.getResponsavelFinanceiroAluno().get(0) );
			}
			else{
				setMaeResponsavelFinanceiro( aluno, aluno.getResponsavelFinanceiroAluno().get(0) );
			}
		}
		
	}

	private void setMaeResponsavelFinanceiro(AlunoEntity aluno,	ResponsavelFinanceiroAluno resp) {
		resp.setNome( aluno.getFiliacaoMae().get(0).getNomeMae() );
		resp.setCpf( aluno.getFiliacaoMae().get(0).getCpf() );
		resp.setDataNascimento( aluno.getFiliacaoMae().get(0).getDataNascimento() );
		resp.setIdentidade( aluno.getFiliacaoMae().get(0).getIdentidade() );
		resp.setProfissao( aluno.getFiliacaoMae().get(0).getProfissao() );
		resp.setTelefone( aluno.getFiliacaoMae().get(0).getTelefone() );
		resp.setCelular( aluno.getFiliacaoMae().get(0).getCelular() );
		resp.setLocalTrabalho( aluno.getFiliacaoMae().get(0).getLocalTrabalho() );
		resp.setEmail( aluno.getFiliacaoMae().get(0).getEmail() );
		
		setEnderecoResponsavelFinanceiro(aluno, resp);
		aluno.getResponsavelFinanceiroAluno().clear();
		aluno.getResponsavelFinanceiroAluno().add(resp);
	}

	private void setPaiResponsavelFinanceiro(AlunoEntity aluno,	ResponsavelFinanceiroAluno resp) {
		resp.setNome( aluno.getFiliacaoPai().get(0).getNomePai() );
		resp.setCpf( aluno.getFiliacaoPai().get(0).getCpf() );
		resp.setDataNascimento( aluno.getFiliacaoPai().get(0).getDataNascimento() );
		resp.setIdentidade( aluno.getFiliacaoPai().get(0).getIdentidade() );
		resp.setProfissao( aluno.getFiliacaoPai().get(0).getProfissao() );
		resp.setTelefone( aluno.getFiliacaoPai().get(0).getTelefone() );
		resp.setCelular( aluno.getFiliacaoPai().get(0).getCelular() );
		resp.setLocalTrabalho( aluno.getFiliacaoPai().get(0).getLocalTrabalho() );
		resp.setEmail( aluno.getFiliacaoPai().get(0).getEmail() );
		
		setEnderecoResponsavelFinanceiro(aluno, resp);
		aluno.getResponsavelFinanceiroAluno().clear();
		aluno.getResponsavelFinanceiroAluno().add(resp);
	}

	private void setEnderecoResponsavelFinanceiro(AlunoEntity aluno,ResponsavelFinanceiroAluno resp) {
		if( !aluno.getEndereco().isEmpty() ){
			resp.setBairro( aluno.getEndereco().get(0).getBairro() );
			resp.setCep( aluno.getEndereco().get(0).getCep() );
			resp.setCidade( aluno.getEndereco().get(0).getCidade() );
			resp.setComplemento( aluno.getEndereco().get(0).getComplemento() );
			resp.setEndereco( aluno.getEndereco().get(0).getLogradouro() );
			resp.setNumero( aluno.getEndereco().get(0).getNumero() );
			resp.setUf( aluno.getEndereco().get(0).getUf() );
		}
	}
	
	public String adicionarNovoServico(){
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		ServicoAluno servico = new ServicoAlunoEntity();
		servico.setAluno(aluno);
		aluno.getServicoAluno().add(servico);
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	public String excluiServicoAluno(ServicoAlunoEntity servicoAluno) throws PlcException{
		
		if(servicoAluno != null){
			IAppFacade fc = (IAppFacade)getServiceFacade();
			fc.excluirServicoAluno(servicoAluno.getId());
		}
		AlunoEntity aluno = (AlunoEntity)entidadePlc;
		if(aluno.getServicoAluno() != null ){
			for (Iterator iterator = aluno.getServicoAluno().iterator(); iterator.hasNext();) {
				ServicoAlunoEntity servico = (ServicoAlunoEntity) iterator.next();
				
				if(servico.getId().equals( servicoAluno.getId() ) ){
					iterator.remove();
					break;
				}
			}
			
			if( aluno.getServicoAluno().isEmpty() ){
				addNovoServico(aluno);
			}
		}
		else{
			aluno.setServicoAluno( new ArrayList<ServicoAluno>() );
			addNovoServico(aluno);
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	private void addNovoServico(AlunoEntity aluno) {
		ServicoAlunoEntity novoServico = new ServicoAlunoEntity();
		novoServico.setAluno(aluno);
		aluno.getServicoAluno().add( novoServico );
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UploadedFile getFotoAluno() {
		return fotoAluno;
	}

	public void setFotoAluno(UploadedFile fotoAluno) {
		this.fotoAluno = fotoAluno;
	}

	public Calendar getDataBaseContrato() {
		if (dataBaseContrato==null){
			return Calendar.getInstance();
		}else {
			return dataBaseContrato;
		}
	}

	public void setDataBaseContrato(Calendar dataBaseContrato) {
		this.dataBaseContrato = dataBaseContrato;
	}

	public boolean isPopup() {
		return popup;
	}

	public void setPopup(boolean popup) {
		this.popup = popup;
	}

}
