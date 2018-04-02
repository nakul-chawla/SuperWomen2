package buzz.pentagon.superwomen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    String sques[];
    Questions ques[];
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment frag=new Fourth();
             ft.replace(R.id.frames, frag);
                ft.commit();

//                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
//                intent.putExtra("Questions",sques);
//                startActivity(intent);



    }


}


