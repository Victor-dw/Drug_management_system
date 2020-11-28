package edu.itstudy.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;

import edu.itstudy.bean.warehousebean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class warehouseInfoServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֹ����
		List<warehousebean> list = iuserServices.warehouseInfo();//�õ�Dao�е�list
		System.out.println("thisis warehouselist"+list);
		request.setAttribute("warehouseList", list);//��list������Ϊ��list����
		request.getRequestDispatcher("warehouseOK").forward(request,response);
		request.getRequestDispatcher("index").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
