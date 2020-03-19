package com.example.reactive;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class DocumentGenerator implements Callable<Document> {

    private final String mType;

    public DocumentGenerator(String fileType) {
        mType = fileType;
    }

    @Override
    public Document call() throws Exception {
        Log.i("Reactive","createPDF: "+Thread.currentThread().getName());
        Document document = new Document(mType);
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 + 1));
        return document;
    }
}

