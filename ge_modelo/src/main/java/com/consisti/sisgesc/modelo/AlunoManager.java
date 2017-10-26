package com.consisti.sisgesc.modelo;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;

import com.consisti.sisgesc.comuns.AppBaseContextVO;
import com.consisti.sisgesc.comuns.AppConstantesComuns;
import com.consisti.sisgesc.dominio.PeriodoServico;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.CertidaoNascimento;
import com.consisti.sisgesc.entidade.ResponsavelAlunoCasoAcidente;
import com.consisti.sisgesc.entidade.ServicoAluno;
import com.consisti.sisgesc.persistencia.hibernate.AlunoDAO;
import com.consisti.sisgesc.persistencia.hibernate.TurmaDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class AlunoManager extends AppManager {
	
	public AlunoDAO alunoDAO;
	public TurmaDAO turmaDAO;
	
	public AlunoManager( AlunoDAO alunoDAO, TurmaDAO turmaDAO ) {
		this.alunoDAO = alunoDAO;
		this.turmaDAO = turmaDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void recuperaApos(Class classe, Object id, PlcBaseVO entidade)
			throws PlcException {
		
		AlunoEntity aluno = (AlunoEntity) entidade;
		
		Hibernate.initialize(aluno.getEndereco());
		Hibernate.initialize(aluno.getFiliacaoMae());
		Hibernate.initialize(aluno.getFiliacaoPai());
		Hibernate.initialize(aluno.getContrato());
		
		super.recuperaApos(classe, id, entidade);
	}
	
	/* (non-Javadoc)
	 * @see com.powerlogic.jcompany.modelo.PlcBaseBO#antesPersistencia(com.powerlogic.jcompany.comuns.PlcBaseVO, com.powerlogic.jcompany.comuns.PlcBaseVO, java.lang.String)
	 */
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		AlunoEntity aluno = (AlunoEntity)entidadeAtual;
		
		if( AppConstantesComuns.CASOUSO.CDU001.equals( ((AppBaseContextVO)getContext()).getCasoUso() ) ){
			validaListaResponsavelAlunoCasoAcidente(aluno);
			validaCertidaoNascimentoAluno( aluno );
			validaServicoAluno( aluno );
		}
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}
	
	/**
	 * Seta objeto aluno caso necessario
	 * @param aluno
	 */
	private void validaServicoAluno(AlunoEntity aluno) {
		
		if( !aluno.getServicoAluno().isEmpty() ){
			for (ServicoAluno servicoAluno : aluno.getServicoAluno()) {
				if( servicoAluno.getServico() != null ){
					if( servicoAluno.getAluno() == null){
						servicoAluno.setAluno(aluno);
					}
				}
			}
		}
	}

	/**
	 * Seta objeto aluno caso necessario
	 * @param aluno
	 */
	private void validaCertidaoNascimentoAluno(AlunoEntity aluno) {
		
		if( aluno.getCertidaoNascimento() != null ){
			for (CertidaoNascimento certidao : aluno.getCertidaoNascimento() ) {
				
				if( StringUtils.isNotBlank( certidao.getCertidaoNascimentoIdentidade() ) ){
					certidao.setAluno( aluno );
				}
			}
		}
	}

	/**
	 * Utilizado por problema na hora de gravar o detalhe!
	 * @param aluno
	 */
	private void validaListaResponsavelAlunoCasoAcidente(AlunoEntity aluno) {
		
		if( aluno.getResponsavelAlunoCasoAcidente() != null && !aluno.getResponsavelAlunoCasoAcidente().isEmpty() ){
			for (ResponsavelAlunoCasoAcidente responsavel : aluno.getResponsavelAlunoCasoAcidente()) {
				//se o nome estiver preenchido seta o mestre no detalhe
				if( responsavel.getNome() != null ){
					responsavel.setAluno( aluno );
				}
				else{
					responsavel.setIndExcPlc("S");
				}
			}
		}
	}

	public AlunoEntity recuperarAlunoVO( Long idAluno ) throws PlcException{
		AlunoEntity aluno = (AlunoEntity) recuperaSomenteVO( AlunoEntity.class, idAluno );
		Hibernate.initialize( aluno.getResponsavelFinanceiroAluno() );
		Hibernate.initialize( aluno.getSaudeAluno() );
		Hibernate.initialize( aluno.getServicoAluno() );
		
		return aluno;
	}
	
	@Override
	protected void recuperaListaApos(String orderyByDinamico, List argsQBE,
			int primeiraLinha, int maximoLinhas, List lista)
			throws PlcException {
		//Quando é jgrid o context tem outra instancia
		if( getContext() instanceof AppBaseContextVO ){
			if( AppConstantesComuns.CASOUSO.CDUF006.equals( ((AppBaseContextVO)getContext()).getCasoUso() ) ){
				getListaAlunoComValorTotal(lista);
			}
		}
		super.recuperaListaApos(orderyByDinamico, argsQBE, primeiraLinha, maximoLinhas,
				lista);
	}

	/**
	 * Utilizado pelo contas a receber por demanda
	 * @param lista
	 * @throws PlcException
	 */
	private void getListaAlunoComValorTotal(List lista) throws PlcException {
		if( lista != null ){
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				AlunoEntity aluno = (AlunoEntity) iterator.next();
				
				AlunoEntity alunoVO = recuperarAlunoVO(aluno.getId());
				
				if( alunoVO != null){
					aluno.setValorTotal( alunoVO.getValorTotalMensalidade() );
					aluno.setTurma( alunoVO.getTurma() );
					aluno.setTipoEducacao( alunoVO.getTipoEducacao() );
					
					if( alunoVO.getServicoAluno() != null ){
						for (ServicoAluno servicoAluno : alunoVO.getServicoAluno()) {
							//garante que soma somente mensal
							if( PeriodoServico.M.equals(servicoAluno.getServico().getPeriodoServico()) ){
								aluno.setValorTotal( aluno.getValorTotal().add( servicoAluno.getServico().getValorServico() ) );
							}
						}
					}
				}
			}			
		}
	}
	
}
