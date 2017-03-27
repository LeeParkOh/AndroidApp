package lpo.boardandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import lpo.boardandroidapp.R;
import lpo.boardandroidapp.response.BoardMainRes;

/**
 * Created by leewoonho on 2017. 3. 13..
 */

public class BoardMainAdapter extends RecyclerView.Adapter<BoardMainAdapter.ViewHolder>{

    private BoardMainRes mBoardMainRes;

    public BoardMainAdapter(BoardMainRes boardMainRes) {
        mBoardMainRes = boardMainRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.idxTv.setText("IDX = " + mBoardMainRes.list.get(position).boardTilte);
//        holder.rnumTv.setText("RNUM = " + mBoardMainRes.list.get(position).boardBody1);
//        holder.hitCntTv.setText("HIT_CNT = " + mBoardMainRes.list.get(position).boardBody2);
//        holder.creaDtmTv.setText("CREA_DTM = " + mBoardMainRes.list.get(position).boardCd);
//        holder.titleTv.setText("TITLE = " + mBoardMainRes.list.get(position).regDt);

        holder.titleTv.setText(mBoardMainRes.list.get(position).boardTilte);
        holder.contentTv.setText(mBoardMainRes.list.get(position).boardBody1);
        holder.userNameTv.setText(mBoardMainRes.list.get(position).userNm);
        holder.dateTv.setText(mBoardMainRes.list.get(position).regDt);

    }

    @Override
    public int getItemCount() {
        return mBoardMainRes.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTv;
        public TextView contentTv;
        public TextView userNameTv;
        public TextView dateTv;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.item_title);
            contentTv = (TextView) itemView.findViewById(R.id.content1);
            userNameTv = (TextView) itemView.findViewById(R.id.user_name);
            dateTv = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
