package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 메인 게시판 목록 가져오는 객체
 * Created by Woonho on 2017. 3. 12..
 */

public class BoardMainRes {

    protected static final String TAG = "BoardMainRes";

    @SerializedName("total")
    public String total;

    @SerializedName("list")
    public ArrayList<list> list = new ArrayList<>();

    public class list {
        @SerializedName("IDX")
        public String idx;
        @SerializedName("RNUM")
        public String rnum;
        @SerializedName("HIT_CNT")
        public String hit_cnt;
        @SerializedName("CREA_DTM")
        public String crea_dtm;
        @SerializedName("TITLE")
        public String title;
    }
}
