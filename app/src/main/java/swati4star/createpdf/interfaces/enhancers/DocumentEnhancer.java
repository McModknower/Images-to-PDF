package swati4star.createpdf.interfaces.enhancers;

import com.itextpdf.text.Document;

public interface DocumentEnhancer extends Enhancer {

    void enhanceDocument(Document doc);

}
