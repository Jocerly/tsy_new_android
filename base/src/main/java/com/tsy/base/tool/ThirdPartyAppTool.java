package com.tsy.base.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by jay on 17/8/11.
 */

public final class ThirdPartyAppTool {

    private ThirdPartyAppTool() {
    }

    public static Single<String> getThirdPartyApp(final Context context) {
        return Flowable.create(new FlowableOnSubscribe<PackageInfo>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<PackageInfo> e) throws Exception {

                List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
                for (PackageInfo info : packageInfos) {
                    e.onNext(info);
                }
                e.onComplete();

            }
        }, BackpressureStrategy.BUFFER)
                .filter(new Predicate<PackageInfo>() {
                    @Override
                    public boolean test(@NonNull PackageInfo packageInfo) throws Exception {
                        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
                    }
                })
                .toList()
                .map(new Function<List<PackageInfo>, String>() {
                    @Override
                    public String apply(@NonNull List<PackageInfo> packageInfos) throws Exception {
                        StringBuilder sb = new StringBuilder();
                        for (PackageInfo info : packageInfos) {
                            sb.append(info.applicationInfo.loadLabel(context.getPackageManager()));
                            sb.append(";");
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                });
    }

}
