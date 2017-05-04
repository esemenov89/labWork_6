package code.model.dao;

import code.model.pojo.StorageUnit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitDAO {
    ArrayList<StorageUnit> getAllStorageUnits() throws SQLException;
    StorageUnit getStorageUnitByISN(String isn) throws SQLException;
    void delStorageUnitByISN(String isn) throws SQLException;
    void addStorageUnit(StorageUnit storageUnit) throws SQLException;
}
