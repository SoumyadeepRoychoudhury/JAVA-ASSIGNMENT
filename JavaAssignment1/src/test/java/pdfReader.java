

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfReader {
	public static Properties prop;
	public static void main(String[] args) throws Exception {
		prop = new Properties();
		try {
			FileInputStream data = new FileInputStream("./src/main/java/com/config/keyword.properties");
			prop.load(data);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!!!");
		} catch (IOException e) {
			System.out.println("Unable to read the file!!!");
		}
		
		
		File file = new File("../JavaAssignment1/Resources/Resume.pdf");

	
		PDDocument pdf = Loader.loadPDF(file);
//		System.out.println(pdf.getPages().getCount());
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String docText = pdfStripper.getText(pdf);
		int noOfKeywords = prop.size();
//		System.out.println(noOfKeywords);
		System.out.println(docText.toLowerCase());
		@SuppressWarnings("unused")
		int percentageMatch = 0;
		for (int i = 0; i < noOfKeywords; i++) {
			String key = "keyword" + Integer.toString(i + 1);
			if (docText.toLowerCase().contains(prop.getProperty(key))) {
				percentageMatch += (100 / noOfKeywords);
			} 
		}
		System.out.println("Percentage match = " + percentageMatch);

		pdf.close();

	}

}