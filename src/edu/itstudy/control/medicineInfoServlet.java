package edu.itstudy.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.itstudy.bean.medicinebean;
import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class medicineInfoServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��ֹ����
		String itemId = request.getParameter("uid");//�õ�ǰ�˵�uid
		if(null == itemId ||"".equals(itemId)) {
			System.out.println(itemId);
		}
		//ֱ�Ӷ��õ���uid��Ϊ�����������в�ѯ
//		medicinebean medicine = iuserServices.getProductById(Integer.parseInt(itemId));
		medicinebean medicine = iuserServices.getMedicineById(itemId);
		request.setAttribute("medicineInfo", medicine);
		//�ж��Ƿ���ڣ�ͬʱ������ת
		String flag = request.getParameter("flag");
		if(null == flag) {
			request.getRequestDispatcher("medicineInfo.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
