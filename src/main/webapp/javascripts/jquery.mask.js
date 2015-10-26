/**
 * 
 */
jQuery.fn.mask = function($) {
	$("#campoData").mask("99/99/9999");
	$("#campoTelefone").mask("(999) 9999-9999");
	$("#campoSenha").mask("***-****");
	$("#campoCNPJ").mask("99.999.999/9999-99");
	$("campoCPF").mask("999.999.999-99");
	$("#campoCEP").mask("99.999-999");
	
};
