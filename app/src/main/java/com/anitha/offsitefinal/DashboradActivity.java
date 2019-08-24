package com.anitha.offsitefinal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

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


public class DashboradActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {
    private final AppCompatActivity activity = DashboradActivity.this;

    //  Context mContext
    ImageView img_menu, img_profile;
    PieChart mChart_visits;
    PieChart mChart_missed;
    PieChart mChart_complaints;
    PieChart mChart_incidents;

    private String[] xValues = {"Day", "Night", "Surprise", "Others"};

    private String[] xValues1 = {"Scheduled", "Unscheduled","Missed"};

    private String[] xValues2 = {"Shortage", "Sleeping","Training","Comp.Issues","Others"};

    private String[] xValues3 = {"Theft", "Damage","Fire","Negligence","Others"};




    //  Context mContext
    ImageButton cal_range;
    DatePickerDialog datePickerDialog;

    int year;
    int month;
    int dayOfMonth;
    TextView txt_cal_view;
    ListView list;


    CalendarPickerView calendar;
    TextView selected_dates;
    Dialog myDialog;
    //Button btn_chkin,btn_sos,btn_chkout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

      /*  btn_chkin=(Button)findViewById(R.id.btn_chkin);
        btn_sos=(Button)findViewById(R.id.btn_sos);
        btn_chkout=(Button)findViewById(R.id.btn_chkout);

        btn_chkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(DashboradActivity.this,ScannedBarcodeActivity.class);
                startActivity(i);

                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.btn_chkin:

                        if (btn_chkin == null) {
                            btn_chkin = (Button) findViewById(v.getId());
                        } else {
                            btn_chkin.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_chkin.setTextColor(getResources().getColor(R.color.white));
                            btn_chkin = (Button) findViewById(v.getId());
                        }
                        btn_chkin.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });

        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.btn_sos:

                        if (btn_sos == null) {
                            btn_sos = (Button) findViewById(v.getId());
                        } else {
                            btn_sos.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_sos.setTextColor(getResources().getColor(R.color.white));
                            btn_sos = (Button) findViewById(v.getId());
                        }
                        btn_sos.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });
        btn_chkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.btn_chkout:

                        if (btn_chkout == null) {
                            btn_chkout = (Button) findViewById(v.getId());
                        } else {
                            btn_chkout.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_chkout.setTextColor(getResources().getColor(R.color.white));
                            btn_chkout = (Button) findViewById(v.getId());
                        }
                        btn_chkout.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });*/

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "arial.ttf");
     //   txt_cal_view.setTypeface(face);

        //  TypefaceUtil.overrideFont(getApplicationContext(), "GILLSANSSTD-LIGHT", "fonts/GillSansStd-Light.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        myDialog = new Dialog(this);


        cal_range = (ImageButton) findViewById(R.id.calendar);
        txt_cal_view = (TextView) findViewById(R.id.edit_cal);


        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_menu = (ImageView) findViewById(R.id.menu);

        mChart_visits = (PieChart) findViewById(R.id.piechart_visits);
        mChart_missed = (PieChart) findViewById(R.id.piechart_missed);
        mChart_complaints = (PieChart) findViewById(R.id.piechart_complaints);
        mChart_incidents = (PieChart) findViewById(R.id.piechart_incidents);


        Spinner dropdown = (Spinner) findViewById(R.id.spinnerhub);
        String[] items = new String[]{"Hub", "Bangalore", "Mysore", "Delhi", "Gurgaon", "Chennai", "Hyderabad"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_hub_row, items){

            //spinner font
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

//spinner dropdown font
            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);
                v.setBackgroundColor(Color.WHITE);

                return v;
            }
        };


        dropdown.setAdapter(adapter1);



        Spinner dropdown1 = (Spinner) findViewById(R.id.spinnerbranch);
        String[] items1 = new String[]{"Branch", "Bannerghatta","Outer Ring Road", "VasanthaNagar", "Electronic city", "Domlur", "Whitefield", "Hoodi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_hub_row, items1) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }


            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
                ((TextView) v).setTypeface(externalFont);
                v.setBackgroundColor(Color.WHITE);

                return v;
            }
        };


        dropdown1.setAdapter(adapter);


        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(10, 0));
        entries.add(new Entry(10, 0));
        entries.add(new Entry(20, 0));
        entries.add(new Entry(20, 0));


        PieDataSet dataSet = new PieDataSet(entries, "");
        PieData data = new PieData(xValues, dataSet);
      data.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));


        //  data.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));

        dataSet.setColors(new int[]{Color.rgb(45,96,115), Color.rgb(70,147,177), Color.rgb(64,199,218), Color.rgb(168,199,219)});

       // dataSet.setColors(new int[]{Color.rgb(69,146,177), Color.rgb(165,199,218), Color.rgb(74,74,73), Color.rgb(218,233,241)});
        //  dataSet.setSliceSpace(2f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(8f);

       // dataSet.setDrawValues(false);

        //  dataSet.setDrawValues(true);
        mChart_visits.setDrawSliceText(false); // To remove slice text




        mChart_visits.setUsePercentValues(true);
        mChart_visits.setDrawHoleEnabled(false);
        mChart_visits.setData(data);
        mChart_visits.invalidate();
        mChart_visits.setDescription("");


        ArrayList<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(20, 0));
        entries1.add(new Entry(20, 0));
        entries1.add(new Entry(20, 0));



        PieDataSet dataSet1 = new PieDataSet(entries1, "");
        PieData data1 = new PieData(xValues1, dataSet1);
       data1.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));


        dataSet1.setColors(new int[]{Color.rgb(255,59,63), Color.rgb(255,102,105),Color.rgb(246,159,142)});

       // dataSet1.setColors(new int[]{Color.rgb(255,102,105), Color.rgb(249,170,117),Color.rgb(247,159,142)});
        dataSet1.setValueTextColor(Color.WHITE);
        mChart_missed.setUsePercentValues(true);
        mChart_missed.setDrawHoleEnabled(false);
        mChart_missed.setData(data1);

        mChart_missed.invalidate();
        mChart_missed.setDescription("");
        mChart_missed.setHighlightPerTapEnabled(true);
        mChart_missed.setDrawSliceText(false); // To remove slice text




        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(8, 0));
        entries2.add(new Entry(3, 0));
        entries2.add(new Entry(5,1));
        entries2.add(new Entry(6,9));
        entries2.add(new Entry(4,2));


        PieDataSet dataSet2 = new PieDataSet(entries2, "");
        PieData data2 = new PieData(xValues2, dataSet2);
       data2.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));



        dataSet2.setColors(new int[]{Color.rgb(124,124,124), Color.rgb(146,146,146),Color.rgb(165,165,165),Color.rgb(191,191,191),Color.rgb(213,213,213)});
        dataSet2.setValueTextColor(Color.WHITE);


        mChart_complaints.setUsePercentValues(true);
        mChart_complaints.setDrawHoleEnabled(false);
        mChart_complaints.setData(data2);
        mChart_complaints.invalidate();
        mChart_complaints.setDescription("");
        mChart_complaints.setDrawSliceText(false); // To remove slice text





        ArrayList<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(5, 0));
        entries3.add(new Entry(5, 0));
        entries3.add(new Entry(1,8));
        entries3.add(new Entry(6,9));
        entries3.add(new Entry(4,2));


        PieDataSet dataSet3 = new PieDataSet(entries3, "");
        PieData data3 = new PieData(xValues3, dataSet3);
       data3.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));


        dataSet3.setColors(new int[]{Color.rgb(70,115,156), Color.rgb(80,138,188),Color.rgb(93,154,208),Color.rgb(151,185,223),Color.rgb(189,209,234)});
        dataSet3.setValueTextColor(Color.WHITE);


        mChart_incidents.setUsePercentValues(true);
        mChart_incidents.setDrawHoleEnabled(false);
        mChart_incidents.setData(data3);
        mChart_incidents.invalidate();
        mChart_incidents.setDescription("");
        mChart_incidents.setDrawSliceText(false); // To remove slice text



        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(DashboradActivity.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                DashboradActivity.this,
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
        navMenuView.addItemDecoration(new DividerItemDecoration(DashboradActivity.this, DividerItemDecoration.VERTICAL));
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
            return;
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String text = "";

        if (id == R.id.schedule) {
            Intent schedule = new Intent(DashboradActivity.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(DashboradActivity.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(DashboradActivity.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(DashboradActivity.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(DashboradActivity.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(DashboradActivity.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(DashboradActivity.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(DashboradActivity.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(DashboradActivity.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(DashboradActivity.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(DashboradActivity.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }


   /* public class DecimalRemover extends PercentFormatter {

        protected DecimalFormat mFormat;

        public DecimalRemover(DecimalFormat format) {
            this.mFormat = format;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if(value < 10) return "";
            return mFormat.format(value) + " %";
        }
    }*/

    /*private class MyValueFormatter implements ValueFormatter {


            private DecimalFormat mFormat;

            @RequiresApi(api = Build.VERSION_CODES.N)
            public MyValueFormatter() {
                mFormat = new DecimalFormat("###,###,###"); // use no decimals
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                // write your logic here
                if(value < 10) return "";

                return mFormat.format(value) + "%"; // in case you want to add percent
            }

    }*/


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

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd yyyy");
        String dateString = sdf.format(date);
        txt_current.setText(dateString);






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
               // Toast.makeText(getApplicationContext(),"Selected Date is : " +date.toString(),Toast.LENGTH_SHORT).show();
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
                  //  Toast.makeText(getApplicationContext(),calendar.getSelectedDates().get(i).toString(),Toast.LENGTH_SHORT).show();

                    txt_cal_view.setText(select +" "+ "To"+" " + DAY + "/" + MONTH + "/" + YEAR);
                    Intent intent=new Intent(getApplicationContext(),DashboradActivity.class);
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
}