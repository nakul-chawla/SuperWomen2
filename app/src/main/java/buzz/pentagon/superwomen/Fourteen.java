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
 * Created by Nakul on 12-04-2018.
 */

public class Fourteen extends Fragment {
    public static int Score=0;
    Button button;
    String sques;
    Questions ques;
    DatabaseReference mDatabase;
    TextView text;
    RadioGroup radioGroup;
    RadioButton op1;
    RadioButton op2;
    RadioButton op3;
    RadioButton op4;
    String sop1;
    String sop2;
    String sop3;
    String sop4;
    String sans;
    TextView textView;
    ProgressBar prg;


    private static final String FORMAT = "%02d:%02d:%02d";
    int x=0;
    static CountDownTimer cfirst;

    public Fourteen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first, container, false);
        button=view.findViewById(R.id.next2);
        radioGroup=view.findViewById(R.id.grp);
        op1=view.findViewById(R.id.first);
        op2=view.findViewById(R.id.second);
        op3=view.findViewById(R.id.third);
        op4=view.findViewById(R.id.fourth);
        text=view.findViewById(R.id.textView);
        prg=view.findViewById(R.id.progressBar3);
        textView=view.findViewById(R.id.timer);

        mDatabase = FirebaseDatabase.getInstance().getReference("Question");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ques = dataSnapshot.child(1+"").getValue(Questions.class);
                sques = ques.getQues();
                sop1=ques.getOp1();
                sop2=ques.getOp2();
                sop3=ques.getOp3();
                sop4=ques.getOp4();
                sans=ques.getAns();
                prg.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);

                text.setText("Q.)"+sques);


                op1.setText(sop1);
                op2.setText(sop2);
                op3.setText(sop3);
                op4.setText(sop4);



                cfirst = new CountDownTimer(15000,1000) {
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
                        if(op1.isChecked()&&op1.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Result();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else if(op2.isChecked()&&op2.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Result();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else if(op3.isChecked()&&op3.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Result();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else if(op4.isChecked()&&op4.getText().toString().compareTo(sans)==0)
                        {
                            First.Score++;
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Result();
                            ft.replace(R.id.frames, frag);
                            ft.commit();
                        }
                        else{
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            Fragment frag = new Result();
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
                if(op1.isChecked()&&op1.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cfirst.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if(op2.isChecked()&&op2.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cfirst.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if(op3.isChecked()&&op3.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cfirst.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if(op4.isChecked()&&op4.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                    cfirst.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else if(op1.isChecked()||op2.isChecked()||op3.isChecked()||op4.isChecked()) {
                    cfirst.cancel();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
                }
                else
                    Toast.makeText(getContext(), "Choose one answer", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
