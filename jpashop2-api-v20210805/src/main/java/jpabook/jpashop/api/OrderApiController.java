package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto2;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    //Entity를 직접 노출하면 안된다. 이번껀 예
    @GetMapping("/api/v1/orders")
    public List<Order> orderV1() {
        //검색 조건 없이 다 가져오는
        return orderRepository.findAllByString(new OrderSearch())
                .stream()
                .map(o ->
                {
                    o.getMember().getName();
                    o.getDelivery().getAddress();
                    o.getOrderItems()
                            .stream()
                            .forEach(oi -> oi.getItem().getName());
                    return o;
                })
                .collect(toList());


    }

    @GetMapping("api/v2/orders")
    public List<OrderDto> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> result = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return result;

    }
    @GetMapping("api/v3/orders")
    public List<OrderDto> orderV3(){
        return orderRepository.findAllWithItem()
                .stream()
                .map(OrderDto::new)
                .collect(toList());
    }
    @GetMapping("api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                       @RequestParam(value = "limit",defaultValue = "100") int limit)
    {
        return orderRepository.findAllWithMemberDelivery(offset,limit)
                .stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto2> orderV4() {

        return orderQueryRepository.findOrderQueryDtos();
    }
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto2> orderV5() {

        return orderQueryRepository.findAllByDto_optimization();
    }
    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> orderV6() {

        return orderQueryRepository.findAllByDto_flat();
    }
    //orderflat -> orderQuerydto로 변환시키기.
//    @GetMapping("/api/v7/orders")
//    public List<OrderQueryDto2> orderV6() {
//
//
//    }
    //property = > getter setter
    @Getter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order o) {
            orderId = o.getId();
            name = o.getMember().getName();
            orderDate = o.getOrderDate();
            orderStatus = o.getStatus();
            address = o.getDelivery().getAddress();
            orderItems = o.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }
    }
    @Getter
    static class OrderItemDto{

        private String itemName;
        private int orderPrice;
        private int count;
        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
