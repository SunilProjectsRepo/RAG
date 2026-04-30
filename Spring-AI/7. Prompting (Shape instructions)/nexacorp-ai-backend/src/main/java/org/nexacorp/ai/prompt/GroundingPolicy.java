package org.nexacorp.ai.prompt;

import org.nexacorp.ai.prompt.model.PromptContext;

public class GroundingPolicy {
    public String groundingRules(PromptContext context){
        //Empty context means system doesn't know the answer
        if(context == null || context.getContextText().isBlank()){
            return """
                        You do not have sufficient information to answer the question.
                        Respond clearly that you do not know.
                        Do not guess or infer.
                        """;
        }
        return """
                Answer the user's question using ONLY the provided context.
                If the answer is not present in the context, say that you do not know.
                Do not use prior knowledge.
                """;
    }
}
