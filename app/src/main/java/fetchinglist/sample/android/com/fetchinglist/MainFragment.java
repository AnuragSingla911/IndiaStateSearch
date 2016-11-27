package fetchinglist.sample.android.com.fetchinglist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jade on 26/11/16.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button mTextButton, mIpAdressButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTextButton = (Button) view.findViewById(R.id.ip_text_search_btn);
        mIpAdressButton = (Button) view.findViewById(R.id.ip_address_search_btn);
        mTextButton.setOnClickListener(this);
        mIpAdressButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ip_text_search_btn:
                SearchStateFragment searchStateFragment = new SearchStateFragment();
                searchStateFragment.setRetainInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.activity_main, searchStateFragment, "").addToBackStack("").commit();
                break;

            case R.id.ip_address_search_btn:
                SearchIPAdrFragment searchIPAdrFragment = new SearchIPAdrFragment();
                searchIPAdrFragment.setRetainInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.activity_main, searchIPAdrFragment, "").addToBackStack("").commit();
                break;
        }
    }
}
