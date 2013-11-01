import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.AcroFields.Item;

public class FieldPrinter {

	private AcroFields fields;
	
	public FieldPrinter(AcroFields fields) {
		this.fields = fields;
	}
	
	public void print(String field) {
		System.out.print(field);
		System.out.print(": '");
		System.out.print(fields.getField(field));
		System.out.println("'");
	}
	
	public void printChoices(String field) {
		System.out.println(field);
		String[] states = fields.getAppearanceStates(field);
		for (String state : states) {
			System.out.print("\t");
			System.out.println(state);
		}
	}

	public void printFields() {
		Map<String, Item> fieldMap = fields.getFields();
		for (String field : fieldMap.keySet()) {
			System.out.println(field);
		}
	}

	public void printAllStates() {
		Map<String, Item> fieldsMap = fields.getFields();
		for (String fieldname : fieldsMap.keySet()) {
			System.out.println(fieldname);
			String[] states = fields.getAppearanceStates(fieldname);
			if (states.length > 0) {
				for (String state : states) {
					System.out.println("\t" + state);
				}
			}
			else {
				System.out.println("\t-- None --");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String source = "beispiele/tex/minibogen-plus.pdf";
		PdfReader reader = new PdfReader(source);
		AcroFields fields = reader.getAcroFields();

		FieldPrinter printer = new FieldPrinter(fields);
		printer.printAllStates();
		
		reader.close();
	}
}
