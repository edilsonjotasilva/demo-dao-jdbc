package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Seller  findById(Integer id){
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
	
	private Department instantiationDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));// DepartmentId = 2
		dep.setName(rs.getString("DepName"));// Nome do departamento
		return dep;
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
	
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*,department.Name as DepName "
					+"from seller inner join department "
					+"ON seller.DepartmentId = department.Id "					
					+"order by DepartmentId, Name");	
										
				rs = st.executeQuery();
			
				List<Seller>list  = new ArrayList<Seller>();
				Map<Integer,Department>map = new HashMap<Integer,Department>();
				while(rs.next()) {
					Department dep = map.get(rs.getInt("DepartmentId"));
					if(dep == null) {
						 dep = instantiationDepartment(rs);
						 map.put(rs.getInt("DepartmentId"), dep);						 
					}else {			
					Seller obj = instatiationSeller(rs, dep);					
					list.add(obj);
					}
				}			
				return list;				
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}		
	
	
	}
	@Override
	public List<Seller> findByDepartment(Department department) {// 1° passo, id 2 nome null
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*,department.Name as DepName "
					+"from seller inner join department "
					+"ON seller.DepartmentId = department.Id "
					+"where DepartmentId = ? "//3° passo, ? recebe department.getId() que vale 2
					+"order by Name");	
			
			st.setInt(1, department.getId());//2° passo, department.getId == 2							
				rs = st.executeQuery();
				//4° passo, st.executeQuery() retorna todo seller que tem DepartementId = 2
				// e coloca esse retorno dentro da variavel rs
				List<Seller>list  = new ArrayList<Seller>();
				Map<Integer,Department>map = new HashMap<Integer,Department>();
				while(rs.next()) {
					Department dep = map.get(rs.getInt("DepartmentId"));// No inicio do
					
					// da condição while, map.get retorna nulo, pois a coleção map
					//ainda não foi preenchida
					
					if(dep == null) {
						 dep = instantiationDepartment(rs);
						 map.put(rs.getInt("DepartmentId"), dep);// rs.getInt("DepartmentId") é a chave
						 //dep é o valor referenciado pela chave
						 
					}else {	//else não é necessario, colocado apenas para compreensão				
					Seller obj = instatiationSeller(rs, dep);					
					list.add(obj);
					}
				}			
				return list;				
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}		
	}
}
