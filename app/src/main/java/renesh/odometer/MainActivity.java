package renesh.odometer;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button start,stop,split,reset;
    TextView totDeciTen,totDeci,totTen,totHund,totThous;
    TextView splitDeciTen,splitDeci,splitTen,splitHund,splitThous;
    EditText log;
    double totalMeters,splitMeters;
    Timer timer;
    int kph;
    int splitCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalMeters = 0;
        splitMeters = 0;
        splitCounter= 1;
        kph = 0;
        timer = new Timer();
        start =(Button) findViewById(R.id.btnStart);
        stop =(Button) findViewById(R.id.btnStop);
        split =(Button) findViewById(R.id.btnSplit);
        reset =(Button) findViewById(R.id.btnReset);

        stop.setEnabled(false);
        start.setEnabled(true);
        split.setEnabled(false);
        reset.setEnabled(false);

        log = (EditText) findViewById(R.id.etLog);

        totDeciTen =(TextView) findViewById(R.id.tvTotDeciTen);
        totDeci =(TextView) findViewById(R.id.tvTotDeci);
        totTen =(TextView) findViewById(R.id.tvTotTen);
        totHund =(TextView) findViewById(R.id.tvTotHund);
        totThous =(TextView) findViewById(R.id.tvTotThou);

        splitDeciTen =(TextView) findViewById(R.id.tvSplitDeciTen);
        splitDeci =(TextView) findViewById(R.id.tvSplitDeci);
        splitTen =(TextView) findViewById(R.id.tvSplitTen);
        splitHund =(TextView) findViewById(R.id.tvSplitHund);
        splitThous =(TextView) findViewById(R.id.tvSplitThou);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUi();
            }
        });
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                split();
            }
        });
    }

    public void start(){
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                move();
                updateUi();
            }
        };
        timer.scheduleAtFixedRate(tt,0,100);
        stop.setEnabled(true);
        start.setEnabled(false);
        split.setEnabled(true);
        reset.setEnabled(true);

    }
    public void stop(){
        timer.cancel();
        timer.purge();
        stop.setEnabled(false);
        start.setEnabled(true);
        split.setEnabled(false);
        reset.setEnabled(false);

    }
    public void resetUi(){
        totalMeters = 0;
        splitMeters = 0;
        splitCounter = 0;
        kph = 0;
        updateUi();
    }
    public void split(){
        double kmLog = (int) ((splitMeters/10.0)/100.0);
        log.append(splitCounter +". " + kmLog +" "+ getString(R.string.unitOfMeasure) + "\n");
        splitMeters = 0;
        splitCounter++;
    }

    public void move(){
        double metersPerMove;
        metersPerMove =((kph/3600.0)*1000.0)/10.0;
        totalMeters += metersPerMove;
        splitMeters += metersPerMove;
    }

    public void updateUi(){
        int tempTotal = (int) totalMeters;
        int tempSplit = (int) splitMeters;

        totThous.setText((tempTotal/100000));
        totHund.setText(((tempTotal/10000)%10));
        totTen.setText(((tempTotal/1000)%10));
        totDeci.setText(((tempTotal/100)%10));
        totDeciTen.setText(((tempTotal/1000)%10));

        splitThous.setText((tempSplit/100000));
        splitHund.setText(((tempSplit/10000)%10));
        splitTen.setText(((tempSplit/1000)%10));
        splitDeci.setText(((tempSplit/100)%10));
        splitDeciTen.setText(((tempSplit/1000)%10));


    }
}
