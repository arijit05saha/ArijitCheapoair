package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public void readExcel(String file) throws IOException {

		FileInputStream fs = new FileInputStream(file);

		// Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
//		Row row = sheet.getRow(rowNo);
//		Cell cell = row.getCell(colNo);

		// Create a formatter, do this once
		DataFormatter formatter = new DataFormatter(Locale.US);

		// get all rows in the sheet excepct the first (heading)
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 1; i <= rowCount; i++) {
			// get cell count in a row
			int cellcount = sheet.getRow(i).getLastCellNum();

			// iterate over each cell to print its value
			System.out.println("\nRow" + i + " data is :");

			for (int j = 0; j < cellcount; j++) {
				String data = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				System.out.println(data + " ");
			}
			System.out.println("\n");
		}
	}

}
