package cal_on.cable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Billing extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView date,time;
    int dyear, dmonth, dday;
    String times = "hh:mm aaa";
    EditText name,address,mobile,setup,amount;
    ImageView nameim,addressim,mobileim,setupim,amountim;
    CustomerDataBase cddb;
    SQLiteDatabase data;
    String empty;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        date=(TextView)findViewById(R.id.Dateate) ;
        time=(TextView)findViewById(R.id.time);
        name=(EditText)findViewById(R.id.editText1);
        address=(EditText)findViewById(R.id.editText3);
        mobile=(EditText)findViewById(R.id.editText2);
        setup=(EditText)findViewById(R.id.editText4);
        amount=(EditText)findViewById(R.id.editText5);
        nameim=(ImageView)findViewById(R.id.name);
        mobileim=(ImageView)findViewById(R.id.mobile);
        addressim=(ImageView)findViewById(R.id.address);
        setupim=(ImageView)findViewById(R.id.setupbox);
        cddb=new CustomerDataBase(this);
        nameim.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        empty=name.getText().toString();
        cddb=new CustomerDataBase(getApplicationContext());
        data=cddb.getReadableDatabase();
        Cursor cursor=cddb.getdata(empty,data);
        if (cursor.moveToFirst()){
            String Village=cursor.getString(0);
            String mobilenum=cursor.getString(1);
            String setup1=cursor.getString(3);
            String amount1=cursor.getString(2);


            mobile.setText(Village);
            address.setText(mobilenum);
            setup.setText(setup1);
            amount.setText(amount1);
        }
else {
            Toast.makeText(getApplication(),"Details Not Found",Toast.LENGTH_LONG).show();
        }
    }
});
        mobileim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty=mobile.getText().toString();
                cddb=new CustomerDataBase(getApplicationContext());
                data=cddb.getReadableDatabase();
                Cursor cursor=cddb.getdata1(empty,data);
                if (cursor.moveToFirst()){
                    String Village=cursor.getString(0);
                    String mobilenum=cursor.getString(1);
                    String setup1=cursor.getString(3);
                    String amount1=cursor.getString(2);


                    name.setText(Village);
                    address.setText(mobilenum);
                    setup.setText(setup1);
                    amount.setText(amount1);
                }
                else {
                    Toast.makeText(getApplication(),"Details Not Found",Toast.LENGTH_LONG).show();
                }
            }
        });
        addressim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty=address.getText().toString();
                cddb=new CustomerDataBase(getApplicationContext());
                data=cddb.getReadableDatabase();
                Cursor cursor=cddb.getdata2(empty,data);
                if (cursor.moveToFirst()){
                    String Village=cursor.getString(0);
                    String mobilenum=cursor.getString(1);
                    String setup1=cursor.getString(3);
                    String amount1=cursor.getString(2);


                    name.setText(Village);
                    mobile.setText(mobilenum);
                    setup.setText(setup1);
                    amount.setText(amount1);
                }
                else {
                    Toast.makeText(getApplication(),"Details Not Found",Toast.LENGTH_LONG).show();
                }
            }
        });
        setupim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty=setup.getText().toString();
                cddb=new CustomerDataBase(getApplicationContext());
                data=cddb.getReadableDatabase();
                Cursor cursor=cddb.getdata3(empty,data);
                if (cursor.moveToFirst()){
                    String Village=cursor.getString(0);
                    String mobilenum=cursor.getString(1);
                    String setup1=cursor.getString(3);
                    String amount1=cursor.getString(2);


                    name.setText(Village);
                    mobile.setText(mobilenum);
                    address.setText(setup1);
                    amount.setText(amount1);
                }
                else {
                    Toast.makeText(getApplication(),"Details Not Found",Toast.LENGTH_LONG).show();
                }

            }
        });
        final Calendar c = Calendar.getInstance();
        dyear = c.get(Calendar.YEAR);
        dmonth = c.get(Calendar.MONTH);
        dday = c.get(Calendar.DAY_OF_MONTH);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateDepositDateDisplay();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void updateDepositDateDisplay() {

        date.setText(String.format("%02d-%02d-%04d", dday, dmonth + 1, dyear));
        java.util.Date noteTS = Calendar.getInstance().getTime();

        time.setText(DateFormat.format(times, noteTS));
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.billing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id) {

            case R.id.nav_camera:
                Intent h = new Intent(Billing.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_gallery:
                Intent i = new Intent(Billing.this, CustomerDetails.class);
                startActivity(i);
                break;
            case R.id.nav_slideshow:
                Intent g = new Intent(Billing.this, Billing.class);
                startActivity(g);
                break;
            case R.id.nav_share:
                Intent s = new Intent(Billing.this, CustomerReport.class);
                startActivity(s);
                break;
            case R.id.nav_send:
                Intent t = new Intent(Billing.this, DayReport.class);
                startActivity(t);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
