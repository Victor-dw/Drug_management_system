package edu.itstudy.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.services.IUserServices;
import edu.itstudy.services.imlpm.IUserServicesImp;

public class productOfServlet extends HttpServlet {

	private IUserServices iuserServices = new IUserServicesImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�û�ҳ�����");
		request.setCharacterEncoding("UTF-8");//��ֹ����
		//�����������Ŀ���Ǵ�ǰ��ȡ�����������
		String id = request.getParameter("workerID");
		if(id == ""|| id == null){
			System.out.println("��id����ǿյģ�����");
		}
		String name = request.getParameter("name");
		if(name == ""|| name == null){
			System.out.println("��name����ǿյģ�����");
		}
		System.out.println("ǰ̨�õ���id"+id);
		System.out.println("ǰ̨�õ���name:"+name);

		userbean user_search =new userbean(name,null,null,null,null,0,0,id);
//		productbean product_search = new productbean(0,id,begin,dst);//�����ݽ��з�װ
		List<userbean> list = iuserServices.searchUserInfo(user_search);//����װ�Ķ����뵽Dao��õ�list
//		System.out.println("�û����Ƿ�գ�"+list.isEmpty());
		request.setAttribute("userlist", list);//׼��ת��ǰ�ˣ����д��
		//����productControlҳ�棬�������н���չʾ
		request.getRequestDispatcher("productControl.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
