package cn.campusapp.easyadpter.adapter;

import android.view.View;

/**
 * Created by kris on 16/1/21.
 * 这个容器目前只支持一个header和一个footer
 */
public class HeaderAndFooterHolder {
    View mHeader;
    View mFooter;

    public void addHeader(View header){
        mHeader = header;
    }

    public void addFooter(View footer){
        mFooter = footer;
    }

    public void removeHeader(){
        mHeader = null;
    }

    public void removeFooter(){
        mFooter = null;
    }

    public View getHeader(){
        return mHeader;
    }

    public View getFooter(){
        return mFooter;
    }

    public int getHeaderCount(){
        return mHeader == null ? 0 : 1;
    }

    public int getFooterCount(){
        return mFooter == null ? 0 : 1;
    }


}
