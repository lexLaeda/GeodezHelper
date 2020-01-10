package com.example.geodezhelper.Count;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.geodezhelper.ActivityMain;
import com.example.geodezhelper.BL.ActivityListBL;
import com.example.geodezhelper.BL.Baseline;
import com.example.geodezhelper.BL.DataBaseLine;
import com.example.geodezhelper.Pojo.BaseForCount;
import com.example.geodezhelper.Pojo.NivPoint;
import com.example.geodezhelper.Pojo.Point;
import com.example.geodezhelper.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class FragCountPointParams extends Fragment implements View.OnClickListener {
    private EditText xText;
    private EditText yText;
    private EditText hText;
    private TextView xView;
    private TextView yView;
    private TextView hView;
    private TextView radView;
    private TextView gor_lengthView;
    private TextView lengthView;
    private TextView devView;
    private TextView heightView;

    private Button choose_bl;
    private Button count;
    private Map<EditText, View> views;
    private DataBaseLine dataBaseLine;
    private Baseline currentBL;
    StringResult stringResult;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_count_point_params, null);
        views = new HashMap<>();
        dataBaseLine = DataBaseLine.getInstance(getActivity());
        currentBL = dataBaseLine.getCurrentBL();

        addEditTexts();
        addViews();
        addButtons();
        return view;
    }

    private void addEditTexts(){
        xText = (EditText)view.findViewById(R.id.X_text);
        yText = (EditText)view.findViewById(R.id.Y_text);
        hText = (EditText)view.findViewById(R.id.H_text);
    }
    private void addViews(){
        xView = (TextView)view.findViewById(R.id.view_x_report);
        xView.setVisibility(View.INVISIBLE);
        views.put(xText,xView);
        yView = (TextView)view.findViewById(R.id.view_y_report);
        yView.setVisibility(View.INVISIBLE);
        views.put(yText,yView);
        hView = (TextView)view.findViewById(R.id.view_h_report);
        hView.setVisibility(View.INVISIBLE);
        views.put(hText,hView);
        radView = (TextView)view.findViewById(R.id.view_rad_report);

        gor_lengthView = (TextView)view.findViewById(R.id.view_gor_length_report);

        lengthView = (TextView)view.findViewById(R.id.view_length_report);

        devView = (TextView)view.findViewById(R.id.view_dev_report);

        heightView = (TextView)view.findViewById(R.id.view_height_report);

    }
    private void addButtons(){
        choose_bl = (Button)view.findViewById(R.id.choose_bl);
        choose_bl.setOnClickListener(this);
        if (currentBL!=null){
            choose_bl.setText(currentBL.getName());
        }else {
            choose_bl.setText(R.string.button_choose_bl);
        }
        count = (Button)view.findViewById(R.id.count_point_params);
        count.setOnClickListener(this);
    }
    private Double checkCoord(EditText editText){
        Double result = null;
        try {
            result = Double.parseDouble(editText.getText().toString());
            views.get(editText).setVisibility(View.INVISIBLE);
            return result;
        }catch (Exception ex){
            views.get(editText).setVisibility(View.VISIBLE);
            return result;
        }
    }
    private void countParams(){
        Double x = checkCoord(xText);
        Double y = checkCoord(yText);
        Double h = checkCoord(hText);
        if (x!=null && y!=null && h!=null){
        Point point = new Point(x,y,h);
        currentBL = dataBaseLine.getCurrentBL();
            if (currentBL!=null){
               System.out.println(currentBL.getName());
               stringResult = new StringResult(currentBL,point);
               updateView();
            } else {
                stringResult = new StringResult();
                System.out.println("пизда");
                updateView();
            }
        }

    }
    private void updateView(){
        radView.setText(stringResult.getRadius());
        gor_lengthView.setText(stringResult.getGorizontalLength());
        lengthView.setText(stringResult.getAbsolutLength());
        devView.setText(stringResult.getDeviation());
        heightView.setText(stringResult.getDeltaH());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_bl :
                Intent intent2 = new Intent(getActivity(), ActivityListBL.class);
                startActivity(intent2);
                break;
            case R.id.count_point_params :
                countParams();
                break;
        }
    }
}
