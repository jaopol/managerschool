package com.consisti.sisgesc.modelo.estoque;
        
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.consisti.sisgesc.entidade.estoque.EstoqueEntity;
import com.consisti.sisgesc.entidade.estoque.Movimento;
import com.consisti.sisgesc.entidade.estoque.MovimentoEntity;
import com.consisti.sisgesc.modelo.AppManager;
import com.consisti.sisgesc.persistencia.hibernate.EstoqueDAO;
import com.consisti.sisgesc.persistencia.hibernate.MovimentoDAO;
import com.powerlogic.jcompany.comuns.PlcException;


/**
 * Classe de Modelo gerada pelo assistente
 */
public class EstoqueManager extends AppManager {

	private EstoqueDAO estoqueDao;
	private MovimentoDAO movimentoDao;
	
	public EstoqueManager(EstoqueDAO estoqueDao, MovimentoDAO movimentoDao){
		this.estoqueDao = estoqueDao;
		this.movimentoDao = movimentoDao;
	}
	
	@Override
	protected void recuperaListaQBEApos(Class classe, String selectQBE,
			List argsQBE, List lista) throws PlcException {
		
		for (Object object : lista) {
			EstoqueEntity estoque = (EstoqueEntity) object;
			List<Movimento> movimentos = movimentoDao.recuperaMovimento(estoque.getId());
			for (Movimento movimento : movimentos) {
				movimento.setEstoque(estoque);
			}
			estoque.setMovimento(movimentos);
		}
		
		super.recuperaListaQBEApos(classe, selectQBE, argsQBE, lista);
	}

}
