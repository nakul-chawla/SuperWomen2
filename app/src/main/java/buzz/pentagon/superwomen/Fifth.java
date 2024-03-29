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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    ProgressBar pr1;
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
        pr1=view.findViewById(R.id.progressBar5);
        text=view.findViewById(R.id.textView);
        textView=view.findViewById(R.id.timer);

        mDatabase = FirebaseDatabase.getInstance().getReference("Question");

//yes
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                s = dataSnapshot.child(4+"").getValue(Fill.class);
                sques = s.getQues();
                sans=s.getAns();
                pr1.setVisibility(View.INVISIBLE);

                text.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
               textView.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                text.setText("Q.)"+sques);

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
                        if(!(edit.getText().toString().equals(""))) {
                            if (edit.getText().toString().compareTo(sans) == 0) {
                                First.Score++;
                            }
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Eleven();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else {
                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                            Fragment frag1 = new Eleven();
                            ft1.replace(R.id.frames, frag1);
                            ft1.commit();
                        }
                    }
                }.start();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!edit.getText().toString().equals("")) {
                   if (edit.getText().toString().compareTo(sans) == 0) {
                       First.Score++;
                   }
                   cfifth.cancel();
                   FragmentTransaction ft = getFragmentManager().beginTransaction();
                   Fragment frag = new Eleven();
                   ft.replace(R.id.frames, frag);
                   ft.commit();
               }
               else
                   Toast.makeText(getContext(), "Please enter answer", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
