package com.asuscomm.yangyinetwork.websocket.service;

import com.asuscomm.yangyinetwork.websocket.domain.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class ChannelService {
    private static ChannelService mInstance;

    public static ChannelService getInstance() {
        if(mInstance == null) {
            mInstance = new ChannelService();
        }
        return mInstance;
    }

    public List<Channel> findEmptyChannels() {
        List<Channel> emptyChannels = new ArrayList<>();
        List<Channel> channels = findChannels();

        for (Channel channel:
             channels) {
            if (Channel.Status.NEEDS_MORE_USER.equals(channel.getStatus())) {
                emptyChannels.add(channel);
            }
        }

        log.info("ChannelService/findEmptyChannels: [{}]", emptyChannels.toString());
        return emptyChannels;  // non stub
    }

    public List<Channel> findChannels() {
        List<Channel> channels = new ArrayList<>();
        channels.add(new Channel("1ba", Channel.Status.NEEDS_MORE_USER));
        channels.add(new Channel("2bc", Channel.Status.ALREADY_STARTED));
        channels.add(new Channel("3ba", Channel.Status.NEEDS_ONLY_OBSERVER));

        return channels; // stub
    }
}
