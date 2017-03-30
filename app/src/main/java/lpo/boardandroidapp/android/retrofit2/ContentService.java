package lpo.boardandroidapp.android.retrofit2;

import lpo.boardandroidapp.request.WriteReq;
import lpo.boardandroidapp.response.BoardMainRes;
import lpo.boardandroidapp.response.TabListRes;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Woonho on 2017. 3. 12..
 */

public interface ContentService {

    /**
     * Get 방식
     * @param id 조회 아이디
     * @return
     */
    @GET("/sample/selectBoardList.do")
    Call<BoardMainRes> getBoard(@Query("INFO_ID") String id);

    /**
     * 전체 글 가져오기
     * @param boardCd 게시판 코드
     * @return
     */
    @FormUrlEncoded
    @POST("/politics/searchPoliticsBoardInfo.do")
    Call<BoardMainRes> getPostBoard(@Field("BOARD_CD") String boardCd);

    /**
     * Tab List
     * @param grpCd 그룹 코드
     * @return
     */
    @GET("/util/searchCmnCd.do")
    Call<TabListRes> getTab(@Query("grpCd") String grpCd);

    /**
     * Write Insert
     * @param userId 유저 아이디
     * @param userNm 유저 닉네임
     * @param boardCd 게시판 코드 (Not Null)
     * @param boardTitle 글쓰기 타이틀 (Not Null)
     * @param boardActiveFg 편집 유형 (Not Null)
     * @param boardBody1 글쓰기 내용1 (Not Null)
     * @param boardBody2 글쓰기 내용2
     * @return
     */
    @FormUrlEncoded
    @POST("/politics/insertBoard.do")
    Call<WriteReq> getInsertResult(
            @Field("userId") String userId,
            @Field("userNm") String userNm,
            @Field("boardCd") String boardCd,
            @Field("boardTitle") String boardTitle,
            @Field("boardActiveFg") String boardActiveFg,
            @Field("boardBody1") String boardBody1,
            @Field("boardBody2") String boardBody2);

}
