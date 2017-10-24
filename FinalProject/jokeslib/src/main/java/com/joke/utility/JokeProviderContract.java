package com.joke.utility;

//import android.content.ContentResolver;
//import android.content.ContentUris;
//import android.net.Uri;
//import android.provider.BaseColumns;

public class JokeProviderContract {

}

// Joke DB definition
//public class JokeProviderContract implements BaseColumns {

//
//    /**
//     * This it the content authority for DroidTermsExample provider.
//     */
//    public static final String CONTENT_AUTHORITY = "com.joke.JokeProvider";
//
//    /**
//     * This is the {@link Uri} on which all other DroidTermsExample Uris are built.
//     */
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//
//    /**
//     * The path for terms
//     */
//    public static final String PATH_TERMS = "terms";
//
//    /**
//     * This is the {@link Uri} used to get a full list of terms and definitions.
//     */
//    public static final Uri CONTENT_URI =
//            BASE_CONTENT_URI.buildUpon().appendPath(PATH_TERMS).build();
//
//
//    /**
//     * This is a String type that denotes a Uri references a list or directory.
//     */
//    public static final String CONTENT_TYPE =
//            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS;
//
//    /**
//     * This is a String type that denotes a Uri references a single item.
//     */
//    public static final String CONTENT_ITEM_TYPE =
//            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TERMS;
//
//
//    // Declaring all these as constants makes code a lot more readable.
//    // It also looks a more like SQL.
//
//    /**
//     * This is the version of the database for {@link android.database.sqlite.SQLiteOpenHelper}.
//     */
//    public static final int DATABASE_VERSION = 1;
//
//    /**
//     * This is the name of the SQL table for terms.
//     */
//    public static final String TERMS_TABLE = "joke";
//    /**
//     * This is the name of the SQL database for terms.
//     */
//    public static final String DATABASE_NAME = "jokeDb";
//
//    /**
//     * This is the column name in the SQLiteDatabase for the joke title.
//     */
//    public static final String COLUMN_JOKE_TITLE = "title";
//    /**
//     * This is the column name in the SQLiteDatabase for the joke content.
//     */
//    public static final String COLUMN_JOKE_CONTENT = "content";
//
//    public static final String COLUMN_LAUGH_RATING = "rating";
//
//    /**
//     * This is an array containing all the column headers in the terms table.
//     */
//    public static final String[] COLUMNS =
//            {_ID, COLUMN_JOKE_TITLE, COLUMN_JOKE_CONTENT,COLUMN_LAUGH_RATING};
//
//    /**
//     * This is the index of the column in the joke table
//     */
//    public static final int COLUMN_INDEX_ID = 0;
//
//    public static final int COLUMN_INDEX_TITLE = 1;
//
//    public static final int COLUMN_INDEX_CONTENT = 2;
//
//    public static final int COLUMN_INDEX_RATING = 3;
//
//    /**
//     * This method creates a {@link Uri} for a single term, referenced by id.
//     * @param id The id of the term.
//     * @return The Uri with the appended id.
//     */
//    public static Uri buildTermUriWithId(long id) {
//        return ContentUris.withAppendedId(CONTENT_URI, id);
//    }
//
//
//
//
//
//}
