package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	void insert (Seller obj);
	void delete (Seller obj);
	void update(Seller obj);
	void deleteById (Integer id);
	Department selectDepById(Integer dep);
    Seller findById(Integer id);
	List<Seller>findAll();
	List<Seller>findByDepartment(Department departement);
}
