package c346.rp.edu.demodatabasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        //initialize the variables with UI here
        tvID = findViewById(R.id.tvID);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etContent = findViewById(R.id.etContent);


        Intent i = getIntent();
        data = (note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setNoteContent(etContent.getText().toString());
                dbh.updateNote(data);
                dbh.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.getId());
                dbh.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);
                finish();
            }
        });





    }

}

