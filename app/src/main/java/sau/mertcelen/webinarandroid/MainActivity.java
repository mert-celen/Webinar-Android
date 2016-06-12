package sau.mertcelen.webinarandroid;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
private VideoView  presenterView,guestView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        String presenter = "rtsp://185.7.3.9:1935/WebinarEdge/6026_presenter";
        String guest = "rtsp://185.7.3.9:1935/WebinarEdge/6026_guest";

        presenterView = (VideoView)findViewById(R.id.presenterView);
        presenterView.setVideoPath(presenter);
        presenterView.requestFocus();

        Thread thread1=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                Log.i("mert","VIDEO1 SIGNAL");
                presenterView.start();
                Log.i("mert","VIDEO1 START");
            }
        });
        thread1.start();

        guestView = (VideoView)findViewById(R.id.guestView);
        guestView.setVideoPath(guest);
        guestView.requestFocus();

        Thread thread2=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                Log.i("mert","VIDEO1 SIGNAL");
                guestView.start();
                Log.i("mert","VIDEO1 START");
            }
        });
        thread2.start();
    }
}
