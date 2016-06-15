package com.shangpin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTest implements Pageable {

    @Override
    public int getPageNumber() {

        return 1;
    }

    @Override
    public int getPageSize() {

        return 10;
    }

    @Override
    public int getOffset() {
        //  Auto-generated method stub
        return 0;
    }

    @Override
    public Sort getSort() {
        //  Auto-generated method stub
        return null;
    }

}
