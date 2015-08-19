package reli.reliapp.co.il.reli.dataStructures;

import reli.reliapp.co.il.reli.utils.Const;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ReliTag")
public class ReliTag extends ParseObject {
    private String tagName;

    public ReliTag() {
        // Default Constructor is a must
    }

    public ReliTag(String tagName) {
        setTagName(tagName);
        saveEventually();
    }

    public String getTagName() {
        return getString(Const.COL_TAG_NAME);
    }

    public void setTagName(String tagName) {
        put(Const.COL_TAG_NAME, tagName);
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof ReliTag) &&
                (((ReliTag)o).getTagName().equals(this.getTagName())));

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}