package com.consisti.sisgesc.controle.jsf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.entidade.AditivoEntity;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.AppBaseEntity;
import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

@SuppressWarnings("serial")
public class RelatorioActionPlc extends AppAction {

	protected String somaValorTotalMensalidadesFormatado(List<AlunoEntity> alunos){
		
		BigDecimal total = new BigDecimal(0);
		for (AlunoEntity alunoEntity : alunos) {
			total = total.add(alunoEntity.getValorMensalidadeAluno());
		}
		
		return NumberFormat.getCurrencyInstance().format( total );
		
	}
	
    @Override 
    protected void trataBotoesConformeLogicaApos() throws PlcException{
        
        super.trataBotoesConformeLogicaApos();

    }
	    
    /**
     * Retorna o nome do mes atual
     * @return
     */
    protected HashMap<String, String> mesAtual(){
    	
    	String meses[] = {"Janeiro", "Fevereiro", 
	             "Março", "Abril", "Maio", "Junho", 
	             "Julho", "Agosto", "Setembro", "Outubro",
		      "Novembro", "Dezembro"};
		   
		HashMap<String, String> maps = new HashMap<String, String>();
		maps.put("mesAtual", meses[new Date().getMonth()]);
		maps.put("clausula", "dasdasdas");
    
		return maps;
		
    }
    
    /**
     * eventos destinados a especializações nos descendentes.
     * 
     * Vai passar um objeto para o jasper e partir do mesmo montar 
     * o relatorio. 
     * 
     * @param nomeRelatorio
     * @param entidade
     * @param tipoRelatorio
     * @param parametros
     * @throws PlcException
     */
     protected void geraRelatorio(String nomeRelatorio, AppBaseEntity entidade, HashMap parametros) throws PlcException {
         
         //declarando objetos a serem usados no relatorio
         List<AppBaseEntity> lista = new ArrayList<AppBaseEntity>();
          
         FacesContext facesContext = FacesContext.getCurrentInstance();  
         ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();  
	     
         //parametros essenciais para o relatorio 
         parametros.put("DIR_IMAGENS", servletContext.getRealPath(File.separator+"plc"+File.separator+AppConstantesComuns.RELATORIO.PASTA_IMAGENS));
         parametros.put("SUBREPORT_DIR", servletContext.getRealPath(File.separator+"plc"+File.separator+"rel"));
         
         lista.add(entidade);

         InputStream jasperReport = contextHelperPlc.getRequest().getSession().getServletContext().getResourceAsStream(File.separator+"plc"+File.separator+AppConstantesComuns.RELATORIO.PASTA_RELATORIOS+File.separator+nomeRelatorio);
         
         if (jasperReport==null){
             throw new PlcException("erro.gerar.relatorio.plc");
         }
	          
         byte[] relatorio = montaRelatorioPlc(lista, parametros, jasperReport, nomeRelatorio); 
          
         if (contextHelperPlc.getRequest().getSession().getAttribute("gravaContrato")!=null && entidadePlc instanceof ContratoEntity){
        	 contextHelperPlc.getRequest().getSession().removeAttribute("gravaContrato");
        	  gravaContrato(relatorio);
         }
         
         if (contextHelperPlc.getRequest().getSession().getAttribute("gravarAditivo")!=null){
        	 contextHelperPlc.getRequest().getSession().removeAttribute("gravarAditivo");
        	  addAditivoContratoPlc(relatorio);
        	  relatorio = null;
         }
         
         if (relatorio!=null){
             imprimir(relatorio, nomeRelatorio);
         }

         
     }
     
     /**
      * Gera o carne do evento
     * @param nomeRelatorio
     * @param listContaReceberEvento
     * @param parametros
     * @throws PlcException
     */
    public void geraListaRelatorio(String nomeRelatorio, List<ContaReceberEntity> listContaReceberEvento, HashMap parametros) throws PlcException {
 		
         FacesContext facesContext = FacesContext.getCurrentInstance();  
         ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();  
 	     
         //parametros essenciais para o relatorio 
         parametros.put("DIR_IMAGENS", servletContext.getRealPath(File.separator+"plc"+File.separator+AppConstantesComuns.RELATORIO.PASTA_IMAGENS));
         parametros.put("SUBREPORT_DIR", servletContext.getRealPath(File.separator+"plc"+File.separator+"rel"));
         
         InputStream jasperReport = contextHelperPlc.getRequest().getSession().getServletContext().getResourceAsStream(File.separator+"plc"+File.separator+AppConstantesComuns.RELATORIO.PASTA_RELATORIOS+File.separator+nomeRelatorio);
         
         if (jasperReport==null){
             throw new PlcException("erro.gerar.relatorio.plc");
         }
 	          
         byte[] relatorio = montaRelatorioPlc(listContaReceberEvento, parametros, jasperReport, nomeRelatorio); 
          
         if (contextHelperPlc.getRequest().getSession().getAttribute("gravaContrato")!=null && entidadePlc instanceof ContratoEntity){
        	 contextHelperPlc.getRequest().getSession().removeAttribute("gravaContrato");
        	  gravaContrato(relatorio);
         }
         
         if (contextHelperPlc.getRequest().getSession().getAttribute("gravarAditivo")!=null){
        	 contextHelperPlc.getRequest().getSession().removeAttribute("gravarAditivo");
        	  addAditivoContratoPlc(relatorio);
        	  relatorio = null;
         }
         
         if (relatorio!=null){
             imprimir(relatorio, nomeRelatorio);
         }
         
 		
 	}
	    
     /**
      * Vai gravar o contrato no banco,
      * o aluno pode ter somente um contrato por ano letivo
     * @throws PlcException 
      */
     private void gravaContrato(byte[] contrato) throws PlcException {
		
		ContratoEntity contratoAluno = new ContratoEntity();
		contratoAluno.setAluno( ((ContratoEntity)entidadePlc).getAluno() );
		contratoAluno.setAnoContrato( new Long(  getAnoContrato() ) );
		contratoAluno.setContrato(contrato);
		contratoAluno.setStatusContrato( AtivoInativo.A );
		contratoAluno.setDataInicioContrato( (Date)contextHelperPlc.getSessionAttribute("dataInicio") );
		contratoAluno.setDataFimContrato( (Date)contextHelperPlc.getSessionAttribute("dataFim") );
		contextHelperPlc.getRequest().getSession().removeAttribute("dataInicio");
		contextHelperPlc.getRequest().getSession().removeAttribute("dataFim");
		IAppFacade facade = (IAppFacade) getServiceFacade();
    	facade.gravaObjeto(getContext(), contratoAluno, null);
    	
	}

	/**
	 * Retorna o Ano do contrato pegando como base a data de inicio do contrato
	 * @return int
	 */
	public int getAnoContrato() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( ((ContratoEntity)entidadePlc).getDataInicioContrato() );
		return cal.get( Calendar.YEAR );
	}
     
	/**
	  * Vai gravar um aditivo do contrato do aluno,
	  * o aluno pode ter somente um contrato por ano letivo
	  * @throws PlcException 
	  */
	 private void addAditivoContratoPlc(byte[] aditivo) throws PlcException {
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		Long idContrato = Long.parseLong(contextHelperPlc.getRequest().getSession().getAttribute("idContrato").toString());
		contextHelperPlc.getRequest().getSession().removeAttribute("idContrato");
		AditivoEntity aditivoContrato = new AditivoEntity();
		aditivoContrato.setAditivo(aditivo);
		aditivoContrato.setDataCriacao(new Date());
		ContratoEntity contrato = (ContratoEntity) getServiceFacade().recuperaObjeto(getContext(), ContratoEntity.class, idContrato)[0];
		aditivoContrato.setContrato(contrato);
		facade.gravaObjeto(getContext(), aditivoContrato, null);
		contextHelperPlc.setSessionAttribute("contratoAtualizado", PlcSimNao.S);
		
	}
 
	/**
      * Gera o relatorio a partir da entidade recebida e monta o relatorio,
      * em seguida vai exportar para o formato desejado
      * @param listaObjetos
      * @param parameters
      * @param jasperReport
      * @param tipoRelatorio
      * @param nomeRelatorio
      * @return
      * @throws PlcException
      */
     private byte[] montaRelatorioPlc(List listaObjetos, Map parameters, InputStream jasperReport, String nomeRelatorio) throws PlcException {
 
         try {
                 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);
                 if(jasperReport == null){
                         throw new PlcException("msg.erro.relatorio.jasper.nulo");
                 }
                 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
                 return JasperExportManager.exportReportToPdf(jasperPrint);
                 
         } catch (Exception e) {
        	 log.info(e);
        	 throw new PlcException("erro.gerar.relatorio.plc");
         }
         
     }
	     
     /**
      * Imprime no formator PDF
      * @param relatorio
      * @throws PlcException
      */
     protected void imprimir(byte[] relatorio, String nomeRelatorio) throws PlcException{
         
         
    	 try {
    		 FacesContext context = FacesContext.getCurrentInstance();
    		 
        	 HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        	 response.setContentType("application/pdf");  
        	 response.setContentLength(relatorio.length);  
        	 response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeRelatorio.replace("jasper", "pdf")+ "\"");

             ServletOutputStream saidaDoDownload;
				saidaDoDownload = response.getOutputStream();
             
             try {  
            	 saidaDoDownload.write(relatorio, 0, relatorio.length);
            	 //context.getApplication().getStateManager().saveView(context);  
            	 context.renderResponse();
                 context.responseComplete();    
             
	         } catch (IOException e) {
	                 throw new PlcException(e);
	         }
	         finally{
	        	 saidaDoDownload.flush();  
            	 saidaDoDownload.close(); 
            	 response.getOutputStream().flush();
            	 response.getOutputStream().close();
            	 //response.flushBuffer();
	         }
    	 } catch (IOException e1) {
    		 // TODO Auto-generated catch block
    		 e1.printStackTrace();
    	 }
     }
}
