package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Third extends Fragment {
    Button button;
    String sques;
    True t;
    DatabaseReference mDatabase;
    TextView text;
    RadioGroup radioGroup;
    RadioButton op1;
    RadioButton op2;


    String sop1;
    String sop2;
    String sans;
            TextView textView;
        private static final String FORMAT = "%02d:%02d:%02d";
    CountDownTimer cthird;
    ProgressBar pg;


    public Third() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_third, container, false);
        // Inflate the layout for this fragment
        button=view.findViewById(R.id.next);
        radioGroup=view.findViewById(R.id.grp);
        op1=view.findViewById(R.id.first);
        op2=view.findViewById(R.id.second);
        pg=view.findViewById(R.id.progressBar4);
        textView=view.findViewById(R.id.timer);

//        op3=view.findViewById(R.id.third);
//        op4=view.findViewById(R.id.fourth);
        text=view.findViewById(R.id.textView);

        mDatabase = FirebaseDatabase.getInstance().getReference("Question");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                t = dataSnapshot.child(2+"").getValue(True.class);
                sques = t.getQues();
                sop1=t.getOp1();
                sop2=t.getOp2();

                pg.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);


                text.setText("Q.)"+sques);
                sans=t.getAns();

                op1.setText(sop1);
                op2.setText(sop2);

                cthird = new CountDownTimer(15000,1000) {
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
                        if (op1.isChecked()&&op1.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Fifth();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else if (op2.isChecked()&&op2.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Fifth();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else{
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Fifth();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                    }
                }.start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (op1.isChecked()&&op1.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cthird.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Fifth();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if (op2.isChecked()&&op2.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cthird.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Fifth();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if(op1.isChecked()||op2.isChecked()) {
                    cthird.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Fifth();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else
                    Toast.makeText(getContext(), "Choose one option", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
