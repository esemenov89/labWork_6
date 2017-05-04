package code.services;

import code.model.dao.StorageUnitDAO;
import code.model.pojo.StorageUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Service for working with storage units
 */
@Service
public class StorageUnitServiceImpl implements StorageUnitService {

    private StorageUnitDAO storageUnitDAO;

    @Autowired
    public void setStorageUnitDAO(StorageUnitDAO storageUnitDAO) {
        this.storageUnitDAO = storageUnitDAO;
    }

    /**
     *
     * @return all storage units from database
     */
    @Override
    public ArrayList<StorageUnit> getAllStorageUnits() throws SQLException {
        ArrayList<StorageUnit> storageUnits = null;
        try {
            storageUnits = storageUnitDAO.getAllStorageUnits();
        } catch (SQLException e) {
            throw e;
        }
        return storageUnits;
    }

    /**
     *
     * @param isn - identifier of storage unit
     * @return storage unit with isn identifier from database
     */
    @Override
    public StorageUnit getStorageUnitByISN(String isn) throws SQLException {
        StorageUnit storageUnit = null;
        try {
            storageUnit = storageUnitDAO.getStorageUnitByISN(isn);
        } catch (SQLException e) {
            throw e;
        }
        return storageUnit;
    }

    /**
     * Add storage unit in database
     * @param storageUnit
     */
    @Override
    public void addStorageUnit(StorageUnit storageUnit) throws SQLException {
        try {
            storageUnitDAO.addStorageUnit(storageUnit);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * delete storage unit with isn identifier from database
     * @param isn
     */
    @Override
    public void delStorageUnitByISN(String isn) throws SQLException {
        try {
            storageUnitDAO.delStorageUnitByISN(isn);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Validate storage unit
     * Use it before added storage unit in database
     * @param author - of storage unit
     * @param title - of storage unit
     * @param publishingHouse - of storage unit
     * @param city - where storage unit was published
     * @param year - when storage unit was published
     * @param pagesCount - of storage unit
     * @param isn - identifier of storage unit
     * @param text - of storage unit
     * @return validated storage unit or storage this errors
     */
    @Override
    public StorageUnit validateStorageUnit(String author, String title, String publishingHouse, String city, String year,
                                           String pagesCount, String isn, String text){

        StorageUnit storageUnit = new StorageUnit(author, title, publishingHouse, city, 0, 0, isn, text);

        Pattern p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ0-9-\\s.,_]{1,20}$+");
        Matcher m = p.matcher(author);
        if (!m.matches()){
            storageUnit.setAuthor("@Error1");
        }
        p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ.,0-9-\\s_]{1,50}$+");
        m = p.matcher(title);
        if (!m.matches()){
            storageUnit.setTitle("@Error1");
        }
        m = p.matcher(publishingHouse);
        if (!m.matches()){
            storageUnit.setPublishingHouse("@Error1");
        }
        m = p.matcher(city);
        if (!m.matches()){
            storageUnit.setCity("@Error1");
        }
        p = Pattern.compile("^[0-9]{1,4}$+");
        m = p.matcher(year);
        if (!m.matches()){
            storageUnit.setYear(-1);
        }
        else{
            storageUnit.setYear(Integer.parseInt(year));
        }
        m = p.matcher(pagesCount);
        if (!m.matches()){
            storageUnit.setPagesCount(-1);
        }
        else{
            storageUnit.setPagesCount(Integer.parseInt(pagesCount));
        }
        p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ0-9-]{1,50}$+");
        m = p.matcher(isn);
        if (!m.matches()){
            storageUnit.setTitle("@Error1");
        }
        if (text.replaceAll(" ","").equals("") || text.length()>1_000_000){
            storageUnit.setText("@Error1");
        }

        return storageUnit;
    }
}
