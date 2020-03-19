package com.example.reactive;

import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

class DocumentCreator{

    private final String mType;

    DocumentCreator(String fileType) {
        mType = fileType;
    }

    Document createDocument() {
        try {
            Log.i("Reactive","createPDF: "+Thread.currentThread().getName());
            Document document = new Document(mType);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 + 1));
            return document;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

