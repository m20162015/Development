package com.splashpapers.chalktalk.events.model;

import java.io.Serializable;

/**
 * Created by manishsharma on 11/30/16.
 */
public class EventsVO implements Serializable{

    private String noticeId;
    private String noticeTitle;
    private String notice;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
