// jCompany 3.0 : Arquivo Javascript específico da aplicação.
// É inserido e disponibilizado em todas as páginas

/**
*	Envia mensagem de confirmação do jquery.
*	Os parametros deve ser passados no formato jquery	
*
*   @param div - nome div a ser apresentada
*	@param acaoSim - id do botão para ação SIM
*	@param acaoNao - id do botão para ação NAO
*
**/
function mensagemConfirmacao(div,acaoSim,acaoNao){

    jQuery(div).dialog({
          modal: true,
          resizable: false,
          width: 300,
          buttons: {
            "N\xE3o" : function() {
        		if(acaoNao != null){
        			jQuery(acaoNao).click();
        		}
                jQuery(this).dialog("close");
            },
            "Sim": function() {
            	if(acaoSim != null){
            		jQuery(acaoSim).click();
            	}
                jQuery(this).dialog("close");
            }
          }
    });

}

/**
 * Exibe confirmação para exclusão nas tabulares.
 * 
 * @return
 */
function confirmaExclusaoTabular(){
	try{
		//verifica se pergunta
		var pergunta = false;
		var i = 0;
		while(document.getElementById("corpo:formulario:plcLogicaItens:"+i+":indExcPlc") != null ){
			if(document.getElementById("corpo:formulario:plcLogicaItens:"+i+":indExcPlc").checked){
				pergunta = true;
				break;
			}
			i++;
		}
		//pergunta
		if(pergunta){
			// Cria o jQuery UI Dialog
		    var divConfirmacaoSimNao = jQuery(
		        '<div title="Confirma" >Deseja excluir o(s) registro(s) selecionado(s)?</div>'
		    );
		    mensagemConfirmacao(divConfirmacaoSimNao,"#corpo\\:formulario\\:gravarDireto",null);
			return false;
		}else{
			return true;
		}
	}catch(e){
		alert(e.message);
	}
}

/**
 * Exibe confirmação para exclusão dos detalhes.
 * 
 * @return
 */
function confirmaExclusaoDetalhe(detalhe){
	try{
		//verifica se pergunta
		var pergunta = false;
		var i = 0;
		while(document.getElementById("corpo:formulario:"+detalhe+":"+i+":indExcPlc") != null ){
			if(document.getElementById("corpo:formulario:"+detalhe+":"+i+":indExcPlc").checked){
				pergunta = true;
				break;
			}
			i++;
		}
		//pergunta
		if(pergunta){
			// Cria o jQuery UI Dialog
		    var divConfirmacaoSimNao = jQuery(
		        '<div title="Confirma" >Deseja excluir o(s) registro(s) selecionado(s)?</div>'
		    );
		    mensagemConfirmacao(divConfirmacaoSimNao,"#corpo\\:formulario\\:gravarDireto",null);
			return false;
		}else{
			return true;
		}
	}catch(e){
		alert(e.message);
	}
}

/**
 * Exibe confirmação para exclusão dos detalhes.
 * 
 * @return
 */
function confirmaExclusaoDetalheAlterado(detalhe){
	try{
		//verifica se pergunta
		var pergunta = false;
		var i = 0;
		while(document.getElementById("corpo:formulario:"+detalhe+":"+i+":exclusao") != null ){
			if(document.getElementById("corpo:formulario:"+detalhe+":"+i+":exclusao").checked){
				pergunta = true;
				break;
			}
			i++;
		}
		//pergunta
		if(pergunta){
			// Cria o jQuery UI Dialog
		    var divConfirmacaoSimNao = jQuery(
		        '<div title="Confirma" >Deseja excluir o(s) registro(s) selecionado(s)?</div>'
		    );
		    mensagemConfirmacao(divConfirmacaoSimNao,"#corpo\\:formulario\\:gravarDireto",null);
			return false;
		}else{
			return true;
		}
	}catch(e){
		alert(e.message);
	}
}


/**
 * Exibe confirmação para exclusão dos detalhes.
 * 
 * @return
 */
function confirmaExclusao(msg){
	try{
		// Cria o jQuery UI Dialog
	    var divConfirmacaoSimNao = jQuery(
	        '<div title="Confirma" >'+msg+'</div>'
	    );
	    mensagemConfirmacao(divConfirmacaoSimNao,"#corpo\\:formulario\\:excluirDireto",null);
		return false;
	}catch(e){
		alert(e.message);
	}
}

/**
 * Exibe confirmação para gravação.
 * 
 * @return
 */
function confirmaGravacaoEmLote(msg){
	try{
		// Cria o jQuery UI Dialog
	    var divConfirmacaoSimNao = jQuery(
	        '<div title="Confirma" >'+msg+'</div>'
	    );
	    mensagemConfirmacao(divConfirmacaoSimNao,
	    		"#corpo\\:formulario\\:gravarDireto",
	    		"#corpo\\:formulario\\:negaGravacaoPorLote");
		return false;
	}catch(e){
		alert(e.message);
	}
}


/*
* Exibe confirmação de exclusão do registro todo.
*/
function execConfirmacaoGravacaoEmLote(){
	try{
		if(document.getElementById("corpo:formulario:exibeMsgConfirmacaoGravacaoLote").value == "S"){
			
				var retorno = confirmaGravacaoEmLote(document.getElementById("corpo:formulario:msgConfirmacaoGravacaoLote").value);
				//return retorno;
			
		}
	}catch(e){
		//alert(e.message);
		//retira,para que outras funções sejam chamadas
	}
}



/**
* Fun??es para capturar tecla pressionada e executar a??o associada ? tecla
* @param nomeBotao Nome do bot?o que atender? ao evento
* @param evento Evento associado ao bot?o
* @variable disparouBotao Indicador de bot?o disparado
* @variable disparouEnter Indicador de tecla ENTER pressionada
* @variable interval Intervalo de tempo utilizado para execu??o de a??o<br>
*			associada ? tecla ENTER
* @see testaEventoFuncoes
* @see executarAcaoFuncoes
* @see executarEventoAplicacao
* @see selBotao
* @see getBotaoArray
* @see disparaBotao
*/
var disparouBotao = false;
var disparouEnter = false;
var qtdePassou = 0;
function executarAcaoFuncoes(evt)
{
	clearInterval(interval);
	plcLog.logEscondeErros();
	
	var elementoOrigem;
	if(evt){
		if(NavYes)
			elementoOrigem = evt.target;
		else
			elementoOrigem = evt.srcElement;
	}
	if(plcAjax.AJAX_ATIVO){
		return false;
	}

	var acao = "";
	var botaoAcaoAux = botaoAcao;
	botaoAcao = "";
	if (botaoAcaoAux == "")
		acao = testaEventoFuncoes(evt);
	else
		acao = botaoAcaoAux;
	
	
	//para parar a ação do botão F12
	//que estava passando duas vezes por este local
	if(acao == "botaoAcaoGravar" && qtdePassou == 1){
		qtdePassou = 0;
		return false;
	}
	if(acao == "botaoAcaoGravar" && qtdePassou == 0){
		qtdePassou = 1;
	}
	
	
	
	if(acao == "TAB" || acao == "ERRO" || acao == "ESCAPE")
		return true;

	if(acao == "ENTER" && botaoAcaoEnter != "")
	{
		if(elementoOrigem.type != "textarea")
		{
			botaoAcao = botaoAcaoEnter;
			if(getVarGlobal("trSelecao") == null && !plcGeral.MENU_ATIVO)
				interval = setInterval("executarAcaoFuncoes()",100);
			return false;
		}
	}

	if(!disparouBotao && !executarEventoAplicacao(acao))
		return false;
	else
		disparouBotao = false;
	var botao = eval(selBotao(acao));

	if(botao != null)
	{
		disparouBotao = true;
		disparaBotao(botao);
	}else
		return true;
}

/*
 * Chama a funcao execmascara apos um segundo
 */
function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}


/*
 * Chama a mascara de acordo com o tipo
 */
function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

/*
 * Monta mascara da versão do coletor.
 */
function versaoColetor(v){
	v=v.replace(/\D/g,"");
	if( v.length == 2) {	
		v=v.replace(/(\d{1})(\d{1,2})$/,"$1.$2");
	}else if (v.length >= 3) {	
		v = v.substring(0,2)+"."+v.substring(2,3);
	}		
	return v;
}

/*
 * Mascara para campos somente numeros
 */
function numerico(v) {
    return v.replace(/\D/g, "");
}

/***INICIO: DESABILITA ARRASTA***/
var DesabilitarArrasta = {
	enable : function(e) {
    	var e = e ? e : window.event;
		
		if (e.button != 1) {
	   		if (e.target) {
	      		var targer = e.target;
	   		} else if (e.srcElement) {
	      		var targer = e.srcElement;
            }
            
			var targetTag = targer.tagName.toLowerCase();
			
		  	if ((targetTag != "input") && (targetTag != "textarea")) {
		  			document.getElementById("plc-topo-menu-link2").focus();
					return false;
			  	}else{
			  		return true;
			  	}
	     	}
	   	},
	   		
	    disable : function () {
			return true;
	    }
	}
	
   function desabilitarArraste() {
      if (typeof(document.onselectstart) != "undefined") {
  
	document.onselectstart = DesabilitarArrasta.enable;
	
  } else {
	document.onmousedown = DesabilitarArrasta.enable;
	document.onmouseup = DesabilitarArrasta.disable;
  }
}

setFuncaoOnLoad("desabilitarArraste()");
/***FIM: DESABILITA***/

function autoRecuperacaoVinculado(vinculado, propRecupera){	
		
	 if (ajaxUsa != "false") {
	 	TrPage._autoSubmit('corpo:formulario', vinculado, window.event, 1, {partialVinculado: vinculado});
	 	setTimeout (function () { jQuery(getRootDocument().getElementById("lookup_" + vinculado)).focus(); }, 500);
		setTimeout (function () { jQuery(getRootDocument().getElementById("lookup_" + vinculado)).select(); }, 500);
	 } else {
	 	submitForm('corpo:formulario', 1, {source:vinculado});
	 }
	
	 //formata o campo id do vinculado	 
	setTimeout(function () { jQuery(getRootDocument().getElementById(vinculado)).numeric(false, null); }, 1000); 
	setTimeout(function () { jQuery(getRootDocument().getElementById(vinculado)).attr('maxlength',5); }, 1000);
	

};

function limpaDataAntes(){
	
	if(jQuery('#corpo\\:formulario\\:dataJustificativa').val() == '__/__/____'){
    	jQuery('#corpo\\:formulario\\:dataJustificativa').val('');
    }
	return true;
}


function setaValorDesprezar(campoValor, campoDesprezar, detalhe, indice){
	try{
		if(document.getElementById("corpo:formulario:"+detalhe+":"+indice+":"+campoValor).value != null)
			if(document.getElementById("corpo:formulario:"+detalhe+":"+indice+":"+campoValor).value != "")
				document.getElementById("corpo:formulario:"+detalhe+":"+indice+":"+campoDesprezar).value = indice + "";
			else
				document.getElementById("corpo:formulario:"+detalhe+":"+indice+":"+campoDesprezar).value = null;
		else
			document.getElementById("corpo:formulario:"+detalhe+":"+indice+":"+campoDesprezar).value = null;
	}catch(e){
		alert(e.message);
	}
	
}

/**
 * Abre popup do UC027 no UC009
 * @return
 */
function abrirImportarPodutos(){

	//se houve alguma alteração na tela pergunta 
	//se quer gravar antes de continuar
	try{
		if(document.getElementById("corpo:formulario:alertaAlteracaoPlc") != null 
				&& document.getElementById("corpo:formulario:alertaAlteracaoPlc").value == "S"){
			
			var divConfirmacaoSimNao = jQuery(
			        "<div title='Confirma' >Deseja salvar as altera\xE7\xF5es?</div>"
		    );
			//alert("entrei");
			//não aproveitado o que já tinha pois 
			//era necessário retornar.
			jQuery(divConfirmacaoSimNao).dialog({
		          modal: true,
		          resizable: false,
		          width: 300,
		          buttons: {
		            "N\xE3o" : function() {
						//se não quer gravar, abre a tela de importação
						//alert("nao");
						document.getElementById("corpo:formulario:limpaImportar").click();
		                jQuery(this).dialog("close");
		                return false;
		            },
		            "Sim": function() {
		            	//grava e abre a tela de importação logo após
		            	//alert("sim");
		            	document.getElementById("corpo:formulario:gravarImportar").click();
		                jQuery(this).dialog("close");
		                return false;
		            }
		          }
		    });
			
		}else{
			//abre tela se não teve alteração
			abreTelaImportacao();
		}
		return false;
	}catch(e){
		alert(e.message);
	}
	
}

/**
 * 
 * @return
 */
function abreTelaImportacao(){
	var idColecao = document.getElementById("corpo:formulario:idColecao").value;
	document.getElementById("corpo:formulario:fechouImportacao").value = "S";
	selecaoModalImportacao('/irma/f/n/ger/importarprodutoscolecaocon?&amp;idColecao='+idColecao+'&amp;modoJanelaPlc=modal&amp;delimPropsPlc=,',
			'','','920','550','10','10','importarprodutoscolecaocon',',');
}

/**
 * Criado para ser usado na tela de importação (UC027)
 *
 * @param url
 * @param listaCampos
 * @param separador
 * @param larg
 * @param alt
 * @param posX
 * @param posY
 * @param alvo
 * @param delimPropsPlc
 * @return
 */
function selecaoModalImportacao(url, listaCampos, separador, larg, alt, posX, posY, alvo, delimPropsPlc)
{
	// Registra os campos de retorno, no escopo da janela que pediu o Modal
	// Window!
	window.camposRetorno = registrarCamposRetorno(listaCampos, "nome,id", separador);
	if (window.camposRetorno) {
		window.camposRetorno.delimPropsPlc = delimPropsPlc;
	}
	// Cria a Janela Modal!
	var dialog = jQuery.irma.janelaModalImportacao({
		title: 'Importa&ccedil;&atilde;o de Produtos'
		,url: url.replace('modal','popup')
		,width: (larg != '' ? parseInt(larg) : '')
		,height: (alt != '' ? parseInt(alt) : '')
	});
}

/**
 * Criado para ser usado na tela de importação (UC027)
 *
 * @param url
 * @param listaCampos
 * @param separador
 * @param larg
 * @param alt
 * @param posX
 * @param posY
 * @param alvo
 * @param delimPropsPlc
 * @return
 */
function selecaoModalRelatorio(url, listaCampos, separador, larg, alt, posX, posY, alvo, delimPropsPlc)
{
	// Registra os campos de retorno, no escopo da janela que pediu o Modal
	// Window!
	window.camposRetorno = registrarCamposRetorno(listaCampos, "nome,id", separador);
	if (window.camposRetorno) {
		window.camposRetorno.delimPropsPlc = delimPropsPlc;
	}
	// Cria a Janela Modal!
	var dialog = jQuery.plc.janelaModal({
		title: 'Relat\xF3rio'
		,url: url.replace('modal','popup')
		,width: (larg != '' ? parseInt(larg) : '')
		,height: (alt != '' ? parseInt(alt) : '')
	});
}

jQuery.irma = { 
/*
 * Cria uma nova Janela Dialog do jQuery UI. Recebe um objeto de
 * configuração, que deve ter como parâmetro, a url e o tamanho da janela. {
 * url: '...' ,width: ... ,height: ... } @param Objeto de configuração com
 * URL. @return Retorna o Objeto jQuery Dialog.
 */
janelaModalImportacao: function(c, dialogOpener){
	
	// Possibilita recursividade!
	if (window.parent && window.parent != window) {
		var funcaoModal = window.parent.jQuery && window.parent.jQuery.plc && window.parent.jQuery.plc.janelaModal;
		if (funcaoModal)
			return window.parent.jQuery.irma.janelaModalImportacao(c, (dialogOpener || window));
	}
	
	var defaultWidth = 720, defaultHeight = 480;
	
	//verificando se já existe uma janela modal
	var janelas = jQuery('div[id^=plc-modal]');
	if (jQuery('div[id^=plc-modal]').length == 0) {
		id_modal = 'plc-modal';
	} else if (janelas.length > 0) {
		var id_modal = /(\d)/.exec(jQuery('div[id^=plc-modal]')[janelas.length -1].id);
		if (id_modal) {
			id_modal = 'plc-modal' + (parseInt(id_modal[0]) + 1);	
		} else {
			id_modal = 'plc-modal2';	
		}
	}	
	
	// Cria o jQuery UI Dialog com iFrame!
	var dialog = jQuery(
		'<div id="' + id_modal + '"'+
		'title="'+ (c.title||'') +'" '+
			'style="padding: 0px; margin: 0px; width: '+(c.width || defaultWidth)+'px; height: '+(c.height || defaultHeight)+'px; display: block;"'+
		'>'+
			'<iframe src="' + c.url + '" style="display: none; width: 99%; height: 99%; border: none;"></iframe>'+
		'</div>'
	);
	
	dialog.dialog({
		modal: true
		,width: (c.width || defaultWidth)
		,height: (c.height || defaultHeight)
		//,hide: 'closeText'
		// ,hide: 'slide'
		,open: function(){
			if (c.onopen){
				c.onopen(c, dialogOpener);
			}
			dialog.children('iframe').show();
			/*
			 * retira o botão fechar da tela
			 */
			$(".ui-dialog-titlebar-close").hide(); 
		}
		,dragStart: function(){
			if (c.ondragstart){
				c.ondragstart(c, dialogOpener);
			}
			dialog.children('iframe').hide();
		}
		,resizeStart: function(){
			if (c.onresizestart){
				c.onresizestart(c, dialogOpener);
			}
			dialog.children('iframe').hide();
		}
		,dragStop: function(){
			if (c.ondragstop){
				c.ondragstop(c, dialogOpener);
			}
			dialog.children('iframe').show();
		}
		,resizeStop: function(){
			if (c.onresizestop){
				c.onresizestop(c, dialogOpener);
			}
			dialog.children('iframe').show();
		}
		,beforeClose: function(){
			if (c.onbeforeclose){
				c.onbeforeclose(c, dialogOpener);
			}
			dialog.children('iframe').hide();
		}
		,close: function(){
			if (c.onclosed){
				c.onclosed(c, dialogOpener);
			}
			dialog.dialog('destroy');
		}
	});
	
	// Injeta no iFrame o dialogOpener, e a funcao dialogClose!
	dialog.children('iframe').bind('load', function(){
		var iWindow = jQuery(this).attr('contentWindow');
		iWindow.dialogOpener = dialogOpener || window;
		iWindow.dialogClose = function(){
			dialog.dialog('close');
			dialog.children('iframe').unbind('load');
		};
	});
	
	return dialog;
}

}


/**
 * Encontra a linha do detalhe.
 * 
 * @return
 */
function encontraLinhaAtualDet(){
	var indices = jQuery.plc.componenteFoco.id.split(":");
	var linha = indices[3] ;
	return linha;
}

/**
 * Encontra a linha do detalhe.
 * 
 * @return
 */
function encontraLinhaAtualDetComId(id){
	var indices = id.split(":");
	var linha = indices[3] ;
	return linha;
}

/**
 * Exibe a mensagem de confirmação do produto.
 * Adicionado aqui por o div é apresentado toda hora que a página é renderizada.
 * Pertence a página 'cadastrarprodutoscolecaoMad.xhtml'
 * 
 * @return
 */
function executaMensagemValidacao(){

	try{
		if(document.getElementById("corpo:formulario:executaMensagemValidacao").value == "S"){
			document.getElementById("corpo:formulario:executaMensagemValidacao").value = "N";
			
			var codigoProduto = document.getElementById("corpo:formulario:codigoDescricaoProdutoInvalido").value;
			
		 	var divConfirmacaoSimNao = jQuery(
			        "<div title='Confirma' >Deseja incluir o produto '"+codigoProduto+"', " +
			        "mesmo que este n\xE3o conste em todas as lojas da cole\xE7\xE3o?</div>"
		    );
		    mensagemConfirmacao(divConfirmacaoSimNao,
		    		"#corpo\\:formulario\\:btAprovaProduto",
		    		"#corpo\\:formulario\\:btNegarProduto");
		}
	}catch(e){
		//alert(e.message);	
		//retira,para que outras funções sejam chamadas
	}
	
}

/**
 * Abre a tela de qualquer relatório.
 * 
 * @return
 */
function abreTelaRelatorio(){
	try{
		if(document.getElementById("corpo:formulario:abrirPlcVis").value == "S"){
			document.getElementById("corpo:formulario:abrirPlcVis").value = "N";
			selecaoModalRelatorio('/irma/relatorio?&amp;modoJanelaPlc=modal&amp;delimPropsPlc=,',
					'','','1050','550','10','10','relatorio',',');
		}
	}catch(e){
		//alert(e.message);
		//retira,para que outras funções sejam chamadas
	}
}

/**
 * Abre a tela de importação UC027.
 * @return
 */
function abreTelaImportacaoRefresh(){
	try{
		if(document.getElementById("corpo:formulario:abreImportacao") != null){
			if(document.getElementById("corpo:formulario:abreImportacao").value == "S"){
				document.getElementById("corpo:formulario:abreImportacao").value = "N";
				abreTelaImportacao();
			}
		}
	}catch(e){
		//alert(e.message);
		//retira,para que outras funções sejam chamadas
	}
	
}

/*
 * Montar máscara de telefone com DDD
 */
function telefoneDDD(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})(\d)/, "($1)$2"); // Coloca hífen entre o segundo e o
	
	if (v.length < 13){
		v = v.replace(/(\d{4})(\d)/, "$1-$2"); // Coloca hífen entre o quarto e o
	}
	else if (v.length == 13){//9 digitos
		v = v.replace('-','');
		v = v.replace(/(\d{5})(\d)/, "$1-$2"); // Coloca hífen entre o quarto e o
		//navegarParaProximoCampo();
	}

	return v;
}


/*
 * Monta máscara de cpf
 */
function cpf(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{3})(\d)/, "$1.$2") // Coloca um ponto entre o terceiro
											// e o quarto dígitos
	v = v.replace(/(\d{3})(\d)/, "$1.$2") // Coloca um ponto entre o terceiro
											// e o quarto dígitos
	// de novo (para o segundo bloco de números)
	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2") // Coloca um hífen entre o
												// terceiro e o quarto dígitos
	return v
}

function cnpj(v) {
	v = v.replace( /\D/g , ""); //Remove tudo o que não é dígito
	v = v.replace( /^(\d{2})(\d)/ , "$1.$2"); //Coloca ponto entre o segundo e o terceiro dígitos
	v = v.replace( /^(\d{2})\.(\d{3})(\d)/ , "$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
	v = v.replace( /\.(\d{3})(\d)/ , ".$1/$2"); //Coloca uma barra entre o oitavo e o nono dígitos
	v = v.replace( /(\d{4})(\d)/ , "$1-$2"); //Coloca um hífen depois do bloco de quatro dígitos
	return v;
}


/*
 * Montar máscara de cep
 */
function cep(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})(\d)/, "$1.$2") 
	v = v.replace(/(\d{3})(\d)/, "$1-$2")

	if (v.length == 10)
		navegarParaProximoCampo();

	return v;
}


/*
 * Montar máscara de telefone com DDD
 */
function hora(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})(\d)/, "$1:$2") // Coloca hífen entre o segundo e o
	
	if( v > 23 )
		v = "";
	
	return v
}

/*
 * Montar máscara de telefone com DDD
 */
function idadeTurma(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})(\d)/, "$1-$2") // Coloca hífen entre o segundo e o
	
	return v;
}

/*
 * Montar máscara de telefone com DDD
 */
function mesDia(v) {
	v = v.replace(/\D/g, "") // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})(\d)/, "$1-$2") // Coloca hífen entre o segundo e o
	
	v1 = v.split("-");
	if( v1[0] > 31){
		v = ""
	}
	if( v1[1] > 12){
		v = ""
	}
	
	return v;
}

/*---------------------------------------------------------------------------*\
  jCompany 2.5.7 Configura barra de progresso
\*---------------------------------------------------------------------------*/
// xp_progressbar
// Copyright 2004 Brian Gosselin of ScriptAsylum.com
// v1.0 - Initial release
// v1.1 - Added ability to pause the scrolling action (requires you to assign
//        the bar to a unique arbitrary variable).
//      - Added ability to specify an action to perform after a x amount of
//      - bar scrolls. This requires two added arguments.
// v1.2 - Added ability to hide/show each bar (requires you to assign the bar
//        to a unique arbitrary variable).
// var xyz = createBar(total_width,total_height,background_color,border_width,border_color,block_color,scroll_speed,block_count,scroll_count,action_to_perform_after_scrolled_n_times)
// var xyz = createBar(LARGURA,ALTURA,COR FUNDO,TAMANHO BORDA,COR BORDA,COR BLOCOS,VELOCIDADE,NUMERO BLOCOS,NUMERO VEZES,ENDERE?O PARA EXECUTAR APOS FIM, MENSAGEM)
var w3c=(document.getElementById)?true:false;
var ie=(document.all)?true:false;
var N=-1;
var barraProgressoAjax;
//var deslocWidthBar = (ie) ? 10 : 5;
//var deslocHeightBar = (ie) ? 90 : 125;
var deslocWidthBar = (ie) ? 30 : 20;
var deslocHeightBar = (ie) ? 100 : 135;
function createBar(w,h,bgc,brdW,brdC,blkC,speed,blocks,count,action,msg){
	var centroBar 	= getPosicaoCentro(w,h);
	var tamWindow	= getTamanhoWindow();
	var centroFundo = getPosicaoCentro(tamWindow.tamX, tamWindow.tamY);
	//alert((parseInt(tamWindow.tamX)) +","+ (parseInt(tamWindow.tamY)))
	//alert(window.offsetHeight);
	/**
	 * Calculo da posicao central independente do tamanho e tipo de resolucao (fullframe ou widescreen)
	 * @author Rodrigo Valério
	 * @since jCompany 5.0
	 */
	 var margemEsquerda = w/2;
	 var margemSuperior = h/2;
	 
	if(ie||w3c){
		var t='';
		//t+='<a href="#" onclick="barraProgresso(); return false;">Barra</a>';
		t+='<div id="fundoBar" style="background-color:lavender; z-index:200; display:none; position:absolute; top:0; left:0; width:'+(tamWindow.tamX - deslocWidthBar)+'px; height:'+(tamWindow.tamY - deslocHeightBar)+'px;'
		t+=(ie)?'filter:alpha(opacity='+50+')':'-Moz-opacity:'+(0.5);
		t+='"></div>';
		//t+='<div id="barraProgresso" style="visibility:visible; z-index:300; position:absolute; top:'+centroBar.moveCentroX+'; left:'+centroBar.moveCentroY+';">';
		t+='<div id="barraProgresso" style="visibility:visible; z-index:300; position:absolute; top:50%; left:50%; margin-left:-'+margemEsquerda+'px; margin-top:-'+margemSuperior+'px;">';
		t+='<div id="_xpbar'+(++N)+'" style="display:none; position:relative; overflow:hidden; width:'+w+'px; height:'+h+'px; background-color:'+bgc+'; border-color:'+brdC+'; border-width:'+brdW+'px; border-style:solid; font-size:1px;">';
		t+='<span id="blocks'+N+'" style="left:-'+(h*2+1)+'px; position:absolute; font-size:1px">';
		for(i=0; i<blocks; i++){
			t+='<span style="background-color:'+blkC+'; left:-'+((h*i)+i)+'px; font-size:1px; position:absolute; width:'+h+'px; height:'+h+'px; '
			t+=(ie)?'filter:alpha(opacity='+(100-i*(100/blocks))+')':'-Moz-opacity:'+((100-i*(100/blocks))/100);
			t+='"></span>';
		}
		//t+='</span><span style="vertical-align:\'middle\';"><table border="0"><tr><td style="vertical-align:\'middle\';'+estiloFonte+'; width:'+w+'px; height:'+h+'px;text-align:\'center\';"><center><b>'+msg+'</b></center></td></tr></table></span></div>';
		t+='</span><span style="font: 10px Verdana; width:'+w+'px; height:'+h+'px;text-align:\'center\';"><center><b>'+msg+'</b></center></span></div>';
		t+='</div>';
		//alert(t)
		document.write(t);
		var barraProgressoAjax=(ie)?document.all['blocks'+N]:document.getElementById('blocks'+N);
		barraProgressoAjax.fundo = new Object();
		barraProgressoAjax.fundo=(ie)?document.all['fundoBar']:document.getElementById('fundoBar');
		barraProgressoAjax.bar=(ie)?document.all['_xpbar'+N]:document.getElementById('_xpbar'+N);
		barraProgressoAjax.blocks=blocks;
		barraProgressoAjax.N=N;
		barraProgressoAjax.w=w;
		barraProgressoAjax.h=h;
		barraProgressoAjax.speed=speed;
		barraProgressoAjax.ctr=0;
		barraProgressoAjax.count=count;
		barraProgressoAjax.action=action;
		//barraProgressoAjax.togglePause=togglePause;
		barraProgressoAjax.showBar=function(){
			//this.bar.style.visibility="visible";
			this.bar.style.display="block";
			this.fundo.style.display="block";
			hideFormSelect();
			hideIframe();
		}
		barraProgressoAjax.hideBar=function(){
			//this.bar.style.visibility="hidden";
			this.bar.style.display="none";
			this.fundo.style.display="none";
			showFormSelect();
			showIframe();
		}
		barraProgressoAjax.initBar=function(){
			this.showBar()
			barraProgressoAjax.tid=setInterval('startBar('+N+')',speed);
		}
		barraProgressoAjax.stopBar=function(){
			this.hideBar()
			clearInterval(barraProgressoAjax.tid);
		}
		//this.hide();
		return barraProgressoAjax;
	}
}



var winPopupBarra;
var interval;
function iniciarBarraProgresso(winPopup){
	if (barraProgressoAjax) {
		barraProgressoAjax.initBar();
		if (winPopup) {
			winPopupBarra = winPopup;
			interval = setInterval('testePararBarra()', 1);
		}
	}
}

/**
* Para a barra de progresso
*/
function pararBarraProgresso(){
	if(barraProgressoAjax)
		barraProgressoAjax.stopBar();
}



/**
* Inicia a barra de progresso
*/
function startBar(bn){
	var t=(ie)?document.all['blocks'+bn]:document.getElementById('blocks'+bn);
	try {
	
	  if( this.tid != 0){
		if(t.style && parseInt(t.style.left)+t.h+1-(t.blocks*t.h+t.blocks)>t.w){
			t.style.left=-(t.h*2+1)+'px';
			t.ctr++;
			if(t.ctr >= t.count){
				eval(t.action);
				clearInterval(this.tid)
				this.tid=0;
				t.ctr=0;
			}
		}else
			t.style.left=(parseInt(t.style.left)+t.h+1)+'px';
	  }
	  } catch (e) {
	    // Nao precisa tratar
	  }

		//Posiciona a barra e fundo caso haja scroll na p?gina
		var posScroll = getPosicaoScroll();
		redimensionaElementoPor("fundoBar", posScroll.posEsquerda, posScroll.posTopo);
		//posicionaElementoPor("barraProgresso", posScroll.posTopo, posScroll.posEsquerda);
}

plc.jq(document).ready(function (){
	jsf.ajax.addOnEvent(function(event) {
		if(event.status=="begin") {
			// Exibe o icone de loading
			plc.partialLoading();
			// Desabilita todos campos e botões da tela
			plc.jq("input, select, textarea, button").attr("disabled", "disabled");
			// Remove o evento onclick dos spans
			plc.jq("span").attr("onclick", "#");
		} else if(event.status=="complete") {
			// Oculta o icone de loading
			plc.partialLoading(false);
		}
	});
});
