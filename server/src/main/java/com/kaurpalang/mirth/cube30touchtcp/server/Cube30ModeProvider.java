package com.kaurpalang.mirth.cube30touchtcp.server;

import com.kaurpalang.mirth.annotationsplugin.annotation.ServerClass;
import com.kaurpalang.mirth.cube30touchtcp.shared.config.Constants;
import com.mirth.connect.donkey.server.message.StreamHandler;
import com.mirth.connect.donkey.server.message.batch.BatchStreamReader;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.plugins.TransmissionModeProvider;

import java.io.InputStream;
import java.io.OutputStream;

@ServerClass
public class Cube30ModeProvider extends TransmissionModeProvider {

    @Override
    public StreamHandler getStreamHandler(InputStream inputStream, OutputStream outputStream, BatchStreamReader batchStreamReader, TransmissionModeProperties transmissionModeProperties) {
        return new Cube30StreamHandler(inputStream, outputStream, batchStreamReader, transmissionModeProperties);
    }

    @Override
    public String getPluginPointName() {
        return Constants.PLUGIN_POINTNAME;
    }
}
