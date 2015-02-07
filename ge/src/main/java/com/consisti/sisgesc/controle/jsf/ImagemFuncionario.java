package com.consisti.sisgesc.controle.jsf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagemFuncionario extends HttpServlet  {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	
		byte[] arquivo = (byte[]) request.getSession().getAttribute("fotoFuncionario"); 
		if (arquivo!=null){
			request.getSession().removeAttribute("fotoFuncionario"); 
			response.setHeader("Cache-Control", "no-store");  
		    response.setHeader("Pragma", "no-cache");  
		    response.setDateHeader("Expires", 0);  
		    response.setContentType("image/jpeg");  
		  
		    ServletOutputStream out = response.getOutputStream();  
		    out.write(arquivo);  
		    out.flush();  
		    out.close();
		}
	} 
	
}
