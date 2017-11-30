package co.hien.fott.android.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import co.hien.fott.android.helper.Utils;
import co.hien.fott.android.model.Article;

/**
 * Created by nguyenvanhien on 11/29/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper implements DatabaseConstants {

    public static final int DB_VERSION = 4;
    SQLiteDatabase db;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        this.db.execSQL("CREATE TABLE " + TABLE_ARTICLE + "(" + COL_ID
                + " INTEGER PRIMARY KEY NOT NULL, " + " " + COL_CONTENT
                + " TEXT NOT NULL);");
        this.db = db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertArticle(Article item){
        db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_ARTICLE + " (" + COL_CONTENT
                + ") VALUES ('" + item.toJson() + "');");
        db.close();
    }

    public ArrayList<Article> getListArticle(){
        db = this.getReadableDatabase();
        ArrayList<Article> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_ARTICLE,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String itemString = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
                Article article = Utils.getArticleFromJson(itemString);
                if(article!=null) {
                    list.add(article);
                }
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void deleteAllArticle(){
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_ARTICLE +";");
        db.close();
    }

    public void insertListArticle(ArrayList<Article> list){
        for (Article item: list) {
            insertArticle(item);
        }
    }

}
