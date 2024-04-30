package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	/**
	 * Author: Aidan Fox
	 * Date: 4/16/24
	 * Description: Method to get a Cell object, given the Sheet name, row and column numbers. Returns null if the cell is empty or does not exist.
	 * @param SheetName
	 * @param rNum
	 * @param cNum
	 * @return Cell
	 */
	public Cell getCell(String SheetName, int rNum, int cNum){
		Cell c = null;
		try {
			FileInputStream fis = new FileInputStream("The Phantom Inn.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			if(r == null) {
				return null;
			}
			c = r.getCell(cNum);
		} catch (Exception e) {
			System.out.println("Exception:" + e + " in getCell!");
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/16/24
	 * Description: Method for creating a Cell at a given row and column number for the sheet given by SheetName. The string value is inserted at the appropriate location in the sheet.
	 * @param SheetName
	 * @param rNum
	 * @param cNum
	 * @param value
	 */
	public void CreateCell(String SheetName, int rNum, int cNum, String value) {
		try {
			FileInputStream fis = new FileInputStream("The Phantom Inn.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			if(r == null ) {
				r = s.createRow(rNum);
			}
			Cell cell = r.createCell(cNum);
		    cell.setCellValue(value);
		    FileOutputStream fos = new FileOutputStream("The Phantom Inn.xlsx");
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception:" + e + " in CreateCell!");
			e.printStackTrace();
		}
	}
	
	/** Author Aidan Fox
	 * Date: 4/16/24
	 * Description: Deletes a Cell in the Sheet given by SheetName, at row and column numbers.
	 * @param SheetName
	 * @param rNum
	 * @param cNum
	 */
	public void DeleteCell(String SheetName, int rNum, int cNum) {
		try {
			FileInputStream fis = new FileInputStream("The Phantom Inn.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			Cell cell = r.getCell(cNum);
			if(cell != null) {
				cell.setCellValue("");
			}
			
			FileOutputStream fos = new FileOutputStream("The Phantom Inn.xlsx");
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception:" + e + " in DeleteCell!");
			e.printStackTrace();
		}
	}
	
	public void DeleteRow(String SheetName, int rNum) {
		try {
			FileInputStream fis = new FileInputStream("The Phantom Inn.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rNum);
			s.removeRow(r);
			if(s.getRow(rNum + 1) != null) {				
				s.shiftRows(rNum + 1, s.getLastRowNum(), -1);
			}
			FileOutputStream fos = new FileOutputStream("The Phantom Inn.xlsx");
			wb.write(fos);
			fos.close();
		} catch(Exception e) {
			System.out.println("Exception:" + e + " in DeleteRow!");
			e.printStackTrace();
		}
	}
}