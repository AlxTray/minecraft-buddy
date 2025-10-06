package com.alxtray.minecraftbuddy.interfaces;

public interface ResponseSubscriber {
    void onResponseAsync(Object response);
    void onResponse(Object response);
}
