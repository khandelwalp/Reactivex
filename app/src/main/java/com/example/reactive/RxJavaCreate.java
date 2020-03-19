package com.example.reactive;

import android.util.Log;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaCreate {

    /*
        Observable.create
        subscribe(Observer)
    */

    private void execute() {
        List<String> files = Common.getDocumentList();
        Observable<Document> observable = Common.createObservable(files);
        Log.i("Reactive", "subscribe");
        observable.subscribe(mObserver);
    }

    private Observer<Document> mObserver = new Observer<Document>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.i("Reactive","onSubscribe");
        }

        @Override
        public void onNext(Document document) {
            Log.i("Reactive","onNext: "+Thread.currentThread().getName());
            Log.i("Reactive","onNext: "+document.type);
        }

        @Override
        public void onError(Throwable e) {
            Log.i("Reactive","onError: "+e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.i("Reactive","onComplete");
        }
    };
}
