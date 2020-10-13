package swati4star.createpdf.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;
import java.util.List;

import swati4star.createpdf.interfaces.enhancers.DocumentEnhancer;
import swati4star.createpdf.interfaces.enhancers.Enhancer;
import swati4star.createpdf.interfaces.enhancers.ImageEnhancer;
import swati4star.createpdf.interfaces.enhancers.PdfWriterEnhancer;
import swati4star.createpdf.model.EnhancementOptionsEntity;

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

    public void runPdfWriterEnhancers(PdfWriter writer) throws DocumentException {
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

    /**
     * Get all Enhancers stored in this bundle
     */
    public List<Enhancer> getEnhancers() {
        List<Enhancer> result = new ArrayList<>();
        result.addAll(mOtherEnhancers);
        result.addAll(mDocumentEnhancers);
        result.addAll(mWriterEnhancers);
        result.addAll(mImageEnhancers);
        return result;
    }

    /**
     * Get the EnhancementOptionsEntites for all Enhancers in the same order as getEnhancers()
     */
    public List<EnhancementOptionsEntity> getEnhancementOptionsEntities() {
        List<Enhancer> enhancers = getEnhancers();
        List<EnhancementOptionsEntity> result = new ArrayList<>();
        for (Enhancer e:enhancers)
            result.add(e.getEnhancementOptionsEntity());
        return result;
    }

    /**
     * Reset the stored values of the enhancers back to the default
     */
    public void resetValues() {
        for (Enhancer e: getEnhancers())
            e.resetValues();
    }
}
