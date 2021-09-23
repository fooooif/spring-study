package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * x to One (Many to one, One to One
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        List<Order> allByString = orderRepository.findAllByString(new OrderSearch());
        for (Order order : allByString) {
            order.getMember().getName(); //Lazy강제 초기화.
            order.getDelivery().getAddress();// Lazy강제 초기화.
        }
        return allByString;
    }

    /**
     * address => value object
     * 참고 jpa
     * @return
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> collect = all.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(toList());
        return collect;
    }
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3(){
        return orderRepository.findAllWithMemberDelivery()
                .stream()
                .map(SimpleOrderDto::new)
                .collect(toList());

    }
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4(){
        return orderRepository.findOrderDtos();

    }


    @GetMapping("/api/v5/simple-orders")
    public Result orderV5(){
        //order2개.
        //N + 1 -> 1 + 회원 N + 배송 N -> 1 + 2+ 2 -> 5
        List<Order> all = orderRepository.findAllByString(new OrderSearch());


        List<SimpleOrderDto> collect = all.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(toList());

        return new Result(collect);
    }

    @Data
    static class Result<T>{
        private T data;
        public Result(T t) {
            data = t;
        }
    }
    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderdate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order o) {
            this.orderId = o.getId();
            this.name = o.getMember().getName();//Lazy 초기화 영속성 컨테스트에 없으면 찾아옴.
            this.orderdate = o.getOrderDate();
            this.orderStatus = o.getStatus();
            this.address = o.getDelivery().getAddress();//Lazy 초기화 영속성 컨테스트에 없으면 찾아옴.

        }
    }
}
