package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fifth extends Fragment {

    Button submit;
    EditText edit;
    String sques;
    Fill s;
    DatabaseReference mDatabase;
    TextView text;

    String sans;
    CountDownTimer cfifth;
            TextView textView;
      private static final String FORMAT = "%02d:%02d:%02d";
    public Fifth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fifth, container, false);
        submit=view.findViewById(R.id.next1);
        submit.setText("next");
        edit=view.findViewById(R.id.edit);

        text=view.findViewById(R.id.textView);

        mDatabase = FirebaseDatabase.getInstance().getReference();

//yes
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                s = dataSnapshot.child("Question").child(4+"").getValue(Fill.class);
                sques = s.getQues();
                sans=s.getAns();

                text.setText(sques);


            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        textView=view.findViewById(R.id.timer);
        cfifth = new CountDownTimer(15000,1000) {
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
                    FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    Fragment frag1 = new Result();
                    ft1.replace(R.id.frames, frag1);
                    ft1.commit();

            }
        }.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                }
                cfifth.cancel();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
            }
        });
        return view;
    }

}
