package edu.itstudy.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.productbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class productOfServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֹ����
		//�����������Ŀ���Ǵ�ǰ��ȡ�����������
		String id = request.getParameter("productid");
		String begin = request.getParameter("begin");
		String dst = request.getParameter("dest");	
		productbean product_search = new productbean(0,id,begin,dst);//�����ݽ��з�װ
		List<productbean> list = iuserServices.searchInfo(product_search);//����װ�Ķ����뵽Dao��õ�list
		request.setAttribute("list", list);//׼��ת��ǰ�ˣ����д��
		//����productControlҳ�棬�������н���չʾ
		request.getRequestDispatcher("productControl.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
