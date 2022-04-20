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

    @ColumnInfo(name = "nbEpreuve")
    public int nbEpreuve;

    public Score(  String pseudo, int nbEpreuve){

        this.pseudo=pseudo;
        this.nbEpreuve=nbEpreuve;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + pseudo + " : " + nbEpreuve ;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbEpreuve() {
        return nbEpreuve;
    }
}
