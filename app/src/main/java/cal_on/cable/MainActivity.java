package cal_on.cable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    Button Submit;
    EditText name,mobileno,amount;
    String result;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelper(this);
        Submit=(Button)findViewById(R.id.button) ;
        name=(EditText)findViewById(R.id.editText1);
        mobileno=(EditText)findViewById(R.id.editText2);
        amount=(EditText)findViewById(R.id.editText3);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText1 = name.getText().toString();
                String editText2 = mobileno.getText().toString();
                String editText3 = amount.getText().toString();
                result = db.insertContact(editText1,editText2,editText3);
                Log.d(getClass().getName(), "value = " + result);
                if (name.length() != 0 && mobileno.length() != 0 && amount.length() != 0) {

                    name.setText("");
                    mobileno.setText("");
                    amount.setText("");
                    Toast.makeText(getApplicationContext()," Details  Successfully Stored",Toast.LENGTH_SHORT).show();
                }
                else if (name.length() == 0 && mobileno.length() == 0 && amount.length() == 0) {
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

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
                Intent h = new Intent(MainActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_gallery:
                Intent i = new Intent(MainActivity.this, CustomerDetails.class);
                startActivity(i);
                break;
            case R.id.nav_slideshow:
                Intent g = new Intent(MainActivity.this, Billing.class);
                startActivity(g);
                break;
            case R.id.nav_share:
               Intent s = new Intent(MainActivity.this, CustomerReport.class);
               startActivity(s);
                break;
            case R.id.nav_send:
                Intent t = new Intent(MainActivity.this, DayReport.class);
                startActivity(t);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
