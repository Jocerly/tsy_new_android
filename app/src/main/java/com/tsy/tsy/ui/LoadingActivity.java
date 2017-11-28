package com.tsy.tsy.ui;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsy.base.tool.PreferencesLoader;
import com.tsy.tsy.R;
import com.tsy.tsy.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by froyomu on 2017/4/13.
 */
public class LoadingActivity extends BaseActivity {

    @BindView(R.id.hezi)
    ImageView hezi;

    @BindView(R.id.float1)
    LinearLayout float1;

    @BindView(R.id.float1_img)
    ImageView float1_img;

    @BindView(R.id.float2)
    TextView float2;

    @BindView(R.id.float3)
    LinearLayout float3;

    @BindView(R.id.float3_img)
    ImageView float3_img;

    @BindView(R.id.float4)
    LinearLayout float4;

    @BindView(R.id.float4_img)
    ImageView float4_img;

    @BindView(R.id.float5)
    LinearLayout float5;

    @BindView(R.id.float5_img)
    ImageView float5_img;

    @BindView(R.id.float6)
    TextView float6;

    @BindView(R.id.float7)
    TextView float7;

    @BindView(R.id.float8)
    LinearLayout float8;

    @BindView(R.id.float8_img)
    ImageView float8_img;

    private StringBuffer apks = new StringBuffer();

//    private List<Game> mGames = new ArrayList<>();

//    private CommonLoadingDialog dialog;

    private PreferencesLoader mPreferencesLoader;

    private int hezi_height;//盒子的高度

    private int hezi_width;//盒子的宽度

    private float hezi_x;//盒子x坐标

    private float hezi_y;//盒子y坐标

    private float start_x;//动画开始x坐标

    private float start_y;//动画开始y坐标

    private List<Animator> mAnimators;

    private String mThirdPartApps;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoadingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
//        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        if (!hasFocus) {
//            for (Animator animator : mAnimators) {
//                animator.cancel();
//            }
//            mAnimators.clear();
//        }
    }

    private void initView() {

//        getThirdPartApps();

        mAnimators = new ArrayList<>();
        mPreferencesLoader = new PreferencesLoader(this);

        // 获取顶部高度后，设置滚动监听
        ViewTreeObserver vto = hezi.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hezi.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                hezi_width = hezi.getWidth();
                hezi_height = hezi.getHeight();
                hezi_x = hezi.getX();
                hezi_y = hezi.getY();

                start_x = hezi_x + hezi_width / 2 - 70;
                start_y = hezi_y + hezi_height * 2 / 3 - 80;

//                getData();
            }
        });

    }

//    private void getData() {
////        long lastUpdateDate = mPreferencesLoader.getLong(PreferenceConstants.GAMES_FOR_USER_DATE);
//
//        Flowable.just(lastUpdateDate)
//                .subscribeOn(Schedulers.io())
//                .map(new Function<Long, Boolean>() {
//                    @Override
//                    public Boolean apply(@NonNull Long aLong) throws Exception {
//                        if (aLong == 0) {
//                            return false;
//                        } else {
//                            Date recordDate = new Date(aLong);
//                            Date nowDate = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
//                            return recordDate.after(nowDate);
//                        }
//                    }
//                })
//                .map(new Function<Boolean, List<Game>>() {
//                    @Override
//                    public List<Game> apply(@NonNull Boolean aBoolean) throws Exception {
//                        List<Game> games = new ArrayList<>();
//                        if (aBoolean) {
//                            Realm realm = Realm.getDefaultInstance();
//                            RealmResults<Game> results = realm.where(Game.class).findAll();
//                            games.addAll(realm.copyFromRealm(results));
//                            realm.close();
//
//                        }
//                        return games;
//                    }
//                })
//                .flatMap(new Function<List<Game>, Publisher<List<Game>>>() {
//                    @Override
//                    public Publisher<List<Game>> apply(@NonNull List<Game> games) throws Exception {
//                        if (games.isEmpty()) {
//                            return getRemoteData();
//                        } else {
//                            return Flowable.just(games);
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(this.<List<Game>>bindUntilEvent(ActivityEvent.DESTROY))
//                .doOnSubscribe(new Consumer<Subscription>() {
//                    @Override
//                    public void accept(Subscription subscription) throws Exception {
//                        dialog = new CommonLoadingDialog(LoadingActivity.this);
//                        dialog.show();
//                        dialog.setDialogMessage("正在分析数据..");
//                    }
//                })
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        if (dialog != null && dialog.isShowing()) {
//                            dialog.dismiss();
//                        }
//                    }
//                })
//                .subscribe(new Consumer<List<Game>>() {
//                    @Override
//                    public void accept(List<Game> games) throws Exception {
//                        setData(games);
//                    }
//                }, new ErrorConsumer(this));
//    }
//
//    private void getThirdPartApps() {
//        ThirdPartyAppTool.getThirdPartyApp(this)
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        mThirdPartApps = s;
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//    }
//
//    private Flowable getRemoteData() {
//        return Retrofits.api().recommendGames(mThirdPartApps, "8")
//                .map(new ServerResponseFunc<List<Game>>())
//                .map(new Function<List<Game>, List<Game>>() {
//                    @Override
//                    public List<Game> apply(@NonNull List<Game> games) throws Exception {
//                        mPreferencesLoader.saveLong(PreferenceConstants.GAMES_FOR_USER_DATE, new Date().getTime());
//                        Realm realm = Realm.getDefaultInstance();
//                        realm.beginTransaction();
//                        realm.copyToRealmOrUpdate(games);
//                        realm.commitTransaction();
//                        realm.close();
//                        return games;
//                    }
//                });
//
//    }
//
//
//    /**
//     * 显示动画
//     */
//    private void show() {
//        AnimatorSet animatorSet = new AnimatorSet();
//        mAnimators.add(animatorSet);
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(hezi, "alpha", 0f, 1f);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(hezi, "Y", DeviceParams.screenHeight(this), hezi_y);
//        animatorSet.setInterpolator(new DecelerateInterpolator());
//        animatorSet.play(alpha).with(translationY);//两个动画同时开始
//        animatorSet.setDuration(2000);
//        animatorSet.start();
//        hezi.setVisibility(View.VISIBLE);
//
//        //盒子显示出来之后继续
//        hezi.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startAnim(float1, 1000, 300);
//                startAnim(float2, 900, 400);
//                startAnim(float3, 800, 500);
//                startAnim(float4, 800, 600);
//                startAnim(float5, 700, 700);
//                startAnim(float6, 600, 800);
//                startAnim(float7, 500, 900);
//                startAnim(float8, 400, 1000);
//
//                startAnim2(float1, 2000);
//                startAnim2(float2, 3000);
//                startAnim2(float3, 3500);
//                startAnim2(float4, 4000);
//                startAnim2(float5, 2000);
//                startAnim2(float6, 3000);
//                startAnim2(float7, 3000);
//                startAnim2(float8, 2000);
//            }
//        }, 2000);
//    }
//
//    private void setData(List<Game> games) {
//        mGames.clear();
//        mGames.addAll(games);
//        int cout = mGames == null ? 0 : mGames.size();
//        for (int i = 0; i < (cout >= 8 ? 8 : cout); i++) {
//            Game game = mGames.get(i);
//            String url = URLConstant.URL_IMG_HOST + game.pic;
//            String name = game.name;
//            if (name.length() > 4) {
//                name = name.substring(0, 4);
//            }
//            if (name.length() > 2) {
//                String twoChar = name.substring(0, 2);
//                name = name.replace(twoChar, twoChar + "\n");
//            }
//            switch (i) {
//                case 0:
//                    float1.setTag(game);
//                    ImageLoader.instance.loadImage(float1_img, url);
//                    break;
//                case 1:
//                    float2.setTag(game);
//                    float2.setText(name);
//                    break;
//                case 2:
//                    float3.setTag(game);
//                    ImageLoader.instance.loadImage(float3_img, url);
//                    break;
//                case 3:
//                    float4.setTag(game);
//                    ImageLoader.instance.loadImage(float4_img, url);
//                    break;
//                case 4:
//                    float5.setTag(game);
//                    ImageLoader.instance.loadImage(float5_img, url);
//                    break;
//                case 5:
//                    float6.setTag(game);
//                    float6.setText(name);
//                    break;
//                case 6:
//                    float7.setTag(game);
//                    float7.setText(name);
//                    break;
//                case 7:
//                    float8.setTag(game);
//                    ImageLoader.instance.loadImage(float8_img, url);
//                    break;
//            }
//        }
//        show();
//
//    }
//
//    @OnClick(value = {R.id.to_home, R.id.to_buy, R.id.to_sell, R.id.float1, R.id.float2, R.id.float3, R.id.float4, R.id.float5, R.id.float6, R.id.float7, R.id.float8})
//    private void clickListener(View view) {
//        switch (view.getId()) {
//            case R.id.float1:
//            case R.id.float2:
//            case R.id.float3:
//            case R.id.float4:
//            case R.id.float5:
//            case R.id.float6:
//            case R.id.float7:
//            case R.id.float8:
//                Object object = view.getTag();
//                if (object != null && object instanceof Game) {
//                    Game game = (Game) view.getTag();
//                    if (game != null)
//                        SearchResultActivity.launch(this, game, true);
//                } else {
//                    MainActivity.launch(this);
//                }
//                break;
//            case R.id.to_home:
//                MainActivity.launch(this);
//                break;
//            case R.id.to_buy:
//                /**
//                 * 神策统计
//                 */
//                Retrofits.api().cmClick("/index", "", "0", "index_goto_buy", "")
//                        .compose(this.<HttpResult<Object>>bindToLifecycle())
//                        .subscribe(new Consumer<HttpResult<Object>>() {
//                            @Override
//                            public void accept(HttpResult<Object> objectHttpResult) throws Exception {
//
//                            }
//                        });
//                MainActivity.launch(this, MainActivity.SEARCH_CATE);
//                break;
//            case R.id.to_sell:
//                /**
//                 * 神策统计
//                 */
//                Retrofits.api().cmClick("/index", "", "0", "index_goto_sell", "")
//                        .compose(this.<HttpResult<Object>>bindToLifecycle())
//                        .subscribe(new Consumer<HttpResult<Object>>() {
//                            @Override
//                            public void accept(HttpResult<Object> objectHttpResult) throws Exception {
//
//                            }
//                        });
//                MainActivity.launch(this, MainActivity.PUBLISH_CATE);
//                break;
//
//        }
//        finish();
//    }
//
//    private void startAnim(final View view, final long duration, final long delay) {
//        hezi.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                AnimatorSet animatorSet = new AnimatorSet();//组合动画
//                mAnimators.add(animatorSet);
//                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "X", start_x, view.getX());
//                ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "Y", start_y, view.getY());
//                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
//
//                animatorSet.setInterpolator(new DecelerateInterpolator());
//                animatorSet.play(translationX).with(translationY).with(alpha);//两个动画同时开始
//                animatorSet.setDuration(duration);
//                animatorSet.start();
//                view.setVisibility(View.VISIBLE);
//            }
//        }, delay);
//
//    }
//
//    private void startAnim2(final View view, long delay) {
//        hezi.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ObjectAnimator translationY1 = ObjectAnimator.ofFloat(view, "Y", view.getY(), view.getY() + 40);
//                mAnimators.add(translationY1);
//                translationY1.setRepeatCount(ValueAnimator.INFINITE);
//                translationY1.setRepeatMode(ObjectAnimator.REVERSE);
//                translationY1.setDuration(2000);
//                translationY1.setInterpolator(new DecelerateInterpolator());
//                translationY1.start();
//            }
//        }, delay);
//    }

}
