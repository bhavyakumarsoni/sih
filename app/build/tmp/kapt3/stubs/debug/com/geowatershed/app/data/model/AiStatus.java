package com.geowatershed.app.data.model;

/**
 * Lifecycle of an AI suggestion for one capture.
 *
 * Note there is no state in which an AI suggestion is simply "true". It is
 * Suggested until a human presses Confirm or Reject; nothing downstream reads
 * it as fact before that, and nothing reads it as a priority input ever.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/geowatershed/app/data/model/AiStatus;", "", "(Ljava/lang/String;I)V", "NotRequested", "Queued", "Running", "Suggested", "Failed", "Confirmed", "Rejected", "app_debug"})
public enum AiStatus {
    /*public static final*/ NotRequested /* = new NotRequested() */,
    /*public static final*/ Queued /* = new Queued() */,
    /*public static final*/ Running /* = new Running() */,
    /*public static final*/ Suggested /* = new Suggested() */,
    /*public static final*/ Failed /* = new Failed() */,
    /*public static final*/ Confirmed /* = new Confirmed() */,
    /*public static final*/ Rejected /* = new Rejected() */;
    
    AiStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.geowatershed.app.data.model.AiStatus> getEntries() {
        return null;
    }
}