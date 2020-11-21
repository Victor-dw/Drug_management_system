package edu.itstudy.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.userbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class forgetPassword extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֹ����
		System.out.println("�����������");
		String name = request.getParameter("name");//�õ�name
		String brotherName = request.getParameter("brotherName");//��ȡ���ֵ�����

		userbean user = new userbean(0,name,null,null,null,brotherName);//��װ
		String password = iuserServices.forgetPassword(user);//���ò�ѯ����
		if(password != null && password != "") {
			request.getSession().setAttribute("password",password);//�洢session
			response.sendRedirect("password.jsp");//��תҳ��
		}else {
			request.setAttribute("info", "��ѯ�������,������ע��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
