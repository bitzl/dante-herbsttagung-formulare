import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class ServletExample extends HttpServlet {

	private static final long serialVersionUID = 7813967103389397605L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			super.doGet(req, resp);
			PdfReader reader = new PdfReader("beispiele/tex/minibogen.pdf");
			PdfStamper stamper = new PdfStamper(reader, resp.getOutputStream());
			AcroFields form = stamper.getAcroFields();
			form.setField("Name", "Hans Wurst");
			stamper.close();
			reader.close();
		} catch (DocumentException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
}
