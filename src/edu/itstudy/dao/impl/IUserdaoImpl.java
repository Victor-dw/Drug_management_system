package edu.itstudy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import edu.itstudy.bean.medicinebean;
import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.dao.IUserDao;
import edu.itstudy.utils.DBContil;

public class IUserdaoImpl implements IUserDao {
//ע��
	@Override
	public int register(userbean user1) {
		Connection conn = DBContil.getConn();
		int res = 0;
		String sql = "insert into userinfo ( name , password , Apassword , email , brotherName)  values( ? , ? , ?  , ? , ? )";
		String sql2 = "select name from userinfo where name  = ?";
		PreparedStatement NameEqual = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);// ��ʼ��
			pstmt.setString(1, user1.getName());// ���ݴ�ǰ���õ���ֵ
			pstmt.setString(2, user1.getPassword());
			pstmt.setString(3, user1.getApassword());
			pstmt.setString(4, user1.getEmail());
			pstmt.setString(5, user1.getBrotherName());

//			���￼�Ը��û����ظ�����
			NameEqual = conn.prepareStatement(sql2);// ��ʼ��
			NameEqual.setString(1, user1.getName());// �õ������û�ע����û���
			ResultSet rsName = NameEqual.executeQuery();// ��ǰ̨���ݴ���resultset��
			if (rsName.next() == false) {// ������ƥ����
				res = pstmt.executeUpdate();// ֱ�ӽ����ϴ�
			}
			if (rsName.next() != false) {// �������ظ�
				res = -1;// ����-1����ʾʧ��
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
	}
//��¼
	@Override
	public userbean login(userbean user1) {
		int uid = 0;
		userbean user = null;
		Connection conn = DBContil.getConn();
		String sql = "select uid,email,brotherName,isAdmin from userinfo where name =? and password = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user1.getName());
			pstmt.setString(2, user1.getPassword());
//			  pstmt.setString(3,user1.getBrotherName());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				uid = rs.getInt(1);
				String email = rs.getString(2);
				String brotherName = rs.getString(3);
				int  isAdmin = rs.getInt(4);
				user = new userbean(uid,user1.getName(),user1.getPassword(),null,email,brotherName,isAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String forgetPassword(userbean user1) {
		String result = "";//��ʼ��
		Connection conn = DBContil.getConn();//��������
		String sql = "select password from userinfo where name = ? and brotherName = ?";//��ѯ����
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);//ȥ���ݿ��ѯ
			pstmt.setString(1, user1.getName());
			pstmt.setString(2,user1.getBrotherName());
			ResultSet rs = pstmt.executeQuery();//���ݴ洢
			if (rs.next()) {
				result = rs.getString(1);//�õ���������
				System.out.println(result);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
//		list.clear();
	}

	@Override
	public List<productbean> getAllinfos() {
		Connection conn = DBContil.getConn();//��ʼ��
		//sql��䣬�������ݿ��е�����
		String sql = "select itemId,price,begin,dest,jiphone,dephone,isDeal,time,productID,Date from product where isD = 1";
		PreparedStatement pstmt = null;
		List<productbean> list = new ArrayList<productbean>();
		try {
			pstmt = conn.prepareStatement(sql);//��sql������Mysql���в�ѯ
			ResultSet rs = pstmt.executeQuery();//����ѯ�����е����ݴ洢��ResultSet������
			while (rs.next()) {
				//����ResultSet�����������ݿ����ݰ����ó�
				int pid = rs.getInt(1);
				int price = rs.getInt(2);
				String begin = rs.getString(3);
				String dest = rs.getString(4);
				String jiphone = rs.getString(5);
				String dephone = rs.getString(6);
				int isDeal = rs.getInt(7);
				int time = rs.getInt(8);
				String productID = rs.getString(9);
				String Date = rs.getString(10);
				//�������ó������productbean�����װ
				productbean product = new productbean(pid, productID, price, begin, dest, jiphone, dephone, isDeal,
						time, Date);
				//�����list����Ϊ����ֵ
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//��ȡ�ض����û���Ϣ
//	public List<userbean> getSpecialUserInfo
//	��ȡ�û��� getUserSearchInfo
	@Override
	public List<userbean> getAllUserinfos() {
		Connection conn = DBContil.getConn();//��ʼ��
		//sql��䣬�������ݿ��е�����
		String sql = "select uid , name , email,brotherName from userinfo where isAdmin = 0";
		PreparedStatement pstmt = null;
		List<userbean> list = new ArrayList<userbean>();
		try {
			pstmt = conn.prepareStatement(sql);//��sql������Mysql���в�ѯ
			ResultSet rs = pstmt.executeQuery();//����ѯ�����е����ݴ洢��ResultSet������
			while (rs.next()) {
				//����ResultSet�����������ݿ����ݰ����ó�
				int uid = rs.getInt("uid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String brotherName = rs.getString("brotherName");

				//�������ó������productbean�����װ
				userbean user = new userbean(uid,name,null,null,email,brotherName);
				//�����list����Ϊ����ֵ
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<medicinebean> getMedicineInfo() {
		Connection conn = DBContil.getConn();//��ʼ��
		//sql��䣬�������ݿ��е�����
		String sql = "select medicineIntroId , medicineName , medicineIntroduce ,isOTC,dosage,img from medicineintroduce";
		PreparedStatement pstmt = null;
		List<medicinebean> list = new ArrayList<medicinebean>();
		try {
			pstmt = conn.prepareStatement(sql);//��sql������Mysql���в�ѯ
			ResultSet rs = pstmt.executeQuery();//����ѯ�����е����ݴ洢��ResultSet������
			while (rs.next()) {
				//����ResultSet�����������ݿ����ݰ����ó�
				String medicineIntroId = rs.getString("medicineIntroId");
				String medicineName = rs.getString("medicineName");
				String medicineIntroduce = rs.getString("medicineIntroduce");
				int isOTC = rs.getInt("isOTC");
				String dosage = rs.getString("dosage");
				String img = rs.getString("img");

				medicinebean medicineInfomation  = new medicinebean(medicineIntroId,medicineName,medicineIntroduce,isOTC,dosage,img);
				list.add(medicineInfomation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	getUserSearchInfo
	public List<userbean> getUserSearchInfo(userbean user) {
		Connection conn = DBContil.getConn();//��ʼ��
		String sql = "select uid,name,email,brotherName,isAdmin,workerID from userinfo where isD=0 ";//�����ݿ����ݽ��в�ѯ
		StringBuffer sb = new StringBuffer(sql);//ת��Ϊstringbuffer���ͣ����㶯̬sql
		List<Object> params = new ArrayList<Object>();
		//���µ�����ifĿ�����ж�������������Ƿ�Ϊ�գ������Ϊ�գ����ж�̬sql�����ĸ��ӡ�
		if ( user.getWorkerID()!=null&&!"".equals(user.getWorkerID())) {
			sb.append(" and workerID like ? ");
			params.add("%" + user.getWorkerID() + "%");
		}
		if (user.getName() != null && !"".equals(user.getName())) {
			sb.append(" and name like ? ");
			params.add("%" + user.getName() + "%");
		}

		sql = sb.toString(); //��������Stringת��
		PreparedStatement pstmt = null;
		List<userbean> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));//��list��Ӷ���
			}
			ResultSet rs = pstmt.executeQuery();//�õ���Ҫ��ʾ������
			list = new ArrayList<userbean>();
			while (rs.next()) {
				//����Ҫ��ʾ�����ݴ�rs ��ȡ����
				int uid = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String brotherName = rs.getString(4);
				int isAdmin = rs.getInt(5);
				String workerID = rs.getString(6);
				//�����װ
//				productbean product1 = new productbean(uid, productID, begin, dest, isDeal, riqi);
//				userbean user1 = new userbean(uid,name,null,null,email,brotherName,isAdmin);
				userbean user1 = new userbean(uid,name,null,null,email,brotherName,isAdmin,workerID);
				list.add(user1);//�������ʾlist

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//����
	}
	//��ȡҩƷ��Ϣ��
	public List<medicinebean> getMedicineSearchInfo(medicinebean medicine) {
		Connection conn = DBContil.getConn();//��ʼ��
		String sql = "select medicineIntroId,medicineName,medicineIntroduce,isOTC,dosage from medicineintroduce where isD=0 ";//�����ݿ����ݽ��в�ѯ
		StringBuffer sb = new StringBuffer(sql);//ת��Ϊstringbuffer���ͣ����㶯̬sql
		List<Object> params = new ArrayList<Object>();
		//���µ�����ifĿ�����ж�������������Ƿ�Ϊ�գ������Ϊ�գ����ж�̬sql�����ĸ��ӡ�
		if ( medicine.getMedicineName()!=null&&!"".equals(medicine.getMedicineName())) {
			sb.append(" and medicineName like ? ");
			params.add("%" + medicine.getMedicineName() + "%");
		}
		if (medicine.getDosage() != null && !"".equals(medicine.getDosage())) {
			sb.append(" and dosage like ? ");
			params.add("%" + medicine.getDosage() + "%");
		}

		sql = sb.toString(); //��������Stringת��
		PreparedStatement pstmt = null;
		List<medicinebean> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));//��list��Ӷ���
			}
			ResultSet rs = pstmt.executeQuery();//�õ���Ҫ��ʾ������
			list = new ArrayList<medicinebean>();
			while (rs.next()) {
				//����Ҫ��ʾ�����ݴ�rs ��ȡ����
				String medicineIntroId = rs.getString(1);
				String medicineName = rs.getString(2);
				String medicineIntroduce = rs.getString(3);
				int isOTC = rs.getInt(4);
				String dosage = rs.getString(5);
				//�����װ
//				productbean product1 = new productbean(uid, productID, begin, dest, isDeal, riqi);
//				userbean user1 = new userbean(uid,name,null,null,email,brotherName,isAdmin);
				medicinebean medicine1 = new medicinebean(medicineIntroId,medicineName,medicineIntroduce,isOTC,dosage,null);
				list.add(medicine1);//�������ʾlist

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//����
	}
	@Override
	public List<productbean> getSearchInfo(productbean product) {
		Connection conn = DBContil.getConn();//��ʼ��
		String sql = "select productID,begin,dest,Date,isDeal,itemId from product where isD=1 ";//�����ݿ����ݽ��в�ѯ
		StringBuffer sb = new StringBuffer(sql);//ת��Ϊstringbuffer���ͣ����㶯̬sql
		List<Object> params = new ArrayList<Object>();
		//���µ�����ifĿ�����ж�������������Ƿ�Ϊ�գ������Ϊ�գ����ж�̬sql�����ĸ��ӡ�
		if (product.getProductID() != null && !"".equals(product.getProductID())) {
			sb.append(" and productID like ? ");
			params.add("%" + product.getProductID() + "%");
		}
		if (product.getBegin() != null && !"".equals(product.getBegin())) {
			sb.append(" and begin like ? ");
			params.add("%" + product.getBegin() + "%");
		}
		if (product.getDest() != null && !"".equals(product.getDest())) {
			sb.append(" and dest like ? ");
			params.add("%" + product.getDest() + "%");
		}
		sql = sb.toString(); //��������Stringת��
		PreparedStatement pstmt = null;
		List<productbean> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));//��list��Ӷ���
			}
			ResultSet rs = pstmt.executeQuery();//�õ���Ҫ��ʾ������
			list = new ArrayList<productbean>();
			while (rs.next()) {
				//����Ҫ��ʾ�����ݴ�rs ��ȡ����
				String productID = rs.getString(1);
				String begin = rs.getString(2);
				String dest = rs.getString(3);
				String riqi = rs.getString(4);
				int isDeal = rs.getInt(5);
				int uid = rs.getInt(6);
				//�����װ
				productbean product1 = new productbean(uid, productID, begin, dest, isDeal, riqi);
				list.add(product1);//�������ʾlist
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//����
	}


	
	@Override
	public userbean getProductById(String id) {
		Connection conn = DBContil.getConn();
		String sql = "select workerID,isAdmin from userinfo where name = ? ";
		PreparedStatement pstmt = null;
		userbean product = null;
		try {
			pstmt = conn.prepareStatement(sql);
//			System.out.println("�ҿ���ǰ̨�õ�id��û"+" "+id);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String workerID = rs.getString(1);
				int isAdmin = rs.getInt(2);
//				product = new productbean(pid, productID, price, begin, dest, jiphone, dephone, isDeal, time, Date);
				product = new userbean(id,workerID,isAdmin);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return product;

	}

	public medicinebean getMedicineById(String id){
		Connection conn = DBContil.getConn();
		String sql = "select medicineName,medicineIntroduce,isOTC,dosage from medicineintroduce where medicineIntroId = ?";
		PreparedStatement pstmt = null;
		medicinebean medicine = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String medicineName = rs.getString(1);
				String medicineIntroduce = rs.getString(2);
				int isOTC = rs.getInt(3);
				String dosage = rs.getString(4);

//				medicine = new productbean(pid, productID, price, begin, dest, jiphone, dephone, isDeal, time, Date);
				medicine = new medicinebean(id,medicineName,medicineIntroduce,isOTC,dosage,null);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return medicine;
	}

	@Override
	public int updateproduct(userbean product) {
		Connection conn = DBContil.getConn();
		int res = 0;
		String sql = "update userinfo set isAdmin = ? , workerID = ? where name = ? ";
//		String sql2 = "select name from userinfo where name  = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);// ��ʼ��
			System.out.println("!!!!?!"+product.getWorkerID()+" "+product.getIsAdmin());
			pstmt.setInt(1, product.getIsAdmin());// ���ݴ�ǰ���õ���ֵ
			pstmt.setString(2, product.getWorkerID());
			pstmt.setString(3,product.getName());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("res="+res);
		return res;
	}

	@Override
	public int delete(String uid,int isD) {
		Connection conn = DBContil.getConn();
		int res = 0;
		String sql = "update userinfo set isD=? where workerID = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);// ��ʼ��

			pstmt.setInt(1, 1);
			pstmt.setString(2, uid);
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int addProduct(productbean product) {
		Connection conn = DBContil.getConn();
		int res = 0;
		String sql = "insert into product ( productID , price , begin , dest , jiphone , dephone , isDeal , time , Date )  values( ? , ? , ? , ? ,? , ? , ?,?,?  )";
		String sql2 = "select productID from product where productID  = ?";
		PreparedStatement NameEqual = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);// ��ʼ��
			pstmt.setString(1,product.getProductID() );// ���ݴ�ǰ���õ���ֵ
			pstmt.setInt(2,product.getPrice() );
			pstmt.setString(3,product.getBegin() );
			pstmt.setString(4, product.getDest());
			pstmt.setString(5, product.getJiphone());
			pstmt.setString(6, product.getDephone());
			pstmt.setInt(7, product.getIsDeal());
			pstmt.setInt(8, product.getTime());
			pstmt.setString(9, product.getDate());

//			���￼�Ը��û����ظ�����
			NameEqual = conn.prepareStatement(sql2);// ��ʼ��
			NameEqual.setString(1, product.getProductID());//
			ResultSet rsName = NameEqual.executeQuery();// ��ǰ̨���ݴ���resultset��
			if (rsName.next() == false) {// ������ƥ����
				res = pstmt.executeUpdate();// ֱ�ӽ����ϴ�
			}
			if (rsName.next() != false) {// �������ظ�
				res = -1;// ����-1����ʾʧ��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
