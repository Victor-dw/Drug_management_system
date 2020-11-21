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
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class medicineOfServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ҩƷ��Ϣҳ�����");
		request.setCharacterEncoding("UTF-8");//��ֹ����
		//�����������Ŀ���Ǵ�ǰ��ȡ�����������
		String medicineName = request.getParameter("medicineName");
		String dosage = request.getParameter("dosage");

//		userbean user_search =new userbean(name,null,null,null,null,0,0,id);
		medicinebean medicine_search = new medicinebean(medicineName,dosage);

		List<medicinebean> list = iuserServices.searchMedicineInfo(medicine_search);//����װ�Ķ����뵽Dao��õ�list
//		System.out.println("�û����Ƿ�գ�"+list.isEmpty());
		request.setAttribute("medicineList", list);//׼��ת��ǰ�ˣ����д��
		//����productControlҳ�棬�������н���չʾ
		request.getRequestDispatcher("medicineControl.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
