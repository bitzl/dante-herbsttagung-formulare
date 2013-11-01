import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class ExamplePlus {

	public static void fill(AcroFields form) throws IOException, DocumentException {
		form.setField("Name", "Hans Wurst");
	}
	
	public static void grade(AcroFields form) throws IOException, DocumentException {
		String field = "coolness";
		String[] states = form.getAppearanceStates(field);
		int evaluation = (int) (Math.random() * states.length);
		form.setField(field, states[evaluation]);
	}

	public static void process(String source, String dest) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(source);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		AcroFields form = stamper.getAcroFields();
		fill(form);
		grade(form);
		stamper.close();
		reader.close();
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
		process("beispiele/tex/minibogen-plus.pdf", "beispiele/tex/graded.pdf");
	}

}
