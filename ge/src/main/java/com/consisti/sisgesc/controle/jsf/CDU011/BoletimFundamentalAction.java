package com.consisti.sisgesc.controle.jsf.CDU011;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ui.util.JSF;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.controle.jsf.AppAction;
import com.consisti.sisgesc.entidade.BoletimFundamentalDetEntity;
import com.consisti.sisgesc.entidade.BoletimFundamentalEntity;
import com.consisti.sisgesc.facade.IAppFacade;
import com.powerlogic.jcompany.comuns.PlcArgVO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.config.comuns.PlcConstantes;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.MSG;
import com.powerlogic.jcompany.config.comuns.PlcConstantes.PlcJsfConstantes.NAVEGACAO;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * Classe de Controle gerada pelo assistente
 */
@SuppressWarnings("serial")
public class BoletimFundamentalAction extends AppAction  {
	
	/* (non-Javadoc)
	 * @see com.consisti.sisgesc.controle.jsf.AppAction#trataBotoesConformeLogicaApos()
	 */
	@Override
	protected void trataBotoesConformeLogicaApos() throws PlcException {
		super.trataBotoesConformeLogicaApos();
		trataBotoes();
		retirarMensagem();
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#pesquisaAntes(java.util.List)
	 */
	@Override
	protected boolean pesquisaAntes(List<PlcArgVO> listaArgumentos)
			throws PlcException {
		
		if( listaArgumentos != null ){
			for (PlcArgVO plcArgVO : listaArgumentos) {
				if( "aluno".equals( plcArgVO.getNome() ) ){
					//coloca o id do aluno na sessão
					contextHelperPlc.setSessionAttribute("idAluno", plcArgVO.getValor());
				}
			}
		}
		
		return super.pesquisaAntes(listaArgumentos);
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#editaAntes()
	 */
	@Override
	protected String editaAntes() throws PlcException {
		
		//quando o for registro de notas o chPlc é null
		if( getChPlc() == null ){
			//seta o chPlc como 0 para não encontrar e tratar como novo registro
			this.chPlc = "0";
			return NAVEGACAO.IND_MESMA_PAGINA;
		}
		else{
			removeAtributoSessao();
			return super.editaAntes();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#novoApos()
	 */
	@Override
	protected String novoApos() throws PlcException {
		
		String idAlunoStr = (String) contextHelperPlc.getSessionAttribute("idAluno");
		removeAtributoSessao();
		
		if( StringUtils.isNotBlank( idAlunoStr ) ){
			contextHelperPlc.setRequestAttribute("idAlunoReq", idAlunoStr);
		}
		else{
			idAlunoStr = (String) contextHelperPlc.getRequestAttribute("idAlunoReq");
			contextHelperPlc.getRequest().removeAttribute("idAlunoReq");
			
			BoletimFundamentalEntity boletim = (BoletimFundamentalEntity)entidadePlc;
			IAppFacade fc = (IAppFacade)getServiceFacade();
			
			this.entidadePlc = (PlcBaseVO) fc.montaBoletimAluno(Long.parseLong(idAlunoStr), boletim, ((AppBaseContextVO)getContext()) );
			
			if( ((AppBaseContextVO)getContext()).isApresentaMensagem() ){
				helperMsgJsfPlc.msg( "msg.info.CDU011.registroNotas.incompleto", MSG.IND_COR_MENSAGEM_VALORES.IND_AMARELO );
			}
			
		}
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.controle.jsf.PlcBaseJsfAction#abre()
	 */
	@Override
	public String abre() throws PlcException {
		removeAtributoSessao();
		return super.abre();
	}

	/**
	 * Retira atributos da sessao
	 */
	private void removeAtributoSessao() {
		contextHelperPlc.getRequest().getSession().removeAttribute( "idAluno" );
	}
	
	/**
	 *Trata botoes 
	 */
	private void trataBotoes() {
		contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_INCLUIR, PlcConstantes.NAO_EXIBIR );
		
		//quando não tiver todas as disciplinas lançadas não deixa gravar o boletim
		if( ((AppBaseContextVO)getContext()) != null && ((AppBaseContextVO)getContext()).isApresentaMensagem() ){
			contextHelperPlc.getRequest().setAttribute( PlcConstantes.ACAO.EXIBE_BT_GRAVAR, PlcConstantes.NAO_EXIBIR );
		}
		//so apresenta o botao para reprovar quando ainda não for boletim
		if( ((BoletimFundamentalEntity)entidadePlc) != null && ((BoletimFundamentalEntity)entidadePlc).getId() == null ){
			contextHelperPlc.getRequest().setAttribute("exibeReprovaRegistro", "S");
		}
		else{
			contextHelperPlc.getRequest().setAttribute("exibeReprovaRegistro", "N");
		}
	}
	
	/**
	 * Utilizado para setar o registro como aberto para o professor fazer a alteraçao
	 * @return
	 * @throws PlcException 
	 */
	public String reprovaRegistroFundamental() throws PlcException{
		
		//TODO impelmentar
		BoletimFundamentalEntity boletim = (BoletimFundamentalEntity)entidadePlc;
		
		if( boletim.getBoletimFundamentalDet() == null || boletim.getBoletimFundamentalDet().isEmpty() ){
			throw new PlcException("msg.info.boletimDet.vazio");
		}
		else{
			
			List<BoletimFundamentalDetEntity> listBoletimDetReprovar = new ArrayList<BoletimFundamentalDetEntity>();
			//percorre a lista para verificar se tem algum registro marcado
			for (Iterator iterator = boletim.getBoletimFundamentalDet().iterator(); iterator.hasNext();) {
				BoletimFundamentalDetEntity notas = (BoletimFundamentalDetEntity) iterator.next();
				
				if( PlcSimNao.S.toString().equals( notas.getNotaReprovada() ) ){
					//adiciona na lista as notas que vão ser reprovadas
					listBoletimDetReprovar.add( notas );
				}
			}
			
			if( listBoletimDetReprovar.isEmpty() ){
				throw new PlcException("msg.info.boletimDet.marcarNotas.reprovar");
			}
			else{
				IAppFacade fc = (IAppFacade)getServiceFacade();
				fc.reprovaRegistroNotas( listBoletimDetReprovar );
			}
			
		}
		
		
		return NAVEGACAO.IND_MESMA_PAGINA;
	}

}
