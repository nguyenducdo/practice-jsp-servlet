package com.dodo.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dodo.constant.SystemConstant;
import com.dodo.model.New;
import com.dodo.paging.Pageble;
import com.dodo.service.ICategoryService;
import com.dodo.service.INewService;

@WebServlet(urlPatterns = {"/admin-news"})
public class NewController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action==null) {
			Integer count = newService.countTotalItems();
			if(count==null) {
				System.out.println("count news error");
			}
			
			String pageStr = req.getParameter("page");
			String limitStr = req.getParameter("limit");
			String sortName = req.getParameter("sortName");
			String sortBy = req.getParameter("sortBy");
			
			Pageble pageble = newService.getPagebleForList(pageStr, limitStr, sortName, sortBy);
			
			int totalPages = pageble.getTotalPages(count);
			
			New model = new New();
			model.setList(newService.findAll(pageble));
			req.setAttribute(SystemConstant.MODEL, model);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", pageble.getPage());
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/new/list.jsp");
			rd.forward(req, resp);
			return;
		}else if(action.equalsIgnoreCase(SystemConstant.ACTION_CREATE) || 
				action.equalsIgnoreCase(SystemConstant.ACTION_UPDATE)) {
			String idStr = req.getParameter("id");
			if(idStr!=null) {
				long id = Long.parseLong(idStr);
				New model = newService.findOne(id);
				req.setAttribute(SystemConstant.MODEL, model);
			}
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/new/editView.jsp");
			rd.forward(req, resp);
			return;
		}
	}
	
}