package com.androidiqaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Mrathi on 9/21/2016.
 */
public class CustomAdapter extends BaseAdapter {
    String [] result;
    String [] result2;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(ActivityClass mainActivity, String[] questionList, String[] answerList) {
        // TODO Auto-generated constructor stub
        result=questionList;
        result2=answerList;
        context=mainActivity;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView questionName,answerName;
        ToggleButton img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.questionlist, null);

        holder.questionName=(TextView) rowView.findViewById(R.id.q1);
        holder.answerName=(TextView)rowView.findViewById(R.id.a1);
        //holder.img=(ToggleButton) rowView.findViewById(R.id.toggleButton);
        holder.questionName.setText(result[position]);
        holder.answerName.setText(result2[position]);
       // holder.img.(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();

            }
        });
        return rowView;
    }

}
