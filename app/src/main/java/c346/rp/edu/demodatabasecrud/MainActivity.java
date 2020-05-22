package c346.rp.edu.demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, btnRetrieve;
    EditText etContent;
    ArrayList<note> al;
    ListView lv;
    ArrayAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the variables with UI here
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        etContent = findViewById(R.id.etContent);
        lv = findViewById(R.id.displayData);

        al = new ArrayList<note>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                note data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);

                i.putExtra("data", data);
                startActivityForResult(i, 9);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etContent.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(data);
                dbh.close();

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note target = al.get(0);

                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data", target);
                startActivityForResult(i, 9);

            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes());
                dbh.close();

                String txt = "";
                for (int i = 0; i< al.size(); i++){
                    note tmp = al.get(i);
                    txt += "ID:" + tmp.getId() + ", " +
                            tmp.getNoteContent() + "\n";
                }
                aa = new ArrayAdapter<note>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);



            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            btnRetrieve.performClick();
        }
    }

}

