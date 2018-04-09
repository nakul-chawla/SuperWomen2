package buzz.pentagon.superwomen;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

public class MainActivity extends AppCompatActivity {
    private static final String FORMAT = "%02d:%02d:%02d";
    TextView clock;
    //DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 //       clock=findViewById(R.id.clock);

//
//        final CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
//            @Override
//            public void onTick(long l) {
//                clock.setText(" " + "" + String.format(FORMAT,
//                        TimeUnit.MILLISECONDS.toHours(l),
//                        TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(
//                                TimeUnit.MILLISECONDS.toHours(l)),
//                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(
//                                TimeUnit.MILLISECONDS.toMinutes(l))));
//
//            }
//            @Override
//            public void onFinish(){
//                FragmentTransaction ft1 =getSupportFragmentManager().beginTransaction();
//                Fragment frag1 = new Result();
//                ft1.replace(R.id.frames, frag1);
//                ft1.commit();
//            }
//        }.start();

               FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment frag=new Fourth();
             ft.replace(R.id.frames, frag);
                ft.commit();

//                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
//                intent.putExtra("Questions",sques);
//                startActivity(intent);



    }


}


