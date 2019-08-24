package com.anitha.offsitefinal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;

public class DayChecklistSummary  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton img_menu,camera_clk1;
    private MyCustomAdapter mAdapter;

    Button with_camera, without_camera_btn, with_cal_ques, with_radio_ques;
    String result;
    ListView dataList;
    Button checklisttypesubmit,checklisttypecancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_checklist_summary);


        checklisttypecancel=(Button)findViewById(R.id.checklisttypecancel);
        checklisttypesubmit=(Button)findViewById(R.id.checklisttypesubmit);

        checklisttypesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.checklisttypesubmit:

                        if (checklisttypesubmit == null) {
                            checklisttypesubmit = (Button) findViewById(v.getId());
                        } else {
                            checklisttypesubmit.setBackgroundResource(R.drawable.btncancelstyle);
                            checklisttypesubmit.setTextColor(getResources().getColor(R.color.white));
                            checklisttypesubmit = (Button) findViewById(v.getId());
                        }
                        checklisttypesubmit.setBackgroundDrawable(dr);
                        Intent intent=new Intent(getApplicationContext(),HomeActivityMenu.class);
                        startActivity(intent);
                        break;


                    default:
                        break;
                }


            }
        });


        checklisttypecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.checklisttypecancel:

                        if (checklisttypecancel == null) {
                            checklisttypecancel = (Button) findViewById(v.getId());
                        } else {
                            checklisttypecancel.setBackgroundResource(R.drawable.btncancelstyle);
                            checklisttypecancel.setTextColor(getResources().getColor(R.color.white));
                            checklisttypecancel = (Button) findViewById(v.getId());
                        }
                        checklisttypecancel.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });


        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"arial.ttf",true);

        img_menu = (ImageButton) findViewById(R.id.menu);
        img_menu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(DayChecklistSummary.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                DayChecklistSummary.this,
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
        navMenuView.addItemDecoration(new DividerItemDecoration(DayChecklistSummary.this, DividerItemDecoration.VERTICAL));
        navigationView2.setNavigationItemSelectedListener(this);

        dataList = (ListView) findViewById(R.id.list);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));

        mAdapter = new MyCustomAdapter(DayChecklistSummary.this);
        mAdapter.addItem(new ListItem(result));
        scrollMyListViewToBottom();
        mAdapter.addItem1(new ListItem(result));
        scrollMyListViewToBottom();
        mAdapter.addItem2(new ListItem(result));
        scrollMyListViewToBottom();
        mAdapter.addItem3(new ListItem(result));
        scrollMyListViewToBottom();

        dataList.setAdapter(mAdapter);

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
            Intent schedule = new Intent(DayChecklistSummary.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(DayChecklistSummary.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(DayChecklistSummary.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(DayChecklistSummary.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(DayChecklistSummary.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(DayChecklistSummary.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(DayChecklistSummary.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(DayChecklistSummary.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(DayChecklistSummary.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(DayChecklistSummary.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(DayChecklistSummary.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void scrollMyListViewToBottom() {

        dataList.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                dataList.setSelection(mAdapter.getCount() - 1);
            }
        });
    }
}
