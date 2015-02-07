package com.consisti.sisgesc.modelo;

import com.consisti.sisgesc.entidade.ContratoEntity;
import com.powerlogic.jcompany.comuns.PlcException;

public class ContratoManager extends AppManager {
	
	public void alteraContrato( ContratoEntity contrato ) throws PlcException{
		getDAOPadrao().altera( contrato );
	}

}
