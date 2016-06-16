package sau.mertcelen.webinarandroid;

import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class LiveStream extends AppCompatActivity {
    private static VideoView  presenterView,guestView;
    private static Thread thread1,thread2;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static boolean presenterFlag = true, guestFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.live_stream);

//      prevent starting activity without actually login. There won't be any problem without it but
//       this prevents app from crash.
        if(LoginActivity._code == null || LoginActivity._guestStream==null || LoginActivity._presenterStream==null){
            finish();
        }

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int type = getArguments().getInt(ARG_SECTION_NUMBER);
            if(type==1){
                View rootView = inflater.inflate(R.layout.activity_stream_video, container, false);
                String _eventID = String.valueOf(MainActivity._eventID);
                String presenter = LoginActivity._presenterStream;
                String guest = LoginActivity._guestStream;
                Log.i("mert","Event ID : " + _eventID);
                presenterView = (VideoView) rootView.findViewById(R.id.presenterView);
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

                guestView = (VideoView)rootView.findViewById(R.id.guestView);
                guestView.setVideoPath(guest);
                guestView.requestFocus();

                thread2=new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                        if(presenterView.isPlaying()==false){
                            guestView.start();
                        }
                    }
                });
                thread2.start();

//              for disabling popup error
                presenterView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        if(presenterFlag){
                            Toast.makeText(getContext(),"Presenter Not Available!",Toast.LENGTH_SHORT).show();
                            presenterFlag = false;
                        }

                        return true;
                    }
                });

                guestView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        if(guestFlag){
                            Toast.makeText(getContext(),"Guest Not Available!",Toast.LENGTH_SHORT).show();
                            guestFlag = false;
                        }
                        return true;
                    }
                });

                presenterView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        presenterView.setBackgroundColor(0);
                    }
                });

                guestView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        guestView.setBackgroundColor(0);
                    }
                });
                return rootView;
            }else{
                View rootView = inflater.inflate(R.layout.chat_layout, container, false);
                try{
                    WebView w = (WebView) rootView.findViewById(R.id.chatBrowser);
                    w.clearCache(true);
                    w.clearHistory();
                    w.getSettings().setJavaScriptEnabled(true);
                    String chatUrl = "http://webinarclient.sakarya.edu.tr/Live/WatcherMobile/" + MainActivity._eventID + "?code=" + LoginActivity._code;
                    w.loadUrl(chatUrl);
                    Log.i("mert",chatUrl);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return rootView;
            }

        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
