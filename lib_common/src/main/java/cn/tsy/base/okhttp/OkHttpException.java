package cn.tsy.base.okhttp;

/**
 * 异常类，返回错误码和错误信息到业务层
 * Created by Jocerly on 2018/4/10.
 */
public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    private int ecode; //错误码
    private Object emsg; //错误消息

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
