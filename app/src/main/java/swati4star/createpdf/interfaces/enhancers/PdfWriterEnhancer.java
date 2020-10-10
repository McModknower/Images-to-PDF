package swati4star.createpdf.interfaces.enhancers;

import com.itextpdf.text.pdf.PdfWriter;

public interface PdfWriterEnhancer extends Enhancer {

    void enhancePdfWriter(PdfWriter writer);

}
