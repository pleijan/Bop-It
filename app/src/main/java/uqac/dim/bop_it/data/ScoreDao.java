package uqac.dim.bop_it.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM score ORDER BY temps DESC")
    List<Score> getAllScore();

    @Insert
    void insertAll(Score... scores);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addScore(Score score);

    @Query ("delete from score")
    void deleteAllScore();
}
