package buzz.pentagon.superwomen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result extends Fragment {
TextView text;
DatabaseReference fb;
Sign a;
public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_result, container, false);
       a=new Sign();
//        fb=FirebaseDatabase.getInstance().getReference();
//        int b =getArguments().getInt("edtText");
        text=view.findViewById(R.id.score);
        String s;
        s=String.valueOf(First.Score);
//        a.setScore(First.Score);
//        fb.child("SignUp").child(""+b).setValue(a);

        text.setText("Your score is"+s);//yes
        return view;
    }

}
