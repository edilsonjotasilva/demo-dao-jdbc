package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	Department obj = new Department(1, "books");
	System.out.println(obj);
//	Date data = new Date();
	Seller seller = new Seller(21, "Edilson", "edilson@gmail.com", new Date(), 3500.0, obj);
	System.out.println(seller);
	}

}
