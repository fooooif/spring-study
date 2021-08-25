package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    //ModelAttribute는 2가지 처리를 해준다 자동으로 model에 넣어준다.
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item
//             모델도 날려도 된다.
//            ,Model model
                            ) {
        itemRepository.save(item);
//        model.addAttribute("item", item);

        return "basic/item";
    }
    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        // model에 Item->item으로 담긴다 name value를 쓰지 않아도
        itemRepository.save(item);
//        model.addAttribute("item", item);

        return "basic/item";
    }



    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 100));
        itemRepository.save(new Item("itemB", 20000, 200));
    }
}
