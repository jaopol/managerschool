package com.consisti.sisgesc.persistencia.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import com.consisti.sisgesc.entidade.FornecedorEntity;
import com.consisti.sisgesc.persistencia.AppBaseDAO;
import com.powerlogic.jcompany.comuns.PlcException;

public class FornecedorDAO extends AppBaseDAO {
	
	/**
	 * Recupera o cpf de acordo
	 * @throws PlcException 
	 */
	public FornecedorEntity recuperaCpf(String cpf) throws PlcException {
		
		Session sess = getSession();
		String hql = "select new FornecedorEntity (obj.cpf, obj.id) from FornecedorEntity obj where obj.cpf = :CPF";
		Query query = sess.createQuery(hql);
		query.setString("CPF", cpf);
		return (FornecedorEntity) query.uniqueResult();
	}

	/**
	 * Recupera o cnpj de acordo
	 * @throws PlcException 
	 */
	public FornecedorEntity recuperaCnpj(String cnpj) throws PlcException {
		Session sess = getSession();
		String hql = "select new FornecedorEntity (obj.cnpj, obj.id) from FornecedorEntity obj where obj.cnpj = :CNPJ";
		Query query = sess.createQuery(hql);
		query.setString("CNPJ", cnpj);
		return (FornecedorEntity) query.uniqueResult();
	}
	
}
