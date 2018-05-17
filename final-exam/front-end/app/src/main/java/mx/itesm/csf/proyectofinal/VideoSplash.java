package mx.itesm.csf.proyectofinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;

import static android.support.v4.content.ContextCompat.startActivity;

public class VideoSplash extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    final Random rnd = new Random();
    String splashName;                                  /* Variable to access the image or video for the splash */
    int drawableId;
    String TAG = "SPLASH";
    String splash_url;

    private MyPlayerStateChangeListener playerStateChangeListener;

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    public static final String YOUTUBE_API_KEY = "AIzaSyANjY-J69nRSIaCPKuBr380BQNLT4KrxPk";

    String[] videoArray = {"GvIjLHGLUxE", "CNgsZjXXOZk", "7QUCJD388uM"};
    int vidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YOUTUBE_API_KEY, this);
        vidId = rnd.nextInt(500) % 3;

        playerStateChangeListener = new MyPlayerStateChangeListener();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        player.setPlayerStateChangeListener(playerStateChangeListener);

        if (!wasRestored) {
            player.cueVideo(videoArray[vidId]);
            player.loadVideo(videoArray[vidId]);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    }
}

