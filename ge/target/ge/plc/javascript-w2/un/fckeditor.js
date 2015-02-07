jQuery(function(){
	
	jQuery(document.getElementById('#{id}')).hide();
	
	setTimeout(function(){
		var textarea = '#{id}';
		var width = '#{width}' || 340;
		var height = '#{height}' || 200;
		var toolbar = '#{toolbar}' || 'jCompanyMinimo';
		if (document.getElementById(textarea)) {
			
			FCKeditorAPI = null;
			__FCKeditorNS = null;
			
			new FCKeditor(textarea, width, height, toolbar).ReplaceTextarea();
			
			jQuery(document).one('botaoAcaoGravar', function(){
				FCKeditorAPI.GetInstance(textarea).UpdateLinkedField();
			});
		}
	}, 200);
});
