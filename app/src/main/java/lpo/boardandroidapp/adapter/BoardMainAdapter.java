package lpo.boardandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import lpo.boardandroidapp.R;
import lpo.boardandroidapp.response.BoardMainRes;

/**
 * Created by leewoonho on 2017. 3. 13..
 */

public class BoardMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected static final String TAG = "BoardMainAdapter";
    private BoardMainRes mBoardMainRes;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public BoardMainAdapter(BoardMainRes boardMainRes) {
        Log.d(TAG, "BoardMainAdapter");
        mBoardMainRes = boardMainRes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "RecyclerView.ViewHolder >>> 1");
        if (viewType == TYPE_ITEM) {
            Log.d(TAG, "RecyclerView.ViewHolder >>> 2");
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
            return new ItemViewHolder(v);
        } else if (viewType == TYPE_FOOTER) {
            Log.d(TAG, "RecyclerView.ViewHolder >>> 3");
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer, parent, false);
            return new FooterViewHolder(v);
        }
        Log.d(TAG, "RecyclerView.ViewHolder >>> 4");
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "RecyclerView.getItemViewType >>> 1");
        if (isPositionFooter(position)) {
            Log.d(TAG, "RecyclerView.getItemViewType >>> 2");
            return TYPE_FOOTER;
        }
        Log.d(TAG, "RecyclerView.getItemViewType >>> 3");
        return TYPE_ITEM;
    }

    public boolean isPositionFooter(int position) {
        Log.d(TAG, "isPositionFooter >>> 1");
        int i = mBoardMainRes.list.size();
        return position == i;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder >>> 1");
        if (holder instanceof FooterViewHolder) {
            Log.d(TAG, "onBindViewHolder >>> 2");
            FooterViewHolder fvh = (FooterViewHolder) holder;
        } else if (holder instanceof ItemViewHolder) {
            Log.d(TAG, "onBindViewHolder >>> 3");
            ItemViewHolder hd = (ItemViewHolder) holder;

            hd.titleTv.setText(mBoardMainRes.list.get(position).boardTitle);
            hd.contentTv.setText(mBoardMainRes.list.get(position).boardBody1);
            hd.userNameTv.setText(mBoardMainRes.list.get(position).userNm);
            hd.dateTv.setText(mBoardMainRes.list.get(position).regDt);
            hd.clickCountTv.setText(mBoardMainRes.list.get(position).boardSearchCnt);
            hd.replyCountTv.setText(mBoardMainRes.list.get(position).replyCnt);
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount >>> 1");
        return mBoardMainRes.list.size() + 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTv;
        public TextView contentTv;
        public TextView userNameTv;
        public TextView dateTv;
        public TextView clickCountTv;
        public TextView replyCountTv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ItemViewHolder >>> 1");
            titleTv = (TextView) itemView.findViewById(R.id.item_title);
            contentTv = (TextView) itemView.findViewById(R.id.content1);
            userNameTv = (TextView) itemView.findViewById(R.id.user_name);
            dateTv = (TextView) itemView.findViewById(R.id.date);
            clickCountTv = (TextView) itemView.findViewById(R.id.click_count);
            replyCountTv = (TextView) itemView.findViewById(R.id.reply_count);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
