package c346.rp.edu.demodatabasecrud;

import java.io.Serializable;

public class note implements Serializable {
    private int id;
    private String noteContent;

    public note(int id, String noteContent){
        this.id = id;
        this.noteContent = noteContent;

    }

    public int getId() {
        return id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
    @Override
    public String toString() {
        return "ID:" + id + ", " + noteContent;
    }



}
