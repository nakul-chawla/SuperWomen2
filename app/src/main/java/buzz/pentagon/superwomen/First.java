package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment {
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

    public First() {
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

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    ques = dataSnapshot.child("Question").child(1+"").getValue(Questions.class);
                    sques = ques.getQues();
                    sop1=ques.getOp1();
                    sop2=ques.getOp2();
                    sop3=ques.getOp3();
                    sop4=ques.getOp4();
                    sans=ques.getAns();
                    text.setText(sques);

                    op1.setText(sop1);
                    op2.setText(sop2);
                    op3.setText(sop3);
                    op4.setText(sop4);
                }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(op1.isChecked())
            {
                First.Score++;
            }
            if(op1.isChecked()||op2.isChecked()||op3.isChecked()||op4.isChecked()) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment frag = new Third();
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
