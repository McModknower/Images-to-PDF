package swati4star.createpdf.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;
import java.util.List;

import swati4star.createpdf.interfaces.enhancers.DocumentEnhancer;
import swati4star.createpdf.interfaces.enhancers.Enhancer;
import swati4star.createpdf.interfaces.enhancers.ImageEnhancer;
import swati4star.createpdf.interfaces.enhancers.PdfWriterEnhancer;

public class EnhancerBundle {

    private ArrayList<DocumentEnhancer> documentEnhancers;
    private ArrayList<PdfWriterEnhancer> writerEnhancers;
    private ArrayList<ImageEnhancer> imageEnhancers;
    private ArrayList<Enhancer> otherEnhancers;

    public EnhancerBundle() {
        documentEnhancers = new ArrayList<>();
        writerEnhancers = new ArrayList<>();
        imageEnhancers = new ArrayList<>();
        otherEnhancers = new ArrayList<>();
    }

    public void runDocumentEnhancers(Document doc) {
        for(DocumentEnhancer de: documentEnhancers)
            de.enhanceDocument(doc);
    }

    public void runPdfWriterEnhancers(PdfWriter writer) {
        for(PdfWriterEnhancer pe: writerEnhancers)
            pe.enhancePdfWriter(writer);
    }

    public void runImageEnhancers(Image image) {
        for(ImageEnhancer ie: imageEnhancers)
            ie.enhanceImage(image);
    }

    public void addEnhancer(Enhancer e) {
        if(e instanceof DocumentEnhancer)
            documentEnhancers.add((DocumentEnhancer) e);
        else if(e instanceof PdfWriterEnhancer)
            writerEnhancers.add((PdfWriterEnhancer) e);
        else if(e instanceof ImageEnhancer)
            imageEnhancers.add((ImageEnhancer) e);
        else
            otherEnhancers.add(e);

    }

    public List<Enhancer> getEnhancers() {
        List<Enhancer> result = new ArrayList<>();
        result.addAll(documentEnhancers);
        result.addAll(writerEnhancers);
        result.addAll(imageEnhancers);
        result.addAll(otherEnhancers);
        return result;
    }

}
