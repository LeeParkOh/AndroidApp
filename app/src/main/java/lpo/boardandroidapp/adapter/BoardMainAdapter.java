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

    protected static final String TAG = "BoardMainAdapter";
    private BoardMainRes mBoardMainRes;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public BoardMainAdapter(BoardMainRes boardMainRes) {
        mBoardMainRes = boardMainRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder vh = new ViewHolder(v, viewType);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public boolean isPositionFooter(int position) {
        int i = mBoardMainRes.list.size();
        return position == i;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titleTv.setText(mBoardMainRes.list.get(position).boardTitle);
        holder.contentTv.setText(mBoardMainRes.list.get(position).boardBody1);
        holder.userNameTv.setText(mBoardMainRes.list.get(position).userNm);
        holder.dateTv.setText(mBoardMainRes.list.get(position).regDt);
        holder.clickCountTv.setText(mBoardMainRes.list.get(position).boardSearchCnt);
        holder.replyCountTv.setText(mBoardMainRes.list.get(position).replyCnt);
    }

    @Override
    public int getItemCount() {
        return mBoardMainRes.list.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTv;
        public TextView contentTv;
        public TextView userNameTv;
        public TextView dateTv;
        public TextView clickCountTv;
        public TextView replyCountTv;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.item_title);
            contentTv = (TextView) itemView.findViewById(R.id.content1);
            userNameTv = (TextView) itemView.findViewById(R.id.user_name);
            dateTv = (TextView) itemView.findViewById(R.id.date);
            clickCountTv = (TextView) itemView.findViewById(R.id.click_count);
            replyCountTv = (TextView) itemView.findViewById(R.id.reply_count);
        }
    }
}
