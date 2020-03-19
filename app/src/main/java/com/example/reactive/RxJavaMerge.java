package com.example.reactive;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaMerge {
    /*
        Observable.merge
        Observable.fromCallable
    */

    private void mergeObservable() {
        Observable<Document> observable = Observable.merge(getObservable()).observeOn(AndroidSchedulers.mainThread());
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

    /**
     * cold observables
     * Observable.fromCallable
     */
    private List<Observable<Document>> getObservable() {
        List<Observable<Document>> observableList = new ArrayList<>();
        final List<String> docList = getDocumentList();
        for(String docName: docList) {
            observableList.add(Observable.fromCallable(new DocumentGenerator(docName)).subscribeOn(Schedulers.io()));
        }
        return observableList;
    }

    private List<String> getDocumentList() {
        List<String> list = new ArrayList<>();
        list.add("PRESCRIPTION");
        list.add("REPORT");
        list.add("BILL");
        return list;
    }
}
