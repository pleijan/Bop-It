package uqac.dim.bop_it.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class}, version=1)
public abstract class ScoreBD extends RoomDatabase {

    private static ScoreBD INSTANCE;
    public abstract ScoreDao scoreDao();

    public static ScoreBD getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,ScoreBD.class,"scoreDatabase").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
