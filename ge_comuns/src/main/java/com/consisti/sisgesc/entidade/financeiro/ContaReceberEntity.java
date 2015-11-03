package com.consisti.sisgesc.entidade.financeiro;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import com.consisti.sisgesc.dominio.BancoSuportado;
import com.consisti.sisgesc.entidade.Aluno;
import com.consisti.sisgesc.entidade.AlunoEntity;
import com.powerlogic.jcompany.comuns.anotacao.PlcIoC;
import com.powerlogic.jcompany.dominio.tipo.PlcSimNao;
import com.consisti.sisgesc.entidade.financeiro.BancoEntity;
import com.consisti.sisgesc.entidade.financeiro.FormaPagamentoEntity;
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
	@NamedQuery(name="ContaReceberEntity.querySel3", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.formaRecebimento.id , obj.formaRecebimento.descricao, obj.valorTotal, obj.dataRecebimento, obj.dataVencimento) from ContaReceberEntity obj left outer join obj.aluno left outer join obj.formaRecebimento order by obj.id asc"),
	@NamedQuery(name="ContaReceberEntity.querySel2", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.banco.id , obj.banco.agencia, obj.valorTotal, obj.dataVencimento) from ContaReceberEntity obj left outer join obj.aluno left outer join obj.banco order by obj.id asc"),
	@NamedQuery(name="ContaReceberEntity.queryMan", query="from ContaReceberEntity obj"),
	@NamedQuery(name="ContaReceberEntity.querySel", query="select new ContaReceberEntity(obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.valorTotal, obj.dataVencimento, obj.dataRecebimento, obj.boletoGerado, obj.recebido, ban.bancoSuportado) from ContaReceberEntity obj left outer join obj.aluno left join obj.banco ban order by obj.dataRecebimento, obj.aluno.nomeAluno, obj.dataVencimento "),
	@NamedQuery(name="ContaReceberEntity.querySelLookup", query="select new ContaReceberEntity (obj.id, obj.aluno) from ContaReceberEntity obj where obj.id = ? order by obj.id asc")
})
public class ContaReceberEntity extends ContaReceber {
 	
	private transient String boletoGeradoStr;
	private transient String recebidoStr;
	
	private transient Boolean geraRemessa;
	
	private transient BancoSuportado bancoSuportadoAux;
	
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

	//obj.id, obj.aluno.id , obj.aluno.nomeAluno, obj.valorTotal, obj.dataVencimento, obj.dataRecebimento, obj.boletoGerado, obj.recebido, obj.banco.bancoSuportado
	public ContaReceberEntity(Long id, Long alunoId, String alunoNome, BigDecimal valorTotal, Date dataVencimento, Date dataRecebimento, PlcSimNao boletoGerado, PlcSimNao recebido, BancoSuportado bancoSuportado) {
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
	
	@Transient
	public String getValorTotalFormatado(){
		if (getValorTotal()!=null){
			return NumberFormat.getCurrencyInstance().format(getValorTotal());
		}
		return "";
	}
	public ContaReceberEntity(Long id, Long alunoId, String nomeAluno, Long formaRecebimentoId, String formaRecebimentoDescricao, java.math.BigDecimal valorTotal, java.util.Date dataRecebimento, java.util.Date dataVencimento) {
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
	}
/**
	 * Utilizado em ContaReceberDAO.recuperaListaContasAReceber
	 * @param id
	 * @param nomeAluno
	 * @param formaRecebimentoDescricao
	 * @param valorTotal
	 */
	public ContaReceberEntity(Long id, BigDecimal valorTotal,  String nomeAluno, String formaRecebimentoDescricao ) {
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
	}
public BancoSuportado getBancoSuportadoAux() {
		return bancoSuportadoAux;
	}
	public void setBancoSuportadoAux(BancoSuportado bancoSuportadoAux) {
		this.bancoSuportadoAux = bancoSuportadoAux;
	}


}
