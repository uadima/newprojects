package uadima.firstapp.application;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient client;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (EditText) findViewById(R.id.num);

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

    private final static Character plus = '+';
    private final static Character minus = '-';
    private final static Character divide = '/';
    private final static Character multiply = '*';
    private final static Character openbreacket = '(';
    private final static Character closebreacket = ')';
    private int temp = -1;
    String nextreserved = new String();


    public void calcButtonOnClick(View view) {
        text.setText(text.getText().toString() + ((Button) view).getText().toString());
    }


    public void naHuiOnClick(View view) {
        text.setText(null);
    }

    public void calcOnClick(View view) {
        try {
            calc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void calc() {
        String str = text.getText().toString();
        temp = -1;
        int sum = phasebreacket(str);
        Log.d(TAG, sum + " результат ");
    }

    private int phasebreacket(String next) {
        Log.d(TAG, "получил на обработку строку " + next);
        boolean flag = false;
        if ((temp != -1 && nextreserved.length() < 2)) {
            Log.d(TAG, "длина оставшейся строки минимальна отдаю на выход ");
            return (Integer.parseInt(next));
        }
        if (temp != -1) {
            String beforeSubStr = nextreserved.substring(0, temp);
            String afterSubStr = nextreserved.substring(temp);
            next = beforeSubStr + next + afterSubStr;
            Log.d(TAG, "Склеиваю это дерьмо - получаю уравнение вида: " + next);
            nextreserved = ""; // дабы не зациклится удалим склееную строку
        }
        if (next.indexOf('+') == -1 && next.indexOf('-') == -1 && next.indexOf('/') == -1 && next.indexOf('*') == -1 && next.indexOf(')') == -1 && next.indexOf('(') == -1 && nextreserved.indexOf('+') == -1 && nextreserved.indexOf('-') == -1 && nextreserved.indexOf('/') == -1 && nextreserved.indexOf('*') == -1 && nextreserved.indexOf(')') == -1 && nextreserved.indexOf('(') == -1)
            return (Integer.parseInt(next));
        for (int i = 0; i < next.toCharArray().length; i++) {
            if (closebreacket.equals(next.toCharArray()[i])) {
                Log.d(TAG, " Схуяли ты закрываеш ещё не открытую скобку!? 2 ка в аттестат неглядя ");
                return (0);
            }
            if (openbreacket.equals(next.toCharArray()[i])) {
                temp = i;
                for (int x = i; x < next.toCharArray().length; x++) {
                    if (openbreacket.equals(next.toCharArray()[x]))
                        temp = x; // в случае если находим ещё 1ну открывающуюся скобку, забываем прошлую
                    if (closebreacket.equals(next.toCharArray()[x])) {
                        nextreserved = next.substring(0, temp) + next.substring(x + 1);
                        Log.d(TAG, "оставляю в памяти строку " + nextreserved);
                        next = next.substring(temp + 1, x);
                        flag = true;
                        break;
                    }
                    if (x + 1 == next.toCharArray().length) {
                        Log.d(TAG, "Закрой скобку, мудак! ");
                        return (0);
                    }
                }
            }
        }
        if (flag) {
            Log.d(TAG, "выражение в скобках " + next);
        } else Log.d(TAG, "выражений в скобках не найдено");
        return calc(next);
    }


    private int calc(String next) {
        List<String> res1 = new ArrayList<>();
        List<String> action = new ArrayList<>();
        for (int i = 0; i <= next.toCharArray().length; i++) {
            if (i == next.length()) {
                res1.add(next);
                Log.d(TAG, res1.size() + "oe " + " последнее значение = " + res1.get(res1.size() - 1));
                break;
            }
            if (plus.equals(next.toCharArray()[i]) || minus.equals(next.toCharArray()[i]) || divide.equals(next.toCharArray()[i]) || multiply.equals(next.toCharArray()[i])) {
                if (plus.equals(next.toCharArray()[i])) {
                    action.add("plus");
                }
                if (minus.equals(next.toCharArray()[i])) {
                    action.add("minus");
                }
                if (divide.equals(next.toCharArray()[i])) {
                    action.add("divide");
                }
                if (multiply.equals(next.toCharArray()[i])) {
                    action.add("multiply");
                }
                res1.add(next.substring(0, i));
                next = next.substring(i + 1);
                Log.d(TAG, res1.size() + " значение = " + res1.get(res1.size() - 1));
                Log.d(TAG, action.get(action.size() - 1) + " действие");
                i = 0; //длина пройденной строки,обнуляется в случае нахождения арифм операции
            }
        }
        return phasetwo(res1, action);
    }

    private int phasetwo(List<String> res1, List<String> action) {
        for (int f = 0; f < action.size(); f++) {
            for (int x = 0; x <= action.size() - 1; x++) {
                if (action.get(f) == "divide") {
                    Log.d(TAG, " делю " + res1.get(f) + "на" + res1.get(f + 1));
                    res1.set(f, Integer.toString(Integer.parseInt(res1.get(f)) / Integer.parseInt(res1.get(f + 1))));
                    Log.d(TAG, " получаю " + res1.get(f));
                    for (int t = f; t <= action.size() - 1; t++) {
                        if (t == action.size() - 1) {
                            res1.set(t + 1, "");
                            action.set(t, "");
                        } else {
                            res1.set(t + 1, res1.get(t + 2));
                            action.set(t, action.get(t + 1));
                        }
                    }
                }
                if (action.get(f) == "multiply") {
                    res1.set(f, Integer.toString(Integer.parseInt(res1.get(f)) * Integer.parseInt(res1.get(f + 1))));
                    for (int t = f; t <= action.size() - 1; t++) {
                        if (t == action.size() - 1) {
                            res1.set(t + 1, "");
                            action.set(t, "");
                        } else {
                            res1.set(t + 1, res1.get(t + 2));
                            action.set(t, action.get(t + 1));
                        }
                    }
                }
            }
        }
        return phaseThree(res1, action);
    }

    private int phaseThree(List<String> res1, List<String> action) {
        int sum = 0;
        for (int f = 0; f < action.size(); f++) {
            if (action.get(f) == "plus") {
                if (f == 0) {
                    sum = Integer.parseInt(res1.get(f)) + Integer.parseInt(res1.get(f + 1));
                }
                if (f > 0) {
                    sum = sum + Integer.parseInt(res1.get(f + 1));
                }
            }
            if (action.get(f) == "minus") {
                if (f == 0) {
                    sum = Integer.parseInt(res1.get(f)) - Integer.parseInt(res1.get(f + 1));
                }
                if (f > 0) {
                    sum = sum - Integer.parseInt(res1.get(f + 1));
                }
            }
        }
        if (0 == sum) {
            sum = Integer.parseInt(res1.get(0));
        }
        return phasebreacket(Integer.toString(sum));
    }

}
