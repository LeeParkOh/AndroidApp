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

        holder.idxTv.setText("IDX = " + mBoardMainRes.list.get(position).idx);
        holder.rnumTv.setText("RNUM = " + mBoardMainRes.list.get(position).rnum);
        holder.hitCntTv.setText("HIT_CNT = " + mBoardMainRes.list.get(position).hit_cnt);
        holder.creaDtmTv.setText("CREA_DTM = " + mBoardMainRes.list.get(position).crea_dtm);
        holder.titleTv.setText("TITLE = " + mBoardMainRes.list.get(position).title);

    }

    @Override
    public int getItemCount() {
        return mBoardMainRes.list.size();
    }

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
