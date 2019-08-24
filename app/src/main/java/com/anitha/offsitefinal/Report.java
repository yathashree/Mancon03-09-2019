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

public class Report extends AppCompatActivity {
    ImageButton img_menu;

    TextView visits,schedulevisits,schv_count,un_schedulevisits,unsv_count,missed_visits,mv_count,site_inspec,details,day,day_count,night,night_count,surprise,surprise_count,others_site,others_count,complaints,total_compliants,
            sv_count,total_open_complaints,un_sv_count,details_of_compliants,total,open,shortage,shortage_total_count;

    TextView shortage_open_count,sleeping,sleeping_total_count,sleeping_open_count,training,training_total_count,training_open_count,
            comp_issues,comp_issues_total_count,comp_issues_open_count,others,others_total_count,others_open_count;

    TextView performance,meetings,meetings_count,threat,threat_sv_count,variances,variances_count,trainings,
            training_count,breif,breif_count,toolbox_talk,toolbox_talk_count;

    TextView incidents,total_incidents,inci_count,total_open_incidents,total_inci,details_of_incidents,total_inci_count,
            open_inci_count,theft,theft_total_count,theft_open_count,damage,damage_total_count,damage_open_count,
            fire,fire_total_count,fire_open_count,negligence,negligence_total_count,
            negligence_open_count,others_inci,others_inci_total_count,others_inci_open_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_report);




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
        complaints=(TextView)findViewById(R.id.complaints);
        total_compliants=(TextView)findViewById(R.id.total_compliants);

        sv_count=(TextView)findViewById(R.id.sv_count);
        total_open_complaints=(TextView)findViewById(R.id.total_open_complaints);
        un_sv_count=(TextView)findViewById(R.id.un_sv_count);
        details_of_compliants=(TextView)findViewById(R.id.details_of_compliants);

        total=(TextView)findViewById(R.id.total);
        open=(TextView)findViewById(R.id.open);
        shortage=(TextView)findViewById(R.id.shortage);
        shortage_total_count=(TextView)findViewById(R.id.shortage_total_count);

        shortage_open_count=(TextView)findViewById(R.id.shortage_open_count);
        sleeping=(TextView)findViewById(R.id.sleeping);
        sleeping_total_count=(TextView)findViewById(R.id.sleeping_total_count);
        sleeping_open_count=(TextView)findViewById(R.id.sleeping_open_count);

        training=(TextView)findViewById(R.id.training);
        training_total_count=(TextView)findViewById(R.id.training_total_count);
        training_open_count=(TextView)findViewById(R.id.training_open_count);
        comp_issues=(TextView)findViewById(R.id.comp_issues);

        comp_issues_total_count=(TextView)findViewById(R.id.comp_issues_total_count);
        comp_issues_open_count=(TextView)findViewById(R.id.comp_issues_open_count);
        others=(TextView)findViewById(R.id.others);
        others_total_count=(TextView)findViewById(R.id.others_total_count);

        others_open_count=(TextView)findViewById(R.id.others_open_count);
        performance=(TextView)findViewById(R.id.performance);
        meetings=(TextView)findViewById(R.id.meetings);
        meetings_count=(TextView)findViewById(R.id.meetings_count);

        threat=(TextView)findViewById(R.id.threat);
        threat_sv_count=(TextView)findViewById(R.id.threat_sv_count);
        variances=(TextView)findViewById(R.id.variances);
        variances_count=(TextView)findViewById(R.id.variances_count);

        trainings=(TextView)findViewById(R.id.trainings);
        training_count=(TextView)findViewById(R.id.training_count);
        breif=(TextView)findViewById(R.id.breif);
        breif_count=(TextView)findViewById(R.id.breif_count);

        toolbox_talk=(TextView)findViewById(R.id.toolbox_talk);
        toolbox_talk_count=(TextView)findViewById(R.id.toolbox_talk_count);
        incidents=(TextView)findViewById(R.id.incidents);
        total_incidents=(TextView)findViewById(R.id.total_incidents);

        inci_count=(TextView)findViewById(R.id.inci_count);
        total_open_incidents=(TextView)findViewById(R.id.total_open_incidents);
        total_inci=(TextView)findViewById(R.id.total_inci);
        details_of_incidents=(TextView)findViewById(R.id.details_of_incidents);

        total_inci_count=(TextView)findViewById(R.id.total_inci_count);
        open_inci_count=(TextView)findViewById(R.id.open_inci_count);
        theft=(TextView)findViewById(R.id.theft);
        theft_total_count=(TextView)findViewById(R.id.theft_total_count);

        theft_open_count=(TextView)findViewById(R.id.theft_open_count);
        damage=(TextView)findViewById(R.id.damage);
        damage_total_count=(TextView)findViewById(R.id.damage_total_count);
        damage_open_count=(TextView)findViewById(R.id.damage_open_count);

        fire=(TextView)findViewById(R.id.fire);
        fire_total_count=(TextView)findViewById(R.id.fire_total_count);
        fire_open_count=(TextView)findViewById(R.id.fire_open_count);
        negligence=(TextView)findViewById(R.id.negligence);

        negligence_total_count=(TextView)findViewById(R.id.negligence_total_count);
        negligence_open_count=(TextView)findViewById(R.id.negligence_open_count);
        others_inci=(TextView)findViewById(R.id.others_inci);
        others_inci_total_count=(TextView)findViewById(R.id.others_inci_total_count);
        others_inci_open_count=(TextView)findViewById(R.id.others_inci_open_count);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "arial.ttf", true);


    }


}






