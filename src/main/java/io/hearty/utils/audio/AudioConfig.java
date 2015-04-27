package io.hearty.utils.audio;

import android.media.MediaRecorder;
import io.hearty.utils.files.AndroidFileUtils;

import java.io.File;

/**
 * Created by ejf3 on 4/25/15.
 */
public class AudioConfig {
    private final String name;
    private final File path;
    private final AudioMetaData metaData;
    private final int bitRate;
    private final int numChannels;
    private final int sampleRate; // samples per second, 8 to 96 kHz
    private final int encoding; // e.g. MediaRecorder.AudioEncoder.AAC
    private final int outputFormat; // e.g. MediaRecorder.OutputFormat.MPEG_4
    private final int source; // e.g. MediaRecorder.AudioSource.MIC

    public static class Builder {
        private File path = AndroidFileUtils.getDir();
        private String name = "tmp";
        private int sampleRate = 32000; // samples per second, 8 to 96 kHz
        private int bitRate = 96000;
        private int numChannels = 1;
        private AudioMetaData metaData;
        private int encoding = MediaRecorder.AudioEncoder.AAC; // e.g. MediaRecorder.AudioEncoder.AAC
        private int outputFormat = MediaRecorder.OutputFormat.MPEG_4; // e.g. MediaRecorder.OutputFormat.MPEG_4
        private int source = MediaRecorder.AudioSource.MIC; // e.g. MediaRecorder.AudioSource.MIC

        private Builder(){}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPath(File path) {
            this.path = path;
            return this;
        }

        public Builder withSampleRate(int sampleRate) {
            this.sampleRate = sampleRate;
            return this;
        }

        public Builder withBitRate(int bitRate) {
            this.bitRate = bitRate;
            return this;
        }

        public Builder withNumChannels(int numChannels) {
            this.numChannels = numChannels;
            return this;
        }

        public Builder withMetaData(AudioMetaData metaData) {
            this.metaData = metaData;
            return this;
        }

        public Builder withEncoding(int encoding) {
            this.encoding = encoding;
            return this;
        }

        public Builder withOutputFormat(int outputFormat) {
            this.outputFormat = outputFormat;
            return this;
        }

        public Builder withSource(int source) {
            this.source = source;
            return this;
        }

        public AudioConfig build(){
            return new AudioConfig(name, path, metaData, bitRate, numChannels,
                    sampleRate, encoding, outputFormat, source);
        }
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    private AudioConfig(String name, File path, AudioMetaData metaData,
                        int bitRate, int numChannels, int sampleRate,
                        int encoding, int outputFormat, int source) {
        this.name = name;
        this.path = path;
        this.metaData = metaData;
        this.bitRate = bitRate;
        this.numChannels = numChannels;
        this.sampleRate = sampleRate;
        this.encoding = encoding;
        this.outputFormat = outputFormat;
        this.source = source;
    }


    public File getFile() {
        if (null == path)
            return AndroidFileUtils.getFileInDefaultDir(name);
        return AndroidFileUtils.getFileInDir(path, name);
    }

    public File getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getBitRate() {
        return bitRate;
    }

    public int getNumChannels() {
        return numChannels;
    }

    public AudioMetaData getMetaData() {
        return metaData;
    }

    public int getEncoding() {
        return encoding;
    }

    public int getOutputFormat() {
        return outputFormat;
    }

    public int getSource() {
        return source;
    }
}
