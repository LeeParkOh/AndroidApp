package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Woonho on 2017. 3. 12..
 */

public class BoardMainRes {

    @SerializedName("total")
    public String total;

    public String getTotal() {
        return total;
    }

    public ArrayList<list> list = new ArrayList<>();

    public ArrayList<BoardMainRes.list> getList() {
        return list;
    }

    public class list {
        @SerializedName("idx")
        public String idx;
        @SerializedName("rnum")
        public String rnum;
        @SerializedName("hit_cnt")
        public String hit_cnt;
        @SerializedName("crea_dtm")
        public String crea_dtm;
        @SerializedName("title")
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
