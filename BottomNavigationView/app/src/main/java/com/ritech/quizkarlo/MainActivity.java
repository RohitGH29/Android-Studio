package com.ritech.quizkarlo;

import static com.ritech.quizkarlo.R.id.nav_home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ritech.quizkarlo.Fragments.AccountFragment;
import com.ritech.quizkarlo.Fragments.HomeFragment;
import com.ritech.quizkarlo.Fragments.LeaderBoardFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView drawerProfileName, drawerProfileText;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;

    // for app update notification
    public static int UPDATE_CODE = 22;
    AppUpdateManager appUpdateManager;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            setFragment(new HomeFragment());
                            return true;

                        case R.id.navigation_leaderBoard:
                            setFragment(new LeaderBoardFragment());
                            return true;

                        case R.id.navigation_Account:
                            setFragment(new AccountFragment());
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase notification
        FirebaseMessaging.getInstance().subscribeToTopic("a");

        // update app
        inAppUp();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Categories");

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        main_frame = findViewById(R.id.fragment_container);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerProfileName=navigationView.getHeaderView(0).findViewById(R.id.nav_drawer_name);
        drawerProfileText=navigationView.getHeaderView(0).findViewById(R.id.nav_drawer_text_img);

        String name= DbQuery.myProfile.getName();
        Log.d("itsapp", "Date Minutes:" + name );
        drawerProfileText.setText(name.toUpperCase().substring(0,1));

        drawerProfileName.setText(name);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.nav_open,R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(nav_home);
        }


        // bottom nav bar
        setFragment(new HomeFragment());


        // This when app start it's show home fragment firstly
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }


    private void inAppUp() {
        appUpdateManager = AppUpdateManagerFactory.create(this);

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> task = appUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE))
                {
                    try {
                        appUpdateManager.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.FLEXIBLE
                                ,MainActivity.this,UPDATE_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                        Log.d("itsapp","onSucess: "+ e.toString());
                    }
                }
            }
        });
        appUpdateManager.registerListener(listener);
    }

    InstallStateUpdatedListener listener = installState -> {

        if (installState.installStatus()== InstallStatus.DOWNLOADED){
            popUp();
        }
    };

    private void popUp() {

        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content),"App Update Almost Done",
                Snackbar.LENGTH_INDEFINITE
        );

        snackbar.setAction("Reload", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(Color.parseColor("#FF0000"));
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_CODE){

            if (resultCode != RESULT_OK){

            }
        }
    }





    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {

       int id = item.getItemId();

       if (id== nav_home)
       {
           //setFragment(new HomeFragment());
           bottomNavigationView.setSelectedItemId(R.id.navigation_home);
       }
       else if(id==R.id.nav_leaderBoard)
       {
           //setFragment(new LeaderBoardFragment());
          bottomNavigationView.setSelectedItemId(R.id.navigation_leaderBoard);

       }
       else if(id==R.id.nav_Account)
       {
          //setFragment(new AccountFragment());
          bottomNavigationView.setSelectedItemId(R.id.navigation_Account);
       }
       else if(id==R.id.nav_share)
       {
           Intent shareIntent = new Intent(Intent.ACTION_SEND);
           shareIntent.setType("text/plain");
           String shareBody = " Download Quiz app :  https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
           shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
           startActivity(Intent.createChooser(shareIntent,"Share Via"));
       }
       else if(id==R.id.nav_feedback)
       {
           Intent launchEmailAppIntent = new Intent(Intent.ACTION_SENDTO);
           launchEmailAppIntent.setData(Uri.parse("mailto:channelone17@gmail.com")); // only email app handle this
           startActivity(launchEmailAppIntent);

       }
       else if (id == R.id.nav_Bookmark)
       {
           Intent intent = new Intent(MainActivity.this,BookMarksActivity.class);
           startActivity(intent);
       }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(),fragment);
        transaction.commit();
    }


}