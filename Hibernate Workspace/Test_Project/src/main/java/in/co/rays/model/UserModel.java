package in.co.rays.model;

import in.co.rays.exception.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.co.rays.bean.UserBean;
import in.co.rays.utility.JDBCDataSource;

public class UserModel {
	public long nextPK() throws DatabaseException {

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public void add(UserBean bean) throws Exception {

		// Class.forName("com.mysql.jdbc.Driver");
		Connection conn = JDBCDataSource.getConnection();
		conn.setAutoCommit(false);

		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO ST_USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setLong(1, nextPK());
		ps.setString(2, bean.getFirstName());
		ps.setString(3, bean.getLastName());
		ps.setString(4, bean.getLogin());
		ps.setString(5, bean.getPassword());
		ps.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(7, bean.getMobileNo());
		ps.setLong(8, bean.getRoleId());
		ps.setInt(9, bean.getUnSuccessfulLogin());
		ps.setString(10, bean.getGender());
		ps.setTimestamp(11, bean.getLastLogin());
		ps.setString(12, bean.getLock());
		ps.setString(13, bean.getRegisteredIp());
		ps.setString(14, bean.getLastLoginIp());
		ps.setString(15, bean.getCreatedBy());
		ps.setString(16, bean.getModifiedBy());
		ps.setTimestamp(17, bean.getCreatedDatetime());
		ps.setTimestamp(18, bean.getModifideDatetime());

		int i = ps.executeUpdate();

		System.out.println(i + "Inserted");
		conn.commit();
		ps.close();
		conn.close();

	}

	public void delete(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_USER WHERE ID = ?");
			ps.setLong(1, bean.getId());
			int i = ps.executeUpdate();
			System.out.println(i + " deleted");
			conn.commit();
			ps.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getLogin());
			ps.setString(4, bean.getPassword());
			ps.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			ps.setString(6, bean.getMobileNo());
			ps.setLong(7, bean.getRoleId());
			ps.setInt(8, bean.getUnSuccessfulLogin());
			ps.setString(9, bean.getGender());
			ps.setTimestamp(10, bean.getLastLogin());
			ps.setString(11, bean.getLock());
			ps.setString(12, bean.getRegisteredIp());
			ps.setString(13, bean.getLastLoginIp());
			ps.setString(14, bean.getCreatedBy());
			ps.setString(15, bean.getModifiedBy());
			ps.setTimestamp(16, bean.getCreatedDatetime());
			ps.setTimestamp(17, bean.getModifideDatetime());
			ps.setLong(18, bean.getId());

			int i = ps.executeUpdate();
			System.out.println(i + " Updated Data");
			conn.commit();
			ps.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List<UserBean> search() throws Exception {
		Connection conn = null;
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_USER");
			list = new ArrayList<UserBean>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifideDatetime(rs.getTimestamp(18));
				list.add(bean);

			}
			conn.commit();
			ps.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<UserBean> findByPk(UserBean bean1) throws ApplicationException {
		Connection conn = null;
		UserBean bean;
		ArrayList<UserBean> list;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_USER WHERE ID =?");
			ps.setLong(1, bean1.getId());
			ResultSet rs = ps.executeQuery();

			list = new ArrayList<UserBean>();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			conn.commit();
			ps.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<UserBean> findByLogin(UserBean bean1) throws ApplicationException {
		Connection conn = null;
		UserBean bean;
		ArrayList<UserBean> list;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_USER WHERE LOGIN =?");
			ps.setString(1, bean1.getLogin());
			ResultSet rs = ps.executeQuery();

			list = new ArrayList<UserBean>();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			conn.commit();
			ps.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
