package com.kaurpalang.mirth.cube30touchtcp.shared;

import com.kaurpalang.mirth.cube30touchtcp.shared.config.Constants;
import com.mirth.connect.model.transmission.framemode.FrameModeProperties;

public class Cube30ModeProperties extends FrameModeProperties {

    private String ackBytes;
    private String nackBytes;
    private String maxRetries;

    public Cube30ModeProperties() {
        super(Constants.PLUGIN_POINTNAME);
        setStartOfMessageBytes("");
        setEndOfMessageBytes("");
        ackBytes = "06"; // <ACK>
        nackBytes = "15"; // <NAK>
        maxRetries = "2";
    }

    public String getAckBytes() {
        return ackBytes;
    }

    public void setAckBytes(String ackBytes) {
        this.ackBytes = ackBytes;
    }

    public String getNackBytes() {
        return nackBytes;
    }

    public void setNackBytes(String nackBytes) {
        this.nackBytes = nackBytes;
    }

    public String getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(String maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof Cube30ModeProperties) {
            Cube30ModeProperties props = (Cube30ModeProperties) obj;
            equal = props.getAckBytes().equals(ackBytes) &&
                    props.getNackBytes().equals(nackBytes) &&
                    props.getMaxRetries().equals(maxRetries);
        }

        return equal && super.equals(obj);
    }
}
