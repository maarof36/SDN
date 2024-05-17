package com.example.sdn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link barFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class barFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public barFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment barFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static barFragment newInstance(String param1, String param2) {
        barFragment fragment = new barFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
       // intint();
    }

    private void intint() {
        GraphView grf = getView().findViewById(R.id.idGraphView);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        grf.setTitle("My Graph View");

        // on below line we are setting
        // text color to our graph view.
        grf.setTitleColor(R.color.black);

        // on below line we are setting
        // our title text size.
        grf.setTitleTextSize(18);

        // on below line we are adding
        // data series to our graph view.
        grf.addSeries(series);
    }
}