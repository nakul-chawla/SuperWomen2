package buzz.pentagon.superwomen;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by arshiyaahuja on 06/04/18.
 */

public class SugnUp extends AppCompatActivity {private FirebaseAuth auth;
    EditText mail;
    EditText pass;
    Button submit;
    //   View view;
    ProgressBar progressBar;
    DatabaseReference mref;
    Sign s[];
    String id[];
    String pas[];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        s=new Sign[5];
        id=new String[5];
        pas=new String[5];

        //mail = (EditText) view.findViewById(R.id.mail);
        mail=(EditText)findViewById(R.id.mail) ;
        pass = (EditText)findViewById(R.id.editText3);
        submit = (Button)findViewById(R.id.button4);
        mref= FirebaseDatabase.getInstance().getReference().child("SignUp");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i<4;i++){
            s[i]=dataSnapshot.child(""+i).getValue(Sign.class);
            id[i]=s[i].getEmail();
            pas[i]=s[i].getPassword();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().equals("")) {
                    //Toast.makeText(SugnUp.this, "Enter email address!", Toast.LENGTH_LONG).show();
                    Toast.makeText(SugnUp.this, "Enter email", Toast.LENGTH_SHORT).show();
                }

//yes
                if (pass.getText().toString().equals("")) {
                    Toast.makeText(SugnUp.this, "Enter password!", Toast.LENGTH_LONG).show();
                }

                for(int i=1;i<4;i++){
                if(mail.getText().toString().compareTo(id[i])==0 && pass.getText().toString().compareTo(pas[i])==0){
                    Bundle bundle = new Bundle();
                    bundle.putInt("edtText",i);
                    // set Fragmentclass Arguments
                    Result fragobj = new Result();
                    fragobj.setArguments(bundle);
                    Intent intent=new Intent(SugnUp.this,MainActivity.class);
                    startActivity(intent);

                }

                }
//                String email = mail.getText().toString();
//                final String password = pass.getText().toString();


//
//           //     progressBar.setVisibility(View.VISIBLE);
//
//                //authenticate user
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(SugnUp.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (!task.isSuccessful()) {
//                                    // there was an error
//                                    if (password.length() < 6) {
//                                        Toast.makeText(SugnUp.this,"Enter valid password",Toast.LENGTH_LONG).show();
//
//                                    } else {
//                                        Toast.makeText(SugnUp.this, "Authentecation Failed", Toast.LENGTH_LONG).show();
//                                    }
//                                } else {
//                                    Intent intent=new Intent(SugnUp.this,MainActivity.class);
//
//                                    startActivity(intent);
//
//                                }
//                            }
//
//
//                        });
//
//
            }
        });
    }

}



