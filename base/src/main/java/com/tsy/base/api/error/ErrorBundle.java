package com.tsy.base.api.error;

/**
 * Created by jay on 17/8/1.
 */

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();

}
