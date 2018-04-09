package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    Button submit;
    EditText edit;
    String sques;
    Fill s;
    DatabaseReference mDatabase;
    TextView text;
    String sans;
    TextView textView;
    private static final String FORMAT = "%02d:%02d:%02d";
    int x=0;
    public Second() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view=inflater.inflate(R.layout.fragment_second, container, false);
        submit=view.findViewById(R.id.next);
        submit.setText("next");
        edit=view.findViewById(R.id.edit);

        text=view.findViewById(R.id.textView);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                s = dataSnapshot.child("Question").child(3+"").getValue(Fill.class);
                sques = s.getQues();
                sans=s.getAns();

                text.setText(sques);


            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        //        TextView textView;
//        private static final String FORMAT = "%02d:%02d:%02d";

        textView=view.findViewById(R.id.timer);
        final CountDownTimer countDownTimer = new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {
                textView.setText(" " + "" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(l),
                        TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(l)),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(l))));

            }
            @Override
            public void onFinish(){
                if(x==0)
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag=new Fifth();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
            }
        }.start();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 x=x+1;
                if(!(edit.getText().toString().equals("")))
                {
                    if(edit.getText().toString().compareTo(sans)==0)
                    {
                        First.Score++;
                    }

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Fifth();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else
                Toast.makeText(getContext(), "Please add an answer.", Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

}
