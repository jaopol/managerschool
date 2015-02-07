package com.consisti.sisgesc.persistencia.hibernate;

import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class ServicoAlunoDAO extends AppBaseDAO {

	
	/**
	 * Por problemas na exclusão do detalhe, foi criado dessa forma
	 * @param id
	 * @throws PlcException
	 */
	public void excluirServicoAluno(Long id) throws PlcException{
		String sql = "DELETE FROM ServicoAlunoEntity obj WHERE obj.id =:id ";
		getSession().createQuery(sql).setLong("id", id).executeUpdate();
	}
	
}
