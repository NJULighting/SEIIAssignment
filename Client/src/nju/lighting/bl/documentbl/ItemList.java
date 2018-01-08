package nju.lighting.bl.documentbl;

import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class ItemList<Item extends DocItem> {
    private List<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public <T> void add(T t, Function<T, Item> function) {
        items.add(function.apply(t));
    }

    public <PO> List<PO> toPO(Function<Item, PO> function) {
        return transform(function);
    }

    public <VO> List<VO> toVO(Function<Item, VO> function) {
        return transform(function);
    }

    public <Attribute> boolean containItemWithAttribute(Attribute attribute, Function<Item, Attribute> function) {
        for (Item i : items) {
            if (attribute.equals(function.apply(i)))
                return true;
        }
        return false;
    }

    public <T> List<T> transform(Function<Item, T> function) {
        return items.stream().map(function).collect(Collectors.toList());
    }

    public double transformAndSum(ToDoubleFunction<Item> numberFunction) {
        return items.stream().mapToDouble(numberFunction).sum();
    }

    public void redFlush() {
        // Set id to 0 so that it can be added to the database successfully
        items.forEach(item -> {
            item.setId(0);
            item.redFlush();
        });
    }

    public ResultMessage approve() {
        ResultMessage res = ResultMessage.FAILURE;
        for (Item i : items) {
            if (!(res = i.approve()).success())
               break;
        }

        return res;
    }

    public List<Item> getItems() {
        return items;
    }
}
