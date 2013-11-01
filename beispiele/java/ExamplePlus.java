import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.AcroFields.Item;


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

	public static void printCheckboxValues(AcroFields form) throws IOException, DocumentException {
		FieldPrinter printer = new FieldPrinter(form);
		printer.printFields();
		for (int i = 0; i < 5; i++) {
			printer.print("C" + i);
		}
	}
	
	public static void printCheckboxChoices(AcroFields form) throws IOException, DocumentException {
		FieldPrinter printer = new FieldPrinter(form);
		for (int i = 0; i < 5; i++) {
			printer.printChoices("C" + i);
		}
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

	public static void printFields(AcroFields form) {
		System.out.println("Fields:");
		Map<String, Item> fields = form.getFields();
		for (String fieldname : fields.keySet()) {
			System.out.println(fieldname);
		}
	}
	
	public static void printAllStates(AcroFields form) {
		Map<String, Item> fields = form.getFields();
		for (String fieldname : fields.keySet()) {
			System.out.println(fieldname);
			String[] states = form.getAppearanceStates(fieldname);
			if (states.length > 0) {
				for (String state : states) {
					System.out.println("\t" + state);
				}
			}
			else {
				System.out.println("-- None --");
			}
		}
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
		process("beispiele/tex/minibogen-plus.pdf", "beispiele/tex/graded.pdf");
	}

}
