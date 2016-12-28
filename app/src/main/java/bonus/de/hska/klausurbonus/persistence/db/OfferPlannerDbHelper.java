package bonus.de.hska.klausurbonus.persistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import bonus.de.hska.klausurbonus.persistence.SampleDataProvider;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;

/**
 * Created by Walde on 27.12.16.
 */
public class OfferPlannerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "OfferPlanner.db";

    public OfferPlannerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(OfferPlannerContract.SQL_CREATE_OFFER_TABLE);
        db.execSQL(OfferPlannerContract.SQL_CREATE_FAVORITE_OFFER_TABLE);
        new SampleDataProvider().fillDatabaseWithSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(OfferPlannerContract.SQL_DELETE_OFFER_TABLE);
        db.execSQL(OfferPlannerContract.SQL_DELETE_FAVORITE_OFFER_TABLE);
        onCreate(db);
    }
}
