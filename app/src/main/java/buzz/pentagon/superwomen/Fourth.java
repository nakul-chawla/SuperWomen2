package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fourth extends Fragment {
    Button button;
    Questions ques;

    DatabaseReference mDatabase;
    public static int no;

    public Fourth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fourth, container, false);
        button=view.findViewById(R.id.start);
     //   sques=new String[4];
     //   ques=new Questions[4];
        mDatabase = FirebaseDatabase.getInstance().getReference("Question");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            ques=dataSnapshot.child(""+1).getValue(Questions.class);

//                    ques[i] = dataSnapshot.child("Question").child(i+"").getValue(Questions.class);
//                    sques[i] = ques[i].getQues();
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment frag=new First();
                ft.replace(R.id.frames, frag);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
