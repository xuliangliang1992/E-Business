package com.jinlong.ebusiness.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.http.response.MessageListBean;
import com.xll.mvplib.utils.StringUtil;
import com.xll.mvplib.view.ItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息列表适配器
 *
 * @aucthor xll
 * @date 2018/9/23
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder> {

    private Context mContext;
    private List<MessageListBean.ContentBean> data;
    private ItemClickListener mItemClickListener;
    private ItemCheckedChangedListener mItemCheckedChangedListener;
    /**
     * 是否可标记
     */
    private boolean isEdit = false;
    private boolean isAllChoose = false;

    public void setItemClickListener(ItemClickListener itemClickListener,ItemCheckedChangedListener itemCheckedChangedListener) {
        mItemClickListener = itemClickListener;
        mItemCheckedChangedListener = itemCheckedChangedListener;
    }

    public MessageListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<MessageListBean.ContentBean> data) {
        this.data = data;
    }

    public void setEdit(boolean edit) {
        this.isEdit = edit;
        this.isAllChoose = false;
    }

    public void setAllChoose(boolean allChoose) {
        isAllChoose = allChoose;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item, parent, false);
        return new MessageListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListViewHolder holder, final int position) {
        MessageListBean.ContentBean contentBean = data.get(position);
        MessageListBean.ContentBean.MessageBean message = contentBean.getMessage();
        holder.mTvTitle.setText(StringUtil.emptyIs(message.getTitle()));
        holder.mTvDate.setText(StringUtil.emptyIs(message.getCreateTime()));
        holder.mTvContent.setText(StringUtil.emptyIs(message.getHtml()));
        holder.mTvRead.setVisibility(contentBean.getStatus() == 0 ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickListener(v, position);
            }
        });

        if (isEdit) {
            holder.mCbChoose.setVisibility(View.VISIBLE);
        } else {
            holder.mCbChoose.setVisibility(View.GONE);
        }

        holder.mCbChoose.setChecked(isAllChoose);

        holder.mCbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mItemCheckedChangedListener.onItemCheckedChangedListener(buttonView,position,isChecked);
            }
        });
    }

    class MessageListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_choose)
        CheckBox mCbChoose;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.tv_read)
        TextView mTvRead;

        MessageListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


