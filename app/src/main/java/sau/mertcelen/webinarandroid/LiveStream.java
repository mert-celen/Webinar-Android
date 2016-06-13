package sau.mertcelen.webinarandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;

public class LiveStream extends AppCompatActivity {
    private VideoView  presenterView,guestView;
    private Thread thread1,thread2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        String _eventID = String.valueOf(MainActivity._eventID);

        String presenter = "rtsp://webinar.sakarya.edu.tr:1935/Webinar/"+ _eventID +"_presenter";
        String guest = "rtsp://webinar.sakarya.edu.tr:1935/Webinar/" + _eventID + "_guest";
        Log.i("mert","Event ID : " + _eventID);
        Log.i("mert","Presenter URL : " + presenter);
        Log.i("mert","Guest URL : " + guest);
        presenterView = (VideoView)findViewById(R.id.presenterView);
        presenterView.setVideoPath(presenter);
        presenterView.requestFocus();

        thread1=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                for(int i=0;i <=10;i++){
                    if(presenterView.isPlaying()==false){
                        presenterView.start();
                    }
                }

            }
        });
        thread1.start();

        guestView = (VideoView)findViewById(R.id.guestView);
        guestView.setVideoPath(guest);
        guestView.requestFocus();

        thread2=new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                guestView.start();
            }
        });
        thread2.start();
    }
//
//    @Override
//    public void onBackPressed() {
//        Log.i("mert","Destroying current event!");
//        thread1.stop();
//        thread2.stop();
//        moveTaskToBack(true);
//    }
}
