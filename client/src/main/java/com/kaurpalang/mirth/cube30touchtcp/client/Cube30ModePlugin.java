package com.kaurpalang.mirth.cube30touchtcp.client;

import com.kaurpalang.mirth.annotationsplugin.annotation.ClientClass;
import com.kaurpalang.mirth.cube30touchtcp.shared.config.Constants;
import com.mirth.connect.plugins.TransmissionModeClientProvider;
import com.mirth.connect.plugins.TransmissionModePlugin;

@ClientClass
public class Cube30ModePlugin extends TransmissionModePlugin {

    public Cube30ModePlugin(String name) {
        super(name);
    }

    public String getPluginPointName() {
        return Constants.PLUGIN_POINTNAME;
    }

    public TransmissionModeClientProvider createProvider() {
        return new Cube30ModeClientProvider();
    }
}
