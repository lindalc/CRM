package com.example.os.crm.presenter;

import com.example.os.crm.model.Res;
import com.example.os.crm.model.UserDetail;
import com.example.os.crm.network.Net;
import com.example.os.crm.presenter.base.BasePresenterImp;
import com.example.os.crm.ui.view.LoginView;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenterImp<LoginView> {

    public void Login(String phone,String pwd){
        Subscription subscription = Net.getService()
                .Login(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Res<UserDetail>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Res<UserDetail> res) {

                    }
                });

        addSubscription(subscription);
    }

}
