package lpo.boardandroidapp.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Woonho on 2017. 3. 30..
 */

public class WriteReq {

    protected static final String TAG = "WriteReq";

    @SerializedName("ResultCode")
    public String resultCode;

    @SerializedName("ResultMsg")
    public String resultMsg;

}
