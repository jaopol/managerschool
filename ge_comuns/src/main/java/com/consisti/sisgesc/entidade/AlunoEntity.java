package com.consisti.sisgesc.entidade;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.Sexo;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.dominio.Uf;
import com.powerlogic.jcompany.comuns.anotacao.PlcEntidade;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="ALUNO")
@SequenceGenerator(name="SE_ALUNO", sequenceName="SE_ALUNO")
@AccessType("field")

@PlcEntidade(numPorPagina=20)

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="AlunoEntity.querySel4", query="select new AlunoEntity(obj.id, obj.nomeAluno ) from AlunoEntity obj order by obj.nomeAluno asc"),
	@NamedQuery(name="AlunoEntity.querySel3", query="select new AlunoEntity(obj.id, obj.matricula, obj.sexo) from AlunoEntity obj order by obj.id asc"),
	@NamedQuery(name="AlunoEntity.querySel2", query="select new AlunoEntity(obj.id, obj.matricula, obj.nomeAluno) from AlunoEntity obj where obj.tipoEducacao = 'F' order by obj.nomeAluno asc"),
	@NamedQuery(name="AlunoEntity.queryMan", query="from AlunoEntity obj"),
	@NamedQuery(name="AlunoEntity.querySel", query="select new AlunoEntity(obj.id, obj.matricula, obj.turma.id , obj.turma.descricao, obj.nomeAluno, obj.telefone, obj.responsavelAluno, obj.tipoEducacao) from AlunoEntity obj left outer join obj.turma order by obj.matricula asc, obj.nomeAluno asc, obj.turma.descricao asc"),
	@NamedQuery(name="AlunoEntity.querySelLookup", query="select new AlunoEntity (obj.id, obj.matricula, obj.nomeAluno) from AlunoEntity obj where obj.id = ? order by obj.id asc")
})
public class AlunoEntity extends Aluno {
	
	
	private transient List<CronogramaTurma> cronogramaTurma;
	private transient AlunoEntity aluno;
	//utilizado no contas receber demanda
	private transient Long idBanco;
	private transient Date dataVencimento;
	private transient BigDecimal valorTotal;
	private transient String tipoEducacaoStr;
	private transient List<AlunoEntity> dadosPorTurma;
	private transient String responsavelFinanceiroStr;
	private transient String valorMensalidadeStr;
	private transient Long periodoContrato;
 	
    /*
     * Construtor padrão
     */
    public AlunoEntity() {
    }
	public AlunoEntity(Long id, String matricula, String nomeAluno) {
		this.setId(id);
		this.setMatricula(matricula);
		this.setNomeAluno(nomeAluno);
	}
	
	/**
	 * Utilizado pelo metodo recuperaAlunoPelaTurma
	 * @param id
	 * @param nomeAluno
	 */
	public AlunoEntity(Long id, String nomeAluno) {
		this.setId(id);
		this.setNomeAluno(nomeAluno);
	}
	
	@Override
	public String toString() {
		if( getNomeAluno() != null ){
			return getNomeAluno();
		}
		else{
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.TurmaEntity.class,com.consisti.sisgesc.entidade.EnderecoEntity.class,com.consisti.sisgesc.entidade.CertidaoNascimentoEntity.class,com.consisti.sisgesc.entidade.FiliacaoPaiEntity.class,com.consisti.sisgesc.entidade.FiliacaoMaeEntity.class,com.consisti.sisgesc.entidade.SaudeAlunoEntity.class};
	}

	public AlunoEntity(Long id, String matricula, Long turmaId, String turmaDescricao, String nomeAluno, String telefone, String responsavelAluno, TipoEducacao tipoEducacao) {
		setId(id);
		setMatricula(matricula);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		setNomeAluno(nomeAluno);
		setTelefone(telefone);
		setResponsavelAluno(responsavelAluno);
		setTipoEducacao(tipoEducacao);
		
		
	}
	
	@Transient
	public String getRua(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getLogradouro(); 
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getNumero(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getNumero(); 
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getComplemento(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getComplemento(); 
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getBairro(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getBairro(); 
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getCep(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getCep(); 
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getUfStr(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getUf().name() +" - "+Uf.valueOf(this.getEndereco().get(0).getUf().name()).getDescricao();
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getCidadeStr(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return StringUtils.isNotEmpty(this.getEndereco().get(0).getCidade()) ? this.getEndereco().get(0).getCidade() : "";
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getEstadoStr(){
		if (this.getEndereco()!=null){
			if (!this.getEndereco().isEmpty()){
				return this.getEndereco().get(0).getUf() != null ? Uf.valueOf(this.getEndereco().get(0).getUf().name()).getDescricao() : "";
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getNomePaiStr(){
		if (this.getFiliacaoPai()!=null){
			if (!this.getFiliacaoPai().isEmpty()){
				return StringUtils.isNotEmpty(this.getFiliacaoPai().get(0).getNomePai()) ? this.getFiliacaoPai().get(0).getNomePai() : "";
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getNomeMaeStr(){
		if (this.getFiliacaoMae()!=null){
			if (!this.getFiliacaoMae().isEmpty()){
				return StringUtils.isNotEmpty(this.getFiliacaoMae().get(0).getNomeMae()) ? this.getFiliacaoMae().get(0).getNomeMae() : "";
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getFazTratSaude(){
		if (this.getSaudeAluno()!=null){
			if (!this.getSaudeAluno().isEmpty()){
				if (PlcSimNao.S.equals(this.getSaudeAluno().get(0).getFazTratamentoSaude())) {
					return "(X) Sim  ( ) Não    Qual? "+this.getSaudeAluno().get(0).getQualTratamentoSaude();
				} else {
					return "( ) Sim  (X) Não    Qual?";
				}
			} else {
				return "";
			}
		}
		return "";
	}
	
	@Transient
	public String getAlergia(){
		if (this.getSaudeAluno()!=null){
			if (!this.getSaudeAluno().isEmpty()){
				if (PlcSimNao.S.equals(this.getSaudeAluno().get(0).getAlergia())) {
					return "(X) Sim  ( ) Não    Qual? "+this.getSaudeAluno().get(0).getQualAlergia();
				} else {
					return "( ) Sim  (X) Não    Qual?";
				}
			} else {
				return "";
			}
		}
		
		return "";
		
	}
	
	@Transient
	public String getFebreDorCabeca(){
		if (this.getSaudeAluno()!=null){
			if (!this.getSaudeAluno().isEmpty()){
				return "<style isBold='true' pdfFontName='Helvetica-Bold'>Em caso de febre/ medicação: </style>"+this.getSaudeAluno().get(0).getQualRemedioCasoDorCabFebre()+"  Dosagem: "+this.getSaudeAluno().get(0).getDosagemDorRemedioCasoDorCabFebre();
			}
			return "";
		}
		return "";
	}
	
	@Transient
	public String getTipoIR(){
		return "www";
	}
	
	@Transient
	public String getDocumento(){
		if (this.getCertidaoNascimento()!=null){
			if (!this.getCertidaoNascimento().isEmpty()){
				StringBuilder sb = new StringBuilder();
				if (StringUtils.isNotEmpty(this.getCertidaoNascimento().get(0).getCertidaoNascimentoIdentidade())){
					sb.append(this.getCertidaoNascimento().get(0).getCertidaoNascimentoIdentidade());
				}
				if (StringUtils.isNotEmpty(this.getCertidaoNascimento().get(0).getNumeroIdentidade())){
					sb.append(" / ");
					sb.append(this.getCertidaoNascimento().get(0).getNumeroIdentidade());
				}
				return sb.toString();
			}
			return "";
		}
		return "";
	}
	
	@Transient
	public String getNomeRespFinanceiroStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getNome();
			}
		}
		return "";
	}
	
	@Transient
	public String getDataNascimentoStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				return sdf.format(this.getResponsavelFinanceiroAluno().get(0).getDataNascimento());
			}
		}
		return "";
	}
	
	@Transient
	public String getNacionalidadeStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getNacionalidade();
			}
		}
		return "";
	}
	
	@Transient
	public String getEstadoCivilStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getEstadoCivil().getDescricao();
			}
		}
		return "";
	}
	
	@Transient
	public String getProfissaoStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getProfissao();
			}
			return "";
		}
		return "";
	}
	
	@Transient
	public String getTelefoneStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getTelefone();
			}
		}
		return "";
	}
	
	@Transient
	public String getLocalTrabalhoStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getLocalTrabalho();
			}
		}
		return "";
	}
	
	@Transient
	public String getCpfStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getCpf();
			}
		}
		return "";
	}
	
	@Transient
	public String getRgStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getIdentidade();
			}
		}
		return "";
	}
	
	@Transient
	public String getImpostoStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				return this.getResponsavelFinanceiroAluno().get(0).getImpostoRenda().toString();
			}
		}
		return "";
	}
	
	@Transient
	public String getQualImpostoStr(){
		if (this.getResponsavelFinanceiroAluno()!=null){
			if (!this.getResponsavelFinanceiroAluno().isEmpty()){
				if (this.getResponsavelFinanceiroAluno().get(0).getQualImposto()!=null){
					return this.getResponsavelFinanceiroAluno().get(0).getQualImposto().getDescricao();
				}
			}
			return "";
		}
		return "";
	}
	
	public List<CronogramaTurma> getCronogramaTurma() {
		return cronogramaTurma;
	}
	public void setCronogramaTurma(List<CronogramaTurma> cronogramaTurma) {
		this.cronogramaTurma = cronogramaTurma;
	}
	
	public AlunoEntity(Long id, String matricula, Sexo sexo) {
		setId(id);
		setMatricula(matricula);
		setSexo(sexo);
	}
	public AlunoEntity getAluno() {
		return aluno;
	}
	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}
	
	/*
	 * QuerySel4  obj.servicoAluno, obj.valorTotalMensalidade
	 * */
	public AlunoEntity(Long id, String nomeAluno, Long turmaId, String turmaDescricao, TipoEducacao tipoEducacao) {
		setId(id);
		setNomeAluno(nomeAluno);
		if (getTurma() == null){
			setTurma(new TurmaEntity());
		}
		getTurma().setId(turmaId);
		getTurma().setDescricao(turmaDescricao);
		setTipoEducacao(tipoEducacao);
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public AlunoEntity(Long id, String matricula, java.util.Date dataCadastro, String nomeAluno, Sexo sexo, java.util.Date dataNascimento) {
		setId(id);
		setMatricula(matricula);
		setDataCadastro(dataCadastro);
		setNomeAluno(nomeAluno);
		setSexo(sexo);
		setDataNascimento(dataNascimento);
	}
	public String getTipoEducacaoStr() {
		if (this.getTipoEducacao()!=null){
			return this.getTipoEducacao().getDescricao();
		}
		return tipoEducacaoStr;
	}
	public void setTipoEducacaoStr(String tipoEducacaoStr) {
		this.tipoEducacaoStr = tipoEducacaoStr;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public List<AlunoEntity> getDadosPorTurma() {
		return dadosPorTurma;
	}
	public void setDadosPorTurma(List<AlunoEntity> dadosPorTurma) {
		this.dadosPorTurma = dadosPorTurma;
	}
	public String getResponsavelFinanceiroStr() {
		return responsavelFinanceiroStr;
	}
	public void setResponsavelFinanceiroStr(String responsavelFinanceiroStr) {
		this.responsavelFinanceiroStr = responsavelFinanceiroStr;
	}
	public String getValorMensalidadeStr() {
		if( this.getValorMensalidadeAluno() != null ){
			valorMensalidadeStr = NumberFormat.getCurrencyInstance().format( this.getValorMensalidadeAluno() );
		}
		return valorMensalidadeStr;
	}
	public void setValorMensalidadeStr(String valorMensalidadeStr) {
		this.valorMensalidadeStr = valorMensalidadeStr;
	}
	public Long getPeriodoContrato() {
		return periodoContrato;
	}
	public void setPeriodoContrato(Long periodoContrato) {
		this.periodoContrato = periodoContrato;
	}
}
