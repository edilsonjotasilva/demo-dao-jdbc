package application;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	static int op;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	SellerDao sellerDao = DaoFactory.createSellerDao();
	
		

		System.out.println("=== TEST 1: Seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	
		System.out.println();

		System.out.println("\n=== TEST 2: Seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		
		System.out.println("\n=== TEST 3: Seller findAll ===");
		List<Seller> listAll = sellerDao.findAll();

		for (Seller sellerAll : listAll) {
			System.out.println(sellerAll);
		}
		System.out.println();

//		System.out.println("\n=== TEST 4: Seller Insert ===");
//		seller = new Seller();
//		System.out.println("Insira os Dados");
//		do {
//			System.out.println("Digite o nome: ");
//			sc.hasNextLine();
//			String nome = sc.nextLine();
//			seller.setName(nome);
//			System.out.println("Digite o email: ");
//			String email = sc.next();
//			seller.setEmail(email);
//			System.out.println("DAta: ");
//			sc.nextLine();
//			String data = sc.nextLine();
//
//			try {
//				Date dataSql = new java.sql.Date(sdf.parse(data).getTime());
//				seller.setBirthDate(dataSql);
//			} catch (ParseException e) {
//
//				e.printStackTrace();
//			}
//
//			System.out.println("Salario Base:");
//			Double salario = sc.nextDouble();
//			seller.setBaseSalary(salario);
//			System.out.println("ID do Departamento");
//			int idDapart = sc.nextInt();
//			Department newDepart = new Department(idDapart, null);
//			seller.setDepartment(newDepart);
//			sellerDao.insert(seller);
//			System.out.println("Usuario inserido com sucesso! Deseja Continuar ?");
//			System.out.println("Id New Seller inserted :"+seller.getId());
//			System.out.println("Sair(S)- Continuar(C)");
//			op = sc.next().toUpperCase().charAt(0);
//			sc.nextLine();// <-
//		
//			if (op != 'C' && op != 'S') {
//				System.out.println("Opção Inválida! Digite novamente.");
//			}					
//		} while (op == 'C' && op != 'S');
//		System.out.println("Fim !!!");
//		System.out.println();
		
//		System.out.println("\n=== TEST 5: Seller Update ===");
//		System.out.println("Digite o Id do Seller a ser alterado");
//		int id = sc.nextInt();
//		seller = sellerDao.findById(id);		
//		seller.setName("Presidente Kennedy");
//		sellerDao.update(seller);		
//		System.out.println("Seller ID: "+ id + " Alterado");
//		System.out.println();
////////////////////////////////////////////////////////////////////////
		System.out.println("\n=== TEST 6: Seller Update ===");
		System.out.println("Digite o Id do Seller a ser Deletado");
		int idDelete = sc.nextInt();				
		sellerDao.deleteById(idDelete);
		sc.close();
	}

}
