package com.kaurpalang.mirth.cube30touchtcp.server;

import com.kaurpalang.mirth.cube30touchtcp.shared.model.CubeFrame;
import com.kaurpalang.mirth.cube30touchtcp.shared.model.CubeFrames;
import com.mirth.connect.donkey.server.message.batch.BatchStreamReader;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.model.transmission.framemode.FrameStreamHandler;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cube30StreamHandler extends FrameStreamHandler {
    private Logger logger = Logger.getLogger(this.getClass());

    public Cube30StreamHandler(InputStream inputStream, OutputStream outputStream, BatchStreamReader batchStreamReader, TransmissionModeProperties transmissionModeProperties) {
        super(inputStream, outputStream, batchStreamReader, transmissionModeProperties);
        setReturnDataOnException(true);
    }

    @Override
    public byte[] read() throws IOException {
        byte[] message = super.read();

        CubeFrames frames = new CubeFrames();

        // Iterate over frames
        for (LinkedList<Byte> frameBytes : getListOfFrames(message)) {
            CubeFrame frame = new CubeFrame(frameBytes);

            frames.add(frame);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Generate XML from models
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CubeFrames.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true );

            jaxbMarshaller.marshal(frames, byteArrayOutputStream);
        } catch (Exception e) {
            logger.error(e);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private List<LinkedList<Byte>> getListOfFrames(byte[] input) {
        List<LinkedList<Byte>> framesAggregator = new ArrayList<>();
        LinkedList<Byte> bytesAggregator = new LinkedList<>();

        byte currentByte;
        boolean isMessageSplit = false;
        for (int i = 0; i < input.length; i++) {
            currentByte = input[i];

            // Make sure we have room to look two bytes ahead
            if (input.length - i >= 2) {
                // Allow a split only for >00 byte sequence that is not at the beginning of message
                if (i != 0
                        && currentByte == 0x3E
                        && input[i + 1] == 0x30
                        && input[i + 2] == 0x30) {
                    isMessageSplit = true;
                }
            }

            // If current iteration is at >00, add old bytes aggregator to messages aggregator and
            // instantiate a new one
            if (isMessageSplit) {
                framesAggregator.add(bytesAggregator);
                bytesAggregator = new LinkedList<>();
                isMessageSplit = false;
            }

            bytesAggregator.add(currentByte);
        }
        framesAggregator.add(bytesAggregator);

        return framesAggregator;
    }
}
