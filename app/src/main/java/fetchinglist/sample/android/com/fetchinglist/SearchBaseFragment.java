package fetchinglist.sample.android.com.fetchinglist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jade on 26/11/16.
 */

public abstract class SearchBaseFragment extends Fragment implements SearchView.OnQueryTextListener , RequestExecuter.CallBack{

    protected abstract String getRequestURL();

    protected abstract String getRequestSuffix();

    protected abstract View.OnClickListener getItemListener();

    protected abstract IParser getParser();

    private ArrayList<BaseModal> mDataList = new ArrayList<>();
    private SearchView mSearchText;
    private RecyclerView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mSearchText = (SearchView)view.findViewById(R.id.search_box);
        mSearchText.setOnQueryTextListener(this);
        mListView = (RecyclerView)view.findViewById(R.id.search_results);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mDataList != null && mDataList.size() > 0){
            ListAdapter adapter = new ListAdapter(getActivity() , mDataList , getItemListener());
            if (mListView != null)
                mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onRetreiveData(Response response) {
        if (response != null) {
            mDataList = response.getData();
            ListAdapter adapter = new ListAdapter(getActivity(), mDataList, getItemListener());
            if (mListView != null) {
                mListView.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        RequestExecuter executer = new RequestExecuter();
        executer.setmCallback(this);
        executer.execute(getRequestURL() + newText + getRequestSuffix(), getParser());
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView = null;


        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView)itemView.findViewById(android.R.id.text1);


        }
    }

    public static class ListAdapter extends RecyclerView.Adapter<ViewHolder>{

        private ArrayList<BaseModal> mDataList = new ArrayList<>();

        private Context mContext;

        private View.OnClickListener mListener;

        public ListAdapter(Context context, ArrayList<BaseModal> mDataList,View.OnClickListener listener){
            mContext = context;
            this.mDataList = mDataList;
            this.mListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            view.setOnClickListener(mListener);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            BaseModal modal = mDataList.get(position);
            holder.mTextView.setText(modal.getmStateName() + " , " + modal.getmCountry());
            holder.mTextView.setTag(modal);
        }

        @Override
        public int getItemCount() {
            return mDataList != null ? mDataList.size() : 0;
        }
    }


}
