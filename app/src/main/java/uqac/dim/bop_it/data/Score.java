package uqac.dim.bop_it.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "pseudo")
    public String pseudo;

    @ColumnInfo(name = "temps")
    public int temps;

    public Score(  String pseudo, int temps){

        this.pseudo=pseudo;
        this.temps=temps;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + pseudo + " : " + temps ;
    }

    public String getPseudo() {
        return id+" "+pseudo;
    }

    public int getTemps() {
        return temps;
    }
}
