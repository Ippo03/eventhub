package com.example.eventhub.domain;

import java.io.Serializable;

public enum EventType implements Serializable {
    FREE,
    CLOSED,
    OPEN;
}