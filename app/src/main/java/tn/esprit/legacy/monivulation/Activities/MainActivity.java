package tn.esprit.legacy.monivulation.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.savvi.rangedatepicker.CalendarPickerView;
import com.vlstr.blurdialog.BlurDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import devlight.io.library.ntb.NavigationTabBar;
import tn.esprit.legacy.monivulation.Data.DataSuppliers.CycleDS;
import tn.esprit.legacy.monivulation.Data.DataSuppliers.UserDS;
import tn.esprit.legacy.monivulation.Helpers.DialogHelper;
import tn.esprit.legacy.monivulation.Models.Cycle;
import tn.esprit.legacy.monivulation.Models.User;
import tn.esprit.legacy.monivulation.R;

public class MainActivity extends Activity {

    public static MainActivity instance = null;
    CalendarPickerView calendar;
    private static final String MY_PREFS_NAME = "myPrefs" ;
    private SharedPreferences prefs;
    private int userId;
    String strdate="", strdate2="" ;

    DialogHelper dialogHelper;
    BlurDialog blurDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;
        dialogHelper = new DialogHelper();

        prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        Boolean loggedIn = prefs.getBoolean("loggedIn", false);
        if (loggedIn) {
            userId = prefs.getInt("userId", 0);
        }

        initUI();


    }

    private void initUI() {

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);




        final ViewPager viewPager = findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                if(position==0){

                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.item_vp, null, false);

                    container.addView(view);

                    CycleDS ds = new CycleDS();
                    ds.getCycleInfo(String.valueOf(userId), new CycleDS.CallbackGet() {
                        @Override
                        public void onSuccess(Cycle result) {
                            TextView cycleInfo = view.findViewById(R.id.cycleInfo);
                            //TextView username = view.findViewById(R.id.username);
                            cycleInfo.setText(result.getCurrentDayOfCycle()+" "+result.getCurrentStatus());
                            //username.setText("hello User");
                            Log.d("myLog",result.getCurrentDayOfCycle()+result.getCurrentStatus());
                            Toast.makeText(MainActivity.this, result.getCurrentStatus(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Log.d("myLog","error");
                            Toast.makeText(MainActivity.this, "could not reach the server", Toast.LENGTH_LONG).show();
                        }

                    });

                    UserDS ds2 = new UserDS();
                    ds2.getUserById(userId, new UserDS.CallbackGet() {
                        @Override
                        public void onSuccess(User result) {
                            TextView username = view.findViewById(R.id.username);
                            username.setText(result.getFirstName()+" "+result.getLastName());

                        }

                        @Override
                        public void onError(VolleyError error) {
                            //Log.d("myLog","error");
                            Toast.makeText(MainActivity.this, "could not reach the server", Toast.LENGTH_LONG).show();
                        }

                    });


                    return view;

                } else if (position==1){
                    final View view = LayoutInflater.from(getBaseContext())
                            .inflate(R.layout.calender_range_picker, null, false);

                    container.addView(view);

                    calendar =  view.findViewById(R.id.calendar_view);

                    blurDialog = view.findViewById(R.id.blurLoader);


                    //blurDialog.show();
                    dialogHelper.blurDialogShow(instance,blurDialog,"Please wait");
                    CycleDS ds = new CycleDS();
                    ds.getCycleInfo(String.valueOf(userId), new CycleDS.CallbackGet() {
                        @Override
                        public void onSuccess(Cycle result) {

                            dialogHelper.blurDialogHide(instance,blurDialog);
                            //blurDialog.hide();
                            strdate = result.getFertilityStartDate();
                            strdate2 = result.getFertilityEndDate();
                            Log.d("myLog","fertility start : " + strdate);
                            Toast.makeText(MainActivity.this, "data loaded", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            dialogHelper.blurDialogHide(instance,blurDialog);
                            //blurDialog.hide();
                            Log.d("myLog","error");
                            Toast.makeText(MainActivity.this, "could not reach the server", Toast.LENGTH_LONG).show();
                        }

                    });


                    //calendar = new CalendarPickerView(getApplicationContext(), null);
                    //ArrayList<Integer> list = new ArrayList<>();
                    //list.add(1);

                    //calendar.deactivateDates(list);

                    ArrayList<Date> arrayList = new ArrayList<>();
                    try {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                        //strdate = "22-05-2018 00:00:00";
                        //strdate2 = "26-05-2018 00:00:00";
                        Log.d("myLog","test fertility start : " + strdate);
                        Date newdate = dateformat.parse(strdate);
                        Date newdate2 = dateformat.parse(strdate2);
                        dateformat.applyPattern("dd-MM-yyyy");
                        String dateStr = dateformat.format(newdate);
                        String dateStr2 = dateformat.format(newdate2);
                        Date finalDate = dateformat.parse(dateStr);
                        Date finalDate2 = dateformat.parse(dateStr2);
                        arrayList.add(finalDate);
                        arrayList.add(finalDate2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM yyyy", Locale.getDefault())) //
                            .inMode(CalendarPickerView.SelectionMode.RANGE) //
                            .withHighlightedDate(new Date())
                            .displayOnly()
                            //.withDeactivateDates(list)
                            .withSelectedDates(arrayList);
                    //.withHighlightedDates(arrayList);

                    return view;

                } else if (position==2){
                    final View view = LayoutInflater.from(getBaseContext())
                            .inflate(R.layout.item3_vp, null, false);

                    container.addView(view);

                    TextView temperature = view.findViewById(R.id.temperature);

                    temperature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), TemperatureLineChartActivity.class);
                            startActivity(intent);
                        }
                    });
                    return view;


                } else { // position==3

                    final View view = LayoutInflater.from(getBaseContext())
                            .inflate(R.layout.item4_vp, null, false);

                    container.addView(view);
                    TextView logoutBtn = view.findViewById(R.id.logout);
                    logoutBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("loggedIn", false);
                            //editor.putInt("userId", (int) result.getId());
                            editor.apply();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    });

                    return view;

                }

            }


        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home),
                        Color.parseColor(colors[0]))
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("Home")
                        .badgeTitle("luteal")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.calendar),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Calender")
                        .badgeTitle("fertile")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh),
                        Color.parseColor(colors[2]))
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("analytics")
                        .badgeTitle("period")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Me")
                        .badgeTitle("warning")
                        .build()
        );


        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
