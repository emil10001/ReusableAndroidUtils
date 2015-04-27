package io.hearty.utils.audio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by ejf3 on 4/25/15.
 */
public class AudioCapture {
    private static final String LOG_TAG = "AudioRecordTest";

    // http://developer.android.com/reference/android/media/MediaRecorder.html
    private final MediaRecorder mRecorder = new MediaRecorder();
    // http://developer.android.com/reference/android/media/MediaPlayer.html
    private MediaPlayer mPlayer = null;
    private AudioConfig config = null;

    public AudioCapture(AudioConfig config) {
        this.config = config;
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(config.getFile().getPath());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder.reset();
        configRecorder();

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void configRecorder() {
        mRecorder.setAudioSource(config.getSource());
        mRecorder.setOutputFormat(config.getOutputFormat());
        mRecorder.setOutputFile(config.getFile().getPath());
        mRecorder.setAudioEncoder(config.getEncoding());
        mRecorder.setAudioChannels(config.getNumChannels());
        mRecorder.setAudioSamplingRate(config.getSampleRate());
        mRecorder.setAudioEncodingBitRate(config.getBitRate());
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder.reset();
    }


    private void toggleRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void togglePlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }



}
