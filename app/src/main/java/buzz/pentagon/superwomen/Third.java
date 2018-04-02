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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
    RadioButton op3;
    RadioButton op4;
    String sop1;
    String sop2;
    String sop3;
    String sop4;



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
//        op3=view.findViewById(R.id.third);
//        op4=view.findViewById(R.id.fourth);
        text=view.findViewById(R.id.textView);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                t = dataSnapshot.child("Question").child(2+"").getValue(True.class);
                sques = t.getQues();
                sop1=t.getOp1();
                sop2=t.getOp2();
                text.setText(sques);

                op1.setText(sop1);
                op2.setText(sop2);

            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (op1.isChecked())
                {
                    First.Score++;
                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment frag=new Second();
                ft.replace(R.id.frames, frag);
                ft.commit();

            }
        });
        return view;
    }

}
