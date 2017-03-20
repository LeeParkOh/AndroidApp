package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by Woonho on 2017. 3. 20..
 */

public class TabListRes {

    protected static final String TAG = "TabListRes";

    @SerializedName("list")
    public ArrayList<list> list = new ArrayList<>();

    public class list {
        @SerializedName("userYn")
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
