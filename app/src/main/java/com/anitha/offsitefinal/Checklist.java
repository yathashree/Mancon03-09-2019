package com.anitha.offsitefinal;

import android.app.AlertDialog;
import android.content.Context;
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
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;


public class Checklist extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton img_menu;
    Button daycheckselect,checklistsubmit;
    Button nightchecklistselect;
    Button surprisechecklistselect;
    Button otherschecklistselect;
    Button slachecklistselect;
    Button ok,cancel;

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);


        //custom g4s font
        final Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);

        otherschecklistselect = (Button) findViewById(R.id.otherschecklistselect);
        slachecklistselect = (Button) findViewById(R.id.slachecklistselect);
        surprisechecklistselect = (Button) findViewById(R.id.surprisechecklistselect);
        nightchecklistselect = (Button) findViewById(R.id.nightchecklistselect);

        daycheckselect = (Button) findViewById(R.id.daycheckselect);
        daycheckselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentday = new Intent(Checklist.this, ChecklistTypes.class);
                startActivity(intentday);

                //button background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                switch (v.getId()) {
                    case R.id.daycheckselect:

                        if (daycheckselect == null) {
                            daycheckselect = (Button) findViewById(v.getId());
                        } else {
                            daycheckselect.setBackgroundResource(R.drawable.btncancelstyle);
                            daycheckselect.setTextColor(getResources().getColor(R.color.white));
                            daycheckselect = (Button) findViewById(v.getId());
                        }
                        daycheckselect.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });

        nightchecklistselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnight = new Intent(Checklist.this, ChecklistTypes.class);
                startActivity(intentnight);

                //button background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                switch (v.getId()) {
                    case R.id.nightchecklistselect:

                        if (nightchecklistselect == null) {
                            nightchecklistselect = (Button) findViewById(v.getId());
                        } else {
                            nightchecklistselect.setBackgroundResource(R.drawable.btncancelstyle);
                            nightchecklistselect.setTextColor(getResources().getColor(R.color.white));
                            nightchecklistselect = (Button) findViewById(v.getId());
                        }
                        nightchecklistselect.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });


        surprisechecklistselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsurprise = new Intent(Checklist.this, ChecklistTypes.class);
                startActivity(intentsurprise);

                //button background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                switch (v.getId()) {
                    case R.id.surprisechecklistselect:

                        if (surprisechecklistselect == null) {
                            surprisechecklistselect = (Button) findViewById(v.getId());
                        } else {
                            surprisechecklistselect.setBackgroundResource(R.drawable.btncancelstyle);
                            surprisechecklistselect.setTextColor(getResources().getColor(R.color.white));
                            surprisechecklistselect = (Button) findViewById(v.getId());
                        }
                        surprisechecklistselect.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });




              //  final Button btnOpenPopup = (Button) findViewById(R.id.otherschecklistselect);
                otherschecklistselect.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        //button background color change when clicked
                        Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                        dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                        switch (view.getId()) {
                            case R.id.otherschecklistselect:

                                if (otherschecklistselect == null) {
                                    otherschecklistselect = (Button) findViewById(view.getId());
                                } else {
                                    otherschecklistselect.setBackgroundResource(R.drawable.btncancelstyle);
                                    otherschecklistselect.setTextColor(getResources().getColor(R.color.white));
                                    otherschecklistselect = (Button) findViewById(view.getId());
                                }
                                otherschecklistselect.setBackgroundDrawable(dr);

                                break;


                            default:
                                break;
                        }

                        LayoutInflater li = LayoutInflater.from(context);

                        View promptsView = li.inflate(R.layout.checklistothers, null);

                        //ok button clicking
                        ok=(Button)promptsView.findViewById(R.id.ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i=new Intent(getApplicationContext(),Checklist.class);
                                startActivity(i);
                            }
                        });

                        cancel=(Button)promptsView.findViewById(R.id.cancel);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
Intent i=new Intent(getApplicationContext(),Checklist.class);
startActivity(i);
                               // Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();

                            }
                        });

                        //theme change color of title
                             AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);



                        alertDialogBuilder.setView(promptsView);

// set dialog message
                        alertDialogBuilder.setTitle("Client Visited/Reasons");


                        //   alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
// create alert dialog
                        final AlertDialog alertDialog = alertDialogBuilder.create();
                        final Spinner spinothers = (Spinner) promptsView
                                .findViewById(R.id.onetimepassword);
                        String[] items1 = new String[]{"Select Client", "Ram", "Arum", "karan", "Teja", "Prabhu", "Arvi"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Checklist.this, R.layout.spinner_hub_row, items1);


                        spinothers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        spinothers.setAdapter(adapter);

                        //cancel button onclick
//                        final Button cancel = (Button) promptsView
//                                .findViewById(R.id.cancel);
//                        cancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                System.exit(0);
//                            }
//                        });

                        alertDialog.show();


                        alertDialog.setCanceledOnTouchOutside(false);


                    }


                });






        slachecklistselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsla = new Intent(Checklist.this, ChecklistTypes.class);
                startActivity(intentsla);
                //button background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                switch (v.getId()) {
                    case R.id.slachecklistselect:

                        if (slachecklistselect == null) {
                            slachecklistselect = (Button) findViewById(v.getId());
                        } else {
                            slachecklistselect.setBackgroundResource(R.drawable.btncancelstyle);
                            slachecklistselect.setTextColor(getResources().getColor(R.color.white));
                            slachecklistselect = (Button) findViewById(v.getId());
                        }
                        slachecklistselect.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });



        TypefaceUtil.overrideFont(getApplicationContext(), "arial", "fonts/arial.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        img_menu = (ImageButton) findViewById(R.id.menu);
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(Checklist.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                Checklist.this,
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
        final ImageButton menuRight = (ImageButton) findViewById(R.id.menu);
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

        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView2.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(Checklist.this, DividerItemDecoration.VERTICAL));
        navigationView2.setNavigationItemSelectedListener(this);




        checklistsubmit = (Button) findViewById(R.id.checklistsubmit);
        checklistsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();

                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.checklistsubmit:

                        if (checklistsubmit == null) {
                            checklistsubmit = (Button) findViewById(v.getId());
                        } else {
                            checklistsubmit.setBackgroundResource(R.drawable.btncancelstyle);
                            checklistsubmit.setTextColor(getResources().getColor(R.color.white));
                            checklistsubmit = (Button) findViewById(v.getId());
                        }
                        checklistsubmit.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });

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
            Intent schedule = new Intent(Checklist.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(Checklist.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(Checklist.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(Checklist.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(Checklist.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(Checklist.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(Checklist.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(Checklist.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(Checklist.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(Checklist.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(Checklist.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

}




