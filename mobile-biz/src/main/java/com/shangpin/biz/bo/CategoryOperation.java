package com.shangpin.biz.bo;

import java.util.List;

public class CategoryOperation {
	private String type;
	private String name;
	private String nameEN;
	private String width;
	private String height;
    private String pic;
	private List<CategoryOperationContent> list;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	/**
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<CategoryOperationContent> getList() {
		return list;
	}

	public void setList(List<CategoryOperationContent> list) {
		this.list = list;
	}

}
