package com.qedum.simplyposted.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by bogdan.aksonenko on 4/21/17.
 */

public class PostExpandable extends ExpandableGroup<Post> {

    public PostExpandable(String title, List<Post> items) {
        super(title, items);
    }
}
