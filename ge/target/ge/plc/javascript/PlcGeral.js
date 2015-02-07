// NoConflict do jQuery e outras JS-APIs
//if (jQuery)
//	jQuery.noConflict();

/**
 * Arquivo geral de fun??es javascript do jCompany.
 * @author Rodrigo Magno Xy
 * @author Cl?udia Seara
 * @version 3.0
 * @since jCompany 1.0
 * @lastmodified 21/02/2006
 */

/**
* Vari?veis globais de utiliza??o geral
* @deprecated NNav Vari?vel que indica o browser como Netscape. Utilizada para
* compatibiliza??o com c?digos antigos [Boolean]
* @variable debugNav Utiliza para ligar/desligar debug de vari?veis globais [Boolean]
* @variable AgntUsr  Define qual o browser utilizado [String]
* @variable AppVer Define qual vers?o do browser utilizado [String]
* @variable DomYes Define se o browser utilizado aceita a fun??o "getElementById" [Boolean]
* @variable NavYes Define se o browser ? compat?vel com engine Mozilla [Boolean]
* @variable ExpYes Define se o browser ? compat?vel com Internet Explorer [Boolean]
* @variable Opr Define se o browser ? compat?vel com Opera[Boolean]
* @variable interval Utilizada para intervalos de tempo [Object]
*/

var NNav = ((navigator.appName == "Netscape"));
var debugNav = false;
var AgntUsr	= navigator.userAgent.toLowerCase();
var AppVer	= navigator.appVersion.toLowerCase();
var DomYes	= document.getElementById ? 1:0;
var NavYes	= AgntUsr.indexOf('mozilla') != -1 && AgntUsr.indexOf('compatible') == -1 ? 1:0;
var ExpYes	= AgntUsr.indexOf('msie') != -1 ? 1:0;
var Opr		= AgntUsr.indexOf('opera')!= -1 ? 1:0;
var interval;
var alterouVinculado 	= false;
var idVinculadoAlterado = "";

if(debugNav){
	alert("Agente: "+AgntUsr+" - Versao: "+AppVer+"\n"+
	"Netscape/Mozilla: "+NavYes+"\n"+
	"Internet Explorer: "+ExpYes+"\n"+
	"Opera: "+Opr);
	var i = i;
}

var ajaxUsa = false;

function setAjaxUsa (ajaxUsaArg) {
	ajaxUsa = ajaxUsaArg;
}

var tecnologia = "Struts";

function setTecnologia(tecnologiaArg) {
	tecnologia = tecnologiaArg;
}

/**
* Construtor para objeto PlcGeral
*/
function PlcGeral(){}
/**
* @variable plcGeral Instancia do objeto PlcGeral
*/
var plcGeral = new PlcGeral();

plcGeral.exibeAlertaAlteracao = true;

plcGeral.ationCorpoFormulario = "";

/**
 * configura context path dinamicamento no objeto plcGeral 
 */
if (setContextPath) setContextPath();
/**
* Fun??o para permitir utiliza??o do evento onload por diversos scripts
*/
PlcGeral.prototype.eventOnLoad = function(){}
/**
* Fun??o que ser? executada ap?s devolu??o de sele??o popup
*/
PlcGeral.prototype.aposDevolveSelecaoPopup = function() {
	if (alterouVinculado) {
		mostraLimparVinculado(idVinculadoAlterado + "Limpar");
	}
};

/**
* Vari?vel que contem o contexto da aplica??o
*/
PlcGeral.prototype.contextPath = "";
/**
* Mensagem de erro para obrigat?rios
*/
PlcGeral.prototype.obrigatorioMsg = "";
/**
* Express?o regular para valores alfab?ticos (sem n?meros)
*/
PlcGeral.prototype.alfabeticoPattern = /^[^0-9]+$/;
/**
* Express?o regular para valores num?ricos
*/
PlcGeral.prototype.numericoPattern =  /\d/;
/**
* Express?o regular para valores monetarios
*/
PlcGeral.prototype.currencyPattern =  /[][,]{1}\d{2}/;

/**
* Express?o regular para data
*/
PlcGeral.prototype.dataPattern =  /\d{2}\/\d{2}\/\d{4}/;
/**
* Express?o regular para data/hora
*/
PlcGeral.prototype.datahoraPattern =  /\d{2}\/\d{2}\/\d{4} \d{2}:\d{2}/;

//Indica se menu de sistema est? ativo para navega??o via setas
PlcGeral.prototype.MENU_ATIVO = false;

PlcGeral.prototype.formSubmit = function(action,evento){
	
	if (tecnologia != "JSF" && tecnologia != "Facelets" ){
		var form = getForm();
		form.action=plcGeral.contextPath+action+".do?evento="+evento;
		form.submit();
	}
	else{
		// Não retirar o timeout porque deve esperar a função TrocaAba ser executada
		setTimeout("document.getElementById('corpo:formulario:botaoAcaoRecuperaPorDemanda').click()",100);
	}
}

/**
* Função utilizada para exportação de Registros da Pesquisa.
* Esta função abre uma Janela e faz a requisição no servidor para montar a exportação de acordo com o 'formato' escolhido.
*
* Função é utilizada para a tecnologia Struts e JSF.
*
* Com Struts chama o método F9-Pesquisar da PlcBaseAction que refaz a pesquisa dos registros baseando-se nos argumentos informados pelo usuário, e redireciona para o Serviço de exportação que retornará o informações conforme o formato escolhido.
* Com JSF chama o Servlet de exportação 'PlcExportaJsfcaoServlet' que recupera da Conversação a lista de registros da seleção, 
* evitando refazer a pesquisa.
* @autor Pedro Henrique 
*/	
PlcGeral.prototype.exportaPopup = function(contextoAplicacao,pStrutsAction,isRequestJSF){

	var campoExportaPlc 	= getCampo("exportaPlc");
	var parentForm 			= getForm();
	var metodo				= 'post'; 
	
	var win 		= janela("");
	var action 		= "";
	var	conteudo  	= '<html><body> <form name="inicialForm" method="post" action="'+ contextoAplicacao +  pStrutsAction ;
	//metodo = 'get'; caso queira utilizar get 
	
	/**
	* Com JSF chama o Servlet de exportação 'PlcExportaJsfcaoServlet' que recupera da Conversação a lista de registros da seleção, 
	* evitando refazer a pesquisa.
	*/
	if (isRequestJSF == 'S'){
		var conversationIdPlc = getCampo('corpo:formulario:conversationIdPlc');
	 	action = contextoAplicacao + '/exportacaojsf">';
	 	if (typeof conversationIdPlc != "undefined")
			action +=  ' <input type="hidden" name="conversationIdPlc" value="' + conversationIdPlc.value +'" id="conversationIdPlc" >';
		action +=  ' <input type="hidden" name="action" value="' + pStrutsAction +'" id="action" >';
	 	if (typeof campoExportaPlc != "undefined")
			action +=  ' <input type="hidden" name="fmtPlc" value="' + campoExportaPlc.value +'" id="fmtPlc" >';
		
	}
	/**
	* Com Struts chama o método F9-Pesquisar da PlcBaseAction que refaz a pesquisa dos registros baseando-se nos argumentos 
	* informados pelo usuário, e redireciona para o Serviço de exportação que retornará o informações conforme o formato escolhido.
	*/
	else{
	  	action = contextoAplicacao +  pStrutsAction  + '.do?evento=F9-Pesquisar&isExportacaoPlc=S&fmtPlc=' + campoExportaPlc.value + '">';
	
		for (var i = 0; i < parentForm.elements.length; i++) 
			action +=  ' <input type="hidden" name="' + parentForm.elements[i].name + '" value="' + parentForm.elements[i].value +'" id="' + parentForm.elements[i].name + '" >';
	}
		
	// montando o conteúdo da página
	var	conteudo  	= '<html><body> <form name="inicialForm" method="' +metodo+ '" action="'+ action + '</form> </body></html>';
	
	win.document.write(conteudo);
	win.document.forms[0].submit();
	
	// Voltando com a seleção do campo de exportação para a primeira Opção  [Exportar para ...]
	if(campoExportaPlc)
		campoExportaPlc.selectedIndex = 0;
	
}

/**
 * Fun??o para executar outras fun??es no onload da p?gina
 * @variable campoFocus Vari?vel que identifica campo a ser posicionado
 * @variable interval Utilizada para intervalos de tempo [Object]
 * @see moverFoco
 * @see testaAvisoOnline
 * @see setUpOnFocusHandlers
 * @see gravaResolucaoVideo
 * @see gerarImpressaoInteligente
 * @see executarFuncaoOnLoad
 */
function iniciarPagina()
{
	moverFoco(); //Move foco automaticamente
	testaAvisoOnline(); //Envia aviso online
	setUpOnFocusHandlers(); //Configura evento onfocus automaticamente
	gravaResolucaoVideo(); // Grava resolucao de video para uso no jcpmonitor.
	if(typeof getParametroUrl("impIntel") != "undefined" && 
	getParametroUrl("impIntel").toLowerCase() == "s")
		gerarImpressaoInteligente(); //Executa impressao inteligente
	executarFuncaoOnLoad(); //Executa fun??es configuradas para onload da p?gina
	mantemAbaSelecionada();
	
}

/**
* Grava resolu??o de v?deo do cliente no cookie.
* Essa informa??o ? utilizada pelo jcpmonitor.
*/
function gravaResolucaoVideo() {
	document.cookie='resolucaoPlc='+screen.width+'x'+screen.height+';';
}

/**
 * Fun??o mover o foco automaticamente
 * @variable campoFocus Vari?vel que identifica campo a ser posicionado
 */
function moverFoco(){

	if (getCampoFocus() == "") {
		setTimeout("testaCampos()", 20)
	} else {
		if (!setaFocoAba()){
			setTimeout("setFocus(getCampoFocus(), getCampoFocusSelecionar())", 20);
		}	
	}
}

function setAncora(ancora){
	document.location.hash = ancora;
}

/**
 * Fun??o posicionar foco em campo espec?fico
 * @variable campoFocus Vari?vel que identifica campo a ser posicionado [String]
 * @param nomeCampo Nome do campo a ser focado [String,OB]
 */
var campoFocus = "";
var campoFocusSelecionar = false;
function setCampoFocus(nomeCampo, selecionar){
	this.campoFocus = nomeCampo;
	setCampoFocusSelecionar(selecionar);	
}

function getCampoFocus(){
	return this.campoFocus;
}
function setCampoFocusSelecionar(selecionar){
	if(selecionar && typeof selecionar != "undefined" && selecionar != "")
		this.campoFocusSelecionar = true;
}
function getCampoFocusSelecionar(){
	this.campoFocusSelecionar
}

/**
 * Função que retorna o objeto window correto da janela atual, para evitar problemas com IFrame PPR.
 * Implementado por Bruno Grossi - 29/03/2007
 */
function getRootWindow() {
	return "_pprIFrame" != window.name ? window : window.parent;
}

/**
 * Função que retorna o document da janela atual, para evitar problemas com IFrame PPR.
 * Implementado por Bruno Grossi - 29/03/2007
 */
function getRootDocument() {
	return getRootWindow().document;
}

/**
 * Fun??o para retornar um objeto que representa um form da p?gina
 * @param form Nome do form, caso n?o seja o form padr?o. [String,OP]
 * @see getVarGlobal
 * @return form Objeto form [Object]
 */
function getForm(form){

	var sessForm = getVarGlobal("form");
	// Se houver um opener recupera o form deste
	var parentForm = "";
	if(opener && form != null){
		try {
			if( opener.getVarGlobal("parentForm") != "" && opener.getVarGlobal("parentForm") != 0 &&
				opener.getVarGlobal("parentForm") != null && opener.getVarGlobal("parentForm") != "undefined" )
				return opener.getVarGlobal("parentForm"); 
		} catch(ex) {
			// Erro de permissão caso a window opener, esteja em outro domínio.
		}
	}	
	if(form != "" && form != 0 && ""+form != "undefined" && form != null)
		form = eval("getRootDocument().forms['"+form+"']");
	else if(sessForm != "" && sessForm != 0 && ""+sessForm != "undefined" && ""+sessForm != "null")
		form = eval("getRootDocument().forms['"+sessForm+"']");
	else
		form = eval("getRootDocument().forms[0]");
	return form;
}

/**
 * Fun??o para retornar um objeto que representa um campo da p?gina
 * @param campo Nome do campo que se quer recuperar. [String, OB]
 * @param form Nome do form, caso n?o seja o form padr?o. [String,OP]
 * @see getForm
 * @return campo Objeto campo [Object]
 */
function getCampo(campo, form)
{
	form = getForm(form);
	if(form)
		campo = form.elements[campo];
	return campo;
}

/**
 * Fun??o para envia mensagem de confirma??o para  exclus?o de registro e dispara
 * bot?o associado ? exclus?o
 * @param acao A??o que determina bot?o de exclus?o a ser disparado [String]
 * @see disparaBotaoAcao
 */
function confirmaExclusao(acao)
{
	if (confirm('Confirma a Exclus?o do Registro?'))
		disparaBotaoAcao(acao);
}

/**
 * Fun??o dispara clique em um bot?o da p?gina conforme acao passada.<b>
 * Se for passado tamb?m o par?metro id o bot?o clicado deve ter este valor declarado
 * @param acao A??o que determina bot?o de exclus?o a ser disparado [String, OB]
 * @param id Identificador do bot?o a ser disparado [String, OP]
 * @return disparou Retorna true se bot?o foi disparado ou false caso contr?rio
 */
function disparaBotaoAcao(acao, id)
{
	if (id == "" || ""+id == "undefined")
		id = "botao_menu";

	var numElements;
	var elementValue;
	var disparou = false;
	var form = getForm(form);
	var elements;

	if(form.elements[id].length)
		elements = form.elements[id];
	else
		elements = form.elements;

	if (form)
	{
		numElements = elements.length;
		if(numElements > 0)
		{
			for(i=0; i < numElements; i++)
			{
				elementValue = elements[i].value;
				if(elementValue == acao)
				{
					elements[i].click();
					i =numElements;
					disparou = true;
				}
			}
		}
	}
	else if (eval(form.elements[acao]))
		form.elements[acao].click();

	return disparou;
}

/**
 * Fun??o de alerta em janela com mensagem<br>
 * Passo 1: Colar as linha abaixo no fim da p?gina:<br><dd>
 *	<blockquote><code>&lt;!-- IN?CIO DIV DE ALERTA --&gt;<br>
 *		<blockquote>&lt;div  ID=ALERTA class="janela_msg" style="visibility='hidden'"&gt;<br>
 *  		<blockquote>&lt;table width="250" height="100%"  border="0" cellpadding="0" cellspacing="0"&gt;<br>
 * 				<blockquote>&lt;tr\&gt;<br>
 *  						&lt;td width="239" class="janelaMsgTit" ID=TITULO&gt;&nbsp;&lt;/td&gt;<br>
 *  						&lt;td width="11" align="center" valign="middle" class="janelaMsgTit"&gt;<br>
 *  						<blockquote>&lt;a href="#" onclick="showDiv('ALERTA', false); return false;"&gt;<br>
 *  									&lt;img align="ABSMIDDLE" alt="Clique para fechar esta janela" border="0" height="11" hspace="0" src="&lt;%=request.getContextPath()%&gt;/midia/g_ico_fechar.gif" vspace="0" width="11"&gt;<br>
 *  									&lt;a&gt;
 * 							</blockquote><br>
 *							&lt;/td&gt;&lt;/tr&gt;<br>
 *  						&lt;tr&gt;<br>
 *							&lt;td colspan="2" class="janelaMsgSubtit" ID=SUBTITULO&gt;&lt;/td&gt;<br>
 * 							&lt;/tr&gt;<br>
 * 							&lt;tr&gt;<br>
 *  						&lt;td colspan="2" valign="top" class="janelaMsgConteudo" ID=CONTEUDO&gt;&nbsp;&lt;/td&gt;
 * 							&lt;/tr&gt;
 * 			</blockquote>
 *			&lt;/table&gt;<br>
 *		</blockquote>
 *	&lt;/div&gt;<br>
 *	</blockquote>
 *	<blockquote>&lt;!-- FIM DIV DE ALERTA --&gt;</blockquote></code><><br>
 *  Chamada: <br><dd><code>&lt;a href="#" onClick="janelaMsg('ALERTA','MEU TITULO','MEU SUBTITULO','MEU CONTEUDO'); return false;"&gt;Link&lt;/a&gt;<code>
 *  @param div Div onde a mensagem vai ser mostrada [String]
 *  @param titulo Titulo da janela de mensagem [String]
 *  @param subtitulo Sub-Titulo da janela de mensagem [String]
 *  @param conteudo Conteudo da mensagem [String]
 *  @param evt N?o utilizado (RETIRAR)
 *  @see showDiv
 */
function janelaMsg(div, titulo, subtitulo, conteudo, evt)
{
	showDiv(div, true,evt);
	var sufix = "";
	var i = div.indexOf("_");
	if(i >= 0)
		sufix = div.substring(i,div.length);
	var tit 	= eval("TITULO"+sufix);
	var subtit 	= eval("SUBTITULO"+sufix);
	var cont 	= eval("CONTEUDO"+sufix);

	tit.innerHTML = titulo;
	subtit.innerHTML = subtitulo;
	cont.innerHTML = conteudo;
}

/**
* Comuta paineis entre visiveis e invisiveis
* @since jCompany 3.03
* @param ajuda Objeto que representa a janela de ajuda [String,OB]
* @see showFormSelect
*/
function janelaPainel(painel)
{
	if (painel.style.visibility =='hidden')
	{
		painel.style.visibility = 'visible';
		hideFormSelect();
	}
	else
	{
		painel.style.visibility = 'hidden';
		showFormSelect();
	}
}

/**
* Fun??o para esconder / mostrar qualquer elemento da tela que contenha um id
* @param idObj Objeto para esconder / mostrar [String,OB]
* @param visibilidade Define se o objeto vai ser escondido ou mostrado [Boolean,OB] {true|false}
* @TODO Alterar para chamar getElementoStyle()
*/
function setVisible(idObj, visibilidade)
{
	var obj = eval("document.all['"+idObj+"']");
	if(obj)
		obj.style.visibility = visibilidade;
}

/**
 * Fun??o que mostra/esconde div de mensagem
 *  @param div Nome do div onde a mensagem vai ser mostrada [String,OB]
 *  @param show Vari?vel que indica define se o div ser? mostrado ou escondido [Boolean,OB] {true|false}
 */
function showDiv(div, show)
{
	var wDiv = 200; //Largura do div declarada no CSS CLASS = janelaMsg
	var hDiv = 200; //Altura do div declarada no CSS CLASS = janelaMsg
    var ie4=document.all && navigator.userAgent.indexOf("Opera")==-1;
    var ns6=document.getElementById&&!document.all;
    var ns4=document.layers;
    var eventX=ie4? event.clientX : ns6? e.clientX : e.x;
    var eventY=ie4? event.clientY : ns6? e.clientY : e.y;
	var menuobj = eval(div);
	menuobj.contentwidth = wDiv;
	menuobj.contentheight = hDiv;
	menuobj.thestyle=(ie4||ns6)? menuobj.style : menuobj;

	if(show)
	{
			menuobj.style.visibility='visible';
           //Find out how close the mouse is to the corner of the window
           var rightedge=ie4? document.body.clientWidth-eventX : window.innerWidth-eventX;
           var bottomedge=ie4? document.body.clientHeight-eventY : window.innerHeight-eventY;

           //if the horizontal distance isn't enough to accomodate the width of the context menu
           if (rightedge<menuobj.contentwidth)
           //move the horizontal position of the menu to the left by it's width
	           menuobj.thestyle.left=ie4? document.body.scrollLeft+eventX-menuobj.contentwidth : ns6? window.pageXOffset+eventX-menuobj.contentwidth : eventX-menuobj.contentwidth;
           else
           //position the horizontal position of the menu where the mouse was clicked
        	   menuobj.thestyle.left=ie4? document.body.scrollLeft+eventX : ns6? window.pageXOffset+eventX : eventX;

           //same concept with the vertical position
           if (bottomedge<menuobj.contentheight)
    	       menuobj.thestyle.top=ie4? document.body.scrollTop+eventY-menuobj.contentheight : ns6? window.pageYOffset+eventY-menuobj.contentheight : eventY-menuobj.contentheight;
           else
	           menuobj.thestyle.top=ie4? document.body.scrollTop+event.clientY : ns6? window.pageYOffset+eventY : eventY;
           menuobj.thestyle.visibility="visible";
           return false;
	}
	else{
		menuobj.style.visibility='hidden';
	}
}


/**
* Fun??o para colocar foco no primeiro campo v?lido da p?gina.
* Ap?s o onload da p?gina a fun??o procura nos campos da p?gina um campo v?lido para focar
* @see setFocus
*/
function testaCampos(){
	if (getCampoFocus() != '') {
		return;
	}
	var numForms = getRootDocument().forms.length;
	var numElements = 0;
	var primeiroCampo = "";
	var campoFocado	= null;

	if(getVarGlobal("trSelecao") != null)
		return;
	if(getRootDocument().location.hash && getRootDocument().location.hash != null && getRootDocument().location.hash.indexOf("#") > -1)	
		return;
		
	for(i=0; i < numForms; i++)
	{
		numElements = getRootDocument().forms[i].elements.length;

		for(j=0; j < numElements; j++)
		{
			   if (getRootDocument().forms[i].elements[j].getAttribute("inibeFoco") != 'S' && 
			    !(getRootDocument().forms[i].elements[j].className.indexOf("inibeFoco") > -1)){
					if (getRootDocument().forms[i].elements[j].getAttribute("id") != null && !(getRootDocument().forms[i].elements[j].getAttribute("id").indexOf(":inibeFoco") > -1)
						&& !(getRootDocument().forms[i].elements[j].getAttribute("id").indexOf("inibeFoco_") > -1)){
						if((getRootDocument().forms[i].elements[j].type=="text" ||
							getRootDocument().forms[i].elements[j].type=="password" ||
							//getRootDocument().forms[i].elements[j].type=="file" ||
							getRootDocument().forms[i].elements[j].type=="textarea") &&
							getRootDocument().forms[i].elements[j].type != "hidden" &&
							!getRootDocument().forms[i].elements[j].readOnly &&
							!getRootDocument().forms[i].elements[j].disabled){
							if(getRootDocument().forms[i].elements[j].value == ""){
								campoFocado = getRootDocument().forms[i].elements[j];
								primeiroCampo = getRootDocument().forms[i].elements[j];
								i = numForms;
								j = numElements;
							}else if(primeiroCampo == "" && getRootDocument().forms[i].elements[j].type != "hidden" &&
							!getRootDocument().forms[i].elements[j].readOnly && !getRootDocument().forms[i].elements[j].disabled)
								primeiroCampo = getRootDocument().forms[i].elements[j];
						}
						else if(getRootDocument().forms[i].elements[j].type == "radio"){
							//setFocus(document.forms[i].elements[j].name);
						}
						else if((getRootDocument().forms[i].elements[j].type == "select-one" ||
							getRootDocument().forms[i].elements[j].type == "select-multiple") &&
							getRootDocument().forms[i].elements[j].options.length > 0 &&
							getRootDocument().forms[i].elements[j].options.selectedIndex <= 0 &&
							!getRootDocument().forms[i].elements[j].readOnly &&
							!getRootDocument().forms[i].elements[j].disabled){
							try {
								primeiroCampo = "";
								getRootDocument().forms[i].elements[j].focus();
								getRootDocument().forms[i].elements[j].options[0].selected = true;
								campoFocado = getRootDocument().forms[i].elements[j];
							}catch (e){}
							i = numForms;
							j = numElements;
						}else if(primeiroCampo == "" && typeof getRootDocument().forms[i].elements[j].type != 'undefined' &&
							getRootDocument().forms[i].elements[j].type != "button" &&
							getRootDocument().forms[i].elements[j].type != "submit" &&
							getRootDocument().forms[i].elements[j].type != "hidden" &&
							getRootDocument().forms[i].elements[j].type != "radio" &&
							getRootDocument().forms[i].elements[j].type != "checkbox" &&
							!getRootDocument().forms[i].elements[j].readOnly &&
							!getRootDocument().forms[i].elements[j].disabled)
								primeiroCampo = getRootDocument().forms[i].elements[j];
					}
			}
		}
	}
	if (primeiroCampo != "") {
		setFocus(primeiroCampo.name, true);
		campoFocado = primeiroCampo;
	}
	setVarGlobal("campoFocadoPlc", campoFocado);	
}

/**
* Fun??o para testar se campo permite foco
*/
function permiteFocus(nomeCampo){

	var campo = getCampo(nomeCampo);
	
	if(campo && 
		((campo.getAttribute("inibeFoco") != 'S' &&
		campo.className.indexOf("inibeFoco") < 0) ||
		(typeof campo.getAttribute("id")!= 'undefined' && campo.getAttribute("id") != null &&
		  campo.getAttribute("id").indexOf(":inibeFoco")>-1)) &&
		!campo.readOnly && !campo.disabled){
		if((campo.type=="text" ||
			campo.type=="password" ||
			campo.type=="file" ||
			campo.type=="textarea") &&
			campo.type != "hidden" &&
			campo.value == ""){
			
			return true;	
		}
		else if((campo.type == "select-one" ||
			campo.type == "select-multiple") &&
			campo.options.length > 0 &&
			campo.options.selectedIndex <= 0){

			return true;
		}
	}
	
	return false;
}

function focarCampoDetalhe(nomeDetalhe, campoFoco, numDetalhes){
	try {
		for(var d = 0; d < numDetalhes; d++){
			var campoDetalhe = campoFoco.indexOf("[0]") > -1 ? campoFoco.replace("[0]","["+d+"]") : 
								nomeDetalhe+"["+d+"]."+campoFoco;
			//alert("FOCARCAMPODETALHE - CAMPODETALHE: "+campoDetalhe)								
			if(permiteFocus(campoDetalhe)){
				setCampoFocus(campoDetalhe);
				break;
			}	
		}
	}
	catch(e) {}
}

function setFocoDetalhe(){
	//alert("SETFOCODETALHE")								
	var idPortlet 	= getVarGlobal("idPortlet");
	var campoFoco 	= getVarGlobal("campoFoco_"+idPortlet);
	//alert("SETFOCODETALHE - IDPORTLET: "+idPortlet)								
	//alert("SETFOCODETALHE - CAMPOFOCO: "+campoFoco)								
	focarCampoDetalhe(idPortlet, campoFoco,parseInt(getVarGlobal("numDetalhes_"+idPortlet)))
}

/**
* Fun??o de registro dos dados de avisos on-line
* @variable avisoArray Array que guarda dados do aviso on-line
* @param url Url da popup do aviso [String,OB]
* @param props Propriedades da popup do aviso [String]
*/

var avisoArray = new Array();
function regAvisoOnline(url, props){
	this.url	= url;
	this.props	= props;
}

/**
* Fun??o para cria??o de avisos online
* @variable avisoArray Array onde s?o guardados os dados do aviso on-line
* @param url Url da popup do aviso [String,OB]
* @param props Propriedades da popup do aviso [String]
* @see regAvisoOnline
*/
function setAvisoOnline(url, props)
{
	avisoArray[avisoArray.length] = new regAvisoOnline(url, props);
}

/**
* Fun??o para testar se existe avisos online e abrir popup do mesmo
* @variable avisoArray Array de onde s?o retiradas os dados do aviso on-line
* @see janela
*/
function testaAvisoOnline()
{
	if(avisoArray.length > 0)
		janela(avisoArray[0].url,"","",avisoArray[0].props);
}


/**
* Fun??o que altera o CLASS de um elemento
* Colar dentro do elemento: styleId="botao_menu" onmouseover="try{animar(event , \'2\')}catch(e){}"  onmouseout="try{animar(event, \'\')}catch(e){}"
* @param e Evento que originou a chamada da fun??o. [Object,OB]
* @param classe Nome da classe a ser trocada dinamicamente [String,OB]
*/
function animar(e, classe)
{
	var obj;
	if(NavYes)
		obj = e.target;
	else
		obj = e.srcElement;
	var originalClass = obj.id;
	if(originalClass){
		if(originalClass.indexOf("2") != -1)
			originalClass = originalClass.substring(0, originalClass.length-1);

		classe = originalClass+classe;
		//if (DomExp)
			obj.id = classe;
	}
}

/**
* Fun??o para abrir um janela do tipo POP-UP
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","width","height","props"); return false;'&gt;</code>
* @param url Endere?o para abertura da janela
* @param wa Largura da janela. [String, OP]
* @param ha Altura da janela. [String, OP]
* @param props Propriedades da janela. Informar no lugar de <I>wa</I> e <I>ha</I> que devem ser informados como "" [String, OP]
* @param alvo Inst?ncia para abertura. [String]
* @param max Indica que a janela deve abrir maximizada. [String]
* @param posX posi??o x onde a janela deve ser criada. [String, OP]
* @param posY posi??o y onde a janela deve ser criada. [String, OP]
*/
function janela(url,wa,ha,props,alvo,max,posX,posY) {
	var w = 720;
	var h = 350;
	var t = "";
	var p = "";
	var win;

	if ( alvo == null || alvo == "" ){
		alvo = "plcPopup";
	}
	if (arguments[1] && arguments[1] != "" && arguments[1] != "0") //Largura
		w = wa;
	if (arguments[2] && arguments[2] != "" && arguments[2] != "0") //Altura
		h = ha;
	p = "resizable=yes,scrollbars=yes,width="+w+",height="+h;
	if (arguments[3] && arguments[3] != "") //Propriedades
		p = props;
	if (arguments[4] && arguments[4] != "") //Alvo
		t = alvo;

	win = window.open(url,t,p);

	if (arguments[5] && arguments[5] != "" && arguments[5] != "N") //Redimensiona
	{
		//Retirar ap?s resolver problema de privil?gios para Mozilla
		if(!NavYes)
			redimensiona(win);
	}
	else if(url.indexOf("http") == -1 && (props == "" || ""+props == "undefined"))
	{
		var posCentro = getPosicaoCentro(w,h,posX, posY);
		try{
			win.moveTo(posCentro.moveCentroX,posCentro.moveCentroY);
		} catch(e) {
		}
	}

	return win;
}

/**
* Fun??o que recupera o correto posicionamento central para uma janela popup
* @param w Largura da janela. [String, OB]
* @param h Altura da janela. [String, OB]
* @param posX posi??o x onde a janela deve ser criada. [String, OP]
* @param posY posi??o y onde a janela deve ser criada. [String, OP]
* @return posicaoCentro Objeto com os valores para posicionamento da janela [Object]
*/
function getPosicaoCentro(w,h,posX, posY){

	var moveCentroX 	= 0;
	var moveCentroY 	= 0;

	if( (posX && posX != "") || (posY && posY != "") ) {
		if(posX && posX != "")
			moveCentroX = posX;
		if(posY && posY != "")
			moveCentroY = posY;
	}
	else {
		//Centralizar janela popup
		if(NavYes)
		{
			moveCentroX 	= window.screenX + ((window.outerWidth - w) / 2);
			moveCentroY 	= window.screenY + ((window.outerHeight - h) / 2);
			//netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
			//netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
		}
		else
		{
			moveCentroX = (screen.availWidth/2);
			moveCentroX = moveCentroX - (w/2);
			moveCentroY = (screen.availHeight/2);
			moveCentroY = moveCentroY - (h/2);
		}
	}

	var posicaoCentro = new Object(moveCentroX, moveCentroY);
	posicaoCentro.moveCentroX 	= moveCentroX;
	posicaoCentro.moveCentroY 	= moveCentroY;
	return posicaoCentro;
}

/**
* Fun??o que recupera o correto posicionamento do mouse na tela
* @return posicaoCentro Objeto com os valores para posicionamento da janela [Object]
*/
//document.onmousemove = getMouseXY;
//if (!ExYes) 
//	document.captureEvents(Event.MOUSEMOVE)
function getPosicaoMouse(evt){

	var posX = 0;
	var posY = 0;
	if (ExpYes) { // grab the x-y pos.s if browser is IE
		posX = evt.clientX;// + document.body.scrollLeft - document.Show.offsetX.value;
		posY = evt.clientY;// + document.body.scrollTop - document.Show.offsetY.value;
	}
	else {  // grab the x-y pos.s if browser is NS
		posX = evt.pageX;
		posY = evt.pageY;
	}  
	var posicaoMouse = new Object(posX, posY);
	posicaoMouse.posX 	= posX;
	posicaoMouse.posY 	= posY;
	return posicaoMouse;
}

/**
 * Recupera o tamanho da janela atual
 * @variable NNav Vari?vel que identifica navegador [String,SYS]
 * @return tamWindow Objeto com tamanhos horizontal e vertical da janela
 */

function getTamanhoWindow(){

	var tamX	= 0;
	var tamY	= 0;

	//Centralizar janela popup
	if(NNav)
	{
		tamX	= window.outerWidth;
		tamY	= window.outerHeight;
	}
	else
	{
		tamX	= screen.availWidth;
		tamY	= screen.availHeight;
	}
	var tamWindow = new Object(tamX, tamY);
	tamWindow.tamX 	= tamX;
	tamWindow.tamY 	= tamY;
	return tamWindow;
}
/**
* Fun??o redimensionar janela para ocupar toda a tela do browser
* @variable win Instancia de uma janela para redimensionamento
* @variable NNav Vari?vel que identifica navegador [String,SYS]
*/
function redimensiona(win)
{
	if(NNav)
	{
		netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
		if (win.outerHeight < screen.availHeight || win.outerWidth < screen.availWidth)
		{
			win.outerHeight = screen.availHeight;
			win.outerWidth = screen.availWidth;
		}
	}
	else
	{
		win.resizeTo(screen.availWidth,screen.availHeight);
	}
	win.moveTo(0,0);
}

/**
* Fun??o para abrir um janela do tipo POP-UP em uma mesma instancia passada
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","alvo"); return false;'&gt;</code>
* @param url Endere?o para abertura da janela
* @param wa Largura da janela. [String, OP]
* @param ha Altura da janela. [String, OP]
* @param props Propriedades da janela. Informar no lugar de <I>wa</I> e <I>ha</I> que devem ser informados como "" [String, OP]
*/
function janelaComAlvo(url,alvo,wa,ha,props,max) {
	return janela(url,wa,ha,props,alvo,max);
}

/**
* Fun??o para abrir um janela do tipo POP-UP redimensionada para tamanho m?ximo da resolu??o
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","alvo","props"); return false;'&gt;</code>
* @param url Endere?o para abertura da janela [String]
* @param alvo Inst?ncia para abertura. [String]
* @param props Propriedades da janela. Informar no lugar de <I>wa</I> e <I>ha</I> que devem ser informados como "" [String, OP]
*/
function janelaMaximizada(url,w,h,alvo,props) {
	return janela(url,w,h,props,alvo,"S");
}

/**
* Fun??o para abrir um janela do tipo POP-UP em modo modal
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","alvo"); return false;'&gt;</code>
* @variable modalWin Objeto que representa instancia da janela modal
* @param url Endere?o para abertura da janela [String]
* @param alvo Inst?ncia para abertura. [String]
*/
var modalWin = new Object();
modalWin.returnValue = false;
function janelaModal(url,wa,ha,props,alvo) {

	if(modalWin.returnValue)
	{
		modalWin.returnValue = false;
		return true
	}
	else if (!modalWin.win || (modalWin.win && modalWin.win.closed))
	{
		modalWin.win = janela(url,wa,ha,props,alvo);
		modalWin.modalFocus = true;
		modalWin.win.focus();
		window.onfocus = checkModal;
		return false;
	}
	else
	{
		checkModal();
		return false;
	}
}

/**
* For?a o focus em janela model
* @variable modalWin Objeto que representa instancia da janela modal
*/
function checkModal()
{
	if(modalWin.modalFocus)
	{
		if (modalWin.win && !modalWin.win.closed)
			modalWin.win.focus();
	}else
		window.focus();
}

/**
* Configura retorno da janela modal
* @variable value Valor de retorno
*/
function setReturnValue(value)
{
	window.onfocus 		= window.focus;
	modalWin.modalFocus 	= false;
	modalWin.returnValue = value;
	modalWin.win.close();
	modalWin.win = null;
}

/**
* Fun??o para guardar dados de impress?o
* @param url Endere?o para abertura da janela de impress?o
* @param titulo T?tulo da janela. [String]
* @param nome Nome da janela. [String]
* @param html Conte?do para impress?o. [String]
*/
var objImpressao;
function objetoImpressao (url, titulo, nome, html)
{
	this.titulo 	= titulo;
	this.nome	 	= nome;
	this.url 		= url;
	this.html 		= html;
}
/**
* Fun??o de chamada da impress?o
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","width","height","props"); return false;'&gt;</code>
* @param url Endere?o para abertura da janela de impress?o
* @param titulo T?tulo da janela. [String]
* @param nome Nome da janela. [String]
* @variable objImpressao Objeto que cont?m dados para impress?o
* @see objetoImpressao
* @see htmlImpressao
*/
function chamarImpressao(url,titulo,nome)
{
// faz na p?gina para melhorar reuso
//	titulo = "<h2>" + titulo + "</h2>";
	objImpressao = new objetoImpressao(url, titulo, nome, hmtlImpressao(window));
	window.open(objImpressao.url,objImpressao.nome,'');
	setBotaoAcao("");
}


/**
* Abrir uma janela tipo IMPRESSAO<br>
* Chamada: <code><br><dd>&lt;a href=# onClick="impressao()"; return=false;&gt;Link&lt;/a&gt;
* A fun??o retira do par?metro objeto uma parte do html entre as<br>
* tags de coment?rio e retorna este html para a p?gina que a chamou
* Chamada: <br><dd><code>&lt;a href='#' onclick='janela("url_janela","width","height","props"); return false;'&gt;</code>
* @param window Objeto Window
* @return textoImpressao Conteudo final para janela de impress?o
*/
function hmtlImpressao(window)
{
	//Objeto => par?metro que representa uma p?gina

	var html = window.document.body.innerHTML;
	var textoInicioImpressao = new String("<!-- INI -->");
	var textoTerminoImpressao = new String("<!-- FIM -->");
	var posIni = html.search(textoInicioImpressao);
	var posFim = html.search(textoTerminoImpressao);
	var posTer;
	var conteudoImpressao = "";
	if (posIni >= 0 && posFim > posIni )
	{
		posTer = posFim - posIni + textoTerminoImpressao.length;
		conteudoImpressao = html.substr(posIni, posTer);
	}
	return conteudoImpressao;
}

/**
* Fun??o que redireciona p?gina est?tica fora do projeto (abrindo em nova inst?ncia)
* Chamada: &lt;body onLoad=redireciona("URL_DA_P?GINA");&gt;
* @param url Endere?o para redirecionamento
*/
function redireciona(url){
	var win;
	win = window.open(url,"win","");
	history.back();
}


/**
* Fun??o para exibir uma mensagem de texto como alerta
* Chamada: &lt;a href='#' onclick='janela("url_janela"); return false;'&gt;
* @param texto Conte?do da mensagem
*/
function exibeMsg(texto) {
	alert(texto);
}

/**
* Fun??es para registro de bot?es que respondem a eventos
* @param nomeBotao Nome do bot?o que atender? ao evento
* @param evento Evento associado ao bot?o
* @variable botaoArray Array que cont?m dados do evento/bot?o
* @see regBotao
*/
var botaoArray = new Array();
function regBotaoEvento(nomeBotao, evento)
{
	botaoArray[botaoArray.length] = new regBotaoEvt(nomeBotao, evento);
}

/**
* Fun??es que guarda dados do evento/bot?o no array
* @param nomeBotao Nome do bot?o que atender? ao evento
* @param evento Evento associado ao bot?o
*/
function regBotaoEvt(nomeBotao, evento)
{
		this.nomeBotao	= nomeBotao;
		this.evento 	= evento;
}

/**
* Fun??es que recupera o bot?o associado ao evento
* @param nomeBotao Nome do bot?o que atender? ao evento
* @param evento Evento associado ao bot?o
* @variable botaoArray Array que cont?m dados do evento/bot?o
* @return Retorna nome do bot?o ou valor "FALSE" se bot?o n?o encontrado
*/
function getBotaoArray(evento)
{	
    plcLog.debug("evento="+evento);
	for(i = 0; i < botaoArray.length; i++)
	{
		plcLog.debug("botaoArray evento="+botaoArray[i].evento+" = "+evento);
		if(botaoArray[i].evento == evento)
			return botaoArray[i].nomeBotao;
	}
	return "FALSE";
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

/**
* Fun??es para devolver bot?o associado a tecla pressionada
* @param evt Objeto Event
* @param evento Evento associado ao bot?o
* @see getBotaoArray
* @return getBotaoArray Retorna o resultado da chamada da fun??o
*/
function testaEventoFuncoes(evt)
{
	var key;
	var keychar;
	var keycharAtalho;
	
	key = getKeyCode(evt);
	keychar = String.fromCharCode(key);

	keycharAtalho = traduzTeclaAtalho(getAtalho(keychar));
	keycharAtalho = keycharAtalho == "" ? keychar : keycharAtalho;

	//plcLog.debug("KEY: "+key)		
	//plcLog.debug("KEYCHAR: "+keychar)		
	//plcLog.debug("KEYCHARATALHO 2: "+keycharAtalho)		
	//plcLog.debug("KEYCHARATALHO CHARCODE: "+keycharAtalho.charCodeAt(0))		
	//plcLog.debug("traduzTeclaAcao(traduzTeclaAtalho(keycharAtalho)): "+traduzTeclaAcao(traduzTeclaAtalho(keycharAtalho)))		
	
   if ((key==null) || (key==0) || (key==8) || (key==9))
		return "ERRO";
 	else if ((key==13))
		return "ENTER";
   else if ((key == 9))
		return "TAB";
   else if ((key == 27))
		return "ESCAPE";
   else if (key == 117 || key == 118 || key == 119 || key == 120 || key == 121 || key == 123)
		return getBotaoArray(traduzTeclaAcao(traduzTeclaAtalho(keycharAtalho)));
/*   else if(keycharAtalho.charCodeAt(0) == traduzTeclaAtalho(keychar).charCodeAt(0))
		return getBotaoArray(traduzTeclaAcao(traduzTeclaAtalho(keychar)));
   else if(keychar.charCodeAt(0) == traduzTeclaAtalho("F8").charCodeAt(0))
		return getBotaoArray("ABRIR");
   else if(keychar.charCodeAt(0) == traduzTeclaAtalho("F9").charCodeAt(0))
		return getBotaoArray("PESQUISAR");
   else if(keychar.charCodeAt(0) == traduzTeclaAtalho("F10").charCodeAt(0))
		return getBotaoArray("GRAVAR");
   else if(keychar.charCodeAt(0) == traduzTeclaAtalho("F12").charCodeAt(0))
		return getBotaoArray("IMPRIMIR");
   else 
		return getBotaoArray(getAtalho(traduzTeclaAtalho(keychar)));
*/		
}

function getKeyCode(evt){
	var key;
	if (ExpYes)
		key = evt.keyCode;
	else
		key = evt.which;
	return key;
}
/**
* Traduz teclas de atalho. 
* Se for informado o nome da tecla retorno caracter associado.
* Se for informado o caracter associado retorno nome da tecla
* @param tecla Nome ou caracter que representa a tecla de atalho [String,OB]
* return Tecla de atalho traduziada
*/
function traduzTeclaAtalho(tecla){
	if(tecla == "F7")
		return "v";
	else if(tecla == "F8")
		return "w";
	else if(tecla == "F9")
		return "x";
	else if(tecla == "F6")
		return "u";
	else if(tecla == "F10")
		return "y";
	else if(tecla == "F12")
		return "{";
	else if(tecla == "F2")
		return "q";
	else if(tecla == "v")
		return "F7";
	else if(tecla == "w")
		return "F8";
	else if(tecla == "x")
		return "F9";
	else if(tecla == "u")
		return "F6";
	else if(tecla == "y")
		return "F10";
	else if(tecla == "{")
		return "F12";
	else if(tecla == "q")
		return "F2";
	else
		return "";		
}

function traduzTeclaAcao(tecla){
	if(tecla == "F7")
		return "INCLUIR";
	else if(tecla == "F8")
		return "ABRIR";
	else if(tecla == "F9")
		return "PESQUISAR";
	else if(tecla == "F10")
		return "GRAVAR";
	else if(tecla == "F12")
		return "IMPRIMIR";
}

/**
* Redefine uma tecla de atalho de evento para outra tecla.
* Caso n?o haja redefini??o para a nova tecla utilizada configura mensagem
* de alerta para tecla redefinida
* @see setAtalho
* @see getAtalho
*/
function redefinirTeclaAtalho(teclaDe, teclaPara){
	setAtalho(traduzTeclaAtalho(teclaPara), teclaDe);
	if(typeof getAtalho(traduzTeclaAtalho(teclaDe)) == "undefined"){
		setAtalho(traduzTeclaAtalho(teclaDe), "REDEFINIDA#"+teclaPara);
	}
}

/**
* Define uma tecla de atalho para uma evento novo
* @param novaTecla Nome da tecla de atalho [String,OB]
* @param labelBotao Label do bot?o associado ? nova tecla de atalho [String,OB]
* @param evento Evento associado ? nova tecla. Utilizado para tecla inteligentes [String,OB]
* @see setAtalho
* @see regBotaoEvento
*/
function definirTeclaAtalho(novaTecla, labelBotao, evento){
	setAtalho(novaTecla, evento);
	regBotaoEvento(labelBotao,evento);
}

/**
* Verifica se a a??o associada a uma tecla foi redefinida para outra.
* Neste caso envia mensagem informando qual tecla utilizar
* @variable msgTeclaRedefinida Mensagem para tecla redefinida. Deve ser definida no arquivo
* de mensagens no formato:  Esta fun??o foi redefinida para a tecla {0} [String,OB]
* @param keychar Caracter associado com a tecla pressionada. [String,OB]
* @see getAtalho
* @see setAtalho
*/
/*
//RETIRAR FUTURAMENTE
var msgTeclaRedefinida = "";
function verificaTeclaRedefinida(keychar){
	keychar = getAtalho(keychar);
	if(keychar != null && typeof keychar != "undefined" && keychar.indexOf("REDEFINIDA") > -1){
		keychar		= keychar.substring(keychar.indexOf("#") + 1, keychar.length);
		//alert(msgTeclaRedefinida.replace("{0}",keychar));
		return false;
	}
	return true;
}
*/
/**
* Fun??o que seleciona o bot?o associado ? fun??o da tecla
* @param acao A??o ? qual o bot?o est? associado
* @return botao Objeto Button representando o bot?o associado ? a??o
*/
function selBotao(acao, form)
{
	var numElements;
	var form = getForm(form);
	var elementValue;
	var retorno = false;
	var botao = null;

	if (document.forms && document.forms.length > 0)
	{
		if (typeof form != "undefined" && typeof form.elements["evento"] != "undefined")
		{
			numElements = form.elements["evento"].length;
			if (typeof numElements != "undefined"){
				for(i=0; i < numElements; i++)
				{
					elementValue = form.elements["evento"][i].value;
					if(elementValue == acao && form.elements["evento"][i].id != "RECUPERACAO_AUTOMATICA")
					{
						botao = eval(form.elements["evento"][i]);
						i = numElements;
					}
				}
			}else if (typeof form.elements["evento"] != "undefined"){
				elementValue = form.elements["evento"].value;
				if(elementValue == acao && form.elements["evento"].id != "RECUPERACAO_AUTOMATICA")
					botao = eval(form.elements["evento"]);
			} 
		}
		else if (eval(form.elements[acao]))
			botao = eval(form.elements[acao]);
		// acerto para encontrar os botoes do trinidad
		else if (document.getElementById('corpo:formulario:'+acao)) 
			botao = document.getElementById('corpo:formulario:'+acao);
	}
	return botao;
}

/**
* Fun??es para simular um clique no bot?o informado
* @param botao Nome do bot?o que ser? clicado
*/
function disparaBotao(botao){
	if(botao)
		botao.click();
}

function disparaBotaoGravar(){

	if(!plcValida.validacaoVerificarRegras())
		return false;
	else{
		setBotaoAcao(getBotaoArray('GRAVAR'));
		return true;
	}	
}
/**
* Fun??es para associar a??o a um bot?o
* @param acao A??o a ser associada ao bot?o
* @variable botaoAcao Armazena a a??o associada ao bot?o
*/
var botaoAcao = "";
function setBotaoAcao(acao)
{
	botaoAcao = acao;
}

/**
* Fun??es para associar a??o a um bot?o ao pressionar a telca ENTER
* @param acao A??o a ser associada ao bot?o
* @variable botaoAcaoEnter Armazena a a??o associada ao bot?o
*/
//FUN??O PARA SETAR A??O DO BOT?O AO PRESSIONAR ENTER
var botaoAcaoEnter = "";
function setBotaoAcaoEnter(acao)
{
	botaoAcaoEnter = acao;
}

/**
* JCompany: Devolve os valores selecionados em uma janela de sele??o popup.
* Os par?metros devem ser passados para este fun??o aos pares e na seguinte ordem: nome e valor do
* atributo.
* @see recebeSelecao
* @author: Rodrigo Magno - Powerlogic 2003 (c)
*/

function devolveSelecaoPopup(listaValores)
{
	//Compatibiliza??o
	if(arguments.length > 1)
		devolveSelecao(arguments);
	else
	{
		// caso seja um Dialog Modal!
		if (typeof dialogOpener != 'undefined') {
			dialogOpener.focaElementoInformado();
			dialogOpener.recebeSelecaoPopup(listaValores);
			dialogClose();
		} else {
			opener.focaElementoInformado();
			opener.recebeSelecaoPopup(listaValores);
			window.close();
		}
	}
}

var devolveSelecaoModal = devolveSelecaoPopup;

/**
 * JCompany: Devolve os valores selecionados em uma janela de sele??o popup.
 * Os par?metros devem ser passados para este fun??o aos pares e na seguinte ordem: nome e valor do
 * atributo.
 * @author: Claudia Seara - Powerlogic 2003 (c)
 */
function devolveSelecao()
{
	//Compatibiliza??o
	if(arguments.length == 1)
		devolveSelecaoPopup(arguments[0])
	else
	{
		// Verifica se o n?mero de argumentos ? par
		if ((arguments.length % 2) != 0 ) {
			alert("ERRO! \n\nFun??o: devolveSelecao(). \n\nErro: A quantidade de parametros passados para esta fun??o n?o ? um n?mero par." );
			return;
		}

		// Monta os vetores de nome e valor para os atributos selecionados
		var nome = new Array();
		var valor = new Array();
		var j = 0;

		for(var i = 0; i < arguments.length;) {
			nome[j] = arguments[i++];
			valor[j++] = arguments[i++];
		}

		if (typeof dialogOpener != 'undefined') {
			dialogOpener.focaElementoInformado();
			dialogOpener.recebeSelecao(nome, valor);
			dialogClose();
		} else {
			window.opener.focaElementoInformado();
			// Passa os vetores para a janela que acionou a janela de sele??o
			window.opener.recebeSelecao(nome, valor);
			// Fecha a janela de sele??o
			window.close();
		}
	}
	
}

function focaElementoInformado (){
	if (jQuery.plc.componenteFoco)
		jQuery.plc.componenteFoco.focus();
}

/**
 * JCompany: Recebe os valores selecionados em uma janela de sele??o popup e procura por atributos
 * do mesmo nome no form corrente. Caso encontre, atribui o valor selecionado ao atributo do form.
 * @author: Claudia Seara - Powerlogic 2003 (c)
 */
function recebeSelecao()
{
	//Compatibiliza??o
	if(arguments.length == 1)
		recebeSelecaoPopup(arguments[0])
	else
	{
		// Recebe os vetores de nome e valor dos atributos selecionados
		var nome 	= arguments[0];
		var valor 	= arguments[1];

		// Procura no form corrente por atributos de mesmo nome. Se achar, atribui o valor passado
		// do atributo ao atributo de mesmo nome no form.
		for(var j = 0; j < nome.length; j++) {
			for(var i = 0; i < getForm().length; i++) {

				if (getForm().elements[i].name == nome[j]) {
					getForm().elements[i].value = valor[j];
					break;
				}
			}
		}
		eval("executarEventoAplicacao('TESTAR_NIVEL')");
	}
}


/**
* Devolve a seleção popup com uma possível customização para devolver vários campos
*  Alterado por: Geraldo Matos
*/
function recebeSelecaoPopup(listaValores){
	//Compatibiliza??o
	if(arguments.length > 1)
		recebeSelecao(arguments)
	else
	{
		var campo		= "";
		var nome		= "";
		var idRetorno 	= "";
		var id 			= "";
		var separador	= "";
		// Adicao do delimitador de retorno, em caso dos valores terem ',' contido no valor.
		var retornoArray = registrarCamposRetorno (listaValores , "id,valor", "#", camposRetorno.delimPropsPlc);

		for(i = 0; i < retornoArray.length; i++) {
			idRetorno	= retornoArray[i].id;
			valRetorno	= unescape(retornoArray[i].valor);
			//valRetorno	= retornoArray[i].valor;
			for(j = 0; j < camposRetorno.length; j++) {
				nome		= unescape(camposRetorno[j].nome);
				id			= camposRetorno[j].id;
				separador	= camposRetorno[j].separador;
				if(nome == idRetorno || idRetorno == id) {
					if(setValorCampo(nome, valRetorno, separador)){
						//Marca flag de altera??o de dados
						setAlertaAlteracao();
						//
						if (idVinculadoAlterado == "") {
							alterouVinculado = true;
							idVinculadoAlterado = nome;
						}
						if (i > 1) {
							if (nome.indexOf("lookup_") != -1 && valRetorno == "") {
								ocultaLimparVinculado(nome.replace("lookup_", "") + "Limpar", false);
							}
						}
						break;
					}
				}
			}
		}
		plcGeral.aposDevolveSelecaoPopup();
		eval("executarEventoAplicacao('TESTAR_NIVEL')");
	}
}

var divs = new Array();
function setID(checkName, id)
{
	this.id = id;
	this.checkName = checkName;
}

function getID(chkName)
{
	for(i=0; i < divs.length; i++)
	{
		if(divs[i].checkName == chkName)
			return divs[i].id;
	}
	return "";
}

function setPortlet(idPortlet)
{
	if (getForm().elements["pIdPlc"])
		getForm().elements["pIdPlc"].value=idPortlet;
}

function abrirArquivo(info)
{
	var arquivo = info.substr(info.indexOf(';', 0)+1);
	var path = info.substr(0, info.indexOf(';', 0));

    var win = window.open(path, 'downloadArquivo', '');
	win.location = path + '&filename=' + '/' + arquivo;
}


function recuperaCodigo(url, msg1, msg2, campo)
{
	 var codigo = getForm().elements[campo].value;
	 if (codigo == "")
	 {
		 getForm().elements[campo].focus();
	 }
	 else if(isNaN(codigo))
	 {
		 getForm().elements[campo].select();
	 }
	 else
		 document.location.href = url+codigo;

 }

/************************************************************************
* 			L?GICAS PARA REGISTRO E EXECU??O DE EVENTOS GEN?RICOS
*
*
* Registrar eventos da aplica??o
* FORMATO: 	regEvento ("Evento","A??o Executada","Funcao Associada ? A??o");
* AC?ES:		TESTE:		Executa alguma fun??o (deve-se informar uma fun??o para teste)
*				MENSAGEM:	Envia uma mensagem
*
* EVENTOS:  Evento podem ser padr?es (definidos pelas a??es dos bot?es de a??o) ou definidos
*				pelo programador.
* Ex.:
*		Evento 'F10-Gravar':  ocorre quando o usu?rio executa grava??o de algum registro
*		Evento 'TESTAR':  	 deve ser executado pela chamada da fun??o
*									 "executarEventoAplicacao(<evento>)"
*
************************************************************************/

//Array que guarda eventos da aplica??o
var evtArray = new Array();

//Fun??es para registro de eventos
function regEvento(evento, tipoAcao, funcao)
{
	evtArray[evtArray.length] = new regEvt(evento, tipoAcao, funcao);
}

function regEvt(evento, tipoAcao, funcao)
{
		this.evento 	= evento;
		this.tipoAcao 	= tipoAcao;
		this.funcao 	= funcao;
}

//Fun??o para teste dos eventos da aplica??o
function executarEventoAplicacao(acao)
{
	var retorno = true;
	var i = 0;
	if(acao == "")
		acao = botaoAcao;

	while(i < evtArray.length)
	{
		if(evtArray[i].evento == acao)
		{
			if(evtArray[i].tipoAcao == "MENSAGEM")
			{
				retorno = enviarMensagem(evtArray[i].evento);
				break;
			}
			else if(evtArray[i].tipoAcao == "TESTE")
			{
				if (!eval(evtArray[i].funcao))
				{
					retorno = enviarMensagem(evtArray[i].evento);
					break;
				}
			}
			else if(evtArray[i].tipoAcao == "FUNCAO")
			{
					retorno = eval(evtArray[i].funcao);
					break;
			}
		}
		i++;
	}

	return retorno;
}

/************************************************************************
* 		L?GICAS PARA REGISTRO E ENVIO DE MENSAGENS DE ALERTA GEN?RICAS
*
* Registrar mensagens alertas da aplica??o
* FORMATO: regMensagem ("Evento","Tipo Mensagem","Texto Mensagem");
* EVENTO:  Deve ser um dos eventos registrados acima
* TIPOS:	  CONFIRMACAO:	Mostra janela para confirma??o
*  		  ALERTA:		Envia mensagem de alerta. (Retorna true)
*  		  ALERTA_ERRO:	Envia mensagem de alerta. (Retorna false)
*
************************************************************************/
function regMensagem(evento, tipo, msg) {
   if (this.msgArray == null) {this.msgArray = new Object();}
   this.msgArray[evento] = new regMsg(evento, tipo, msg);
}

function regMsg(evento, tipo, msg)
{
	this.evento	= evento;
	this.tipo 	= tipo;
	this.msg	= msg;
}

function getMsgArray(evento)
{
	return this.msgArray[evento];
}

function enviarMensagem(evento)
{
	auxArray = getMsgArray(evento);
	if (auxArray != null)
	{
		if(auxArray.tipo == "CONFIRMACAO")
			return confirm(auxArray.msg);
		else if (auxArray.tipo == "ALERTA")
			alert(auxArray.msg);
		else if (auxArray.tipo == "ALERTA_ERRO")
		{
			alert(auxArray.msg);
			return false;
		}
	}
	return true;
}

/******************************************************************************
* L?GICAS PARA MONTAR ARRAY DE CAMPOS E RETORNAR VALORES NESTES CAMPOS
******************************************************************************/
//Fun??o que registra os campos de retorno no array
//Array para conter os campos para l?gicas de retorno de valores
var camposRetorno = new Array();

function getCampoRetornoById(id)
{
	for(i = 0; i < camposRetorno.length; i++)
	{
		if(camposRetorno[i].id == id)
			return camposRetorno[i].nome;
	}
	return "";
}

function registrarCamposRetorno (listaRetorno, props, separador, delimitador)
{
	var termosRetorno	= listaRetorno.split(delimitador || ",");
	var propsCampo 		= props.split(",");
	var propsRetorno;
	var arrayRetorno 	= new Array();
	var separadorCampos = listaRetorno.indexOf("#") >= 0 ? "#" : "=";
	for( i = 0; i < termosRetorno.length; i++)
	{
		propsRetorno 	= termosRetorno[i].split(separadorCampos);
		if (propsRetorno.length > 2) {
			propsRetorno = [propsRetorno[0], termosRetorno[i].substring(termosRetorno[i].indexOf(separadorCampos) + 1)];
		}
		arrayRetorno[i] = new regProps(propsCampo, propsRetorno, separador);
	}
	return arrayRetorno;
}

function regProps(propsCampo, propsRetorno, separador)
{
	for(j = 0; j < propsCampo.length; j++)
		eval("this."+propsCampo[j]+" = '"+escape(propsRetorno[j])+"'");
	this.separador = separador;
}


/*----------------------------------------------------------------*\
 			FUN??ES PARA SETAR VALOR EM CAMPO INFORMADO
\*----------------------------------------------------------------*/
function set(nomeCampo, valor, separador, form) {
    setValorCampo(nomeCampo, valor, separador, form);
}

function setValorCampo(nomeCampo, valor, separador, form)
{
	
	nomeCampo = padronizaNomeCampoFormulario(nomeCampo);
	
	var campo = getCampo(nomeCampo,form);
	if(campo)
	{
		if(arguments[2])
			campo.value = concatenar(retornaValorCampo(nomeCampo), valor, separador);
		else
			campo.value = valor;
		return true;
	}
	return false;
}

/**
* Padroniza o nome do campo, conforme padrão de nomes de campos do jCompany.  
* Ex: corpo:formulario:nomeCampo
*/
function padronizaNomeCampoFormulario(nomeCampo){
	if(nomeCampo){
		var temCorpo = nomeCampo.search('corpo:')== 0;
		var temFormulario = nomeCampo.search('corpo:formulario:')== 0;
		if (temCorpo){
			if (! temFormulario)
				nomeCampo = 'corpo:formulario:' + nomeCampo.substring(6,nomeCampo.length);
		}
	}
	return nomeCampo; 
}

function setValorCampoAtualizacao(nomeCampo, valorReplace, valorNovo, separador, form)
{
	atualizaValorCampo(nomeCampo, valorReplace, valorNovo, separador, form);
	setValorCampo(nomeCampo, valorNovo, separador, form);
}

function atualizaValorCampo(nomeCampo, valorReplace, valorNovo, separador, form)
{
	var campo 	= getCampo(nomeCampo,form);
	var valor 	= "";
	var exp 	= "";
	var sepAux	= "";
	if(valorNovo == "")
		sepAux = "";
	else
		sepAux = separador;
	if(campo)
	{
		valor = campo.value;
		if(valor.indexOf(separador) < 0)
		{
			try{
				exp = new RegExp(valorReplace);
				if(valor != "")
					valor = replaceString(exp, valor, valorNovo);
			}catch(e){
				plcLog.alertaExcecao(e,"Erro ao criar expressao regular para:\n"+ valorReplace);
			}
		}else
		{
			try{
				exp = new RegExp(valorReplace+"\\"+separador);
				valor = replaceString(exp, valor, valorNovo+sepAux);
			}catch(e){
				plcLog.alertaExcecao(e,"Erro ao criar expressao regular para:\n"+ valorReplace+"\\"+separador);
			}
			try{
				exp = new RegExp("\\"+separador+valorReplace);
				valor = replaceString(exp, valor, sepAux+valorNovo);
			}catch(e){
				plcLog.alertaExcecao(e,"Erro ao criar expressao regular para:\n"+ "\\"+separador+valorReplace);
			}
		}
		campo.value = valor;
		return true;
	}
	return false;
}

/************************************************************************
* 			FUN??O PARA SETAR FOCUS EM UM CAMPO INFORMADO
************************************************************************/
var botao;
function setFocus(nomeCampo, selecionar){

	campoFocus = "";
	var campo 	= getCampo(nomeCampo);
	
	if (setaFocoAba()) {
		campo = "";
	}
	
	if(campo){
		setCampoFocus(nomeCampo);
		try{
			if((campo.type == "text" || campo.type=="password" || campo.type=="textarea" || campo.type=="file") &&
			campo.type != "hidden"  && !campo.readOnly && !campo.disabled){
					campo.focus();
					if(selecionar)
						selecionarCampo(campo.name);
			}
			else if((campo.type == "select-one" || campo.type == "select-multiple") &&
			campo.options.length > 0 && campo.options.selectedIndex <= 0 && !campo.disabled){

				campo.focus();
				campo.options[0].selected = true;
			}
			/*else if(campo.length && campo[0].type == "radio"){
				var checado = false;
				for(i = 0; i < campo.length; i++){
					if(campo[i].checked)
						checado = true;
					alert(campo[i].checked)
				}
				if(!checado)
					campo[0].checked = true;
			}*/
		}catch(e){}
	}
}

function selecionarCampo(nomeCampo){

	var campo 	= getCampo(nomeCampo);

	if(campo)
	{
		try{
			if((campo.type == "text" || campo.type=="password" || campo.type=="textarea" || campo.type=="file") &&
			campo.type != "hidden"  && !campo.readOnly && !campo.disabled){
					campo.select();
			}
			else if((campo.type == "select-one" || campo.type == "select-multiple") &&
			campo.options.length > 0 && campo.options.selectedIndex <= 0 && !campo.disabled)
			{
					campo.focus();
					campo.selected = true;
			}
		}catch(e){}
	}
}

/************************************************************************
* 			FUN??ES PARA TRATAMENTO DE RETORNO DE DATA DO CALEND?RIO
************************************************************************/
//Campo para retorno de data
var campoData;
//Fun??o para abertura do calend?rio
function abrirCalendario(url, nomeCampo, wa, ha, props)
{
	var w = 720;
	var h = 350;

	if (arguments[1])
		w = wa;
	if (arguments[2])
		h = ha;

	nomeCampo = nomeCampo+"=data";
	camposRetorno = registrarCamposRetorno (nomeCampo, "nome,id");

	if(props != "" && ""+props != "undefined"){
		janela(url, "","", props);
	}
	else if(isNaN(w) || isNaN(h)){
		janela(url, "","", "width=260, height=250, resizable=no");
	}
	else{
		janela(url, w,h);
	}
}

//Fun??o para retorno da data no campo indicado
function retData(data)
{
	campoData.value = data;
	campoData.focus();
}


function setFocusFim (campo) {
	if (campo.createTextRange) {
		var r = campo.createTextRange();
		r.moveStart('character', campo.value.length);
		r.collapse();
		r.select();
	}
	else
		setFocus(campo.name);
}

/**********************************************************************************
* 					FUN??O PARA RETORNAR UM VALOR EM UM CAMPO
**********************************************************************************/
function get(field, form)  {
   return retornaValorCampo(field, form);
}
function retornaValorCampo(field, form) {

	field = padronizaNomeCampoFormulario(field);

	var campo = "";
	if(form == "" || form == 0 || ""+form == "undefined"){
		if(getRootDocument().forms && getForm() && getForm().elements)
			campo = eval("getForm().elements['"+field+"']");
	}
	else
		campo = eval("getForm('"+form+"').elements['"+field+"']");

	if(campo)
	{
		// HACK para retira a mascara do valor.
		if (jQuery) {
			var jCompanySubmitUnmask = jQuery(campo).data('jcompany-submit-unmask');
			if (typeof jCompanySubmitUnmask == 'function') {
				return jCompanySubmitUnmask.call() || "";
			}
		}
		
		plcLog.debug("retornaValorCampo - nome campo: "+campo.name);
		plcLog.debug("retornaValorCampo - tipo campo: "+campo.type);
		//Acerto para resolver problemas de campos duplicados inclu?dos pela
		//gera??o via plugin
		//Alterado: 16/12/2005
		if(campo.length > 0 && campo[0]){
			if(	campo[0].type == "text" || campo[0].type == "hidden" || campo[0].type == "textarea"  ||
				campo[0].type == "file" || campo[0].type == "password")
				campo = campo[0];
		}
		if(	campo.type == "text" || campo.type == "hidden" || campo.type == "textarea" ||
			campo.type == "file" || campo.type == "password")
		{
			return campo.value;
		}
		else if (campo.type == "checkbox")
		{
			if(campo.checked)
				return campo.value;
			else{
				if(getVarGlobal("uncheck_"+campo.name))
					return getVarGlobal("uncheck_"+campo.name);
				else
					return "N";
			}
		}
		else if (campo.type == "select-one")
		{
			return campo.options[campo.selectedIndex].value;
		}
		else if (campo.type == "select-multiple")
		{
			var valSelect = new Object();
			for(i = 0; i < campo.length; i++){
				if(campo.options[i].selected){
					valSelect[valSelect.length] = campo.options[i].value;
				}
			}
			return valSelect;
		}
		else if (campo.type == "radio")
		{
			if(campo.checked)
				return campo.value;
		}
		else //if (typeof campo.type == "undefined")
		{
			plcLog.debug("retornaValorCampo - tamanho campo: "+campo.length);
			
			plcLog.debug(campo.name+' '+campo.length);
			for(var i = 0; i < campo.length; i++){
				plcLog.debug("retornaValorCampo - valor campo: "+campo[i].value);
				plcLog.debug("retornaValorCampo - check campo: "+campo[i].checked);
				if(campo[i].checked){
					return campo[i].value;
				}
			}
		}
	}

	return "";
}
/**
 * Pega valor de campo de formulário quando usando apache trinidad para renderizar.
 * Para pegar de itens pegar o indice com '#{plcItensStatus.index}'
*/
function getValorJsf(field, indice, detalhe)  {
   if (indice != null) {
		if (detalhe == null){
			// Assume iteracao 
			field = "corpo:formulario:plcLogicaItens:"+indice+":"+field;
		}else{
			field = "corpo:formulario:"+detalhe+":"+indice+":"+field;
		}
   } else {
     field = "corpo:formulario:"+field;     
   }
   return retornaValorCampo(field);
}
/**
 * Pega setar valor em campo de formulário quando usando apache trinidad para renderizar.
 * Para setar de itens pegar o indice com '#{plcItensStatus.index}'
*/
function setValorJsf(field, indice, valor)  {
   if (indice != null) {
   	// Assume iteracao 
   	 field = "corpo:formulario:plcLogicaItens:"+indice+":"+field;
   } else {
     field = "corpo:formulario:"+field;     
   }
   return set(field,valor);
}
/**
 * Pega uma referencia a campo de formulário quando usando apache trinidad para renderizar.
 * Para pegar de itens pegar o indice com '#{plcItensStatus.index}'
*/
function getObjetoJsf(field, indice)  {
   if (indice != null) {
   	// Assume iteracao 
   	 field = "corpo:formulario:plcLogicaItens:"+indice+":"+field;
   } else {
     field = "corpo:formulario:"+field;     
   }
   return getCampo(field);
}

/**********************************************************************************
* 					FUN??O PARA INSERIR UM VALOR EM UM CAMPO
**********************************************************************************/
function insereValorCampo(field,value,form)
{
	var campo = "";
	if(form == "" || form == 0 || ""+form == "undefined")
		campo = eval("getForm().elements['"+field+"']");
	else
		campo = eval("getForm('"+form+"').elements['"+field+"']");
	if(campo) {
		if (campo.type == "select-one")	{	
			for(i = 0; i < campo.options.length; i++) {
				if(campo.options[i].value == value)
				{
					campo.options[i].selected = true;
					i = campo.options.length;
				}
			}
		} else {
			campo.value = value;
		}
	}
}

/**********************************************************************************
* 					FUN??O PARA REDIRECIONAMENTO DE ENDERE?O
**********************************************************************************/
/**
* @deprecated Mantida por compatibilidade. Utilizar submeteUrl
*/
function redirect(url)
{
	document.location.href=url;
}

/**
 * Submete (GET) a URL informada, na mesma instancia
 */
function submeteUrl(url)
{
	redirect(url);
}

/**
* fun??o mantida para compatibilidade com antigas manuten??es
*/ 
function redirectPopup(url){

	if(getParametroUrl("modoJanelaPlc") != "")
		redirect(url+"&modoJanelaPlc=popup");
	else
		redirect(url);

}
/**********************************************************************************
* 						FUN??ES PARA AVALIA??O DE EXPRESS?ES
**********************************************************************************/

/******************AVALIA??O DE EXPRESS?ES COM REPETI??O DE CAMPOS ***************
=> Avalia a express?o passada
=> Se informado um campo para retorno este ir? receber o valor da expresss?o
avaliada.
=> Sintaxe: avaliaExpressao(expressao, campoRetorno)
=> Par?metros ( O = Obrigat?rio)
	expressao 	= express?o para ser avaliada
		Para a composi??o da express?o podem ser utilizado algarismos, operadores,
		par?nteses e campos da aplica??o.
		Quando for utilizar campos informar o nome do campo entre "#".
		Exemplo: ((10-20)*5+#campo1#)/campo2
	campoRetorno= campo para onde ser? enviado o valor da express?o avaliada
*********************************************************************************/

function avaliaExpressao(expressao, campoRetorno)
{
	var debug = false;
	//Parsing de express?o
	var expAux 		= "";
	var campo 		= "";
	var valCampo 	= 0;
	var expEval 	= "";
	var index 		= 0;
	var resultado	= 0;
	if(expressao.indexOf(" ") > -1)
	{
		alert("Express?o n?o pode conter espa?os.");
		return;
	}
	else
	{
		index 	= expressao.indexOf("#");
		while(index > -1)
		{
			expEval += expressao.substring(0,index);
			expressao = expressao.substring(index+1,expressao.length);
			index = expressao.indexOf("#");
			campo = expressao.substring(0,index);
			expressao = expressao.substring(index+1,expressao.length);
			valCampo = retornaValorCampo("",campo);
			expEval += valCampo;
			index = expressao.indexOf("#");
		}
		expEval += expressao;
		expressao = expEval;
	}
	if(eval(expressao))
	{
		resultado = eval(expressao);
		campo = eval("getForm().elements['"+campoRetorno+"']");
		if(campo)
			campo.value = resultado;
		return resultado;
	}
	else
		alert("Express?o inv?lida.");
}
/**
* FUN??ES PARA TORNAR VIS?VEIS/INVIS?VEIS CAMPOS DO FORM
*/
function alteraEstadoCampo(tipo, estado)
{
  if (!document.all)
  {
    return; // only Internet Explorer is affected by the bug
  }
  var numForms 	= document.forms.length;
  var numCampos	= "";
  var numTipos		= separaListaTermos(tipo,",");
  var find 			= false;
  for (var i = 0; i < numForms; i++)
  {
    numCampos = document.forms[i].elements.length;
    for (var j = 0; j < numCampos; j++)
    {
	    for (var k = 0; k < numTipos || find; k++)
	    {
	      if (document.forms[i].elements[j].type == numTipos[k])
	      {
	        document.forms[i].elements[j].style.visibility = estado;
	        find = true;
	      }
	    }
    }
  }
}

/**********************************************************************************
* 			FUN??O PARA CONCATENAR DOIS VALORES COM O SEPARADOR INFORMADO
**********************************************************************************/
function concatenar(oldValue, newValue, separator,doisLados)
{
	var valRetorno = oldValue;
	if(newValue != "")
	{
		if(oldValue == "") {
			if (doisLados)
				valRetorno = separator+newValue+separator;
			 else
				valRetorno = newValue;
		
		}
		else if (doisLados) {
			if (oldValue.indexOf(separator+newValue+separator) == -1)
				valRetorno = oldValue + newValue+separator;
		} else if (oldValue.indexOf(newValue) == -1)
			valRetorno = oldValue + separator + newValue;
	}
	return valRetorno;
}


/**
* 			FUN??O PARA SEPARAR UM LISTA DE TERMOS EM ARRAY
*/
function separaListaTermos (str, separador)
{
	var termos = new Array();
	termos = str.split(separador);
	return termos;
}

/**********************************************************************************
* 					FUN??O PARA C?LCULO DE VALORES
**********************************************************************************/
function retornaCalculo(oper, incremento, maximo, minimo, campoRetorno)
{
	var format = "00";
	var campo = eval("getForm().elements['"+campoRetorno+"']");
	var resultado;
	var val = parseFloat(campo.value);
	resultado = eval(val + oper + incremento);
	if(!isNaN(resultado) && (resultado <= maximo && resultado >= minimo))
	{
		var auxResultado = "";
		var resultado = new String(resultado);
		if(format.length > resultado.length)
		{
			var posRes = 0;
			for(i = (format.length - 1); i >= 0; i--)
			{
				if(i <= resultado.length - 1)
				{
					auxResultado += parseFloat(format.charAt(i))+parseFloat(resultado.charAt(posRes));
					posRes++;
				}
				else
					auxResultado += parseFloat(format.charAt(i));
			}
			if(campo)
				campo.value = auxResultado;
			else
				return auxResultado;
		}
		else
		{
			if(campo)
				campo.value = resultado;
			else
				return resultado;
		}
	}
}

function selecaoPopup(url, listaCampos, separador, larg, alt, posX, posY, alvo, delimPropsPlc)
{
	camposRetorno = registrarCamposRetorno(listaCampos, "nome,id", separador);
	if (camposRetorno) {
		camposRetorno.delimPropsPlc = delimPropsPlc;
	}
	 var janelaRetornada = janela(url,larg,alt,"",alvo,"",posX, posY);
// TODO garantir foco na popup
//	 setTimeout('focoJanela('+janelaRetornada+')',500);
	 return janelaRetornada;
}

function selecaoModal(url, listaCampos, separador, larg, alt, posX, posY, alvo, delimPropsPlc)
{
	// Registra os campos de retorno, no escopo da janela que pediu o Modal Window!
	window.camposRetorno = registrarCamposRetorno(listaCampos, "nome,id", separador);
	if (window.camposRetorno) {
		window.camposRetorno.delimPropsPlc = delimPropsPlc;
	}
	// Cria a Janela Modal!
	var dialog = jQuery.plc.janelaModal({
		title: 'Sele&ccedil;&atilde;o'
		,url: url.replace('modal','popup')
		,width: (larg != '' ? parseInt(larg) : '')
		,height: (alt != '' ? parseInt(alt) : '')
	});
}

function focoJanela(janela) {
	 janela.focus();
}

/******************************************************************************
* FUN??O QUE RETORNA UMA STRING PARA USO EM URL, CONTENDO O NOME DO CAMPO+VALOR DO CAMPO,
* CONCATENANDO TODOS OS CAMPOS PASSADOS POR ARGUMENTO.
* Recebe: Nome do Form + Nomes dos Campos
* Devolve: URL no formato exemplo: campo1=<valor1>&campo2=<valor2>...
******************************************************************************/
function geraLink()
{
   var form = arguments[0];
   var link = "";
   var arrayTermos;
   for (i=1;i<arguments.length;i++) {
		if (i > 1) link+="&";

		if(arguments[i].indexOf(",") >= 0)
		{
			arrayTermos = separaListaTermos(arguments[i],",");
			link+=arrayTermos[1]+"="+retornaValorCampo(arrayTermos[0],form);
		}
        else
    		link+=arguments[i]+"="+retornaValorCampo(arguments[i],form);
   }
   return link;
}


/******************************************************************************
* FUN??O PARA DIFERENCIAR LINHA PARA EXCLUS?O EM L?GICAS TABULARES
******************************************************************************/

function marcarExclusaoDetalhe(chave, checkbox, evt){
	var campo = getCampo('corpo:formulario:indExcDetPlc') || getCampo('indExcDetPlc');
	
	if (!campo) {
		return;
	}
	
	if (!checkbox.checked) {
		campo.value = campo.value.replace('#'+chave+'#', '#');
	} else if (campo.value.indexOf('#'+ chave +'#') == -1) {
		campo.value = concatenar(campo.value, chave, '#', true);
	}
	
	if (campo.value == '#') {
		campo.value = '';
	}
}

function marcarExclusao(checkbox, evt)
{
	// TODO Struts
	//marcarExclusaoDetalhe(checkbox.name.substring(0,checkbox.name.indexOf("[")), checkbox, evt)
	marcarExclusaoDetalhe(checkbox.name, checkbox, evt)

	checarUm(checkbox);
}

function manterMarcaExc(val, frm){
	frm 		= getForm(frm);
	if(frm)
	{
		var EL	= frm.elements;
		for (var i = 0; i < EL.length; i++)
		{
			var e = EL[i];
			if ((e.name.indexOf("indExcPlc") >= 0) && (e.type == 'checkbox'))
			{
				if(e.checked || e.value == "S")
				{	e.click(); e.click();}
				else
					setClasse(e, "TR", "");
			}
		}
	}
}

function checarTodos(CT, CBID, nomeChk, frm){
	frm  		= getForm(frm);
	CT   		= getCheckTodos(CT, frm);
	CBID 		= getCheckExc(CBID, frm);
	nomeChk 	= getNomeChk(nomeChk);
	for (var i = 0; i < CBID.length; i++)
	{
		var e=CBID[i];
		if ((e.name.indexOf(nomeChk) >= 0) && (e.type=='checkbox'))
		{
			e.checked = CT.checked;
			testarChekbox(e);
		}
	}
}

function checarUm(CB, CT, nomeChk, CBID, frm)
{
	frm  		= getForm(frm);
	CT	  		= getCheckTodos(CT, frm);
	CBID 		= getCheckExc(CBID, frm);
	nomeChk 	= getNomeChk(nomeChk);
	testarChekbox(CB);
	var TB=TO=0;
	for (var i=0;i < CBID.length;i++)
	{
		var e = CBID[i];
		if ((e.name && e.name.indexOf(nomeChk) >= 0) && (e.type=='checkbox'))
		{
			TO++;
			if (e.checked)	TB++;
		}
	}
	CT.checked=(TO==TB)?true:false;
}

function testarChekbox(CHK)
{
	var tag = "TR";
	if (CHK.checked) setClasse(CHK, tag, "campoComErro plc-linha-destaque");
	else setClasse(CHK, tag, "");
}

function setClasse(E, tag, classe)
{
	var Etag = E.tagName;
	while (Etag != tag)
	{
		if(ExpYes)
			E = E.parentElement;
		else
			E = E.parentNode;
		Etag = E.tagName;
	}
	E.className = classe;
	E.marcado 	= true;

}
function getCheckTodos(CT, frm)
{
	if(""+CT != "undefined" && CT != "") 	return CT;
	else if(frm.cbTodos)
		return frm.cbTodos;
	else
		return frm;
}

function getCheckExc(CBID, frm)
{
	if(""+CBID != "undefined" && CBID != "")
	{
		if(frm.CBID)
			return frm.CBID;
	}
	return frm.elements;
}

function getNomeChk(nomeChk)
{
	if(""+nomeChk != "undefined" && nomeChk != "")
			return nomeChk;
	return "indExcPlc";
}

/* ---------------------------------------------------------------------- *\
  Function    : setVarGlobal
  Description : set a variable with a global scope
  Usage       : setVarGlobal(varName, value);
  Arguments   : varName - name of the global variable to set
                value - value of the global variable to set
\* ---------------------------------------------------------------------- */
function setVarGlobal(nome, valor) {
   if (this.cache == null) {this.cache = new Object();}
   this.cache[nome] = valor;
}
/* ---------------------------------------------------------------------- *\
  Function    : getGlobalVar
  Description : get a variable in a global scope
  Usage       : value = getGlobalVar(varName);
  Arguments   : varName - name of the global variable to get
                value - value of the global variable to get
\* ---------------------------------------------------------------------- */
function getVarGlobal(nome, valor) {
   if (this.cache == null) {
     return null;
   } else {
     return this.cache[nome];
   }
}

/* ---------------------------------------------------------------------- *\
	FUN??O PARA TRATAMENTO DE ERROS
\* ---------------------------------------------------------------------- */
function stoperror(){

//	var strErro = 	"ALERTA DE ERRO\n";
// TODO Retornar apos testar em IE 7
//	var strErro = "ALERTA DE ERRO. Ocorreu um erro no javascript desta pagina.\n";
	//TODO VOLTAR COM ESSE CODIGO
	//alert(strErro+"Mensagem: "+arguments[0]+"\n"+arguments[1]+" [Linha: "+arguments[2]+"]");
	return true;
}
window.onerror=stoperror;


function hideFormSelect()
{
  if (!document.all)
  {
    return; // only Internet Explorer is affected by the bug
  }
  var dfl = document.forms.length;
  for (var i = 0; i < dfl; i++)
  {
    var dfle = document.forms[i].elements.length;
    for (var j = 0; j < dfle; j++)
    {
      if (document.forms[i].elements[j].type && document.forms[i].elements[j].type.indexOf('sel') != -1)
      {
        document.forms[i].elements[j].style.visibility = 'hidden'
      }
    }
  }
}

function showFormSelect()
{
  if (!document.all)
  {
    return; // only Internet Explorer is affected by the bug
  }
  var dfl = document.forms.length;
  for (var i = 0; i < dfl; i++)
  {
    var dfle = document.forms[i].elements.length;
    for (var j = 0; j < dfle; j++)
    {
      if (document.forms[i].elements[j].type && document.forms[i].elements[j].type.indexOf('sel') != -1)
      {
        document.forms[i].elements[j].style.visibility = 'visible';
      }
    }
  }
}

function hideIframe()
{
	if(document.frames){
		for(i = 0; i < document.frames.length; i++)
		{
			try{
				var ifUrl = document.frames[i].location.href;
				setVisible(document.frames[i].name, "hidden");
			}catch(e){
				plcLog.debug("IFRAME SEM PERMISSAO DE ACESSO");
			}
		}
	}
}

function showIframe()
{
	if(document.frames){
		for(i = 0; i < document.frames.length; i++)
		{
			try{
				var ifUrl = document.frames[i].location.href;
				setVisible(document.frames[i].name, "visible")
			}catch(e){
				plcLog.debug("IFRAME SEM PERMISSAO DE ACESSO");
			}
		}
	}
}

/*
* Fecha a janela
*/
function fecharJanela()
{
	window.close();
}



/*
* Abre janela utilizando a resolu??o dispon?vel.
*/
function janelaMax(url) {
    var win;
    var w = screen.availWidth -3;
    var h = screen.availHeight -25;

    win = window.open(url,"","resizable=yes,scrollbars=yes,left=0,top=0,width="+w+",height="+h);
}



/**********************************************************
Formatar a data digitada
--------------------------------------------
Fun??o:     formataData(Campo,Prox,tammax,teclapres)
--------------------------------------------

=> Campo =  Tipo: String
        Nome do campo de data
=> prox  =  Tipo: String
        Nome do pr?ximo campo
=> tammax = Tipo: int [8]
        Tamanho m?ximo permitido
=> teclapres =  Tipo: event [event]
        O evento disparado para chamar a fun??o
        (tecla pressionada, por exemplo)

<Chamar no ONKEYPRESS do campo>

Exemplo:    formataData('fldData',event);

Obs.: A data digitada aparecer? no formato: dd/mm/aaaa

**********************************************************/
function formataData(campo,evt) {
   formataDataComMascara(campo,evt,"dd/MM/yyyy");		
}

function formataDataComMascara(campo,evt,mascara)
{

	if (mascara != "dd/MM/yyyy" && mascara != "MM/yyyy" && mascara != "MM/yy" & mascara != "dd/MM/yy")
		alert('Máscara inválida. Informe uma de [dd/MM/yyyy] [MM/yyyy] [MM/yy] [dd/MM/yy]');

    if(NavYes)
        var tecla = evt.which;
    else
        var tecla = evt.keyCode;

    vr = retornaValorCampo(campo);
    vr = vr.replace( ".", "" );
    vr = vr.replace( "/", "" );
    vr = vr.replace( "/", "" );
    vr = vr.replace( "/", "" );
    tam = vr.length +1;
    if ( tecla != 9 && tecla != 8 && tecla != 0 )
    {
    	if (mascara=="dd/MM/yyyy" || mascara=="dd/MM/yy") {
			if ( tam ==2 && vr>'3')
				vr = '';
			else if ( tam ==3 && vr>'31')
				vr = vr.substr( 0, 1 );
			else {
				if ( tam > 2 && tam < 5 )
					vr = vr.substr( 0, 2 ) + '/' + vr.substr(2, tam );
				if ( mascara=="dd/MM/yyyy" && tam >= 5 && tam <= 10 )
					vr = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 3 );
				if ( mascara=="dd/MM/yy" && tam >= 5 && tam <= 8 )
					vr = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 2, 3 ); 
			}
         } else if (mascara=="MM/yyyy") {
			 if ( tam ==2 && vr>'1')
				vr = '';
			 if ( tam ==3 && vr>'12')
				vr = vr.substr( 0, 1 );
			 else if ( tam > 2) {
				vr = vr.substr( 0, 2 ) + '/' + vr.substr(2, 3 );
				}
		 } else if (mascara=="MM/yy") {
			 if ( tam ==2 && vr>'1')
				vr = '';
			 if ( tam ==3 && vr>'12')
				vr = vr.substr( 0, 1 );	
			 else if ( tam > 2)
					vr = vr.substr( 0, 2 ) + '/' + vr.substr(2, 1 );
	     }
		
		setValorCampo(campo, vr);
        
        return false;
    }
}

/****************************************************************
Bloqueia digita??o de caracter n?o permitido pelo tipo
------------------------------------------------
Fun??o:		validaCaracter(campo, evt, tipo)
------------------------------------------------

=> campo  =	Tipo: String
		Nome do campo atual
=> tipo   =	Tipo: String
		Tipo do valor no campo [D=Data; V=Valor ; H=Hora]
=> evt    =	[event]
		O evento disparado para chamar a fun??o
		(tecla pressionada, por exemplo)

<Chamar no ONKEYDOWN do campo testando seu retorno>

Exemplo:	return validaCaracter('fldCPF',event, "V");

****************************************************************/
function validaCaracter(campo, evt, tipo)
{
	//Contribui??o Dionatan Almeida
   var key;
   var keychar;
	key = getKeyCode(evt);	
   /**
   * C?digos de teclas do teclado num?rico
	de 96 ? 105 =  0 - 9
        106 = *
        107 = +
        109 = -
        110 = ,
        111 = /
        194 = .
      */

   // array das setas
   var keyseta = new Array(37,39);

   // array dos numeros + setas
   var keynum = new Array(96,97,98,99,100,101,102,103,104,105,37,39);

   // array de data + numeros
   var keynumD = new Array(96,97,98,99,100,101,102,103,104,105,39,111);

   // array dos numeros
   var keydigit = new Array(96,97,98,99,100,101,102,103,104,105);

   keychar = String.fromCharCode(key);

   var ehValido = false;

   if ((key==null) || (key==0) || (key==8) || (key==9)|| (key==27) ||
		(key==46))
        ehValido = true;
   else if (tipo=="V" && ((("0123456789").indexOf(keychar) > -1) ||
		validaKeyArray(key,keynum)))
   		ehValido = true;
   else if (tipo=="D" && ((("/0123456789").indexOf(keychar) > -1) ||
		validaKeyArray(key,keynumD)))
	    ehValido = true;
   else if (tipo=="DT" && (((" :/0123456789").indexOf(keychar) > -1) ||
		validaKeyArray(key,keynumD)))
	    ehValido = true;
   else if (tipo=="H" && (((":0123456789").indexOf(keychar) > -1) ||
		validaKeyArray(key,keynum)))
   		ehValido = true;
   else if (tipo=="A" && (("0123456789").indexOf(keychar) == -1 || 
   			validaKeyArray(key,keyseta)))
		ehValido = true;
	
	if (!ehValido && campo != null) {
		while (campo.value.indexOf(keychar) > -1 || campo.value.indexOf(keychar.toLowerCase()) > -1){
				campo.value = campo.value.replace(keychar, "");
				campo.value = campo.value.replace(keychar.toLowerCase(), "");
		}
	}	
	
	//Adaptação para Internet Explorer, visto que, no Internet Explorer, o keychar não chega corretamente para os símbolos abaixo. 
	// TODO ver como melhorar, pois no internet explorer continua aceitando / e : por causa de Data
	/* 
	231 - ç
	6   - ¨
	252 - ü
	219 - ´
	180 - ´´
	199 - Ç
	220 - Ü 
	*/
	var caracteresEspeciais = new Array(String.fromCharCode(231),
										String.fromCharCode(6),
										String.fromCharCode(252),
										String.fromCharCode(219),
										String.fromCharCode(180),
										String.fromCharCode(199),
										String.fromCharCode(220),
										"!","@","#","$","%","^","&","*","(",")",
										"_","-","+","=","{","}","|","[","]","\\",
										"\"",":",";","'","<",">","?",",",";","`","~");
	
	for (var i=0;i<caracteresEspeciais.length;i++){
		var caracter = caracteresEspeciais[i];
		if (campo.value.indexOf(caracter) > -1){
			campo.value = campo.value.replace(caracter, "");
		}
	}
	
   return ehValido;
}

function validaKeyArray(keycode, keyArray){
	var achou = false;

	if(keyArray != null){
		for(var i = 0; i < keyArray.length; i++){
			//alert("keycode:"+keycode+" = keyArray["+i+"]:"+keyArray[i]);
			if(keyArray[i] == keycode){
				achou = true;
				break;
			}
		}
		return achou;
	} else {
		return true;
	}
}

/***********************************************************************************
Filtra dados do campo - Utilizado apenas pelo Netscape
***********************************************************************************/
function filtraCampo(valor){
	var s = "";
	var cp = "";
	vr = valor;
	tam = vr.length;

	for (i = 0; i < tam ; i++)
	{
		if (vr.substring(i,i + 1) != "/" && vr.substring(i,i + 1) != "-" && 			vr.substring(i,i + 1) != "."  && vr.substring(i,i + 1) != "," )
		{
		 	s = s + vr.substring(i,i + 1);
		}
	}
	return s
}

/************************************************************
Saltar para o pr?ximo campo
--------------------------------------------------------
Fun??o:		saltaCampo (campo,prox,tammax,teclapres)
--------------------------------------------------------

=> campo =	Tipo: String
		Nome do campo atual
=> prox  =	Tipo: String
		Nome do pr?ximo campo
=> tammax =	Tipo: int
		Tamanho m?ximo do campo atual
=> teclapres =	[event]
		O evento disparado para chamar a fun??o
		(tecla pressionada, por exemplo)

<Chamar no ONKEYUP do campo>

Exemplo:	saltaCampo('fldatual','fldprox',5,event);

************************************************************/

function saltaCampo (campo,prox,tammax,teclapres,form)
{
    if(NNav)
        var tecla = teclapres.which;
    else
        var tecla = teclapres.keyCode;

	var ssProx 	= getCampo(prox, form);
	var vr = retornaValorCampo(campo,form);
 	var tam = vr.length;

 	if (tecla != 0 && tecla != 9 && tecla != 16)
	{
		if(tecla == 13)
			ssProx.focus();
		else if ( tam == tammax )
		{
			ssProx.focus() ;
		}
	}
}

function setFuncaoOnLoad(decFunction) {
   if (this.cacheFunction == null) {this.cacheFunction = new Array();}
   this.cacheFunction[this.cacheFunction.length] = decFunction;
}

function executarFuncaoOnLoad()
{
	plcLog.debug("##### Entrou para executar funcoes no onload")
	plcLog.debug("FUNCOES: "+this.cacheFunction);

	if(this.cacheFunction != null)
	{
		var i=0;
		while(i < this.cacheFunction.length){

			try{
				eval(this.cacheFunction[i]);
			}catch(e){
				plcLog.alertaExcecao(e,"Erro ao executar funcao no onload. Funcao executada: "+this.cacheFunction[i]);
			}

			i++;

		}

		/*for(i = 0; i < this.cacheFunction.length; i++){
			try{
				eval(this.cacheFunction[i]);
			}catch(e){
				plcLog.alertaExcecao(e,"Erro ao executar funcao no onload. Funcao executada: "+this.cacheFunction[i]);
	}
		}*/
		
}
}

function replaceString(exp, str, repl){
	var strRepl = new String(str);
	strRepl = strRepl.replace(exp,repl);
	return strRepl;
}

function stringPrimeiraMaiuscula(str){
	return str.substring(0,1).toUpperCase() + str.substring(1,str.length);
}

/*-------------------------------------*\
	INICIO FUN??ES PARA TRATAMENTO URL
\*-------------------------------------*/

function limparUrl(url,str)
{
	var pos = url.indexOf(str);
	if(pos >= 0)
		return url.substring(0,pos);
	else
		return url;
}

function incluirIdSessao(url, id)
{
	var exp = new RegExp("\\.do");
	url = replaceString(exp, url, ".do;jsessionid="+id);
	return url;
}

//FIM FUN??ES PARA TRATAMENTO URL


/*----------------------------------------*\
	INICIO FUN??ES PARA TRATAMENTO IMAGENS
\*----------------------------------------*/
var images = new Array();
function regImg(nome, src, alt)
{
	this.nome = nome;
	this.src  = src;
	this.alt  = alt;
}

function getImagem(nome)
{
	for(i = 0; i < images.length; i++)
	{
		if(images[i].nome == nome)
			return images[i];
	}
	return false;
}

function getImagemById(id)
{
	return document.images[id];
}

function alteraImagem(id, src, alt)
{
	var img = getImagemById(id);
	if(img)
	{
		img.src = src;
		img.alt = alt;
	}
}

function alteraImagems(id, src, alt)
{
	if(document.images){
		for(i=0; i<document.images.length; i++)
		{
			if(document.images[i] && document.images[i].id == id) {
				if(document.images[i].src)
					document.images[i].src = src;
				if(document.images[i].alt)
					document.images[i].alt = alt;
			}
		}
	}
}

/**
 * Fun??o para retornar multiplos valores.<b>
 * adiciona o valor no campo registrado para a chave idsPlc e descPlc
 * no idsPlc ser? adicionado os valos do campo com id idsPlc da p?gina de sele??o
 * no descPlc ser? adicionado os valos do campo com id descPlc da p?gina de sele??o
 */
function retornarMultiSel()
{
	var checks 	= getCampo("indExcPlc");	//checkbox
	var idsPlc 	= getCampo("idsPlc");	// ids de todas as linhas
	var descPlc = getCampo("descPlc");	// descri??o de todas as linhas

	//se encontrou o objeto
	if(checks){
		if(!checks.length){	//se for apenas 1 check, o javascript trava n?o como array e sim como apenas umc campo
			if (checks.checked){
				opener.setValorCampo(opener.getCampoRetornoById('idsPlc'),idsPlc.value,",");
				opener.setValorCampo(opener.getCampoRetornoById('descPlc'),descPlc.value,"\n");
			}
		}

		//para cada check marcado, seta o valor nos campos. n?o adiciona valores repetidos
		for (var i = 0; i < checks.length; i++){
			var e = checks[i];
			if (e.checked){
				opener.setValorCampo(opener.getCampoRetornoById('idsPlc'),idsPlc[i].value,",");
				opener.setValorCampo(opener.getCampoRetornoById('descPlc'),descPlc[i].value,"\n");
			}
		}

		window.close();
	}
}

/* 
 
 		Funções abaixo feitos para seleção multipla
 
 */
var arrayCamposRetorno = new Array();
var posicaoDetalhe = 0;
function selecaoPopupMulti(url, listaCampos, separador, larg, alt, posX, posY, alvo){

	//corpo:formulario:(\\w*):(\\d*):(\\w*)
	//corpo:formulario:(\\w*):(\\d*):(\\w*)#(\\w*)
	//(\w*)corpo:formulario:(\w*):(\d*):(\w*)#(\w*)
	
	var padrao = new RegExp ("(\\w*)corpo:formulario:(\\w*):(\\d*):(\\w*)#(\\w*)");
	var array = padrao.exec(listaCampos);

	posicaoDetalhe = parseInt(array[3]);
	var count = 0;

	while (true){
		array = padrao.exec(listaCampos);
	
		if (array == null)
			break;

		var obj = new Object ();
		var expresao = array[0];
		obj.prefix = array[1]
		obj.detalhe = array[2];
		obj.campoDetalhe = array[4];
		obj.varSubstituto = array[5];
		arrayCamposRetorno[count] = obj;
		
		listaCampos = listaCampos.replace(expresao,"");

		count++;
	}

	janela(url,larg,alt,"",alvo,"",posX, posY);
}
/* 
 * Em caso de recuperacão Multipla, guardar na variavel as linhas selecionadas
 */
var valorSelMulti = new Array();
function guardaSelecaoMultiJSF(valores, tagLinha){

	var expr = new RegExp ("corpo:formulario:plcLogicaItens:([0-9]*):linhaSel");
	var padroes = expr.exec (tagLinha.id);

	var existeEmArray = false;

	for (var i=0; i<valorSelMulti.length; i++){
		var arrayObj = valorSelMulti[i];
		if (arrayObj.valor == valores){
			existeEmArray = true;		
		}
	}

	if (!existeEmArray){
		var obj = new Object();
		obj.indice = padroes[1];
		obj.valor = valores;

		valorSelMulti[valorSelMulti.length] = obj;
	}
}

function retornarMultiSelJSF (){
	
	var devolveSelecao = new Array();
	
	var count = 0;
	
	for(var i=0; i<valorSelMulti.length; i++){
		
		var obj = valorSelMulti[i];
		var idIndExcPlc = "corpo:formulario:plcLogicaItens:" + obj.indice + ":indMultiSelPlc";
		var tagIndExcPlc = getRootDocument().getElementById(idIndExcPlc);
		
		if (typeof tagIndExcPlc != "undefined" && tagIndExcPlc.checked){
			tagIndExcPlc.checked = false;
			var valor = obj.valor;
			devolveSelecao [count] = valor;
			count++;
		}
		
	}
	
	opener.devolveSelecaoMultipla (devolveSelecao); 
	
	window.close();
	
}

function devolveSelecaoMultipla (devolveSelecaoMulti){

	

	for (var j=0; j<arrayCamposRetorno.length; j++){
		
		var count = 0;
		
		for (var i=0; i<devolveSelecaoMulti.length; i++){
		
			var devolveSelecao = devolveSelecaoMulti[i];
			var campos = devolveSelecao.split(",");
			
			for (var t=0; t<campos.length; t++){
				var campoValor = campos[t].split("#");
			
				var nomeCampo = campoValor[0];
				var valor = campoValor[1];
				var obj = arrayCamposRetorno[j];
				
				if (nomeCampo == obj.varSubstituto){
					var posicaoTag = posicaoDetalhe + count;

					var idTag = obj.prefix + "corpo:formulario:" + obj.detalhe + ":" + posicaoTag.toString() + ":" + obj.campoDetalhe;
					var tag = getElementoPorId (idTag);
			
					if (tag != null && typeof tag != "undefined"){
						tag.value = valor;
					}	
				}

			}
			count++;
	
		}
	}
	
	
}


/*-----------------------------------------------------*\
	INICIO FUN??ES PARA TRATAMENTO CRITERIO ORDENACAO
	-------------------------------------------------
 	Utiliza??o:
 	<a href="#" onclick="setValorCampoAtualizacao('saida', 'obj.id '+getCriterioOrdenacao('id'), montaCriterioNovo('id', 'obj'), ',');
 	substituirImagem('id')">C?digo</a>
\*-----------------------------------------------------*/

function atualizaCriterio(nomeCampo, valorReplace, valorNovo, separador, form)
{
	atualizaValorCampo(nomeCampo, valorReplace, valorNovo, separador, form);
	setValorCampo(nomeCampo, valorNovo, separador, form);
	setValorCampo(nomeCampo, ordenaCriterio (nomeCampo, retornaValorCampo(nomeCampo), separador));
}

var ordem = new Array();
function ordenaCriterio (campo, listaCampos, separador)
{
	listaOrdenada = "";
	for (i = 0; i < ordem.length; i++)
	{
		if(ordem[i] != "undefined" && listaCampos.indexOf(ordem[i]+" ") >= 0)
		{
			var criterio = getCriterioOrdenacao(ordem[i]);
			if (criterio != ""){
				if(listaOrdenada != "")
					listaOrdenada += separador;
				listaOrdenada += ordem[i]+" "+getCriterioOrdenacao(ordem[i]);
			}
		}
	}
	return listaOrdenada;
}

function atualizaCriterioOrdenacao(chave)
{
	var criterio = getVarGlobal(chave);
	if(criterio == "desc")
		setVarGlobal(chave, "");
	else if(criterio == "asc")
		setVarGlobal(chave, "desc");
	else if(criterio == null || criterio == "")
	{
		setVarGlobal(chave, "asc");
		criterio = "asc";
	}
	return getVarGlobal(chave);
}

function getCriterioOrdenacao(chave)
{
	var criterio = getVarGlobal(chave);
	//Alterado porque n?o retornava sem crit?rio (branco)
	//if (criterio == null || criterio == "" || ""+criterio == "undefined") {
	if (criterio == null || typeof criterio == "undefined") {
		criterio = "asc";
	}
	return criterio;
}

function setCriterioOrdenacao(chave, criterio)
{
	setVarGlobal(chave,criterio);
}

function mantemEstadoCriterio(listaCampos, chave, separador)
{
	var campos = listaCampos.split(separador);
	for (k=0; k < campos.length; k++)
	{
		var dadosCampo = campos[k].split(" ");
		if(dadosCampo[0] == chave){
			setCriterioOrdenacao(dadosCampo[0],dadosCampo[1]);
			substituirImagems(dadosCampo[0]);
			break;
		}
	}
}

function montaCriterioNovo(campo, alias)
{
	var criterio = atualizaCriterioOrdenacao(campo);
	if(criterio == null || criterio == "" || ""+criterio == "undefined")
		return "";
	else
		return campo+" "+criterio;
}

function substituirImagem(campo)
{
	var criterio = getCriterioOrdenacao(campo);
	var imgArray = getImagem(criterio);
	alteraImagem("IMAGEM_"+campo, imgArray.src, imgArray.alt);
}

function substituirImagems(campo)
{
	var criterio = getCriterioOrdenacao(campo);
	var imgArray = getImagem(criterio);
	alteraImagems("IMAGEM_"+campo, imgArray.src, imgArray.alt);
}

function getDocumento(){
	return (document.compatMode && document.compatMode!="BackCompat") ? document.documentElement : document.body
}

function getElementoPorId(elementoID){
	var crossElemento = null;
	if(document.getElementById && document.getElementById(elementoID))
		crossElemento = document.getElementById(elementoID)
	else if(elementoID){
		if (tecnologia=="Struts"){
			if(document.all && eval("document.all."+elementoID))
				crossElemento = eval("document.all."+elementoID);
			else if (eval("document."+elementoID))
				return eval("document."+elementoID);
		}else{
			if(document.all && eval("document.all['"+elementoID+"']"))
				crossElemento = eval("document.all['"+elementoID+"']");
			else if (eval("document['"+elementoID+"']"))
				return eval("document['"+elementoID+"']");
		}
	}
	return crossElemento;
}

function getPosicaoScroll(){

	var dsocleft = document.all? getDocumento().scrollLeft : pageXOffset;
	var dsoctop	=	document.all? getDocumento().scrollTop : pageYOffset;

	var posicaoScroll = new Object();
	posicaoScroll.posEsquerda 	= dsocleft;
	posicaoScroll.posTopo 			= dsoctop;
	return posicaoScroll;
}

function getElementoStyle(elementoID){

	var crossElemento = getElementoPorId(elementoID);
	var crossElementoStyle = "";
	if(crossElemento){
		if (document.all||document.getElementById)
			crossElementoStyle =  eval(crossElemento.style);
		else if (document.layers)
			crossElementoStyle =  crossElemento;
	}
	return crossElementoStyle;
}

function posicionaElemento(elementoID, posX, posY, incremental){
	var crossElementoStyle = getElementoStyle(elementoID);
	incremental = incremental != "" || typeof incremental != "undefined" ? incremental : false;
	var crossElementoStyle = getElementoStyle(elementoID);
	if(posX && posX != "" && typeof posX != "undefined")
		crossElementoStyle.top = incremental ? getVarGlobal("topo"+elementoID) + parseInt(posX) : parseInt(posX) ;
	if(posY && posY != "" && typeof posY != "undefined")
		crossElementoStyle.left = incremental ? getVarGlobal("esquerda"+elementoID) + parseInt(posY) : parseInt(posY);
}

function posicionaElementoPor(elementoID, posX, posY){
	var crossElementoStyle = getElementoStyle(elementoID);
	if(getVarGlobal("esquerda"+elementoID) == null)
		setVarGlobal("esquerda"+elementoID,parseInt(crossElementoStyle.left));
	if(getVarGlobal("topo"+elementoID) == null)
		setVarGlobal("topo"+elementoID,parseInt(crossElementoStyle.top));

	posicionaElemento(elementoID, posX, posY, true);
}

function redimensionaElemento(elementoID, wa, ha, incremental){

	incremental = incremental != "" || typeof incremental != "undefined" ? incremental : false;
	var crossElementoStyle = getElementoStyle(elementoID);
	if(wa && wa != "" && typeof wa != "undefined")
		crossElementoStyle.width = incremental ? getVarGlobal("largura"+elementoID) + wa : wa;
	if(ha && ha != "" && typeof ha != "undefined")
		crossElementoStyle.height = incremental ? getVarGlobal("altura"+elementoID) + ha : ha;
}

function redimensionaElementoPor(elementoID, wa, ha){
	var crossElementoStyle = getElementoStyle(elementoID);
	if(getVarGlobal("altura"+elementoID) == null)
		setVarGlobal("altura"+elementoID,parseInt(crossElementoStyle.height));
	if(getVarGlobal("largura"+elementoID) == null)
		setVarGlobal("largura"+elementoID,parseInt(crossElementoStyle.width));

	redimensionaElemento(elementoID, wa, ha,true);
}

/*********************************
* Switch Content script- ? Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use. Last updated Mar 23rd, 2004.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/
/********************************************************************************
IMPORTANTE: ESTE PAR?METRO DEVE ESTAR COM VALOR "off" PARA EVITAR PROBLEMA DE
QUEDA DE SESS?O PELA SUBSTITUI??O DO COOKIE DE SESS?O
********************************************************************************/
var enablepersist="off" //Enable saving state of content structure using session cookies? (on/off)
/********************************************************************************/

var collapseprevious="no" //Collapse previously open content when opening present? (yes/no)

if (document.getElementById){
document.write('<style type="text/css">')
document.write('.expandeRetraiPlc{display:none;}')
document.write('</style>')
}

function getElementbyClass(classname){
ccollect=new Array()
var inc=0
var alltags=document.all? document.all : document.getElementsByTagName("*")
for (i=0; i<alltags.length; i++){
if (alltags[i].className==classname)
ccollect[inc++]=alltags[i]
}
}

function contractcontent(omit){
var inc=0
while (ccollect[inc]){
if (ccollect[inc].id!=omit)
ccollect[inc].style.display="none"
inc++
}
}

function expandcontent(cid){
if (typeof ccollect!="undefined"){
if (collapseprevious=="yes")
contractcontent(cid)
document.getElementById(cid).style.display=(document.getElementById(cid).style.display!="block")? "block" : "none"
}
}

function revivecontent(){
contractcontent("omitnothing")
selectedItem=getselectedItem()
selectedComponents=selectedItem.split("|")
for (i=0; i<selectedComponents.length-1; i++)
document.getElementById(selectedComponents[i]).style.display="block"
}

function get_cookie(Name) {
var search = Name + "="
var returnvalue = "";
if (document.cookie.length > 0) {
offset = document.cookie.indexOf(search)
if (offset != -1) {
offset += search.length
end = document.cookie.indexOf(";", offset);
if (end == -1) end = document.cookie.length;
returnvalue=unescape(document.cookie.substring(offset, end))
}
}
return returnvalue;
}

function getselectedItem(){
if (get_cookie(window.location.pathname) != ""){
selectedItem=get_cookie(window.location.pathname)
return selectedItem
}
else
return ""
}

function saveswitchstate(){
var inc=0, selectedItem=""
while (ccollect[inc]){
if (ccollect[inc].style.display=="block")
selectedItem+=ccollect[inc].id+"|"
inc++
}

document.cookie=window.location.pathname+"="+selectedItem
}

function do_onload(){
uniqueidn=window.location.pathname+"firsttimeload"
getElementbyClass("switchcontent")
if (enablepersist=="on" && typeof ccollect!="undefined"){
document.cookie=(get_cookie(uniqueidn)=="")? uniqueidn+"=1" : uniqueidn+"=0"
firsttimeload=(get_cookie(uniqueidn)==1)? 1 : 0 //check if this is 1st page load
if (!firsttimeload)
revivecontent()
}
}

if (window.addEventListener)
window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
window.attachEvent("onload", do_onload)
else if (document.getElementById)
window.onload=do_onload

if (enablepersist=="on" && document.getElementById)
window.onunload=saveswitchstate

/*********************************
* Fim Switch Content
**********************************/

/*-----------------------------------------------------*\
   INICIO FUN??ES PARA ABA ?GIL
\*-----------------------------------------------------*/

var layers    = new Array();
var layersFilhos   = new Array();
var abaAgilUsaAncora = true;
var tabSelecionada = "";
var tabFilhoSelecionada = "";
function showHideAba(aba,ancora){
	var layersAux = layers;
	var _doc = getRootDocument();
	if(aba.indexOf("->") > -1)
		layersAux = layersFilhos;	
	for( var i=0; i<layersAux.length; i++){
		try{
			if(layersAux[i] == aba){
				if(aba.indexOf("->") < 0)
			   		tabSelecionada = aba;
				_doc.getElementById(layersAux[i]).className = "ativada";
				_doc.getElementById("td_borda_"+layersAux[i]).className = "ativada";
			   	_doc.getElementById(layersAux[i]+"_corpo").className = "tabVisivel";
				if(ancora != null && ancora != "" && abaAgilUsaAncora)
				   	_doc.location.hash=ancora;
				if (tecnologia=='Struts')
				   	setValorCampo('tabCorrenteDinamicoPlc',aba);
				else
					setValorCampo('corpo:formulario:tabCorrenteDinamicoPlc',aba);
				var numAba	= tabSelecionada.substring(tabSelecionada.indexOf("_")+1);
				tabFolderFocaCampo(numAba)		
			}else{
			   	_doc.getElementById(layersAux[i]).className = "";
				_doc.getElementById("td_borda_"+layersAux[i]).className = "";
			   	_doc.getElementById(layersAux[i]+"_corpo").className = "tabOculta";
			}
		}catch(e){}
  	}
}

/*MANTEM QUAL TABFOLDER EST? SELECIONADA*/
function mantemAbaSelecionada (){
	if (tecnologia=='Struts')
		tabSelecionada = get('tabCorrenteDinamicoPlc') != "" ? get('tabCorrenteDinamicoPlc') : tabSelecionada;
	else
		tabSelecionada = get('corpo:formulario:tabCorrenteDinamicoPlc') != "" ? get('corpo:formulario:tabCorrenteDinamicoPlc') : tabSelecionada;
	if(tabSelecionada.indexOf("->") > -1){
		var tabSelecionadaAux = tabSelecionada; 
		showHideAba(tabSelecionada.substring(0,tabSelecionada.indexOf("->")));
		tabSelecionada = tabSelecionadaAux; 
		showHideAba(tabSelecionada);
	}else{
		setTimeout("showHideAba('" + tabSelecionada + "')", 0);
		if(tabFilhoSelecionada != "")
			showHideAba(tabFilhoSelecionada);
	}	
}


function trocaAba(index,thisId,nomeAba){
	var detCorrPlc = "";
	
	if (tabFolderCamposFoco != null && typeof tabFolderCamposFoco != "undefined")
		detCorrPlc = getDetalhePeloCampo(tabFolderCamposFoco[index]);
	
	if(detCorrPlc == "" && getVarGlobal(index) && typeof getVarGlobal(index) != "undefined")
		detCorrPlc = getVarGlobal(index);
	
	set('detCorrPlc',detCorrPlc);
	
	showHideAba(thisId, nomeAba);
}

/******************************************************************************\
					 FUN??ES PARA MANIPULA??ES DE EVENTOS
\******************************************************************************/
function PlcEvento (){}

var plcEvento = new PlcEvento();

/*----------------------------------------------------------------------------*\
   jCompany 2.5. Guarda objeto do foco para facilitar cria??o de novos detalhes
\*----------------------------------------------------------------------------*/
 function trataOnFocus (evt) {
	// Pega nome do detalhe, se campo contiver, ou esvazia o "detCorrPlc"
	var nomeDetalhe = getDetalhePeloCampo(this.name);
	jQuery('#detCorrPlc').val(nomeDetalhe);
	
	if (this.oldOnFocus && this.oldOnFocus != trataOnFocus){
      this.oldOnFocus(evt);
	}
}

function getDetalhePeloCampo (nomeCampo){
	
	if (tecnologia == "Struts"){
		
		var padraoDetalhe = new RegExp ("(\\w+)\\[(\\d+)\\].(\\w+)");
		var padraoSubDetalhe = new RegExp ("(\\w+)\\[(\\d+)\\].(\\w+)\\[(\\d+)\\]");
		
		if (padraoSubDetalhe.exec(nomeCampo) != null){
			var grupos = padraoSubDetalhe.exec(nomeCampo);
			var nomeCampoSubDetalhe = grupos[2];
			return nomeCampoSubDetalhe;
		} else if (padraoDetalhe.exec(nomeCampo) != null) {
			var grupos = padraoDetalhe.exec(nomeCampo);
			var nomeCampoDetalhe = grupos[1];
			return nomeCampoDetalhe;
		} else {
			return "";
		}
		
	} else if (nomeCampo.indexOf("corpo:formulario:") > -1){

		var padraoDetalhe = new RegExp ("corpo:formulario:(\\w+):(\\d+):(\\w+)");
		var padraoSubDetalhe = new RegExp ("corpo:formulario:(\\w+):(\\d+):(\\w+):(\\d+):(\\w+)");
		
		if (padraoSubDetalhe.exec(nomeCampo) != null){
			var grupos = padraoSubDetalhe.exec(nomeCampo);
			var nomeCampoSubDetalhe = grupos[1]+"["+grupos[2]+"]."+grupos[3];
			return nomeCampoSubDetalhe;
		} else if (padraoDetalhe.exec(nomeCampo) != null) {
			var grupos = padraoDetalhe.exec(nomeCampo);
			var nomeCampoDetalhe = grupos[1];
			return nomeCampoDetalhe;
		} else {
			return "";
		}
		
	} else {
		
		var padraoDetalhe = new RegExp ("corpo:(\\w+):(\\d+):(\\w+)");
		var padraoSubDetalhe = new RegExp ("corpo:(\\w+):(\\d+):(\\w+):(\\d+):(\\w+)");
		
		if (padraoSubDetalhe.exec(nomeCampo) != null){
			var grupos = padraoSubDetalhe.exec(nomeCampo);
			var nomeCampoSubDetalhe = grupos[1]+"["+grupos[2]+"]."+grupos[3];
			return nomeCampoSubDetalhe;
		} else if (padraoDetalhe.exec(nomeCampo) != null) {
			var grupos = padraoDetalhe.exec(nomeCampo);
			var nomeCampoDetalhe = grupos[1];
			return nomeCampoDetalhe;
		} else {
			return "";
		}
		
	}
	
}

PlcEvento.prototype.getEventoElemento = function(evento){

	plcLog.debug("##### Entrou em getEventoElemento")
	var elemento = null;
	if(ExpYes){
		if(evento.srcElement)
			elemento = evento.srcElement;
	}else{
		if(evento.target)
			elemento = evento.target;
	}
	return elemento
}
PlcEvento.prototype.getEventoAtual = function(evento){
	if(ExpYes && window.event)
		return window.event.type;
	else if (NavYes && evento && Event){
		if(evento.toUpperCase() == "ONCHANGE" && document.captureEvents(Event.ONCHANGE))
			return document.captureEvents(Event.ONCHANGE);
		if(evento.toUpperCase() == "ONCLICK" && document.captureEvents(Event.ONCLICK))
			return document.captureEvents(Event.ONCLICK);
		if(evento.toUpperCase() == "ONKEYDOWN" && document.captureEvents(Event.ONKEYDOWN))
			return document.captureEvents(Event.ONKEYDOWN);
	}
}
/*---------------------------------------------------------------------------------*\
  jCompany 2.5. Acrescenta evento ? fun??o onFocus para logica de registro do objeto
\*---------------------------------------------------------------------------------*/
//TODO Ver possibilidade de adequar a novo modelo de eventos
 function setUpOnFocusHandlers () {
 	if(getRootDocument().forms) {
	    for (var f = 0; f < getRootDocument().forms.length; f++) {
 			if(getRootDocument().forms[f].elements) {
		      for (var e = 0; e < getRootDocument().forms[f].elements.length; e++) {
		        if (getRootDocument().forms[f].elements[e] &&
		          (getRootDocument().forms[f].elements[e].type == 'text'
		           || getRootDocument().forms[f].elements[e].type == 'textarea'
		           || getRootDocument().forms[f].elements[e].type == 'select-one'
		           || getRootDocument().forms[f].elements[e].type == 'select-multiple'
		           || getRootDocument().forms[f].elements[e].type == 'password'
		           || getRootDocument().forms[f].elements[e].type == 'checkbox'
		           || getRootDocument().forms[f].elements[e].type == 'radio'
		           || getRootDocument().forms[f].elements[e].type == 'file'
		           || getRootDocument().forms[f].elements[e].type == 'fileupload')) {
		          if (typeof getRootDocument().forms[f].elements[e].oldOnFocus == "undefined"){
		          	getRootDocument().forms[f].elements[e].oldOnFocus = getRootDocument().forms[f].elements[e].onfocus;
		          	getRootDocument().forms[f].elements[e].onfocus = trataOnFocus;
		          }
		        }
		      }
		   }
	    }
	 }
  }

/*--------------------------------------------------------------*\
   jCompany 2.7.2 - Inibe evento onclick
\*--------------------------------------------------------------*/
 function inibeOnClick (evt) {
	return false;
 }

PlcEvento.prototype.trataEventoJcp = function (evt) {
	//plcLog.debug("trataEventoJcp")
	//Desmarca item da lista de sele??o pois foi focado campo de argumento
	desmarcaListaSelecao();
	return true;
};
/*--------------------------------------------------------------*\
   jCompany 2.7.2 - Trata evento onclick genericamente
\*--------------------------------------------------------------*/
PlcEvento.prototype.eventoTrataClick = function (evt) {return true;};
function eventoTrataonclick(evt, objeto){
	var retClick = true;
	if(typeof objeto == "undefined")
		objeto = this;
	if(plcEvento.eventoTrataClick(evt)){
		if (objeto.oldonclick)
			retClick = objeto.oldonclick(evt);
		if(retClick)
			retClick = plcEvento.trataEventoJcp(evt);
	}else
		return false;
	return retClick;
}

/*--------------------------------------------------------------*\
   jCompany 2.7.2 - Trata evento onchange genericamente
\*--------------------------------------------------------------*/
PlcEvento.prototype.eventoTrataChange = function (evt) {return true;};
function eventoTrataonchange(evt, objeto){

	plcLog.debug("eventoTrataonchange")
	if(typeof evt == "undefined")
		evt = plcEvento.getEventoAtual('ONCHANGE')
	plcLog.debug("EVENTO: "+evt)	
	var retChange = true;
	if(typeof objeto == "undefined")
		objeto = this;
	if(plcEvento.eventoTrataChange(evt)){
		if (objeto.oldonchange)
			retChange = objeto.oldonchange(evt);
		if(retChange)
			retChange = plcEvento.trataEventoJcp(evt);
	}else
		return false;
	return retChange;

}

/*--------------------------------------------------------------*\
   jCompany 3.0 - Trata evento onfocus genericamente
\*--------------------------------------------------------------*/
PlcEvento.prototype.eventoTrataFocus = function (evt) {return true;};
function eventoTrataonfocus(evt, objeto){
	var retFocus = true;
	if(typeof objeto == "undefined")
		objeto = this;
	if(plcEvento.eventoTrataFocus(evt)){
		if (objeto.oldonfocus)
			retFocus = objeto.oldonfocus(evt);
		if(retFocus)
			retFocus = plcEvento.trataEventoJcp(evt);
	}else
		return false;
	return retFocus;
}

/*--------------------------------------------------------------*\
   jCompany 3.0 - Trata evento onblur genericamente
\*--------------------------------------------------------------*/
PlcEvento.prototype.eventoTrataBlur = function (evt) {return true;};
function eventoTrataonblur(evt, objeto){
	var retBlur = true;
	if(typeof objeto == "undefined")
		objeto = this;
	if(plcEvento.eventoTrataBlur(evt)){
		if (objeto.oldonblur)
			retBlur = objeto.oldonblur(evt);
		if(retBlur)
			retBlur = plcEvento.trataEventoJcp(evt);
	}else
		return false;
	return retBlur;
}

/*--------------------------------------------------------------*\
   jCompany 2.7.2 - Verifica se h? altera??o em algum campo
\*--------------------------------------------------------------*/
var msgAlteracao;
function enviaAlertaAlteracao (evt) {
	//alert("enviaAlertaAlteracao")
	//alert("inibeAlertaAlteracaoPadrao: "+inibeAlertaAlteracaoPadrao(this))
	//alert("plcGeral.inibeAlertaAlteracao: "+plcGeral.inibeAlertaAlteracao(this))
	//alert("disparouBotao: "+disparouBotao)
 	//if (ExpYes) {
		if(!disparouBotao  && !inibeAlertaAlteracaoPadrao(this) && !plcGeral.inibeAlertaAlteracao(this) && plcGeral.exibeAlertaAlteracao){
			if(confirm(msgAlteracao)){
				setAlertaAlteracao();
			}else
				return false;
		}
	//}
	return eventoTrataonclick(evt, this);
}

/*--------------------------------------------------------------*\
   jCompany 2.7.2 - Fun??o para sobreposi??o em caso de regras para
   inibi??o de alerta de altera??o
\*--------------------------------------------------------------*/
PlcGeral.prototype.inibeAlertaAlteracao = function (objeto) {return false;}
function inibeAlertaAlteracaoPadrao(objeto){

	//alert("getAlertaAlteracao(): "+getAlertaAlteracao())
	if(getAlertaAlteracao() != "S")
		return true;
		
	var inibeAlertaAtributo = false;
	var inibeAlertaBotao 	= false;
	try{
		if (tecnologia == "Struts"){
			inibeAlertaAtributo = (typeof objeto != "undefined" && 
								 	(	(objeto.getAttribute("inibeAlertaAlteracao") != null || objeto.getAttribute("inibeAlertaAlteracao")) || 
										(typeof objeto.id != "undefined" && objeto.id.indexOf("INIBE_ALERTA_ALTERACAO") > -1) )						
									);
		}
		else{
			if (tecnologia == "JSF" || tecnologia == "Facelets"){
				inibeAlertaAtributo = !( typeof objeto != "undefined" && 				 	 
				(typeof objeto.id != "undefined" && objeto.id.indexOf("EXIBE_ALERTA_ALTERACAO") > -1))								
									
			}
		}
										
	}catch(e){}
	
	
	var ehNovoDetalhe = objeto != undefined && objeto.id != undefined && objeto.id.indexOf("botaoAcaoNovo") > -1  && !get('detCorrPlc') == "";
	
	//alert("objeto.id: "+objeto.id)
	//alert("inibeAlertaAtributo : "+inibeAlertaAtributo)
	//alert("objeto.value: "+objeto.value)
	if(getAlertaAlteracao() == "S"){
		inibeAlertaBotao = 
		(objeto.value == getBotaoArray('EXCLUIR') ||
		objeto.value == getBotaoArray('GRAVAR') || 
		objeto.value == getBotaoArray('INCLUIR_DET') || 
		objeto.value == getBotaoArray('IMPRIMIR') || 
		objeto.value == getBotaoArray('ASSISTENTE_INICIALIZA') ||
		objeto.value == getBotaoArray('ASSISTENTE_ANTERIOR') ||
		objeto.value == getBotaoArray('ASSISTENTE_CANCELA') ||
		objeto.value == getBotaoArray('ASSISTENTE_PROXIMO') ||
		objeto.value == getBotaoArray('REFRESH') ||
		objeto.value == getBotaoArray('REFRESH_CACHE') ||
		objeto.value == getBotaoArray('VIS_DOCUMENTO') ||
		objeto.value == getBotaoArray('EDT_DOCUMENTO') ||
		objeto.value == getBotaoArray('ARQ_ANEXADO') ||
		(objeto.value == getBotaoArray('INCLUIR') && get('detCorrPlc') != "")
		);
	}
	//alert("inibeAlertaBotao: "+inibeAlertaBotao)
	return inibeAlertaAtributo || inibeAlertaBotao || ehNovoDetalhe;
}

function setAlertaAlteracao(evt, alerta){
	plcLog.debug("setAlertaAlteracao")

	if (tecnologia == "Struts"){
		if(alerta != "")
			set("alertaAlteracaoPlc","S");
		else 	
			set("alertaAlteracaoPlc","");
	} else if (tecnologia == "JSF" || tecnologia == "Facelets" ){
		if(alerta != "")
			set("corpo:formulario:alertaAlteracaoPlc","S");
		else 	
			set("corpo:formulario:alertaAlteracaoPlc","");
	}
	
	plcLog.debug("setAlertaAlteracao - evt: "+evt)
	//if(evt && evt != "")	
		return eventoTrataonchange(evt, this);
}

function getAlertaAlteracao(){
	if (tecnologia == "Struts"){
		return get("alertaAlteracaoPlc");
	} else if (tecnologia == "JSF" || tecnologia == "Facelets"){
		return get("corpo:formulario:alertaAlteracaoPlc");
	}
}
var msgExclusaoDetalhe;
function enviaAlertaExclusaoDetalhe(){

	if (tecnologia == "JSF"){
		var usaAlertaExclusaoDetalhe = getCampo("corpo:formulario:alertaExclusaoDetalhePlc");
		if (usaAlertaExclusaoDetalhe != undefined && usaAlertaExclusaoDetalhe.value=='S'){
			var campo = getCampo("corpo:formulario:indExcDetPlc");
			if (campo && campo.value!=''){
				return confirm(msgExclusaoDetalhe);
			}
		}
		return true;
	}
	return true;
}

/*---------------------------------------------------------------------------*\
  jCompany 2.7.2 - Acrescenta eventos a um conjunto de tags ou a um elemento
\*---------------------------------------------------------------------------*/
function setUpOnEventoElemento (idElemento, evento, funcao) {
	setUpEventos ("", idElemento, evento, funcao,"");
}

function setUpOnEventoTag (tag, evento, funcao) {
	if( ExpYes && ( funcao == 'destacaCampoFocado' || funcao == 'reverteDestaqueCampoFocado') ){ 
		return ; 
	}
	
	setUpEventos (tag, "", evento, funcao,"");
}

function setUpOnEventoTagCampoNome (tag, evento, funcao, nome) {
	setUpEventos (tag, "", evento, funcao, nome);
}

function setUpEventos (tag, idElemento, evento, funcao, nome) {

	var tags;
	var tipo = "";
	var elementos;

	//alert("Entrei setUpEventos");

	//alert("setUpEventos - Nome: "+nome);
	//alert("setUpEventos - Tag: "+tag);
	//alert("setUpEventos - Funcao: "+funcao)
	//alert("setUpEventos - Tipo: "+tipo)
	//alert("setUpEventos - Id Elemento: "+idElemento);
	
	if(typeof tag != "undefined" && tag != ""){
		var posTipo = tag.indexOf("#")
		if(posTipo >= 0){
			tags = getRootDocument().getElementsByTagName(tag.substring(0,posTipo));
			tipo = tag.substring(posTipo+1)
		}else
			tags = getRootDocument().getElementsByTagName(tag);
	}else if (typeof nome != "undefined" && nome != ""){
		tags = new Array();
		tags[tags.length] = getCampo(nome);
	}
	else if(typeof idElemento != "undefined" && idElemento != "")
		elementos = getElementoPorId(idElemento);

 	if(tags) {
		//alert("NUM. TAGS: "+tags.length)
	    for (var t = 0; t < tags.length; t++) {
			var umaTag = tags[t];
 			if(umaTag && ((tipo == "" || umaTag.type == tipo) && 
 			(nome == "" || umaTag.name == nome))) {
				//alert("UMATAG.NAME: "+umaTag.name)
				if(eval("umaTag."+funcao) != funcao )
					eval("umaTag.old"+evento.toLowerCase()+" = umaTag."+evento.toLowerCase());
				if(typeof funcao != "undefined" && funcao != ""){
					eval("umaTag."+funcao+"='"+funcao+"'");
					eval("umaTag."+evento.toLowerCase()+" = "+funcao);
				}
				else
					eval("umaTag."+evento.toLowerCase()+" = eventoTrata"+evento.toLowerCase());
			}
			
			//if(umaTag.name == "nome_Arg"){
				//alert("FOCUS: "+umaTag.onfocus)
				//alert("OLD FOCUS: "+umaTag.oldonfocus)
				//alert("CHANGE: "+umaTag.onchange)
				//alert("OLD CHANGE: "+umaTag.oldonchange)
				//alert("BLUR: "+umaTag.onblur)
				//alert("OLD BLUR: "+umaTag.oldonblur)
			//}
		}
	}
	if(elementos) {
	
		var elementoAnterior = elementos;
		if(!elementos.length){
			elementos = new Array();
			elementos[elementos.length] = elementoAnterior
		}
		
        if (!ExpYes && evento.substring(0,2)=="on") {
			evento = evento.substring(2);
        }
		
	    for (e = 0; e < elementos.length; e++) {
	    	var umElemento = elementos[e];
	    	if(umElemento){
				if(eval("umElemento."+funcao) != funcao )
					eval("umElemento.old"+evento.toLowerCase()+" = umElemento."+evento.toLowerCase());
				if(typeof funcao != "undefined" && funcao != ""){
					if(ExpYes)
						eval("umElemento."+evento.toLowerCase()+" = "+funcao);
					else
						eval("umElemento.addEventListener('"+evento.toLowerCase()+"',"+funcao+",false)");
				}
				else{
					if(ExpYes)
						eval("umElemento."+evento.toLowerCase()+" = eventoTrata"+evento.toLowerCase());	
					else
						eval("umElemento.addEventListener('"+evento.toLowerCase()+"','eventoTrata"+evento.toLowerCase()+",false)");
				}
	    	}
		}
	}
}

/*---------------------------------------------------------------------------*\
  jCompany 2.5.3 Altera classes dos objetos
\*---------------------------------------------------------------------------*/
 function alteraClasse () {
	if(arguments && arguments.length > 0)
	{
		this.ID 		= "";
		this.CAMPO		= "";
		this.TIPO		= "";
		this.CLASSE		= "";
		this.OBJETO		= "";
		this.INICIAL	= false;
		this.NOVACLASSE	= false;

		for(i = 0; i < arguments.length; i++)
		{
			if(arguments[i] == "ID")
				this.ID = arguments[++i];
			else if(arguments[i] == "CAMPO")
				this.CAMPO = arguments[++i];
			else if(arguments[i] == "TIPO")
				this.TIPO = arguments[++i];
			else if(arguments[i] == "CLASSE")
				this.CLASSE = arguments[++i];
			else if(arguments[i] == "OBJETO")
				this.OBJETO = arguments[++i];
			else if(arguments[i] == "NOVACLASSE")
				this.NOVACLASSE = true;
			else if(arguments[i] == "INICIAL")
				this.INICIAL = true;
		}
	}

	var elements = "";
	if(this.ID != ""){
		elements = getElementoPorId(this.ID);
	}else if (this.OBJETO != ""){
		elements = this.OBJETO;
		elements = new Array(elements);
	}else if (this.CAMPO){
		elements = getForm().elements[this.CAMPO];
		elements = new Array(elements);
	}
  	if(elements) {
		for (var e = 0; e < elements.length; e++) {
			if (elements[e]) {
				if(this.NOVACLASSE){
					if(elements[e].type=="radio" && NavYes){
						elements[e].parentNode.className = this.CLASSE;
					}
					else{
						elements[e].className = this.CLASSE;
					}
				}
				else if (this.INICIAL){
					var exp = this.CLASSE;
					if(elements[e].type=="radio" && NavYes){
						elements[e].parentNode.className = this.CLASSE;
					}
					else{
						elements[e].className = elements[e].className.replace(exp,"");
					}
				}
				else{
					if(elements[e].type=="radio" && NavYes){
						elements[e].parentNode.className = this.CLASSE;
					}
					else{
						elements[e].className = elements[e].className +" "+ this.CLASSE;
					}
				}
			}
		}
	}
}

function marcaSelecao(linha, evt){
		if(evt.type == "mouseover")
			alteraClasse('OBJETO', linha, 'CLASSE', 'campoComErro');
		else
			alteraClasse('OBJETO', linha, 'CLASSE', 'campoComErro','INICIAL');
			
		if(getVarGlobal("trSelecao") != null){	
			alteraClasse('OBJETO', getVarGlobal("trSelecao"), 'CLASSE', 'campoComErro','INICIAL');
			setVarGlobal("trSelecao", null)
		}	
}

/*---------------------------------------------------------------------------*\
  jCompany 2.5.7 Fun??o: formata campo do tipo monet?rio.
  Contribui??o Est?dio de Desenvolvimento - Grupo Ultra
  EXEMPLO DE CHAMADA:
  onkeypress=" return(currencyFormat(this, event, <separador milhar>, <separador decimal>))"
	this = refer?ncia ao objeto campo [OB]
	event= evento que ocasionou a chamada da fun??o. [Fixo, OB]
	separador milhar = caracter utilizado para separar milhares. <Default: '.'> [OP]
	separador decimal= caracter utilizado para separar casas decimais <Default: ','> [OP]
  @since 03/08/2005
\*---------------------------------------------------------------------------*/
  function formataMonetario(fld, e, milSep, decSep) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	milSep = typeof milSep != "undefined" ? milSep : ".";
	decSep = typeof decSep != "undefined" ? decSep : ",";
	var whichCode = getKeyCode(e);
	if (whichCode == 13) // Tecla 'Enter'
		return true;
	if (NavYes && whichCode == 0) // Tecla 'Tab'
		return true;
		
	key = String.fromCharCode(whichCode);  			// Recupera c?digo da tecla pressionada
	if(!validaCaracter(null, e, "V"))
		return false;
	len = fld.value.length;
	for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep))
			break;
	aux = '';
	for(; i < len; i++){
		if (strCheck.indexOf(fld.value.charAt(i))!=-1)
			aux += fld.value.charAt(i);
	}
	aux += key;
	len = aux.length;
	if (len == 0) fld.value = '';
	if (len == 1) fld.value = '0'+ decSep + '0' + aux;
	if (len == 2) fld.value = '0'+ decSep + aux;
	if (len > 2) {
		aux2 = '';
		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += milSep;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}
		fld.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--)
		fld.value += aux2.charAt(i);
		fld.value += decSep + aux.substr(len - 2, len);
	}
	return false;
 }

/**
* Fun??o que expande retrai menu de contexto
*/
 function expandeMenu(obj){
	if(document.getElementById){
	var el = document.getElementById(obj);
	var ar = document.getElementById("masterdiv").getElementsByTagName("span");
		if(el.style.display != "block"){
			for (var i=0; i<ar.length; i++){
				if (ar[i].className=="expandeRetraiPlc")
				ar[i].style.display = "none";
			}
			el.style.display = "block";
		}else{
			el.style.display = "none";
		}
	}
}

function alteraDisplay(idObjeto, objeto){

	var estiloObjeto;
	if(!objeto || typeof objeto == "undefined")
		estiloObjeto = getElementoStyle(idObjeto);
	else 	
		estiloObjeto = objeto.style;
	plcLog.debug("estiloObjeto: "+estiloObjeto);	
	plcLog.debug("estiloObjeto.display: "+estiloObjeto.display);	
	if(estiloObjeto){
		if(estiloObjeto.display == "block")
			estiloObjeto.display = "none";
		else
			estiloObjeto.display = "block";
	}
}

function alteraEstilo(idObjeto){

	var estiloObjeto = getElementoStyle(idObjeto);
	if(estiloObjeto){
		for(i = 1; i < arguments.length; i++){
			//plcLog.debug("alteraEstilos: "+"estiloObjeto."+arguments[i]+" = "+arguments[++i]);
			eval("estiloObjeto."+arguments[i]+" = "+arguments[++i]);
		}
	}
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
function testePararBarra(){
	clearInterval(interval);
	interval = setInterval('testePararBarra()',1);
	if(winPopupBarra && winPopupBarra.closed){
		clearInterval(interval);
		barraProgressoAjax.hideBar();
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

/*---------------------------------------------------------------------------*\
  jCompany 2.5.7 - Fun??o para recuperar valor de par?metros na url
\*---------------------------------------------------------------------------*/
function getParametroUrl ( parametro, queryString) {
	queryString = (typeof queryString == "undefined" && queryString != "") ? getQueryString() : queryString;
	if(queryString.indexOf(parametro+"=") >= 0){
		queryString = queryString.lastIndexOf("#") >= 0 ? queryString.substring(0,queryString.lastIndexOf("#")) : queryString;
 		var arrayParametros	= separaListaTermos(queryString,"&");
		for(i = 0; i < arrayParametros.length; i++){
			if(arrayParametros[i].indexOf(parametro+"=") >= 0){
				return (arrayParametros[i].substring(arrayParametros[i].indexOf("=")+1,arrayParametros[i].length));
			}
		}
	}
}

/*---------------------------------------------------------------------------*\
  jCompany 2.5.7 - Fun??o para recuperar todos os par?metros da url
\*---------------------------------------------------------------------------*/
function getQueryString(){
	return document.location.search;
}

function getCamposEntrada (strTest, atributo, operador, f) {

	var condicao = 	(typeof strTest != "undefined" &&
					typeof atributo != "undefined" &&
					typeof operador != "undefined") ?
					operador == "indexOf" ?
					".indexOf('"+strTest+"') >= 0" :
					operador+strTest : "";
	var arrayCamposEntrada = new Array();
	var form = f || getForm();
	if(form){
		var formElements = form.elements;
		//plcLog.debug("formElements: "+formElements.length);
		for(e = 0; e < formElements.length; e++ ){
			//plcLog.debug("formElements[e].type: "+formElements[e].type);
	        if(condicao == "" || plcEval(formElements[e], atributo, condicao)){
	        	arrayCamposEntrada[arrayCamposEntrada.length] = formElements[e];
	        }
        }
	}
	return arrayCamposEntrada;
}

/**
* Fun??o que avalia o atributo de um elemento para uma condi??o preestabelecida
*/
function plcEval(elemento, atributo, condicao){

	if(atributo == "name"){
		return eval("\"" + elemento.name + "\"" + condicao);
	}
	return false;
}

/**
* Fun??o que inibe o clique do bot?o direito do mouse
*/
function inibeRightClick(evt){
	var valEvento = "";
	if (ExpYes)
		valEvento = event.button;
	else
		valEvento = evt.which;
	if (valEvento ==2 || valEvento == 3){
		alert("Desabilitado");
		return false;
	}
}

/**
* Fun??o que configura funcionamento para eventos do mouse associando uma
* nova fun??o ao evento
*/
function configEventosMouse(tipoEvento, funcao){
	if(typeof funcao == "undefined")
		funcao = "inibeRightClick";
	//Evento MOUSEDOWN
	if(tipoEvento.toLowerCase() == "mousedown"){
		if (NavYes)
			document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown = eval(funcao);
	}
	//Menu de contexto (bot?o direito)
	if(tipoEvento.toLowerCase() == "contextmenu"){
		document.oncontextmenu = eval(funcao);
	}
}

/*-----------------------------------------------------*\
   		FUN??ES PARA LOG JAVASCRIPT EM CONSOLE
\*-----------------------------------------------------*/
function PlcLog (){
	this.isEnabled = false;
	this.console = new Object();
}
var plcLog = new PlcLog();

PlcLog.prototype.newLogErros = function(){
	plcLog.logErros = new Object();
	plcLog.logErros["TAMANHO"] = 0;
}

PlcLog.prototype.logErros = new plcLog.newLogErros();

PlcLog.prototype.logEvent = function (evt){}

//Envia alerta de excecao
PlcLog.prototype.alertaExcecao = function (ex, msg){alert(plcLog.montaMsgExcecao(ex,msg));}

//Envia log de excecao
PlcLog.prototype.logExcecao = function (ex, msg){plcLog.debug(plcLog.montaMsgExcecao(ex,msg));}

PlcLog.prototype.logMostraErros = function (){
	plcLog.debug("LOG MOSTRA ERROS")
	var erros = plcLog.logPreparaErros()
	if(plcLog.logErros["TAMANHO"] > 0){
		getElementoStyle("MENSAGEM_TABELA_msgVermelho").display  = 'none';
		getElementoStyle("VALIDACAO_TABELA_JAVASCRIPT").display  = 'block';
		getElementoPorId("validacao_erros_javascript").innerHTML = erros;
		plcLog.debug("ERROS "+erros)
		plcLog.newLogErros();
	}
}

PlcLog.prototype.logEscondeErros = function (){
	//plcLog.debug("LOG ESCONDE ERROS")
	var tabela = getElementoStyle("VALIDACAO_TABELA_JAVASCRIPT");
	var erros = getElementoPorId("validacao_erros_javascript");
	if (tabela && erros) {
		tabela.display = "none";
	    erros.innerHTML = "";
		plcLog.newLogErros();
	}
}

PlcLog.prototype.logPreparaErros= function (){
	plcLog.debug("LOG PREPARA ERROS")
	var msgErros = "";
	var k = 0;
	var naoInformouCamposObrigatorios = false;
	for (var propErro in plcLog.logErros){
		if(propErro != "TAMANHO"){
			plcLog.debug("PROP: "+propErro);
			plcLog.debug("ERRO: "+plcLog.logErros[propErro]);
			if(plcLog.logErros[propErro] != "OBRIGATORIO"){
				msgErros += "<img align='ABSMIDDLE' alt='Erro' height='11' hspace='4' src='"+plcGeral.contextPath+"/plc/midia/msgVermelho/lin.gif' vspace='4' width='11'>";
				msgErros += plcLog.logErros[propErro] + "<br>";
			}else
				naoInformouCamposObrigatorios = true;
		//	alert('propErro='+propErro);
		  //  alert('valor='+getCampo(propErro));
		   //  alert('valor aval ='+getCampo(propErro)+ " tipo="+(typeof getCampo(propErro)));
			if (((getCampo(propErro)) && !(getCampo(propErro).type) &&
			     getCampo(propErro).length && getCampo(propErro).length>1)) {
			 	for (var j = 0; j<getCampo(propErro).length; j++) {
			 		alteraClasse ("OBJETO",getCampo(propErro)[j],"CLASSE","campoComErro","NOVACLASSE");
			 	}
			}  else {
					alteraClasse ("CAMPO",propErro,"CLASSE","campoComErro","NOVACLASSE");
			}
	
			if(k == 0)		
				setFocus(propErro);
			k++;	
		}
	}
	if(naoInformouCamposObrigatorios){
		msgErros += "<img align='ABSMIDDLE' alt='Erro' height='11' hspace='4' src='"+plcGeral.contextPath+"/plc/midia/msgVermelho/lin.gif' vspace='4' width='11'>";
		msgErros += plcGeral.obrigatorioMsg;
	}	
	
	return msgErros;
}

PlcLog.prototype.logAdicionaErro = function (){
	plcLog.debug("ADICIONA ERRO")
	if(arguments.length == 1){
		this.ID 	= "ERRO_"+plcLog.logErros.TAMANHO;
		this.ERRO 	= arguments[0];
	}else{
		for(var j = 0; j < arguments.length; j++)
		{
			if(arguments[j] == "ID")
				this.ID = arguments[++j];
			if(arguments[j] == "ERRO")
				this.ERRO = arguments[++j];
		}
	}
	plcLog.debug("ADD ERRO - ID: "+this.ID)
	plcLog.debug("ADD ERRO - ERRO: "+this.ERRO)
	plcLog.logErros[this.ID] = this.ERRO;
	plcLog.logErros["TAMANHO"] = plcLog.logErros.TAMANHO + 1;
}

PlcLog.prototype.logAdicionaErroCampo = function (nomeCampo, erro){
	plcLog.debug("ADICIONA ERRO CAMPO");
	plcLog.logAdicionaErro("ID", nomeCampo, "ERRO", erro);
}

PlcLog.prototype.logEnviaErro = function (erro){
	plcLog.debug("ENVIA ERRO");
	plcLog.logAdicionaErro(erro);
	plcLog.logMostraErros();
}

PlcLog.prototype.logEnviaErroCampo = function (nomeCampo, erro){
	plcLog.debug("ENVIA ERRO CAMPO");
	plcLog.logAdicionaErro("ID", nomeCampo, "ERRO", erro);
	plcLog.logMostraErros();
}

//Monta mensagem de excecao
PlcLog.prototype.montaMsgExcecao = function (ex, msg){
	var msgExPadrao = "ALERTA DE ERRO. Ocorreu um erro no javascript desta pagina.";
	var descEx = plcLog.logGetDescExcecao(ex);
	if(typeof msg != "undefined")
		msgExPadrao += "\n" + msg;
	var msgEx = msgExPadrao + "\nExcecao: "+ ex.name +".\nDescricao: "+ descEx;
	return msgEx;
}

//Recupera descri??o da exce??o
PlcLog.prototype.logGetDescExcecao = function (ex){
	return ExpYes ? ex.description : ex.message;
}

if(ExpYes)
	document.onkeydown   = function() { plcLog.logEvent(event); }
else {
	document.onkeydown   = function(evt){
		plcLog.logEvent(evt);
	}
}	

var strChave = "";
PlcLog.prototype.logEvent = function (evt){

	var ord = ""; // ascii order of key pressed
	
	if (ExpYes)
		ord = evt.keyCode; 
	else
		ord = evt.which;
	var altKey    = evt.altKey;
	var ctrlKey   = evt.ctrlKey;
	var shiftKey  = evt.shiftKey;

	/*alert(
	"evt.type: "+evt.type+"\n"+
	"ctrlKey: "+ctrlKey+"\n"+
	"altKey: "+altKey+"\n"+
	"shiftKey: "+shiftKey+"\n"+
	"ord: "+ord+"\n"
	)*/
	//Clicando ALT + SHIFT + CTRL + C
	if (altKey && shiftKey && ctrlKey && (ord == 67) && evt.type == 'keydown') {
		alert("Utilize SHIFT + CTRL + C")
	}
	//Clicando SHIFT + CTRL + C
	if (shiftKey && ctrlKey && (ord == 67) && evt.type == 'keydown') {     //Abrir console javascript

		evt.returnValue = false;  evt.cancelBubble = true;
		plcLog.isEnabled = !plcLog.isEnabled;
		if(plcLog.isEnabled){
			plcLog.console.window = window.open("","CONSOLE");
			plcLog.console.window.document.writeln("<input type='button' onclick='window.document.all.CONSOLE.innerHTML=\"\"' value='Limpar'>");
			plcLog.console.window.document.write("<input type='button' onclick='window.close()' value='Fechar'>");
			plcLog.console.window.document.writeln("<H3>CONSOLE JAVASCRIPT</H3>");
			plcLog.console.window.document.writeln("<div id='CONSOLE'>");
			plcLog.console.window.document.writeln("</div>");
		}else{
			plcLog.console.window.close();
		}
	}
	//Clicando ALT + SHIFT + CTRL + SCROLL
	if (altKey && shiftKey && ctrlKey && (ord == 145) && evt.type == 'keydown') {
		alert("jCompany 2.7 - Javascript Log \n Powerlogic S.A.")
	}

	//Clicando ALT + SHIFT + CTRL + PLC
	if (altKey && shiftKey && ctrlKey && evt.type == 'keydown' && (ord == 80 || ord == 76 || ord == 67)) {
		//alert(strChave)
		if(ord == 80 && strChave == ""){
			strChave += "P";
		}
		else if(ord == 76 && strChave == "P"){
			strChave += "L";
		}
		else if(ord == 67 && strChave == "PL"){
			strChave = "jCompany Professional Open-Source\n";
			strChave += "Arquivo de Javascript\n";
			strChave += "Powerlogic S.A.";
			alert(strChave);
			strChave = "";
		}
		else
			strChave = "";
	}
	
	var retornoEvento = executarAtalho(evt);
	try{
		getRootDocument().focus() ;
	}catch(e){}
	
	if(retornoEvento)
		return retornoEvento;
	
	return true;	
}

PlcLog.prototype.debug = function (log) {
	if(plcLog.isEnabled)
	{
		if(eval(plcLog.console.window) && !plcLog.console.window.closed)
		{
			plcLog.console.window.focus();
			var htmlLog = plcLog.console.window.document.all.CONSOLE.innerHTML;
			htmlLog += "<br>" + log;
			plcLog.console.window.document.all.CONSOLE.innerHTML = htmlLog;
		}
	}
}

/*MANIPULADOR DE TECLAS DE ATALHO*/
function setAtalho(atalho, funcao){
    if (this.objetoAtalho == null) {this.objetoAtalho = new Object();}
	this.objetoAtalho[atalho.toUpperCase()] = funcao;
}

function getAtalho(atalho){
	if(this.objetoAtalho && atalho != null && typeof atalho != "undefined")
		return this.objetoAtalho[atalho.toUpperCase()];
	return null;	
}

function executarAtalho(evt){

	var ctrlKey   	= evt.ctrlKey 	? "CTRL" 	: "";
	var altKey    	= evt.altKey 	? "#ALT" 	: "";
	var shiftKey  	= evt.shiftKey 	? "#SHIFT" 	: "";
	var keyChar   	= evt.keyCode && keyCodePermitido(evt.keyCode) ? "#"+String.fromCharCode(evt.keyCode).toUpperCase() : "";    // ascii order of key pressed
	//Tratar acao enter
	if(evt.keyCode == 13){
		return eval(getAtalho("#ENTER"));
	}
	var funcao 		= "";
	var atalho = (ctrlKey+altKey+shiftKey+keyChar).toUpperCase();
	
	//alert(evt.keyCode)
	//alert(String.fromCharCode(evt.keyCode))
	//alert(keyCodePermitido(evt.keyCode))
	//alert(atalho)
	//alert(getAtalho(atalho))
	
	var cancelaEvento = false;
	try{
		//if((ctrlKey && altKey) || (ctrlKey && shiftKey) || (shiftKey && altKey))
		setVarGlobal("event", evt);
		cancelaEvento = eval(getAtalho(atalho));
		//setVarGlobal("event", null);
	}catch(e){
		plcLog.alertaExcecao(e,"Erro ao executar atalho. Funcao: "+getAtalho(atalho));
	}
	
	if(cancelaEvento){
		if( evt.stopPropagation ) 
			evt.stopPropagation(); 
		evt.cancelBubble = true;
		return false;
	}
	
}

function keyCodePermitido(keyCode){
	return keyCodeSeta(keyCode) || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122);
}
function keyCodeSeta(keyCode){
	return keyCode == 40 || keyCode == 38 || keyCode == 39 || keyCode == 37;
}

/*-----------------------------------------------------*\
   	  FUN??ES PARA MANIPULA??O DE REQUEST VIA AJAX
\*-----------------------------------------------------*/
//Contrutor Ajax
function PlcAjax(){}

var plcAjax = new PlcAjax();

//Constante para que indica que o request via AJAX foi completado
PlcAjax.prototype.REQUEST_COMPLETO = 4;

//Constante para que indica que h? uma requisi??o AJAX em andamento
PlcAjax.prototype.AJAX_ATIVO = false;

//Indica se a aplica??o utiliza barra de progresso no tratamento
PlcAjax.prototype.ajaxUsaBarraProgresso = true;

//Habilita a barra de progresso. Default false
PlcAjax.prototype.ajaxEscondeBarraProgresso = function (){
	plcAjax.ajaxUsaBarraProgresso = false;
}
//Indica se a requisi??o ? para camadas diferentes da camada padr?o jCompany
PlcAjax.prototype.ajaxEspecifico = false;

//Indica se a requesi??o espec?fica utiliza barra de progresso. Default = true
PlcAjax.prototype.ajaxEspecificoBarraProgresso = true;

//Indica qual evento est? executando atualmente a requisi??o Ajax
PlcAjax.prototype.ajaxEventoAtual = "";

//Guarda objeto Event
PlcAjax.prototype.ajaxEvent;

//Indica tipo de encoding utilizado para retorno dos dados pelo Ajax
PlcAjax.prototype.ajaxCharset = "UTF-8";

//Indica que o m?todo GET deve ser executado com endere?o absoluto
PlcAjax.prototype.ajaxUrlBase = new Object();

//Indica uma ?rea espec?fica para atualiza??o
PlcAjax.prototype.ajaxCamadaEspecifica = "";

//Habilita m?todo GET com endere?o absoluto
PlcAjax.prototype.ajaxSetUrlBase = function (actionBase, urlBase){
	plcAjax.ajaxUrlBase[actionBase] = urlBase;
}


/**
* Executa chamada POST Ajax para URL específica
*/
PlcAjax.prototype.ajaxPost = function (URL, especifico, usaBarra, evento, form){
	if(especifico)
		plcAjax.ajaxHabilitaEspecifico(usaBarra);	
	plcAjax.ajaxSubmit("POST", evento, URL, form);
};
/**
* Executa chamada POST Ajax para método específico e URL específica e área específica
*/
PlcAjax.prototype.ajaxPostEspecificoArea = function (URL, usaBarra, evento, area, form){
	plcLog.debug("#####AJAX POST AREA ESPECIFICA");
	plcAjax.ajaxCamadaEspecifica = area;
	plcAjax.ajaxPost(URL, true, usaBarra, evento, form);
};
/**
* Executa chamada GET Ajax para método específico e URL específica e área específica
*/
PlcAjax.prototype.ajaxGetEspecificoArea = function (URL, usaBarra, evento, area){
	plcLog.debug("#####AJAX GET AREA ESPECIFICA");
	plcAjax.ajaxCamadaEspecifica = area;
	plcAjax.ajaxGet(URL, true, usaBarra, evento);
};

/**
* Executa chamada GET Ajax para URL específica
*/
PlcAjax.prototype.ajaxGet = function (URL, especifico, usaBarra, evento){
	plcLog.debug("#####AJAX GET");
	if(especifico)
		plcAjax.ajaxHabilitaEspecifico(usaBarra);	
	plcAjax.ajaxSubmit("GET", evento, URL);
};

/**
* Executa chamada Ajax para método específico e URL específica
*/
PlcAjax.prototype.ajaxSubmitSimples = function (metodo, URL, especifico, usaBarra, evento, form){
	plcLog.debug("#####AJAX SUBMIT SIMPLES");
	if(metodo.toUpperCase() == "GET")
		plcAjax.ajaxGet(URL, especifico, usaBarra, evento, form);
	else if(metodo.toUpperCase() == "POST")
		plcAjax.ajaxPost(URL, especifico, usaBarra, evento, form);
};

/**
* Executa chamada Ajax para método específico e URL atual
*/
PlcAjax.prototype.ajaxSubmitDireto = function (metodo, evento, especifico, usaBarra, form){
	plcLog.debug("#####AJAX SUBMIT DIRETO");
	if(metodo.toUpperCase() == "GET")
		plcAjax.ajaxGet("", especifico, usaBarra, evento, form);
	else if(metodo.toUpperCase() == "POST")
		plcAjax.ajaxPost("", especifico, usaBarra, evento, form);
};

//Fun??o para configura??o de submit espec?fico com Ajax
PlcAjax.prototype.ajaxSubmitEspecifico = function(metodo,evento,usaBarra) {
	plcAjax.ajaxHabilitaEspecifico(usaBarra);
	plcAjax.ajaxSubmit(metodo,evento);
};

/**
 * Executa chamada Ajax para m?todo espec?fico e URL espec?fica e ?rea espec?fica
 */
PlcAjax.prototype.ajaxSubmitEspecificoCamada = function (metodo,evento,usaBarra, camada){
	plcLog.debug("#####AJAX SUBMIT CAMADA ESPECIFICA");
	plcAjax.ajaxCamadaEspecifica = camada;
	plcAjax.ajaxSubmitEspecifico(metodo, evento, usaBarra);
};


//Habilita ajax especifico. Default false
PlcAjax.prototype.ajaxHabilitaEspecifico = function (usaBarraProgresso){
	plcAjax.ajaxEspecifico 			= true;
	if(!usaBarraProgresso || usaBarraProgresso == "")
		plcAjax.ajaxEspecificoBarraProgresso 	= usaBarraProgresso;
}

//Objeto que representa camadas AJAX da aplica??o
//Caso a camada de dados n?o seja informada, assume a camadaAtualiza??o com padr?o
PlcAjax.prototype.ajaxCamada = function (camadaAtualizacao, camadaDados){
	this.camadaAtualizacao 	= camadaAtualizacao;
	if(typeof camadaDados != "undefined" && camadaDados != "")
		this.camadaDados 	= camadaDados;
	else
		this.camadaDados 	= camadaAtualizacao;
}

//Cria camadas de atualiza??o via AJAX
PlcAjax.prototype.arrayCamadaAjax;
//Camadas por ID: retorna a posi??o no arrayCamadaAjax
PlcAjax.prototype.arrayCamadaAjaxPorId;
PlcAjax.prototype.ajaxCriaCamada 	= function (camadaAtualizacao, camadaDados){
	if(plcAjax.arrayCamadaAjax == null)
		plcAjax.arrayCamadaAjax = new Array();
	if(plcAjax.arrayCamadaAjaxPorId == null)
		plcAjax.arrayCamadaAjaxPorId = new Object();
	
	if (!plcAjax.arrayCamadaAjaxPorId[camadaAtualizacao]) { //Evita criar camada que j? existe.
		var camada = new plcAjax.ajaxCamada(camadaAtualizacao,camadaDados);
		plcAjax.arrayCamadaAjaxPorId[camadaAtualizacao] = plcAjax.arrayCamadaAjax.length;
		plcAjax.arrayCamadaAjax[plcAjax.arrayCamadaAjax.length] = camada;
	}
}

PlcAjax.prototype.cacheFunctionAjax = new Array();
//Guarda fun??es para serem executadas ap?s submit Ajax
PlcAjax.prototype.setFuncaoAposAjax = function (decFunction) {
   if (this.cacheFunctionAjax == null) {this.cacheFunctionAjax = new Array();}
   this.cacheFunctionAjax[this.cacheFunctionAjax.length] = decFunction;
}

//Executa fun??es ap?s submit Ajax
PlcAjax.prototype.ajaxExecutaFuncaoAposSubmit = function ()
{
	if(this.cacheFunctionAjax != null)
	{
		for(i = 0; i < this.cacheFunctionAjax.length; i++){
			try{
				eval(this.cacheFunctionAjax[i]);
			}catch(e){
				plcLog.debug("Erro ao executar funcao apos Ajax. Funcao "+this.cacheFunctionAjax[i]+" nao declarada.")
			}	
		}	
	}
}


//Recupera objeto de request para utiliza??o AJAX
PlcAjax.prototype.ajaxGetRequest = function (){

	var ajaxhttp = false;
	//Default
	if(window.XMLHttpRequest) {
		try {
			ajaxhttp = new XMLHttpRequest();
	    } catch(e) {
	    	alert("Objeto AJAX inexistente");
			ajaxhttp = false;
	    }
	// IE/Windows ActiveX version
	} else if(window.ActiveXObject) {
	   	try {
	    	ajaxhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  	} catch(e) {
	    	try {
	      		ajaxhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    	} catch(e) {
	    		alert("Erro ao utilizar Ajax. Controles ActiveX podem estar desabilitados. Habilite-os para utilizar Ajax\n"+
						"Acesse 'Tools/Internet Options/Security. Selecione 'Internet' ou 'Intranet', clique 'Custom Level'\n"+
						"Marque 'Enable' para o item 'Initialize and script ActiveX controls no marked as safe'");
	      		ajaxhttp = false;
	    	}
		}
	}
	return ajaxhttp;
}

/**
 * Realiza submit via request AJAX
 * @variable metodo M?todo utilizado no request [String,Ob] {GET,POST}
 * @variable evento Evento jCompany a ser enviado no request [String,Ob]
 * @variable url Url chamado no request [String,Ob]
 * @variable form Nome do formul?rio que cont?m os dados de entrada para request [String,Op]
 * @variable actionBase Action enviado no request. Necess?rio quando a chamada utilizar url absoluta [String,Op]
 * @see getForm
 * @see get
 * @see disparaBotao
 */
PlcAjax.prototype.ajaxSubmit = function (metodo, evento, url, form, actionBase){

	plcLog.debug("##### Entrou para submit AJAX");

	//if(!plcValida.validacaoVerificarRegras())
		//return false;

	var paramPost 	= "";
	var contentType	= "";
	var charset		= "";
	var form 		= getForm(form);

	//Cria??o do objeto xmlhttprequest
	var ajaxhttp 	= plcAjax.ajaxGetRequest();

	//Defini??o de content type
	if(ExpYes && form)
		contentType = form.encoding;
	else
		contentType = "application/x-www-form-urlencoded";

	//Defini??o de encoding
	if(plcAjax.ajaxCharset != "")
		charset = plcAjax.ajaxCharset;

	//Defini??o de url para request
    if (form && (url == "" || typeof url == "undefined"))
    	url = form.action;
    if (url == "")
    	url = location.href;

	if(plcAjax.ajaxUrlBase[actionBase]){
		url = plcAjax.ajaxUrlBase[actionBase] + url;
	}

	if(ajaxhttp){
		//Defini??o do m?todo de request
		metodo = metodo != "" && typeof metodo != "undefined" ? metodo : "POST";

		//Testa se foi informado o argumento id_Arg para l?gica de recupera??o autom?tica via id
		var id_Arg = get("id_Arg");
		if(id_Arg != "" && typeof id_Arg != "undefined"){
			var botao = getElementoPorId("RECUPERACAO_AUTOMATICA");
			//disparaBotao(selBotao(evento));
			disparaBotao(botao);
			return;
		}

		//Defini??o de evento para request
		if(evento != ""){
			if(metodo == "GET"){
					url =+ "?evento="+escape(evento);
			}
			if(metodo == "POST"){
				plcAjax.antesAjaxComplementaPost();
				paramPost += "evento="+escape(evento)+"&";
				paramPost += plcAjax.ajaxMontaPost(form);
			}
		}

		plcLog.debug("Parametros para request");
		plcLog.debug("URL: "+url);
		plcLog.debug("METODO: "+metodo);
		plcLog.debug("EVENTO: "+evento);
		plcLog.debug("CONTENT TYPE: "+contentType);
		plcLog.debug("CHARSET: "+charset);
		plcLog.debug("PARAMPOST: "+paramPost);
		plcLog.debug("ESPECIFICO BARRA: "+plcAjax.ajaxEspecificoBarraProgresso);

		plcLog.debug("EVENTO ATUAL AJAX: "+plcAjax.ajaxEventoAtual)
		//plcLog.debug("EVENTO JANELA: "+window.event.type)
		//Executa a??o antes de enviar submit
		plcLog.debug("AJAX ATIVO: "+plcAjax.AJAX_ATIVO);
		if(!plcAjax.AJAX_ATIVO && plcAjax.antesAjaxSubmit() && (plcAjax.ajaxEventoAtual == "" || plcAjax.ajaxEventoAtual == window.event.type)){
			plcLog.debug("Executando request Ajax para url: "+url);

			if(ExpYes) {
				if (window.event != null)
					plcAjax.ajaxEventoAtual = window.event.type;
			}else{
				if(document.captureEvents(Event.ONCHANGE))
					plcAjax.ajaxEventoAtual = document.captureEvents(Event.ONCHANGE).type;
			}

			//Aborta submit anterior n?o completado
			ajaxhttp.abort()
			//Abrir thread para request
			ajaxhttp.open(metodo,url,true);
			//Configura??o encoding do request
	   		ajaxhttp.setRequestHeader("Content-Type", contentType);
			//Configura??o encoding do request
			if(charset != "")
		   		ajaxhttp.setRequestHeader("charset", charset);
			//Definir fun??o para tratamento do retorno do request
			ajaxhttp.onreadystatechange = function(){
				if(ajaxhttp.readyState == plcAjax.REQUEST_COMPLETO){
					plcLog.debug(ajaxhttp.getAllResponseHeaders())
					plcAjax.ajaxResponse(ajaxhttp.responseText);
				}
			}

			if(plcAjax.ajaxUsaBarraProgresso && plcAjax.ajaxEspecificoBarraProgresso)
				iniciarBarraProgresso();
			plcAjax.AJAX_ATIVO = true;
			ajaxhttp.send(paramPost);
		}else{
			plcLog.debug("Request abandonado. Ajax ativo")
		}
	}
}

//Monta par?metros do request AJAX quando submiss?o for via m?todo POST
PlcAjax.prototype.ajaxMontaPost = function (form)
{

	plcLog.debug("##### Entrou para submit AJAX");

	var arrayCampos = getCamposEntrada ('','','',form);
	var strPost = "";

	//plcLog.debug("Qtde. argumentos: "+arrayCampos.length);
	if (arrayCampos && arrayCampos.length > 0){
		var i = 0;
		while(i < arrayCampos.length){
			//plcLog.debug("Tipo argumento: "+arrayCampos[i].type)
			//plcLog.debug("Qtde. valores: "+arrayCampos[i].length)
			var valCampo = encodeURIComponent(get(arrayCampos[i].name, form ? form.name : null));
				//valCampo = valCampo.replace("%",escape("%"));
				//valCampo = valCampo.replace("&",escape("&"));
				//valCampo = valCampo.replace("+","%2B");
				//valCampo = valCampo.replace("\"","%22");
				strPost += arrayCampos[i].name+"=" + valCampo +"&";
			//plcLog.debug("Argumento: "+arrayCampos[i].name+"="+valCampo);
			i++;
		}
	}
	return strPost;
}


//Recebe e trata a resposta do request AJAX
PlcAjax.prototype.ajaxResponse = function(ajaxResponse)
{
	plcLog.debug("##### Entrou para responder AJAX");
	try{
		//plcLog.debug("Valor response: "+ajaxResponse);
		if(plcAjax.ajaxEspecifico && plcAjax.arrayCamadaAjax && plcAjax.arrayCamadaAjax.length > 0){
			//Mostra camada Ajax espec?fica
			plcLog.debug("AJAX ESPECIFICO");
			plcLog.debug("AJAX CAMADA ESPECIFICA: "+plcAjax.ajaxCamadaEspecifica);

			var numCamadas = plcAjax.arrayCamadaAjax.length;
			for(j = 0; j < numCamadas; j++){
				plcLog.debug("CAMADA ATUALIZACAO: "+plcAjax.arrayCamadaAjax[j].camadaAtualizacao);
				plcLog.debug("CAMADA DADOS: "+plcAjax.arrayCamadaAjax[j].camadaDados);
				if (plcAjax.ajaxCamadaEspecifica=="" || plcAjax.ajaxCamadaEspecifica==plcAjax.arrayCamadaAjax[j].camadaAtualizacao) {
					plcLog.debug("USANDO CAMADA: "+plcAjax.arrayCamadaAjax[j].camadaAtualizacao);
					var ajaxCamada	= plcAjax.ajaxPreparaCamada(ajaxResponse, "\<"+plcAjax.arrayCamadaAjax[j].camadaDados+"\>", "\<\/"+plcAjax.arrayCamadaAjax[j].camadaDados+"\>");
					plcAjax.ajaxMostraCamada(ajaxCamada, plcAjax.arrayCamadaAjax[j].camadaAtualizacao);
				}
			}
		}else{
			plcLog.debug("AJAX PADRAO");
			//Camada Ajax padr?o jCompany
			var ajaxCamada	= plcAjax.ajaxPreparaCamada(ajaxResponse, "\<AJAX\>", "\<\/AJAX\>");
			plcAjax.ajaxMostraCamada(ajaxCamada, "AJAX");
		}

		//Monta conteudo especifico
		plcLog.debug("AJAX USA BARRA PROGRESSO: "+plcAjax.ajaxUsaBarraProgresso)
		plcLog.debug("AJAX ESPECIFICO USA BARRA PROGRESSO: "+plcAjax.ajaxEspecificoBarraProgresso)
		if(plcAjax.ajaxUsaBarraProgresso && plcAjax.ajaxEspecificoBarraProgresso)
			pararBarraProgresso();
		plcAjax.aposAjaxSubmit();
		plcAjax.ajaxMantemTabAgil();

		if (!plcAjax.ajaxEspecifico){
			moverFoco();
			executarFuncaoOnLoad();
		}

		//plcLog.debug("Funcoe ajax: "+plcAjax.cacheFunctionAjax.length)
		plcAjax.ajaxExecutaFuncaoAposSubmit();

		//NAO RETIRAR PORQUE TRATA EDI??O DE CAMPOS
		plcAjax.ajaxInicializarEstado();

	}catch(ex){
		if(plcAjax.ajaxUsaBarraProgresso && plcAjax.ajaxEspecificoBarraProgresso)
			pararBarraProgresso();
		plcAjax.ajaxInicializarEstado();
		plcLog.alertaExcecao(ex);
	}

	plcLog.debug("Finalizando Ajax");
}

PlcAjax.prototype.ajaxInicializarEstado = function(){

	//Limpar vari?veis para camada espec?fica
	plcAjax.ajaxEspecifico = false;
	plcAjax.ajaxEspecificoBarraProgresso = true;

	//Configurar Ajax inativo
	plcAjax.AJAX_ATIVO = false;

	//Limpa cache de fun??es
	plcAjax.cacheFunctionAjax = new Array();

	//Limpa refer?ncia ao evento Ajax
	plcAjax.ajaxEventoAtual = "";
	
	//Limpa ?rea espec?fica de atualiza??o
	plcAjax.ajaxCamadaEspecifica = "";

}
//Prepara a camada AJAX para atualiza??o
PlcAjax.prototype.ajaxPreparaCamada = function (ajaxResponse, tagIni, tagFim){

	plcLog.debug("##### Entrou para preparar camada AJAX");
	plcLog.debug("TAG INICIO: "+escape(tagIni));
	plcLog.debug("TAG FIM: "+escape(tagFim));
	var ajaxCamada = "";
	if(ajaxResponse){
		plcLog.debug("Filtrando camada AJAX");
		var posIni = ajaxResponse.indexOf(tagIni);
		var posFim = ajaxResponse.indexOf(tagFim);
		plcLog.debug("PosIni: "+posIni+"<br>posFim: "+posFim);
		ajaxCamada = ajaxResponse.substring( posIni, posFim);
	}

	plcLog.debug("Camada AJAX:<br> "+ajaxCamada);
	return ajaxCamada;
}

//Atualiza a camada AJAX no elemento correspondente
PlcAjax.prototype.ajaxMostraCamada= function (ajaxConteudo, elementoID){

	plcLog.debug("##### Entrou para mostrar resultado pesquisa");
	plcLog.debug("ID Elemento atualizacao: "+elementoID);
	var elementoAtualizacao = getElementoPorId(elementoID);
	plcLog.debug("Elemento atualizacao: "+elementoAtualizacao.id);
	if(elementoAtualizacao != null){
		elementoAtualizacao.innerHTML = ajaxConteudo;
		plcAjax.executaScriptsInternos(elementoAtualizacao);
	}
}

PlcAjax.prototype.executaScriptsInternos = function (elemento) {
	plcLog.debug("##### Entrou para executar scripts internos");
	if (ExpYes || tecnologia == "Struts") {
		var tags = elemento.getElementsByTagName("SCRIPT");
		for(t = 0; t < tags.length; t++){
			tag = tags[t];
			plcLog.debug("AVALIAR: "+tag.getAttribute("avaliar")+"<br>TEXTO TAG: "+tag.text);
			if (tag.text && tag.text!='') {
				if (tag.getAttribute("avaliar")=='S' || (typeof tag.getAttribute("id") != 'undefined' && tag.getAttribute("id") != null && tag.getAttribute("id").indexOf('avaliar:')>-1)) {
					window.eval(tag.text);
					plcLog.debug("AVALIADO");
				}
			}
		}
		if (typeof PlcFormTabs == 'function') PlcFormTabs();
	}
}

//Fun??o para configura??o de submit espec?fico com Ajax
PlcAjax.prototype.ajaxSubmitEspecifico = function(metodo,evento,usaBarra) {
	plcAjax.ajaxHabilitaEspecifico(usaBarra);
	plcAjax.ajaxSubmit(metodo,evento);
}

/**
 * Executa chamada Ajax para m?todo espec?fico e URL espec?fica e ?rea espec?fica
 */
PlcAjax.prototype.ajaxSubmitEspecificoCamada = function (metodo,evento,usaBarra, camada){
	plcLog.debug("#####AJAX SUBMIT CAMADA ESPECIFICA")
	plcAjax.ajaxCamadaEspecifica = camada;
	plcAjax.ajaxSubmitEspecifico(metodo, evento, usaBarra);
}

//Func?o chamada ap?s submit Ajax para manter situa??o de tab folder ?gil
PlcAjax.prototype.ajaxMantemTabAgil = function () {
	mantemAbaSelecionada();
}

//Implementa??o de a??es antes de enviar o submit
PlcAjax.prototype.antesAjaxSubmit = function (){ return true;}

//Implementa??o de a??es ap?s retorno submit
PlcAjax.prototype.aposAjaxSubmit = function (){}

//Implementação de ações para complemento do post antes do submit
PlcAjax.prototype.antesAjaxComplementaPost = function (){ }

/*IMPRESS?O INTELIGENTE*/
function executaImpressao()
{
	var bodyOriginal = document.body.innerHTML;
	document.body.innerHTML = "<form>"+bodyOriginal + opener.objImpressao.html+"</form>";
	//Verifica se utiliza impress?o inteligente
	var impIntel = getParametroUrl ( "impIntel", document.location.search);
	if(impIntel != null && impIntel.toLowerCase() == "s"){
		gerarImpressaoInteligente()
	}
}

function gerarImpressaoInteligente(){

	var tag 	= "";

	var tags 	= document.getElementsByTagName("INPUT");
	
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if(tag.type == "reset"){
			tag.style.display = 'none';
		}else if(tag.type == "checkbox"){
			tag.disabled = true;
		}else if(tag.type == "password"){
			tag.disabled = true;
		}else if(tag.type == "radio"){
			tag.disabled = true;			
		}else if(tag.type != "hidden" && tag.type != "button" && tag.type != "password" && tag.type != "submit"){
			substituirCampoPorLabel(tag);
		}
	}
	
	tags = document.getElementsByTagName("BUTTON");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if (tag.parentNode.id!="barraAcoes"){
			tag.disabled = true;
		}
	}
	tags = document.getElementsByTagName("TEXTAREA");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		substituirCampoPorLabel(tag)
	}
	tags = document.getElementsByTagName("PASSWORD");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		tag.disabled = true;
	}
	tags = document.getElementsByTagName("SELECT");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		substituirCampoPorLabel(tag)
	}

	tags = document.getElementsByTagName("SPAN");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if(tag.className == "bt")
			tag.style.display = 'none';
	}
	//EDITORES HTML
	/*tags = opener.getVarGlobal("editores");
	if(tags){
		for(t = 0; t < tags.length; t++){
			tag = tags[t];
			tag.style.display = 'none';
		}
	}*/
}

function gerarCamposProtegidos(){
	
	var tag 	= "";
	var tags 	= document.getElementsByTagName("INPUT");
	
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if(tag.type == "reset"){
			tag.disabled = true;
		}else if(tag.type == "checkbox"){
			tag.disabled = true;
		}else if(tag.type == "password"){
			tag.disabled = true;
		}else if(tag.type != "radio" && tag.type != "hidden" && tag.type != "button" && tag.type != "password" && tag.type != "submit"){
			tag.disabled = true;
		}
	}
	

	tags = document.getElementsByTagName("BUTTON");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if (tag.parentNode.id!="barraAcoes" && tag.id!='rodape:desconecta'){
			tag.disabled = true;
		}
	}
	tags = document.getElementsByTagName("TEXTAREA");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		tag.disabled = true;
	}
	tags = document.getElementsByTagName("PASSWORD");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		tag.disabled = true;
	}
	tags = document.getElementsByTagName("SELECT");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		tag.disabled = true;
	}

	tags = document.getElementsByTagName("SPAN");
	for(t = 0; t < tags.length; t++){
		tag = tags[t];
		if(tag.className == "bt"){
			tag.style.display = 'none';
		}
	}
}

function substituirCampoPorLabel(tag){
	var tagSpan = "";
	var paiTag = tag.parentNode;
	if(paiTag){
		if(tag.type == "select-multiple"){
			for(s = 0; s < tag.options.length; s++){
				tagSpan = document.createElement("SPAN");
				if(tag.options[s].selected)
					tagSpan.style.backgroundColor = "yellow";
				tagSpan.innerHTML = tag.options[s].text+"<br>";
				paiTag.appendChild(tagSpan);
			}
		}else{
			tagSpan = document.createElement("SPAN");
			if(tag.type == "select-one")
				tagSpan.innerHTML = tag.options[tag.selectedIndex].text;
			else
				tagSpan.innerHTML = tag.value+"&nbsp;";
			paiTag.appendChild(tagSpan);
		}
		tag.style.display = 'none';
	}
}

conteudo_textarea = "";
function garanteTamanhoMaximo(campo, tamanhoMaximo){	
	num_caracteres = campo.value.length;
	if (num_caracteres > tamanhoMaximo){
      campo.value = conteudo_textarea;
	}else{
      conteudo_textarea = campo.value;
	}
}

function converteMaiuscula(evt, campo){
	var key = getKeyCode(evt);
	if(key != 37 && key != 39 && (plcGeral.alfabeticoPattern.test(campo.value) || plcGeral.numericoPattern.test(campo.value)))
		campo.value = campo.value.toUpperCase();
}

function converteMinuscula(evt, campo){
	var key = getKeyCode(evt);
	if(key != 37 && key != 39 && (plcGeral.alfabeticoPattern.test(campo.value) || plcGeral.numericoPattern.test(campo.value)))
		campo.value = campo.value.toLowerCase();
}

/******************************************************************
VALIDA??O DE DADOS
******************************************************************/
//Contrutor Validacao
function PlcValida(){}

var plcValida = new PlcValida();

var arrayValidacaoCampos 	= new Array();

function validacaoCampo (argumentos){
	this.nomeCampo 		= argumentos[0];	
	this.formatoCampo 	= argumentos[1];
	this.msgErro		= argumentos[2];
	this.PARAM_0	= "";
	this.PARAM_1	= "";
	this.PARAM_2	= "";
	this.PARAM_3	= "";

	for(i = 3; i < argumentos.length; i++)
	{
		if(argumentos[i] == "PARAM_0")
			this.PARAM_0 = argumentos[++i];
		if(argumentos[i] == "PARAM_1")
			this.PARAM_1 = argumentos[++i];
		if(argumentos[i] == "PARAM_2")
			this.PARAM_2 = argumentos[++i];
		if(argumentos[i] == "PARAM_3")
			this.PARAM_3 = argumentos[++i];
	}
	
	this.msgErro = this.msgErro.replace("{0}", this.PARAM_0);
	this.msgErro = this.msgErro.replace("{1}", this.PARAM_1);
	this.msgErro = this.msgErro.replace("{2}", this.PARAM_2);
	this.msgErro = this.msgErro.replace("{3}", this.PARAM_3);
}

function validacaoCriaCampo(){
	arrayValidacaoCampos[arrayValidacaoCampos.length] = new validacaoCampo(arguments);
}

PlcValida.prototype.validacaoVerificarRegras = function(){
	plcLog.debug("VERIFICAR REGRAS");
	plcLog.newLogErros();
	if(!validacaoExecutar()){
		plcLog.logMostraErros();
		return false;
	}
	return true;
}

function validacaoExecutar(){
	plcLog.debug("VALIDACAO EXECUTAR");
	var validou = true;
	//jaValidou 	= false;
	plcLog.debug("QTDE. CAMPOS: "+arrayValidacaoCampos.length);
	for(var i = 0; i < arrayValidacaoCampos.length; i++){
		plcLog.debug("VAI VALIDAR CAMPO "+i);
		plcLog.debug("CAMPO NOME: "+arrayValidacaoCampos[i].nomeCampo);
		plcLog.debug("CAMPO FORMATO: "+arrayValidacaoCampos[i].formatoCampo);
		try{
			//alteraClasse ("CAMPO",arrayValidacaoCampos[i].nomeCampo,"CLASSE","campoComErro","INICIAL")
			if(!eval("plcValida.regra_"+arrayValidacaoCampos[i].formatoCampo.toLowerCase()+"(arrayValidacaoCampos[i])"))
				validou = false;
		plcLog.debug("VALIDOU CAMPO "+i);
		}catch(ex){
			if(plcLog.logGetDescExcecao(ex) == "Object doesn't support this property or method")
				plcLog.logAdicionaErroCampo("EXCECAO","ALERTA DE ERRO: Formato '"+arrayValidacaoCampos[i].formatoCampo+"' invalido para o campo '"+arrayValidacaoCampos[i].nomeCampo+"'");
			else {
				plcLog.logAdicionaErro(plcLog.montaMsgExcecao(ex));
				}
			return false;
		}
	}
	
	return validou;
}

PlcValida.prototype.regra_numerico = function (objCampo){
	var valCampo = get(objCampo.nomeCampo);
	if(valCampo != "" && !plcGeral.numericoPattern.test(valCampo)){
		//plcLog.logAdicionaErroCampo(objCampo.nomeCampo,objCampo.msgErro);
		return false;
	}
	return true;	
}

PlcValida.prototype.regra_alfabetico= function (objCampo){
	var valCampo = get(objCampo.nomeCampo);
	if(valCampo != "" && !plcGeral.alfabeticoPattern.test(valCampo)){
		//plcLog.logAdicionaErroCampo(objCampo.nomeCampo,objCampo.msgErro);
		return false;
	}
	return true;	
}

PlcValida.prototype.regra_data= function (objCampo){
	var valCampo = get(objCampo.nomeCampo);
	if(valCampo != "" && !plcGeral.dataPattern.test(valCampo)){
		plcLog.logAdicionaErroCampo(objCampo.nomeCampo,objCampo.msgErro);
		return false;
	}
	return true;	
}

PlcValida.prototype.regra_datahora= function (objCampo){
	var valCampo = get(objCampo.nomeCampo);
	if(valCampo != "" && !plcGeral.datahoraPattern.test(valCampo)){
		plcLog.logAdicionaErroCampo(objCampo.nomeCampo,objCampo.msgErro);
		return false;
	}
	return true;	
}

var flagDesprezarA = new Array();

function pegaFlagDesprezar(chaveDet,chaveSubDet) {

   for(var i = 0; i < flagDesprezarA.length; i++){
	
	    if (chaveSubDet!='') { 
	//		alert('chaveSubDet='+chaveSubDet+ ' componente='+flagDesprezarA[i].componente);
	    }
		if ((chaveDet.indexOf(flagDesprezarA[i].componente)>-1 && chaveSubDet=='') ||
			(chaveSubDet!='' && flagDesprezarA[i].componente.indexOf(chaveSubDet)>-1)) {
			return flagDesprezarA[i].flagDesprezar;
		}
		
	}
	
	return '';

}

PlcValida.prototype.regra_obrigatorio= function (objCampo){

	var valCampo = get(objCampo.nomeCampo);
	//alert('obj='+objCampo.nomeCampo+' valor campo='+valCampo);
	var considera = true;
	if (objCampo && objCampo.nomeCampo.indexOf("].")>-1) {
		// Pega valor da coluna flag para a cole?ao corrente
		var fimDet = objCampo.nomeCampo.indexOf("].")+2;
		var prefixoDet = objCampo.nomeCampo.substring(0,fimDet);
		var fimSubDet = objCampo.nomeCampo.indexOf("].",fimDet+1);
		//	alert('fimSubDet'+fimSubDet+ ' para '+objCampo.nomeCampo);
		var prefixoSubDet='';
		if (fimSubDet>-1) {
			var iniSubDet = objCampo.nomeCampo.indexOf("[",fimDet+1);
			prefixoSubDet=objCampo.nomeCampo.substring(fimDet,iniSubDet+1);
		//	alert('achou um subdet='+prefixoSubDet);
			fimSubDet = fimSubDet+2;
			fimDet = fimSubDet;
		}
		var prefixoTotal = objCampo.nomeCampo.substring(0,fimDet);
//		alert('prefixo total='+prefixoTotal+' pref det='+prefixoDet+' pref subdet='+prefixoSubDet);
		var colunaFlag = prefixoTotal + pegaFlagDesprezar(prefixoDet,prefixoSubDet);
	//	alert('colunaFlagNome='+colunaFlag+' valor='+get(colunaFlag));
		considera = get(colunaFlag) != '';
	}
	if (considera && valCampo.trim() == ""){
		plcLog.logAdicionaErroCampo(objCampo.nomeCampo,"OBRIGATORIO" );
		return false;
	}
	return true;	
}

String.prototype.trim = function(){
    var str = this;
    while (str.charAt(0) == " ")
        str = str.substr(1,str.length -1);
    while (str.charAt(str.length-1) == " ")
        str = str.substr(0,str.length-1);
    return str;
} 

/******************************************************************************\
						NAVEGA??O AUTOMATICA TABFOLDER
\******************************************************************************/

//Navega pr?xima aba
function tabFolderNavegaProx(evt){
	plcLog.debug("##### Entrou tabFolderNavegaProx");
	tabFolderNavegaAutomatico(evt,1)
}
//Navega aba anterior
function tabFolderNavegaAnte(evt){
	plcLog.debug("##### Entrou tabFolderNavegaAnte");
	tabFolderNavegaAutomatico(evt,-1)
}
//L?gicas gen?ricas na navega??o autom?tica de tab folder
function tabFolderNavegaAutomatico(evt,nav){

	plcLog.debug("##### Entrou tabFolderNavegaAutomatico");
	if(tabFolderNavegaInibe(evt, nav))
		return;
	if (typeof tabSelecionada != 'undefined') 
		tabFolderNavegaTrocaAba(nav)
}

//L?gicas gen?ricas para troca de aba na navega??o autom?tica de tab folder
function tabFolderNavegaTrocaAba(nav){
	plcLog.debug("tabFolderNavegaTrocaAba - tabSelecionada: "+tabSelecionada)
	var nomeAbaAtual	= tabSelecionada.substring(0,tabSelecionada.indexOf("_"));
	plcLog.debug("tabFolderNavegaTrocaAba - NOME ABA ATUAL: "+nomeAbaAtual)
	var numAbaAtual	= tabSelecionada.substring(tabSelecionada.indexOf("_")+1);
	plcLog.debug("tabFolderNavegaTrocaAba - NUM ABA ATUAL: "+numAbaAtual);
	plcLog.debug("tabFolderNavegaTrocaAba - NAV: "+nav);
	var numNavAba	= (parseInt(numAbaAtual) + parseInt(nav));
	if(numNavAba < 0) 
		numNavAba = 0;
	plcLog.debug("tabFolderNavegaTrocaAba - NUM NAV ABA: "+numNavAba);
	plcLog.debug("tabFolderNavegaTrocaAba - ABA NAV: "+nomeAbaAtual +"_"+numNavAba);
	plcLog.debug("tabFolderNavegaTrocaAba - GET ABA: "+getVarGlobal(numNavAba));
	if(numNavAba < layers.length){
		trocaAba(numNavAba,nomeAbaAtual +"_"+numNavAba,nomeAbaAtual +"_"+numNavAba );
		//set('detCorrPlc',getVarGlobal(numNavAba));
		//showHideAba(nomeAbaAtual +"_"+numNavAba,nomeAbaAtual +"_"+numNavAba );
		tabFolderFocaCampo(numNavAba)		
	}
}

//Foca o campo mais apropriada na tab folder ap?s navega??o autom?tica
function tabFolderFocaCampo(numNavAba){
	
	if (tabFolderCamposFoco != null && tabFolderCamposFoco.length > 0){
		setTimeout("setFocus('"+tabFolderCamposFoco[numNavAba - 1]+"')",20);	
	}else{
		var camposVolta = getVarGlobal("cacheCamposVolta");
		if (camposVolta!=null)
		{
			if(numNavAba == 0 && getVarGlobal('campoFocadoPlc')){
				setTimeout("setFocus('"+getVarGlobal('campoFocadoPlc').name+"')",20);
				return;
			}	
			if(numNavAba > 0)
				numNavAba--;	
			if(camposVolta[numNavAba])
				setTimeout("setFocus('"+camposVolta[numNavAba].name+"')",20);
		}
	}
}

//Verifica se tecla pressionada permite navega??o autom?tica em tab folder
function tabFolderNavegaInibe(evt, nav){
	var key = 0;
   	if (ExpYes){
   		evt = window.event;
		key = evt.keyCode
   	}else{
		key = evt.which;
	}	
	return key != 9 || (nav == -1 && !evt.shiftKey) || (nav == 1 && evt.shiftKey);	
}
var tabFolderCamposIda 			= "";
var tabFolderCamposVolta 		= "";
var tabFolderCamposFoco 		= null;
function setTabFolderCamposIda(campos){
	tabFolderCamposIda = campos;
}
function setTabFolderCamposVolta(campos){
	tabFolderCamposVolta = campos;
}
function setTabFolderCamposFoco(campos){
	tabFolderCamposFoco = separaListaTermos(campos, ",");
}

//Configura eventos onkeydown para marcar campo focado para l?gicas de navega??o em tab folder via onblur
function configuraEventosTabFolder(){
	
		var camposIda 		= separaListaTermos(tabFolderCamposIda,",")
		var camposVolta 	= separaListaTermos(tabFolderCamposVolta,",")
		var cacheCamposIda	= new Array();
		var cacheCamposVolta= new Array();
		var campo		= null;
		var detalhes		= null;
		//Configura campos ida
		for(ci = 0; ci < camposIda.length; ci++){
			campo = null;
			/*if(camposIda[ci] && camposIda[ci].indexOf(".") > -1){
				detalhes = getCamposDetalhes(camposIda[ci]);
				campo = detalhes.ultimo;
			}	
			else*/
			campo = getCampo(camposIda[ci]);
			if(campo && campo != null){
				setUpOnEventoTagCampoNome (campo.tagName, "onkeydown", "tabFolderNavegaProx", campo.name);
				cacheCamposIda[cacheCamposIda.length] = campo;
			}
		}
		setVarGlobal("cacheCamposIda",cacheCamposIda);
		//Configura campos volta
		for(cv = 0; cv < camposVolta.length; cv++){
			campo = null;
			//alert("camposVolta[cv]: "+camposVolta[cv])	
			/*if(camposVolta[cv] && camposVolta[cv].indexOf(".") > -1){
				detalhes = getCamposDetalhes(camposVolta[cv]);
				campo = detalhes.primeiro;
			}
			else*/
			campo = getCampo(camposVolta[cv]);
			//alert("campo.name: "+campo.name)	
			if(campo && campo != null){
				setUpOnEventoTagCampoNome (campo.tagName, "onkeydown", "tabFolderNavegaAnte", campo.name)
				cacheCamposVolta[cacheCamposVolta.length] = campo;
			}
		}
		setVarGlobal("cacheCamposVolta",cacheCamposVolta);	
	
}	

//Recupera primeiro e ?ltimo detalhe de uma lista
function getCamposDetalhes(nomeCampo, nomeLista){

	var ultimoDetalhe = null;
	var primeiroDetalhe = null;
	var nomeCampoAux = nomeCampo;
	var posPonto = nomeCampoAux.indexOf(".");
	if(posPonto > -1){
		nomeLista = nomeCampoAux.substring(0,posPonto);
		nomeCampo = nomeCampoAux.substring(posPonto+1);
	}
	var cont = 0;
	do{
		var campo = getCampo(nomeLista+"["+cont+"]."+nomeCampo);
		if(campo){
			ultimoDetalhe = campo;
			if(primeiroDetalhe == null)
				primeiroDetalhe = ultimoDetalhe;
		}else{
			cont = -1;
			break;	
		} 
		cont++;
	}while(getCampo(nomeLista+"["+cont+"]."+nomeCampo) && cont > 0)
	var retorno = new Object();
	retorno.primeiro = primeiroDetalhe;
	retorno.ultimo = ultimoDetalhe;
	return retorno;
}
//***********************FIM NAVEGA??O AUTOMATICA TABFOLDER *********************
//Reverte configura??es de layout para campos focados no evento onbluir
function reverteDestaqueCampoFocado(evt){
	destacaCampoFocado(evt);
}
//L?gica de destaque autom?tico de campos focados
function destacaCampoFocado(evt){
	if(ExpYes){
		evt = window.event;
		obj = evt.srcElement;
	}	
	else{	
		obj = evt.target;
		if(obj.type == 'checkbox' || obj.type == 'radio')
			obj = obj.parentNode;
	}	
	with (obj){
		try	{
			if(evt.type == 'focus' || typeof oldClassName == "undefined" || oldClassName == null){
				if(className != "adicionaBorda"){
					oldClassName = className;
						if(obj.type && type.indexOf("select") > -1)
							jQuery(obj).addClass("adicionaFundo");
						else	
							jQuery(obj).addClass("adicionaBorda");
				}	
			}else {
				className = oldClassName;
				oldClassName = null;
			}
		}catch(e){}
	}
	if(	evt.type == 'focus')
		eventoTrataonfocus(evt, obj);
	else if(	evt.type == 'blur')
		eventoTrataonblur(evt, obj);
}

/******************************************************************************\
						NAVEGA??O AUTOMATICA 
\******************************************************************************/
//Construtor PlcNavegacao
//function PlcNavegacao (){}
//Objeto plcNavegacao
//var plcNavegacao = new PlcNavegacao();
//Fun??es para navega??o espec?ficas nas setas para extens?o no cliente
//PlcNavegacao.prototype.navegaSetaParaCimaEspecifico = function() {}
//PlcNavegacao.prototype.navegaSetaParaBaixoEspecifico = function() {}
//PlcNavegacao.prototype.navegaSetaParaEsquerdaEspecifico = function() {}
//PlcNavegacao.prototype.navegaSetaParaDireitaEspecifico = function() {}

//Executa navega??o autom?tica com pressionamento de seta para cima
function navegaSetaParaCima(){
	plcLog.debug("##### Entrou navegaSetaParaCima")
	
	//Verificar se inibe navega??o
	if(navegaSetaInibe())
		return;
	//Navega??o no menu de sistema
	if(plcGeral.MENU_ATIVO){
		menuSistemaNavSetaParaCima();
		return;
	}	
	//Verifica se linha de sele??o foi selecionada para navega??o
	if(getVarGlobal("trSelecao") == null)
		return;
	
	var numLinha = "undefined";
	
	if (tecnologia == "Struts"){
		numLinha = getVarGlobal("trSelecao") == null || getVarGlobal("trSelecao") == "undefined" ? -1 : getVarGlobal("trSelecao").id.substring(getVarGlobal("trSelecao").id.lastIndexOf("_")+1);
	} else {
		
		var padrao = new RegExp("corpo\\:(\\w+)\\:(\\d+)\\:(\\w+)");
		
		if (getVarGlobal ("trSelecaoIdIter") == null){
			
			var primeiraLinha = new RegExp("corpo\\:(\\w+)\\:0:(\\w+)");
			
			if (primeiraLinha.exec(document.documentElement.innerHTML) != null){
				//Registrar tokens da primeira linha
				var padraoPrimeiraLinha = primeiraLinha.exec(document.documentElement.innerHTML);
				setVarGlobal("trSelecaoIdIter", padraoPrimeiraLinha[1]);
				setVarGlobal("trSelecaoLinhaSel", padraoPrimeiraLinha[2]);
			}
		}
		
		var linhaSelecao = getVarGlobal("trSelecao");
		if (linhaSelecao != null && linhaSelecao != "undefined"){
			var recebePadrao = padrao.exec(linhaSelecao.id);
			numLinha = recebePadrao[2];
		} else {
			numLinha = -1;
			/*getVarGlobal("trSelecao") == null || getVarGlobal("trSelecao") == "undefined" ? -1 : getVarGlobal("trSelecao").id
			.substring(21,getVarGlobal("trSelecao").id.lastIndexOf(":linhaSel"));*/
		}
	}
	 
	navegaListaSelecao(parseInt(numLinha)-1);
}
//Executa navega??o autom?tica com pressionamento de seta para baixo
function navegaSetaParaBaixo(){
	plcLog.debug("##### Entrou navegaSetaParaBaixo")

	//Verificar se inibe navega??o
	if(navegaSetaInibe())
		return;
	//Navega??o no menu de sistema
	if(plcGeral.MENU_ATIVO){
		menuSistemaNavSetaParaBaixo();
		return;
	}
	//Abrir janela popup
	if(navegaSetaPopup())
		return;
 	var evt = getVarGlobal("event");
	if(typeof evt != "undefined"){
		var elemento = ""; 
		try{
			elemento = plcEvento.getEventoElemento(evt);
		}catch(e){}	
		if(elemento && elemento.id == "LINK_INTELIGENTE")
			dispararEvento(elemento, "onclick");
	}	

	var numLinha = "undefined";
	
	if (tecnologia == "Struts"){
		numLinha = getVarGlobal("trSelecao") == null || getVarGlobal("trSelecao") == "undefined" ? -1 : getVarGlobal("trSelecao").id.substring(getVarGlobal("trSelecao").id.lastIndexOf("_")+1);
	} else {
		
		var padrao = new RegExp("corpo\\:(\\w+)\\:(\\d+)\\:(\\w+)");
		
		if (getVarGlobal ("trSelecaoIdIter") == null){
			
			var primeiraLinha = new RegExp("corpo\\:(\\w+)\\:0:(\\w+)");
			
			if (primeiraLinha.exec(document.documentElement.innerHTML) != null){
				//Registrar tokens da primeira linha
				var padraoPrimeiraLinha = primeiraLinha.exec(document.documentElement.innerHTML);
				setVarGlobal("trSelecaoIdIter", padraoPrimeiraLinha[1]);
				setVarGlobal("trSelecaoLinhaSel", padraoPrimeiraLinha[2]);
			}
		}
		
		var linhaSelecao = getVarGlobal("trSelecao");
		if (linhaSelecao != null && linhaSelecao != "undefined"){
			var recebePadrao = padrao.exec(linhaSelecao.id);
			numLinha = recebePadrao[2];
		} else {
			numLinha = -1;
			/*getVarGlobal("trSelecao") == null || getVarGlobal("trSelecao") == "undefined" ? -1 : getVarGlobal("trSelecao").id
			.substring(21,getVarGlobal("trSelecao").id.lastIndexOf(":linhaSel"));*/
		}
	}
	
	
	navegaListaSelecao(parseInt(numLinha)+1);
}	

function navegaSetaParaDireita(){
	plcLog.debug("##### Entrou navegaSetaParaDireita")
 	//var evt = getVarGlobal("event");
	tabFolderNavegaSetaDireita()		
}
function navegaSetaParaEsquerda(){
	plcLog.debug("##### Entrou navegaSetaParaEsquerda")
 	//var evt = getVarGlobal("event");
	tabFolderNavegaSetaEsquerda()
}

//Fun??es para navega??o espec?ficas em tabfolder para extens?o no cliente
//PlcNavegacao.prototype.tabFolderNavegaSetaDireitaEspecifico = function() {}
//PlcNavegacao.prototype.tabFolderNavegaSetaEsquerda = function() {}

function tabFolderNavegaSetaDireita(){
	plcLog.debug("##### Entrou tabFolderNavegaSetaDireita");
	tabFolderNavegaTrocaAba(1)
}
function tabFolderNavegaSetaEsquerda(){
	plcLog.debug("##### Entrou tabFolderNavegaSetaEsquerda");
	tabFolderNavegaTrocaAba(-1)
}

function navegaSetaInibe(){
	plcLog.debug("##### Entrou navegaSetaInibe")

 	var evt = getVarGlobal("event");
	var elemento = null;
	if(typeof evt != "undefined"){ 
		try{
			elemento = plcEvento.getEventoElemento(evt);
		}catch(e){}	
		if(elemento && elemento.type && (elemento.type.indexOf("select") >= 0 || elemento.type == 'textarea'))
			return true;
	}	
	return false;	
}	

var idElementoPopupPlc = "";
function setIdElementoPopupPlc(id){
	plcLog.debug("##### Entrou setIdElementoPopupPlc")
	idElementoPopupPlc = id;
}
function getIdElementoPopupPlc(){
	plcLog.debug("##### Entrou getIdElementoPopupPlc")
	return idElementoPopupPlc;
}

function navegaSetaPopup(){
	plcLog.debug("##### Entrou navegaSetaPopup")

 	var evt = getVarGlobal("event");
	if(getElementoPorId(getIdElementoPopupPlc()+"SelPop")){
		setNavSetaFocoPlc(true);
		setVarGlobal("campoPopupPlc", getElementoPorId(getIdElementoPopupPlc()+"SelPop"));
		setIdElementoPopupPlc("");
		setTimeout("navegaSetaAbrePopup()", 10);
		return true;
	}	
	setIdElementoPopupPlc("");
	return false;	
}	

function navegaSetaAbrePopup(campoPopupPlc){
	plcLog.debug("##### Entrou navegaSetaAbrePopup")
	
	if(typeof campoPopupPlc == "undefined"){
		campoPopupPlc = getVarGlobal("campoPopupPlc");
		setVarGlobal("campoPopupPlc", null);
	}	
	if(ExpYes)	
		dispararEvento(campoPopupPlc, "onclick")		
	else{
		campoPopupPlc = campoPopupPlc.getAttribute("onclick");
		if(campoPopupPlc.indexOf("return") > -1)
			campoPopupPlc = campoPopupPlc.substring(0, campoPopupPlc.indexOf("return")) 
		eval(campoPopupPlc);
	}
}

function navegaListaSelecao(linha){
	if(typeof linha == "undefined")
		linha = 0;
	
	var trSelecao = "undefined";
	
	if (tecnologia == "Struts"){
		trSelecao = getElementoPorId('linhaSel_'+linha);
	} else if (tecnologia == "JSF"){
		var trSelecaoIdIter = getVarGlobal("trSelecaoIdIter");
		var trSelecaoLinhaSel = getVarGlobal("trSelecaoLinhaSel");
		trSelecao = getElementoPorId('corpo:formulario:'+trSelecaoIdIter+':'+linha+':'+trSelecaoLinhaSel);
	}
		
	var evt = plcEvento.getEventoAtual();
	var evtAux = evt;	
	//Marcar linha atual
	if(trSelecao){	
		if(!evtAux || typeof evtAux.type == "undefined"){
			evtAux = new Object()
			evtAux.type = "mouseover";	
		}		
		marcaSelecao(trSelecao, evtAux);
		evtAux = evt;
		var trTagOut =	 getVarGlobal("trSelecao");
		//Desmarcar linha anterior
		if(trTagOut != null){
			if(!evtAux || typeof evtAux.type == "undefined"){
				evtAux = new	 Object()
				evtAux.type = "mouseout";
			}		
		 	marcaSelecao(trTagOut, evtAux);
		 }	
		setVarGlobal("trSelecao", trSelecao)
	 }
}	

/*Desmarca lista de sele??o quando campo de argumento ? focado*/
function desmarcaListaSelecao(){
	if(getVarGlobal("trSelecao") != null){	
		alteraClasse('OBJETO', getVarGlobal("trSelecao"), 'CLASSE', 'campoComErro','INICIAL');
		setVarGlobal("trSelecao", null)
	}
}

/*Simula o click em um link de sele??o*/
function clicarListaSelecao(){
	if(getVarGlobal("trSelecao") != null){
		dispararEvento(getVarGlobal("trSelecao"), "onclick")
	}
}
//***********************FIM NAVEGA??O AUTOMATICA LISTA SELE??O*********************
function executarEnter(){
	if(plcGeral.MENU_ATIVO)
		menuSistemaClicar();
	else	
		clicarListaSelecao();
}
function dispararEvento(elemento, evento){
	evento = evento.toLowerCase();
	if(ExpYes){
		var evObj = document.createEventObject();
		elemento.fireEvent(evento, evObj);
	}
	else{
		var evObj = document.createEvent('MouseEvents');
		evObj.initMouseEvent( evento.replace("on",""), true, true, window, 1, 12, 345, 7, 220, false, false, true, false, 0, null );
		elemento.dispatchEvent(evObj);
	}		
}

var numMenu = null;
var numItem = null;
var numSubItem = null;
function menuSistemaNavega(nMenu, nItem, nSubItem){
	plcLog.debug("menuSistemaNavega");

	//Temporizador para fechar menu
	if(CloseTmr)
		clearTimeout(CloseTmr);
	CloseTmr = setTimeout('menuSistemaFechar()',DissapearDelay * 2)
	numMenu = parseInt(nMenu);
	numItem = typeof nItem != "undefined" ? parseInt(nItem) : null;
	numSubItem = typeof nSubItem != "undefined" ? parseInt(nSubItem) : null;
	var sufixMenu = numItem != null ? numMenu +"_"+ numItem : numMenu; 
	sufixMenu = numSubItem != null ? numMenu +"_"+ numItem +"_"+ numSubItem : sufixMenu; 
	var menuObj = getElementoPorId("Menu"+sufixMenu);
	if(menuObj){
		setNavSetaFocoPlc(true);
		if((""+sufixMenu).indexOf("_") < 0)
			dispararEvento(menuObj, "onclick");
		else
			dispararEvento(menuObj, "onmouseover");
		setVarGlobal("menuObj", menuObj);
	}
}

function menuSistemaFechar(){
	Init(FrstCntnr);
	IniFlg=0;
	AfterCloseAll();
	ShwFlg=0
}

function menuSistemaClicar(){
	var menuObj = getVarGlobal("menuObj");
	dispararEvento(menuObj, "onclick");
}

function menuSistemaNavSetaParaCima(){
	if(plcGeral.MENU_ATIVO){
		if(numSubItem == null)	
			menuSistemaNavega(numMenu,numItem - 1)
		else	
			menuSistemaNavega(numMenu,numItem, numSubItem - 1)
	}
}
function menuSistemaNavSetaParaBaixo(){
	if(plcGeral.MENU_ATIVO){
		if(numSubItem == null)	
			menuSistemaNavega(numMenu,numItem + 1)
		else	
			menuSistemaNavega(numMenu,numItem, numSubItem + 1)
	}
}

function menuSistemaNavSetaParaDireita(){
	if(plcGeral.MENU_ATIVO){
		if(numSubItem == null)	
			menuSistemaNavega(numMenu,numItem, 1)
	}
}
function menuSistemaNavSetaParaEsquerda(){
	if(plcGeral.MENU_ATIVO){
		if(numSubItem != null)	
			menuSistemaNavega(numMenu,numItem)
	}
}

//Fun??o para foco em campo auxiliar
function setNavSetaFocoPlc(focar){

	if(focar && getElementoPorId("navSetaFocoPlc:inibeFoco"))
		getElementoPorId("navSetaFocoPlc:inibeFoco").focus();
	else{
		if(getVarGlobal("campoFocadoPlc"))
			getVarGlobal("campoFocadoPlc").focus();
	}	
}

function CheckNumerico(campo,tammax,teclapres)
{
	var teclaChar = String.fromCharCode(getKeyCode(teclapres));

	// retirando caracteres especiais	
	var caracteresEspeciais = new Array("!","@","#","$","%","^","&","*","(",")",
										"_","-","+","=","{","}","|","[","]","\\",
										"\"",":",";","'","<",">","?","/");
	for (var i = 0;i<caracteresEspeciais.length; i++){
		if (campo.value.indexOf (caracteresEspeciais[i]) > -1){
			campo.value = campo.value.replace(caracteresEspeciais[i], "");
		}	
	}
	
 	if (teclaChar != 0) {
		if (("0123456789").indexOf(teclaChar) > -1)
			return;
		else{
			while (campo.value.indexOf(teclaChar) > -1 || campo.value.indexOf(teclaChar.toLowerCase()) > -1){
				campo.value = campo.value.replace(teclaChar, "");
				campo.value = campo.value.replace(teclaChar.toLowerCase(), "");
			}
		}
	}
	return true;
}

function FormataValor(campo,tammax,teclapres,prec)
{
	//pegar tecla e definir valor de virgula
	var tecla = teclapres.keyCode;
	var virgula = ',';

	//pegar valor do campo atual e remover todas virgulas, pontos, barras etc...
	vr = campo.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );

	//se precisao for 0 definir virgula como inexistente para n?o aparecer
	if (prec==0)
		virgula='';

	//antes de checar tamanho do campo remover 0s da frente do campo
	for (k=0;k<prec;k++)
	{
		if (vr.substr(0,1) == '0')
			vr=vr.substr(1,prec+1);
	}

	//pegar tamanho dos valores j? limpos
	tam = vr.length;
	
	//se tamanho for zero n?o fazer nada
	if (tam==0)
		return

	//se teclas apertadas forem numericas, backspace, del etc.... entrar em if
	if (!tecla || tecla==8 || tecla==46 || ((tecla <= 57 && tecla >= 48) || (tecla <=105 && tecla >= 96)))
	{
		//if para campos de valores fracionais ate 0,999
		if ( tam <= prec + 1)
		{
			campo.value = '0' + virgula;
			for (k=1;k<=prec-tam;k++)
			{	
				campo.value += '0' ;
			}	
			campo.value+=vr;
		}

		//if para campos com valores at? 999,999
		if ( (tam > prec) && (tam <= prec + 3) )
		{
			campo.value = vr.substr(0,tam-prec) + virgula + vr.substr(tam-prec,prec+1); 
		}

		//if para campos com valores at? 999.999,999
		if ( (tam > prec + 3) && (tam <= prec + 6) )
		{
			campo.value = vr.substr(0, tam-(prec+3)) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec, prec+1) ; 
		}

		//if para campos com valores at? 999.999.999,999
		if ( (tam > prec + 6) && (tam <= prec + 9) ){
			campo.value = vr.substr(0, tam-(prec+6) ) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3),3 ) + virgula + vr.substr(tam-prec, prec+1); 
		}

		//if para campos com valores at? 999.999.999.999,999
		if ( (tam > prec + 9) && (tam <= prec + 12) )
		{
			campo.value = vr.substr(0, tam-(prec+9)) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}

		//if para campos com valores at? 999.999.999.999.999,999
		if ( (tam > prec + 12) && (tam <= prec + 15) )
		{
			campo.value = vr.substr(0, tam-(prec+12)) + '.' + vr.substr(tam-(prec+12), 3) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}

//if ( (tam >= 15) && (tam <= 17) ){
// campo.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + virgula + vr.substr( tam - 2, tam ) ;}
//
	}

	return;
}

/**
* Escreve marca de obrigatorio ao final do campo.
*/
function escrevePlcAfter(tags,nameClass,alt,src) {
	var ts = tags.length;
	for (var i = 0; i < ts; ++i) {
		var tag = tags[i];
		if (tag.className.indexOf('AFReadOnly') < 1) {
			if (tag.className.indexOf(nameClass) > -1) {
				var $tag = jQuery(tag);
				if (!$tag.hasClass('plc-obrigatorio')) {
					var $parent = $tag.parent();
					$tag.remove();
					$tag.addClass('plc-obrigatorio');
					var $table = jQuery('<table><tr><td/><td/></tr></table>');
					$table.find('td:eq(0)').append($tag);
					$table.find('td:eq(1)').append('<span class="plc-img-obrigatorio"><img id="'+ tag.childNodes[0].id +'_icone" src="'+src+'"style="float:left; border:0" title="'+alt+'" /></span>');
					$parent.append($table);
				}
			}
        }
    }
}

/*
Funcao responsavel por definir em qual campo o foco devera ser setado em logicas mestre-detalhe (subdetalhe)
*/
function setaFocoAba() {
	var campoDetCorrPlc = jQuery('input:hidden[name="detCorrPlc"]').val();
	if ((tabFolderCamposFoco == "") || (tabFolderCamposFoco == null) || (tabFolderCamposFoco == 0) || (tabFolderCamposFoco == "undefined")) { // somente para logicas mestre-detalhe(subdetalhe) sem tabfolder
		if (campoDetCorrPlc) {
			var detIdSubdet = /(\w+)\[(\d+)\].(\w+)/.exec(campoDetCorrPlc);
			
			if (detIdSubdet) {// Detalhe[X].Subdetalhe
				var campoVazio = jQuery('input:text:enabled[name^="corpo:formulario:'+ detIdSubdet[1] +':'+ detIdSubdet[2] +':'+ detIdSubdet[3] +'"][value=""]:first');
				if (campoVazio.get(0)) {
					campoVazio.focus();
				} else {
					jQuery('input:text:enabled[name^="corpo:formulario:'+ detIdSubdet[1] +':'+ detIdSubdet[2] +':'+ detIdSubdet[3] +'"]:first').focus();	
				}	
				return true;
			} else {// Detalhe				
				var campoVazio = jQuery('input:text:enabled[name^="corpo:formulario:'+ campoDetCorrPlc +'"][value=""]:first');
				if (campoVazio.get(0)) {
					campoVazio.focus();
				} else {
					 jQuery('input:text:enabled[name^="corpo:formulario:'+ campoDetCorrPlc +'"]:first').focus();
				}	
				return true;
			}	
		} else {
			var campoFoco = getCampoFocus();
			if (campoFoco) {
				var campo = campoFoco.split(':0:');
				var campoVazio = jQuery('input:text[name^="'+ campo[0] +'"][name$=":'+ campo[1] +'"][value=""]:first');
				if (campoVazio.get(0)) {
					campoVazio.focus();
					return true;
				}
				return false;	
			}
		}
		return false;
	}	
	else {
		if (campoDetCorrPlc) {
			var detIdSubdet = /(\w+)\[(\d+)\].(\w+)/.exec(campoDetCorrPlc);
			
			if (detIdSubdet) {// Detalhe[X].Subdetalhe
				var campoVazio = jQuery('input:text:enabled[name^="corpo:formulario:'+ detIdSubdet[1] +':'+ detIdSubdet[2] +':'+ detIdSubdet[3] +'"][value=""]:first', ui.panel);
				if (campoVazio.get(0)) {
					campoVazio.focus();
				} else {
					jQuery('input:text:enabled[name^="corpo:formulario:'+ detIdSubdet[1] +':'+ detIdSubdet[2] +':'+ detIdSubdet[3] +'"]:first', ui.panel).focus();
				}	
				return true;
			} else {// Detalhe
				var numAbaAtual	= tabSelecionada.substring(tabSelecionada.indexOf("_")+1);
				var det = tabFolderCamposFoco[numAbaAtual].split(':0:');
				var campoVazio = jQuery('input:text[name^="'+ det[0] +'"][name$=":'+ det[1] +'"][value=""]:first');
				if (campoVazio.get(0)) {
					campoVazio.focus();
				} else {
					jQuery('input:text[name^="'+ det[0] +'"][name$=":'+ det[1] +'"]:first').focus();						
				}
				
				return true;
			}	
		}
		return false;
	}
}

//--------------------------------------------------------------------------------------
/*

	Javascript para recuperacao automatica de registro com chaveNatural

*/
//--------------------------------------------------------------------------------------

var valorChave;
var campoChave;
var contemCampo;

function autoRecuperacaoChaveNatural (campo, nomeCampo, numeroCampos){
	
	if (valorChave == null || valorChave == "")
		valorChave = new Array ();
	
	if (campoChave == null || campoChave == "")
		campoChave = new Array ();

	if (contemCampo == null || contemCampo == "")
		contemCampo = new Array ();

	if (campo.value != ""){

		if (!contemCampoId(nomeCampo)){
		
			campoChave [campoChave.length] = nomeCampo;
			valorChave [valorChave.length] = campo.value;
		
			contemCampo [contemCampo.length] = nomeCampo;
	
		} else {
			trocaValor (nomeCampo, campo.value);
		}

	} else {
		retiraCampo (nomeCampo);
	}

	if (campoChave.length == numeroCampos)	
		recuperandoChaveNatural();
		
}

function atualizaAutoRecuperacaoVinculado(clienteId, inibeFocoClientId){	
	getElementoPorId(clienteId).value = getElementoPorId(inibeFocoClientId).value;
}

function trocaValor (nomeCampo, valor){

	for (var i = 0; i <campoChave.length; i++){
		if (campoChave[i] == nomeCampo)	{
			valorChave[i] = valor;			
			break;	
		}
	}	

}

function retiraCampo (nomeCampo) {
	
	var novoContemCampo = new Array ();
	var novoCampoChave = new Array ();
	var novoValorChave = new Array ();

	for (var i = 0;i<contemCampo.length;i++){
		if (contemCampo[i] != nomeCampo){
			novoContemCampo [novoContemCampo.length] = contemCampo[i];
			novoCampoChave[novoCampoChave.length] = campoChave[i];
			novoValorChave[novoValorChave.length] = valorChave[i];
		}
	}

	contemCampo = novoContemCampo;
	campoChave = novoCampoChave;
	valorChave = novoValorChave;
}

function contemCampoId (idCampo){

	for (var i = 0; i<contemCampo.length; i++){
		if (contemCampo[i] == idCampo)
			return true;	
	}	
	return false;
}

function recuperandoChaveNatural() {
														
	var url = getRootDocument().location.pathname+'?evento=y';
	
	for (var i = 0;i<campoChave.length;i++){									
		url = url + '&' + campoChave[i] + '=' +valorChave[i];
	}
										
	submeteUrl(url);
	
}
//---------------------------------------------------------------------------------------------------------------



/**
 * Comuta de aba automaticamente - jcompany 5.0.
 * @param posicaoAba Ordem da aba de 0 a N
 */
 function comutaAba(posicaoAba,nomeCampo) {
	
	trocaAba(posicaoAba,'def.tab.automatico_'+posicaoAba,'def.tab.automatico_'+posicaoAba);
	setTimeout('window.focus()',40);
	var comandoFoco = "setFocus('"+nomeCampo+"')";
	setTimeout(comandoFoco,50);

 }

 function comutaAbaFacelets(posicaoAba) {
		
	 jQuery('#plc-form-tab').tabs('select', posicaoAba);
	 
 }


/**
 * Seleciona um agregado pela tecla 40 (seta para baixo)
 */ 
function selecionaPorTecla(event,objeto) {
	var idObjeto = objeto.id;
	var botaoSelecao = idObjeto +"Sel";
		
	if (botaoSelecao.indexOf("lookup_") > -1)
		botaoSelecao = botaoSelecao.replace("lookup_", "");
		
	if (getEvento(event).keyCode==40) {
		dispararEvento(getElementoPorId(botaoSelecao),'onclick');
		if (idObjeto.indexOf ("lookup_") < 0){
			idObjeto = "lookup_" + idObjeto;	
			getElementoPorId(idObjeto).focus();
		} else {
			getElementoPorId(idObjeto).focus();
		}
	}
}

/**
 * Seleciona um link inteligente pela tecla 40 (seta para baixo)
 */ 
function selecionaLinkInteligentePorTecla(event,objeto) {
	
	if (getEvento(event).keyCode==40 && objeto) 
		redirect(objeto.href);
	
}

 
 /**
 * Devolve um evento (tecla clicada, por exemplo), cross-browser
 */
function getEvento(evt) {
	if (ExpYes) {
		return window.event;
	} else {
		return evt;
	}
}

/**
*  Dispara botao de visualizar documento, se estiver visivel
*/
function visualizaFormulario() {
		if (getRootDocument().getElementById('corpo:formulario:botaoAcaoVisualizaDocumento')!=null) {
			dispararEvento(getRootDocument().getElementById('corpo:formulario:botaoAcaoVisualizaDocumento'),'onclick');
		}
}

/**
*	Dispara pesquisa para RSS
*/
function pesquisaParaRSS(){
	var url = getRootDocument().location.href;
	
	// Verifica evento
	if (url.indexOf("evento=y")>-1){
		// Não faz nada
	} else {
		url = url + "?evento=y";	
	}
	
	// Verifica formato
	if (url.indexOf("formato=RSS")>-1){
		// Não faz nada
	} else {
		url = url + "&formato=RSS";
	}
	
	submeteUrl(url);
}

/**
* Função que limpara os Títulos após o novo utilizando AJAX
* @autor Geraldo Matos, Pedro Neves
*/
function verificarAlteracaoTitulos() {

	var tituloJanela = document.getElementById("inputTituloPagina");
	if (tituloJanela){
		document.title = tituloJanela.value;
		var arrayDivs = document.getElementsByTagName("div");
		for (j = 0; j < arrayDivs.length; j++) {
			if (arrayDivs[j].className == "tituloPagina") {
				arrayDivs[j].innerHTML = tituloJanela.value;
				break;
			}
		}
	}
}



/**
* Limpa vinculado e suas propriedades.
*/
function limparVinculado(botao, propriedades) {
	var campos = propriedades.split(",");
	for (i = 0; i < campos.length; i++) {
		var campo = document.getElementById(campos[i]);
		campo.value = "";
		if (campos[i].indexOf("lookup_") != -1) {
			var id = campos[i].replace("lookup_", "") + "Limpar";
			ocultaLimparVinculado(id, true);
		}
	}
	ocultaLimparVinculado(botao.id, true);
}

/**
* Mostra ou Esconde botão Limpar Vinculado.
*/
function mostraOcultaLimparVinculado(id) {
	var botao = document.getElementById(id);
	if (botao != null && botao != undefined) {
		if (botao.style.display == '') {
			botao.style.display = 'none';
		}else {
			botao.style.display = '';
		}
		alterouVinculado = false;
		idVinculadoAlterado = "";
	}
}

function limparReferenciaVinculado() {
	alterouVinculado = false;
	idVinculadoAlterado = "";
}

function ocultaLimparVinculado(id, limparReferencia) {
	var botao = document.getElementById(id);
	if (botao != null && botao != undefined) {
		botao.style.display = 'none';
		if (limparReferencia) {
			limparReferenciaVinculado();
		}
	}
}

function mostraLimparVinculado(id) {
	var botao = document.getElementById(id);
	if (botao != null && botao != undefined) {
		botao.style.display = '';
		limparReferenciaVinculado();
	}
}

function autoRecuperacaoVinculado(vinculado, propRecupera){
	if (ajaxUsa != "false") {
		TrPage._autoSubmit('corpo:formulario', vinculado, window.event, 1, {partialVinculado: vinculado});
		setTimeout (function () { jQuery(getRootDocument().getElementById("lookup_" + vinculado)).focus(); }, 200);
		setTimeout (function () { jQuery(getRootDocument().getElementById("lookup_" + vinculado)).select(); }, 200);
	} else {
		submitForm('corpo:formulario', 1, {source:vinculado});
	}
}

/*******************************************************************/
/***                                                             ***/
/***   Tokenizer.js - JavaScript String Tokenizer Function       ***/
/***                                                             ***/
/***   Version   : 0.2                                           ***/
/***   Date      : 01.05.2005                                    ***/
/***   Copyright : 2005 Adrian Zentner                           ***/
/***   Website   : http://www.adrian.zentner.name/               ***/
/***                                                             ***/
/***   This library is free software. It can be freely used as   ***/
/***   long as this this copyright notice is not removed.        ***/
/***                                                             ***/
/*******************************************************************/

String.prototype.tokenize = tokenize;

function tokenize()
  {
     var input             = "";
     var separator         = " ";
     var trim              = "";
     var ignoreEmptyTokens = true;

     try {
       String(this.toLowerCase());
     }
     catch(e) {
       window.alert("Tokenizer Usage: string myTokens[] = myString.tokenize(string separator, string trim, boolean ignoreEmptyTokens);");
       return;
     }

     if(typeof(this) != "undefined")
       {
          input = String(this);
       }

     if(typeof(tokenize.arguments[0]) != "undefined")
       {
          separator = String(tokenize.arguments[0]);
       }

     if(typeof(tokenize.arguments[1]) != "undefined")
       {
          trim = String(tokenize.arguments[1]);
       }

     if(typeof(tokenize.arguments[2]) != "undefined")
       {
          if(!tokenize.arguments[2])
            ignoreEmptyTokens = false;
       }

     var array = input.split(separator);

     if(trim)
       for(var i=0; i<array.length; i++)
         {
           while(array[i].slice(0, trim.length) == trim)
             array[i] = array[i].slice(trim.length);
           while(array[i].slice(array[i].length-trim.length) == trim)
             array[i] = array[i].slice(0, array[i].length-trim.length);
         }

     var token = new Array();
     if(ignoreEmptyTokens)
       {
          for(var i=0; i<array.length; i++)
            if(array[i] != "")
              token.push(array[i]);
       }
     else
       {
          token = array;
       }

     return token;
}

	function redirecionaPesquisa(campos) {
		
		var campo = campos.tokenize(",", " ", true);
		
		var url = "?";
		//adicionando parâmetros da url, inclusive evento=y
		var parametros = window.location.search.substring(1);
		if (parametros != undefined && parametros != "")
			parametros = "&" + parametros;
			
		var separador = "&seppesqrestful=s";
		if (parametros!= undefined){
			if (parametros.indexOf(separador) != -1){
				var indiceSeparador = parametros.indexOf(separador);
				parametros = parametros.substring(0, indiceSeparador);
			}
			
			if(parametros.indexOf('evento=y') == -1)
				url = url +"evento=y";
				
			url = url + parametros + separador;
		}
		for(var i=0; i<campo.length; i++) {
			elemento = document.getElementById("corpo:formulario:"+campo[i]);

			if (elemento==null)
			{
				continue;
			} else	if (elemento.type=='select-one')	{
				
				var indice = elemento.selectedIndex;
				if (indice>0)
				{
					//url=url+"&"+campo[i]+"="+escape(elemento.options[indice].getAttribute('chPlc'));
					valorIndice = parseInt(elemento.options[indice].value)+1;
					url=url+"&"+campo[i]+"="+escape(valorIndice);
				}
			} else if (elemento.tagName=='SPAN')
			{
				
				var indice = 0;
				
				while(document.getElementById("corpo:formulario:"+campo[i]+":_"+indice) != undefined ) {
					if (document.getElementById("corpo:formulario:"+campo[i]+":_"+indice).checked)
					{
						url=url+"&"+campo[i]+"="+escape(document.getElementById("corpo:formulario:"+campo[i]+":_"+indice).getAttribute('chPlc'));	
					} 
					indice = indice +1;
				}

			} else if (elemento.type=='checkbox') {

				if (elemento.checked)
				{
					url=url+"&"+campo[i]+"=true";	
				} 

			} else if (elemento!= undefined &&  
					elemento.value!='' && 
					elemento.value!=undefined) {

				url=url+"&"+campo[i]+"="+escape(elemento.value);
			}
		}

		var urlAtual = getRootDocument().location.href;
		if (urlAtual.indexOf("modoJanelaPlc")>-1){
			// Padrão para pegar valor de modoJanelaPlc
			// modoJanelaPlc=([a-zA-Z]*)
			var padrao = new RegExp ("modoJanelaPlc=([a-zA-Z]*)")
			var modoJanela = padrao.exec(urlAtual);
			url=url+"&modoJanelaPlc="+modoJanela[1];
		}

		document.location=url;
		
		return false;		
    }
    
function mascararCep(campo, evt){
	var elemento = document.getElementsByName("cep"); 
	if(NavYes)
	var tecla = evt.which;
	else
	var tecla = evt.keyCode;
    var vr = campo.value;
    tam = vr.length;

    if ((((tecla) > 95) && ((tecla) < 106)) ||(((tecla) > 47) && ((tecla) < 58))) {
    
    	if(elemento != null)
    	{

            if (tam == 5){
                  campo.value = vr.substr(0, 5 ) + '-' + vr.substr(5 );
                  }
            }
    }
}

function validarCep(campo, evt){
	var elemento = document.getElementsByName("cep"); 
    var vr = campo.value;
    tam = vr.length;
    
    if(elemento != null){
    	if(tam !=9){
    		alert("CEP inv\u00E1lido. \nFORMATO CORRETO: 99999-999");
    		campo.value = "";
    		}
    }
}

function mascararCnpjCpf(campo, evt, tipoDePessoa){
	if(NavYes)
	var tecla = evt.which;
	else
	var tecla = evt.keyCode;
	
    var vr = campo.value;
    tam = vr.length;
	
    if ((((tecla) > 95) && ((tecla) < 106)) ||(((tecla) > 47) && ((tecla) < 58))) {
    
    	if(tipoDePessoa == 0 || tipoDePessoa == 2){
            if (tam == 3){
                  campo.value = vr.substr(0, 3 ) + '.' + vr.substr(3 );
                  }
            if (tam == 7){
 				  campo.value = vr.substr(0, 7 ) + '.' + vr.substr(7 );
                  }
            if (tam == 11){
 				  campo.value = vr.substr(0, 11 ) + '-' + vr.substr(11 );
                 }
            if (tam == 14 || tam == 15){
 				  campo.value = vr.substr(0, 14 );
 				  //return validacpf(campo.value);
                 }
        }
    	if(tipoDePessoa == 1){
            if (tam == 2){
                  campo.value = vr.substr(0, 2 ) + '.' + vr.substr(2 );
                  }
            if (tam == 6){
 				  campo.value = vr.substr(0, 6 ) + '.' + vr.substr(6 );
                  }
            if (tam == 10){
 				  campo.value = vr.substr(0, 10 ) + '/' + vr.substr(10 );
                 }
            if (tam == 15){
 				  campo.value = vr.substr(0, 15 ) + '-' + vr.substr(15 );
                 }
            if (tam == 18){
            	//validacnpj(campo.value);
            	}
        }
        
    }
}    
function configuraEventos(){
	setUpOnEventoTag ('INPUT', 'onchange', 'setAlertaAlteracao');
	setUpOnEventoTag ('TEXTAREA', 'onchange', 'setAlertaAlteracao');
	setUpOnEventoTag ('SELECT', 'onchange', 'setAlertaAlteracao');
	setUpOnEventoTag ('A', 'onclick', 'enviaAlertaAlteracao');
	setUpOnEventoTag ('INPUT#button', 'onclick', 'enviaAlertaAlteracao');
	setUpOnEventoTag ('button', 'onclick', 'enviaAlertaAlteracao');
	//setUpOnEventoElemento ('botao_menu', 'onclick', 'enviaAlertaAlteracao');
}
jQuery(function(){
	if (jQuery('#corpo\\:formulario\\:botaoAcaoGravar').length > 0){
		removeMascaraCampo('#corpo\\:formulario\\:botaoAcaoGravar','#corpo\\:formulario');
	}
});

function removeMascaraCampo (botaoGravar, prefixoElementos){
	jQuery(botaoGravar).mousedown(function(){
		var elements = jQuery(prefixoElementos).attr('elements');
		jQuery.each(elements, function(){
			   if (typeof jQuery(this).data('jcompany-submit-unmask') == 'function') {
				   jQuery(this).val(jQuery(this).data('jcompany-submit-unmask').call(this));
			   }
	   
		});
	});
}

//-- BPM --
function registraTransicaoBPM (valor){
	if (tecnologia=="Struts"){
		insereValorCampo('proximaTransicao', valor);
	}else if (tecnologia == "JSF"){
		insereValorCampo('corpo:formulario:proximaTransicao', valor);
	}
}

function criaMenuBPM(menuID) {
	jQuery('#' + menuID).children('a').each(function(){
		var button = jQuery(this);
		var content = button.next();
		if (!content.is('ul'))
			return;
		content.remove();
		button.menu({
			content: '<ul class="plc-menu-bpm">' + content.html() + '</ul>'
			,flyOut: true, width: 350 
		});
	});
}	

function verificaImagemProcesso(bpm_console, idProcessInstance, idProcessDefinition, isJsp){
	var cookie = jQuery.cookie('jBpm-cookie');
	if (cookie && idProcessInstance){
		exibeImagemProcesso(true, bpm_console, idProcessInstance, idProcessDefinition, isJsp);
	}
}

function atualizaDiv (bpm_console, idProcessInstance, idProcessDefinition, isJsp){
	if (jQuery('#divImagemProcesso').children("iframe").length == 0){
		jQuery.cookie('jBpm-cookie', 'exibeImagem');
		exibeImagemProcesso(true, bpm_console, idProcessInstance, idProcessDefinition, isJsp);
	} else {
		jQuery.cookie('jBpm-cookie', null);
		exibeImagemProcesso(false);
	}
}

function exibeImagemProcesso(exibe, bpm_console, idProcessInstance, idProcessDefinition, isJsp){
	if (exibe){
		if (isJsp){
			jQuery('#divImagemProcesso')
				.html("<iframe src='"+plcGeral.contextPath+"/plc/jsps/geralImagemJbpm.jsp?urlConsole="+bpm_console+"&processInstanceId="+idProcessInstance+"&processDefinitionId="+idProcessDefinition+"' width='100%' height='600' frameborder='0'></iframe>")
				.show();
		} else {
			jQuery('#divImagemProcesso')
			.html("<iframe src='"+plcGeral.contextPath+"/f/fcls/geralImagemJbpm.xhtml?urlConsole="+bpm_console+"&processInstanceId="+idProcessInstance+"&processDefinitionId="+idProcessDefinition+"' width='100%' height='600' frameborder='0'></iframe>")
			.show();
		}
	}else{
		jQuery('#divImagemProcesso')
			.hide()
			.children("iframe")
			.remove();
	}
}

//---------

function retraiExpandeSubdetalhe (componente, subdetalhe){
	if (jQuery(componente).hasClass('iMinimizar')){
		jQuery(componente).removeClass('iMinimizar').addClass('iMaximizar');
		jQuery('#corpo\\:formulario\\:novoComponente\\:' + subdetalhe ).hide();
		jQuery('#corpo\\:formulario\\:fieldset\\:' + subdetalhe + ' > *').not('legend').hide()
	} else {
		jQuery(componente).removeClass('iMaximizar').addClass('iMinimizar');
		jQuery('#corpo\\:formulario\\:novoComponente\\:' + subdetalhe ).show(); 
		jQuery('#corpo\\:formulario\\:fieldset\\:' + subdetalhe + ' > *').not('legend').show();
	}
}

function getURLParams(url){
	var i, h = {}, u = window.location.href, q = u;
	// Cache da ultima URL processada.
	if (getURLParams.u === u) return getURLParams.h;
	// url?querystring
	if ((i = u.indexOf('?')) != -1) q = u.substring(i + 1);
	// desconsidera &amp; no split
	var s = q.split(/&(?!amp;)/);
	// Gera um hash com os parametros.
	for(i = 0; i < s.length; i++){
		var p = s[i], j = p.indexOf('=');
		if (j != -1) {
			h[p.substring(0, j)] = unescape(p.substring(j + 1).replace(/&amp;/gi,'&'));
		} else {
			h[p] = '';
		}
	}
	getURLParams.u = s;
	getURLParams.h = h;
	return h;
}
// Cache da ultima URL.
getURLParams.u = null; getURLParams.p = null;

jQuery.plc = {
	/*
	 * Transforma um Form em um Objeto.
	 * @param form Seletor jQuery, ou DOM do formulário.
	 * @param preffix Prefixo presente nos elementos do formulário, que deve ser descartado.
	 * 
	 * O Prefixo é comum em JSF. 
	 */
	componentesDef:null
	,componenteFoco:null
	,formAtual:null
	,form2json: function(form, preffix){
		var $form = (form && jQuery(form)) || null;
		if ($form) {
			if ($form.is('form')) {
				var elements = $form.attr('elements');
				if (elements && elements.length) {
					var data = {};
					jQuery.each(elements, function(){
						var $it = jQuery(this);
						var name = $it.attr('name');
						if (preffix && name && name.indexOf(preffix) == 0) {
							name = name.substring(preffix.length);
						}
						if (name) {
							var value = $it.val();
							var type = $it.attr('type');
							if ((type == 'checkbox' || type == 'radio') && $it.attr('checked') != true) {
								return;
							}
							if (value) {
								if (typeof data[name] != 'undefined') {
									if (data[name].constructor!= Array) {
										data[name]=[data[name], value];
									} else {
										data[name].push(value);
									}
								} else {
									data[name] = value;
								}
							}
						}
					});
					return data;
				}
			}
		}
		return null;
	}
	/*
	 * Faz requisição de componentes via ajax 
	 * @param url Faz requisicão de uma url especifica. Caso vazio, utiliza url do form
	 * @param Evento a ser executado
	 * @param componentes Um array com os componentes que serão recuperados. Ex.: ['plc.jsps.geralMensagemPlc', 'def.corpo.ancestral.tab'] 
	 * @param form Um seletor para o form
	 */
	,partialAjaxSubmit: function (url, evento, componentes, form) {
						
		if (form && (url == "" || typeof url == "undefined" || url == null)){
	    	url = jQuery(form).attr('action');
		}
	    
	    if (url == ""){
	    	url = location.href;
		}
		
	    if (evento)
	    	url = url + '?evento=' + evento;
		
		for ( var i = 0; i < componentes.length; i++) {
			url = url + "&plcDef=" + componentes[i];
		} 
	
		jQuery.plc.componentesDef = componentes;
		jQuery.plc.formAtual = form;
		
	    jQuery.post(url, jQuery(form).serialize(), function (t){
		   
	    	for ( var i = 0; i < jQuery.plc.componentesDef.length; i++) {

				var	componente = jQuery.plc.componentesDef[i];
	    		if (jQuery("span[class=" + componente + "]").length > 0){
	    			if (jQuery(t).filter("span[class=" + componente + "]").length > 0){
	    				var atualizar = jQuery(t).filter("span[class=" + componente + "]").html();
	    				jQuery("span[class=" + componente + "]").html(atualizar);
		    		}
					if (jQuery(t).find("span[class=" + componente + "]").length > 0){
	    				var atualizar = jQuery(t).find("span[class=" + componente + "]").html();
	    				jQuery("span[class=" + componente + "]").html(atualizar);
		    		}
	    		}
			}
	    	mantemAbaSelecionada ();
	    });
		
	}
	/*
	 * Cria uma nova Janela Dialog do jQuery UI.
	 * Recebe um objeto de configuração, que deve ter como parâmetro, a url e o tamanho da janela.
	 * {
	 * 	url: '...'
	 *  ,width: ...
	 *  ,height: ...
	 * }
	 * @param Objeto de configuração com URL.
	 * @return Retorna o Objeto jQuery Dialog.
	 */
	,janelaModal: function(c, dialogOpener){
		
		// Possibilita recursividade!
		if (window.parent && window.parent != window) {
			var funcaoModal = window.parent.jQuery && window.parent.jQuery.plc && window.parent.jQuery.plc.janelaModal;
			if (funcaoModal)
				return window.parent.jQuery.plc.janelaModal(c, (dialogOpener || window));
		}
		
		var defaultWidth = 720, defaultHeight = 480;
		
		// Cria o jQuery UI Dialog com iFrame!
		var dialog = jQuery(
			'<div '+
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
			//,hide: 'slide'
			,open: function(){
				if (c.onopen){
					c.onopen(c, dialogOpener);
				}
				dialog.children('iframe').show();
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
	
	,applyToString: function(o){
		function toString(){
			return this.lookup || '';
		}
		if (o && o.hasOwnProperty('lookup') && o.toString !== toString) {
			o.toString = toString;
			for (i in o) {
				if (o[i] && o[i].hasOwnProperty('lookup')) {
					jQuery.plc.applyToString(o[i]);
				}
			}
		}	
	}
	
	/*
	 * Transforma um Form em um Objeto.
	 * @param form Seletor jQuery, ou DOM do formulário.
	 * @param preffix Prefixo presente nos elementos do formulário, que deve ser descartado.
	 * 
	 * O Prefixo é comum em JSF. 
	 */
	,criaSelecaoJqGrid: function(idGrid, idNavegacao, configuracaoGrid){
		
		var modoJanelaPlc = configuracaoGrid.modoJanelaPlc || '';
		var evento = configuracaoGrid.evento || '';
		var nomeLogica = configuracaoGrid.nomeLogica || '';
		var propAgregada = configuracaoGrid.propAgregada || nomeLogica;
		var urlPesquisa = (configuracaoGrid.urlPesquisa || (plcGeral.contextPath + '/soa/plc/selecao/pesquisa/' + nomeLogica));
		var urlEdicao = (configuracaoGrid.urlEdicao || (nomeLogica + 'man'));
		var fwPlc = getURLParams()['fwPlc'];
		
		idGrid = document.getElementById(idGrid);
		idNavegacao = document.getElementById(idNavegacao);
		
		var seletorBotaoPesquisar = (modoJanelaPlc == 'modal' ? '#modal\\:formulario\\:botaoAcaoPesquisar' : '#corpo\\:formulario\\:botaoAcaoPesquisar');
		var seletorFormulario = (modoJanelaPlc == 'modal' ? '#modal\\:formulario' : '#corpo\\:formulario');
		var prefixoFormulario = (modoJanelaPlc == 'modal' ? 'modal:formulario:' : 'corpo:formulario:');
		
		var dadosPesquisa = {};
		var aoSelecionarLinha = null;
		
		var getDadosPesquisa = function(id) {
			var data = null;
			if (dadosPesquisa) {
				jQuery.each(dadosPesquisa, function(){
					if (this.id == id) {
						data = this;
						return false;
					}
				});
			}
			return data;
		};
		
		var pesquisaAjax = function(postData){
			
			jQuery.plc.partialLoading();
			
			var params = [];
			
			jQuery.each(postData, function(k, v){
				params.push(escape(k)+'='+escape(v));
			});
			
			jQuery.ajax({
				url: urlPesquisa
				,type: "GET"
				,data: params.join('&')
				,dataType: "json"
				,success: function(data){
					
					jQuery.plc.partialLoading(false);
					
					if (!data) {
						jQuery.plc.exibeMensagem ("Erro ao processar pesquisa.");
						return;
					} else if (typeof data == "string") {
						jQuery.plc.exibeMensagem(data);
						return;
					} else {
						jQuery.plc.exibeMensagem(false);
					}
					
					dadosPesquisa = (data && data.rows) || [];
					// Em caso de chave natural, o objeto não tem ID, e o Grid precisa do ID para identificar a linha
					jQuery.each(dadosPesquisa, function(i, row){
						jQuery.plc.applyToString(row);
						if (row.id == null && row.idNatural) {
							row.id = i;
						}
					});
					var jqGrid = jQuery(idGrid).get(0);
					if (jqGrid && data) {
						jqGrid.addJSONData(data);
						jQuery.plc.exibeMensagem (null);
						if (data.records == 0) {
							jQuery.plc.exibeMensagem ("Nenhum registro que atende aos crit&eacute;rios informados foi encontrado!");
						} 
					}
					jQuery(idGrid).find('tr.jqgrow:odd').addClass('linhapar');
					jQuery(idGrid).find('tr.jqgrow:even').addClass('linhaimpar');
					
				}
				,error: function(xhr){
					jQuery.plc.partialLoading(false);
					var data = jQuery.parseJSON(xhr.responseText);
					if (!data) {
						jQuery.plc.exibeMensagem ("Erro ao processar pesquisa.");
						return;
					} else if (typeof data == "string") {
						jQuery.plc.exibeMensagem(data);
						return;
					} else {
						jQuery.plc.exibeMensagem(false);
					}					
					jQuery.plc.exibeMensagem(x||"Erro ao processar pesquisa.");
				}
			});
		};
		
		var pesquisar = function(){ 
			
			var formData = jQuery.plc.form2json(seletorFormulario, prefixoFormulario);
			
			jQuery(idGrid).setPostData(formData);
			
			jQuery(idGrid).setGridParam({
				page: 1
				,datatype: pesquisaAjax
			});
			jQuery(idGrid).trigger("reloadGrid");
		};
		
		if (configuracaoGrid.aoSelecionar){
			aoSelecionarLinha = function(id){
				configuracaoGrid.aoSelecionar(id, getDadosPesquisa(id));
			};
		} else {
			aoSelecionarLinha = function(id){
				
				var data = getDadosPesquisa(id);
				
				if (!data) {
					return;
				}
				
				if (modoJanelaPlc == 'modal' || modoJanelaPlc == 'popup') {
					var values = [];
					for (var n in data) {
						values.push(n + '#' + data[n]);
					}
					values.push(propAgregada + '#' + data['idAux']);
					values.push(propAgregada + 'Lookup#' + data['lookup']);
					
					if (typeof data['idNatural']!= "undefined") {
						for (var n in data['idNatural']) {
							values.push(n + '#' + data['idNatural'][n]);
						}
					}
					
					values = values.join(',');
					
					devolveSelecao(values);
					
				} else {
					var urlRedirect = urlEdicao;
					if (urlRedirect.indexOf('?') == -1) {
						urlRedirect += '?';
					}
					urlRedirect += (data.linkEdicaoPlc || ('chPlc=' + id));
					if (fwPlc) {
						urlRedirect += '&fwPlc=' + fwPlc;
					}
					if (plcGeral.parcialAjaxUsa) {
						jQuery.plc.parcialAjaxCorpo(urlRedirect);
					} else {
						redirect(urlRedirect);
					}
				}
			};
		}
		
		// Pesquisa não intrusiva!
		jQuery(seletorBotaoPesquisar).removeAttr("onclick").click(pesquisar);
		
		var larg; 
		if (NavYes) { 
			larg= 0.965; 
		} else 	{
			larg = 0.965;
		}
		
		var defaultJqGrid = {        
                
                jsonReader: {repeatitems: false}
                
                ,page: 1
                ,rowNum: 10
                //,imgpath: plcGeral.contextPath + '/plc/javascript/jquery/jqGrid/themes/basic/images'
                ,pager: jQuery(idNavegacao)
                ,width: jQuery(idGrid).parent().width() * larg
                ,scrollrows : true
                ,viewrecords: true
                ,sortorder: "asc"
                ,multiselect: false
                ,onSelectRow: aoSelecionarLinha
        }
		
		if (evento == 'y' || evento == 'Y' || evento == 'z' || evento == 'Z') {
			defaultJqGrid.postData = jQuery.plc.form2json(seletorFormulario, prefixoFormulario);
			defaultJqGrid.datatype = pesquisaAjax;
		}
		
		 var jqGrid = jQuery.extend({}, defaultJqGrid, configuracaoGrid.jqGrid);
		 if (jqGrid.rowNum && !jqGrid.hasOwnProperty('rowList')) {
                jqGrid.rowList = [jqGrid.rowNum, jqGrid.rowNum*2, jqGrid.rowNum*3]
        }
         
         jQuery(idGrid)
         .jqGrid(jqGrid)
         .navGrid(idNavegacao, {
                 search: false
                 ,add: false
                 ,edit: false
                 ,del: false
         });
		
		jQuery(window).bind('resize', function() { jQuery(idGrid).setGridWidth(jQuery(window).width() * larg); });
		
	}
	
	
	,dadosTreeView: {
	}
	
	,criaTreeView: function(options){
		
		if (!options) {
			options = {};
		}
		
		var id = options.id || 'plc-treeview';
		var element = document.getElementById(id);
		
		if (!element) {
			element = jQuery('<table id="plc-treeview" cellpadding="0" cellspacing="0"></table>').prependTo('#div-treeview').get(0);
		}
		
		var logica = options.logica || null;
		
		var dados = {};
		
		if (logica) {
			if (!jQuery.plc.dadosTreeView.hasOwnProperty(logica)) {
				jQuery.plc.dadosTreeView[logica] = {};
			}
			dados = jQuery.plc.dadosTreeView[logica];
		}
		
		var urlEdicao = options.urlEdicao || null;
		
		var aoSelecionar = null;
		
		var recuperaObjetos = function(parent, level, data){
			
			var tree = jQuery(document.getElementById(id));
			
			if (tree.length > 0 && data && data.rows) {
				// Injeta nos dados omplementares para arvore.
				if (data.rows.length > 0) {
					jQuery.each(data.rows, function(){
						// Atualiza os dados carregados!
						jQuery.plc.applyToString(this);
						
						dados[this.id] = jQuery.extend(this, {
							level: level + 1
							,parent: parent
							,isLeaf: false
							,expanded: false
						});
					});
					
					if (dados[parent]) {
						dados[parent].expanded = true;
					}
					
					tree[0].addJSONData(data);
				}
			}
		}
		
		if (options.aoSelecionar){
			aoSelecionar = function(id){
				options.aoSelecionar(id, dados[id]);
			};
		} else {
			aoSelecionar = function(id){
				
				var objeto = dados[id];
				
				if (!objeto) {
					return;
				}
				
				var urlRedirect = urlEdicao;
				if (urlRedirect.indexOf('?') == -1) {
					urlRedirect += '?';
				}
				urlRedirect += (objeto.linkEdicaoPlc || ('chPlc=' + id));

				if (plcGeral.parcialAjaxUsa) {
					jQuery.plc.parcialAjaxCorpo(urlRedirect);
				} else {
					redirect(urlRedirect);
				}
			};
		}
		
		var defaultOptions = {
				
				colNames: ['', (options.titulo || '')]
				,colModel: [{
					name: 'id',
					index: 'id',
					width: 1,
					hidden: true,
					sortable: false
				},{
					name: 'lookup',
					index: 'lookup',
					width: 300,
					hidden: false,
					sortable: false
				}]
				
				,treeGrid: true
				,treeGridModel: 'adjacency'
				,ExpandColumn: 'lookup'
				,ExpandColClick: true
				
				,jsonReader: {repeatitems: false}
				
				/*,treeIcons:{
					plus:'ui-icon-folder-collapsed'
					,minus:'ui-icon-folder-open'
					,leaf:'ui-icon-tag'
				}*/
				
				,multiselect: false
				,onSelectRow: aoSelecionar
				
				,datatype: function(postData){
					
					var level = (postData.n_level >= 0 ? postData.n_level : -1);
					
					var parent = (postData.nodeid || null);
					
					jQuery.ajax({
						type: 'GET'
						,url: plcGeral.contextPath + '/soa/plc/selecao/treeview/' + logica
						,data: postData
						,dataType: 'json'
						,success: function(data){
							recuperaObjetos(parent, level, data);
						}
					});
				}
			};
		
		jQuery(function(){
			if (element) {
				jQuery(element).jqGrid(jQuery.extend(defaultOptions, (options.jqGrid || {})));
			}
		});
	}
	
	,trocaConteudoParcial: function(selector, html, beginComment, endComment){		
		var begin = html.indexOf(beginComment);
		if (begin != -1) {
			var end = html.indexOf(endComment, begin);
			if (end != -1) {
				var content = jQuery(selector);
				if (content.length > 0) {					
					content.html(html.substring(begin, end + endComment.length));
				}
			}
		}
	}
	
	,partialLoading: function(active) {
		if (active === false) {
			jQuery('#partial-loading').remove();
		} else {
			var el = jQuery('#partial-loading');
			if (el.length == 0) {
				var el = jQuery('<div id="partial-loading" class="partial-loading"/>');
				if (jQuery('#barraAcoes').length != 0) {
					jQuery('#barraAcoes').append(el);
				} else if (jQuery('#plc-corpo-acao').length != 0) {
					jQuery('#plc-corpo-acao').append(el);
				} else {
					jQuery('body').append(el);
				}
			} else {
				el.show();
			}
		}
	} 
	
	,parcialAjaxCorpo: function(url){
		// Trata URLs
		if (url.indexOf('http') != 0 && url.indexOf(plcGeral.contextPath) != 0) {
			url = plcGeral.contextPath + url;
		}
		
		jQuery.plc.partialLoading();
		
		// Faz a requisição GET via Ajax para atualizar o Corpo da Página.
		jQuery.ajax({
			url: url
			,type: "GET"
			,cache: false
			,success: function(html){
				if (html.indexOf('action="j_security_check"') != -1) {
					location.href = plcGeral.contextPath;
				} else {
					jQuery.plc.trocaConteudoParcial('#plc-topo', html, '<!-- #inicio-ajax-plc-topo# -->', '<!-- #fim-ajax-plc-topo# -->');
					jQuery.plc.trocaConteudoParcial('#plc-corpo', html, '<!-- #inicio-ajax-plc-corpo# -->', '<!-- #fim-ajax-plc-corpo# -->');
				}
			}
			,error: function(xhr){
				jQuery.plc.trocaConteudoParcial('html', xhr.responseText, '<html>', '</html>');
			}
			,complete: function(){
				jQuery.plc.partialLoading(false);
			}
		});
	}
	/*
	 * Exibe mensagem no cabeçalho da aplicação. Se a mensagem que for enviada for nula, o cabeçalho será limpado
	 */
	,exibeMensagem: function (mensagem) {
		
		if (!mensagem) {
			jQuery('#corpo\\:formulario\\:ajaxMensagem').empty();
			return;
		}
		

		
		var path = document.location.pathname;
		path = path.substring (1, path.length);
		path = path.substring (0, path.indexOf("/"));
		
		var mensagemInicio = null;
		var mensagemFim = null;
		
		//tecnologia == "JSF" ou tecnologia == "Facelets" 
		if (tecnologia == "JSF") {
			
			mensagemInicio = '<table cellspacing="0" cellpadding="0" border="0" width="100%" id="MENSAGEM_TABELA_msgVermelho">'+ 
										'<tbody><tr>'+ 
											'<td style="width: 40px; vertical-align: top;">'+ 
												'<img src="/'+ path + '/plc/midia/msgVermelho/ico.gif" alt="Mensagem de Erro"/>'+ 
											'</td>'+ 
											'<td>'+ 
												'<table cellspacing="0" cellpadding="0" border="0" width="100%">'+ 
													'<tbody><tr>'+ 
														'<td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/supesq.gif) repeat scroll 0% 0%; width: 7px; height: 3px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td><td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/sup.gif) repeat scroll 0% 0%; height: 7px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td><td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/supdir.gif) repeat scroll 0% 0%; width: 7px; height: 3px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td></tr>'+ 
													'<tr>'+ 
														'<td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/esq.gif) repeat scroll 0% 0%; width: 7px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td><td class="msgVermelho">'+ 
														'<img src="/'+ path + '/plc/midia/msgVermelho/lin.gif" alt="Erro" style="margin: 4px; vertical-align: middle; height: 11px; width: 11px;"/>';
		
			mensagemFim = 							'<br/>'+ 
														'</td>'+ 
														'<td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/dir.gif) repeat scroll 0% 0%; width: 7px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td></tr>'+ 
													'<tr>'+ 
														'<td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/infesq.gif) repeat scroll 0% 0%; width: 7px; height: 3px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td><td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/inf.gif) repeat scroll 0% 0%; height: 7px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
														'</td><td style="background: transparent url(/'+ path + '/plc/midia/msgVermelho/infdir.gif) repeat scroll 0% 0%; width: 7px; height: 3px; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;">'+ 
													'</td></tr>			'+ 
													'</tbody></table>'+ 
													'<!-- FIM TABELA DE MENSAGENS -->'+ 
											'</td>'+ 
										'</tr>'+ 
										'</tbody>'+
								'</table>';
		
		} else {
		
			mensagemInicio = '<span class="af_panelGroupLayout" id="corpo:formulario:ajaxMensagem">' +
								'<div>' +
									'<div class="ui-corner-all ui-state-error plc-msg-erro  ">' +
										'<span class="ico iAlertaVermelho"></span>' +
										'<p>';
		
			mensagemFim = 				'</p>' +
									'</div>' +
								'</div>'+
							 '</span>';
		

		}
							
		var mensagemTexto = mensagemInicio + mensagem + mensagemFim;

		jQuery('#corpo\\:formulario\\:ajaxMensagem').html(mensagemTexto);
	}

	,acao: function(nomeAcao, argAcao){
		if (nomeAcao) jQuery(document).trigger(nomeAcao, (argAcao || {}));
	}
}

/* FIX para AJAX no WebSphere! */
function encodeCaracteresUTF(string) {
	string = string.replace(/\r\n/g,"\n");
	var utftext = "";
	for (var n = 0; n < string.length; n++) {
		var c = string.charCodeAt(n);
		if (c < 128) {
			utftext += String.fromCharCode(c);
		} else if((c > 127) && (c < 2048)) {
			utftext += String.fromCharCode((c >> 6) | 192);
			utftext += String.fromCharCode((c & 63) | 128);
		} else {
			utftext += String.fromCharCode((c >> 12) | 224);
			utftext += String.fromCharCode(((c >> 6) & 63) | 128);
			utftext += String.fromCharCode((c & 63) | 128);
		}
	}
	return utftext;
}
function TrPage_Request_Encode(){
	var _TrPage_addRequestToQueue = TrPage.getInstance().getRequestQueue().constructor.prototype._addRequestToQueue;
	TrPage.getInstance().getRequestQueue().constructor.prototype._addRequestToQueue=function(type, context, listener, actionURL, content, headerParams){
		content = encodeCaracteresUTF(content);
		return _TrPage_addRequestToQueue.call(this, type, context, listener, actionURL, content, headerParams);
	}
}