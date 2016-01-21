package cn.campusapp.easyadpter.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kris on 16/1/21.
 * 目前只支持一个Header和一个Footer
 * 如果是在GridManager布局下，则Header和Footer因为只是一个普通的item，所以还是会只在一列显示，而不会占据整行
 * TODO header和footer在GridManager下也占据整行
 * TODO 可以插入和删除多个header和footer
 *
 */
public class  EasyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List mDataSet;
    private AdapterDelegate mDelegate;

    private HeaderAndFooterHolder mHolder = new HeaderAndFooterHolder();

    public EasyAdapter(List dataSet, AdapterDelegate delegate){
        mDataSet = dataSet;
        mDelegate = delegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ViewType.TYPE_HEADER:
                return new HeaderViewHolder(mHolder.getHeader());
            case ViewType.TYPE_FOOTER:
                return new FooterViewHolder(mHolder.getFooter());
        }
        //如果这里还没有返回，说明是使用者定义的ViewType，交给代理处理
        return mDelegate.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder || holder instanceof HeaderViewHolder){
            //do nothing
        } else {
            mDelegate.onBindViewHolder(holder, mDataSet.get(position - mHolder.getHeaderCount()));
        }
    }

    @Override
    public int getItemCount() {
        return mHolder.getHeaderCount() + mHolder.getFooterCount() + mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position < mHolder.getHeaderCount()){
            return ViewType.TYPE_HEADER;
        } else if(position >= (mHolder.getHeaderCount() + mDataSet.size())){
            return ViewType.TYPE_FOOTER;
        } else {
            int type = mDelegate.getItemViewType(position - mHolder.getHeaderCount());
            if(type == ViewType.TYPE_FOOTER || type == ViewType.TYPE_HEADER){
                throw new RuntimeException("You shouldn't use ViewType integer constant with 1001 and 1002");
            }
            return type;
        }
    }

    public void addHeader(View view){
        mHolder.addHeader(view);
    }

    public void addFooter(View view){
        mHolder.addFooter(view);
    }


    public void removeHeader(View view){
        mHolder.removeHeader();
    }

    public void removeFooter(View view){
        mHolder.removeFooter();
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

    }


    static class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }



    interface ViewType{
        int TYPE_HEADER = 1001;
        int TYPE_FOOTER = 1002;
    }




}
