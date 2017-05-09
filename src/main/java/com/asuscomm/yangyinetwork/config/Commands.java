package com.asuscomm.yangyinetwork.config;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface Commands {

    interface GENERAL {
        interface TO_SERVER {
            String JOIN_CHANNEL = "JOIN_CHANNEL";
            String JOIN_SOMEWHERE = "JOIN_SOMEWHERE";
            String GET_CHANNELS = "GET_CHANNELS";
        }

        interface TO_CLIENT {
            String JOINED_CHANNEL = "JOINED_CHANNEL";
        }
    }

    interface CHANNEL {
        String ON_YOUR_TURN = "ON_YOUR_TURN";
        String ON_NEW_STONE = "ON_NEW_STONE";
        String ON_SYSTEM_MESSAGE = "ON_SYSTEM_MESSAGE";

        String ON_NEW_STONE_FROM_CLIENT = "ON_NEW_STONE_FROM_CLIENT";
    }
}
