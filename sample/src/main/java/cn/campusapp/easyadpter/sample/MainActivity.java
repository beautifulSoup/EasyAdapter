package cn.campusapp.easyadpter.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.campusapp.easyadpter.R;
import cn.campusapp.easyadpter.adapter.AdapterDelegate;
import cn.campusapp.easyadpter.adapter.EasyAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView vRv;

    List<String> mTestData;

    EasyAdapter mEasyAdapter;

    View mHeaderView;
    View mFooterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEasyAdapter != null){
                    mEasyAdapter.removeHeader(mHeaderView);
                    mEasyAdapter.notifyDataSetChanged();
                }
            }
        });

        initUi();


    }


    private void initUi(){
        vRv = (RecyclerView) findViewById(R.id.test_rv);
        vRv.setLayoutManager(new LinearLayoutManager(this));
        mEasyAdapter = new EasyAdapter(getTestDataList(), new StringAdapterDelegate());
        mHeaderView = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_header, null, false);
        mFooterView = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_footer, null, false);
        mEasyAdapter.addHeader(mHeaderView);
        mEasyAdapter.addFooter(mFooterView);
        vRv.setAdapter(mEasyAdapter);
        mEasyAdapter.notifyDataSetChanged();
    }

    public List<String> getTestDataList(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0;i<100;i++){
            list.add(Integer.toString(i));
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class StringViewHolder extends RecyclerView.ViewHolder {

        String mData;

        TextView vTv;

        public StringViewHolder(View itemView) {
            super(itemView);
            vTv = (TextView) itemView.findViewById(R.id.tv);
        }

        public void setText(String text){
            vTv.setText(text);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    class StringAdapterDelegate implements AdapterDelegate<RecyclerView.ViewHolder,String> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 0){
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_item, parent, false);
                return new StringViewHolder(view);
            } else {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_item_img, parent, false);
                return new ImageViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, String item) {
            if(holder instanceof StringViewHolder){
                ((StringViewHolder) holder).setText(item);
            } else {
                // do nothing
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }
    }
}
