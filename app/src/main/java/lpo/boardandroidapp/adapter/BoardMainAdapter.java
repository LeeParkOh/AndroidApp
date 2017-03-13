package lpo.boardandroidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lpo.boardandroidapp.R;
import lpo.boardandroidapp.response.BoardMainRes;

/**
 * Created by leewoonho on 2017. 3. 13..
 */

public class BoardMainAdapter extends RecyclerView.Adapter<BoardMainAdapter.ViewHolder>{

    private ArrayList<BoardMainRes> mBoardMainResRes;

    public BoardMainAdapter(ArrayList<BoardMainRes> boardMainRes) {
        mBoardMainResRes = boardMainRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mBoardMainResRes.get(position);
        holder.idxTv.setText(mBoardMainResRes.get(position).getList().get(position).getIdx());
    }

    @Override
    public int getItemCount() {
        return mBoardMainResRes.size();
    }

//    public void updateMainBoardData(BoardMainRes bmRes) {
//        mBoardMainResRes = bmRes;
//        notifyDataSetChanged();
//    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView idxTv;
        public TextView rnumTv;
        public TextView hitCntTv;
        public TextView creaDtmTv;
        public TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);

            idxTv = (TextView) itemView.findViewById(R.id.item_view_idx);
            rnumTv = (TextView) itemView.findViewById(R.id.item_view_rnum);
            hitCntTv = (TextView) itemView.findViewById(R.id.item_view_hit_cnt);
            creaDtmTv = (TextView) itemView.findViewById(R.id.item_view_crea_dtm);
            titleTv = (TextView) itemView.findViewById(R.id.item_view_title);

        }
    }
}
