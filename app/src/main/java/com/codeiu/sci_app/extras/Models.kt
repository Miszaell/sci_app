package com.codeiu.sci_app.extras
import com.google.gson.annotations.SerializedName

class Models {
    data class Data(
        @SerializedName("token")
        var token: String? = null,
        @SerializedName("name")
        var name: String? = null
    )

    data class LoginResponse (
        @SerializedName("success")
        var success: Boolean? = null,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("data")
        var data: Data? = null,
    )
}