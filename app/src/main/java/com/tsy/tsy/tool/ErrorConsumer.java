package com.tsy.tsy.tool;

import android.content.Context;

import com.tsy.base.api.error.DefaultErrorBundle;
import com.tsy.base.api.error.ErrorBundle;
import com.tsy.base.api.error.ErrorMessageFactory;
import com.tsy.base.api.error.exception.ResponseException;
import com.tsy.base.tool.Toasts;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by jay on 17/8/1.
 */

public class ErrorConsumer implements Consumer<Throwable> {


    private Context context;

    public ErrorConsumer(Context context) {
        this.context = context;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        if (!handleCommonResponseError((Exception) throwable)) {
            if (throwable != null && throwable.getMessage() != null) {
                Timber.w(throwable.getMessage());
            }
            showErrorMessage(new DefaultErrorBundle((Exception) throwable));
        }
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(context, errorBundle.getException());
        showErrorMessage(errorMessage);
    }

    private void showErrorMessage(String errorMessage) {
        Toasts.showLong(errorMessage);
    }

    private boolean handleCommonResponseError(Exception exception) {
        boolean handled = false;
        if (exception instanceof ResponseException) {
            ResponseException responseException = (ResponseException) exception;
            switch (responseException.getStatusCode()) {
                case ResponseException.STATUS_CODE_NEED_LOGIN:
                    handled = true;
//                    app.clearAppToken();
//                    app.user = null;
//                    LoginActivity.launch(context);
                    break;

                case ResponseException.STATUS_CODE_NEED_BIND_MOBILE:
                    handled = true;
//                    Notice.confirm(context, responseException.getErrorMessage(), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            MobileBindActivity.launch(context);
//                        }
//                    }, null);
                    break;
            }
        }
        return handled;
    }

}
