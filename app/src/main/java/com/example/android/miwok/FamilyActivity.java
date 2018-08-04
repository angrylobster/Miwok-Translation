package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> familyMembers = new ArrayList<Word>();

        familyMembers.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyMembers.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyMembers.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyMembers.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyMembers.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyMembers.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyMembers.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyMembers.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyMembers.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyMembers.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter wordAdapter = new WordAdapter(this, familyMembers, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);
//        GridView gridView = (GridView) findViewById(R.id.gridview);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, familyMembers.get(i).getmAudioResourceID());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
