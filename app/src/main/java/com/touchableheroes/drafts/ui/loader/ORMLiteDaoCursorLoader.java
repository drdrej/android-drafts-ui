package com.touchableheroes.drafts.ui.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.stmt.QueryBuilder;
import com.touchableheroes.drafts.db.BasicLongIDDao;


/**
 * Created by asiebert on 23.07.14.
 */
public class ORMLiteDaoCursorLoader extends CursorLoader {

    private final BasicLongIDDao<?> dao;

    public ORMLiteDaoCursorLoader(final Context context, final BasicLongIDDao<?> dao) {
        super(context);
        this.dao = dao;
    }

    @Override
    public Cursor loadInBackground() {
        final QueryBuilder<?, ?> qb = dao.queryBuilder();

        try {
            qb.prepare();

            final CloseableIterator<?> iterator = dao.iterator();

            final AndroidDatabaseResults results =
                    (AndroidDatabaseResults)iterator.getRawResults();
            return results.getRawCursor();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
