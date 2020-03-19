package com.example.reactive;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Common {

    public static Observable<Document> createObservable(List<String> aDocList) {
        return Observable.create(new ObservableOnSubscribe<Document>() {
            @Override
            public void subscribe(ObservableEmitter emitter) {

                try {
                    for(String type : aDocList) {
                        if(!emitter.isDisposed()) {
                            emitter.onNext(new DocumentCreator(type).createDocument());
                        }
                    }
                    if(!emitter.isDisposed()) {
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static List<String> getDocumentList() {
        List<String> list = new ArrayList<>();
        list.add("PRESCRIPTION");
        list.add("REPORT");
        list.add("BILL");
        return list;
    }
}
