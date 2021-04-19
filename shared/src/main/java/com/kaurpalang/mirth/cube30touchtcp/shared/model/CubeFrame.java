package com.kaurpalang.mirth.cube30touchtcp.shared.model;

import com.kaurpalang.mirth.cube30touchtcp.shared.config.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.regex.Matcher;

@NoArgsConstructor
@XmlRootElement(name = "cubeFrame")
public class CubeFrame {
    private Logger logger = Logger.getLogger(this.getClass());

    private LinkedList<Byte> rawData;

    @XmlElement(name = "dataBytesCount")
    private int dataBytesCount;

    @XmlElement(name = "recordTubesCount")
    private int recordTubesCount;

    @XmlElement(name = "barcode")
    private String barcode;

    @XmlElement(name = "dateTime")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;

    @XmlElement(name = "ves")
    private String value;

    @XmlElement(name = "flag")
    private String flag;

    @XmlElement(name = "cycle")
    private int cycleCount;

    @Getter private boolean isValidFrame;

    public CubeFrame(LinkedList<Byte> frameData) {
        this.isValidFrame = false;
        this.rawData = frameData;

        // Convert bytes to a text
        String textRepresentation = new String(getRawDataInPrimitive(), StandardCharsets.UTF_8);
        // Match textual representation against a regex
        Matcher frameMatcher = Constants.FRAME_TEXT_REGEX.matcher(textRepresentation);

        this.isValidFrame = this.validateFrame(frameMatcher);

        // If frame is valid, populate model's other fields too
        if (this.isValidFrame) {
            this.populateFields(frameMatcher);
        }
    }

    private boolean validateFrame(Matcher frameMatcher) {

        boolean isTextValid = frameMatcher.matches();

        // If text does not match regex, return false.
        // I don't really have a better way to check this tbh
        if (!isTextValid) {
            return false;
        }

        // Calculate bitwise XOR value of message
        int xor = 0;
        for (Byte b : rawData) {
            xor = xor ^ b;

            // Break after ETX
            if (b == 0x0D) break;
        }

        // Return true if the checksum group in textual representation equals
        // the xor value converted to hexadecimal
        return frameMatcher.group("checksum").equals(Integer.toHexString(xor));
    }

    private byte[] getRawDataInPrimitive() {
        byte[] bytes = new byte[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) {
            bytes[i] = rawData.get(i);
        }

        return bytes;
    }

    private void populateFields(Matcher frameMatcher) {
        this.dataBytesCount = Integer.parseInt(frameMatcher.group("dataBytesCount"));
        this.recordTubesCount = Integer.parseInt(frameMatcher.group("recordTubesCount"));
        this.barcode = frameMatcher.group("barcode");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmm");
        this.dateTime = LocalDateTime.parse(frameMatcher.group("date") + frameMatcher.group("time"), formatter);
        this.value = frameMatcher.group("value").trim();
        this.cycleCount = Integer.parseInt(frameMatcher.group("cycle"), 16);

        int flagInt = Integer.parseInt(frameMatcher.group("flags"), 16);
        if (DataFlags.flagToTextMap.containsKey(flagInt)) {
            this.flag = DataFlags.flagToTextMap.get(flagInt);
        }
    }

    @Override
    public String toString() {
        return this.getString();
    }

    private String getString() {
        StringBuilder builder = new StringBuilder();
        builder.append("====== CubeFrame ======\n");

        builder.append("dataBytes").append(": ").append(dataBytesCount).append("\n");
        builder.append("recordTubes").append(": ").append(recordTubesCount).append("\n");
        builder.append("barcode").append(": ").append(barcode).append("\n");
        builder.append("dateTime").append(": ").append(dateTime).append("\n");
        builder.append("value").append(": ").append(value).append("\n");
        //builder.append("flags").append(": ").append().append("\n");
        builder.append("cycle").append(": ").append(cycleCount).append("\n");
        builder.append("isValid").append(": ").append(isValidFrame).append("\n");

        return builder.toString().trim();
    }
}
