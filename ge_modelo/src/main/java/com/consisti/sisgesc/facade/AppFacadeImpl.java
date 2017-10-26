/*  																													
	    				   jCompany Developer Suite																		
			    		Copyright (C) 2008  Powerlogic																	
	 																													
	    Este programa � licenciado com todos os seus c�digos fontes. Voc� pode modific�-los e							
	    utiliz�-los livremente, inclusive distribu�-los para terceiros quando fizerem parte de algum aplicativo 		
	    sendo cedido, segundo os termos de licenciamento gerenciado de c�digo aberto da Powerlogic, definidos			
	    na licen�a 'Powerlogic Open-Source Licence 2.0 (POSL 2.0)'.    													
	  																													
	    A Powerlogic garante o acerto de erros eventualmente encontrados neste c�digo, para os clientes licenciados, 	
	    desde que todos os c�digos do programa sejam mantidos intactos, sem modifica��es por parte do licenciado. 		
	 																													
	    Voc� deve ter recebido uma c�pia da licen�a POSL 2.0 juntamente com este programa.								
	    Se n�o recebeu, veja em <http://www.powerlogic.com.br/licencas/posl20/>.										
	 																													
	    Contatos: plc@powerlogic.com.br - www.powerlogic.com.br 														
																														
 */ 
package com.consisti.sisgesc.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jrimum.bopepo.Boleto;

import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.ContratoEntity;
import com.consisti.sisgesc.entidade.EnderecoEntity;
import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.entidade.FuncionarioEntity;
import com.consisti.sisgesc.entidade.MovimentoDiaEntity;
import com.consisti.sisgesc.entidade.ResponsavelFinanceiroAlunoEntity;
import com.consisti.sisgesc.entidade.ServicoAluno;
import com.consisti.sisgesc.entidade.ServicosEntity;
import com.consisti.sisgesc.entidade.Turma;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.consisti.sisgesc.entidade.autenticacao.PerfilEntity;
import com.consisti.sisgesc.entidade.autenticacao.UsuarioEntity;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.ContaPagar;
import com.consisti.sisgesc.entidade.financeiro.ContaReceber;
import com.consisti.sisgesc.entidade.financeiro.ContaReceberEntity;
import com.consisti.sisgesc.entidade.financeiro.ExtratoAluno;
import com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity;
import com.consisti.sisgesc.entidade.financeiro.TipoPlanoContasEntity;
import com.consisti.sisgesc.modelo.AlunoManager;
import com.consisti.sisgesc.modelo.ContaReceberManager;
import com.consisti.sisgesc.modelo.ContratoManager;
import com.consisti.sisgesc.modelo.ExtratoAlunoManager;
import com.consisti.sisgesc.modelo.MovimentoDiaManager;
import com.consisti.sisgesc.modelo.ServicoAlunoManager;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.BancoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContaPagarDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContaReceberDAO;
import com.consisti.sisgesc.persistencia.hibernate.ContratoDAO;
import com.consisti.sisgesc.persistencia.hibernate.EnderecoDAO;
import com.consisti.sisgesc.persistencia.hibernate.FornecedorDAO;
import com.consisti.sisgesc.persistencia.hibernate.FuncionarioDAO;
import com.consisti.sisgesc.persistencia.hibernate.MovimentoDiaDAO;
import com.consisti.sisgesc.persistencia.hibernate.ProdutoMaterialDAO;
import com.consisti.sisgesc.persistencia.hibernate.ProdutoVendaDAO;
import com.consisti.sisgesc.persistencia.hibernate.ResponsavelFinanceiroAlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ServicoAlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.ServicoDAO;
import com.consisti.sisgesc.persistencia.hibernate.TabelaPrecoDAO;
import com.consisti.sisgesc.persistencia.hibernate.TipoPlanoContaDAO;
import com.consisti.sisgesc.persistencia.hibernate.TurmaDAO;
import com.consisti.sisgesc.persistencia.hibernate.autenticacao.PerfilDAO;
import com.consisti.sisgesc.persistencia.hibernate.autenticacao.UsuarioDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.powerlogic.jcompany.facade.PlcFacadeImpl;

/**
 * sisgesc . Classe de implementa��o da interface de persist�ncia
 */
@SuppressWarnings("serial")
public class AppFacadeImpl extends PlcFacadeImpl implements IAppFacade, IAppFacadeRemote {
	
	public List<PerfilEntity> recuperaListaUrlPerfilByLoginUsuario( String login ) throws PlcException{
		PerfilDAO dao = (PerfilDAO)getDAO(PerfilDAO.class );
		return dao.recuperaListaUrlPerfilByLoginUsuario( login );
	}
	
	public PerfilEntity recuperaPerfilUsuarioByLoginUsuario( String login ) throws PlcException{
		PerfilDAO dao = (PerfilDAO)getDAO(PerfilDAO.class );
		return dao.recuperaPerfilUsuarioByLoginUsuario(login);
	}
	
	public ServicosEntity recuperaServico( Long idServico ) throws PlcException{
		ServicoDAO dao = (ServicoDAO)getDAO(ServicoDAO.class);
		return dao.recuperaServico(idServico);
	}
	
	public List<AlunoEntity> recuperaAlunoPelaTurma( Turma turma ) throws PlcException{
		AlunoDAO dao = (AlunoDAO)getDAO(AlunoDAO.class);
		return dao.recuperaAlunoPelaTurma(turma);
	}
	
	public UsuarioEntity recuperaUsuarioByLogin( String login ) throws PlcException{
		UsuarioDAO dao = (UsuarioDAO)getDAO(UsuarioDAO.class);
		return dao.recuperaUsuarioByLogin(login);
	}
	
	public FuncionarioEntity recuperaFuncionarioById( Long idFuncionario ) throws PlcException{
		FuncionarioDAO dao = (FuncionarioDAO)getDAO(FuncionarioDAO.class);
		return dao.recuperaFuncionarioById(idFuncionario);
	}
	
	public String recuperaDescricaoTurma( Long idTurma ) throws PlcException{
		TurmaDAO dao = (TurmaDAO)getDAO(TurmaDAO.class);
		return dao.recuperaDescricaoTurma(idTurma);
	}

	public Long temContratoVigente(Long idAluno, int ano) throws PlcException {
		ContratoDAO dao = (ContratoDAO) getDAO(ContratoDAO.class);
		return dao.temContratoVigente(idAluno, ano);
	}

	public byte [] recuperaContratoAluno(Long idContrato) throws PlcException {
		ContratoDAO dao = (ContratoDAO) getDAO(ContratoDAO.class);
		return dao.recuperaContrato(idContrato);
	}

	public boolean validaTabelaDuplicada(Long idTurma, Integer anoLetivo) throws PlcException {
		TabelaPrecoDAO dao = (TabelaPrecoDAO) getDAO(TabelaPrecoDAO.class);
		return dao.validaTabelaDuplicada(idTurma, anoLetivo);
	}
	
	public BigDecimal recuperaValorMensalidade( Long idTurma, String cargaHoraria, Integer anoLetivo ) throws PlcException{
		TabelaPrecoDAO dao = (TabelaPrecoDAO) getDAO( TabelaPrecoDAO.class );
		return dao.recuperaValorMensalidade(idTurma, cargaHoraria, anoLetivo);
	}

	public TurmaEntity recuperaIdadePermitida(Long idTurma) throws PlcException {
		TurmaDAO dao = (TurmaDAO)getDAO(TurmaDAO.class);
		return dao.recuperaIdadePermitida(idTurma);
	}

	public FornecedorEntity recuperaCnpj(String cnpj) throws PlcException {
		FornecedorDAO dao = (FornecedorDAO)getDAO(FornecedorDAO.class);
		return dao.recuperaCnpj(cnpj);
	}

	public FornecedorEntity recuperaCpf(String cpf) throws PlcException {
		FornecedorDAO dao = (FornecedorDAO)getDAO(FornecedorDAO.class);
		return dao.recuperaCpf(cpf);
	}
	
	public ArrayList<TipoPlanoContasEntity> recuperaListaTipoPlanoContaByIdPlanoConta(Long idPlanoConta) throws PlcException{
		TipoPlanoContaDAO dao = (TipoPlanoContaDAO)getDAO( TipoPlanoContaDAO.class );
		return dao.recuperaListaTipoPlanoContaByIdPlanoConta( idPlanoConta );
	}
	
	public Boleto gerarBoleta(ContaReceberEntity contaReceber) throws PlcException{
		ContaReceberManager bo = (ContaReceberManager)getBO(ContaReceberManager.class);
		return bo.gerarBoleta(contaReceber);
	}
	
	public BigDecimal getValorTotalServicosAluno( List<ServicoAluno> listServicoAluno ) throws PlcException{
		ServicoAlunoManager bo = (ServicoAlunoManager)getBO(ServicoAlunoManager.class);
		return bo.getValorTotalServicosAluno( listServicoAluno );
	}

	public AlunoEntity recuperarAlunoVO( Long idAluno ) throws PlcException{
		AlunoManager bo = (AlunoManager) getBO(AlunoManager.class);
		return bo.recuperarAlunoVO(idAluno);
	}
	
	public void alteraContrato( ContratoEntity contrato ) throws PlcException{
		ContratoManager bo = (ContratoManager) getBO(ContratoManager.class);
		bo.alteraContrato(contrato);
	}
	
	public EnderecoEntity recuperaEnderecoAluno( Long idAluno ) throws PlcException{
		EnderecoDAO dao = (EnderecoDAO)getDAO(EnderecoDAO.class);
		return dao.recuperaEnderecoAluno(idAluno);
	}
	
	public ResponsavelFinanceiroAlunoEntity recuperaResponsavelFinanceiroAluno( Long idAluno ) throws PlcException{
		ResponsavelFinanceiroAlunoDAO dao = (ResponsavelFinanceiroAlunoDAO)getDAO(ResponsavelFinanceiroAlunoDAO.class);
		return dao.recuperaResponsavelFinanceiroAluno(idAluno);
	}

	public List<ContaPagar> recuperaContaPagar(Date dataInicio, Date dataFim)
			throws PlcException {
		ContaPagarDAO dao = (ContaPagarDAO)getDAO(ContaPagarDAO.class);
		return dao.recuperarAllContaPagar(dataInicio, dataFim);
	}

	public List<ContaReceber> recuperaContaReceber(Date dataInicio, Date dataFim)
			throws PlcException {
		ContaReceberDAO dao = (ContaReceberDAO)getDAO(ContaReceberDAO.class);
		return dao.recuperarAllContaReceber(dataInicio, dataFim);
	}

	public List<BancoEntity> recuperaListaBanco() throws PlcException {
		BancoDAO dao = (BancoDAO)getDAO(BancoDAO.class);
		return dao.recuperaListaBanco();
	}

	public void excluirServicoAluno(Long id) throws PlcException {
		ServicoAlunoDAO dao = (ServicoAlunoDAO)getDAO(ServicoAlunoDAO.class);
		dao.excluirServicoAluno(id);
	}

	public void gravarContaReceberPorDemanda(List<PlcBaseVO> listaVO)
			throws PlcException {
		ContaReceberManager bo = (ContaReceberManager)getBO(ContaReceberManager.class);
		bo.gravarContaReceberPorDemanda(listaVO);
	}


	public void pesquisaMovimentoDia(MovimentoDiaEntity movimentoDia, Date date, Long idBanco) throws PlcException {
		MovimentoDiaManager movimentoManager = (MovimentoDiaManager)getBO(MovimentoDiaManager.class);
		movimentoManager.pesquisaMovimentoDia(movimentoDia, date, idBanco);
	}

	public void fecharCaixa(MovimentoDiaEntity movimentoDia) throws PlcException {
		MovimentoDiaDAO dao = (MovimentoDiaDAO)getDAO(MovimentoDiaDAO.class);
		if (movimentoDia.getId()==null){
			dao.inclui(movimentoDia);
		} else {
			dao.altera(movimentoDia);
		}
	}

	public MovimentoDiaEntity recuperaMovimentoExistente(Date dataMovimento) throws PlcException {
		MovimentoDiaDAO dao = (MovimentoDiaDAO)getDAO(MovimentoDiaDAO.class);
		return dao.recuperaMovimentoExistente(dataMovimento);
	}
	public ContratoEntity recuperaUltimoContratoAluno(Long idAluno) throws PlcException {
		ContratoDAO dao = (ContratoDAO)getDAO(ContratoDAO.class);
		return dao.recuperaContratoAluno(idAluno);
	}
	
	public List<AlunoEntity> recuperaDadosPorTurma(Long idTurma) throws PlcException {
		AlunoDAO dao = (AlunoDAO)getDAO(AlunoDAO.class);
		return dao.recuperaDadosPorTurma(idTurma);
	}

	public ContaReceberEntity recuperaValorAlunoSetContaReceber(Long idAluno) throws PlcException {
		ContaReceberManager bo = (ContaReceberManager)getBO(ContaReceberManager.class);
		return bo.recuperaValorAlunoSetContaReceber(idAluno);
	}

	public List<ExtratoAluno> getListExtratoProdutoAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException {
		ExtratoAlunoManager bo = (ExtratoAlunoManager)getBO(ExtratoAlunoManager.class);
		return bo.getListExtratoProdutoAluno(idAluno, dataInicio, dataFim, recebido);
	}
	
	public List<AlunoEntity> recuperarAluno(Aluno aluno, Turma turma, TipoEducacao educacao) throws PlcException{
		AlunoDAO dao = (AlunoDAO) getDAO(AlunoDAO.class);
		return dao.recuperarAluno(aluno, turma, educacao);
	}

	public List<ContaReceberEntity> gravarContaReceberEvento(List<AlunoEntity> listAluno,
			ContaReceberEntity contaReceber) throws PlcException {
		ContaReceberManager bo = (ContaReceberManager)getBO(ContaReceberManager.class);
		return bo.gravarContaReceberEvento(listAluno, contaReceber);
	}

	public String recuperaDescricaoProdutoVenda(Long id) throws PlcException {
		ProdutoVendaDAO dao = (ProdutoVendaDAO)getDAO(ProdutoVendaDAO.class);
		return dao.recuperarDescricaoProdutoVenda(id);
	}

}
