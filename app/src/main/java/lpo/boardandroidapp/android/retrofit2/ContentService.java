package lpo.boardandroidapp.android.retrofit2;

import lpo.boardandroidapp.response.BoardMainRes;
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
     * @param tag 조회 아이디
     * @return
     */
    @GET("/sample/selectBoardList.do")
    Call<BoardMainRes> getBoard(@Query("INFO_ID") String tag);

    /**
     * Post 방식
     * @param content 글 내용
     * @return
     */
    @FormUrlEncoded
    @POST("/sample/selectBoardList.do")
    Call<BoardMainRes> getPostBoard(@Query("INFO_ID") String tag, @Field("CONTENTS") String content);
}
