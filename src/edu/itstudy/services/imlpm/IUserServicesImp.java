package edu.itstudy.services.imlpm;

import java.util.List;

import edu.itstudy.bean.productbean;
import edu.itstudy.bean.userbean;
import edu.itstudy.dao.IUserDao;
import edu.itstudy.dao.impl.IUserdaoImpl;
import edu.itstudy.services.IUserServices;
import edu.itstudy.bean.*;
public class IUserServicesImp implements IUserServices{

	private IUserDao iuserdao = new IUserdaoImpl();
	@Override

	public boolean register(userbean user) {
		int i = iuserdao.register(user);
		return i>0 ? true : false ;
	}
	@Override
	public userbean login(userbean user) {
		userbean user_ = iuserdao.login(user);
		return user_;
	}
	@Override
	public String forgetPassword(userbean user) {
		String find = iuserdao.forgetPassword(user);
		return find;
	}

	public List<productbean> information(){
		List<productbean> list = iuserdao.getAllinfos();
		if(null == list || list.size() == 0) {
			return null;
		}
		return list;
	}
//	�û���
	public List<userbean> UserInfo(){
		List<userbean> list = iuserdao.getAllUserinfos();
		if(null == list||list.size() == 0){
			return  null;
		}
		return list;
	}
//	ҩƷ��
	public List<medicinebean> medicineInfo(){
		List<medicinebean> Mlist = iuserdao.getMedicineInfo();
		if(null == Mlist || Mlist.size() == 0){
			return null;
		}
		return Mlist;
	}

	@Override
	public List<productbean> searchInfo(productbean product) {
		List<productbean> list = iuserdao.getSearchInfo(product);
		if(null == list || list.size() == 0) {
			return null;
		}
		return list ;
	}

	public List<userbean> searchUserInfo(userbean user) {
		List<userbean> list = iuserdao.getUserSearchInfo(user);
		if(null == list || list.size() == 0) {
			System.out.println("Ѱ�ҵ����Ǹ��յģ�ɶ��û��");
			return null;
		}
		System.out.println("������ҵ������");
		return list ;
	}

	public List<medicinebean> searchMedicineInfo(medicinebean medicine) {
		List<medicinebean> list = iuserdao.getMedicineSearchInfo(medicine);
		if(null == list || list.size() == 0) {
			System.out.println("Ѱ��ҩƷ��Ϣ�����Ǹ��յģ�ɶ��û��");
			return null;
		}
		System.out.println("������ҵ���ҩƷ��Ϣ���");
		return list ;
	}
	@Override
	public productbean getProductById(int id) {
		productbean product  = iuserdao.getProductById(id);
		return product;
	}
	public  medicinebean getMedicineById(String id){
		medicinebean medicine = iuserdao.getMedicineById(id);
		return medicine;
	}
	@Override
	public boolean updateProduct(productbean product) {
		int i = iuserdao.updateproduct(product);
		return i>0 ? true : false ;
	}
	@Override
	public boolean delete(int uid, int isD) {
		int i = iuserdao.delete(uid, isD);
		return i >0 ? true : false ;
	}
	@Override
	public boolean addProduct(productbean product) {
		int i = iuserdao.addProduct(product);
		return i>0 ? true : false ;
	}

}
