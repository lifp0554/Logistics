package com.ideacome.logistics.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 快递100状态
 */
public enum LogisticsStatus {
    ON_THE_WAY(0, "在途"),
    PICK_UP(1, "揽件"),
    DIFFICULTIES(2, "疑难"),
    SIGN_UP(3, "签收"),
    SIGN_OUT(4, "退签"),
    SEND(5, "派件"),
    RETURN(6, "退回");

    private int statusCode;
    private String status;
    private static final Map<Integer, LogisticsStatus> lookup = new HashMap<Integer, LogisticsStatus>();

    LogisticsStatus(int statusCode, String status) {
        this.statusCode = statusCode;
        this.status = status;
    }

    public static LogisticsStatus getLogisticsStatus(int statusCode) {
        return lookup.get(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    static {
        for (LogisticsStatus logisticsStatus : EnumSet.allOf(LogisticsStatus.class)) {
            lookup.put(logisticsStatus.getStatusCode(), logisticsStatus);
        }
    }
}
