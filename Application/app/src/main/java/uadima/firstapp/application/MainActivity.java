package uadima.firstapp.application;

import android.net.Uri;
import android.util.Log;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText text = (EditText) findViewById(R.id.num);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button divide = (Button) findViewById(R.id.divide);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button one = (Button) findViewById(R.id.one);
        Button two = (Button) findViewById(R.id.two);
        Button three = (Button) findViewById(R.id.three);
        Button four = (Button) findViewById(R.id.four);
        Button five = (Button) findViewById(R.id.five);
        Button six = (Button) findViewById(R.id.six);
        Button seven = (Button) findViewById(R.id.seven);
        Button eight = (Button) findViewById(R.id.eight);
        Button nine = (Button) findViewById(R.id.nine);
        Button probel = (Button) findViewById(R.id.probel);
        Button zero = (Button) findViewById(R.id.zero);
        Button calc = (Button) findViewById(R.id.calc);
        final CheckBox check = (CheckBox) findViewById(R.id.check);
        final TextView testone = (TextView) findViewById(R.id.testone);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = text.getText().toString();
                String[] res1 = new String[20];
                String[] action = new String[20];
                double sum = 0;
                int i=0;
                int j=0;
                for (Character c : str.toCharArray()) {
                    if (i+1==str.length()){
                        res1[j]=str.substring(1);
                        Log.d(TAG, j+1+"oe "+" последнее значение = "+res1[j]);
                    }
                    if (c.equals('+') || c.equals('-') || c.equals('/') || c.equals('*')) {
                        if (c.equals('+')) { action[j]="plus"; }
                        if (c.equals('-')) { action[j]="minus"; }
                        if (c.equals('/')) { action[j]="divide"; }
                        if (c.equals('*')) { action[j]="multiply"; }
                        if (j==0 ) {res1[j] = str.substring(0, i);} //в случае когдамы первый раз входим в if делаем сдвиг на 1 единицу влево, по скльку цифре не предшествует арифм знак
                        if (j>0 ) {res1[j] = str.substring(1, i);}
                        str=str.substring(i);
                        Log.d(TAG, j+1+" значение = "+res1[j]);
                        Log.d(TAG, action[j]+" действие");
                        j++;
                        i=0; //длина пройденной строки,обнуляется в случае нахождения арифм операции
                        }
                    i++;
                    }


                    for (int f = 0; f <= j; f++) {
                        for (int x=0; x<=j; x++) {
                        if (action[f] == "divide") {
                            Log.d(TAG, sum  + " делю "+res1[f]+"на"+res1[f+1]);
                            res1[f] = Integer.toString(Integer.parseInt(res1[f]) / Integer.parseInt(res1[f + 1]));
                            Log.d(TAG, sum  + " получаю "+res1[f]);
                            for (int t = f; t <= j; t++) {
                                if (t == j) {
                                    res1[t + 1] = "";
                                    action[t] = "";
                                } else {
                                    res1[t + 1] = res1[t + 2];
                                    action[t] = action[t + 1];
                                }
                            }
                        }
                        if (action[f] == "multiply") {
                            res1[f] = Integer.toString(Integer.parseInt(res1[f]) * Integer.parseInt(res1[f + 1]));
                            for (int t = f; t <= j; t++) {
                                if (t == j) {
                                    res1[t + 1] = "";
                                    action[t] = "";
                                } else {
                                    res1[t + 1] = res1[t + 2];
                                    action[t] = action[t + 1];
                                }
                            }
                        }
                    }
                    }
                    for (int f=0; f<=j; f++) {
                    if (action[f]=="plus"){
                        if (f==0) {sum=Integer.parseInt(res1[f])+Integer.parseInt(res1[f+1]);}
                        if (f>0) {sum=sum+Integer.parseInt(res1[f+1]);}
                    }
                    if (action[f]=="minus"){
                        if (f==0) {sum=Integer.parseInt(res1[f])-Integer.parseInt(res1[f+1]);}
                        if (f>0) {sum=sum-Integer.parseInt(res1[f+1]);}
                    }
                        if (f==j  && sum==0)
                        {
                            sum=Integer.parseInt(res1[0]);
                        }
                    }
                   Log.d(TAG, sum  + " результат ");
                }
             /**   for (int i=0; i<=action.length; i++){
                        if (action[i]=="plus"){



                }*/




        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "+");
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "-");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "/");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "*");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText().toString() + "0");
            }
        });

        probel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(null);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://uadima.firstapp.application/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://uadima.firstapp.application/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
