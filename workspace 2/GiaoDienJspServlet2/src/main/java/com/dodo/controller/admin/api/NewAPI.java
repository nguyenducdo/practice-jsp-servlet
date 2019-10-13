package com.dodo.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dodo.model.New;
import com.dodo.service.INewService;
import com.dodo.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api-admin-new")
public class NewAPI extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request
		req.setCharacterEncoding("UTF-8");
		New newModel = HttpUtil.of(req.getReader()).toModel(New.class);
		long id = newService.insert(newModel);
		
		//response
		resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		newModel = newService.findOne(id);
		objectMapper.writeValue(resp.getOutputStream(), newModel);
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request
		req.setCharacterEncoding("UTF-8");
		New newModel = HttpUtil.of(req.getReader()).toModel(New.class);
		newService.update(newModel);
		
		//response
		resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		newModel = newService.findOne(newModel.getId());
		objectMapper.writeValue(resp.getOutputStream(), newModel);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request
		req.setCharacterEncoding("UTF-8");
		New newModel = HttpUtil.of(req.getReader()).toModel(New.class);
		long[] ids = new long[] {newModel.getId()};
		
		newService.delete(ids);
		//response
		resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(resp.getOutputStream(), "{}");
	}
	
}
