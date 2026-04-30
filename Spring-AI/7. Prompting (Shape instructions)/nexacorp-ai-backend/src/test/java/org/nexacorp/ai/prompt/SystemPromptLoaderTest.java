package org.nexacorp.ai.prompt;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.chunking.ChunkingOrchestratorTest;
import org.nexacorp.ai.prompt.model.SystemInstructions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemPromptLoaderTest {
    private static final Logger log = LoggerFactory.getLogger(SystemPromptLoaderTest.class);

    @Test
    void loadSystemPromptFromResource(){
        SystemInstructions instructions = new SystemPromptLoader().load();

        log.info("=== Loaded System Prompt ===");
        log.info("\n{}", instructions.getInstructions());
    }

}
