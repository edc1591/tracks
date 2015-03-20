package net.evancoleman.jumps;

import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;

import java.text.SimpleDateFormat;

import com.reconinstruments.ReconSDK.ReconEvent;
import com.reconinstruments.ReconSDK.ReconJump;

/**
 * Created by evan on 3/15/15.
 */
public class JumpRowAdapter extends ArrayAdapter<ReconEvent> {
    private SimpleDateFormat _dateFormatter;

    public JumpRowAdapter(Context context, int resource) {
        super(context, resource);
        _dateFormatter = new SimpleDateFormat("h:mm");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.jump_row, null, true);
        }
        TextView numText = (TextView)convertView.findViewById(R.id.JumpNum);
        TextView timeText = (TextView)convertView.findViewById(R.id.JumpTime);
        TextView distText = (TextView)convertView.findViewById(R.id.JumpDist);
        TextView heightText = (TextView)convertView.findViewById(R.id.JumpHeight);
        TextView dropText = (TextView)convertView.findViewById(R.id.JumpDrop);
        TextView airTimeText = (TextView)convertView.findViewById(R.id.JumpAirTime);

        ReconJump j = (ReconJump)getItem(position);

        numText.setText((position + 1) + "");
        timeText.setText(_dateFormatter.format(new java.util.Date(j.GetDate() * 1000)));
        distText.setText(java.lang.Math.round(j.GetDistance() * 3.28084) + " ft");
        heightText.setText(java.lang.Math.round(j.GetHeight() * 3.28084) + " ft");
        dropText.setText(java.lang.Math.round(j.GetDrop() * 3.28084) + " ft");
        airTimeText.setText(j.GetAir() + " s");
        return convertView;
    }
}
