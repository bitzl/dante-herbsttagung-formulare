import java.util.Map;

import com.itextpdf.text.pdf.AcroFields;
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
}
