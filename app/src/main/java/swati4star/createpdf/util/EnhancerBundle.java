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

    private ArrayList<DocumentEnhancer> mDocumentEnhancers;
    private ArrayList<PdfWriterEnhancer> mWriterEnhancers;
    private ArrayList<ImageEnhancer> mImageEnhancers;
    private ArrayList<Enhancer> mOtherEnhancers;

    public EnhancerBundle() {
        mDocumentEnhancers = new ArrayList<>();
        mWriterEnhancers = new ArrayList<>();
        mImageEnhancers = new ArrayList<>();
        mOtherEnhancers = new ArrayList<>();
    }

    public void runDocumentEnhancers(Document doc) {
        for (DocumentEnhancer de: mDocumentEnhancers)
            de.enhanceDocument(doc);
    }

    public void runPdfWriterEnhancers(PdfWriter writer) {
        for (PdfWriterEnhancer pe: mWriterEnhancers)
            pe.enhancePdfWriter(writer);
    }

    public void runImageEnhancers(Image image) {
        for (ImageEnhancer ie: mImageEnhancers)
            ie.enhanceImage(image);
    }

    public void addEnhancer(Enhancer e) {
        if (e instanceof DocumentEnhancer)
            mDocumentEnhancers.add((DocumentEnhancer) e);
        else if (e instanceof PdfWriterEnhancer)
            mWriterEnhancers.add((PdfWriterEnhancer) e);
        else if (e instanceof ImageEnhancer)
            mImageEnhancers.add((ImageEnhancer) e);
        else
            mOtherEnhancers.add(e);

    }

    public List<Enhancer> getEnhancers() {
        List<Enhancer> result = new ArrayList<>();
        result.addAll(mDocumentEnhancers);
        result.addAll(mWriterEnhancers);
        result.addAll(mImageEnhancers);
        result.addAll(mOtherEnhancers);
        return result;
    }

}
