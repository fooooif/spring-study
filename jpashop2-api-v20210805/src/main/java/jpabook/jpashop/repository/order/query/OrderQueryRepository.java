package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

   private final EntityManager em;


    public List<OrderQueryDto2> findOrderQueryDtos() {
        List<OrderQueryDto2> result = findOrders();
        result.stream().
                forEach(o -> {
                   List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
                    o.setOrderItems(orderItems);
                });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id ,i.name,oi.orderPrice,oi.count) " +
                        "from OrderItem oi " +
                        "join oi.item i " +
                        "where oi.order.id = :orderId ",OrderItemQueryDto.class)
                .setParameter("orderId",orderId)
                .getResultList();
    }

    private List<OrderQueryDto2> findOrders() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderQueryDto2(o.id,m.name, o.orderDate,o.status,m.address) from Order o " +
                        "join o.member m " +
                        "join o.delivery d", OrderQueryDto2.class)
                .getResultList();
    }
    public List<OrderQueryDto2> findAllByDto_optimization() {
        List<OrderQueryDto2> result = findOrders();
        List<Long> orderIds = result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());

        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id ,i.name,oi.orderPrice,oi.count) " +
                        "from OrderItem oi " +
                        "join oi.item i " +
                        "where oi.order.id in :orderIds ", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }

    public List<OrderQueryDto2> findAllByDto_optimization_2() {
        List<OrderQueryDto2> orders = findOrders2();
        orders.stream()
                .forEach(o -> {
                    o.setOrderItems(findOrderItems2(o.getOrderId()));
                });
        return orders;
    }

    private List<OrderQueryDto2> findOrders2() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderQueryDto2(o.id,m.name, o.orderDate,o.status,m.address) " +
                        "from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery ",OrderQueryDto2.class)
                .getResultList();
    }

    private List<OrderItemQueryDto> findOrderItems2(Long orderId) {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id ,i.name,oi.orderPrice,oi.count) " +
                        "from OrderItem oi " +
                        "join fetch oi.item i " +
                        "where oi.order.id = :orderId ", OrderItemQueryDto.class)
                .setParameter("orderId",orderId)
                .getResultList();


    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count) " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d " +
                        "join o.orderItems oi " +
                        "join oi.item i",OrderFlatDto.class)
                .getResultList();
    }
}