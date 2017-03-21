package lpo.boardandroidapp.android.retrofit2;

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
     * Post 방식
     * @param id 조회 아이디
     * @param content 글 내용
     * @return
     */
    @FormUrlEncoded
    @POST("/sample/selectBoardList.do")
    Call<BoardMainRes> getPostBoard(@Query("INFO_ID") String id, @Field("CONTENTS") String content);

    /**
     * Tab List
     * @param
     * @return
     */
    @GET("/util/searchCmnCd.do")
    Call<TabListRes> getTab();

}
