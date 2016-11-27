package fetchinglist.sample.android.com.fetchinglist;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public class IpResponse implements Response<IPModal> {

    private ArrayList<IPModal> mData = null;

    public IpResponse setData(ArrayList<IPModal> list){
        mData = list;
        return this;
    }
    @Override
    public ArrayList<IPModal> getData() {
        return mData;
    }
}
