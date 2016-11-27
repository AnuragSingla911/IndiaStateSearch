package fetchinglist.sample.android.com.fetchinglist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public class SearchIPAdrFragment extends SearchBaseFragment implements OnMapReadyCallback{

    private LinearLayout mMapCaontainer;
    private GoogleMap mMap;
    private IPModal modal;

    @Override
    protected String getRequestURL() {
        return "http://geo.groupkt.com/ip/";
    }

    @Override
    protected String getRequestSuffix() {
        return "/json";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mMapCaontainer = new LinearLayout(getActivity());
        mMapCaontainer.setId(View.generateViewId());
        ((LinearLayout)view).addView(mMapCaontainer);
        return view;
    }

    @Override
    public void onRetreiveData(Response response) {
        super.onRetreiveData(response);
        ArrayList<IPModal> list =  response.getData();
        if(list != null && list.size() > 0){
            modal = list.get(0);
            if(modal != null){
                SupportMapFragment mapFragment = new SupportMapFragment();
                mapFragment.getMapAsync(this);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(mMapCaontainer.getId(), mapFragment, "").commit();
            }
        }

    }

    @Override
    protected View.OnClickListener getItemListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getTag() != null && view.getTag() instanceof IPModal){
                    Toast.makeText(getActivity(), "finally", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected IParser getParser() {
        return new IpParser();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(modal != null) {
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(modal.getLatitude(), modal.getLongitude());
            mMap.addMarker(new MarkerOptions().position(sydney).title(modal.getmStateName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        }
    }
}
