package br.com.xy_inc.infra;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.xy_inc.controller.RegistroController;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.model.Registro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet responsavel por obter o link da requisicao REST e verificar se existe
 * modelo para o mesmo, caso exista prossegue com a insercao.
 * 
 * @author Leonardo
 * @since 23/10/2015
 */
@WebServlet(value = "/servico/*")
public class ServiceServlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RegistroController registroController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {

			Registro registro = registroController.listarRegistro(request
					.getPathInfo().replace("/", ""), id);

			if (registro != null) {

				Gson gson = new GsonBuilder().serializeNulls()
						.excludeFieldsWithoutExposeAnnotation().create();

				String json = gson.toJson(registro);

				response.setContentType("application/json");
				response.getWriter().write(json);
				
			} else {

				response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
			}

		} else {

			List<Registro> registros = registroController
					.listarTodosRegistros(request.getPathInfo()
							.replace("/", ""));

			if (CollectionUtils.isNotEmpty(registros)) {

				Gson gson = new GsonBuilder().serializeNulls()
						.excludeFieldsWithoutExposeAnnotation().create();

				String json = gson.toJson(registros);

				response.setContentType("application/json");
				response.getWriter().write(json);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String json = request.getParameter("objeto_json");

		if (StringUtils.isNotBlank(json)) {

			Gson gson = new Gson();

			Type collectionType = new TypeToken<List<Propriedade>>() {
			}.getType();

			List<Propriedade> propriedades = gson
					.fromJson(json, collectionType);

			// Se ocorrer algum erro na insercao retorna HTTP erro 400
			// BAD_REQUEST.

			Registro registro = registroController.inserirRegistro(request
					.getPathInfo().replace("/", ""), propriedades);
			if (registro == null) {
				response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
			} else {
				gson = new GsonBuilder().serializeNulls()
						.excludeFieldsWithoutExposeAnnotation().create();

				json = gson.toJson(registro);

				response.setContentType("application/json");
				response.getWriter().write(json);
			}
		} else {
			response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("objeto_json");

		if (StringUtils.isNotBlank(json)) {

			Gson gson = new Gson();

			Type collectionType = new TypeToken<Registro>() {
			}.getType();

			Registro registro = gson.fromJson(json, collectionType);

			// Se ocorrer algum erro na insercao retorna HTTP erro 400
			// BAD_REQUEST.

			registro = registroController.atualizarRegistro(request
					.getPathInfo().replace("/", ""), registro);

			if (registro == null) {
				response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
			} else {
				gson = new GsonBuilder().serializeNulls()
						.excludeFieldsWithoutExposeAnnotation().create();

				json = gson.toJson(registro);

				response.setContentType("application/json");
				response.getWriter().write(json);
			}
		} else {
			response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (!registroController.apagarRegistro(id, request.getPathInfo()
				.replace("/", ""))) {
			response.sendError(AppConstants.HTTP_ERROR_400_BAD_REQUEST);
		}

	}

}
