import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;


public class CheckboxTools {

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
	
}
