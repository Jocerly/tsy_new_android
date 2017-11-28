package com.tsy.base.api.error;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tsy.base.R;
import com.tsy.base.api.error.exception.NetworkConnectionException;
import com.tsy.base.api.error.exception.NotFoundException;
import com.tsy.base.api.error.exception.ResponseException;

import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by jay on 17/8/1.
 */

public class ErrorMessageFactory {

    private ErrorMessageFactory() {

    }

    @SuppressLint("LogNotTimber")
    public static String create(Context context, Exception exception) {
        if (!TextUtils.isEmpty(exception.getMessage())) {
            Log.e("1", exception.getMessage());
        }

        String message = context.getString(R.string.exception_message_genric);

        if (exception instanceof NetworkConnectionException || exception instanceof UnknownHostException) {
            message = context.getString(R.string.exception_message_connection);
        } else if (exception instanceof NotFoundException) {
            message = context.getString(R.string.exception_message_not_found);
        } else if (exception instanceof ResponseException) {
            message = exception.getMessage();
        } else if (exception instanceof HttpException) {
            message = exception.getMessage();
        }
        return message;
    }


}
