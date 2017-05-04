package code.services;

import code.model.pojo.StorageUnit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitService {
    ArrayList<StorageUnit> getAllStorageUnits() throws SQLException;
    StorageUnit getStorageUnitByISN(String isn) throws SQLException;
    void delStorageUnitByISN(String isn) throws SQLException;
    StorageUnit validateStorageUnit(String author, String title, String publishingHouse, String city, String year,
                                    String pagesCount, String isn, String text);
    void addStorageUnit(StorageUnit storageUnit) throws SQLException;
}
