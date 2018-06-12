package cal_on.cable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomerDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayAdapter<String> arrayAdapter;
    ArrayList<String> array_list = new ArrayList<String>();
    DBHelper mydb;
    Spinner amount;
    CustomerDataBase cddb;
    Button submit;
    String result;
    EditText name,mobileno,address,setupbox;
    TextView date,time;
    int dyear, dmonth, dday;
    String times = "hh:mm aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb = new DBHelper(this);
        cddb=new CustomerDataBase(this);
        submit=(Button)findViewById(R.id.button);
        array_list = mydb.getAllCotacts2();
        amount=(Spinner)findViewById(R.id.editText5);
        name=(EditText)findViewById(R.id.editText1);
        mobileno=(EditText)findViewById(R.id.editText2);
        address=(EditText)findViewById(R.id.editText3);
        setupbox=(EditText)findViewById(R.id.editText4);
        date=(TextView)findViewById(R.id.Dateate) ;
        time=(TextView)findViewById(R.id.time);
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

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, array_list);
        amount.setAdapter(arrayAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText1 = name.getText().toString();
                String editText2 = mobileno.getText().toString();
                String editText3 = address.getText().toString();

                String editText5 = setupbox.getText().toString();
                String editText4 = amount.getSelectedItem().toString();
                result = cddb.insertContact(editText1,editText2,editText3,editText4,editText5);
                Log.d(getClass().getName(), "value = " + result);
                if (name.length() != 0 && mobileno.length() != 0 && address.length() != 0 && setupbox.length() !=0) {

                    name.setText("");
                    mobileno.setText("");
                    address.setText("");
                    setupbox.setText("");

                    Toast.makeText(getApplicationContext()," Details  Successfully Stored",Toast.LENGTH_SHORT).show();
                }
                else if (name.length() == 0 && mobileno.length() == 0 && address.length() == 0 && setupbox.length() !=0) {
                    Toast.makeText(getApplicationContext(),"Please Enter the Details",Toast.LENGTH_SHORT).show();

                }
            }
        });
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
        getMenuInflater().inflate(R.menu.customer_details, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_camera:
                Intent h = new Intent(CustomerDetails.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_gallery:
                Intent i = new Intent(CustomerDetails.this, CustomerDetails.class);
                startActivity(i);
                break;
            case R.id.nav_slideshow:
                Intent g = new Intent(CustomerDetails.this, Billing.class);
                startActivity(g);
                break;
            case R.id.nav_share:
                Intent s = new Intent(CustomerDetails.this, CustomerReport.class);
                startActivity(s);
                break;
            case R.id.nav_send:
                Intent t = new Intent(CustomerDetails.this, DayReport.class);
                startActivity(t);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
