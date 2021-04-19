package com.kaurpalang.mirth.cube30touchtcp.shared.model;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cubeFrames")
public class CubeFrames {
    @XmlElement(name = "cubeFrame")
    @Setter List<CubeFrame> cubeFrameList;

    public void add(CubeFrame frame) {
        if (this.cubeFrameList == null) {
            this.cubeFrameList = new ArrayList<>();
        }
        this.cubeFrameList.add(frame);
    }
}
