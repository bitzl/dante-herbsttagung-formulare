import java.io.FileOutputStream;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class Einstellungsvorschlag {

	public static void main(String[] args) {
		String source = "D:/git/date-formulare/beispiele/Einstellungsvorschlag-stud.-Hiwi-3.pdf";
		String dest = "D:/git/date-formulare/beispiele/e.pdf";
		PdfReader reader = new PdfReader(source);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		AcroFields form = stamper.getAcroFields();

		
		stamper.close();
		reader.close();
	}

}
