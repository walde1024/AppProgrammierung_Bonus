package bonus.de.hska.klausurbonus.persistence.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.persistence.db.OfferPlannerDbHelper;

/**
 * Created by Walde on 27.12.16.
 */
public class OfferPlannerContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "bonus.de.hska.klausurbonus.provider";

    public static final String OFFERS_URI = "content://" + PROVIDER_NAME + "/" + OfferPlannerContract.OfferEntry.TABLE_NAME;
    public static final String OFFERS_URI_FAVORITE_FIRST = "content://" + PROVIDER_NAME + "/" + OfferPlannerContract.OfferEntry.TABLE_NAME + "/favoriteFirst";
    public static final String FAVORITE_OFFERS_URI = "content://" + PROVIDER_NAME + "/" + OfferPlannerContract.FavoriteOffer.TABLE_NAME;

    private OfferPlannerDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new OfferPlannerDbHelper(getContext());

        return true;
    }

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PROVIDER_NAME, OfferPlannerContract.OfferEntry.TABLE_NAME, 1);
        uriMatcher.addURI(PROVIDER_NAME, OfferPlannerContract.OfferEntry.TABLE_NAME + "/#", 2);
        uriMatcher.addURI(PROVIDER_NAME, OfferPlannerContract.FavoriteOffer.TABLE_NAME, 3);
        uriMatcher.addURI(PROVIDER_NAME, OfferPlannerContract.FavoriteOffer.TABLE_NAME + "/#", 4);
        uriMatcher.addURI(PROVIDER_NAME, OfferPlannerContract.OfferEntry.TABLE_NAME + "/favoriteFirst", 5);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase sqLiteDatabase = null;

        switch (uriMatcher.match(uri)) {
            case 1:
                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.OfferEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case 2:
                selection = OfferPlannerContract.OfferEntry._ID + " = ?";
                selectionArgs = new String[] {uri.getLastPathSegment()};

                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.OfferEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;

            case 3:
                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.FavoriteOffer.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case 4:
                selection = OfferPlannerContract.FavoriteOffer._ID + " = ?";
                selectionArgs = new String[] {uri.getLastPathSegment()};

                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.FavoriteOffer.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;

            case 5:
                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.FavoriteOffer.TABLE_NAME, null, OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME + " = ?", selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    int firstOfferId = cursor.getInt(cursor.getColumnIndex(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_OFFER_ID));
                    cursor.close();

                    String query = "Select * From " + OfferPlannerContract.OfferEntry.TABLE_NAME + " where " + OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME + " = ? " +
                            "order by case _id when ? then 0 else 1 end";
                            //"Select * From " + OfferPlannerContract.OfferEntry.TABLE_NAME + " where " + OfferPlannerContract.OfferEntry._ID + " != ? " +
                            //"and " + OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME + " = ? ";

                    cursor = sqLiteDatabase.rawQuery(query, new String[]{selectionArgs[0], String.valueOf(firstOfferId)});
                }
                else {
                    cursor.close();
                    cursor = sqLiteDatabase.query(OfferPlannerContract.OfferEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }

                break;

            default:
                throw new IllegalArgumentException("Wrong URI");
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/vnd.bonus.de.hska.klausurbonus.offer";
            case 2:
                return "vnd.android.cursor.item/vnd.bonus.de.hska.klausurbonus.offers";
            case 3:
                return "vnd.android.cursor.dir/vnd.bonus.de.hska.klausurbonus.favoriteOffer";
            case 4:
                return "vnd.android.cursor.item/vnd.bonus.de.hska.klausurbonus.favoriteOffers";
            default:
                throw new IllegalArgumentException("Wrong URI");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        switch (uriMatcher.match(uri)) {
            case 3:
                delete(uri,
                        OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME + " = ?",
                        new String[]{contentValues.getAsString(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME)});

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                long id = db.insert(OfferPlannerContract.FavoriteOffer.TABLE_NAME, null, contentValues);
                db.close();

                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(FAVORITE_OFFERS_URI + "/" + id);
        }

        throw new IllegalArgumentException("Wrong URI");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case 3:
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                int count = db.delete(OfferPlannerContract.FavoriteOffer.TABLE_NAME, selection, selectionArgs);
                db.close();

                getContext().getContentResolver().notifyChange(uri, null);
                return count;
        }

        throw new IllegalArgumentException("Wrong URI");
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }
}
