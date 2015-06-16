package com.consisti.sisgesc.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.Banco;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.dominio.CarteiraBanco;
import com.consisti.sisgesc.dominio.Uf;
import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.EnderecoEntity;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity;
import com.consisti.sisgesc.entidade.ServicoAluno;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberProdutoVenda;
import com.consisti.sisgesc.entidade.financeiro.FormaPagamento;
import com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.BancoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.consisti.sisgesc.persistencia.hibernate.EnderecoDAO;
import com.consisti.sisgesc.persistencia.hibernate.FormaPagamentoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ResponsavelFinanceiroAlunoDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class ContaReceberManager extends AppManager {
	
	private EnderecoDAO enderecaoDAO;
	private ResponsavelFinanceiroAlunoDAO responsavelFinanceiroAlunoDAO;
	private BancoDAO bancoDAO;
	private ContaReceberDAO contaReceberDAO;
	private FormaPagamentoDAO formaPagamentoDAO;
	private AlunoDAO alunoDAO;
	
	public ContaReceberManager( EnderecoDAO enderecoDAO, ResponsavelFinanceiroAlunoDAO responsavelFinanceiroAlunoDAO,
			BancoDAO bancoDAO, ContaReceberDAO contaReceberDAO, FormaPagamentoDAO formaPagamentoDAO, AlunoDAO alunoDAO) {
		this.enderecaoDAO = enderecoDAO;
		this.responsavelFinanceiroAlunoDAO = responsavelFinanceiroAlunoDAO;
		this.bancoDAO = bancoDAO;
		this.contaReceberDAO = contaReceberDAO;
		this.formaPagamentoDAO = formaPagamentoDAO;
		this.alunoDAO = alunoDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.modelo.PlcBaseBO#antesPersistencia(com.powerlogic.jcompany.comuns.PlcBaseVO, com.powerlogic.jcompany.comuns.PlcBaseVO, java.lang.String)
	 */
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		ContaReceberEntity contaReceber = (ContaReceberEntity)entidadeAtual;
		//gera nosso numero somente para mensalidade aluno
		if( contaReceber.getContaReceberProdutoVenda() == null || contaReceber.getContaReceberProdutoVenda().isEmpty() ){
			contaReceber.setNumeroDocumento( geraNumeroDocumento( contaReceber ) );
		}
		else{
			for (ContaReceberProdutoVenda produtoVenda : contaReceber.getContaReceberProdutoVenda()) {
				if(produtoVenda.getDataCadastro() == null ){
					produtoVenda.setDataCadastro(new Date());
				}
				
				if( "S".equals( produtoVenda.getIndExcPlc() ) ){
					if( contaReceber.getValorTotal() != null ){
						contaReceber.setValorTotal( contaReceber.getValorTotal().subtract( produtoVenda.getValorTotal() ) );
						contaReceber.setValorDocumento( contaReceber.getValorTotal() );
					}
					else{
						contaReceber.setValorTotal( BigDecimal.ZERO );
						contaReceber.setValorDocumento( contaReceber.getValorTotal() );
					}
				}
			}
		}
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}
	
	/**
	 * @param listaVO
	 * @throws PlcException
	 */
	public void gravarContaReceberPorDemanda( List<PlcBaseVO> listaVO) throws PlcException{
		
		List<FormaPagamento> listFormaPagamento = formaPagamentoDAO.recuperaFormaPagamento();
		FormaPagamentoEntity formaRecebimento = null;
		
		if( listFormaPagamento != null && !listFormaPagamento.isEmpty() ){
			
			for (FormaPagamento formaPagamentoEntity : listFormaPagamento) {
				if( formaPagamentoEntity.getDescricao().contains("BOLETO") ){
					formaRecebimento = (FormaPagamentoEntity) formaPagamentoEntity;
					break;
				}
			}
			if( formaRecebimento == null ){
				formaRecebimento = (FormaPagamentoEntity) listFormaPagamento.get(0);
			}
			
			String numeroDocumento = geraNumeroDocumento(new ContaReceberEntity());
			for (Iterator iterator = listaVO.iterator(); iterator.hasNext();) {
				AlunoEntity aluno = (AlunoEntity) iterator.next();
				ContaReceberEntity contaReceber = new ContaReceberEntity();
				contaReceber.setAluno(aluno);
				contaReceber.setBanco( new BancoEntity( aluno.getIdBanco() ) );
				contaReceber.setBoletoGerado(PlcSimNao.N);
				contaReceber.setDataDocumento( new Date() );
				contaReceber.setDataVencimento( aluno.getDataVencimento() );
				contaReceber.setFormaRecebimento( formaRecebimento );
				contaReceber.setRecebido(PlcSimNao.N);
				contaReceber.setRemessaGerado(PlcSimNao.N);
				contaReceber.setValorDocumento( aluno.getValorTotal() );
				contaReceber.setValorTotal( aluno.getValorTotal() );
				contaReceber.setNumeroDocumento( numeroDocumento );
				inclui(contaReceber);
				//Encrementa o numero documento
				int numeroInt = Integer.parseInt( numeroDocumento ) + 1;
				//retorna com completando com zeros a esquerda
				numeroDocumento = StringUtils.leftPad( String.valueOf( numeroInt ) , 8, "0");
			}
		}
	}
	
	
	/**
	 * Utilizado para gerar numero do documento sequencial
	 * @param contaReceber
	 * @throws PlcException 
	 */
	private String geraNumeroDocumento(ContaReceberEntity contaReceber) throws PlcException {
		
		//Gera a matricula somente se não tiver
		if( contaReceber.getNumeroDocumento() == null ){
			
			String numeroDocumentoRecuperado = contaReceberDAO.recuperarUltimoNumeroDocumento();
				
			if( StringUtils.isNotBlank( numeroDocumentoRecuperado ) ){
				int numeroInt = Integer.parseInt( numeroDocumentoRecuperado ) + 1;
				//retorna com completando com zeros a esquerda
				return StringUtils.leftPad( String.valueOf( numeroInt ) , 8, "0");
			}
			//Quando não existir nenhuma matricula cadastrada começa com 1
			else{
				int numeroInt = 1;
				//retorna com completando com zeros a esquerda
				return StringUtils.leftPad( String.valueOf( numeroInt ) , 8, "0");
			}
		}
		return contaReceber.getNumeroDocumento();
	}
	
	public Boleto gerarBoleta(ContaReceberEntity contaReceber) throws PlcException{
		
		Sacado sacado = new Sacado( contaReceber.getAluno().getNomeAluno() );
		
	    getEnderecoSacado( sacado, contaReceber.getAluno() );
	    SacadorAvalista sacadorAvalista = getSacadorAvalista( contaReceber.getAluno() ); 
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = getContaBancaria( contaReceber );
       
        /*
         * INFORMANDO OS DADOS SOBRE O TÍTULO.
         */
        Titulo titulo = getTitulo( contaReceber, contaBancaria, sacado, sacadorAvalista );
       
        /*
         * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);
       
        setTextoBoleto( boleto );

        return boleto;
	}


	/**
	 * UTilizado para setar os textos informativos do boleto
	 * @param boleto
	 */
	private void setTextoBoleto(Boleto boleto) {
		
		boleto.setLocalPagamento("PREFERENCIALMENTE NAS AGÊNCIAS SANTANDER");
        boleto.setInstrucaoAoSacado("Instruções (Texto de responsabilidade do Cedente): ");
        boleto.setInstrucao1("");
        boleto.setInstrucao2("MULTA DE R$: 11,00 APOS ");
        boleto.setInstrucao3("JUROS DE R$: 1,82 AO DIA");
        boleto.setInstrucao4("");
        boleto.setInstrucao5("NÃO RECEBER APOS 29 DIAS DO VENCIMENTO");
        boleto.setInstrucao6("PROTESTAR APOS 10 DIAS DE VENCIMENTO");
        /*boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");*/
	}

	/**
	 * Retorna as informacoes do titulo
	 * @param contaReceber
	 * @param contaBancaria
	 * @param sacado
	 * @param sacadorAvalista
	 * @return Titulo
	 */
	private Titulo getTitulo( ContaReceberEntity contaReceber, ContaBancaria contaBancaria, 
						Sacado sacado, SacadorAvalista sacadorAvalista ) {
		
		Titulo titulo = new Titulo( contaBancaria, sacado, getCedente() , sacadorAvalista);
		
		titulo.setNumeroDoDocumento( contaReceber.getNumeroDocumento() ); //"123456"
	   // titulo.setNossoNumero( "99345678912" ); //"99345678912"
		if( contaReceber.getNossoNumero() == null ){
			String nossoNumero = getNossoNumero( contaReceber );
			String digito = getDigitoVerificadorModuloOnze( nossoNumero );
		    titulo.setNossoNumero( nossoNumero ); //"99345678912"
		    titulo.setDigitoDoNossoNumero( digito );
		    contaReceber.setNossoNumero( nossoNumero +"-"+ digito );
		}
		else{
			titulo.setNossoNumero( contaReceber.getNossoNumero().split("-")[0]  ); //pega o numero antes do digito
		    titulo.setDigitoDoNossoNumero( contaReceber.getNossoNumero().split("-")[1] );//pega o numero dp do digito
		}
	    titulo.setValor( contaReceber.getValorTotal() );
	    titulo.setDataDoDocumento( contaReceber.getDataDocumento() );
	    titulo.setDataDoVencimento( contaReceber.getDataVencimento() );
	    titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);//boleto
	    titulo.setAceite(Aceite.A);
	    titulo.setDesconto( contaReceber.getDescontoValor() ); //new BigDecimal(0.05)
	    titulo.setDeducao(BigDecimal.ZERO);
	    titulo.setMora(BigDecimal.ZERO);
	    titulo.setAcrecimo(BigDecimal.ZERO);
	    titulo.setValorCobrado(BigDecimal.ZERO);

		return titulo;
	}


	/**
	 * Utilizado apra gerar o nosso numero com o ID do aluno com a data de geracao do boleto
	 * @param contaReceber
	 * @return String
	 */
	private String getNossoNumero(ContaReceberEntity contaReceber) {
		
		SimpleDateFormat format = new SimpleDateFormat( "MMyyyy" );
		
		StringBuffer stb = new StringBuffer();
		stb.append( contaReceber.getAluno().getIdAux() );
		stb.append( format.format( new Date() ) );
		
		return stb.toString();
	}


	/**
	 * Recupera as informacoes da conta bancaria
	 * @param contaReceber
	 * @return ContaBancaria
	 * @throws PlcException
	 */
	private ContaBancaria getContaBancaria(ContaReceberEntity contaReceber) throws PlcException {
		
		BancoEntity banco = bancoDAO.recuperaBancoContaReceber( contaReceber.getBanco().getId() );
		
		if(banco != null ){
			Integer numeroConta = 0;
			String digitoConta = "";
			Integer numeroAgencia = 0;
			String digitoAgencia = "";
			
			if( banco.getNumeroConta().contains("-") ){
				numeroConta = Integer.valueOf( banco.getNumeroConta().split("-")[0].replace(".", "") );
				digitoConta = banco.getNumeroConta().split("-")[1];
			}
			else{
				numeroConta = Integer.valueOf( banco.getNumeroConta().split("-")[0].replace(".", "") );
			}
			
			if( banco.getAgencia().contains("-") ){
				numeroAgencia = Integer.valueOf( banco.getAgencia().split("-")[0].replace(".", "") );
				digitoAgencia = banco.getAgencia().split("-")[1];
			}
			else{
				numeroAgencia = Integer.valueOf( banco.getNumeroConta().split("-")[0].replace(".", "") );
			}
			
			Integer carteiraBanco = getCarteiraBanco( banco );
			
			ContaBancaria contaBancaria = new ContaBancaria( getBancoSuportado( contaReceber ) );
			contaBancaria.setNumeroDaConta( new NumeroDaConta( numeroConta, digitoConta ) );
		    contaBancaria.setCarteira( new Carteira( carteiraBanco ) );
		    contaBancaria.setAgencia( new Agencia( numeroAgencia, digitoAgencia ) );
		    return contaBancaria;
		}
		
		return null;
	}

	/**
	 * Retorna o codigo da carteira como Integer
	 * @param banco
	 * @param carteiraBanco
	 * @return Integer
	 */
	private Integer getCarteiraBanco(BancoEntity banco) {
		
		Integer carteiraBanco = new Integer(0);
		
		if( banco.getCarteiraBanco() != null ){
			if( CarteiraBanco.C101.getCodigo().equals( banco.getCarteiraBanco().getCodigo() ) ){
				carteiraBanco = new Integer( 101 );
			}
			else if ( CarteiraBanco.C102.getCodigo().equals( banco.getCarteiraBanco().getCodigo() ) ){
				carteiraBanco = new Integer( 102 );
			}
			else if( CarteiraBanco.C201.getCodigo().equals( banco.getCarteiraBanco().getCodigo() ) ){
				carteiraBanco = new Integer( 201 );
			}
		}
		return carteiraBanco;
	}


	/**
	 * retorna o banco de acordo com a escolha no contas a receber
	 * @param contaReceber
	 * @return
	 */
	private Banco getBancoSuportado(ContaReceberEntity contaReceber) {
		
		if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_ABN_AMRO_REAL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_ABN_AMRO_REAL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_BRADESCO.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_BRADESCO.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_DO_BRASIL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_DO_BRASIL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_DO_ESTADO_DO_ESPIRITO_SANTO.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_DO_ESTADO_DO_ESPIRITO_SANTO.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_DO_NORDESTE_DO_BRASIL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_DO_NORDESTE_DO_BRASIL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_INTEMEDIUM.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_INTEMEDIUM.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_ITAU.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_ITAU.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_RURAL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_RURAL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_SAFRA.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_SAFRA.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_SANTANDER.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_SANTANDER.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCO_SICREDI.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCO_SICREDI.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.BANCOOB.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.BANCOOB.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.CAIXA_ECONOMICA_FEDERAL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.CAIXA_ECONOMICA_FEDERAL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.HSBC.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.HSBC.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.MERCANTIL_DO_BRASIL.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.MERCANTIL_DO_BRASIL.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.NOSSA_CAIXA.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.NOSSA_CAIXA.create();
		}
		else if( AppConstantesComuns.BANCO_SUPORTADO.UNIBANCO.equals( contaReceber.getBanco().getBancoSuportado().getCodigo() ) ){
			return BancosSuportados.UNIBANCO.create();
		}
		
		return null;
	}


	/**
	 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
	 * @param aluno 
	 * @return SacadorAvalista
	 * @throws PlcException 
	 */
	private SacadorAvalista getSacadorAvalista(Aluno aluno) throws PlcException {
		
		ResponsavelFinanceiroAlunoEntity respFina = responsavelFinanceiroAlunoDAO.recuperaResponsavelFinanceiroAluno( aluno.getId() );
		SacadorAvalista sacadorAvalista = null;
		
		if( respFina != null ){
			
			sacadorAvalista = new SacadorAvalista( respFina.getNome(), respFina.getCpf() ); //"JRimum Enterprise", "00.000.000/0001-91"
			// Informando o endereço do sacador avalista.
			Endereco enderecoSacAval = new Endereco();
			enderecoSacAval.setUF( getUnidadeFederativa( respFina.getUf() ) );
			enderecoSacAval.setLocalidade( respFina.getCidade() ); //"Brasília"
			enderecoSacAval.setCep( new CEP( respFina.getCep() ) );
			enderecoSacAval.setBairro( respFina.getBairro() ); //"Grande Centro"
			enderecoSacAval.setLogradouro( respFina.getEndereco() ); //"Rua Eternamente Principal"
			enderecoSacAval.setNumero( respFina.getNumero() ); //"001"
			sacadorAvalista.addEndereco( enderecoSacAval );
		}
		return sacadorAvalista;
	}

	/**
	 * Monta o endereco do sacado (Endereco do Aluno)
	 * @param sacado
	 * @param aluno
	 * @throws PlcException
	 */
	private void getEnderecoSacado(Sacado sacado, Aluno aluno) throws PlcException {
		
		EnderecoEntity endereco = enderecaoDAO.recuperaEnderecoAluno( aluno.getId() );
		// Informando o endereço do sacado.
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF( getUnidadeFederativa( endereco.getUf() ) ); //UnidadeFederativa.MG
		enderecoSac.setLocalidade( endereco.getCidade() ); //"Natal"
		enderecoSac.setCep( new CEP( endereco.getCep() ) ); //"59064-120"
		enderecoSac.setBairro( endereco.getBairro() ); //"Grande Centro"
		enderecoSac.setLogradouro( endereco.getLogradouro() ); //"Rua poeta dos programas"
		enderecoSac.setNumero( endereco.getNumero() ); //"1"
		sacado.addEndereco( enderecoSac );
	}

	/**
	 * Retorna o unidade federativa
	 * @param uf
	 * @return
	 */
	private UnidadeFederativa getUnidadeFederativa(Uf uf) {
		
		
		if( "AC".equals( uf ) ){
			return UnidadeFederativa.AC;
		}
		else if( "AL".equals( uf ) ){
			return UnidadeFederativa.AL;
		}
		else if( "AP".equals( uf ) ){
			return UnidadeFederativa.AP;
		}
		else if( "AM".equals( uf ) ){
			return UnidadeFederativa.AM;
		}
		else if( "BA".equals( uf ) ){
			return UnidadeFederativa.BA;
		}
		else if( "CE".equals( uf ) ){
			return UnidadeFederativa.CE;
		}
		else if( "DF".equals( uf ) ){
			return UnidadeFederativa.DF;
		}
		else if( "ES".equals( uf ) ){
			return UnidadeFederativa.ES;
		}
		else if( "GO".equals( uf ) ){
			return UnidadeFederativa.GO;
		}
		else if( "MA".equals( uf ) ){
			return UnidadeFederativa.MA;
		}
		else if( "MT".equals( uf ) ){
			return UnidadeFederativa.MT;
		}
		else if( "MS".equals( uf ) ){
			return UnidadeFederativa.MS;
		}
		else if( "MG".equals( uf ) ){
			return UnidadeFederativa.MG;
		}
		else if( "PA".equals( uf ) ){
			return UnidadeFederativa.PA;
		}
		else if( "PB".equals( uf ) ){
			return UnidadeFederativa.PB;
		}
		else if( "PR".equals( uf ) ){
			return UnidadeFederativa.PR;
		}
		else if( "PE".equals( uf ) ){
			return UnidadeFederativa.PE;
		}
		else if( "PI".equals( uf ) ){
			return UnidadeFederativa.PI;
		}
		else if( "RJ".equals( uf ) ){
			return UnidadeFederativa.RJ;
		}
		else if( "RN".equals( uf ) ){
			return UnidadeFederativa.RN;
		}
		else if( "RS".equals( uf ) ){
			return UnidadeFederativa.RS;
		}
		else if( "RO".equals( uf ) ){
			return UnidadeFederativa.RO;
		}
		else if( "RR".equals( uf ) ){
			return UnidadeFederativa.RR;
		}
		else if( "SC".equals( uf ) ){
			return UnidadeFederativa.SC;
		}
		else if( "SP".equals( uf ) ){
			return UnidadeFederativa.SP;
		}
		else if( "SE".equals( uf ) ){
			return UnidadeFederativa.SE;
		}
		else if( "TO".equals( uf ) ){
			return UnidadeFederativa.TO;
		}
		
		return null;
	}
	
	/**
	 * Multiplicar cada dígito a partir da direita, pelo seu correspondente na seqüência de 2 a 9. 
 	 * - Acumular os resultados da multiplicação. 
	 * - Dividir o resultado por 11. 
	 * - Subtrair o resto de 11, tomando-se como dígito de controle o algarismo das unidades do número
	 *   encontrado. 
	 *   Assim sendo, se o resto for 1, o dígito será 0(zero). 
	 *   Se o resto for 0 (zero) o  dígito será 0 (zero). 
	 *   Se o resto for 10 (dez) o digito será 1 (um) 
	 *   Exemplo1 -Número fornecido= 3147578 
	 *   Exemplo2 -Número fornecido= 4870184                
	 *   8 x 2 = 16       4 x 2 = 8
	 *   7 x 3 = 21       8 x 3 = 24
	 *   5 x 4 = 20       1 x 4 = 4 
	 *   7 x 5 = 35       0 x 5 = 0 
	 *   4 x 6 = 24       7 x 6 = 42   
	 *   1 x 7 =  7       8 x 7 = 56 
	 *   3 x 8 = 24       4 x 8 = 32
	 *   
	 *   147 : 11 = 13  resto = 4
	 *   166 : 11  = 15  resto = 1 
	 *   Dígito = 11 - 4 = 7                                              
	 *   Dígito = 11 -1 = 10  dígito = 0           
	 *   
	 *   Número com Dígito (exemplo 1) = 31475787        
	 *   Número com Dígito (exemplo 2) = 48701840 
	 * @param num
	 * @return
	 */
	private String getDigitoVerificadorModuloOnze( String num ) {
		int soma = 0;
		int mult = 2;
		/* Inverte o numero e calcula o somatorio da multiplicação
		 * de cada caractere pelo multiplicador (2 a 9).
		 */
		num = StringUtils.reverse(num);
		for (int i = 0; i <= num.length() - 1; i++) {
			int n = CharUtils.toIntValue(num.charAt(i));
			soma += n * mult;
			if ( mult == 9 ) {
				mult = 2;
			} else {
				mult++;
			}
		}
		int resto = soma % 11;
		if (String.valueOf(resto).equals("0") || String.valueOf(resto).equals("1")) {
			return "0";
		}
		return String.valueOf(11 - resto);
	}
	
	/**
	 * INFORMANDO DADOS SOBRE O CEDENTE.
	 * @return cedente
	 */
	public Cedente getCedente() {
	       Cedente cedente = new Cedente("INSTITUTO EDUCACIONAL FACULDADE DA CRIANCA", "07.797.977/0001-20");
		return cedente;
	}
	
	public ContaReceberEntity recuperaValorAlunoSetContaReceber(Long idAluno) throws PlcException{
		
		ContaReceberEntity contaReceber = new ContaReceberEntity();
		
		if( idAluno != null){
			AlunoEntity aluno = alunoDAO.recuperaValorMensalidadeAluno( idAluno );
			if(aluno != null){
				contaReceber.setValorDocumento( aluno.getValorTotalMensalidade() );
				
				if( aluno.getServicoAluno() != null && !aluno.getServicoAluno().isEmpty() ){
					for (ServicoAluno servicoAluno : aluno.getServicoAluno()) {
						contaReceber.setValorDocumento( contaReceber.getValorDocumento().add( servicoAluno.getServico().getValorServico() ) );
					}
				}
			}
		}
		return contaReceber;
	}

}
