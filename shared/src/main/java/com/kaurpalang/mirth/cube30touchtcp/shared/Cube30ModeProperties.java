package com.kaurpalang.mirth.cube30touchtcp.shared;

import com.kaurpalang.mirth.cube30touchtcp.shared.config.Constants;
import com.mirth.connect.model.transmission.framemode.FrameModeProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Level;

public class Cube30ModeProperties extends FrameModeProperties {

    @Getter @Setter private boolean rejectInvalidFrames;
    @Getter @Setter private Level logLevel;

    public Cube30ModeProperties() {
        super(Constants.PLUGIN_POINTNAME);
        setStartOfMessageBytes("");
        setEndOfMessageBytes("");

        this.rejectInvalidFrames = true;
        this.logLevel = Level.INFO;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof Cube30ModeProperties) {
            Cube30ModeProperties props = (Cube30ModeProperties) obj;
            equal = props.isRejectInvalidFrames() == rejectInvalidFrames;
        }

        return equal && super.equals(obj);
    }
}
