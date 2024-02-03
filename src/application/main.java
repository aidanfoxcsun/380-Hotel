package application;

public class main {

	public static void main(String[] args) {
		Excel obj = new Excel();
		
		obj.WriteExcel("Sheet1", 1, 1, "test");
		System.out.print(obj.ReadExcel("Sheet1", 1, 1));
		//ff
	}
}