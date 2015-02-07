package com.consisti.sisgesc.modelo.estoque;
        
import com.consisti.sisgesc.dominio.Status;
import com.consisti.sisgesc.entidade.estoque.EstoqueEntity;
import com.consisti.sisgesc.entidade.estoque.Movimento;
import com.consisti.sisgesc.entidade.estoque.MovimentoEntity;
import com.consisti.sisgesc.modelo.AppManager;
import com.consisti.sisgesc.persistencia.hibernate.EstoqueDAO;
import com.powerlogic.jcompany.comuns.PlcBaseVO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class MovimentoManager extends AppManager {

	private EstoqueDAO estoqueDao;
	
	public MovimentoManager(EstoqueDAO estoqueDao){
		this.estoqueDao = estoqueDao;
	}
	
	@Override
	protected void antesPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		MovimentoEntity mov = (MovimentoEntity) entidadeAtual;
		
		if (mov.getEstoque().getId()==null){
			mov.getEstoque().setSaldo(mov.getSaldoTemporario());
			mov.getEstoque().setValorTotalEstoque(mov.getValorEstoqueTemporario());
			mov.getEstoque().setStatus(Status.A);
			estoqueDao.inclui(mov.getEstoque());
		} else {
			EstoqueEntity estoque = (EstoqueEntity) estoqueDao.recupera(EstoqueEntity.class, mov.getEstoque().getId());
			estoque.setSaldo(mov.getSaldoTemporario());
			estoque.setValorTotalEstoque(mov.getValorEstoqueTemporario());
			estoqueDao.altera(estoque);
		}
		
		super.antesPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}
	
	@Override
	protected void aposPersistencia(PlcBaseVO entidadeAtual,
			PlcBaseVO entidadeAnterior, String modoGravacao)
			throws PlcException {
		
		super.aposPersistencia(entidadeAtual, entidadeAnterior, modoGravacao);
	}

}
