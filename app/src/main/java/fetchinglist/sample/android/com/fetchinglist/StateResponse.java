package fetchinglist.sample.android.com.fetchinglist;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public class StateResponse implements Response<BaseModal> {

    private ArrayList<BaseModal> mData = null;

    public StateResponse setData(ArrayList<BaseModal> list){
        mData = list;
        return this;
    }


    @Override
    public ArrayList<BaseModal> getData() {
        return mData;
    }
}
