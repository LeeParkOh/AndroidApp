package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * 메인 상위 탭 리스트 받아오는 객체
 * Created by Woonho on 2017. 3. 20..
 */

public class TabListRes {

    protected static final String TAG = "TabListRes";

    @SerializedName("ResultCode")
    public String resultCode;

    @SerializedName("ResultMsg")
    public String resultMsg;

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
