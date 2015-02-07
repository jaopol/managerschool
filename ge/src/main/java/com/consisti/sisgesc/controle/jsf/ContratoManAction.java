package com.consisti.sisgesc.controle.jsf;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.helper.CurrencyWriter;
import com.consisti.sisgesc.dominio.AtivoInativo;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;


/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContratoManAction extends AditivoAction  {
	
	private Long idContrato;
	private String nomeAluno;
	private String textoAdtivo;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		//contextHelperPlc.getRequest().setAttribute("exibeIncluirPlc", PlcSimNao.N);
		contextHelperPlc.getRequest().setAttribute("exibeExcluirPlc", PlcSimNao.N);
		contextHelperPlc.getRequest().setAttribute("exibeGravarPlc", PlcSimNao.N);
		serviceVisaoPlc.naoExibirAbaTabFolderPos(contextHelperPlc.getRequest(), 1);
		
		if( entidadePlc.getId() == null ){
			contextHelperPlc.getRequest().setAttribute("exibeImprimirContratoAluno", "S");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirAditivoContrato", "N");
		}
		else if( AtivoInativo.A.equals( ((ContratoEntity)entidadePlc).getStatusContrato() ) ){
			contextHelperPlc.getRequest().setAttribute("exibeImprimirAditivoContrato", "S");
			contextHelperPlc.getRequest().setAttribute("exibeImprimirContratoAluno", "N");
		}
		
	}
	
	public void verContrato() throws PlcException{
		
		if (entidadePlc!=null){
			ContratoEntity contrato = (ContratoEntity) entidadePlc;
			imprimir(contrato.getContrato(), AppConstantesComuns.RELATORIO.REL_CONTRATO);
		}
	}
	
	
	public void geraRelatorioContratoPlc() throws PlcException {

		ContratoEntity contrato = (ContratoEntity)entidadePlc;
		
		if( contrato.getDataInicioContrato() == null ){
			throw new PlcException("msg.info.dataInicoContrato.obrigatorio");
		}
		if( contrato.getDataFimContrato() == null ){
			throw new PlcException("msg.info.dataInicoContrato.obrigatorio");
		}
		
		if( contrato.getDataFimContrato().before( contrato.getDataInicioContrato() ) ){
			throw new PlcException("msg.info.dataInicoContrato.maior.dataFimContrato");
		}
		   
		montarGerarContrato( contrato );
		
	}

	/**
	 * @param contrato
	 * @throws PlcException
	 */
	private void montarGerarContrato(ContratoEntity contrato)
			throws PlcException {
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		AlunoEntity aluno = facade.recuperarAlunoVO( contrato.getAluno().getId() ) ;
		setIdContrato( facade.temContratoVigente( aluno.getId(), getAnoContrato() ) );
		
		if (getIdContrato()==null){
			
			contextHelperPlc.getRequest().getSession().setAttribute("gravaContrato", PlcSimNao.S);
			HashMap<String, String> maps = new HashMap<String, String>();
			maps.putAll(mesAtual());
			
			BigDecimal valorTotalServicos = new BigDecimal(0);
			if( aluno.getServicoAluno() != null && !aluno.getServicoAluno().isEmpty() ){
				valorTotalServicos = facade.getValorTotalServicosAluno( aluno.getServicoAluno() );
			}
			
			BigDecimal valorTotalMensalidade = calculaMensalidadeAluno( aluno, contrato );
			
			BigDecimal valor = valorTotalMensalidade.add( valorTotalServicos.multiply( new BigDecimal( getMesesFaltantesParaFimContrato( contrato ) ) ).add( aluno.getValorMatricula() ) );
			BigDecimal valorDeducao = valorTotalMensalidade.add( aluno.getValorMatricula() );
			
			CurrencyWriter cw = new CurrencyWriter();  
			String extenso = cw.write(valor);
			String valorDeducaoExtenso = cw.write(valorDeducao);
			String moeda = NumberFormat.getCurrencyInstance().format(valor);
			String valorDeducaoStr = NumberFormat.getCurrencyInstance().format(valorDeducao);
			maps.put("valor", moeda+" ("+extenso+")");
			maps.put("valorDeducao", valorDeducaoStr+" ("+valorDeducaoExtenso+")");
			
			int mes = getMesesFaltantesParaFimContrato(contrato);
			maps.put("mesesContrato", String.valueOf( mes ) );
			maps.put("mesesContratoExtenso", String.valueOf( mes )+ "("+mesExtenso(mes)+")" );
			maps.put("valoresContrato", montaValoresContrato( contrato, aluno, valorTotalServicos));
			maps.put("dataBaseContrato", getAnoContrato() +"");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			maps.put("dataFinalContrato", sdf.format( contrato.getDataFimContrato() ) );
			maps.put("dataInicioContrato", sdf.format( contrato.getDataInicioContrato() ) );
			String valorTotalMensalidadeMoeda = NumberFormat.getCurrencyInstance().format( aluno.getValorTotalMensalidade() );
			String valorTotalMensalidadeExtenso = cw.write( aluno.getValorTotalMensalidade() );
			maps.put("valorTotalMensalidadeMoeda", valorTotalMensalidadeMoeda+" ("+valorTotalMensalidadeExtenso+")");
			String valorMatriculaMoeda = NumberFormat.getCurrencyInstance().format(aluno.getValorMatricula());
			maps.put("valorMatriculaMoeda", valorMatriculaMoeda);
			maps.put("respAluno", "CPF: "+aluno.getResponsavelFinanceiroAluno().get(0).getCpf());
			contextHelperPlc.setSessionAttribute("dataInicio", contrato.getDataInicioContrato() );
			
			contextHelperPlc.setSessionAttribute("dataFim", contrato.getDataFimContrato() );
			
			super.geraRelatorioPlc(AppConstantesComuns.RELATORIO.REL_CONTRATO, aluno, maps);

		} else {
			chamaGerarAditivo( contrato );
		}
	}

	/**
	 * Utilizado para calcular o valor total das mensalidades.
	 * Quando a turma for berçario serão 12 mensalidades, o restante 11 mensalidades
	 * @param aluno
	 * @param contrato 
	 * @return
	 */
	private BigDecimal calculaMensalidadeAluno(AlunoEntity aluno, ContratoEntity contrato) {
		int mesesFaltantes = getMesesFaltantesParaFimContrato( contrato );
		
		if( aluno.getTurma().getDescricao().toUpperCase().trim().contains( "BERC" ) ||
				aluno.getTurma().getDescricao().toUpperCase().trim().contains( "BERÇ" ) ){
			
			if( mesesFaltantes >= 12 ){
				return aluno.getValorTotalMensalidade().multiply( new BigDecimal(12) );
			}
			else{
				return aluno.getValorTotalMensalidade().multiply( new BigDecimal( mesesFaltantes ) );
			}
		}
		else{
			//Outras turmas será proporcional a 11 mensalidades
			if( mesesFaltantes >= 11 ){
				return aluno.getValorTotalMensalidade().multiply( new BigDecimal(11) );
			}
			else{
				return aluno.getValorTotalMensalidade().multiply( new BigDecimal( mesesFaltantes ) );
			}
		}
	}
	
	private String montaValoresContrato(ContratoEntity contrato, AlunoEntity aluno, BigDecimal valorTotalServicos) {
		
		StringBuffer sb = new StringBuffer();
		
		if (!aluno.getServicoAluno().isEmpty()){
			BigDecimal valor = new BigDecimal(0);
			valor = valorTotalServicos.multiply( new BigDecimal( getMesesFaltantesParaFimContrato(contrato) ) ); 
			
			CurrencyWriter cw = new CurrencyWriter(); 
			String valorServico = NumberFormat.getCurrencyInstance().format( valor );
			String valorServicoExtenso = cw.write( valor );
			
			sb.append("<br>§8o – Valores que não poderão ser deduzidos no IR (imposto de renda) será de "+valorServico+" ( "+valorServicoExtenso+"), equivalente ao(s) serviço(s) diverso(s) listado(s) abaixo: </br>");
			sb.append("<br/>");
			for (int i = 0; i < aluno.getServicoAluno().size(); i++) {
				if (aluno.getServicoAluno().get(i).getServico()!=null){
					sb.append(i+1+" - "+aluno.getServicoAluno().get(i).getServico().getDescricao()+" <br/>");
				}
			}
		}
		
		return sb.toString();
		
	}
	
	private String mesExtenso(int mes) {
		
		if (1==mes){
			return "uma";
		} else if (2==mes){
			return "dois";
		} else if (3==mes){
			return "três";
		} else if (4==mes){
			return "quatro";
		} else if (5==mes){
			return "cinco";
		} else if (6==mes){
			return "seis";
		} else if (7==mes){
			return "sete";
		} else if (8==mes){
			return "oito";
		} else if (9==mes){
			return "nove";
		} else if (10==mes){
			return "dez";
		} else if (11==mes){
			return "onze";
		}  else if (12==mes){
			return "doze";
		}
		return "";
	}
	
	public void geraAditivoContratoPlc() throws PlcException {
		
		ContratoEntity contrato = (ContratoEntity)entidadePlc;
		
		IAppFacade facade = (IAppFacade) getServiceFacade();
		setIdContrato(facade.temContratoVigente( contrato.getAluno().getId(), getAnoContrato() ) );
		if (getIdContrato()==null){
			throw new PlcException("erro.nao.existe.contrato" , new Object []{contrato.getAluno().getNomeAluno()});
		} else {
			chamaGerarAditivo(contrato);
		}
	}

	/**
	 * 
	 * @param contrato
	 */
	private void chamaGerarAditivo(ContratoEntity contrato) {
	
		contextHelperPlc.getRequest().getSession().setAttribute("gravarAditivo", PlcSimNao.S );
		contextHelperPlc.getRequest().setAttribute("geraAditivo", PlcSimNao.S.name() );
		contextHelperPlc.getRequest().setAttribute("idContrato", getIdContrato() );
		contextHelperPlc.getRequest().setAttribute("nomeAluno", contrato.getAluno().getId() );
	}
	
	/**
	 * Calulca quantos meses faltam para o fim do ano
	 * @param contrato 
	 * @return
	 */
	private int getMesesFaltantesParaFimContrato(ContratoEntity contrato) {
		
		Calendar calInicio = Calendar.getInstance();
		Calendar calFim = Calendar.getInstance();
		calInicio.setTime( contrato.getDataInicioContrato() );
		calFim.setTime( contrato.getDataFimContrato() );
		
		int mesInicio = calInicio.get(Calendar.MONTH) +1;
		int mesFim = calFim.get(Calendar.MONTH) +1;
		int mesesFaltantes = mesFim - mesInicio; 
		
		return mesesFaltantes;
	}
	
	@Override
	protected String editaApos() throws PlcException {
		
		String retorno = super.editaApos();
		
		return retorno;
		
	}
	
	public Long getIdContrato() {
		return idContrato;
	}
	
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getTextoAdtivo() {
		return textoAdtivo;
	}

	public void setTextoAdtivo(String textoAdtivo) {
		this.textoAdtivo = textoAdtivo;
	}
	
}