package com.example.eventhub.domain;

import java.io.Serializable;

public enum TicketState implements Serializable {
    AVAILABLE,
    RESERVED,
    BOUGHT,
    CANCELLED
}