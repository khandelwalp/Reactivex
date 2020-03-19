package com.example.reactive;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaBuffer {

    /*
        Observable.fromIterable
        buffer
    */

    public void bufferExample() {
        Observable.fromIterable(getList()).buffer(3).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("Reactive","onSubscribe");
            }

            @Override
            public void onNext(@NonNull List<Integer> integers) {
                Log.i("Reactive","onNext: "+integers);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("Reactive","onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("Reactive","onComplete");
            }
        });
    }

    private List<Integer> getList() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return Arrays.asList(intArray);
    }
}
