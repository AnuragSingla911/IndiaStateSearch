package fetchinglist.sample.android.com.fetchinglist;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jade on 26/11/16.
 */
public class RequestExecuter extends AsyncTask<Object,Void,Response>{

    private CallBack mCallback;

    public void setmCallback(CallBack mCallback) {
        this.mCallback = mCallback;
    }

    public interface CallBack{
        public void onRetreiveData(Response response);
    }

    public Response executeRequest(String request, IParser parser) {
        {
            InputStream inputStream = null;
            String httpData = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(request);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                httpData = stringBuffer.toString();

                Response response = parser.parseResponse(httpData);
                bufferedReader.close();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                urlConnection.disconnect();
            }
        }
    }

    @Override
    protected Response doInBackground(Object... voids) {
        return executeRequest((String)voids[0],(IParser) voids[1]);
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        if(mCallback != null){
            mCallback.onRetreiveData(response);
        }
    }
}
