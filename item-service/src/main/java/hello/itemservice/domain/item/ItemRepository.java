package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    //동시에 여러 쓰레드에서 접근하면 HashMap쓰면 안된다, ConcurrentHashMap 써야한다!!
    private static final Map<Long, Item> store = new HashMap<>();
    //이것도 long쓰면 안되고 atomic long써야한다.
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //프로젝트가 크면 updateParam id는 쓰지 않으니까 dto를 만드는게 좋다!!
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void clearStore() {
        store.clear();

    }
}
