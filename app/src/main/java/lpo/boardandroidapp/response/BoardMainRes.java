package lpo.boardandroidapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 메인 게시판 목록 가져오는 객체
 * Created by Woonho on 2017. 3. 12..
 */

public class BoardMainRes {

    protected static final String TAG = "BoardMainRes";

    @SerializedName("ResultCode")
    public String resultCode;

    @SerializedName("ResultMsg")
    public String resultMsg;

    @SerializedName("PoliticsList")
    public ArrayList<politicsList> list = new ArrayList<>();

    public class politicsList {
        @SerializedName("boardBestCnt")
        public String boardBestCnt;
        @SerializedName("boardBody1")
        public String boardBody1;
        @SerializedName("replyCnt")
        public String replyCnt;
        @SerializedName("boardTitle")
        public String boardTitle;
        @SerializedName("boardBody2")
        public String boardBody2;
        @SerializedName("headSeq")
        public String headSeq;
        @SerializedName("userNm")
        public String userNm;
        @SerializedName("lstChgDt")
        public String lstChgDt;
        @SerializedName("boardSearchCnt")
        public String boardSearchCnt;
        @SerializedName("boardCd")
        public String boardCd;
        @SerializedName("userId")
        public String userId;
        @SerializedName("boardActiveFg")
        public String boardActiveFg;
        @SerializedName("regDt")
        public String regDt;
    }

}
