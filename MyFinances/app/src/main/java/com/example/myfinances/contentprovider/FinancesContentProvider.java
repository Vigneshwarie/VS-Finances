package com.example.myfinances.contentprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentProvider;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class FinancesContentProvider extends ContentProvider {


    static final String PROVIDER_NAME = "com.example.myfinances";

    static final String LOAN_TABLE_NAME = "loans";
    static final String CD_TABLE_NAME = "cds";
    static final String CHECKING_TABLE_NAME = "checkingaccounts";

    static final int CD_URI = 1;
    static final int CHECKING_URI = 2;
    static final int LOAN_URI = 3;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cd", CD_URI);
        uriMatcher.addURI(PROVIDER_NAME, "checkingaccount", CHECKING_URI);
        uriMatcher.addURI(PROVIDER_NAME, "loan", LOAN_URI);
    }

    public static final Uri CD_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/cd");
    public static final Uri LOAN_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/loan");
    public static final Uri CHECKING_CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/checkingaccount");


    static final String _ID = "_id";
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String CURRENT_BALANCE = "CURRENT_BALANCE";

    public static final String INITIAL_BALANCE = "INITIAL_BALANCE";
    public static final String PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
    public static final String INTEREST_RATE = "INTEREST_RATE";

    static final int ACCOUNT_URI = 2;


    private static HashMap<String, String> values;


    private SQLiteDatabase db;
    static final String DATABASE_NAME = "MyFinances";

    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + CHECKING_TABLE_NAME +
            "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ACCOUNT_NUMBER TEXT NOT NULL, " +
            "CURRENT_BALANCE TEXT NOT NULL);";

    static final String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " + CD_TABLE_NAME +
            "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ACCOUNT_NUMBER TEXT NOT NULL, " +
            "INITIAL_BALANCE TEXT NOT NULL, " +
            "CURRENT_BALANCE TEXT NOT NULL, " +
            "INTEREST_RATE TEXT NOT NULL);";

    static final String CREATE_TABLE3 = "CREATE TABLE IF NOT EXISTS " + LOAN_TABLE_NAME +
            "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ACCOUNT_NUMBER TEXT NOT NULL, " +
            "INITIAL_BALANCE TEXT NOT NULL, " +
            "CURRENT_BALANCE TEXT NOT NULL, " +
            "PAYMENT_AMOUNT TEXT NOT NULL, " +
            "INTEREST_RATE TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // creates required tables when DB is initialized
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE2);
            db.execSQL(CREATE_TABLE3);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CD_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + LOAN_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CHECKING_TABLE_NAME);
            onCreate(db);
        }
    }

    // initializes the DB object for read/write operations
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    // queries the DB to get info as per selection
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    // returns MIME type of the data in DB
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    // inserts value into the DB and returns the URI object
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID;
        System.out.println(uri);
        System.out.println(uriMatcher.match(uri));
        switch (uriMatcher.match(uri)) {
            case LOAN_URI:
                rowID = db.insert(LOAN_TABLE_NAME, "", contentValues);
                if (rowID > 0) {
                    Uri _uri = ContentUris.withAppendedId(LOAN_CONTENT_URI, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                throw new SQLException("Failed to add a record into " + uri);
            case CD_URI:
                rowID = db.insert(CD_TABLE_NAME, "", contentValues);
                if (rowID > 0) {
                    Uri _uri = ContentUris.withAppendedId(CD_CONTENT_URI, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                throw new SQLException("Failed to add a record into " + uri);
            case CHECKING_URI:
                rowID = db.insert(CHECKING_TABLE_NAME, "", contentValues);
                if (rowID > 0) {
                    Uri _uri = ContentUris.withAppendedId(CHECKING_CONTENT_URI, rowID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                throw new SQLException("Failed to add a record into " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);

        }
    }

    // deletes value from the DB and returns number of rows deleted
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    // updates DB values and returns number of rows updated
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}


