package com.asuscomm.yangyinetwork.websocket.service;

import com.asuscomm.yangyinetwork.websocket.domain.Channel;
import com.asuscomm.yangyinetwork.websocket.domain.CommandReply;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.asuscomm.yangyinetwork.websocket.controller.consts.Commands.TO_CLIENT.JOINED_CHANNEL;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class GeneralCommandService {
    private static GeneralCommandService mInstance;

    public static GeneralCommandService getInstance() {
        if(mInstance == null) {
            mInstance = new GeneralCommandService();
        }
        return mInstance;
    }

    public CommandReply joinSomewhere() {
        List<Channel> emptyChannels = ChannelService.getInstance().findEmptyChannels();
        if(emptyChannels.size() > 0) {
            log.info("GeneralCommandService/joinSomewhere: [{}]",emptyChannels.get(0).toString());
            return joinChannel(emptyChannels.get(0));
        } else {
            return null;
        }
    }

    public CommandReply joinChannel(Channel channel) {
        // join
        // if success
        String message = "You're "+JOINED_CHANNEL+" to "+channel.toString();
        log.info("GeneralCommandService/joinChannel: [{}]",message);
        CommandReply commandReply = new CommandReply(JOINED_CHANNEL, message, channel);
        return commandReply;
    }
}
