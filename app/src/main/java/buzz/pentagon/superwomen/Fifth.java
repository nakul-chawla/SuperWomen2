package buzz.pentagon.superwomen;


import android.os.Bundle;
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

    public Fifth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fifth, container, false);
        submit=view.findViewById(R.id.next);
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit.getText().toString().compareTo(sans)==0)
                {
                    First.Score++;
                }


                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment frag = new Result();
                    ft.replace(R.id.frames, frag);
                    ft.commit();
            }
        });
        return view;
    }

}
