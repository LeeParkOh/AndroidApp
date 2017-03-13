package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Woonho on 2017. 3. 12..
 */

public class BoardMainRes {

    protected static final String TAG = "BoardMainRes";

    public ArrayList<BoardMainRes> getItems(ArrayList<BoardMainRes> list) {
        if (list == null) {
            return null;
        }
        return list;
    }

    @SerializedName("total")
    public String total;

    public String getTotal() {
        return total;
    }

    @SerializedName("list")
    public ArrayList<list> list = new ArrayList<>();

    public ArrayList<BoardMainRes.list> getList() {
        return list;
    }

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

        public String getTitle() {
            return title;
        }

        public String getIdx() {
            return idx;
        }

        public String getRnum() {
            return rnum;
        }

        public String getHit_cnt() {
            return hit_cnt;
        }

        public String getCrea_dtm() {
            return crea_dtm;
        }
    }
}
