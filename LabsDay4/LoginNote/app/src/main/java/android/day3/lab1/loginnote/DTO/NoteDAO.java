package android.day3.lab1.loginnote.DTO;

public class NoteDAO {
    private String NoteTitle;
    private String NoteBody;

    public NoteDAO()
    {}

    public NoteDAO(String noteTitle, String noteBody) {
        NoteTitle = noteTitle;
        NoteBody = noteBody;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public String getNoteBody() {
        return NoteBody;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public void setNoteBody(String noteBody) {
        NoteBody = noteBody;
    }
}
