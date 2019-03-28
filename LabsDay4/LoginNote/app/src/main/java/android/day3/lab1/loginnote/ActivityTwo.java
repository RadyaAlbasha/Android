package android.day3.lab1.loginnote;

import android.content.Context;
import android.content.Intent;
import android.day3.lab1.loginnote.DTO.NoteDAO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityTwo extends AppCompatActivity {

    TextView title;
    TextView noteBody;
    Button btnBack;

    String noteBodyStr;
    String titleStr;
    AdapterConnectSQL adapter;
    NoteDAO noteDAO;
    public static final String FILE_NAME = "MyNote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        title = (TextView) findViewById(R.id.textViewTitle);
        noteBody = (TextView) findViewById(R.id.textViewNoteBody);
        btnBack= (Button) findViewById(R.id.btnCloseB);
        adapter = new AdapterConnectSQL(getApplicationContext());
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("titleNumber").toString());
        noteBody.setText(intent.getStringExtra("noteBody").toString());
        titleStr = title.getText().toString();
        noteBodyStr = noteBody.getText().toString();

        btnBack.setOnClickListener((v) -> {
            finish();
        });
    }

    public void saveInternalStorage(View view) {
        try {
            /*
            //low level
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(titleStr.getBytes());
            fos.write(noteBodyStr.getBytes());
            fos.flush();
            fos.close();*/
            DataOutputStream dos = new DataOutputStream(openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            dos.writeUTF(titleStr);
            dos.writeUTF(noteBodyStr);
            dos.flush();
            dos.close();

            title.setText("");
            noteBody.setText("");

            Toast.makeText(ActivityTwo.this , "Save Sucessfuly" ,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInternalStorage(View view) {
        try {
            DataInputStream dis = new DataInputStream(openFileInput(FILE_NAME));
            titleStr = dis.readUTF();
            noteBodyStr = dis.readUTF();
            dis.close();

            title.setText(titleStr);
            noteBody.setText(noteBodyStr);

            Toast.makeText(ActivityTwo.this , "Load Sucessfuly" ,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSQL(View view) {
        noteDAO = new NoteDAO(titleStr,noteBodyStr);
        adapter.insertNote(noteDAO);

        title.setText("");
        noteBody.setText("");

        Toast.makeText(ActivityTwo.this , "Save Sucessfuly" ,Toast.LENGTH_SHORT).show();
    }

    public void loadSQL(View view) {
        noteDAO = adapter.getNote(titleStr);

        title.setText(noteDAO.getNoteTitle());
        noteBody.setText(noteDAO.getNoteBody());

        Toast.makeText(ActivityTwo.this , "Load Sucessfuly" ,Toast.LENGTH_SHORT).show();
    }
}
