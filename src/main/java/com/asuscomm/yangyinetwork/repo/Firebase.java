package com.asuscomm.yangyinetwork.repo;

import com.asuscomm.yangyinetwork.websocket.channel.domain.Channel;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.Task;
import org.apache.commons.lang3.ArrayUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static com.asuscomm.yangyinetwork.config.FirebaseConfig.*;

/**
 * Created by jaeyoung on 2017. 5. 9..
 */
public class Firebase {
    private static Firebase mInstance;
    private DatabaseReference ref;

    public static Firebase getInstance() {
        if (mInstance == null) {
            mInstance = new Firebase();
        }
        return mInstance;
    }

    public Firebase() {
        FirebaseOptions options = null;

        try {
            options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream(JSON_PATH))
                    .setDatabaseUrl(DB_PATH)
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(options);
        ref = FirebaseDatabase
                .getInstance()
                .getReference(DB_ENDPOINT);
    }

    public String saveGame() {
        Channel channel = new Channel("1");
        DatabaseReference pushed = ref.push();
        String key = pushed.getKey();
        pushed.setValue(channel);
        return key;
    }

    public void saveStonePoint(String key,int[] newStonePoint, int stoneType) {
        StonePointForDB stonePoint = new StonePointForDB(newStonePoint, stoneType);
        ref.child(key).child(DB_STONEPOINT).push().setValue(stonePoint);
    }

    public void saveBoard(String key,int[][] board) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] array : board) {
            Integer[] intArray = ArrayUtils.toObject(array);
            list.add(Arrays.asList(intArray));
        }

        ref.child(key).child(DB_BOARD).setValue(list);
    }
}
