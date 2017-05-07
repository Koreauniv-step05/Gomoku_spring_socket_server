package com.asuscomm.yangyinetwork.websocket.channel.service;

import com.asuscomm.yangyinetwork.websocket.channel.domain.Channel;
import com.asuscomm.yangyinetwork.websocket.channel.domain.CommandReply;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.manager.ChannelControllerManagerImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.websocket.channel.controller.consts.Commands.TO_CLIENT.JOINED_CHANNEL;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class ChannelService {
    private static ChannelService mInstance;
    private List<Channel> channels;

    public ChannelService() {
        log.info("ChannelService/ChannelService: ");
        this.channels = new ArrayList<>();
        channels.add(new Channel("1ba"));
        channels.get(0).addUser();
    }

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
        return channels; // stub
    }

    public void enableChannel(Channel channel) {
        String channelId = channel.getId();

        log.info("ChannelService/enableChannel: makeNewChannelController [{}]",channelId);
        ChannelControllerManagerImpl.getInstance().makeNewChannelController(channelId);
    }

    public CommandReply joinChannel(Channel channel) {
        // join
        // if success
        channel.addUser();

        if (Channel.Status.NEEDS_ONLY_OBSERVER.equals(channel.getStatus())) {
            log.info("ChannelService/joinChannel: ");
            this.enableChannel(channel);
        }

        String message = "You're "+JOINED_CHANNEL+" to "+channel.toString();
        log.info("GeneralCommandService/joinChannel: [{}]",message);
        CommandReply commandReply = new CommandReply(JOINED_CHANNEL, message, channel);
        return commandReply;
    }
}
