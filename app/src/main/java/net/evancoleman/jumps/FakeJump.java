package net.evancoleman.jumps;

import com.reconinstruments.ReconSDK.ReconJump;

/**
 * Created by evan on 3/16/15.
 */
public class FakeJump extends ReconJump {

    public FakeJump() {
        this.mAir = 2;
        this.mDate = 1426556782;
        this.mDistance = 10;
        this.mDrop = 4;
        this.mHeight = 2;
    }
}
