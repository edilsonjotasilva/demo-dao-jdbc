package model.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
private Connection conn;

	public SellerDaoJDBC(Connection conn) {
	this.conn = conn;
}
	@Override
	public void insert(Seller obj) {

	}
	@Override
	public void delete(Seller obj) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st =conn.prepareStatement(
					"DELETE from seller where Id = ? "
					);
			st.setInt(1, 24);
			int rowsAffected = st.executeUpdate();
			System.out.println("rows Affected: "+rowsAffected);
		}
		 catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.getConnection();
		}
	}
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
							+ "from seller inner join department "
							+ "on seller.DepartmentId = department.Id "
							+ "where seller.Id = ?");		
				st.setInt(1, id);
				rs = st.executeQuery();
				if(rs.next()) {
					Department dep = instantiationDepartment(rs);
					Seller obj = instatiationSeller(rs, dep);
					return obj;
				}			
				return null;				
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}		
	}

	private Seller instatiationSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
			obj.setId(rs.getInt("Id"));
			obj.setName(rs.getString("Name"));
			obj.setEmail(rs.getString("Email"));
			obj.setBaseSalary(rs.getDouble("BaseSalary"));
			obj.setBirthDate(rs.getDate("BirthDate"));
			obj.setDepartment(dep);
		return obj;
	}
	private Department instantiationDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
