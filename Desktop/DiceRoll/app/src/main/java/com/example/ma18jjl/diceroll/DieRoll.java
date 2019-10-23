package com.example.ma18jjl.diceroll;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import static com.example.ma18jjl.diceroll.R.id.die_roll;
import static com.example.ma18jjl.diceroll.R.id.info2;

public class DieRoll extends AppCompatActivity {

    private TextView title;
    private EditText user_input;
    private int counter = 0;
    private TextView user_points;
    private TextView point_tracker;
    private TextView info1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die_roll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        info1 = (TextView) findViewById(R.id.info);
        title = (TextView) findViewById(R.id.title);
        user_input = (EditText) findViewById(R.id.userInput);
        user_points = (TextView) findViewById(R.id.userpoints);
        point_tracker = (TextView) findViewById(R.id.counter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_die_roll, menu);
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

    public void on_button_click(View view)
    {
        //Rolling the die & button
        TextView tv = (TextView) this.findViewById(R.id.die_roll);
        Random r = new Random();
        int number = r.nextInt(6)+1;
        tv.setText(Integer.toString(number));

        //User input casted to int
        int n = Integer.parseInt(user_input.getText().toString());

        if (n < 1 || n > 6)
        {
            //User input validation
            Toast.makeText(this, "Error, please enter a number by 1 and 6", Toast.LENGTH_SHORT).show();
        }
        else if (n == number)
        {
            //Correct answer
            counter = counter + 5;
            user_points.setVisibility(View.VISIBLE);
            user_points.setText(Integer.toString(counter));
        }
        else
        {
            //Wrong answer
            counter = counter - 1;
            user_points.setVisibility(View.VISIBLE);
            user_points.setText(Integer.toString(counter));
        }
        if (counter <= -10)
        {
            //Lost game
            Toast.makeText(this, "Unlucky, you lost!", Toast.LENGTH_SHORT).show();
            TextView gameresult = (TextView) this.findViewById(info2);
            gameresult.setText("Better luck next time!");
            point_tracker.setText("You lost");
            user_points.setVisibility(View.INVISIBLE);
            info1.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
            user_input.setVisibility(View.INVISIBLE);
        }
        else if (counter >= 10)
        {
            //Won game
            Toast.makeText(this, "Well done! You had luck on your side", Toast.LENGTH_SHORT).show();
            TextView gameresult = (TextView) this.findViewById(info2);
            gameresult.setText("Congratulations!");
            point_tracker.setText("You beat the game!");
            user_points.setVisibility(View.INVISIBLE);
            info1.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
            user_input.setVisibility(View.INVISIBLE);
        }
    }
}
