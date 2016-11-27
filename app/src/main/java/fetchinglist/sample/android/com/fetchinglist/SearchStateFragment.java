package fetchinglist.sample.android.com.fetchinglist;

import android.view.View;

/**
 * Created by jade on 26/11/16.
 */

public class SearchStateFragment extends SearchBaseFragment {

    @Override
    protected String getRequestURL() {
        return "http://services.groupkt.com/state/search/IND?text=";
    }

    @Override
    protected String getRequestSuffix() {
        return "";
    }

    @Override
    protected View.OnClickListener getItemListener() {
        return null;
    }

    @Override
    protected IParser getParser() {
        return new StateParser();
    }
}
