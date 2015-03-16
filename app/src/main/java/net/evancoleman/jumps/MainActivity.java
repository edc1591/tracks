package net.evancoleman.jumps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import java.lang.System;
import java.util.ArrayList;
import java.util.Collections;

//import com.reconinstruments.ReconSDK.ReconSDKActivity;
import com.reconinstruments.ReconSDK.ReconSDKManager;
import com.reconinstruments.ReconSDK.IReconEventListener;
import com.reconinstruments.ReconSDK.IReconDataReceiver;
import com.reconinstruments.ReconSDK.ReconDataResult;
import com.reconinstruments.ReconSDK.ReconEvent;
import com.reconinstruments.ReconSDK.ReconJump;
import com.reconinstruments.ReconSDK.ReconRun;
import com.reconinstruments.ReconSDK.ReconSpeed;

public class MainActivity extends Activity implements IReconEventListener,IReconDataReceiver {
    ArrayList<ReconJump> _jumps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _jumps = new ArrayList<ReconJump>();

        ReconSDKManager manager = ReconSDKManager.Initialize(getApplicationContext());
        try {
            manager.registerListener(this, ReconEvent.TYPE_JUMP);
            manager.receiveData(this, ReconEvent.TYPE_RUN);
            manager.receiveData(this, ReconEvent.TYPE_JUMP);
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onDataChanged(ReconEvent event, java.lang.reflect.Method m) {
        if (event.getType() == ReconEvent.TYPE_JUMP) {
            ReconJump j = (ReconJump)event;
            _jumps.add(j);
            listview = (ListView)findViewById(R.id.JumpsList);
            listview.setAdapter(new JumpRowAdapter(this, _jumps));
        }
    }

    @Override
    public void onReceiveCompleted(int status, ReconDataResult result) {
        if (result.arrItems.size() > 0) {
            System.out.println(result.arrItems.get(0));
            if (result.arrItems.get(0) instanceof ReconRun) {
                TextView t = (TextView)findViewById(R.id.Runs);
                t.setText(result.arrItems.size() + " runs");
            } else if (result.arrItems.get(0) instanceof ReconJump) {
                _jumps.clear();
                _jumps.addAll(result.arrItems);
                listview = (ListView)findViewById(R.id.JumpsList);
                listview.setAdapter(new JumpRowAdapter(this, _jumps));
            }
        }
    }

    @Override
    public void onFullUpdateCompleted(int status, ArrayList<ReconDataResult> results) {

    }
}
