package swati4star.createpdf.interfaces.enhancers;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public interface PdfWriterEnhancer extends Enhancer {

    void enhancePdfWriter(PdfWriter writer) throws DocumentException;

}
