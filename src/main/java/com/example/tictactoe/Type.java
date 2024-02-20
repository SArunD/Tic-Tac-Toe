package com.example.tictactoe;

public enum Type {
    UNSELECTED("None"),
    PLAYER("PvP"),
    AI("PvAI");

    private final String id;

    Type(String id) {
        this.id = id;
    }
}
