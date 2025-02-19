package com.example.ragassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RagService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RagService.class);
    private final VectorStore vectorStore;

    public RagService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/docs/Smile Art Clinic.pdf")
    private Resource pdfDocument;


    @Override
    public void run(String... args) throws Exception {
        var pdfReader = new PagePdfDocumentReader(pdfDocument);
        TextSplitter textSplitter = new TokenTextSplitter();

        List<Document> fullText = pdfReader.get();
        List<Document> splitText = textSplitter.apply(fullText);

        log.info("Extracted {} chunks from PDF", splitText.size());

        vectorStore.accept(splitText);

        log.info("VectorStore successfully loaded");
    }
}
