package com.anitha.offsitefinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class MyCustomAdapter extends BaseAdapter {

    private static final int L0 = 0;
    private static final int L1 = 1;
    private static final int L2 = 2;
    private static final int L3 = 3;

    private static int mlayout = 0;
    private static int mlayoutAry[] = new int[300];
    private static int mCount = 0;


    private static final int MAX_LAYOUT_COUNT = 4;

    private ArrayList<ListItem> mData = new ArrayList<ListItem>();
    private LayoutInflater mInflater;


    Context context;
    int flag = -1;


    public MyCustomAdapter(Context context) {

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;

    }


    public void addItem(final ListItem listItem) {

        for (int i = 0; i < 9; i++) {

            mlayout = 0;
            mlayoutAry[mCount++] = mlayout;

            mData.add(listItem);

            notifyDataSetChanged();
        }
    }

    public void addItem1(final ListItem listItem) {
        for (int i = 0; i < 9; i++) {
            mlayout = 1;
            mlayoutAry[mCount++] = mlayout;

            mData.add(listItem);
            notifyDataSetChanged();
        }
    }

    public void addItem2(final ListItem listItem) {
        for (int i = 0; i < 9; i++) {
            mlayout = 2;
            mlayoutAry[mCount++] = mlayout;

            mData.add(listItem);
            notifyDataSetChanged();
        }
    }

    public void addItem3(final ListItem listItem) {
        for (int i = 0; i < 9; i++) {
            mlayout = 3;
            mlayoutAry[mCount++] = mlayout;

            mData.add(listItem);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemViewType(int position) {
        //mlayoutAry[position] = mlayout;
        /*if (position==0)
            return L0;
        else if (mSeparatorsSet1.contains(position))
            return L1;
        else if (mSeparatorsSet2.contains(position))
            return L2;
        else
            return L3;*/
        return mlayoutAry[position];
    }


    @Override
    public int getViewTypeCount() {
        return MAX_LAYOUT_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ListItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View convertViews;

        convertViews = convertView;

        ViewHolder holder = null;
        ListItem myImage = mData.get(position);
        int type = getItemViewType(position);


        if (convertView == null) {
            holder = new ViewHolder();


            switch (type) {
                case L0:
                    convertView = mInflater.inflate(R.layout.row_type3, null);
                    final Button btnyes, btnno,btn_sub_comment;
                    ImageButton camera_clk;
                    final TextView edit_comment;
                   final EditText comment;


                    holder.textView = (TextView) convertView.findViewById(R.id.with_camera);
                    /* holder.button_yes = (Button) convertView.findViewById(R.id.btnyes1);*/
                    //   holder.button_no = (Button) convertView.findViewById(R.id.btnno1);
                    btnyes = (Button) convertView.findViewById(R.id.btnyes);
                    btnno = (Button) convertView.findViewById(R.id.btnno);
                    //   camera_clk=(ImageButton)convertView.findViewById(R.id.camera_clk);
                    edit_comment = (TextView) convertView.findViewById(R.id.user_entry1);

                    /*comment=(EditText)convertView.findViewById(R.id.edit_comment);
                    comment.setFocusable(true);

                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(comment, InputMethodManager.SHOW_IMPLICIT);
*/



                    btnyes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Change the text color and button background when we pressed
                            Drawable dr = context.getResources().getDrawable(R.drawable.btncancelstyle);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btnyes.setBackgroundResource(R.drawable.btncancelstyle);
                            btnyes.setTextColor(context.getResources().getColor(R.color.white));
                            //btnyes = (Button) findViewById(v.getId());
                            Toast.makeText(context, "You Clicked NO ", Toast.LENGTH_SHORT).show();


                            btnyes.setBackgroundDrawable(dr);
                            btnno.setBackgroundResource(R.drawable.sos_select);
                            btnno.setTextColor(context.getResources().getColor(R.color.text_color_black));





                        }
                    });




                    btnno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final EditText comment;

                            LayoutInflater li = LayoutInflater.from(context);


                            View promptsView = li.inflate(R.layout.row_item, null);


                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setView(promptsView);

                            comment=(EditText)promptsView.findViewById(R.id.edit_comment);
                            comment.requestFocus();
                            comment.setShowSoftInputOnFocus(true);

/*
                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(comment, InputMethodManager.SHOW_IMPLICIT);*/

                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);


                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (hasFocus) {
                                        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                                    }
                                }
                            });




                            alertDialog.show();


                            Drawable dr = context.getResources().getDrawable(R.drawable.sos_design);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btnno.setBackgroundResource(R.drawable.sos_design);
                            btnno.setTextColor(context.getResources().getColor(R.color.white));

                            Toast.makeText(context, "You Clicked NO ", Toast.LENGTH_SHORT).show();

                            btnyes.setBackgroundResource(R.drawable.sos_select);
                            btnyes.setTextColor(context.getResources().getColor(R.color.text_color_black));





                        }
                    });


                    break;

                case L1:

                  final ImageButton btn_cam,btn_msg;
                    final EditText save_comment,comment_enter;
                    final Button btn_sub,btn_yes2,btn_no2;
                    convertView = mInflater.inflate(R.layout.row_type4, null);
                    holder.textView1 = (TextView) convertView.findViewById(R.id.without_camera_ques);
                    holder.textView1.setText(myImage.getName());

                    btn_cam=(ImageButton)convertView.findViewById(R.id.camera_clk1);
                    btn_msg=(ImageButton)convertView.findViewById(R.id.comment);

                    save_comment=(EditText) convertView.findViewById(R.id.comment_save);
                    comment_enter=(EditText) convertView.findViewById(R.id.edit_comment);

                    btn_sub=(Button) convertView.findViewById(R.id.btn_sub);

                    btn_yes2=(Button) convertView.findViewById(R.id.btn_yes2);
                    btn_no2=(Button) convertView.findViewById(R.id.btn_no2);

                    comment_enter.setVisibility(View.GONE);
                    btn_sub.setVisibility(View.GONE);


                    btn_no2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Change the text color and button background when we pressed
                            Drawable dr = context.getResources().getDrawable(R.drawable.btncancelstyle);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btn_no2.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_no2.setTextColor(context.getResources().getColor(R.color.white));
                            //btnyes = (Button) findViewById(v.getId());
                            Toast.makeText(context, "You Clicked NO ", Toast.LENGTH_SHORT).show();

                            comment_enter.setVisibility(View.GONE);
                            btn_sub.setVisibility(View.GONE);
                            btn_no2.setBackgroundDrawable(dr);
                            btn_yes2.setBackgroundResource(R.drawable.sos_select);
                            btn_yes2.setTextColor(context.getResources().getColor(R.color.text_color_black));





                        }
                    });



                    btn_yes2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Drawable dr = context.getResources().getDrawable(R.drawable.sos_design);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btn_yes2.setBackgroundResource(R.drawable.sos_design);
                            btn_yes2.setTextColor(context.getResources().getColor(R.color.white));

                            Toast.makeText(context, "You Clicked YES ", Toast.LENGTH_SHORT).show();
                            comment_enter.setVisibility(View.GONE);
                            btn_sub.setVisibility(View.GONE);
                            btn_no2.setBackgroundResource(R.drawable.sos_select);
                            btn_no2.setTextColor(context.getResources().getColor(R.color.text_color_black));



                        }
                    });

                    btn_msg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            comment_enter.setVisibility(View.VISIBLE);
                            btn_sub.setVisibility(View.VISIBLE);

                         //  String save = btn_sub.getText().toString();
                            btn_sub.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String save = comment_enter.getText().toString();
                                    save_comment.setText("User Comment:\t" + save );
                                    comment_enter.setVisibility(View.GONE);
                                    btn_sub.setVisibility(View.GONE);


                                }
                            });

                        }
                    });




                    break;




                case L2:
                    final ImageButton btn_cal, camera_click;


                    final EditText user_entry;
                    final Button btn_yes;
                    final Button btn_no;





                    convertView = mInflater.inflate(R.layout.calendar_questions, null);
                    holder.ques_head = (TextView) convertView.findViewById(R.id.ques_head);
                    holder.ques_head.setText(myImage.getName());


                    btn_yes = (Button) convertView.findViewById(R.id.btnyes1);
                    btn_no = (Button) convertView.findViewById(R.id.btnno1);

                    user_entry = (EditText) convertView.findViewById(R.id.user_entry1);
                    btn_cal = (ImageButton) convertView.findViewById(R.id.cal);
                    camera_click = (ImageButton) convertView.findViewById(R.id.camera_clk1);

                    user_entry.setVisibility(View.GONE);
                    btn_cal.setVisibility(View.GONE);
                    camera_click.setVisibility(View.GONE);


                    btn_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Change the text color and button background when we pressed
                            Drawable dr = context.getResources().getDrawable(R.drawable.btncancelstyle);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btn_no.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_no.setTextColor(context.getResources().getColor(R.color.white));
                            //btnyes = (Button) findViewById(v.getId());
                            Toast.makeText(context, "You Clicked NO ", Toast.LENGTH_SHORT).show();

                            user_entry.setVisibility(View.VISIBLE);
                            btn_cal.setVisibility(View.VISIBLE);
                            camera_click.setVisibility(View.VISIBLE);
                            btn_no.setBackgroundDrawable(dr);
                            btn_yes.setBackgroundResource(R.drawable.sos_select);
                            btn_yes.setTextColor(context.getResources().getColor(R.color.text_color_black));





                        }
                    });



                    btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Drawable dr = context.getResources().getDrawable(R.drawable.sos_design);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btn_yes.setBackgroundResource(R.drawable.sos_design);
                            btn_yes.setTextColor(context.getResources().getColor(R.color.white));

                            Toast.makeText(context, "You Clicked YES ", Toast.LENGTH_SHORT).show();
                            user_entry.setVisibility(View.GONE);
                            btn_cal.setVisibility(View.GONE);
                            camera_click.setVisibility(View.GONE);
                            btn_no.setBackgroundResource(R.drawable.sos_select);
                            btn_no.setTextColor(context.getResources().getColor(R.color.text_color_black));



                        }
                    });


                  /*  btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Change the text color and button background when we pressed
                            Drawable dr = context.getResources().getDrawable(R.drawable.btncancelstyle);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


                            btn_yes.setBackgroundResource(R.drawable.btncancelstyle);
                            btn_yes.setTextColor(context.getResources().getColor(R.color.white));
                            //btnyes = (Button) findViewById(v.getId());
                            Toast.makeText(context, "You Clicked YES ", Toast.LENGTH_SHORT).show();

//                                        user_entry.setVisibility(View.VISIBLE);
//                                        btn_cal.setVisibility(View.VISIBLE);
//                                        camera_click.setVisibility(View.VISIBLE);
                            // btn_no.setEnabled(false);
                            btn_no.setClickable(true);
                            btn_no.setEnabled(false);

                            btn_yes.setBackgroundDrawable(dr);


                        }
                    });*/
                   /* btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            //Change the text color and button background when we pressed
                            Drawable dr = context.getResources().getDrawable(R.drawable.btncancelstyle);
                            dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

                            switch (v.getId()) {
                                case R.id.btnyes1:

                                    if (btn_yes == null) {
                                        // btnyes = (Button) findViewById(v.getId());
                                    } else {
                                        btn_yes.setBackgroundResource(R.drawable.btncancelstyle);
                                        btn_yes.setTextColor(context.getResources().getColor(R.color.white));
                                        //btnyes = (Button) findViewById(v.getId());
                                        Toast.makeText(context, "You Clicked YES ", Toast.LENGTH_SHORT).show();


                                    }
                                    btn_yes.setBackgroundDrawable(dr);

                                    break;


                                default:
                                    break;
                            }


                        }
                    });
*/

                    camera_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            /*CaptureImage.capturingImage = true;
                            Handler handler = new Handler((Looper.getMainLooper()));
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        CaptureImage.takePhoto(context,"12345", false);
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        CaptureImage.capturingImage = false;
                                        e.printStackTrace();
                                    }
                                }
                            });


*/
                            RegCaptureImage.startCamera("12345", App.getAct());

                        }
                    });


                    btn_cal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog;
                            TextView textfilter;

                            int year;
                            int month;
                            int dayOfMonth;
                            Calendar calendar;
                            TextView edit_cal;

                            // datePickerDialog.setTitle(null);
                            calendar = Calendar.getInstance();
                            year = calendar.get(Calendar.YEAR);
                            month = calendar.get(Calendar.MONTH);
                            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                            datePickerDialog = new DatePickerDialog(context, R.style.CalendarDatePickerDialog,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                            Toast.makeText(context, "date", Toast.LENGTH_SHORT).show();

                                            user_entry.setText(day + "/" + (month + 1) + "/" + year);



                                        }
                                    }, year, month, dayOfMonth);
                            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                            datePickerDialog.show();

                        }
                    });


                    break;

                case L3:
                    convertView = mInflater.inflate(R.layout.radio_question, null);
                    holder.text_radio = (TextView) convertView.findViewById(R.id.question_radio);
                    holder.text_radio.setText(myImage.getName());
                    break;
                default:
                    System.exit(0);
                    break;

            }
        } else {
            //convertViews = convertView;

            holder = (ViewHolder) convertView.getTag();


        }


        switch (type) {
            case L0:


                holder.textView.setText("1. Is Uniform complete, Clean and Ironed?");


                break;
            case L1:
                holder.textView1.setText("1. Availability of Material records/register");
                break;
            case L2:
                holder.ques_head.setText("1. Date of renewal of existing site and post instruction");
                // user_entry.setText(myImage.getName());
                break;
            case L3:
                holder.text_radio.setText("1. Is there any incident reported in last one month?");
                break;

            default:
                System.exit(0);
                break;
        }
        convertView.setTag(holder);


        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public TextView textView1;
        public TextView text_radio;


        public TextView ques_head;
        public Button btn_yes;
        public Button btn_no;
        public EditText user_entry;
        public ImageButton cal;
        public ImageButton cam;


    }
}

