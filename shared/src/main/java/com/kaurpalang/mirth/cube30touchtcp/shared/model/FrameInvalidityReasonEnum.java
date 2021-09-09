package com.kaurpalang.mirth.cube30touchtcp.shared.model;

public enum FrameInvalidityReasonEnum {
    TEXTUAL_REPRESENTATION_FAULT("Frame does not match pattern"),
    CHECKSUM_FAULT("Stated and calculated checksums do not match");

    public final String description;

    private FrameInvalidityReasonEnum(String description) {
        this.description = description;
    }
}
