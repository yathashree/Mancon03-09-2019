package com.anitha.offsitefinal;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.anitha.offsitefinal.R.id.drawer_layout;

public class Employee_Report extends AppCompatActivity {
    ImageButton img_menu;


    TextView visits,schedulevisits,schv_count,un_schedulevisits,unsv_count,missed_visits,mv_count,site_inspec,details,day,day_count,night,night_count,
            surprise,surprise_count,others_site,others_count;



    TextView total_variances,variances,variances_count,details_of_variances,training_count,alert,alert_count,uniform,uniform_count,sop,sop_count,hse_count,hse_site,emergency,emergency_count,
            training_site,client_complaints,total_reported,total_report_count,op_complaints,open_complaints,incidents,total_incidents,inci_count,total_open_incidents,total_inci,client_information,
            clnt_under_threat,clnt_threat_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_report);


        visits=(TextView)findViewById(R.id.visits);

        schedulevisits=(TextView)findViewById(R.id.schedulevisits);

        schv_count=(TextView)findViewById(R.id.schv_count);

        un_schedulevisits=(TextView)findViewById(R.id.un_schedulevisits);
        unsv_count=(TextView)findViewById(R.id.unsv_count);

        missed_visits=(TextView)findViewById(R.id.missed_visits);
        mv_count=(TextView)findViewById(R.id.mv_count);
        site_inspec=(TextView)findViewById(R.id.site_inspec);

        details=(TextView)findViewById(R.id.details);

        day=(TextView)findViewById(R.id.day);
        day_count=(TextView)findViewById(R.id.day_count);

        night=(TextView)findViewById(R.id.night);
        night_count=(TextView)findViewById(R.id.night_count);
        surprise=(TextView)findViewById(R.id.surprise);
        surprise_count=(TextView)findViewById(R.id.surprise_count);

        others_site=(TextView)findViewById(R.id.others_site);
        others_count=(TextView)findViewById(R.id.others_count);






        variances=(TextView)findViewById(R.id.variances);
        total_variances=(TextView)findViewById(R.id.total_variances);
        variances_count=(TextView)findViewById(R.id.variances_count);
        details_of_variances=(TextView)findViewById(R.id.details_of_varinces);
        alert=(TextView)findViewById(R.id.alert);
        alert_count=(TextView)findViewById(R.id.alert_counr);
        uniform=(TextView)findViewById(R.id.uniform);
        uniform_count=(TextView)findViewById(R.id.uniform_count);
        sop=(TextView)findViewById(R.id.sop);

        sop_count=(TextView)findViewById(R.id.sop_count);
        hse_site=(TextView)findViewById(R.id.hse_site);
        hse_count=(TextView)findViewById(R.id.hse_count);
        emergency=(TextView)findViewById(R.id.emergency);
        emergency_count=(TextView)findViewById(R.id.emergency_count);
        training_site=(TextView)findViewById(R.id.training_site);
        training_count=(TextView)findViewById(R.id.training_count);

        client_complaints=(TextView)findViewById(R.id.client_complaints);
        total_reported=(TextView)findViewById(R.id.total_reported);
        total_report_count=(TextView)findViewById(R.id.total_report_count);
        op_complaints=(TextView)findViewById(R.id.op_complaints);

        open_complaints=(TextView)findViewById(R.id.open_complaints);
        incidents=(TextView)findViewById(R.id.incidents);
        total_incidents=(TextView)findViewById(R.id.total_incidents);
        inci_count=(TextView)findViewById(R.id.inci_count);
        total_open_incidents=(TextView)findViewById(R.id.total_open_incidents);
        total_inci=(TextView)findViewById(R.id.total_inci);

        client_information=(TextView)findViewById(R.id.client_information);
        clnt_under_threat=(TextView)findViewById(R.id.client_information);
        clnt_threat_count=(TextView)findViewById(R.id.client_information);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);


    }


}






