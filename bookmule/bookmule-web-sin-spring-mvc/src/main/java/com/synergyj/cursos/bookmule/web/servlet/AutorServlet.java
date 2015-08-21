package com.synergyj.cursos.bookmule.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.synergyj.bookmule.core.domain.Autor;
import com.synergyj.bookmule.core.services.AutorService;

@WebServlet("/autores")
public class AutorServlet extends HttpServlet {

	private static final long serialVersionUID = -4484926642446004252L;

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AutorServlet.class);

	private AutorService autorService;

	/**
	 * Inicializamos nuestro servlet inyectando los beans que necesitemos
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		logger.debug("inicializando dependencias. ");
		WebApplicationContext appContext;

		// C) TODO, inicilizar el appContext
		appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext());

		autorService = appContext.getBean("autorService", AutorService.class);

		// Nota: podría verse esta técnica un tanto artesanal puesto que no hay
		// inyección directa,
		// sin embargo varios frameworks web proporcionan varios mecanimos para
		// realizarla,
		// incluyendo algunas funcionalidades que Spring ofrece para frameworks
		// como JSF , Struts.
		// etc.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Set<Autor> autorSet;
		autorSet = autorService.buscaAutores(new Autor());
		req.setAttribute("autores", autorSet);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/listaAutores.jsp");
		rd.forward(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Autor autor = new Autor();
		autor.setNombre(req.getParameter("nombre"));
		autor.setApellidoMaterno(req.getParameter("apellidoMaterno"));
		autor.setApellidoPaterno(req.getParameter("apellidoPaterno"));
		autorService.creaAutor(autor);
		doGet(req, resp);
	}

}
