package com.anitha.offsitefinal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.PopupMenu;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;

public class MapaClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton img_menu;
    Button mapaclient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);
        TypefaceUtil.overrideFont(getApplicationContext(), "GILLSANSSTD-LIGHT", "fonts/GillSansStd-Light.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        mapaclient = (Button) findViewById(R.id.mapclientbtn);
        mapaclient.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                                 Intent i = new Intent(MapaClientActivity.this, ScannedBarcodeActivity.class);
                                              startActivity(i);
                                          }
                                      });







        img_menu = (ImageButton) findViewById(R.id.menu);

        img_menu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(MapaClientActivity.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MapaClientActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu

            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        ImageButton menuRight = (ImageButton) findViewById(R.id.menu);
        menuRight.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView2.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(MapaClientActivity.this, DividerItemDecoration.VERTICAL));
        navigationView2.setNavigationItemSelectedListener(this);

        //nav font change
        Menu m = navigationView2.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }

    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "arial.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String text = "";

        if (id == R.id.schedule) {
            Intent schedule = new Intent(MapaClientActivity.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(MapaClientActivity.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(MapaClientActivity.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(MapaClientActivity.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(MapaClientActivity.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(MapaClientActivity.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(MapaClientActivity.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(MapaClientActivity.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(MapaClientActivity.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(MapaClientActivity.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(MapaClientActivity.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}

