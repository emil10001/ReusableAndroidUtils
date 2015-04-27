package io.hearty.utils.audio;

/**
 * Created by ejf3 on 4/25/15.
 */
public class AudioMetaData {
    private final String filename;
    private final String title;
    private final String artist;
    private final String album;
    private final String artPath;
    private final String albumArtist;
    private final int trackNum;
    private final int totalTracks;

    public static class Builder {
        private String filename;
        private String title;
        private String artist;
        private String album;
        private String artPath;
        private String albumArtist;
        private int trackNum;
        private int totalTracks;

        public Builder withFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder withAlbum(String album) {
            this.album = album;
            return this;
        }

        public Builder withArtPath(String artPath) {
            this.artPath = artPath;
            return this;
        }

        public Builder withAlbumArtist(String albumArtist) {
            this.albumArtist = albumArtist;
            return this;
        }

        public Builder withTrackNum(int trackNum) {
            this.trackNum = trackNum;
            return this;
        }

        public Builder withTotalTracks(int totalTracks) {
            this.totalTracks = totalTracks;
            return this;
        }

        private Builder() {
        }

        public AudioMetaData build() {
            return new AudioMetaData(filename, title, artist, album, artPath, albumArtist, trackNum, totalTracks);
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private AudioMetaData(String filename, String title, String artist, String album, String artPath, String albumArtist, int trackNum, int totalTracks) {
        this.filename = filename;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.artPath = artPath;
        this.albumArtist = albumArtist;
        this.trackNum = trackNum;
        this.totalTracks = totalTracks;
    }

    public String getFilename() {
        return filename;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtPath() {
        return artPath;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public int getTrackNum() {
        return trackNum;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

}
