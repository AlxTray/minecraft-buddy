package com.alxtray.minecraftbuddy.interfaces;

import com.alxtray.minecraftbuddy.ModelResponse;

public interface ResponseSubscriber {
    void onResponseAsync(ModelResponse modelResponse);
    void onResponse(ModelResponse modelResponse);
}
