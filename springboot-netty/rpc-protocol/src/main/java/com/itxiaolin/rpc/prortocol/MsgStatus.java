package com.itxiaolin.rpc.prortocol;

import lombok.Getter;

public enum MsgStatus {
    SUCCESS(0),
    FAIL(1);

    @Getter
    private final int code;

    MsgStatus(int code) {
        this.code = code;
    }

}