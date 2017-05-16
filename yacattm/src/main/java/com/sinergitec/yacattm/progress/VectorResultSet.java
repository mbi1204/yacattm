package com.sinergitec.yacattm.progress;

import java.util.Vector;
import com.progress.open4gl.InputResultSet;

/**
 * 
 * @author mendoza
 *
 */


public class VectorResultSet extends InputResultSet {

    private int rowNum;
    @SuppressWarnings("rawtypes")
	Vector rows;
    @SuppressWarnings("rawtypes")
	Vector currentRow;

    /**
     * Constructor.
     * Recibe un vector de Vectores con los datos de la tabla temporal de entrada de progress
     * @param rows Vector
     */

    
    @SuppressWarnings("rawtypes")
	public VectorResultSet(Vector rows) {
        this.rows = rows;
        currentRow = null;
        rowNum = 0;
    }

    @SuppressWarnings("rawtypes")
	public boolean next() throws java.sql.SQLException {
        try {
            currentRow = (Vector) rows.elementAt(rowNum++);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Object getObject(int columnIndex) throws java.sql.SQLException {
        return currentRow.elementAt(columnIndex - 1);
    }
}