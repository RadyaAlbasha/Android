package android.day3.lab1.loginnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.day3.lab1.loginnote.DTO.NoteDAO;

public class AdapterConnectSQL {

    private HelperSQL helper;

    public AdapterConnectSQL(Context _context) {
        helper = new HelperSQL(_context);
    }

    public long insertNote(NoteDAO noteDAO)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.TITLE,noteDAO.getNoteTitle());
        contentValues.put(helper.NOTEBODY,noteDAO.getNoteBody());
        long res =  db.insert(helper.TABLE_NAME,null,contentValues);
        db.close();
        return res;
    }
    public NoteDAO getNote(String title)
    {
        NoteDAO noteDAO = new NoteDAO();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT "+helper.TITLE+", "+helper.NOTEBODY+" FROM "+helper.TABLE_NAME+" WHERE "+ helper.TITLE+" LIKE '"+title+"'",null );
        while (cursor.moveToNext())
        {
            noteDAO.setNoteTitle(cursor.getString(0));
            noteDAO.setNoteBody(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return noteDAO;
    }

    static class HelperSQL extends SQLiteOpenHelper
    {
         private static final String DATABASE_NAME = "noteAppdb.db";
         private static final int DATABASE_VERSION = 1;
         private static final String TABLE_NAME ="Notes";
         private static final String UID="_id";
         private static final String TITLE="Title";
         private static final String NOTEBODY="NoteBody";

         public HelperSQL(Context context){
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
         }

        @Override
        public void onCreate(SQLiteDatabase db) {
             String creatTable = "CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+TITLE+" TEXT, "+NOTEBODY+" TEXT)";
            db.execSQL(creatTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
