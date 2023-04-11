package com.crush.ps.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author crush
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementMessage implements Serializable {

    private static final long serialVersionUID = 8632296967087444509L;

    /**
     * 公告信息id
     */
    private String id;

    /**
     * 公告内容
     */
    private String content;
}
