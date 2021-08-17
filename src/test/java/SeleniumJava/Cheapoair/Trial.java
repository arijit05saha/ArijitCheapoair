package SeleniumJava.Cheapoair;

import java.io.IOException;

import org.testng.annotations.Test;

import resources.Base;
import resources.Utilities;

public class Trial extends Base{
	
	@Test
	public void readData() throws IOException, InterruptedException{
		
		System.out.println(System.getProperty("user.dir"));
		
		System.out.println(prop.getProperty("browser"));
	
		String testInputFileName = prop.getProperty("url");
		String testInputFile = System.getProperty("user.dir") + "\\" + testInputFileName;
		
		System.out.println("testInputFile");
		
		Utilities ut = new Utilities();
		ut.readExcel(testInputFile);
	}
}
