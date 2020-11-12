package edu.itstudy.control;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.userbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class RegistServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String Apassword = request.getParameter("Apassword");
		String email = request.getParameter("email");
		String brotherName = request.getParameter("brotherName");
		userbean user = new userbean(0, name, password, Apassword, email,brotherName);
		boolean result = iuserServices.register(user);
		if (result) {//�ɹ���
			response.sendRedirect("login.jsp");//ֱ�ӽ���ҳ����ת
		} else {//��δ�ɹ�
			request.setAttribute("info", "�û����Ѵ��ڵ���ע��ʧ��");//ǰ̨���ݲ���info����֪�û������������
			request.getRequestDispatcher("register.jsp").forward(request, response);//ͬʱ��ת��ע��ҳ��
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
