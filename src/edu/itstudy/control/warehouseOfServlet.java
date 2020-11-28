package edu.itstudy.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.bean.medicinebean;
import edu.itstudy.bean.warehousebean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class warehouseOfServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�ֿ�ҳ�����");
		request.setCharacterEncoding("UTF-8");//��ֹ����
		//�����������Ŀ���Ǵ�ǰ��ȡ�����������
		String medicine_id = request.getParameter("medicine_id");
		String medicine_name = request.getParameter("medicine_name");
		System.out.println("���������id"+" !!!!!! "+medicine_id+" "+" !!!!!! "+medicine_name);
		warehousebean warehouse_search = new warehousebean(medicine_id,medicine_name);

		List<warehousebean> list = iuserServices.searchwarehouseInfo(warehouse_search);//����װ�Ķ����뵽Dao��õ�list
		System.out.println("������warehouse�����list" + list);
//		System.out.println("�û����Ƿ�գ�"+list.isEmpty());
		request.setAttribute("warehouseList", list);//׼��ת��ǰ�ˣ����д��
		//����productControlҳ�棬�������н���չʾ
//		request.getRequestDispatcher("medicineControl.jsp").forward(request, response);
		request.getRequestDispatcher("warehouseOK.jsp").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
