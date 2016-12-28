package bonus.de.hska.klausurbonus.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;

/**
 * Created by Walde on 27.12.16.
 */
public class DatabaseReader {

    public static final String TIME_EIGHT_THIRTY = "08:30";
    public static final String TIME_NINE = "09:00";
    public static final String TIME_TEN = "10:00";
    public static final String TIME_ELEVEN = "11:00";
    public static final String TIME_THIRTEEN = "13:00";
    public static final String TIME_FOURTEEN = "14:00";
    public static final String TIME_FIFTEEN = "15:00";
    public static final String TIME_SIXTEEN = "16:00";

    public static Cursor getOffers(SQLiteDatabase db, String time) {
        String selection = OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME + " = ?";

        Cursor cursor = db.query(
                OfferPlannerContract.OfferEntry.TABLE_NAME, // The table to query
                null,                                       // The columns to return
                selection,                                  // The columns for the WHERE clause
                new String[]{time},                         // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );

        return cursor;
    }
}
