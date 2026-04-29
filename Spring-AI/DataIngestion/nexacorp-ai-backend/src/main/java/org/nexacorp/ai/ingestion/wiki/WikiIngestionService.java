package org.nexacorp.ai.ingestion.wiki;

import org.nexacorp.ai.ingestion.pdf.PDFIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class WikiIngestionService {

    private static final Logger log = LoggerFactory.getLogger(WikiIngestionService.class);
    private static final String WIKI_DIRECTORY="data/wiki";

    public void ingestWikiFiles() throws Exception {

        File[] markdownFiles = new File(WIKI_DIRECTORY).listFiles();

        for(File file: markdownFiles){
            ingestSingleFile(file);
        }
    }

    private void ingestSingleFile(File file) throws IOException {
        log.info("Ingesting wiki file: {}", file.getName());

        String content = Files.readString(file.toPath());

        log.info("--- Wiki Content({}) ----", file.getName());
        log.info(content);
    }

}
