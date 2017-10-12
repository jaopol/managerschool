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
 /** ************************* META-DADOS GLOBAIS DA APLICA��O ******************************
  ********************** Configura��es padr�o para toda a aplica��o *************************
  ************ Obs: configura��es corporativas devem estar no n�vel anterior,****************
  ************              preferencialmente na camada Bridge               ****************
  *******************************************************************************************/

@PlcConfigGrupoControleAplicacao(
	navegacaoPadraoOtimizada="S",
	definicao=@PlcConfigAplicacaoDefinicao(
			grupoTecnologico=PlcConfigAplicacaoDefinicao.GrupoTecnologicoControleVisao.JSF_TRINIDAD_SEAM_FACELETS_JQUERY, nome="sisgesc",sigla="sisgesc",versao=1,release=0),
	controleIoC=@PlcConfigControleIoC(
		facadeImplementacaoPadrao="com.consisti.sisgesc.facade.AppFacadeImpl",
		contextPadrao="com.consisti.sisgesc.comuns.AppBaseContextVO",
		perfilUsuario=@PlcConfigPerfilUsuario(
				voPadrao=com.consisti.sisgesc.comuns.AppUsuarioPerfilVO.class,
				implementacaoPadrao=com.consisti.sisgesc.controle.AppUsuarioPerfilService.class)),
	classesDominioDiscreto={com.consisti.sisgesc.dominio.Status.class,
		        com.consisti.sisgesc.dominio.TipoConta.class,
		        com.consisti.sisgesc.dominio.SituacaoBoletim.class,
		        com.consisti.sisgesc.dominio.TipoPessoa.class,
		        com.consisti.sisgesc.dominio.AbertoFechado.class,
		        com.consisti.sisgesc.dominio.PeriodoServico.class,
		        com.consisti.sisgesc.dominio.AtivoInativo.class,
		        com.consisti.sisgesc.dominio.TipoResidencia.class,
		        com.consisti.sisgesc.dominio.EstadoCivil.class,
				com.consisti.sisgesc.dominio.Sexo.class,
				com.consisti.sisgesc.dominio.TipoImpostoRenda.class,
				com.consisti.sisgesc.dominio.TipoEducacao.class,
				com.consisti.sisgesc.dominio.PeriodoAluno.class,
				com.consisti.sisgesc.dominio.TipoMatricula.class,
				com.consisti.sisgesc.dominio.EstadoCivil.class,
				com.consisti.sisgesc.dominio.Uf.class,
				com.consisti.sisgesc.dominio.TipoMovimentacao.class,
				com.powerlogic.jcompany.dominio.tipo.PlcSimNao.class,
				com.consisti.sisgesc.dominio.BancoSuportado.class,
				com.consisti.sisgesc.dominio.CarteiraBanco.class,
				com.consisti.sisgesc.dominio.TipoReceberDe.class,
				com.consisti.sisgesc.dominio.TipoFavorecido.class,
				com.consisti.sisgesc.dominio.TipoContaReceber.class},
	classesLookup={
		com.consisti.sisgesc.entidade.financeiro.BancoEntity.class,
		com.consisti.sisgesc.entidade.financeiro.ProdutoVendaEntity.class,
		com.consisti.sisgesc.entidade.FornecedorEntity.class,
		com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity.class,
		com.consisti.sisgesc.entidade.financeiro.PlanoContasEntity.class,
		com.consisti.sisgesc.entidade.EmpresaEntity.class,
		com.consisti.sisgesc.entidade.TurmaEntity.class,
		com.consisti.sisgesc.entidade.EventoEntity.class}
)

@PlcConfigOtimizacao(javascriptEspecificoUsa=true, cssEspecificoUsa=false)

/*
@PlcConfigComportamento
@PlcConfigModeloTecnologia ( tecnologia= PlcConfigModeloTecnologia.Tecnologia.EJB3)
@PlcConfigEjbFacadeRef(nomeFacadeApp="sisgesc_ear/AppFacadeImpl/remote")
*/

package com.powerlogic.jcompany.config.app;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigAplicacaoDefinicao;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigControleIoC;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigGrupoControleAplicacao;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigOtimizacao;
import com.powerlogic.jcompany.config.controle.geral.PlcConfigPerfilUsuario;
 
