package com.consisti.sisgesc.controle.jsf.financeiro;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.entidade.Endereco;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAluno;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;

public class ArquivoRemessa {
	
	static final String USUARIO = "GRANDE EMPRESA LTD";
	static final Integer FILIALMATRIZ = 5000;
	
	static int numeroSequencial;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
	
	static void exportarRemessa(ContaReceberEntity conta, IAppFacade fc, HttpServletResponse response) throws IOException, PlcException{
		
		//File layout = new File(ClassLoaders.getResource("LayoutContabilidadeToy.txg.xml").getFile());
		File layout = new File(ClassLoaders.getResource("LayoutSantaderCNAB400EnvioNovo.txg.xml").getFile());
		
		FlatFile<Record> ff = Texgit.createFlatFile(layout);
		//Cabecalho do arquivo
		ff.addRecord( createHeader( ff ) );
		//TODO percorrer a lista para colocar todas boletas
		ff.addRecord( transacaoTitulo( ff, conta, fc ) );
		
		//TODO fim do arquivo
		ff.addRecord(trailler(ff));
		
		sendStringWithName( ff.write(), "ArquivoRemessa.txt", response );
		
		//FileUtils.writeLines(new File("C:\\Users\\joaopaulo\\ArquivoRemessa.txt"),ff.write(), "\r\n");
		
	}
	
	private static Record trailler(FlatFile<Record> ff) {
		
		Record trailler = ff.createRecord("Trailler");
		
		trailler.setValue( "CodigoDoRegistro", "9" ); // value="9" length="1" position="1" />
		//TODO implementar quantidade
		trailler.setValue( "QuantidadeTitulos", "20" ); // length="6" padding="ZERO_LEFT" />
		//implementar quantidade
		trailler.setValue( "ValorTotalTitulos", "100.00" ); // length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />		
		trailler.setValue( "Branco" , "0" ); //length="374" padding="ZERO_LEFT" />
		trailler.setValue( "NumeroSequencialRegistro", "123456" ); // length="6" padding="ZERO_LEFT"/>	
		
		return trailler;
	}

	static Record createHeader(FlatFile<Record> ff){
		
		Record header = ff.createRecord("Header");
		
		header.setValue("CodigoDoRegistro", 1 ); //length="1" position="1" Código do registro = 0 
		header.setValue("IdentificacaoRemessa", 1 ); // length="1" value="1" ); Código da remessa = 1 
		header.setValue("LiteralRemessa", "REMESSA" ); // value="REMESSA" length="7" ); Literal de transmissão = REMESSA 
		header.setValue("CodigoServico", "01" ); // length="2" value="01" padding="ZERO_LEFT" ); Código do serviço = 01
		header.setValue("LiteralServico", "COBRANCA" ); // value="COBRANCA" length="15" ); Literal de serviço = COBRANÇA 
		header.setValue("CodigoTransmissao", "1234" ); //length="20" padding="ZERO_LEFT" ); Código de Transmissão (nota 1) 
		header.setValue("NomeEmpresa", AppConstantesComuns.DADOS_EMPRESA.NOME_EMPRESA ); // length="30" Nome do cedente
		header.setValue("CodigoBanco", "033" ); //length="3" value="353" padding="ZERO_LEFT"); Código do Banco = 033 
		header.setValue("NomeBanco", "SANTANDER" ); // length="15" value="SANTANDER" ); Nome do Banco = SANTANDER 
		header.setValue("DataGravacao", sdf.format( new Date() ) ); //length="6" type="DATE" format="DATE_DDMMYY" ); Data de Gravação 
		header.setValue("Zeros" , "0" ); //length="16" padding="ZERO_LEFT" ); Zeros 
		header.setValue("Brancos" , "" ); //length="40" );
		header.setValue("Brancos" , "" ); //length="47" );
		header.setValue("Brancos" , "" ); //length="47" );
		header.setValue("Brancos" , "" ); //length="47" );
		header.setValue("Brancos" , "" ); //length="47" );
		header.setValue("Brancos" , "" ); //length="47" );
		header.setValue("NumeroSequencialRemessa", "000" ); // length="3" padding="ZERO_LEFT" ); Número da versão da remessa opcional, se informada, será controlada pelo sistema (opcional = 000) 
		header.setValue("NumeroSequencialRegistro", "1"); //length="6" padding="ZERO_LEFT" ); Número seqüencial do registro no arquivo = 000001
		
		return header;
	}
	
	 static Record transacaoTitulo(FlatFile<Record> ff, ContaReceberEntity conta, IAppFacade fc) throws PlcException{
		 
		conta.getAluno().setEndereco( new ArrayList<Endereco>() );
		conta.getAluno().getEndereco().add( fc.recuperaEnderecoAluno( conta.getAluno().getId() ) );
		
		conta.getAluno().setResponsavelFinanceiroAluno( new ArrayList<ResponsavelFinanceiroAluno>() );
		conta.getAluno().getResponsavelFinanceiroAluno().add( fc.recuperaResponsavelFinanceiroAluno( conta.getAluno().getId() ) );
		
		Record transacao = ff.createRecord("TransacaoTitulo");
		transacao.setValue( "CodigoRegistro" , "1" ); //value="1" length="1" position="1" /> Código do registro = 1  
		transacao.setValue( "InscricaoEmpresa", "02" ); // length="2"  padding="ZERO_LEFT" />  Tipo de inscrição do cedente: 01 = CPF 02 = CGC
		transacao.setValue( "NumeroInscricao", AppConstantesComuns.DADOS_EMPRESA.CNPJ ); //length="14" padding="ZERO_LEFT" /> CGC ou CPF do cedente
		transacao.setValue( "CodigoTransmissao", "1234" ); // length="4"  padding="ZERO_LEFT" /> Código de Transmissão (nota 1)//TODO pegar com o banco 
		transacao.setValue( "NumeroControle", conta.getId() ); // length="25" />
		transacao.setValue( "NossoNumero", conta.getNossoNumero().replace("-", "") ); // length="8" />
		transacao.setValue( "DataSegundoDesconto", "180414" ); //length="6" type="DATE" format="DATE_DDMMYY" />
		transacao.setValue( "Branco", "" ); // length="1" />
		transacao.setValue( "Multa", "0" ); //length="1" />
		transacao.setValue( "PercentualMulta", conta.getJuroPercentual().toString() ); // length="4" padding="ZERO_LEFT"/>
		transacao.setValue( "UnidadeValorMoeda", "00" ); // length="2" padding="ZERO_LEFT" />
		transacao.setValue( "ValorTituloOutraUnidade", conta.getValorTotal() ); // type="BIGDECIMAL" format="DECIMAL_DD" length="13" padding="ZERO_LEFT" />					
		transacao.setValue( "Branco", "" ); // length="4" />
		transacao.setValue( "DataCobrancaMulta", "070414" ); //length="6" type="DATE" format="DATE_DDMMYY"/>
		transacao.setValue( "CodigoCarteira", "1" ); // length="1" /> 1 = ELETRÔNICA COM REGISTRO 3 = CAUCIONADA ELETRÔNICA 4 = COBRANÇA SEM REGISTRO 5 = RÁPIDA COM REGISTRO (BLOQUETE EMITIDO PELO CLIENTE) 6 = CAUCIONADA RAPIDA  7 = DESCONTADA ELETRÔNICA 
		transacao.setValue( "CodigoOcorrencia", "01" ); // length="2" type="INTEGER" value="1" padding="ZERO_LEFT" /> 01 = ENTRADA DE TÍTULO 02 = BAIXA DE TÍTULO 04 = CONCESSÃO DE ABATIMENTO 05 = CANCELAMENTO ABATIMENTO 06 = PRORROGAÇÃO DE VENCIMENTO 07 = ALT . NÚMERO CONT .CEDENTE 08 = ALTERAÇÃO DO SEU NÚMERO    09 = PROTESTAR    18 = SUSTAR PROTESTO 
		transacao.setValue( "SeuNumero", conta.getNumeroDocumento() ); // length="10" />
		transacao.setValue( "DataVencimentoTitulo", sdf.format( conta.getDataVencimento() ) ); // length="6" />
		transacao.setValue( "ValorTitulo", conta.getValorTotal() ); //length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />
		transacao.setValue( "NumeroBanco", "033" ); //length="3" padding="ZERO_LEFT" value="353" />
		transacao.setValue( "CodigoAgencia", "005" ); //length="5" padding="ZERO_LEFT" />
		transacao.setValue( "EspecieDocumento", "01" ); // length="2" padding="ZERO_LEFT" /> 01 = DUPLICATA 02 = NOTA PROMISSÓRIA 03 = APÓLICE / NOTA DE SEGURO 05 = RECIBO 06 = DUPLICATA DE SERVIÇO 07 = LETRA DE CAMBIO 
		transacao.setValue( "Aceite", "N" ); //length="1" />
		transacao.setValue( "DataEmissao", sdf.format( conta.getDataDocumento() ) ); // length="6" type="DATE" format="DATE_DDMMYY" />
		transacao.setValue( "PrimeiraInstrucao", "05" ); //length="2" value="05" padding="ZERO_LEFT" />
		transacao.setValue( "SegundaInstrucao", "10" ); //length="2" value="10" padding="ZERO_LEFT" />
		transacao.setValue( "ValorJurosMora", "" ); //length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />
		transacao.setValue( "DataDesconto", "" ); //length="6" padding="ZERO_LEFT" />
		transacao.setValue( "ValorDesconto", "" ); //length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />
		transacao.setValue( "ValorIOF", "0" ); //length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />
		transacao.setValue( "ValorAbatimento", "0" ); //length="13" type="BIGDECIMAL" format="DECIMAL_DD" padding="ZERO_LEFT" />
		transacao.setValue( "TipoSacado", "01" ); //length="2" padding="ZERO_LEFT" /> Tipo de inscrição do sacado:  01 = CPF   02 = CGC 
		transacao.setValue( "DocumentoSacado", conta.getAluno().getResponsavelFinanceiroAluno().get(0).getCpf().replace(".", "").replace("-", "") ); // length="14" padding="ZERO_LEFT" />
		transacao.setValue( "NomeSacado", conta.getAluno().getNomeAluno() ); //length="40" />
		transacao.setValue( "EnderecoSacado", conta.getAluno().getEndereco().get(0).getLogradouro() ); //length="40" />
		transacao.setValue( "BairroSacado", conta.getAluno().getEndereco().get(0).getBairro() ); //length="12" />
		transacao.setValue( "CepSacado", conta.getAluno().getEndereco().get(0).getCep().replace(".", "").replace("-", "") ); //length="8" padding="ZERO_LEFT" />
		transacao.setValue( "CidadeSacado", conta.getAluno().getEndereco().get(0).getCidade() ); //length="15" />
		transacao.setValue( "EstadoSacado", conta.getAluno().getEndereco().get(0).getUf() ); //length="2" />
		transacao.setValue( "SacadorAvalista", conta.getAluno().getResponsavelFinanceiroAluno().get(0).getNome() ); //length="30" />
		transacao.setValue( "Branco", "" ); // length="1" />
		transacao.setValue( "IdentificadoComplemento", "1" ); //length="1" />
		transacao.setValue( "Complemento", "01" ); // length="2" padding="ZERO_LEFT" />
		transacao.setValue( "Branco", "" ); // length="6" />
		transacao.setValue( "DiasProtesto", "06" ); // length="2" padding="ZERO_LEFT" value="06" />
		transacao.setValue( "Branco", "" ); // length="1" />
		transacao.setValue( "NumeroSequencialRegistro", "123456" ); // length="6" padding="ZERO_LEFT"/>
		
		return transacao;
	}
	 
	 /** 
	     * Envia uma String no formato de array de bytes para o response, a fim de ser 
	     * feito o download. 
	     * @param list = arquivo que sera aberto 
	     * @param name = nome dado ao arquivo 
	     * @param response = HttpServletResponse 
	     */  
	    public static void sendStringWithName(List<String> list, String name, HttpServletResponse response) throws IOException {  
	        
	    	if( list != null ){
	    		StringBuffer texto = new StringBuffer();
	    		for (int i = 0; i < list.size(); i++) {
					texto.append( list.get(i) ).append("\n");
					
				}
		    	byte[] report = texto.toString().getBytes();  
		        response.setContentType("application/save");  
		        response.setContentLength(report.length);  
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + name+"\"");  
		        response.addHeader("Pragma", "no-cache");  
		        response.setDateHeader("Expires", 0);
		        
		        ServletOutputStream outputStream = response.getOutputStream();
		        outputStream.write(report, 0, report.length);  
		        outputStream.flush();  
		        outputStream.close();  
	    		response.flushBuffer();
	    	}
	    }  
	
}
