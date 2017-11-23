package com.tsy.base.api.error

/**
 * Created by jay on 17/8/1.
 */

class DefaultErrorBundle(private val exception: Exception?) : ErrorBundle {

    override fun getException(): Exception? {
        return exception
    }

    override fun getErrorMessage(): String {
        return if (exception != null) this.exception.message!! else DEFAULT_ERROR_MSG
    }

    companion object {

        private val DEFAULT_ERROR_MSG = "Unknow error"
    }
}
