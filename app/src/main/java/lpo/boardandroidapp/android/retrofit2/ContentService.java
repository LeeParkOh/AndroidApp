package lpo.boardandroidapp.android.retrofit2;

import lpo.boardandroidapp.response.BoardMainRes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Woonho on 2017. 3. 12..
 */

public interface ContentService {

    @GET("/sample/selectBoardList.do")
    Call<BoardMainRes> getBoard();

}
