package br.univel.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.dao.AlunoDao;
import br.univel.model.Aluno;

/**
 * Servlet recurso que é gerenciado pelo servidor de aplicação
 * E por gerenciado ele consegue instaciar outro objetos
 * @author VLBPANOSSO
 *
 */
@WebServlet("/TesteInsert")
public class TesteInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ao fazer deploy, o servidor porcura essa anotation e instacia a variavel.
	 * injeção de dependecia
	 */
	@EJB
	private AlunoDao dao;
	
	/**
	 * HttpServlet é uma classe que facilita na comunicação Http
	 * request: é requisição do cliente 
	 * Response: resposta para o cliente 	
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (PrintWriter writer = response.getWriter()) {
			
			writer.println("Inserido 1000 registros.");
			
			for (int i = 0; i < 1000; i++) {
				Aluno a = new Aluno();
				a.setNome("Aluno " + i);
				a.setTelefone("4599999999");
				
				//DAO: Objeto de Acesso a dados
				Aluno aRet = dao.salvar(a);
				writer.println("Inserido id: " + aRet.getId());
			}
			writer .flush();
		}
	}

}
