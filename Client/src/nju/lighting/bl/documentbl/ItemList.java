package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public abstract class ItemList<Item> {
    protected List<Item> items;

    protected ItemList() {
        items = new ArrayList<>();
    }

    protected <T> void add(T t, Function<T, Item> function) {
        items.add(function.apply(t));
    }

    public abstract List<DocPO> toPO(String docId);
}
