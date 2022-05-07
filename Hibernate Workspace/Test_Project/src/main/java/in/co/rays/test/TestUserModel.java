package in.co.rays.test;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.UserModel;

public class TestUserModel {
	public static void main(String[] args) throws Exception {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testSearch();
		// testFindByPk();
		testFindByLogin();

	}

	private static void testFindByLogin() throws ApplicationException {
		UserBean bean1 = new UserBean();
		bean1.setLogin("veenayadav@gmail.com");
		UserModel model = new UserModel();
		List list = model.findByLogin(bean1);
		Iterator it = list.iterator();
		while (it.hasNext()) {

			UserBean bean = (UserBean) it.next();
			System.out.print(bean.getId());
			System.out.print(" " + bean.getFirstName());
			System.out.print(" " + bean.getLastName());
			System.out.print(" " + bean.getLogin());
			System.out.print(" " + bean.getPassword());
			System.out.print(" " + bean.getDob());
			System.out.print(" " + bean.getRoleId());
			System.out.print(" " + bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.print(" " + bean.getLastLogin());
			System.out.print(" " + bean.getLock());
			System.out.print(" " + bean.getRegisteredIp());
			System.out.print(" " + bean.getLastLoginIp());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifideDatetime());

		}
	}

	private static void testFindByPk() throws ApplicationException {
		UserBean bean1 = new UserBean();
		bean1.setId(1);
		UserModel model = new UserModel();
		List list = model.findByPk(bean1);
		Iterator it = list.iterator();
		while (it.hasNext()) {

			UserBean bean = (UserBean) it.next();
			System.out.print(bean.getId());
			System.out.print(" " + bean.getFirstName());
			System.out.print(" " + bean.getLastName());
			System.out.print(" " + bean.getLogin());
			System.out.print(" " + bean.getPassword());
			System.out.print(" " + bean.getDob());
			System.out.print(" " + bean.getRoleId());
			System.out.print(" " + bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.print(" " + bean.getLastLogin());
			System.out.print(" " + bean.getLock());
			System.out.print(" " + bean.getRegisteredIp());
			System.out.print(" " + bean.getLastLoginIp());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifideDatetime());

		}

	}

	private static void testSearch() throws Exception {
		UserModel model = new UserModel();
		List list = model.search();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			UserBean bean = (UserBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getUnSuccessfulLogin());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getLastLogin());
			System.out.print("\t" + bean.getLock());
			System.out.print("\t" + bean.getRegisteredIp());
			System.out.print("\t" + bean.getLastLoginIp());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifideDatetime());

		}

	}

	private static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("mm-DD-yyy");

		bean.setFirstName("Priya");
		bean.setLastName("Sharma");
		bean.setLogin("sharmapriya@gmail.com");
		bean.setPassword("12345");
		bean.setDob(sdf.parse("01-02-2000"));
		bean.setMobileNo("1234567890");
		bean.setRoleId(12345);
		bean.setUnSuccessfulLogin(0);
		bean.setGender("female");
		bean.setId(5);

		UserModel model = new UserModel();
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		UserBean bean = new UserBean();
		bean.setId(12);
		UserModel model = new UserModel();
		model.delete(bean);

	}

	private static void testAdd() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("mm-DD-yyy");
		UserBean bean = new UserBean();
		bean.setFirstName("abc");
		bean.setLastName("xyz");
		bean.setLogin("abcxyz@gmail.com");
		bean.setPassword("12345");
		bean.setDob(sdf.parse("01-02-2000"));
		bean.setMobileNo("1234567890");
		bean.setRoleId(12345);
		bean.setUnSuccessfulLogin(0);
		bean.setGender("male");

		UserModel model = new UserModel();
		model.add(bean);

	}
}
