package dev.redstones.mediaplayerinfo;

import java.util.Collections;
import java.util.List;

public class MediaPlayerInfo {
    public static final MediaPlayerInfo Instance = new MediaPlayerInfo();

    public List<IMediaSession> getMediaSessions() {
        return Collections.emptyList();
    }
}
