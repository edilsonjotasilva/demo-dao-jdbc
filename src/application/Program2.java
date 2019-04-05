package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
//		Department department = new Department();
//		System.out.println("==========TEST 1 : findById==========");
//		department = departmentDao.findById(1);
//		System.out.println(department);
	/////////////////////////////////////////////////////
		System.out.println(); 
		List<Department>departmentList = new ArrayList<Department>();
		System.out.println("==========TEST 2 : findAll==========");
		departmentList = departmentDao.findAll();
		for (Department department2 : departmentList) {
			System.out.println(department2);
		}
//		System.out.println();
//		System.out.println("==========TEST 3 : findAll==========");	
//		System.out.println("enter department ID to delete");
//		int id = sc.nextInt();
//		departmentDao.deleteById(id);
/////////////////////////////////////////////////////
//		System.out.println("==========TEST 4 : insert==========");	
//		System.out.println("enter departmente name to insert");
//		String name = sc.nextLine();
//		departmentDao.insert(name);
		///////////////////////////////////////////////
		System.out.println("==========TEST 5 : Update==========");
		Department depUpdate = new Department();
		System.out.println("enter departmente Id to Update");
		int idUpdate = sc.nextInt();
		System.out.println("enter new departmente Name to Update");
		sc.nextLine();
		String nameUpdate = sc.nextLine();
		depUpdate.setId(idUpdate);
		depUpdate.setName(nameUpdate);
		departmentDao.update(depUpdate);
		
		
	}

}
