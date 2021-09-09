package com.kaurpalang.mirth.cube30touchtcp.shared.config;

import java.util.regex.Pattern;

public class Constants {
    public static final String PLUGIN_POINTNAME = "Cube30Touch";
    public static final String DIALOG_BORDER_TITLE = "Cube30 Mode";
    public static final String DIALOG_TITLE = "Cube 30 Mode transmission settings";
    public static final String DIALOG_LOGGER_LEVEL_TOOLTIP = "Select logger level to use when logging rejected frames";

    public static final Pattern FRAME_TEXT_REGEX = Pattern.compile(">00(?<dataBytesCount>\\d{2})0151(?<recordTubesCount>\\d{2})(?<barcode>[a-zA-Z]\\d+)?\u0010(?<date>\\d{6})(?<time>\\d{4})(?<value>(?:>| {1,3})\\d{1,3})(?<flags>\\d{2})0000(?<cycle>[0-9a-fA-F]{2})[\\n\\r](?<checksum>[0-9a-fA-F]{2})");

    private Constants() {}
}
