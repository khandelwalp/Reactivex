package com.example.reactive;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaBufferMasterList {

    /*
        Observable.fromIterable
        buffer
    */

    private List<ArrayList<Integer>> mMasterList;
    RxJavaBufferMasterList() {
        mMasterList = new ArrayList<ArrayList<Integer>>();
    }

    public void execute() {
        Observable.fromIterable(getList()).buffer(3).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("Reactive","onSubscribe");
            }

            @Override
            public void onNext(@NonNull List<Integer> integers) {
                Log.i("Reactive","onNext: "+integers);
                mMasterList.add((ArrayList<Integer>) integers);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("Reactive","onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("Reactive","onComplete: "+mMasterList);
            }
        });
    }

    private List<Integer> getList() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return Arrays.asList(intArray);
    }
}
