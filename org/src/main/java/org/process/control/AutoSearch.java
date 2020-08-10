package org.process.control;

import org.model.dao.AutoDAO;
import org.model.objects.dto.Auto;

import java.util.List;

public class AutoSearch {

    private AutoSearch() {

    }

    public static AutoSearch autoSearch = null;



    public static AutoSearch getInstance() {
        if (autoSearch == null) {
            autoSearch = new AutoSearch();
        }
        return autoSearch;
    }

    public List<Auto> getAuto(String auto) {

        return AutoDAO.getInstance().getAuto(auto);

    }

}
