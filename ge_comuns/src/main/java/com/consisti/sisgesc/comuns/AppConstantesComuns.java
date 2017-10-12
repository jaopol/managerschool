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
package com.consisti.sisgesc.comuns;

import com.powerlogic.jcompany.comuns.PlcConstantesComuns;

/**
 * Implementar aqui constantes específicas da Aplicação
 * 
 */
public interface AppConstantesComuns extends PlcConstantesComuns {
    
    String NOME_CONSTANTE = "valorConstante";
    
    int DIAS_PARA_GERAR_HISTORICO = 365;
    
    interface RELATORIO{
    	
    	String REL_MENSALIDADES_TURMA = "mensalidades_turma.jasper";
    	String PASTA_IMAGENS = "midia";
    	String PASTA_RELATORIOS = "rel";
    	String REL_CDU001 = "ficha_matricula.jasper";
    	String REL_CONTRATO = "contrato.jasper";
    	String REL_FICHA_SAUDE = "ficha_informacao_saude.jasper";
    	String BOLETIM = "boletim.jasper";
    	String ADITIVO = "aditivo_contrato.jasper";
    	String SERVICO_ALUNO = "servico_aluno.jasper";
    	String ESTOQUE_SINTETICO = "estoque_sintetico.jasper";
    	String ESTOQUE_ANALITICO = "estoque_analitico.jasper";
    	String TERMO_MATRICULA = "termo_matricula.jasper";
    	String REL_MOVIMENTO_DIA = "movimento_dia.jasper";
    	String CARNE_EVENTO_ALUNO = "carne_evento_aluno.jasper";
    	
    }
    
	public interface CASOUSO{
		
       	public static final String UC011 = "UC011";
       	public static final String CDU001 = "CDU001";
       	public static final String CDUF006 = "CDUF006"; //CONTA RECEBER
       	
	}
	
	interface TIPO_DOCUMENTO{
		String BOLETIM = "BOLETIM";
		String REGISTRO_NOTAS = "REGISTRO DE NOTAS";
	}
    
	interface BANCO_SUPORTADO{
		
		String BANCO_DO_BRASIL = "B001";
		String BANCO_DO_NORDESTE_DO_BRASIL = "B004";		
		String BANCO_DO_ESTADO_DO_ESPIRITO_SANTO = "B021";
		String BANCO_SANTANDER = "B033";
		String BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL = "B041";
		String BANCO_INTEMEDIUM = "B077";	
		String CAIXA_ECONOMICA_FEDERAL = "B104";
		String NOSSA_CAIXA = "B151";	
		String BANCO_BRADESCO = "B237";
		String BANCO_ITAU = "B341";
		String BANCO_ABN_AMRO_REAL = "B356";
		String MERCANTIL_DO_BRASIL = "B389";
		String HSBC = "B399";
		String UNIBANCO = "B409";
		String BANCO_SAFRA = "B422";
		String BANCO_RURAL = "B453";
		String BANCO_SICREDI = "B748";
		String BANCOOB = "B756";

	}
	
	interface DADOS_EMPRESA{
		String NOME_EMPRESA = "FACULDADE DA CRIANÇA LTDA";
		String CNPJ = "07797977000120";
	}
}