package com.consisti.sisgesc.persistencia.hibernate;

import java.util.ArrayList;

import com.consisti.sisgesc.entidade.financeiro.TipoPlanoContasEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class TipoPlanoContaDAO extends AppBaseDAO {

	
	/**
	 * Recupera a lista de Tipo Plano Contas pelo id do Plano Contas
	 * @param idPlanoConta
	 * @return List
	 * @throws PlcException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TipoPlanoContasEntity> recuperaListaTipoPlanoContaByIdPlanoConta(Long idPlanoConta) throws PlcException {
		
		StringBuffer hql = new StringBuffer();
		hql.append( "SELECT new TipoPlanoContasEntity ( obj.id, " );
		hql.append( " obj.descricao ) " );
		hql.append(	" FROM TipoPlanoContasEntity obj " );
		hql.append( " WHERE obj.planoContas.id =:idPlanoConta " );
		
		return (ArrayList<TipoPlanoContasEntity>) getSession().createQuery( hql.toString() ).setLong("idPlanoConta", idPlanoConta).list();
	}

	
	
}
