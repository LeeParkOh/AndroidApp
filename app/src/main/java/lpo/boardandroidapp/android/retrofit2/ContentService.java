package lpo.boardandroidapp.android.retrofit2;

import lpo.boardandroidapp.response.BoardMainRes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Woonho on 2017. 3. 12..
 */

public interface ContentService {

//    @GET("/sample/selectBoardList.do")
//    Call<BoardMainRes> getBoard();
    // 파라미터 테스트
    @GET("/sample/selectBoardList.do")
    Call<BoardMainRes> getBoard(@Query("idx") String tag);

}
