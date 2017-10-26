package com.consisti.sisgesc.entidade.financeiro;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.dominio.TipoEducacao;
import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.consisti.sisgesc.entidade.TurmaEntity;
import com.powerlogic.jcompany.comuns.anotacao.PlcIoC;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="CONTA_RECEBER")
@SequenceGenerator(name="SE_CONTA_RECEBER", sequenceName="SE_CONTA_RECEBER")
@AccessType("field")
@PlcIoC(nomeClasseBC="com.consisti.sisgesc.modelo.ContaReceberManager")

@SuppressWarnings("serial")
@NamedQueries({

	@NamedQuery(name="ContaReceberEntity.querySel3", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.formaRecebimento.id , obj.formaRecebimento.descricao, obj.valorTotal, obj.dataRecebimento, obj.dataVencimento, obj.outro, obj.numeroDocumento) from ContaReceberEntity obj left outer join obj.aluno left outer join obj.formaRecebimento where obj.tipoContaReceber <> 'M' order by obj.dataVencimento desc"),
	@NamedQuery(name="ContaReceberEntity.querySel2", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.banco.id , obj.banco.agencia, obj.valorTotal, obj.dataVencimento) from ContaReceberEntity obj left outer join obj.aluno left outer join obj.banco order by obj.id asc"),
	@NamedQuery(name="ContaReceberEntity.queryMan", query="from ContaReceberEntity obj"),
	@NamedQuery(name="ContaReceberEntity.querySel", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.valorTotal, obj.dataVencimento, obj.dataRecebimento, obj.boletoGerado, obj.recebido, ban.bancoSuportado, obj.numeroDocumento) from ContaReceberEntity obj left outer join obj.aluno left join obj.banco ban where obj.tipoContaReceber = 'M' order by obj.dataRecebimento, obj.aluno.nomeAluno, obj.dataVencimento "),
	@NamedQuery(name="ContaReceberEntity.querySelLookup", query="select new ContaReceberEntity (obj.id, obj.aluno) from ContaReceberEntity obj where obj.id = ? order by obj.id asc")
})
public class ContaReceberEntity extends ContaReceber {
	
	final transient SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
 	
	private transient String boletoGeradoStr;
	private transient String recebidoStr;
	
	private transient Boolean geraRemessa;
	
	private transient BancoSuportado bancoSuportadoAux;
	
	private transient String descricaoRecebido;
	private transient String descProdVenda;
	
	private transient TurmaEntity turma;
	private transient TipoEducacao tipoEducacao;
	
	//usado pelo relatorio
	private transient String dataEmissaoStr;
	private transient String dataVencimentoStr;
	private transient String valorTotalFormatado;
	private transient String descricaoCarne;
	
    /*
     * Construtor padrão
     */
    public ContaReceberEntity() {
    }
    /*
     * Métodos GET/SET auxiliares gerados automaticamente pelo jCompany
     */
    public String getAlunoStr() {
    	return getAluno()!=null? getAluno().getIdAux() : null;
    }
    
    public void setAlunoStr(String alunoStr) {
    	if (alunoStr != null && alunoStr.trim().length() > 0) {
    		Long id = Long.valueOf(alunoStr);
    		if (getAluno()==null || !id.equals(getAluno().getId())) {
    			com.consisti.sisgesc.entidade.Aluno obj = new com.consisti.sisgesc.entidade.AlunoEntity();
    			obj.setId(id);
    			this.setAluno(obj);
    		}
    	} else {
    		this.setAluno(null);
    	}
    }

	public ContaReceberEntity(Long id, Aluno aluno) {
		this.setId(id);
		this.setAluno(aluno);
	}
	@Override
	public String toString() {
		return getAlunoStr();
	}

	@SuppressWarnings("unchecked")
	@javax.persistence.Transient
	public Class[] getAgregadosLazyPlc() {
		return new Class[] {com.consisti.sisgesc.entidade.Aluno.class,com.consisti.sisgesc.entidade.financeiro.Banco.class,com.consisti.sisgesc.entidade.financeiro.FormaPagamento.class};
	}

	public ContaReceberEntity(Long id, Long alunoId, String alunoNome, BigDecimal valorTotal, Date dataVencimento, Date dataRecebimento, PlcSimNao boletoGerado, PlcSimNao recebido) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(alunoNome);
		setValorTotal(valorTotal);
		setDataVencimento(dataVencimento);
		setDataRecebimento(dataRecebimento);
		if( PlcSimNao.S.equals( boletoGerado ) ){
			setBoletoGeradoStr( "SIM" );
		}
		else{
			setBoletoGeradoStr( "NÃO" );
		}
		
		if( PlcSimNao.S.equals( recebido ) ){
			setRecebidoStr( "SIM" );
		}
		else{
			setRecebidoStr( "NÃO" );
		}
	}

	//obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.valorTotal, obj.dataVencimento, obj.dataRecebimento, obj.boletoGerado, obj.recebido, obj.banco.bancoSuportado, obj.numeroDocumento
	public ContaReceberEntity(Long id, Long alunoId, String alunoNome, BigDecimal valorTotal, Date dataVencimento, Date dataRecebimento, PlcSimNao boletoGerado, PlcSimNao recebido, BancoSuportado bancoSuportado, String numeroDocumento) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(alunoNome);
		setValorTotal(valorTotal);
		setDataVencimento(dataVencimento);
		setDataRecebimento(dataRecebimento);
		if( PlcSimNao.S.equals( boletoGerado ) ){
			setBoletoGeradoStr( "SIM" );
		}
		else{
			setBoletoGeradoStr( "NÃO" );
		}
		
		if( PlcSimNao.S.equals( recebido ) ){
			setRecebidoStr( "SIM" );
		}
		else{
			setRecebidoStr( "NÃO" );
		}
		setBancoSuportadoAux(bancoSuportado);
		setNumeroDocumento(numeroDocumento);
		
	}
	
	public String getBoletoGeradoStr() {
		return boletoGeradoStr;
	}
	public void setBoletoGeradoStr(String boletoGeradoStr) {
		this.boletoGeradoStr = boletoGeradoStr;
	}
	public String getRecebidoStr() {
		return recebidoStr;
	}
	public void setRecebidoStr(String recebidoStr) {
		this.recebidoStr = recebidoStr;
	}
	//querySel2
	public ContaReceberEntity(Long id, Long alunoId, String nomeAluno, Long bancoId, String bancoAgencia, java.math.BigDecimal valorTotal, java.util.Date dataVencimento) {
		setGeraRemessa(false);
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(nomeAluno);
		if (getBanco() == null){
			setBanco(new BancoEntity());
		}
		getBanco().setId(bancoId);
		getBanco().setAgencia(bancoAgencia);
		setValorTotal(valorTotal);
		setDataVencimento(dataVencimento);
	}
	
	public Boolean getGeraRemessa() {
		return geraRemessa;
	}
	public void setGeraRemessa(Boolean geraRemessa) {
		this.geraRemessa = geraRemessa;
	}
	
	public String getValorTotalFormatado(){
		if (getValorTotal()!=null){
			return NumberFormat.getCurrencyInstance().format(getValorTotal());
		}
		return valorTotalFormatado;
	}

	//querysel3
	public ContaReceberEntity(Long id, Long alunoId, String nomeAluno, Long formaRecebimentoId, String formaRecebimentoDescricao, java.math.BigDecimal valorTotal, java.util.Date dataRecebimento, java.util.Date dataVencimento, String outro, String numeroDocumento) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setId(alunoId);
		getAluno().setNomeAluno(nomeAluno);
		if (getFormaRecebimento() == null){
			setFormaRecebimento(new FormaPagamentoEntity());
		}
		getFormaRecebimento().setId(formaRecebimentoId);
		getFormaRecebimento().setDescricao(formaRecebimentoDescricao);
		setValorTotal(valorTotal);
		setDataRecebimento(dataRecebimento);
		setDataVencimento(dataVencimento);
		setOutro(outro);
		setNumeroDocumento(numeroDocumento);
	}

/**
	 * Utilizado em ContaReceberDAO.recuperaListaContasAReceber
	 * @param id
	 * @param nomeAluno
	 * @param formaRecebimentoDescricao
	 * @param valorTotal
	 */
	public ContaReceberEntity(Long id, BigDecimal valorTotal,  String nomeAluno, String formaRecebimentoDescricao, String outro ) {
		setId(id);
		if (getAluno() == null){
			setAluno(new AlunoEntity());
		}
		getAluno().setNomeAluno(nomeAluno);
		if (getFormaRecebimento() == null){
			setFormaRecebimento(new FormaPagamentoEntity());
		}
		getFormaRecebimento().setDescricao(formaRecebimentoDescricao);
		setValorTotal(valorTotal);
		setOutro(outro);
	}
	
	/*
	 * recuperarAllContaReceber
	 * obj.aluno.nomeAluno, " );
		str.append( " obj.outro, " );
		str.append( " obj.dataVencimento, " );
		str.append( " obj.dataRecebimento, " );
		str.append( " obj.valorTotal, " );
		str.append( " produtoVenda.descricao, " );
		str.append( " produtoVenda.descricao,
		str.append( " venda.valorTotal " );
	 * *\
	 */
	public ContaReceberEntity(String nomeAluno, String outro, BigDecimal valorTotal, String prodVenda, Date dataVencimento, Date dataRecebimento, BigDecimal valorTotalVenda) {
		if( getAluno() == null ){
			setAluno(new AlunoEntity());
		}
		getAluno().setNomeAluno(nomeAluno);
		setOutro(outro);
		if( valorTotalVenda == null ){
			setValorTotal(valorTotal);
		}else{
			setValorTotal(valorTotalVenda);
		}
		setDescProdVenda(prodVenda);
		setDataVencimento(dataVencimento);
		setDataRecebimento(dataRecebimento);
	}
	
	
	public BancoSuportado getBancoSuportadoAux() {
		return bancoSuportadoAux;
	}
	public void setBancoSuportadoAux(BancoSuportado bancoSuportadoAux) {
		this.bancoSuportadoAux = bancoSuportadoAux;
	}
	public String getDescricaoRecebido() {
		if( getAluno() != null && getAluno().getNomeAluno() != null ){
			return getAluno().getNomeAluno();
		}
		else{
			return getOutro();
		}
	}
	
	public void setDescricaoRecebido(String descricaoRecebido) {
		this.descricaoRecebido = descricaoRecebido;
	}
	public String getDescProdVenda() {
		return descProdVenda;
	}
	public void setDescProdVenda(String descProdVenda) {
		this.descProdVenda = descProdVenda;
	}
	public TurmaEntity getTurma() {
		return turma;
	}
	public void setTurma(TurmaEntity turma) {
		this.turma = turma;
	}
	public TipoEducacao getTipoEducacao() {
		return tipoEducacao;
	}
	public void setTipoEducacao(TipoEducacao tipoEducacao) {
		this.tipoEducacao = tipoEducacao;
	}
	public String getDataEmissaoStr() {
		if( getDataDocumento() != null ){
			return sdf_ddMMyyyy.format(getDataDocumento());
		}
		return dataEmissaoStr;
	}
	public void setDataEmissaoStr(String dataEmissaoStr) {
		this.dataEmissaoStr = dataEmissaoStr;
	}
	public String getDataVencimentoStr() {
		if(getDataVencimento() != null ){
			return sdf_ddMMyyyy.format( getDataVencimento() );
		}
		return dataVencimentoStr;
	}
	public void setDataVencimentoStr(String dataVencimentoStr) {
		this.dataVencimentoStr = dataVencimentoStr;
	}
	public String getDescricaoCarne() {
		return descricaoCarne;
	}
	public void setDescricaoCarne(String descricaoCarne) {
		this.descricaoCarne = descricaoCarne;
	}
	
}
