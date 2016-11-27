package fetchinglist.sample.android.com.fetchinglist;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public interface IParser<T> {

    Response<T> parseResponse(String data);
}
