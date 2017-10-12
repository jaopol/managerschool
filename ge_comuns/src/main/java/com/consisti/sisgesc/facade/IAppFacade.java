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
import com.consisti.sisgesc.entidade.financeiro.TipoPlanoContasEntity;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;
import com.powerlogic.jcompany.comuns.facade.IPlcFacade;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;

/**
 * sisgesc . Interface de persistência.
 */
/**
 * @author joaopaulo
 *
 */
public interface IAppFacade extends IPlcFacade {
	
	/**
	 * Recupera a lista de urls do perfil pelo login do usuario
	 * @param login
	 * @return List<PerfilEntity>
	 * @throws PlcException
	 */
	List<PerfilEntity> recuperaListaUrlPerfilByLoginUsuario( String login ) throws PlcException;

	/**
	 * Recupera o perfil pelo login do usuario
	 * @param login
	 * @return PerfilEntity
	 * @throws PlcException
	 */
	PerfilEntity recuperaPerfilUsuarioByLoginUsuario( String login ) throws PlcException;
	
	/**
	 * Recupera o Serviço pelo id
	 * @param idServico
	 * @return
	 * @throws PlcException
	 */
	ServicosEntity recuperaServico( Long idServico ) throws PlcException;
	
	/**
	 * Recupera a lista de aluno da turma
	 * @param turma
	 * @return List<AlunoEntity>
	 * @throws PlcException
	 */
	List<AlunoEntity> recuperaAlunoPelaTurma( Turma turma ) throws PlcException;

	/**
	 * @param login
	 * @return
	 */
	UsuarioEntity recuperaUsuarioByLogin(String login) throws PlcException;

	/**
	 * Recupera o funcinario pelo id
	 * @param id
	 * @return
	 */
	FuncionarioEntity recuperaFuncionarioById(Long id) throws PlcException;

	/**
	 * Utilizado para recuperar a descriçao da turma pelo id
	 * @param idTurma
	 * @return
	 * @throws PlcException
	 */
	String recuperaDescricaoTurma(Long idTurma) throws PlcException;

	Long temContratoVigente(Long idAluno, int year) throws PlcException;

	byte[] recuperaContratoAluno(Long idContrato) throws PlcException;

	boolean validaTabelaDuplicada(Long idTurma, Long idTabela) throws PlcException;
	
	/**
	 * Recupera o valor da mensalidade pela turma e carga horaria
	 * @param idTurma
	 * @param cargaHoraria
	 * @return
	 * @throws PlcException 
	 * @throws PlcException
	 */
	BigDecimal recuperaValorMensalidade( Long idTurma, String cargaHoraria ) throws PlcException;
	
	/**
	 * Recupera a idade maxima e minima permitida
	 * para o aluno entrar na turma
	 * @param idTipoEducacao
	 * @return Long idade
	 * @throws PlcException
	 */
	TurmaEntity recuperaIdadePermitida( Long idTurma ) throws PlcException;

	FornecedorEntity recuperaCpf(String cpf) throws PlcException;

	FornecedorEntity recuperaCnpj(String cnpj) throws PlcException;

	/**
	 * Recupera a lista de Tipo Plano Contas pelo id do Plano Contas
	 * @param idPlanoConta
	 * @return List
	 * @throws PlcException
	 */
	ArrayList<TipoPlanoContasEntity> recuperaListaTipoPlanoContaByIdPlanoConta(Long idPlanoConta) throws PlcException;
	
	/**
	 * @param contaReceber
	 */
	Boleto gerarBoleta(ContaReceberEntity contaReceber) throws PlcException;

	/**
	 * Utilizado para somar e retornar o valor de todos os serviços do aluno
	 * @param listServicoAluno
	 * @return
	 * @throws PlcException
	 */
	BigDecimal getValorTotalServicosAluno( List<ServicoAluno> listServicoAluno ) throws PlcException;

	AlunoEntity recuperarAlunoVO( Long idAluno ) throws PlcException;
	
	/**
	 *Faz update no contrato
	 * @param contrato
	 * @throws PlcException
	 */
	void alteraContrato( ContratoEntity contrato ) throws PlcException;
	
	/**
	 * Recupera o endereco do aluno
	 * @param idAluno
	 * @return
	 * @throws PlcException
	 */
	EnderecoEntity recuperaEnderecoAluno( Long idAluno ) throws PlcException;
	
	/**
	 * Recupera o responsavel financeiro do aluno
	 * @param idAluno
	 * @return
	 * @throws PlcException
	 */
	ResponsavelFinanceiroAlunoEntity recuperaResponsavelFinanceiroAluno( Long idAluno ) throws PlcException;

	List<ContaPagar> recuperaContaPagar(Date dataInicio, Date dataFim) throws PlcException;

	List<ContaReceber> recuperaContaReceber(Date dataInicio, Date dataFim) throws PlcException;
	
	/**
	 * Utilizado para gravar o contas a reber por demanda
	 * @param listaVO
	 * @throws PlcException
	 */
	void gravarContaReceberPorDemanda( List<PlcBaseVO> listaVO) throws PlcException;

	/**
	 * Recupera os bancos cadastrados
	 * @return List Banco
	 * @throws PlcException 
	 */
	List<BancoEntity> recuperaListaBanco() throws PlcException;

	void excluirServicoAluno(Long id) throws PlcException;
	void pesquisaMovimentoDia(MovimentoDiaEntity movimentoDia, Date date, Long idBanco)throws PlcException;

	void fecharCaixa(MovimentoDiaEntity movimentoDia)throws PlcException;

	MovimentoDiaEntity recuperaMovimentoExistente(Date dataMovimento)throws PlcException;	/**
	 * Recupera o ultimo contrato ativo do aluno
	 * @param id
	 * @return
	 * @throws PlcException 
	 */
	ContratoEntity recuperaUltimoContratoAluno(Long id) throws PlcException;
	
	List<AlunoEntity> recuperaDadosPorTurma(Long idTurma) throws PlcException;
	

	/**
	 * Recupera os valore do aluno
	 * @param contaReceber
	 * @throws PlcException
	 */
	public ContaReceberEntity recuperaValorAlunoSetContaReceber(Long idAluno) throws PlcException;

	/**
	 * Utilizado para recuperar as contas recebidas do aluno e montar o extrato
	 * @param idAluno
	 * @param dataFim 
	 * @param dataInicio 
	 * @throws PlcException 
	 */
	List<ExtratoAluno> getListExtratoProdutoAluno(Long idAluno, Date dataInicio, Date dataFim, PlcSimNao recebido) throws PlcException;
	
	
	/**
	 * Recupera o aluno
	 * @param aluno
	 * @param turma
	 * @param educacao
	 * @return List Aluno
	 * @throws PlcException
	 */
	List<AlunoEntity> recuperarAluno(Aluno aluno, Turma turma, TipoEducacao educacao) throws PlcException;
	
	/**
	 * @param listAluno
	 * @param contaReceber
	 * @return retorna a lista gravada
	 * @throws PlcException
	 */
	List<ContaReceberEntity> gravarContaReceberEvento(List<AlunoEntity> listAluno, ContaReceberEntity contaReceber) throws PlcException;

	String recuperaDescricaoProdutoVenda(Long id) throws PlcException;

}
