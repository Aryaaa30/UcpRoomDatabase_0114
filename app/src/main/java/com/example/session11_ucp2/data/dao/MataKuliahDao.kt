import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Insert
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)

    @Query("SELECT * FROM mataKuliah")
    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    @Update
    suspend fun updateMataKuliah(mataKuliah: MataKuliah)

    @Delete
    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)

    @Query("SELECT * FROM mataKuliah WHERE kode = :kode")
    fun getDetailMataKuliah(kode: String): Flow<MataKuliah>
}
