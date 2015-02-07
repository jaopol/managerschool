package com.consisti.sisgesc.controle.jsf.financeiro.CDUF006;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.valida.PlcMensagem.Cor;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class ContaReceberDemandaAction extends AppAction  {

	private List<BancoEntity> listBanco;
	private List<SelectItem> comboBanco;
	
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		
		contextHelperPlc.getRequest().setAttribute("exibeGravarContaReceber", "S");
		
		super.trataBotoesConformeLogicaApos();
	}
	
	@Override
	protected boolean abreAntes() throws PlcException {
		IAppFacade facade = (IAppFacade)getServiceFacade();
		List<BancoEntity> list = facade.recuperaListaBanco();
		if(list != null ){
			comboBanco = new ArrayList<SelectItem>();
			for (BancoEntity bancoEntity : list) {
				comboBanco.add( new SelectItem(bancoEntity.getId(), bancoEntity.getDescricaoBanco()));
			}
		}
		return super.abreAntes();
	}
	
	@Override
	protected boolean pesquisaAntes(List<PlcArgVO> listaArgumentos)
			throws PlcException {
		((AppBaseContextVO)getContext()).setCasoUso( AppConstantesComuns.CASOUSO.CDUF006 );
		
		if(listaArgumentos.isEmpty()){
			throw new PlcException("msg.infor.contaReceberDemanda.arg.obrigatorio");
		}
		
		return super.pesquisaAntes(listaArgumentos);
	}
	
	/**
	 * Utilizado quando clicar no botao Gerar contas a receber
	 * @return
	 * @throws PlcException
	 */
	public String gravarContaReceber() throws PlcException{
		
		IAppFacade fc = (IAppFacade)getServiceFacade();
		
		List<PlcBaseVO> listaVO = logicaItensPlc.getItensPlc();
		if(listaVO != null){
			fc.gravarContaReceberPorDemanda(listaVO);
			helperMsgJsfPlc.msg("msg.infor.contaReceber.geradoSucesso", Cor.msgAzulPlc.toString());
		}
		else{
			throw new PlcException("msg.infor.contaReceberDemanda.naoExiste");
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

	public List<BancoEntity> getListBanco() {
		return listBanco;
	}

	public void setListBanco(List<BancoEntity> listBanco) {
		this.listBanco = listBanco;
	}

	public List<SelectItem> getComboBanco() {
		return comboBanco;
	}

	public void setComboBanco(List<SelectItem> comboBanco) {
		this.comboBanco = comboBanco;
	}

	
}
