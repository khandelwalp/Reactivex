package com.example.reactive;

import android.util.Log;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class RxJavaConsume {

    /*
        Observable.create
        subscribe(Consumer)
    */

    private void consumeObservable() {
        List<String> list = Common.getDocumentList();
        Observable<Document> observable = Common.createObservable(list);
        Log.i("Reactive", "subscribe");
        Disposable disposable = observable.subscribe(mConsumer);
    }

    private Consumer<Document> mConsumer = document -> {
        Log.i("Reactive","accept: "+Thread.currentThread().getName());
        Log.i("Reactive","accept: "+document.type);
    };
}
