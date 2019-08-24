package com.anitha.offsitefinal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;
import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;
import static java.util.Calendar.YEAR;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
public class EmployeeReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {






    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;



    ImageButton btn_popup;
    Button btn_cal_from, btn_cal_to;
    TextView txt_cal_from, txt_cal_to;
    DatePickerDialog datePickerDialog;
    ImageView img_menu, img_profile;


    ImageButton swipe;
    //  Context mContext
    ImageButton cal_range;

    //  Context mContext

    int year;
    int month;
    int dayOfMonth;
    TextView txt_cal_view;
    ListView list;


    CalendarPickerView calendar;
    TextView selected_dates;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_tab_main);

        img_menu = (ImageView) findViewById(R.id.menu);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);



        myDialog = new Dialog(this);


        cal_range = (ImageButton) findViewById(R.id.calendar);
        txt_cal_view = (TextView) findViewById(R.id.edit_cal);

        //Adding toolbar to the activity
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorHeight(0);
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.light_grey));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Employee Visit"));

        //tab indicator
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#a9a9a9"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));

        tabLayout.addTab(tabLayout.newTab().setText("Site Inspection"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        EmployeeReportsPager adapter = new EmployeeReportsPager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);




        Spinner spin_employee_client = (Spinner) findViewById(R.id.spin__g4s_client);
        String[] items1 = new String[]{"Select", "Bangalore", "Mysore", "Pune", "Hyderbad", "Delhi", "Gurgao"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_hub_row, items1)
        {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }


            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);
                v.setBackgroundColor(Color.WHITE);

                return v;
            }
        };


        spin_employee_client.setAdapter(adapter1);













        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(EmployeeReportActivity.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                EmployeeReportActivity.this,
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


        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView2.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(EmployeeReportActivity.this, DividerItemDecoration.VERTICAL));
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
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String text = "";

        if (id == R.id.dashboard) {
            Intent dashboard = new Intent(EmployeeReportActivity.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.schedule) {
            Intent schedule = new Intent(EmployeeReportActivity.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(EmployeeReportActivity.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(EmployeeReportActivity.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(EmployeeReportActivity.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(EmployeeReportActivity.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(EmployeeReportActivity.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(EmployeeReportActivity.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(EmployeeReportActivity.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(EmployeeReportActivity.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(EmployeeReportActivity.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }






    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void ShowPopup(View v) {
     /*   TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();*/

        myDialog.setContentView(R.layout.cal_custompopup);

//        Window view=((AlertDialog)myDialog).getWindow();
//        view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//// to get rounded corners and border for dialog window
//        view.setBackgroundDrawableResource(R.drawable.edittextstyle);

        Calendar nextYear = Calendar.getInstance();
        calendar = (CalendarPickerView) myDialog.findViewById(R.id.calendarPickerView);


        TextView txt_current=(TextView) myDialog.findViewById(R.id.current_date);

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MM yyyy");
        String dateString = sdf.format(date);
        txt_current.setText(dateString);


        DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);



        nextYear.add(YEAR, 1);

        Calendar prevYear = Calendar.getInstance();
        prevYear.add(YEAR,-1);


        final Date today = new Date();
        // calendar.init(today,nextYear.getTime()).inMode(RANGE);
        // calendar.init(today, nextYear.getTime()).inMode(RANGE);

        calendar.init(prevYear.getTime(),nextYear.getTime()).inMode(RANGE);
        calendar.selectDate(today);



        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //Do something when dates get selected
                //    selected_dates.setText((CharSequence) calendar);
            //    Toast.makeText(getApplicationContext(),"Selected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();
                //   txt_date_range.setText(date + "/" + today);

                // txt_date_range.setText(int);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                String DAY =   simpleDateFormat.format((date));
                //  txt_date_range.setText(selected_dates + "/" + DAY);

                SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("M");

                String MONTH =   simpleDateFormat1.format((date));

                SimpleDateFormat simpleDateFormat2= new SimpleDateFormat("y");

                String YEAR =   simpleDateFormat2.format((date));


                //  System.out.println("DAY "+simpleDateFormat.format(date).toUpperCase());


                //fetch dates
                List<Date> dates = calendar.getSelectedDates();

                for (int i = 0; i< calendar.getSelectedDates().size();i++){

                    Date from=calendar.getSelectedDate();
                    SimpleDateFormat form = new SimpleDateFormat("d/M/y");
                    String  select=   form.format((from));
                    //   String select=calendar.getSelectedDate().toString();


                    //here you can fetch all dates
                    //    Toast.makeText(getApplicationContext(),calendar.getSelectedDates().get(i).toString(),Toast.LENGTH_SHORT).show();

                    txt_cal_view.setText(select +" "+ "To"+" " + DAY + "/" + MONTH + "/" + YEAR);
                    Intent intent=new Intent(getApplicationContext(),EmployeeReportActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




}
