package com.splashpapers.chalktalk.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.aboutus.AboutUsActivity;
import com.splashpapers.chalktalk.app.Constants;
import com.splashpapers.chalktalk.app.SplashScreen;
import com.splashpapers.chalktalk.attendance.AttendanceActivity;
import com.splashpapers.chalktalk.events.EventsActivity;
import com.splashpapers.chalktalk.examtimetable.ExamTimeTableActivity;
import com.splashpapers.chalktalk.fees.FeesActivity;
import com.splashpapers.chalktalk.food.FoodActivity;
import com.splashpapers.chalktalk.homework.HomeWorkActivity;
import com.splashpapers.chalktalk.mediagallery.MediaGalleryActivity;
import com.splashpapers.chalktalk.mykids.MyKidsActivity;
import com.splashpapers.chalktalk.myprofile.MyProfileActivity;
import com.splashpapers.chalktalk.notices.NoticesActivity;
import com.splashpapers.chalktalk.projects.ProjectsActivity;
import com.splashpapers.chalktalk.results.ResultsActivity;
import com.splashpapers.chalktalk.timetable.TimeTableActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            SharedPreferences sharedPref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPref.edit();
            edit.putBoolean(Constants.LOGIN, false);
            edit.commit();

            Intent intent = new Intent(this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            finish();
            startActivity(intent);
        } else if (id == R.id.nav_abt_us) {
            Intent intent = new Intent(HomeActivity.this,AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_timetable) {
            Intent intent = new Intent(HomeActivity.this,TimeTableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_homework) {
            Intent intent = new Intent(HomeActivity.this,HomeWorkActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_notice) {
            Intent intent = new Intent(HomeActivity.this,NoticesActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_attendance) {
            Intent intent = new Intent(HomeActivity.this,AttendanceActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_events) {
            Intent intent = new Intent(HomeActivity.this,EventsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_foodtime) {
            Intent intent = new Intent(HomeActivity.this,FoodActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_projects) {
            Intent intent = new Intent(HomeActivity.this,ProjectsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_media_gallery) {
            Intent intent = new Intent(HomeActivity.this,MediaGalleryActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_exam_timetable) {
            Intent intent = new Intent(HomeActivity.this,ExamTimeTableActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_result) {
            Intent intent = new Intent(HomeActivity.this,ResultsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fees) {
            Intent intent = new Intent(HomeActivity.this,FeesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_kids) {
            Intent intent = new Intent(HomeActivity.this,MyKidsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_myprofile) {
            Intent intent = new Intent(HomeActivity.this,MyProfileActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
