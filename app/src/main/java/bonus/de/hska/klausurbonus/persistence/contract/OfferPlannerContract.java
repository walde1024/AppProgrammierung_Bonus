package bonus.de.hska.klausurbonus.persistence.contract;

import android.provider.BaseColumns;

/**
 * Created by Walde on 27.12.16.
 */
public final class OfferPlannerContract {

    public static final String SQL_CREATE_OFFER_TABLE =
            "CREATE TABLE " + OfferEntry.TABLE_NAME + " (" +
                    OfferEntry._ID + " INTEGER PRIMARY KEY," +
                    OfferEntry.COLUMN_NAME_TITLE + " TEXT," +
                    OfferEntry.COLUMN_NAME_CATEGORY + " TEXT," +
                    OfferEntry.COLUMN_NAME_TIME + " TEXT," +
                    OfferEntry.COLUMN_NAME_ROOM + " TEXT," +
                    OfferEntry.COLUMN_NAME_TEACHER + " TEXT)";

    public static final String SQL_DELETE_OFFER_TABLE =
            "DROP TABLE IF EXISTS " + OfferEntry.TABLE_NAME;

    public static final String SQL_CREATE_FAVORITE_OFFER_TABLE =
            "CREATE TABLE " + FavoriteOffer.TABLE_NAME + " (" +
                    FavoriteOffer._ID + " INTEGER PRIMARY KEY," +
                    FavoriteOffer.COLUMN_NAME_OFFER_ID + " TEXT," +
                    FavoriteOffer.COLUMN_NAME_TIME + " TEXT NOT NULL UNIQUE)";

    public static final String SQL_DELETE_FAVORITE_OFFER_TABLE =
            "DROP TABLE IF EXISTS " + FavoriteOffer.TABLE_NAME;

    public static class OfferEntry implements BaseColumns {
        public static final String TABLE_NAME = "offer";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_ROOM = "room";
        public static final String COLUMN_NAME_TEACHER = "teacher";
    }

    public static class FavoriteOffer implements BaseColumns {
        public static final String TABLE_NAME = "offer_favorite";
        public static final String COLUMN_NAME_OFFER_ID = "offer_id";
        public static final String COLUMN_NAME_TIME = "time";
    }
}
