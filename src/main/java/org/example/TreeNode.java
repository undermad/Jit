package org.example;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public String name;
    public boolean isDirectory;
    public List<TreeNode> children;

    public TreeNode(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }


}