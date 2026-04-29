package org.nexacorp.ai.ingestion.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PDFIngestionService {

    private static final Logger log = LoggerFactory.getLogger(PDFIngestionService.class);
    private static final String PDF_DIRECTORY="data/pdfs";

    public void ingestPDFs() throws Exception {
        File[] pdfFiles = new File(PDF_DIRECTORY).listFiles();

        for(File pdfFile: pdfFiles){
            ingestSingePDF(pdfFile);
        }
    }

    private void ingestSingePDF(File pdfFile) throws IOException {
        log.info("Ingesting PDF: {}", pdfFile.getName());

        try(PDDocument document = PDDocument.load(pdfFile)){
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            log.info("--- Extracted Text({}) ----", pdfFile.getName());
            log.info(text);
        }
    }
}
