package uqac.dim.bop_it.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "pseudo")
    public String pseudo;

    @ColumnInfo(name = "temps")
    public String temps;

    public Score(  String pseudo, String temps){

        this.pseudo=pseudo;
        this.temps=temps;
    }

    @Override
    public String toString() {
        return id + " - " + pseudo + " : " + temps ;
    }
}
