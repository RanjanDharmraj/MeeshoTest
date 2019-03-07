package com.dharmraj.meeshotest.ui.main

sealed class State {
    object Loading : State()
    class Success : State()
    class ServerError(val message: String) : State()
    class NetworkConnectionError(val message: String) : State()
    class ValidationError(val validationErrorType: ValidationErrorType) : State()
}

enum class ValidationErrorType {
    ALL_FIELDS_BLANK,
    OWNER_NAME_BLANK,
    REPO_NAME_BLANK,
}