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
        return items.stream().map(function).collect(Collectors.toList());
    }

    public <VO> List<VO> toVO(Function<Item, VO> function) {
        return items.stream().map(function).collect(Collectors.toList());
    }

    public <Attribute> boolean containItemWithAttribute(Attribute attribute, Function<Item, Attribute> function) {
        for (Item i : items) {
            if (attribute.equals(function.apply(i)))
                return true;
        }
        return false;
    }

    public <T> List<T> transformItemToObject(Function<Item, T> function) {
        return items.stream().map(function).collect(Collectors.toList());
    }

    public double transformItemToNumber(ToDoubleFunction<Item> numberFunction) {
        return items.stream().mapToDouble(numberFunction).sum();
    }

    public void redFlush() {
        items.forEach(Item::redFlush);
    }

    public ResultMessage approve() {
        for (Item i : items) {
            if (i.approve() != ResultMessage.SUCCESS)
                return ResultMessage.FAILURE;
        }

        return ResultMessage.SUCCESS;
    }
}
