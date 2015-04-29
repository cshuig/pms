package com.jiezhu.pms.comm.page;

import java.util.List;

public interface PaginatedList {

    List getList();

    int getPageNumber();

    int getObjectsPerPage();

    int getFullListSize();

    String getSortCriterion();

    int getSortDirection();

    String getSearchId();
}
