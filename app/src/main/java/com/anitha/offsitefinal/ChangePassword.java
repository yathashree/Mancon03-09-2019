package com.anitha.offsitefinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anitha.offsitefinal.model.User;
import com.anitha.offsitefinal.sql.DatabaseHelper;

public class ChangePassword extends AppCompatActivity {

    private final AppCompatActivity activity = ChangePassword.this;


    TextView txt_otp;
    EditText edit_default, edit_new, edit_confirm, otp;
    Button submit;
    int randomNumber = 123456;
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_change_pwd);
        //font family
        TypefaceUtil.overrideFont(getApplicationContext(), "GILLSANSSTD-LIGHT", "fonts/GillSansStd-Light.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        edit_default = (EditText) findViewById(R.id.edit_default);
        edit_new = (EditText) findViewById(R.id.edit_new);
        edit_confirm = (EditText) findViewById(R.id.edit_confirm);
        submit = (Button) findViewById(R.id.btn_submit);
        txt_otp = (TextView) findViewById(R.id.text_otp);

        otp = (EditText) findViewById(R.id.edit_otp);

        txt_otp.setVisibility(View.GONE);
        otp.setVisibility(View.GONE);


        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String myString = settings.getString("pass", "");
        edit_default.setText(myString);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        databaseHelper = new DatabaseHelper(activity);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edit_new.getText().toString().trim().length() == 0) {
                    edit_new.setError("Enter the new password");
                    edit_new.requestFocus();

                }
                if (edit_confirm.getText().toString().trim().length() == 0) {
                    edit_confirm.setError("Enter the confirm password");
                    edit_confirm.requestFocus();
                } else if (!(edit_new.getText().toString().equals(edit_confirm.getText().toString()))) {
                    edit_confirm.setError("Password is not Matching");
                    edit_confirm.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), "OTP send Successfully", Toast.LENGTH_LONG).show();
                }
                //  Intent i=new Intent(getApplicationContext(),ChangePassword.class);
                //  startActivity(i);
                txt_otp.setVisibility(View.VISIBLE);
                otp.setVisibility(View.VISIBLE);

                if (otp.getText().toString().trim().length() == 0) {
                    otp.setError("Please Enter OTP you received");
                    otp.requestFocus();
                } else if (randomNumber == Integer.valueOf(otp.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "You are Login Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ChangePassword.this, HomeActivityMenu.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_LONG).show();

                }

                //   verify();
                /*SharedPreferences settings = getSharedPreferences("mypref",
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("otp", String.valueOf(randomNumber));
                editor.commit();*/



               /* if(randomNumber==Integer.valueOf(otp.getText().toString())){
                    Toast.makeText(getApplicationContext(),"You are Login Successfully",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(in );
                }
                else{
                    otp.setError("Enter Correct One");
                    Toast.makeText(getApplicationContext(),"Wrong Otp",Toast.LENGTH_LONG).show();

                }*/
                /*Intent i=new Intent(ChangePassword.this,ChangePassword.class);
                startActivity(i);
                txt_otp.setVisibility(View.VISIBLE);
                otp.setVisibility(View.VISIBLE);*/











/*

                if (pass == "") {

                    edit_new.setError("Enter New Password");
                }
                if (cpass == "") {

                    edit_confirm.setError("Enter Confirm password");
                }

                if(!pass.equals(cpass)){
                    Toast.makeText(ChangePassword.this,"Password Not matching",Toast.LENGTH_SHORT).show();

                }
                if(!(randomNumber==Integer.valueOf(otp.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"Wrong Otp",Toast.LENGTH_LONG).show();


                }

                else{
                    Intent i=new Intent(ChangePassword.this,VerifyOtp.class);
                    startActivity(i);

                }
*/


             /*   try {
                    // Construct data
                    String apiKey = "apikey=" + "GnQKf2mBt+g-bggApyuDE5mj9NnRblreOIM6RvtNcm";

                  //  Random random = new Random();
*//*
                    randomNumber = random.nextInt(999999);
*//*

                    randomNumber=123456;
                    SharedPreferences settings = getSharedPreferences("mypref",
                            Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("otp", String.valueOf(randomNumber));
                    editor.commit();

                    String message = "&message=" + "Hey"+"Your OTP is-"+randomNumber;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + edit_default.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();


                   // return stringBuffer.toString();
                    Toast.makeText(getApplicationContext(),"OTP send Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(ChangePassword.this,VerifyOtp.class);
                    startActivity(i);


                } catch (Exception e) {
                   // System.out.println("Error SMS "+e);
                    // return "Error "+e;
                   Toast.makeText(getApplicationContext(),"OTP ERROR",Toast.LENGTH_LONG).show();
                   Toast.makeText(getApplicationContext(),"ERROR "+e ,Toast.LENGTH_LONG).show();
                }
*/

            }


        });



      /*  verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomNumber==Integer.valueOf(otp.getText().toString())){
                    Toast.makeText(getApplicationContext(),"You are Login Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(ChangePassword.this,HomeActivity.class);
                    startActivity(i);
                }
                else{
                    otp.setError("Enter Correct One");
                    Toast.makeText(getApplicationContext(),"Wrong Otp",Toast.LENGTH_LONG).show();

                }
            }
        });
*/


    }

    public void verify() {
       /* SharedPreferences settings1 = getSharedPreferences("mypref",
                Context.MODE_PRIVATE);
        randomNumber = Integer.parseInt(settings1.getString("otp", ""));*/


        if (randomNumber == Integer.valueOf(otp.getText().toString())) {
            Toast.makeText(getApplicationContext(), "You are Login Successfully", Toast.LENGTH_LONG).show();
            Intent in = new Intent(getApplicationContext(), HomeActivityMenu.class);
            startActivity(in);
        } else {
            otp.setError("Enter Correct One");
            Toast.makeText(getApplicationContext(), "Wrong Otp", Toast.LENGTH_LONG).show();
        }


    }

    private void validation() {
        String pass = edit_new.getText().toString();
        String cpass = edit_confirm.getText().toString();

        if (pass == "") {

            edit_new.setError("Enter New Password");
        }
        if (cpass == "") {

            edit_confirm.setError("Enter Confirm password");
        }

        if (!pass.equals(cpass)) {
            Toast.makeText(ChangePassword.this, "Password Not matching", Toast.LENGTH_SHORT).show();

        }
        if (!(randomNumber == Integer.valueOf(otp.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Wrong Otp", Toast.LENGTH_LONG).show();


        } else {
            Intent i = new Intent(ChangePassword.this, ChangePassword.class);
            startActivity(i);

        }
    }
}
