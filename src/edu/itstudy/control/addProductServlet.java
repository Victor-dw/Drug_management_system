package edu.itstudy.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class addProductServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֹ����
		//��ǰ����ȡ���������ϴ���Dao�������ݿ���н��
		String productID = request.getParameter("productID");
		String price = request.getParameter("price");
		String begin = request.getParameter("begin");
		String dest = request.getParameter("dest");
		String jiphone = request.getParameter("jiphone");
		String dephone = request.getParameter("dephone");
		String isDeal = request.getParameter("isDeal");
		String time = request.getParameter("time");
		String Date = request.getParameter("Date");
		//��װΪproductbean����
		productbean product = new productbean(0,productID,Integer.parseInt(price),begin,dest,jiphone,dephone,Integer.parseInt(isDeal),Integer.parseInt(time),Date);
		boolean result = iuserServices.addProduct(product);
		if (result) {//�ɹ���
			response.sendRedirect("/net/productControl");//ֱ�ӽ���ҳ����ת
		} else {//��δ�ɹ�
			request.setAttribute("info", "�������Ѵ��ڵ������ʧ��");//ǰ̨���ݲ���info����֪�û������������
			request.getRequestDispatcher("addproduct.jsp").forward(request, response);//ͬʱ��ת��ע��ҳ��
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
