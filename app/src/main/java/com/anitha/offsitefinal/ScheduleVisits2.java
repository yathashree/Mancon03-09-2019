package com.anitha.offsitefinal;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.PopupMenu;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;

public class ScheduleVisits2 extends Activity implements NavigationView.OnNavigationItemSelectedListener{
    Button btnassign,btncancel;
    ImageButton img_menu;
    final Context context = this;
TextView assign;
    AlertDialog mDialog = null;
    boolean selectAll = true;
    int length;
    Button btnmon;


    Button btn, cancel, save;
    EditText edit;
    ImageButton camera;
    TextView text;

    AlertDialog.Builder alertdialogbuilder;

    String[] Client_names = new String[]{
            "Mindtree-IT Sez",
            "Microsoft-Gachibowli",
            "Google-Kondapur",
            "Google-Kondapur Main Road",
            "IBM",
            "Amazon India Pvt Lts",
            "Oracle",
            "Google-Kondapur Main Road",
            "HCL",
            "TNT",
            "test"
    };

    List<String> ItemsIntoList;

    boolean[] Selectedtruefalse = new boolean[]{
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduledvisit2);


        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"arial.ttf",true);
        btnmon=(Button)findViewById(R.id.btnmon);
        btnmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add highlight color black
                Intent i = new Intent(ScheduleVisits2.this, ScheduleVisits2.class);
                startActivity(i);

                //Change the text color and button background when we pressed
                Drawable dr = getResources().getDrawable(R.drawable.btnstylevisits2);
                dr.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);

                switch (v.getId()) {
                    case R.id.btnmon:

                        if (btnmon == null) {
                            btnmon = (Button) findViewById(v.getId());
                        } else {
                            btnmon.setBackgroundResource(R.drawable.btnstylevisits2);
                            btnmon.setTextColor(getResources().getColor(R.color.white));
                            btnmon = (Button) findViewById(v.getId());
                        }
                        btnmon.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }


            }
        });
        btncancel=(Button)findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ScheduleVisits2.class);
                startActivity(i);
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
        btnassign = (Button) findViewById(R.id.btnassign);
        btnassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn background color change when clicked
                Drawable dr = getResources().getDrawable(R.drawable.btncancelstyle);
                dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                switch (v.getId()) {
                    case R.id.btnassign:

                        if (btnassign == null) {
                            btnassign = (Button) findViewById(v.getId());
                        } else {
                            btnassign.setBackgroundResource(R.drawable.btncancelstyle);
                            btnassign.setTextColor(getResources().getColor(R.color.white));
                            btnassign = (Button) findViewById(v.getId());
                        }
                        btnassign.setBackgroundDrawable(dr);

                        break;


                    default:
                        break;
                }

                alertdialogbuilder = new AlertDialog.Builder(ScheduleVisits2.this,R.style.MyAlertDialogStyle1);


                ItemsIntoList = Arrays.asList(Client_names);

             //   final ArrayList<String> selectedStrings = new ArrayList<String>();

                alertdialogbuilder.setMultiChoiceItems(Client_names, Selectedtruefalse, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {


                          for(int i=0;i<ItemsIntoList.size();i++){


                              if(isChecked){

                                  Toast.makeText(context,"Client names"+ Client_names[which],Toast.LENGTH_SHORT).show();
                              }


                          }

                    }
                });

                alertdialogbuilder.setCancelable(false);

                alertdialogbuilder.setTitle("Select Client and Location");

           //     android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle1);

                alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });


                alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });




                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);




//
//
//                LayoutInflater li = LayoutInflater.from(context);
//
//                View promptsView = li.inflate(R.layout.clientasignpopup, null);
//                assign=(TextView)promptsView.findViewById(R.id.assign);
//                assign.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i=new Intent(getApplicationContext(),ScheduleVisits2.class);
//                        startActivity(i);
//                    }
//                });
//
//                //theme change color of title
//                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle1);
//
//
//                alertDialogBuilder.setView(promptsView);
//
//// set dialog message
//                alertDialogBuilder.setTitle("Assign Client & Location");
//
//
//                //   alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
//// create alert dialog
//                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
//
//
//
//                //cancel button onclick
//                final TextView cancel = (TextView) promptsView
//                        .findViewById(R.id.cancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.exit(0);
//                    }
//                });
//
//                alertDialog.show();
//
//
//                alertDialog.setCanceledOnTouchOutside(false);
//

            }


        });
        img_menu = (ImageButton) findViewById(R.id.menu);
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                PopupMenu popup = new PopupMenu(ScheduleVisits2.this, img_menu);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                ScheduleVisits2.this,
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
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView2.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(ScheduleVisits2.this, DividerItemDecoration.VERTICAL));
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
            Intent schedule = new Intent(ScheduleVisits2.this, HomeActivityMenu.class);
            startActivity(schedule);
        } else if (id == R.id.dashboard) {
            Intent dashboard = new Intent(ScheduleVisits2.this, DashboradActivity.class);
            startActivity(dashboard);
        } else if (id == R.id.client_Reports) {
            Intent client_Reports = new Intent(ScheduleVisits2.this, Tab_Main.class);
            startActivity(client_Reports);
        } else if (id == R.id.emp_reports) {
            Intent emp_reports = new Intent(ScheduleVisits2.this, EmployeeReportActivity.class);
            startActivity(emp_reports);
        } else if (id == R.id.mapaclient) {
            Intent mapaclient = new Intent(ScheduleVisits2.this, MapaClientActivity.class);
            startActivity(mapaclient);
        } else if (id == R.id.schedulevisits) {
            Intent schedulevisits = new Intent(ScheduleVisits2.this, ScheduleVisits.class);
            startActivity(schedulevisits);
        } else if (id == R.id.epsreports) {
            Intent epsreports = new Intent(ScheduleVisits2.this, EPSResultsActivity.class);
            startActivity(epsreports);
        } else if (id == R.id.epostingsheet) {
            Intent epostingsheet = new Intent(ScheduleVisits2.this, EPostingSheet.class);
            startActivity(epostingsheet);
        } else if (id == R.id.sendfeedback) {
            Intent sendfeedback = new Intent(ScheduleVisits2.this, SendFeedBack.class);
            startActivity(sendfeedback);
        } else if (id == R.id.help) {
            Intent help = new Intent(ScheduleVisits2.this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(ScheduleVisits2.this, LogOutActivity.class);
            startActivity(logout);
        }

        //   Toast.makeText(this, "You have chosen " + text, Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        //   drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

}






