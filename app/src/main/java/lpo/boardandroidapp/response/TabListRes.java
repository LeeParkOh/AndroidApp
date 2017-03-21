package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by Woonho on 2017. 3. 20..
 */

public class TabListRes {

    protected static final String TAG = "TabListRes";

    @SerializedName("ResultCode")
    public String resultcode;

    @SerializedName("ResultMsg")
    public String resultmsg;

    @SerializedName("cmnCdList")
    public ArrayList<cmnCdList> cmnCdList = new ArrayList<>();

    public class cmnCdList {
        @SerializedName("useYn")
        public String useYn;
        @SerializedName("dtlCd")
        public String dtlCd;
        @SerializedName("lstChgDt")
        public String lstChgDt;
        @SerializedName("dtlCdNm")
        public String dtlCdNm;
        @SerializedName("grpCd")
        public String grpCd;
        @SerializedName("regDt")
        public String regDt;
    }
}
