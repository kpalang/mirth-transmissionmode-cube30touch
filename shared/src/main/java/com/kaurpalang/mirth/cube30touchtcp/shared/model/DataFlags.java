package com.kaurpalang.mirth.cube30touchtcp.shared.model;

import java.util.HashMap;
import java.util.Map;

public class DataFlags {
    //public static final int NO_ERR = 0x00;
    public static final int SAMPLE_HIGH = 0x01;
    public static final int SAMPLE_LOW = 0x02;
    public static final int SAMPLE_ABSENT = 0x04;
    public static final int READING_ERROR = 0x08;
    public static final int QC_PASS = 0x10;
    public static final int QC_FAIL = 0x20;
    public static final int RESERVED_1 = 0x40;
    public static final int RESERVED_2 = 0x80;

    public static final Map<Integer, String> flagToTextMap = new HashMap<Integer, String>() {{
        //put(NO_ERR, "No error");
        put(SAMPLE_HIGH, "Sample High");
        put(SAMPLE_LOW, "Sample Low");
        put(SAMPLE_ABSENT, "Sample Absent");
        put(READING_ERROR, "Reading Error");
        put(QC_PASS, "QC Pass");
        put(QC_FAIL, "QC Fail");
        put(RESERVED_1, "Reserved 1");
        put(RESERVED_2, "Reserved 2");
    }};
}
