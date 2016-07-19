package com.chenyu.monster.yueyue.gankio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.chenyu.monster.yueyue.R;
import com.chenyu.monster.yueyue.framework.BaseListAdapter;
import com.chenyu.monster.yueyue.gankio.func.OnGirlTouchListener;
import com.chenyu.monster.yueyue.gankio.model.Gank;
import com.chenyu.monster.yueyue.widget.RatioImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyu on 16/7/15.
 */
public class GankAdapter extends BaseListAdapter<Gank, RecyclerView.ViewHolder> {
    private OnGirlTouchListener onGirlTouchListener;

    public GankAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return getFootView(parent.getContext());
        }
        View contentView = View.inflate(parent.getContext(), R.layout.item_gank_welfare, parent);
        return new GankViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankViewHolder) {
            Gank girl = data.get(position);
            final GankViewHolder gankHolder = (GankViewHolder) holder;

            int limit = 48;
            String text = girl.desc.length() > limit ? girl.desc.substring(0, limit) + "..." : girl.desc;
            gankHolder.title.setText(text);
            gankHolder.girl = girl;
            Glide.with(mContext).load(girl.url).centerCrop().into(gankHolder.avatar).getSize(new SizeReadyCallback() {
                @Override
                public void onSizeReady(int width, int height) {
                    if (!gankHolder.card.isShown()){
                        gankHolder.card.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void setOnGirlTouchListener(OnGirlTouchListener onGirlTouchListener) {
        this.onGirlTouchListener = onGirlTouchListener;
    }

    public class GankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.iv_avatar)
        RatioImageView avatar;
        @Bind(R.id.tv_title)
        TextView title;
        View card;
        Gank girl;

        public GankViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            card.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            avatar.setOnClickListener(this);
            avatar.setOriginzation(50, 50);
        }

        @Override
        public void onClick(View v) {
            onGirlTouchListener.onTouch(v, avatar, card, girl);
        }
    }
}
