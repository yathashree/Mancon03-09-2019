package com.anitha.offsitefinal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.PopupMenu;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;
import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;
import static java.util.Calendar.YEAR;

public class ScheduleVisits extends AppCompatActivity  implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] NameList;
    ArrayList<Names> arraylist = new ArrayList<Names>();


    CalendarPickerView calendar;
    TextView selected_dates, txt_date_range;
    Dialog myDialog;
    Button btncancel;
    ImageButton img_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulevisit);

//
//        InputMethodManager imm = (InputMethodManager)
//                getSystemService(Context.INPUT_METHOD_SERVICE);
//        if(imm != null){
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
//        }
//

        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }





        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"arial.ttf",true);
      //  TypefaceUtil.overrideFont(getApplicationContext(), "GILLSANSSTD-LIGHT", "fonts/GillSansStd-Light.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        btncancel=(Button)findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScheduleVisits.this,ScheduleVisits.class);
                startActivity(intent);

                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.btncancel:

                        if (btncancel == null) {
                            btncancel = (Button) findViewById(v.getId());
                        } else {
                            btncancel.setBackgroundResource(R.drawable.btncancelstyle);
                            btncancel.setTextColor(getResources().getColor(R.color.white));
                            btncancel = (Button) findViewById(v.getId());
                        }
                        btncancel.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });


        myDialog = new Dialog(this);

        // Generate sample data

        NameList = new String[]{"Shia", "Kiran", "Tharun",
                "Parul", "Payal", "Rahul", "Rakesh", "Raghu",
                "Amar", "Arun", "Anil"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i <NameList.length; i++) {
            Names Names = new Names(NameList[i]);
            // Binds all strings into an array
            arraylist.add(Names);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        editsearch.setQueryHint(Html.fromHtml("<font colo=#000000>" + getResources().getString(R.string.query_hint_search) + "</font>"));




        // editsearch.setIconified(false);
        //change icon color
     //  ImageView searchIcon = editsearch.findViewById(android.support.v7.appcompat.R.id.search_button);
      // searchIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_search));
        list = (ListView) findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_LONG).show();
                myDialog.setContentView(R.layout.cal_custompopup);
                Calendar nextYear = Calendar.getInstance();
                calendar = (CalendarPickerView) myDialog.findViewById(R.id.calendarPickerView);


                TextView txt_current = (TextView) myDialog.findViewById(R.id.current_date);

                long date = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy h:mm a");
                String dateString = sdf.format(date);
                txt_current.setText(dateString);


                nextYear.add(YEAR, 1);

                Calendar prevYear = Calendar.getInstance();
                prevYear.add(YEAR, -1);


                final Date today = new Date();
                // calendar.init(today,nextYear.getTime()).inMode(RANGE);
                // calendar.init(today, nextYear.getTime()).inMode(RANGE);

                calendar.init(prevYear.getTime(), nextYear.getTime()).inMode(RANGE);
                calendar.selectDate(today);


                txt_date_range = (TextView) findViewById(R.id.nameLabel);

                calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(Date date) {
                        //Do something when dates get selected
                        //    selected_dates.setText((CharSequence) calendar);

                        //Toast.makeText(getApplicationContext(), "Selected Date is : " + date.toString(), Toast.LENGTH_SHORT).show();
                        Intent act=new Intent(ScheduleVisits.this,ScheduleVisits2.class);
                        startActivity(act);
                        //   txt_date_range.setText(date + "/" + today);

                        // txt_date_range.setText(int);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                        String DAY = simpleDateFormat.format((date));
                        //  txt_date_range.setText(selected_dates + "/" + DAY);

                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("M");

                        String MONTH = simpleDateFormat1.format((date));

                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("y");

                        String YEAR = simpleDateFormat2.format((date));


                        //  System.out.println("DAY "+simpleDateFormat.format(date).toUpperCase());


                        //fetch dates
                        List<Date> dates = calendar.getSelectedDates();

                        for (int i = 0; i < calendar.getSelectedDates().size(); i++) {

                            Date from = calendar.getSelectedDate();
                            SimpleDateFormat form = new SimpleDateFormat("d/M/y");
                            String select = form.format((from));
                            //   String select=calendar.getSelectedDate().toString();


                            //here you can fetch all dates
                           // Toast.makeText(getApplicationContext(), calendar.getSelectedDates().get(i).toString(), Toast.LENGTH_SHORT).show();

                       //     txt_date_range.setText(select + "-" + DAY + "/" + MONTH + "/" + YEAR);
                        }

                    }

                    @Override
                    public void onDateUnselected(Date date) {

                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

            }


        });

        img_menu = (ImageButton) findViewById(R.id.menu);

        img_menu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(ScheduleVisits.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                ScheduleVisits.this,
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
        navMenuView.addItemDecoration(new DividerItemDecoration(ScheduleVisits.this, DividerItemDecoration.VERTICAL));
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
            Intent schedule = new Intent(ScheduleVisits.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(ScheduleVisits.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(ScheduleVisits.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(ScheduleVisits.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(ScheduleVisits.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(ScheduleVisits.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(ScheduleVisits.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(ScheduleVisits.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(ScheduleVisits.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(ScheduleVisits.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(ScheduleVisits.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }






    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

   /* public void Show(View view) {
        Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
    }*/
}


