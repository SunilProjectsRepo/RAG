package org.nexacorp.ai.ingestion;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.ingestion.db.DatabaseIngestionService;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.nexacorp.ai.ingestion.pdf.PDFIngestionService;
import org.nexacorp.ai.ingestion.wiki.WikiIngestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestionOrchestrator {
    private final PDFIngestionService pdfIngestionService;
    private final WikiIngestionService wikiIngestionService;
    private final DatabaseIngestionService databaseIngestionService;

    public List<IngestedDocument> ingestAll() throws Exception{
        List<IngestedDocument> docs = new ArrayList<>();
        docs.addAll(pdfIngestionService.ingestPDFs());
        docs.addAll(wikiIngestionService.ingestWikiFiles());
        docs.addAll(databaseIngestionService.ingestDatabaseContent());

        return docs;
    }
}
