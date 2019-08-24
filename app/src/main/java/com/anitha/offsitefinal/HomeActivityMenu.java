package com.anitha.offsitefinal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.PopupMenu;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;


public class HomeActivityMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    private final AppCompatActivity activity = HomeActivityMenu.this;

    ListView list1;
    final Context context = this;
    SearchView editsearch;
    public static boolean checkedAll, checkedNon;


    //  Context mContext
    ImageView img_menu, img_profile;
    ImageButton btn_cal;
    DatePickerDialog datePickerDialog;

    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TextView edit_cal;
    ListView list;
    TextView close;
    Button btnapply;

    String[] maintitle = {
            "Google India Pvt Limited", "Accenture",
            "Amazon Technologies pvt ltd", "Microsoft",
            "Cognizant", "Accenture", "IBM",
    };

    String[] subtitle = {
            "Address1", "Address2",
            "Address3", "Address4",
            "Address5", "Address6", "Address7"
    };

    Integer[] imgid = {
            R.drawable.client_icon, R.drawable.client_icon,
            R.drawable.client_icon, R.drawable.client_icon,
            R.drawable.client_icon, R.drawable.client_icon, R.drawable.client_icon,


    };


    Button btn_chkin, btn_sos, btn_chkout, btnfilter;

    String data;


    private String URLstring = "http://192.168.1.22:2020/api/Get_HubDetailsAPI";
    private ArrayList<String> hubs = new ArrayList<String>();

    NavigationView navigationView;
    String[] NameList;


    ArrayList<Names> arrayList = new ArrayList<Names>();

    ListViewCheckAdapter adapter;
    boolean[] Selectedtruefalse = new boolean[]{
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)

            //Initialisation
            img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_menu = (ImageView) findViewById(R.id.menu);
        btn_cal = (ImageButton) findViewById(R.id.calendar);
        edit_cal = (TextView) findViewById(R.id.edit_cal);

        btn_chkin = (Button) findViewById(R.id.btn_chkin);
        btn_sos = (Button) findViewById(R.id.btn_sos);
        btn_chkout = (Button) findViewById(R.id.btn_chkout);
        btnfilter = (Button) findViewById(R.id.textfilter);


        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedAll = true;
                LayoutInflater li = LayoutInflater.from(context);


                final View promptsView = li.inflate(R.layout.activity_filter, null);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                close = (TextView) promptsView.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), HomeActivityMenu.class);
                        startActivity(i);
                    }
                });


                //  selectedItems = new ArrayList<String>();

                NameList = new String[]{"Anitha", "Yathashree", "Megha", "Thrisha", "GiriJa", "Vijaya", "Chandini", "Firdose"};


                //create an instance of ListView
                final ListView chl = (ListView) promptsView.findViewById(R.id.checkable_list);


                //set multiple selection mode
                chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                //supply data itmes to ListView
              /*  <String> aa = new ArrayAdapter<String>(HomeActivityMenu.this, R.layout.checkable_list_layout, R.id.check, items);
                chl.setAdapter(aa);*/

                for (int i = 0; i < NameList.length; i++) {
                    Names Names = new Names(NameList[i]);
                    // Binds all strings into an array
                    arrayList.add(Names);
                }

                // Pass results to ListViewAdapter Class
                adapter = new ListViewCheckAdapter(HomeActivityMenu.this, arrayList);
                // Binds the Adapter to the ListView
                chl.setAdapter(adapter);


                final CheckBox chk_txt_select = (CheckBox) promptsView.findViewById(R.id.select_all);
                chk_txt_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        NameList = new String[]{"Anitha", "Yathashree", "Megha", "Thrisha", "GiriJa", "Vijaya", "Chandini", "Firdose"};


                        //create an instance of ListView
                        final ListView chl = (ListView) promptsView.findViewById(R.id.checkable_list);


                        //set multiple selection mode
                        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                        //supply data itmes to ListView
              /*  <String> aa = new ArrayAdapter<String>(HomeActivityMenu.this, R.layout.checkable_list_layout, R.id.check, items);
                chl.setAdapter(aa);*/

                        for (int i = 0; i < NameList.length; i++) {
                            Names Names = new Names(NameList[i]);
                            // Binds all strings into an array
                            arrayList.add(Names);
                        }

                        // Pass results to ListViewAdapter Class
                        adapter = new ListViewCheckAdapter(HomeActivityMenu.this, arrayList);
                        // Binds the Adapter to the ListView
                        chl.setAdapter(adapter);

                        int cntChoice = chl.getCount();

                        //  SparseBooleanArray sparseBooleanArray = chl.getCheckedItemPositions();

                        for (int i = 0; i < cntChoice; i++) {

                            if (chk_txt_select.isChecked()) {


                                chl.setItemChecked(i, true);
                            } else {
                                chl.setItemChecked(i, false);

                            }

                        }


                    }


                });


                // Locate the EditText in listview_main.xml,searchview
                editsearch = (SearchView) promptsView.findViewById(R.id.search);
                editsearch.setOnQueryTextListener(HomeActivityMenu.this);


                final AlertDialog alertDialog = alertDialogBuilder.create();

                btnapply = (Button) promptsView.findViewById(R.id.btnapply);
                btnapply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), HomeActivityMenu.class);
                        startActivity(i);
                    }
                });
                alertDialog.show();

            }
        });


        //Sann QR CODE
        btn_chkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(HomeActivityMenu.this, ScannedBarcodeActivity.class);
                startActivity(i);

                //Change the text color and button background when we pressed
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

                Intent i = new Intent(HomeActivityMenu.this, CheckOutActivity.class);
                startActivity(i);

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
        });


        //Custom g4s font
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/

        Typeface face = Typeface.createFromAsset(getAssets(),
                "arial.ttf");
        edit_cal.setTypeface(face);


        //Loading Spinner Hub Items
        ArrayList<String> hub_items = getHub("hub.json");
        SearchableSpinner spinhub = (SearchableSpinner) findViewById(R.id.spinnerhub);


/*
        spinhub.setOnFocusChangeListener( new View.OnFocusChangeListener(){

            public void onFocusChange( View view, boolean hasfocus){
                if(hasfocus){

                    view.setBackgroundResource( R.drawable.border_style);
                }
                else{
                    view.setBackgroundResource( R.drawable.lost_focus_style);
                }
            }
        });*/


        ArrayAdapter<String> hub_adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_hub_row, R.id.cust_spin_view, hub_items) {

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


        spinhub.setAdapter(hub_adapter);
        data = (String) spinhub.getSelectedItem();


        //Loading Spinner Hub Items
        ArrayList<String> branch_items = getBranch("branch.json");
        SearchableSpinner spinbranch = (SearchableSpinner) findViewById(R.id.spinnerbranch);

        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_hub_row, R.id.cust_spin_view, branch_items) {

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


        spinbranch.setAdapter(branch_adapter);


        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // datePickerDialog.setTitle(null);
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(HomeActivityMenu.this, R.style.CalendarDatePickerDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                edit_cal.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        edit_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // datePickerDialog.setTitle(null);
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(HomeActivityMenu.this, R.style.CalendarDatePickerDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                edit_cal.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(HomeActivityMenu.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                HomeActivityMenu.this,
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

        navigationView = (NavigationView) findViewById(R.id.nav_view2);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(HomeActivityMenu.this, DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_view2);
        View headerView = navigationView.getHeaderView(0);
        final TextView header_name = (TextView) headerView.findViewById(R.id.nav_name);
        header_name.setText("Abc");


        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid);
        list = (ListView) findViewById(R.id.listview);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(), "Place Your First Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(), "Place Your Second Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {

                    Toast.makeText(getApplicationContext(), "Place Your Third Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 3) {

                    Toast.makeText(getApplicationContext(), "Place Your Forth Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {

                    Toast.makeText(getApplicationContext(), "Place Your Fifth Option Code", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //  if (i == 10) {


            mi = m.findItem(R.id.version);
            //   m.setTag(versionName);
            mi.setTitle("AppVer:1.1");


            //  }
            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
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
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    private ArrayList<String> getBranch(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        cList.add("Branch");
        cList.add("Bannerghatta");
        cList.add("Vasantha nagar");
        cList.add("Electronic city");
        cList.add("Domlur");
        cList.add("Whitefield");
        cList.add("Hoodi");


        try {
            if (data.equalsIgnoreCase("Bangalore")) {
                InputStream is = getResources().getAssets().open(fileName);
                int size = is.available();
                byte[] data = new byte[size];
                is.read(data);
                is.close();
                String bufferString = new String(data);


                //convert string to JSONArray
                jsonArray = new JSONArray(bufferString);


                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {


                        cList.add(jsonArray.getJSONObject(i).getString("BranchName"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }


    private ArrayList<String> getHub(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        cList.add("Hub");

        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("HubName"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
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

        if (id == R.id.schedule) {

            Intent schedule = new Intent(HomeActivityMenu.this, HomeActivityMenu.class);

            startActivity(schedule);

        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(HomeActivityMenu.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(HomeActivityMenu.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(HomeActivityMenu.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(HomeActivityMenu.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(HomeActivityMenu.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(HomeActivityMenu.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(HomeActivityMenu.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(HomeActivityMenu.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(HomeActivityMenu.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(HomeActivityMenu.this, LogOutActivity.class);
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
       /* if (arrayList.contains(query)) {
            adapter.filter(query);
        } else {
            Toast.makeText(HomeActivityMenu.this, "No Match found", Toast.LENGTH_LONG).show();
        }*/
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}