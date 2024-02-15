package application;

public class main {

	public static void main(String[] args) {
		Excel obj = new Excel();
		
		obj.WriteExcel("Victims", 2, 1, "Hey");
		System.out.print(obj.ReadExcel("Victims", 1, 3));
		//t
	}
}