package org.nexacorp.ai.ingestion.db;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.ingestion.pdf.PDFIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DatabaseIngestionService {

    private static final Logger log = LoggerFactory.getLogger(DatabaseIngestionService.class);
    private final JdbcTemplate jdbcTemplate;

    public void ingestFAQs() throws Exception {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList(
                        "SELECT id, question, answer, department, visibility FROM faqs"
                );

        for(Map<String, Object> row: rows){
            log.info("--- FAQs ---");
            log.info("Q: {}", row.get("question"));
            log.info("A: {}", row.get("answer"));
        }
    }

    public void ingestReleaseNotes() throws Exception {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList(
                        "SELECT id, version, summary, details, release_date FROM release_notes"
                );

        for(Map<String, Object> row: rows){
            log.info("--- Release Notes ---");
            log.info("Version: {}", row.get("version"));
            log.info("Description: {}", row.get("summary"));
        }
    }

    public void ingestAnnouncements() throws Exception {
        List<Map<String, Object>> rows =
                jdbcTemplate.queryForList(
                        "SELECT id, subject, body, category, effective_from, effective_to, source_type FROM announcements"
                );

        for(Map<String, Object> row: rows){
            log.info("--- Announcements ---");
            log.info("Title: {}", row.get("subject"));
        }
    }

}
