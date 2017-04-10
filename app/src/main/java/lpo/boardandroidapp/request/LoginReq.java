package lpo.boardandroidapp.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leewoonho on 2017. 4. 10..
 */

public class LoginReq {

    protected static final String TAG = "LoginReq";

    @SerializedName("ResultCode")
    public String resultCode;

    @SerializedName("token")
    public String token;

    @SerializedName("ResultMsg")
    public String resultMsg;

}
