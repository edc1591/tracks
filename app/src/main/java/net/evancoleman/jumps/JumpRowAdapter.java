package net.evancoleman.jumps;

import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

import com.reconinstruments.ReconSDK.ReconJump;

/**
 * Created by evan on 3/15/15.
 */
public class JumpRowAdapter extends BaseAdapter {
    Context _context;
    ArrayList<ReconJump> _jumps;
    private static LayoutInflater _inflater = null;
    SimpleDateFormat _dateFormatter;

    public JumpRowAdapter(Context context, ArrayList<ReconJump> jumps) {
        _context = context;
        _jumps = jumps;
        _inflater = (LayoutInflater)context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _dateFormatter = new SimpleDateFormat("h:mm a");
    }

    @Override
    public int getCount() {
        return _jumps.size();
    }

    @Override
    public Object getItem(int position) {
        return _jumps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) vi = inflater.inflate(R.layout.JumpRow, null);
        TextView numText = (TextView)vi.findViewById(R.id.JumpNum);
        TextView timeText = (TextView)vi.findViewById(R.id.JumpTime);
        TextView distText = (TextView)vi.findViewById(R.id.JumpDist);
        TextView heightText = (TextView)vi.findViewById(R.id.JumpHeight);
        TextView dropText = (TextView)vi.findViewById(R.id.JumpDrop);
        TextView airTimeText = (TextView)vi.findViewById(R.id.JumpAirTime);

        ReconJump j = _jumps.get(position);

        numText.setText(position + 1);
        timeText.setText(_dateFormatter.format(new java.util.Date(j.GetDate())));
        distText.setText(java.lang.Math.round(j.GetDistance() * 3.28084));
        heightText.setText(java.lang.Math.round(j.GetHeight() * 3.28084));
        dropText.setText(java.lang.Math.round(j.GetDrop() * 3.28084));
        airTimeText.setText(j.GetAir());
        return vi;
    }
}
