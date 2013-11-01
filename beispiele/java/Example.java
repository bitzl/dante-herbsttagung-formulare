import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class Example {

	public static void print_fields(AcroFields form) {
		System.out.println("Fields:");
		Map<String, Item> fields = form.getFields();
		for (String fieldname : fields.keySet()) {
			System.out.println(fieldname);
		}
	}

	public static void scanFields(String path) throws IOException {
	    PdfReader pdfReader = new PdfReader(path);
	    AcroFields acroFields = pdfReader.getAcroFields();
	    Map<String,AcroFields.Item> fields = acroFields.getFields();
	    Set<Entry<String, Item>> entrySet = fields.entrySet();
	    for (Entry<String, Item> entry : entrySet) {
	        String key = entry.getKey();
	        System.out.print("'");
	        System.out.print(key);
	        System.out.println("'");
	    }
	}
	
	public static void fill(String path) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(path);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("abc.pdf"));
		AcroFields form = stamper.getAcroFields();
		print_fields(form);
		form.setField("Dings", "Hans Wurst");
		stamper.close();
		reader.close();
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
		scanFields("beispiele/tex/minibogen.pdf");
		fill("beispiele/tex/minibogen.pdf");
	}
}
